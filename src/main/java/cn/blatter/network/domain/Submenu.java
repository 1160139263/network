package cn.blatter.network.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * @author tanyao
 * @Date 2020/7/22 16:26
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Submenu {

	private Integer id;
	private String title;
	private String path;
	private String icon;
}
