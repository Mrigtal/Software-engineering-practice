package com.example.vo;

import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2022-12-04
 */
@Data
public class UserVo extends PageVo {
    /**
     * id
     */
    private Long id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色0是用户1是管理员
     */
    private String role;
}
