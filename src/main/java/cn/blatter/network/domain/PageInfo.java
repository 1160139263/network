package cn.blatter.network.domain;

import lombok.*;

/**
 * @author tanyao
 * @Date 2020/7/23 15:09
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo {
	private Integer pageNumber;
	private Integer pageSize;
}
