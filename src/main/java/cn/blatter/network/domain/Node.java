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
public class Node implements Serializable {
	private String name;
	private Integer id;
	private Integer modelId;
	private Integer elementId;
	private String elementName;
	private Integer projectId;
	private Double pressure;
	private Double loads;
	private Double x;
	private Double y;
	private boolean pressureState;
	private boolean loadState;
	private Double elevation;
}
