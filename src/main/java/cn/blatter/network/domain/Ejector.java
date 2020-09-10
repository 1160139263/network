package cn.blatter.network.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * @author tanyao
 * @Date 2020/9/6 20:46
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ejector extends Base {
    /**
     * 引射器编号
     */
    private Integer id;

    /**
     * 膨胀比,Ph:Pl
     */
    private Double expandRate;

    /**
     * 压缩比,Pm:Pl
     */
    private Double compressRate;

    /**
     * 引射率
     */
    private Double ejectorRate;

    /**
     * 等熵效率
     */
    private Double efficiency;
}
