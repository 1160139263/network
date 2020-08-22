package cn.blatter.network.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * @author tanyao
 * @Date 2020/7/13 13:37
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pipe {
	private String name;
	private Integer id;
	private Integer projectId;
	private Integer modelId;
	private Integer startId;
	private Integer startConnection;
	private Integer endConnection;
	private String startConnectionName;
	private String endConnectionName;
	private String startName;
	private Integer endId;
	private String endName;
	private Double diameter;
	private Double length;
	private Double roughness;
	private Double lambda;

	public void initLambda() {
		lambda = 0.009407 / Math.pow(diameter, 1.0/3);
	}
}
