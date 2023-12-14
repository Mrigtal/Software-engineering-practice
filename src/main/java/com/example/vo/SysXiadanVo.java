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

public class SysXiadanVo extends PageVo{


    /**
     * id
     */
    private Long id;

    /**
     * 商品id
     */
    private String shopid;

    /**
     * 下单人
     */
    private String xiadanname;

    /**
     * 下单电话
     */
    private String xiadanphone;

    /**
     * 下单备注
     */
    private String xiadanmark;

    private String username;


}
