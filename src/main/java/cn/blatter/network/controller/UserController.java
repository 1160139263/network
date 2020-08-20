package cn.blatter.network.controller;

import cn.blatter.network.common.ServiceResponse;
import cn.blatter.network.domain.User;
import cn.blatter.network.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tanyao
 * @Date 2020/7/8 1:23
 */
@Slf4j
@RestController()
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ServiceResponse login(@RequestBody User user) {
		log.info(user + "开始登录...");
		User user1 = userService.login(user.getUsername(), user.getPassword());
		if (user1 == null) {
			log.info(user + "登录失败");
			return ServiceResponse.createByErrorMessage("用户名或密码错误");
		}
		log.info(user + "登录成功");
		return ServiceResponse.createBySuccess("登录成功", user1);
	}
}
