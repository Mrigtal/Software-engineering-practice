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
@TableName("sys_xiadan")
public class SysXiadan implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
      @TableId(value = "id", type = IdType.AUTO)
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
