package cn.blatter.network.service.impl;

import cn.blatter.network.domain.*;
import cn.blatter.network.mapper.ComponentMapper;
import cn.blatter.network.mapper.NodeMapper;
import cn.blatter.network.mapper.PipeMapper;
import cn.blatter.network.mapper.ProjectsMapper;
import cn.blatter.network.service.SimulateService;
import cn.blatter.network.simulator.simulate.Network;
import cn.blatter.network.simulator.simulate.NewtonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author tanyao
 * @Date 2020/8/14 0:39
 */
@Service("simulateService")
public class SimulateServiceImpl implements SimulateService {

	@Autowired
	private NodeMapper nodeMapper;

	@Autowired
	private PipeMapper pipeMapper;

	@Autowired
	private ComponentMapper componentMapper;

	@Autowired
	private ProjectsMapper projectsMapper;

	public double[] setComponent(Component component) {
		Field[] fields = Component.class.getDeclaredFields();
		double[] result = new double[fields.length-2];
		for (int i = 2; i < fields.length; i++) {
			try {
				PropertyDescriptor pd = new PropertyDescriptor(fields[i].getName(), component.getClass());
				Method method = pd.getReadMethod();
				try {
					result[i-2] = (Double) method.invoke(component);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public Network simulate(Integer id) {
		// 节点数据
		List<Node> nodes = nodeMapper.queryByProject(id);
		// 管道数据
		List<Pipe> pipes = pipeMapper.queryByProject(id);
		// 模拟信息
		Projects projects = projectsMapper.queryById(id);
		//组分信息
		Component component = componentMapper.queryById(projects.getComId());
		//创建气体对象
		Gas gas = new Gas();
		gas.setComponent(setComponent(component));
		gas.setRelativeDensity();

		Network network = new Network();
		network.setGas(gas);
		network.setNodes(nodes);
		network.setPipes(pipes);

		NewtonMethod newtonMethod = new NewtonMethod(network);
		newtonMethod.run();
		return network;
	}
}
