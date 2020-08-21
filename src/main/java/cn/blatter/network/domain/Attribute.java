package cn.blatter.network.domain;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Attribute {
    private Integer element_id;
    private Double pressure;
    private boolean pressure_state;
    private Double loads;
    private boolean load_state;
    private Double elevation;
}
