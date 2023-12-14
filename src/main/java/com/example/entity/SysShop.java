package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author
 * @since 2022-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_shop")
public class SysShop implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
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
    //    库存
    private Long count;

}
