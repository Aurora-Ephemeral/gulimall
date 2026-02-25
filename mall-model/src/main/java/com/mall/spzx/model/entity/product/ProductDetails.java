package com.mall.spzx.model.entity.product;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.spzx.model.entity.base.BaseEntity;
import lombok.Data;

@Data
@TableName("product_details")
public class ProductDetails extends BaseEntity {

	private Long productId;
	private String imageUrls;

}