package cn.blatter.network.domain;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Type implements Serializable {
    private Integer tid;

    private String info;

    private String name;
}
