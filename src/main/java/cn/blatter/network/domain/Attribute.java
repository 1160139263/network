package cn.blatter.network.domain;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Attribute {
    private Integer element_id;
    private Double pressure;
    private Boolean pressure_state;
    private Double loads;
    private Boolean load_state;
    private Double elevation;
}
