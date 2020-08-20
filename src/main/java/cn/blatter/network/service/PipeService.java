package cn.blatter.network.service;

import cn.blatter.network.domain.PageInfo;
import cn.blatter.network.domain.Pipe;

import java.util.List;

/**
 * @author tanyao
 * @Date 2020/7/31 0:28
 */
public interface PipeService {
	List<Pipe> pageQuery(PageInfo pageInfo, Integer pid);

	List<Pipe> queryByPid(Integer pid);

	Pipe queryById(Integer id);

	Integer deletePipe(Integer id);

	Integer insertPipe(Pipe pipe);

	Integer updatePipe(Pipe pipe);
}
