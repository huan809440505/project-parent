package com.hyl.rock.entity;

import com.hyl.rock.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("商品信息")
public class Product extends BaseEntity {

    @ApiModelProperty("商品编号")
    private String productNo;

    @ApiModelProperty("商品名称")
    private String productName;
}
