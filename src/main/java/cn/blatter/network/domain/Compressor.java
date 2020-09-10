package cn.blatter.network.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * @author tanyao
 * @Date 2020/9/6 20:45
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Compressor extends Base {
    /**
     * 压缩机编号
     */
    private Integer id;
    /**
     * 干线压力，MPa
     */
    private Double mainPressure;

    /**
     * 天然气热值，kJ/m³
     */
    private Double calorificValue;

    /**
     * 原动机效率
     */
    private Double engineEfficiency;

    /**
     * 压缩机效率
     */
    private Double compressorEfficiency;
}
