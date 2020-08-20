package cn.blatter.network.service;

import cn.blatter.network.common.ServiceResponse;
import cn.blatter.network.domain.User;

/**
 * @author tanyao
 * @Date 2020/7/6 18:58
 */
public interface UserService {

	User login(String username, String password);
}
