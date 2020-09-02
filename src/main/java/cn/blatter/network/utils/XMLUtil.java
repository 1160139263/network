package cn.blatter.network.utils;


import cn.blatter.network.domain.Connection;
import cn.blatter.network.domain.Element;
import cn.blatter.network.domain.Pipe;
import cn.blatter.network.domain.Node;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class XMLUtil {
    private final List<Element> elementList;
    private final List<Connection> connectionList;
    private List<Node> nodeList;

    public XMLUtil(List<Element> elementList,List<Connection> connectionList) {
        this.elementList = elementList;
        this.connectionList = connectionList;
    }

    // 分析nodes返回生成
    public List<Node> generateNodes(String url, Integer pid) throws DocumentException {
        List<Node> nodeList = new ArrayList<>();
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        List<org.dom4j.Node> vertexList = document.selectNodes("//mxCell[@vertex='1']/parent::*");
        for (org.dom4j.Node n : vertexList) {
//            System.out.println(n.asXML());
            Node node = new Node();
            node.setElementName(n.getName());
            node.setElementId(getElementIdByName(n.getName()));
            node.setModelId(Integer.parseInt(n.valueOf("@id")));
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
    public List<Pipe> generatePipes(String url, Integer pid, List<Node> nodeList) throws DocumentException {
        List<Pipe> pipeList = new ArrayList<>();
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        List<org.dom4j.Node> edgeList = document.selectNodes("//mxCell[@edge='1']/parent::*");
        this.nodeList = nodeList;
        for (org.dom4j.Node e : edgeList) {
//            System.out.println(n.asXML());
            Pipe pipe = new Pipe();
            pipe.setName(e.getName());
            pipe.setProjectId(pid);
            pipe.setModelId(Integer.parseInt(e.valueOf("@id")));
            pipe.setDiameter(Double.parseDouble(e.valueOf("@内径")));
            pipe.setLength(Double.parseDouble(e.valueOf("@长度")));
            pipe.setRoughness(Double.parseDouble(e.valueOf("@粗糙度")));

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

    // 修改node
    public void updateNode(String url, Node node)
            throws DocumentException, IOException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        org.dom4j.Element raw = (org.dom4j.Element) document.selectSingleNode("//*[@id=" + node.getModelId() + "]");
        raw.setName(node.getElementName());
        raw.addAttribute("压力",node.getPressure().toString());
        raw.addAttribute("载荷",node.getLoads().toString());
        raw.addAttribute("海拔",node.getElevation().toString());
        raw.addAttribute("压力已知",String.valueOf(node.isPressureState()));
        raw.addAttribute("载荷已知",String.valueOf(node.isLoadState()));
        raw.addAttribute("名称",node.getName());
        org.dom4j.Element geo = (org.dom4j.Element) raw.selectSingleNode("./mxCell/mxGeometry");
        geo.addAttribute("x",node.getX().toString());
        geo.addAttribute("y",node.getY().toString());

        FileWriter out = new FileWriter(url);
        document.write(out);
        out.close();
    }

    // 修改pipe
    public void updatePipe(String url, Pipe pipe, Integer startId, Integer endId,
                           Connection s, Connection t) throws DocumentException, IOException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        org.dom4j.Element raw = (org.dom4j.Element) document.selectSingleNode("//*[@id=" + pipe.getModelId() + "]");
        raw.setName(pipe.getName());
        raw.addAttribute("内径",pipe.getDiameter().toString());
        raw.addAttribute("长度",pipe.getLength().toString());
        raw.addAttribute("粗糙度",pipe.getRoughness().toString());
        org.dom4j.Element cell = (org.dom4j.Element) raw.selectSingleNode("./mxCell");
        String style = "edgeStyle=orthogonalEdgeStyle;exitX=" + s.getX().toString() +
                ";exitY=" + s.getY().toString() + ";exitDx=0;exitDy=0;entryX=" + t.getX().toString() + ";entryY=" +
                t.getY().toString() + ";entryDx=0;entryDy=0;";
        cell.addAttribute("style",style);
        cell.addAttribute("source",startId.toString());
        cell.addAttribute("target",endId.toString());

        FileWriter out = new FileWriter(url);
        document.write(out);
        out.close();
    }

    // 新增node
    public Node insertNode(String url, Node node) throws DocumentException, IOException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        org.dom4j.Element root = (org.dom4j.Element) document.selectSingleNode("/mxGraphModel/root");
        List<org.dom4j.Node> nodes = document.selectNodes("//*[@id]");
        int maxId = 0;
        for(org.dom4j.Node n : nodes) {
            int temp = Integer.parseInt(n.valueOf("@id"));
            maxId = Math.max(temp, maxId);
        }
        maxId++;
        node.setModelId(maxId);
        org.dom4j.Element cell = root.addElement(node.getElementName());
        cell.addAttribute("名称",node.getName());
        cell.addAttribute("压力",node.getPressure().toString());
        cell.addAttribute("载荷",node.getLoads().toString());
        cell.addAttribute("压力已知",String.valueOf(node.isPressureState()));
        cell.addAttribute("载荷已知",String.valueOf(node.isLoadState()));
        cell.addAttribute("海拔",node.getElevation().toString());
        cell.addAttribute("id", String.valueOf(maxId));

        Element element = getElementById(node.getElementId());
        org.dom4j.Element mxcell = cell.addElement("mxCell");
        String style = "shape=image;image=http://localhost:8081/Elements/" +
                URLEncoder.encode(element.getName(),"UTF-8") + ".svg;verticalLabelPosition=bottom;verticalAlign=top";
        mxcell.addAttribute("style",style);
        mxcell.addAttribute("vertex","1");
        mxcell.addAttribute("parent","1");


        double[] size = getSizeFromSVG(getPathRoot() + element.getPath());
        org.dom4j.Element mxgeo = mxcell.addElement("mxGeometry");
        mxgeo.addAttribute("x",node.getX().toString());
        mxgeo.addAttribute("y",node.getY().toString());
        mxgeo.addAttribute("width", String.valueOf(size[0]/2));
        mxgeo.addAttribute("height", String.valueOf(size[1]/2));
        mxgeo.addAttribute("as","geometry");

        List<Connection> connections = getConnectionListByEid(node.getElementId());
        org.dom4j.Element array = mxcell.addElement("Array");
        for(Connection connection : connections) {
            org.dom4j.Element object = array.addElement("Object");
            object.addAttribute("x",connection.getX().toString());
            object.addAttribute("y",connection.getY().toString());
            object.addAttribute("perimeter","1");
        }
        array.addAttribute("as","constraints");

        FileWriter out = new FileWriter(url);
        document.write(out);
        out.close();

        return node;
    }

    // 新增pipe
    public Pipe insertPipe(String url, Pipe pipe, Integer startId, Integer endId,
                           Connection s, Connection t) throws DocumentException, IOException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        org.dom4j.Element root = (org.dom4j.Element) document.selectSingleNode("/mxGraphModel/root");
        List<org.dom4j.Node> nodes = document.selectNodes("//*[@id]");
        int maxId = 0;
        for(org.dom4j.Node n : nodes) {
            int temp = Integer.parseInt(n.valueOf("@id"));
            maxId = Math.max(temp, maxId);
        }
        maxId++;
        pipe.setModelId(maxId);
        org.dom4j.Element cell = root.addElement(pipe.getName());
        cell.addAttribute("内径",pipe.getDiameter().toString());
        cell.addAttribute("长度",pipe.getLength().toString());
        cell.addAttribute("粗糙度",pipe.getRoughness().toString());
        cell.addAttribute("id", String.valueOf(maxId));

        org.dom4j.Element mxcell = cell.addElement("mxCell");
        String style = "edgeStyle=orthogonalEdgeStyle;exitX=" + s.getX().toString() +
                ";exitY=" + s.getY().toString() + ";exitDx=0;exitDy=0;entryX=" + t.getX().toString() + ";entryY=" +
                t.getY().toString() + ";entryDx=0;entryDy=0;";
        mxcell.addAttribute("style",style);
        mxcell.addAttribute("edge","1");
        mxcell.addAttribute("parent","1");
        mxcell.addAttribute("source",startId.toString());
        mxcell.addAttribute("target",endId.toString());

        org.dom4j.Element mxgeo = mxcell.addElement("mxGeometry");
        mxgeo.addAttribute("relative","1");
        mxgeo.addAttribute("as","geometry");

        FileWriter out = new FileWriter(url);
        document.write(out);
        out.close();

        return pipe;
    }

    // 删除node
    public void deleteNode(String url, Node node) throws DocumentException, IOException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        org.dom4j.Element root = (org.dom4j.Element) document.selectSingleNode("/mxGraphModel/root");
        org.dom4j.Node raw = document.selectSingleNode("//*[@id=" + node.getModelId() + "]");
        root.remove(raw);

        FileWriter out = new FileWriter(url);
        document.write(out);
        out.close();
    }

    // 删除pipe
    public void deletePipe(String url, Pipe pipe) throws DocumentException, IOException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        org.dom4j.Element root = (org.dom4j.Element) document.selectSingleNode("/mxGraphModel/root");
        org.dom4j.Node raw = document.selectSingleNode("//*[@id=" + pipe.getModelId() + "]");
        root.remove(raw);

        FileWriter out = new FileWriter(url);
        document.write(out);
        out.close();
    }

    public Element getElementById(Integer id) {
        for(Element element : elementList) {
            if(element.getId().equals(id)) {
                return element;
            }
        }
        return null;
    }

    public Integer getElementIdByName(String name) {
        for(Element element : elementList) {
            if(element.getName().equals(name)) {
                return element.getId();
            }
        }
        return null;
    }

    public Node getNodeByName(String name) {
        for(Node node : nodeList) {
            if(node.getName().equals(name)) {
                return node;
            }
        }
        return null;
    }

    public List<Connection> getConnectionListByEid(Integer eid) {
        List<Connection> list = new ArrayList<>();
        for(Connection connection: connectionList) {
            if(connection.getElement_id().equals(eid)) {
                list.add(connection);
            }
        }
        return list;
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

    public double[] getSizeFromSVG(String url) throws DocumentException {
        System.out.println(url);
        File file = new File(url);
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        org.dom4j.Node svg = document.selectSingleNode("//svg");
        double[] result = new double[2];
        String s1 = svg.valueOf("@width");
        String s2 = svg.valueOf("@height");
        result[0] = Double.parseDouble(s1);
        result[1] = Double.parseDouble(s2);
        return result;
    }

    public String getPathRoot() {
        String pathRoot = this.getClass().getResource("").getPath();
        int index = pathRoot.indexOf("target/");
        pathRoot = pathRoot.substring(1, index);
        return pathRoot.substring(0,pathRoot.length()-1);
    }

}

