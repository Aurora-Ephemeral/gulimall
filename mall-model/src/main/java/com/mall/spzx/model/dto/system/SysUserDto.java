package com.mall.spzx.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "请求参数实体类")
public class SysUserDto {

    @Schema(description = "搜索关键字")
    private String keyword ;

    @Schema(description = "开始时间")
    private String createTimeBegin ;

    @Schema(description = "结束时间")
    private String createTimeEnd;

    @Schema(description = "当前页")
    private Integer pageNum;

    @Schema(description = "每页显示条数")
    private Integer pageSize;

}
