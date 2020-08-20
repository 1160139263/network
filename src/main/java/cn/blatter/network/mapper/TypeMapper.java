package cn.blatter.network.mapper;

import cn.blatter.network.domain.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeMapper {
	List<Type> findAll();
}
