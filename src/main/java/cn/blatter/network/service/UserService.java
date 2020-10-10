package cn.blatter.network.service;

import cn.blatter.network.common.ServiceResponse;
import cn.blatter.network.domain.Connection;
import cn.blatter.network.domain.User;

import java.util.List;

/**
 * @author tanyao
 * @Date 2020/7/6 18:58
 */
public interface UserService {

	User login(String username, String password);
	List<User> findAll();

	void deleteById(Integer uid);

	void updateById(User user);

	void insertUser(Integer uid, String username, String password, Integer role);

}
