package cn.blatter.network.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * @author tanyao
 * @Date 2020/9/6 20:49
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Station extends Base {
    private Integer id;
    private Double inletPressure;
    private Double outletPressure;
    private Double production;
}
