package cn.blatter.network.simulator.simulate;

import cn.blatter.network.domain.Gas;
import cn.blatter.network.domain.Node;
import cn.blatter.network.domain.Pipe;

import java.io.Serializable;
import java.util.List;

/**
 * 纯管网对象模型
 * @author tanyao
 * @date 2019/9/26
 */
public class Network implements Serializable {
    /**
     * 管网中的节点
     */
    private List<Node> nodes;

    /**
     * 管网中的元件
     */
    private List<Pipe> pipes;

    /**
     * 管网中的流体
     */
    private Gas gas;

    @Override
    public String toString() {
        return "Network{" +
                "nodes=" + nodes +
                ", pipes=" + pipes +
                ", gas=" + gas +
                '}';
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Pipe> getPipes() {
        return pipes;
    }

    public void setPipes(List<Pipe> pipes) {
        this.pipes = pipes;
    }

    public Gas getGas() {
        return gas;
    }

    public void setGas(Gas gas) {
        this.gas = gas;
    }
}
