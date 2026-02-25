package com.mall.spzx.model;

import lombok.Data;

@Data
public class PageDomain {
    private Integer pageNum;

    private Integer pageSize;

    private String orderBy;

    private String isAsc = "asc";
}
