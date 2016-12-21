import java.io.File;
import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class uciRoadXPath {
    
    public static void main(String[] args) throws Exception{
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        org.w3c.dom.Document doc = db.parse(new File("uciRoad.xml"));
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xPathEvaluator = xPathfactory.newXPath();
        
        NodeList nodes1 = (NodeList)(xPathEvaluator.evaluate("//rider[majorAchievement/season/@team="
                + "'Omega Pharma-Quick Step' or ../majorAchievement/season/@team='Omega Pharma–Quick-Step']", doc, XPathConstants.NODESET));
        
        XPathExpression preNode1 = xPathEvaluator.compile("lastName");
        
        for(int i =0;i<nodes1.getLength();i++){
            Element node1 = (Element) nodes1.item(i);
            String lastName = preNode1.evaluate(node1);
            
            System.out.println("Meno jazdca za tím Omega Pharma-Quick Step: "+ lastName);
        }
        System.out.println();
        NodeList nodes2 = (NodeList)(xPathEvaluator.evaluate("/UCIRoad/worldTeam/rider[height>1.80 and contains(dateOfBirth,'Feb')]", doc, XPathConstants.NODESET));
        
        XPathExpression preNode2 = xPathEvaluator.compile("nickName");
        
        for(int i =0;i<nodes2.getLength();i++){
            Element node2 = (Element) nodes2.item(i);
            String nickName = preNode2.evaluate(node2);
            
            System.out.println("Prezývka jazdca vyššieho ako 180 cm, narodeného vo februári : "+ nickName);
        }
        
        System.out.println();
        NodeList nodes3 = (NodeList)(xPathEvaluator.evaluate("/UCIRoad/worldTeam/rider[1]/majorAchievement/season[@year = 2016]", doc, XPathConstants.NODESET));
        
        XPathExpression preNode3 = xPathEvaluator.compile("achievement");
        XPathExpression preNode31 = xPathEvaluator.compile("../../lastName");
        
        for(int i =0;i<nodes3.getLength();i++){
            Element node3 = (Element) nodes3.item(i);
            String uspechy = preNode3.evaluate(node3);
            String jazdec = preNode31.evaluate(node3);
            
            System.out.println("Jazdec : "+ jazdec + " v roku 2016 získal achievement: " +uspechy);
        }
        
        
        System.out.println();
        NodeList nodes4 = (NodeList)(xPathEvaluator.evaluate("/UCIRoad/worldTeam[@teamName = 'BMC Racing Team']/rider/majorAchievement/season[@year=2013]", doc, XPathConstants.NODESET));
        
        XPathExpression preNode4 = xPathEvaluator.compile("@team");
        XPathExpression preNode41 = xPathEvaluator.compile("../../lastName");
        
        for(int i =0;i<nodes4.getLength();i++){
            Element node4 = (Element) nodes4.item(i);
            String team = preNode4.evaluate(node4);
            String jazdec = preNode41.evaluate(node4);
            
            System.out.println("Jazdec "+ jazdec +" worldTeam-u BMC Racing Team jazdil v roku 2013 za tím: " + team);
        }
        
        
        System.out.println();
        NodeList nodes5 = (NodeList)(xPathEvaluator.evaluate("/UCIRoad/worldTeam/rider/majorAchievement/season[@team = 'Sky'][count(achievement)>2]", doc, XPathConstants.NODESET));
        
        XPathExpression preNode5 = xPathEvaluator.compile("../../nationality");
        XPathExpression preNode51 = xPathEvaluator.compile("../../firstName");
        
        for(int i =0;i<nodes5.getLength();i++){
            Element node5 = (Element) nodes5.item(i);
            String firstName = preNode51.evaluate(node5);
            String nationality = preNode5.evaluate(node5);
            
            System.out.println("Jazdec "+ firstName+ " národnosti : "+ nationality+ ""
                    + " dosiahol za jednu sezónu v tíme Sky počet úspechov väčší ako 2");
        }
        
    }
    
}
