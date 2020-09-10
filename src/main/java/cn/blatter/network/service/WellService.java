package cn.blatter.network.service;

import cn.blatter.network.domain.Base;
import cn.blatter.network.domain.Well;

import java.util.List;


public interface WellService {
	List<Well> findAll(Integer id);
	Well findById(Integer id);
	void setWell(Well well);
	void addWell(Well well);
	void deleteWell(Integer id);
}
