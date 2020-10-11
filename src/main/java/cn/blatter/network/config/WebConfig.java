package cn.blatter.network.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletContext;
import java.io.File;

/**
 * @author tanyao
 * @Date 2020/7/22 9:50
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter  {

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
//				.allowedOrigins("http://localhost:8080", "null")
				.allowedOrigins("*", "null")
				.allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")
				.allowCredentials(true)
				.maxAge(3600);
	}

	/**
	 * 配置访问静态资源
	 * @param registry
	 */
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry){
//		String pathRoot = this.getClass().getResource("").getPath();
//		int index = pathRoot.indexOf("target/");
//		pathRoot = pathRoot.substring(1, index);
//		System.out.println(pathRoot + "Elements/");
//		registry.addResourceHandler("/Elements/**")
//				.addResourceLocations("file:" + pathRoot + "Elements/");
//		super.addResourceHandlers(registry);
//	}

}
