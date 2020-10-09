package cn.blatter.network.mapper;

import cn.blatter.network.domain.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author tanyao
 * @Date 2020/7/22 16:29
 */
@Mapper
public interface MenuMapper {
	List<Menu> findAll();
	List<Menu> findAdmin();
	List<Menu> findOper();
	List<Menu> findTour();
}
