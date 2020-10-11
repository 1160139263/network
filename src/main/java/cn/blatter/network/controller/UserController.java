package cn.blatter.network.controller;

import cn.blatter.network.common.ServiceResponse;
import cn.blatter.network.domain.User;
import cn.blatter.network.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public ServiceResponse list() {
		List<User> userList = userService.findAll();
		// log.info("用户" + userList);
		return ServiceResponse.createBySuccess(userList);
	}

	@PostMapping(value = "/setUser")
	public ServiceResponse setUser(@RequestBody User user) {
		userService.updateById(user);
		return ServiceResponse.createBySuccess();
	}

	@PostMapping(value = "/deleteUser")
	public ServiceResponse deleteUser(@RequestBody User user) {
		userService.deleteById(user.getUid());
		return ServiceResponse.createBySuccess();
	}

	@PostMapping(value = "/addUser")
	public ServiceResponse addUser(@RequestBody User user){
		userService.insertUser(user.getUid(), user.getUsername(), user.getPassword(), user.getRole());
		return ServiceResponse.createBySuccess();
	}
}
