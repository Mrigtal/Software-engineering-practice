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
public class SysShopVo extends PageVo {


    /**
     * id
     */
    private Long id;

    /**
     * 商品名
     */
    private String shopname;

    /**
     * 商品描述
     */
    private String shopmessage;

    /**
     * 商品价格
     */
    private String shoppice;

    /**
     * 商品图片
     */
    private String shoppoto;

    private String status;
    private Long count;
    private Long categoryId;
    private String categoryName;

}
