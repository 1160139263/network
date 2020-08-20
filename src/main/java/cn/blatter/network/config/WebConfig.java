package cn.blatter.network.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author tanyao
 * @Date 2020/7/22 9:50
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	/**
	 * 1. 访问路径
	 * 2. 请求来源
	 * 3. 方法
	 * 4. 允许携带token
	 * 5. 生命周期
	 * @param registry
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:8080", "null")
				.allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")
				.allowCredentials(true)
				.maxAge(3600);
	}
}
