package cn.blatter.network.mapper;

import cn.blatter.network.domain.Base;
import cn.blatter.network.domain.Well;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author tanyao
 * @Date 2020/7/13 15:37
 */
@Mapper
public interface WellMapper {
	List<Well> findAll(Integer id);
	Well getBaseById(Integer id);
	void setWell(Well well);
	void setBase(Well well);

	void addWell(Well well);
	void addBase(Well well);

	void deleteWell(Integer id);
}
