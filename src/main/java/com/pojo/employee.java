package com.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
//@AllArgsConstructor
//@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
//@JsonPropertyOrder(value = {"name","salary","age"})
//@JsonIgnoreProperties(value = {"status","data","message"})
@Builder(setterPrefix = "set")
@Getter
public class employee {


    private String name;
    private String salary;
    private String age;

}
