package cn.blatter.network.service;

import cn.blatter.network.domain.Component;
import cn.blatter.network.domain.GasProperty;
import cn.blatter.network.domain.PageInfo;

import java.util.List;

/**
 * @author tanyao
 * @Date 2020/8/9 15:38
 */
public interface ComponentService {

	Integer countComponent();

	Integer insertComponent(Component component);

	List<Component> pageQuery(PageInfo pageInfo);

	Integer deleteComponent(Integer id);

	List<GasProperty> queryComponentProperty(Integer id);

	Integer updateComponent(Component component);

	List<Component> findAll();

}
