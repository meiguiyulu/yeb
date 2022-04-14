package com.lyj.server.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户登录实体类
 */
@Data
@ApiModel(value = "AdminLogin对象", description = "用户登录实体类")
public class AdminLoginParam {
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
/*    @ApiModelProperty(value = "验证码", required = true)
    private String code;*/
}
