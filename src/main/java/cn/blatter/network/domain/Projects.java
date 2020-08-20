package cn.blatter.network.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Projects {
    private Integer comId;

    private Integer pid;

    private String info;

    private String author;

    private Date createTime;

    private Date updateTime;

    private String type;
}
