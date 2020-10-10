package cn.blatter.network.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Attribute implements Serializable{
    private Integer id;
    private Integer element_id;
    private String attribute_name;
    private String attribute_value;
    private List<Element> elements;
}
