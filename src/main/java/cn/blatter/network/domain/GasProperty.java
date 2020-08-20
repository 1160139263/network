package cn.blatter.network.domain;

import lombok.*;

/**
 * @author tanyao
 * @Date 2020/8/4 15:34
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GasProperty {
	private String formula;
	private String name;
	private Double percentage;
}
