package cn.blatter.network.domain;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Attribute {
    private Integer id;
    private Integer element_id;
    private String attribute_name;
    private String attribute_value;
}
