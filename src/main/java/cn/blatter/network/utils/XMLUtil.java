package cn.blatter.network.utils;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.util.List;


public class XMLUtil {
    public void generateTables(String url, Integer pid) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        Node root = document.selectSingleNode("/mxGraphModel/root");
        //遍历所有，分类vertex与edge
        List<Node> vertexList = document.selectNodes("//mxCell[@vertex='1']/parent::*");
        List<Node> edgeList = document.selectNodes("//mxCell[@edge='1']/parent::*");
//        for(Node n : vertexList) {
//            System.out.println(n.asXML());
//        }
//        for(Node n : edgeList) {
//            System.out.println(n.asXML());
//        }
        for(Node n : vertexList) {
            cn.blatter.network.domain.Node node = new cn.blatter.network.domain.Node();

        }




    }
}

