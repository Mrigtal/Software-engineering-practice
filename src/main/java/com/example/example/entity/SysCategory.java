package com.example.entity;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2023-11-30
 */

@Data
@TableName("sys_category")
public class SysCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;


}
