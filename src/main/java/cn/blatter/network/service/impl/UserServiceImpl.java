package cn.blatter.network.service.impl;

import cn.blatter.network.domain.Connection;
import cn.blatter.network.domain.User;
import cn.blatter.network.mapper.UserMapper;
import cn.blatter.network.service.UserService;
import cn.blatter.network.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author tanyao
 * @Date 2020/7/9 13:25
 */
@Slf4j
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 处理用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	@Override
	public User login(String username, String password) {
		String md5Password = MD5Util.MD5EncodeUtf8(password);
		User user = userMapper.login(username, md5Password);
		return user;
	}

	@Override
	public List<User> findAll(){
		List<User> userList = userMapper.findAll();
		return userList;
	}

	@Override
	public void deleteById(Integer uid) {
		userMapper.deleteById(uid);
	}

	@Override
	public void updateById(User user) {
		userMapper.updateById(user);
	}

	@Override
	public void insertUser(Integer uid, String username, String password, Integer role) {
		userMapper.insertUser(uid, username, password, role);
	}

}
