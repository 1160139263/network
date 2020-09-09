package cn.blatter.network.service.impl;

import cn.blatter.network.domain.*;
import cn.blatter.network.mapper.NodeMapper;
import cn.blatter.network.mapper.PipeMapper;
import cn.blatter.network.service.PipeService;
import cn.blatter.network.utils.XMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tanyao
 * @Date 2020/7/31 0:50
 */
@Service("pipeService")
public class PipeServiceImpl implements PipeService {

	@Autowired
	private PipeMapper pipeMapper;

	@Autowired
	private NodeMapper nodeMapper;

	@Autowired
	private ConnectionServiceImpl connectionService;

	@Autowired
	private ElementServiceImpl elementService;

	@Autowired
	private ProjectsServiceImpl projectsService;

	/**
	 * 分页查询
	 * @param info
	 * @param pid
	 * @return
	 */
	@Override
	public List<Pipe> pageQuery(PageInfo info, Integer pid) {
		List<Pipe> pipes = pipeMapper.pageQuery(info.getPageSize() * (info.getPageNumber()-1), info.getPageSize(), pid);
		return pipes;
	}

	/**
	 * 根据工程id查询所有管道数据
	 * @param pid
	 * @return
	 */
	@Override
	public List<Pipe> queryByPid(Integer pid) {
		return pipeMapper.queryByProject(pid);
	}

	/**
	 * 根据管道id查询管道信息
	 * @param id
	 * @return
	 */
	@Override
	public Pipe queryById(Integer id) {
		return pipeMapper.queryById(id);
	}

	/**
	 * 根据管道id删除管道
	 * @param id
	 * @return
	 */
	@Override
	public Integer deletePipe(Integer id) {
		try {
			Pipe pipe = pipeMapper.queryById(id);
			String path = "src/main/resources" + projectsService.queryOne(pipe.getProjectId()).getModel();
			List<Element> elements = elementService.findAll();
			List<Connection> connections = connectionService.findAll();
			XMLUtil xmlUtil = new XMLUtil(elements, connections);
			xmlUtil.deletePipe(path,pipe);
			return pipeMapper.deleteById(id);
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Integer insertPipe(Pipe pipe) {
//		// 插入前设置名称
//		if (pipe.getStartId() != null) {
//			Node node = nodeMapper.queryById(pipe.getStartId());
//			pipe.setStartName(node.getName());
//		}
//		if (pipe.getEndName() != null) {
//			Node node = nodeMapper.queryById(pipe.getEndId());
//			pipe.setEndName(node.getName());
//		}
//		if (pipe.getStartConnection() != null) {
//			Connection connection = connectionService.queryById(pipe.getStartConnection());
//			pipe.setStartConnectionName(connection.getName());
//		}
//		if (pipe.getEndConnection() != null) {
//			Connection connection = connectionService.queryById(pipe.getEndConnection());
//			pipe.setEndConnectionName(connection.getName());
//		}
//		try {
//			String path = "src/main/resources" + projectsService.queryOne(pipe.getProjectId()).getModel();
//			List<Element> elements = elementService.findAll();
//			List<Connection> connections = connectionService.findAll();
//			XMLUtil xmlUtil = new XMLUtil(elements, connections);
//			Node start = nodeMapper.queryById(pipe.getStartId());
//			Node end = nodeMapper.queryById(pipe.getEndId());
//			Connection s = connectionService.queryById(pipe.getStartConnection());
//			Connection t = connectionService.queryById(pipe.getEndConnection());
//			Pipe newer = xmlUtil.insertPipe(path, pipe, start.getModelId(), end.getModelId(), s, t);;
//			return pipeMapper.insertPipe(newer);
//		}catch (Exception e) {
//			e.printStackTrace();
//			return 0;
//		}
		return 0;
	}

	@Override
	public Integer updatePipe(Pipe pipe) {
//		// 插入前设置名称
//		if (pipe.getStartId() != null) {
//			Node node = nodeMapper.queryById(pipe.getStartId());
//			pipe.setStartName(node.getName());
//		}
//		if (pipe.getEndName() != null) {
//			Node node = nodeMapper.queryById(pipe.getEndId());
//			pipe.setEndName(node.getName());
//		}
//		if (pipe.getStartConnection() != null) {
//			Connection connection = connectionService.queryById(pipe.getStartConnection());
//			pipe.setStartConnectionName(connection.getName());
//		}
//		if (pipe.getEndConnection() != null) {
//			Connection connection = connectionService.queryById(pipe.getEndConnection());
//			pipe.setEndConnectionName(connection.getName());
//		}
//		try {
//			String path = "src/main/resources" + projectsService.queryOne(pipe.getProjectId()).getModel();
//			List<Element> elements = elementService.findAll();
//			List<Connection> connections = connectionService.findAll();
//			XMLUtil xmlUtil = new XMLUtil(elements, connections);
//			Node start = nodeMapper.queryById(pipe.getStartId());
//			Node end = nodeMapper.queryById(pipe.getEndId());
//			Connection s = connectionService.queryById(pipe.getStartConnection());
//			Connection t = connectionService.queryById(pipe.getEndConnection());
//			xmlUtil.updatePipe(path, pipe, start.getModelId(), end.getModelId(), s, t);
//			return pipeMapper.updateById(pipe);
//		}catch (Exception e) {
//			e.printStackTrace();
//			return 0;
//		}
		return 0;
	}
}
