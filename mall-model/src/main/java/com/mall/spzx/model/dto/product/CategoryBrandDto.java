package com.mall.spzx.model.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "搜索条件实体类")
public class CategoryBrandDto {

	@Schema(description = "品牌id")
	private List<Long> brandIds;

	@Schema(description = "分类id")
	private List<Long> categoryIds;

}