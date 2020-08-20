package cn.blatter.network.service.impl;

import cn.blatter.network.domain.Type;
import cn.blatter.network.mapper.TypeMapper;
import cn.blatter.network.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tanyao
 * @Date 2020/8/5 16:47
 */
@Service("typeService")
public class TypeServiceImpl implements TypeService {

	@Autowired
	private TypeMapper typeMapper;

	@Override
	public List<Type> findAll() {
		List<Type> types = typeMapper.findAll();
		return types;
	}
}
