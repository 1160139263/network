package cn.blatter.network.utils;


import cn.blatter.network.domain.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
            node.setElevation(Double.parseDouble(n.valueOf("@海拔")));
            node.setX(Double.parseDouble(n.selectSingleNode("./mxCell/mxGeometry").valueOf("@x")));
            node.setY(Double.parseDouble(n.selectSingleNode("./mxCell/mxGeometry").valueOf("@y")));

            switch (node.getElementId()) {
                case 1:
                    Node t1 = new Node();
                    t1 = autoFilling(t1, node);
                    t1.setPressure(Double.parseDouble(n.valueOf("@压力")));
                    t1.setLoads(Double.parseDouble(n.valueOf("@载荷")));
                    t1.setPressureState(Boolean.parseBoolean(n.valueOf("@压力已知")));
                    t1.setLoadState(Boolean.parseBoolean(n.valueOf("@载荷已知")));
                    System.out.println(t1.toString());
                    nodeList.add(t1);
                    break;

//                case 2:
//                    Well t2 = new Well();
//                    t2 = autoFilling(t2, node);
//                    t2.setFlowId(Integer.parseInt(n.valueOf("@流ID")));
//                    System.out.println(t2.toString());
//                    nodeList.add(t2);
//                    break;
//
//                case 3:
//                    Ejector t3 = new Ejector();
//                    t3 = autoFilling(t3, node);
//                    t3.setExpandRate(Double.parseDouble(n.valueOf("@膨胀比")));
//                    t3.setCompressRate(Double.parseDouble(n.valueOf("@压缩比")));
//                    t3.setEjectorRate(Double.parseDouble(n.valueOf("@引射率")));
//                    t3.setEfficiency(Double.parseDouble(n.valueOf("@等熵效率")));
//                    System.out.println(t3.toString());
//                    nodeList.add(t3);
//                    break;
//
//                case 4:
//                    Compressor t4 = new Compressor();
//                    t4 = autoFilling(t4, node);
//                    t4.setMainPressure(Double.parseDouble(n.valueOf("@干线压力")));
//                    t4.setCalorificValue(Double.parseDouble(n.valueOf("@天然气热值")));
//                    t4.setEngineEfficiency(Double.parseDouble(n.valueOf("@原动机效率")));
//                    t4.setCompressorEfficiency(Double.parseDouble(n.valueOf("@压缩机效率")));
//                    System.out.println(t4.toString());
//                    nodeList.add(t4);
//                    break;
//
//                case 5:
//                    Station t5 = new Station();
//                    t5 = autoFilling(t5, node);
//                    t5.setInletPressure(Double.parseDouble(n.valueOf("@进场压力")));
//                    t5.setOutletPressure(Double.parseDouble(n.valueOf("@出场压力")));
//                    t5.setProduction(Double.parseDouble(n.valueOf("@产率")));
//                    System.out.println(t5.toString());
//                    nodeList.add(t5);
//                    break;
            }
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
    public <T extends Node> void updateNode(String url, T node)
            throws DocumentException, IOException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        org.dom4j.Element raw = (org.dom4j.Element) document.selectSingleNode("//*[@id=" + node.getModelId() + "]");
        raw.setName(node.getElementName());
        raw.addAttribute("海拔",node.getElevation().toString());
        raw.addAttribute("名称",node.getName());

        switch (node.getElementId()) {
            case 1:
                Method getPressure = node.getClass().getDeclaredMethod("getPressure");
                raw.addAttribute("压力", getPressure.invoke(node).toString());
                Method getLoads = node.getClass().getDeclaredMethod("getLoads");
                raw.addAttribute("载荷", getLoads.invoke(node).toString());
                Method isPressureState = node.getClass().getDeclaredMethod("isPressureState");
                raw.addAttribute("压力已知", String.valueOf(isPressureState.invoke(node)));
                Method isLoadState = node.getClass().getDeclaredMethod("isLoadState");
                raw.addAttribute("载荷已知", String.valueOf(isLoadState.invoke(node)));
                break;

            case 2:
                Method getFlowId = node.getClass().getDeclaredMethod("getFlowId");
                raw.addAttribute("流ID", getFlowId.invoke(node).toString());
                break;

            case 3:
                Method getExpandRate = node.getClass().getDeclaredMethod("getExpandRate");
                raw.addAttribute("膨胀比", getExpandRate.invoke(node).toString());
                Method getCompressRate = node.getClass().getDeclaredMethod("getCompressRate");
                raw.addAttribute("压缩比", getCompressRate.invoke(node).toString());
                Method getEjectorRate = node.getClass().getDeclaredMethod("getEjectorRate");
                raw.addAttribute("引射率", getEjectorRate.invoke(node).toString());
                Method getEfficiency = node.getClass().getDeclaredMethod("getEfficiency");
                raw.addAttribute("等熵效率", getEfficiency.invoke(node).toString());
                break;

            case 4:
                Method getMainPressure = node.getClass().getDeclaredMethod("getMainPressure");
                raw.addAttribute("干线压力", getMainPressure.invoke(node).toString());
                Method getCalorificValue = node.getClass().getDeclaredMethod("getCalorificValue");
                raw.addAttribute("天然气热值", getCalorificValue.invoke(node).toString());
                Method getEngineEfficiency = node.getClass().getDeclaredMethod("getEngineEfficiency");
                raw.addAttribute("原动机效率", getEngineEfficiency.invoke(node).toString());
                Method getCompressorEfficiency = node.getClass().getDeclaredMethod("getCompressorEfficiency");
                raw.addAttribute("压缩机效率", getCompressorEfficiency.invoke(node).toString());
                break;

            case 5:
                Method getInletPressure = node.getClass().getDeclaredMethod("getInletPressure");
                raw.addAttribute("进场压力", getInletPressure.invoke(node).toString());
                Method getOutletPressure = node.getClass().getDeclaredMethod("getOutletPressure");
                raw.addAttribute("出场压力", getOutletPressure.invoke(node).toString());
                Method getProduction = node.getClass().getDeclaredMethod("getProduction");
                raw.addAttribute("产率", getProduction.invoke(node).toString());
                break;

        }

        org.dom4j.Element geo = (org.dom4j.Element) raw.selectSingleNode("./mxCell/mxGeometry");
        geo.addAttribute("x",node.getX().toString());
        geo.addAttribute("y",node.getY().toString());

        FileWriter out = new FileWriter(url);
        document.write(out);
        out.close();
    }

    // 修改pipe
    public void updatePipe(String url, Pipe pipe, Integer startId, Integer endId, Connection s, Connection t)
            throws DocumentException, IOException {
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
    public <T extends Node> T insertNode(String url, T node)
            throws DocumentException, IOException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
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
        cell.addAttribute("海拔",node.getElevation().toString());

        switch (node.getElementId()) {
            case 1:
                Method getPressure = node.getClass().getDeclaredMethod("getPressure");
                cell.addAttribute("压力", getPressure.invoke(node).toString());
                Method getLoads = node.getClass().getDeclaredMethod("getLoads");
                cell.addAttribute("载荷", getLoads.invoke(node).toString());
                Method isPressureState = node.getClass().getDeclaredMethod("isPressureState");
                cell.addAttribute("压力已知", String.valueOf(isPressureState.invoke(node)));
                Method isLoadState = node.getClass().getDeclaredMethod("isLoadState");
                cell.addAttribute("载荷已知", String.valueOf(isLoadState.invoke(node)));
                break;

            case 2:
                Method getFlowId = node.getClass().getDeclaredMethod("getFlowId");
                cell.addAttribute("流ID", getFlowId.invoke(node).toString());
                break;

            case 3:
                Method getExpandRate = node.getClass().getDeclaredMethod("getExpandRate");
                cell.addAttribute("膨胀比", getExpandRate.invoke(node).toString());
                Method getCompressRate = node.getClass().getDeclaredMethod("getCompressRate");
                cell.addAttribute("压缩比", getCompressRate.invoke(node).toString());
                Method getEjectorRate = node.getClass().getDeclaredMethod("getEjectorRate");
                cell.addAttribute("引射率", getEjectorRate.invoke(node).toString());
                Method getEfficiency = node.getClass().getDeclaredMethod("getEfficiency");
                cell.addAttribute("等熵效率", getEfficiency.invoke(node).toString());
                break;

            case 4:
                Method getMainPressure = node.getClass().getDeclaredMethod("getMainPressure");
                cell.addAttribute("干线压力", getMainPressure.invoke(node).toString());
                Method getCalorificValue = node.getClass().getDeclaredMethod("getCalorificValue");
                cell.addAttribute("天然气热值", getCalorificValue.invoke(node).toString());
                Method getEngineEfficiency = node.getClass().getDeclaredMethod("getEngineEfficiency");
                cell.addAttribute("原动机效率", getEngineEfficiency.invoke(node).toString());
                Method getCompressorEfficiency = node.getClass().getDeclaredMethod("getCompressorEfficiency");
                cell.addAttribute("压缩机效率", getCompressorEfficiency.invoke(node).toString());
                break;

            case 5:
                Method getInletPressure = node.getClass().getDeclaredMethod("getInletPressure");
                cell.addAttribute("进场压力", getInletPressure.invoke(node).toString());
                Method getOutletPressure = node.getClass().getDeclaredMethod("getOutletPressure");
                cell.addAttribute("出场压力", getOutletPressure.invoke(node).toString());
                Method getProduction = node.getClass().getDeclaredMethod("getProduction");
                cell.addAttribute("产率", getProduction.invoke(node).toString());
                break;

        }

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

    public <T extends Node> T autoFilling(T target, Node source) {
        target.setElementName(source.getElementName());
        target.setModelId(source.getModelId());
        target.setElementId(source.getElementId());
        target.setElevation(source.getElevation());
        target.setName(source.getName());
        target.setProjectId(source.getProjectId());
        target.setX(source.getX());
        target.setY(source.getY());
        return target;
    }

}

