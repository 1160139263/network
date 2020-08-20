package cn.blatter.network.common;

/**
 * @author tanyao
 * @Date 2020/8/4 18:35
 */
public enum GasName {

	c1("C1", "甲烷"),
	c2("C2", "乙烷"),
	c3("C3", "丙烷"),
	ic4("iC4", "正丁烷"),
	nc4("nC4", "异丁烷"),
	ic5("iC5", "正戊烷"),
	nc5("nC5", "异戊烷"),
	c6("C6", "己烷"),
	n2("N2", "氮气"),
	co2("CO2", "二氧化碳");

	private final String formula;
	private final String name;

	GasName(String formula, String name) {
		this.formula = formula;
		this.name = name;
	}

	public String getFormula() {
		return formula;
	}

	public String getName() {
		return name;
	}
}
