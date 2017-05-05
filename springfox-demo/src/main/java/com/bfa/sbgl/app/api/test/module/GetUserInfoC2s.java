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
public class GetUserInfoC2s implements Serializable {

	@JsonProperty(required = true)
	@ApiModelProperty(value="user id", example="1", notes = "用户编号", required = true)
	private long id;


}
