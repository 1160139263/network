package cn.blatter.network.domain;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Connection {
    private Integer id;
    private Integer element_id;
    private Double x;
    private Double y;
    private String name;
}
