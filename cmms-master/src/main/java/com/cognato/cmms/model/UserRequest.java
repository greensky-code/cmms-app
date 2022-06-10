package com.cognato.cmms.model;

//import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class UserRequest {

    @NotBlank(message = "user name is mandatory")
//    @ApiModelProperty("User name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
