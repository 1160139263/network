package cn.blatter.network.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Base implements Serializable {
    private Integer id;
    private Integer modelId;
    private Integer elementId;
    private Integer projectId;
    private String name;
    private String elementName;
    private Double x;
    private Double y;
    private Double elevation;
}
