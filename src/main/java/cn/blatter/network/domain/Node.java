package cn.blatter.network.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

/**
 * @author tanyao
 * @Date 2020/7/13 13:34
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Node extends Base {
	private Integer id;
	private Double pressure;
	private Double loads;
	private boolean pressureState;
	private boolean loadState;
}
