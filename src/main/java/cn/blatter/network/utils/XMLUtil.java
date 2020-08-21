package cn.blatter.network.utils;


import cn.blatter.network.domain.Connection;
import cn.blatter.network.domain.Element;
import cn.blatter.network.domain.Pipe;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.List;


public class XMLUtil {
    private final List<Element> elementList;
    private final List<Connection> connectionList;
    private List<cn.blatter.network.domain.Node> nodeList;

    public XMLUtil(List<Element> elementList,List<Connection> connectionList) {
        this.elementList = elementList;
        this.connectionList = connectionList;
    }

    // 分析nodes返回生成
    public List<cn.blatter.network.domain.Node> generateNodes(String url, Integer pid) throws DocumentException {
        List<cn.blatter.network.domain.Node> nodeList = new ArrayList<>();
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        List<Node> vertexList = document.selectNodes("//mxCell[@vertex='1']/parent::*");
        for (Node n : vertexList) {
//            System.out.println(n.asXML());
            cn.blatter.network.domain.Node node = new cn.blatter.network.domain.Node();
            node.setElementName(n.getName());
            node.setElementId(getElementIdByName(n.getName()));
            node.setName(n.valueOf("@名称"));
            node.setProjectId(pid);
            node.setPressure(Double.parseDouble(n.valueOf("@压力")));
            node.setLoads(Double.parseDouble(n.valueOf("@载荷")));
            node.setPressureState(Boolean.parseBoolean(n.valueOf("@压力已知")));
            node.setLoadState(Boolean.parseBoolean(n.valueOf("@载荷已知")));
            node.setElevation(Double.parseDouble(n.valueOf("@海拔")));
            node.setX(Double.parseDouble(n.selectSingleNode("./mxCell/mxGeometry").valueOf("@x")));
            node.setY(Double.parseDouble(n.selectSingleNode("./mxCell/mxGeometry").valueOf("@y")));
//            System.out.println(node.toString());
            nodeList.add(node);
        }
        return nodeList;
    }

    // 分析pipes返回生成
    public List<Pipe> generatePipes(String url, Integer pid,
                                    List<cn.blatter.network.domain.Node> nodeList) throws DocumentException {
        List<Pipe> pipeList = new ArrayList<>();
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        List<Node> edgeList = document.selectNodes("//mxCell[@edge='1']/parent::*");
        this.nodeList = nodeList;
        for (Node e : edgeList) {
//            System.out.println(n.asXML());
            Pipe pipe = new Pipe();
            pipe.setName(e.getName());
            pipe.setProjectId(pid);
            pipe.setDiameter(Double.parseDouble(e.valueOf("@内径")));
            pipe.setLength(Double.parseDouble(e.valueOf("@长度")));
            pipe.setRoughness(Double.parseDouble(e.valueOf("@粗糙度")));
            pipe.setLambda(Double.parseDouble(e.valueOf("@摩阻")));

            int source = Integer.parseInt(e.selectSingleNode("./mxCell").valueOf("@source"));
            int target = Integer.parseInt(e.selectSingleNode("./mxCell").valueOf("@target"));
            String sourceName = document.selectSingleNode("//*[@id=" + source + "]").valueOf("@名称");
            String targetName = document.selectSingleNode("//*[@id=" + target + "]").valueOf("@名称");
            cn.blatter.network.domain.Node s = getNodeByName(sourceName);
            cn.blatter.network.domain.Node t = getNodeByName(targetName);
            pipe.setStartId(s.getId());
            pipe.setEndId(t.getId());
            pipe.setStartName(sourceName);
            pipe.setEndName(targetName);

            String style = e.selectSingleNode("./mxCell").valueOf("@style");
            double[] coordinate = parseXYByStyle(style);
            Connection start = getConnection(coordinate[0], coordinate[1], s.getElementId());
            Connection end = getConnection(coordinate[2], coordinate[3], t.getElementId());
            pipe.setStartConnection(start.getId());
            pipe.setEndConnection(end.getId());
            pipe.setStartConnectionName(start.getName());
            pipe.setEndConnectionName(end.getName());
//            System.out.println(pipe.toString());
            pipeList.add(pipe);
        }
        return pipeList;
    }

    // 新增/修改nodes
    public void updateNodes(String url, List<cn.blatter.network.domain.Node> nodeList) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        for(cn.blatter.network.domain.Node node : nodeList) {
            Node raw = document.selectSingleNode("//"+ node.getName() + "[@名称=" + node.getElementName() + "]");

        }

    }

    public Integer getElementIdByName(String name) {
        for(Element element : elementList) {
            if(element.getName().equals(name)) {
                return element.getId();
            }
        }
        return null;
    }

    public cn.blatter.network.domain.Node getNodeByName(String name) {
        for(cn.blatter.network.domain.Node node : nodeList) {
            if(node.getName().equals(name)) {
                return node;
            }
        }
        return null;
    }

    public Connection getConnection(Double x, Double y, Integer id) {
        for(Connection connection : connectionList) {
            if(connection.getElement_id().equals(id) &&
                    Double.doubleToLongBits(connection.getX()) == Double.doubleToLongBits(x) &&
                    Double.doubleToLongBits(connection.getY()) == Double.doubleToLongBits(y)) {
                return connection;
            }
        }
        return null;
    }

    public double[] parseXYByStyle(String style) {
        // result[0,1,2,3] = exitX, exitY, entryX, entryY
        double[] result = new double[4];
        int ex1 = style.indexOf("exitX=") + 6;
        style = style.substring(ex1);
        result[0] = Double.parseDouble(style.substring(0, style.indexOf(";")));
        int ey1 = style.indexOf("exitY=") + 6;
        style = style.substring(ey1);
        result[1] = Double.parseDouble(style.substring(0, style.indexOf(";")));
        int ex2 = style.indexOf("entryX=") + 7;
        style = style.substring(ex2);
        result[2] = Double.parseDouble(style.substring(0, style.indexOf(";")));
        int ey2 = style.indexOf("entryY=") + 7;
        style = style.substring(ey2);
        result[3] = Double.parseDouble(style.substring(0, style.indexOf(";")));
        return result;
    }

}

