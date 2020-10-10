package cn.blatter.network.mapper;

import cn.blatter.network.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

	List<User> findAll();

	User queryById(Integer uid);

	void deleteById(Integer uid);

	void updateById(User user);

	void insertUser(@Param("uid")Integer uid, @Param("username")String username, @Param("password")String password, @Param("role")Integer role);

	User login(@Param("username") String username, @Param("password") String password);
}
