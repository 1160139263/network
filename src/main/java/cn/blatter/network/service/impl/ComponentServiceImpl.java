package cn.blatter.network.service.impl;

import cn.blatter.network.common.GasName;
import cn.blatter.network.domain.Component;
import cn.blatter.network.domain.GasProperty;
import cn.blatter.network.domain.PageInfo;
import cn.blatter.network.mapper.ComponentMapper;
import cn.blatter.network.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tanyao
 * @Date 2020/8/9 15:38
 */
@Service("componentService")
public class ComponentServiceImpl implements ComponentService {

	@Autowired
	private ComponentMapper componentMapper;

	@Override
	public Integer countComponent() {
		Integer count = componentMapper.count();
		return count;
	}

	@Override
	public Integer insertComponent(Component component) {
		componentMapper.insertComponent(component);
		return component.getId();
	}

	@Override
	public List<Component> pageQuery(PageInfo pageInfo) {
		List<Component> components = componentMapper.pageQuery(pageInfo.getPageSize() * (pageInfo.getPageNumber() - 1), pageInfo.getPageSize());
		return components;
	}

	@Override
	public Integer deleteComponent(Integer id) {
		Integer result = componentMapper.deleteById(id);
		return result;
	}

	@Override
	public List<GasProperty> queryComponentProperty(Integer id) {
		Component component = componentMapper.queryById(id);
		List<GasProperty> properties = new ArrayList<>();
		Field[] fields = Component.class.getDeclaredFields();
		for (int i = 2; i < fields.length; i++) {
			GasProperty gasProperty = new GasProperty();
			String name = fields[i].getName();
			gasProperty.setName(GasName.valueOf(name).getName());
			gasProperty.setFormula(GasName.valueOf(name).getFormula());
			try {
				PropertyDescriptor dp = new PropertyDescriptor(fields[i].getName(), Component.class);
				Method method = dp.getReadMethod();
				try {
					gasProperty.setPercentage((Double) method.invoke(component));
					properties.add(gasProperty);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return properties;
	}

	@Override
	public Integer updateComponent(Component component) {
		Integer result = componentMapper.updateById(component);
		return result;
	}

	@Override
	public List<Component> findAll() {
		List<Component> components = componentMapper.findAll();
		return components;
	}
}
