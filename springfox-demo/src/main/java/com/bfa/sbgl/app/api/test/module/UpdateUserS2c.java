package com.bfa.sbgl.app.api.test.module;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;



@Getter
@Setter
@ToString
public class UpdateUserS2c implements Serializable {

	private static final long serialVersionUID = -5267482584689902883L;

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "用户编号", required = true)
    private long id;

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "用户名", required = true)
    private String name;

}
