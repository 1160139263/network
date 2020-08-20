package cn.blatter.network.service.impl;

import cn.blatter.network.domain.Node;
import cn.blatter.network.domain.PageInfo;
import cn.blatter.network.domain.Pipe;
import cn.blatter.network.mapper.NodeMapper;
import cn.blatter.network.mapper.PipeMapper;
import cn.blatter.network.service.PipeService;
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
		Integer result = pipeMapper.deleteById(id);
		return result;
	}

	@Override
	public Integer insertPipe(Pipe pipe) {
		Integer result = pipeMapper.insertPipe(pipe);
		return result;
	}

	@Override
	public Integer updatePipe(Pipe pipe) {
		// 插入前设置名称
		if (pipe.getStartId() != null) {
			Node node = nodeMapper.queryById(pipe.getStartId());
			pipe.setStartName(node.getName());
		}
		if (pipe.getEndName() != null) {
			Node node = nodeMapper.queryById(pipe.getEndId());
			pipe.setEndName(node.getName());
		}
		Integer result = pipeMapper.updateById(pipe);
		return result;
	}
}
