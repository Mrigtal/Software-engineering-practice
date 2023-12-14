package com.example.dto;

import com.example.entity.SysShop;
import com.example.entity.SysXiadan;
import lombok.Data;

@Data
public class OrderShopDto extends SysXiadan{
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
    private SysShop shop;


    public static OrderShopDto from(SysXiadan sysXiadan,SysShop shop) {
        if (sysXiadan == null) {
            return null;
        }
        OrderShopDto orderShopDto = new OrderShopDto();
        orderShopDto.setId(sysXiadan.getId());
        orderShopDto.setShopid(sysXiadan.getShopid());
        orderShopDto.setXiadanname(sysXiadan.getXiadanname());
        orderShopDto.setXiadanphone(sysXiadan.getXiadanphone());
        orderShopDto.setXiadanmark(sysXiadan.getXiadanmark());
        orderShopDto.setUsername(sysXiadan.getUsername());
        orderShopDto.setShop(shop);
        return orderShopDto;
    }
}
