package cn.blatter.network.simulator.simulate;

import org.ejml.simple.SimpleMatrix;

/**
 * @author tanyao
 * @date 2019/10/21
 */
public class FixedFunctions {
    /**
     * 管段内径
     */
    private SimpleMatrix diameter;

    /**
     * 管段摩阻
     */
    private SimpleMatrix lambda;

    /**
     * 管段长度
     */
    private SimpleMatrix length;

    /**
     * 管段起伏
     */
    private SimpleMatrix elevation;

    /**
     * 节点-管段关联矩阵
     */
    private SimpleMatrix incidence;

    /**
     * 简化的节点-管段关联矩阵
     */
    private SimpleMatrix simpleIncidence;

    /**
     * 简化的节点载荷矩阵
     */
    private SimpleMatrix simpleLoad;

    public void init (Network network) {
        //管段数
        int pipeCount = network.getPipes().size();
        //管段内径数组
        double[][] diameter = new double[pipeCount][1];
        //管段摩阻数组
        double[][] lambda = new double[pipeCount][1];
        //管段长度数组
        double[][] length = new double[pipeCount][1];
        //管段起伏
        double[][] elevation = new double[pipeCount][1];

        for (int i = 0; i < pipeCount; i++) {
            //管段内径
            diameter[i][0] = network.getPipes().get(i).getDiameter();
            //摩阻系数
            network.getPipes().get(i).initLambda();
            lambda[i][0] = network.getPipes().get(i).getLambda();
            //管段长度
            length[i][0] = network.getPipes().get(i).getLength();
            //管段起伏
            //elevation[i][0] = network.getPipes().get(i).getEndNode().getElevation()
            //        - network.getPipes().get(i).getStartNode().getElevation();
            elevation[i][0] = 0;
        }

        this.diameter = new SimpleMatrix(diameter);
        this.elevation = new SimpleMatrix(elevation);
        this.lambda = new SimpleMatrix(lambda);
        this.length = new SimpleMatrix(length);

        //节点数
        int nodeCount = network.getNodes().size();
        //节点-管段关联数组
        double[][] pipes = new double[nodeCount][pipeCount];
        for (int i = 0; i < pipeCount; i++) {
            //管段起点编号,network.getElements()[i].getStartNode().getUid()
            //管段终点编号,network.getElements()[i].getEndNode().getUid()
            int startNumber = network.getPipes().get(i).getStartId();
            int endNumber = network.getPipes().get(i).getEndId();
            //编号所对应的序列
            int start = 0;
            int end = 0;
            for (int j = 0; j < network.getNodes().size(); j++) {
                if (network.getNodes().get(j).getId() == startNumber) {
                    start = j;
                }
                if (network.getNodes().get(j).getId() == endNumber) {
                    end = j;
                }
            }
            //节点start在管段i的始端
            pipes[start][i] = 1;
            //节点end在管段i的终端
            pipes[end][i] = -1;
        }
        //根绝节点-管段关联数组生成节点-管段关联矩阵
        this.incidence = new SimpleMatrix(pipes);

        //未知压力的节点个数
        int simpleNode = 0;
        //已知载荷的节点个数
        int simpleLoad = 0;
        for (int i = 0; i < network.getNodes().size(); i++) {
            if (!network.getNodes().get(i).isPressureState()) {
                simpleNode ++;
            }
            if (network.getNodes().get(i).isLoadState()) {
                simpleLoad ++;
            }
        }
        double[][] simplePipes = new double[simpleNode][pipeCount];
        double[][] simpleNodes = new double[simpleLoad][1];
        int flagPipe = 0;
        int flagNode = 0;
        for (int i = 0; i < nodeCount; i++) {
            if (!network.getNodes().get(i).isPressureState()) {
                simplePipes[flagPipe] = pipes[i];
                flagPipe ++;
            }
            if (network.getNodes().get(i).isLoadState()) {
                simpleNodes[flagNode][0] = network.getNodes().get(i).getLoads();
                flagNode ++;
            }
        }
        this.simpleIncidence = new SimpleMatrix(simplePipes);
        this.simpleLoad = new SimpleMatrix(simpleNodes);
    }

    public SimpleMatrix getDiameter() {
        return diameter;
    }

    public SimpleMatrix getLambda() {
        return lambda;
    }

    public SimpleMatrix getLength() {
        return length;
    }

    public SimpleMatrix getElevation() {
        return elevation;
    }

    public SimpleMatrix getIncidence() {
        return incidence;
    }

    public SimpleMatrix getSimpleIncidence() {
        return simpleIncidence;
    }

    public SimpleMatrix getSimpleLoad() {
        return simpleLoad;
    }
}
