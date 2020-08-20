package cn.blatter.network.domain;


import lombok.*;

/**
 * @author tanyao
 * @Date 2020/7/9 18:11
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Component {

	private Integer id;

	private String name;

	private Double c1;

	private Double c2;

	private Double c3;

	private Double ic4;

	private Double nc4;

	private Double ic5;

	private Double nc5;

	private Double c6;

	private Double n2;

	private Double co2;

	public Component() {
		this.c1 = 0.0;
		this.c2 = 0.0;
		this.c3 = 0.0;
		this.ic4 = 0.0;
		this.nc4 = 0.0;
		this.ic5 = 0.0;
		this.nc5 = 0.0;
		this.c6 = 0.0;
		this.n2 = 0.0;
		this.co2 = 0.0;
	}
}
