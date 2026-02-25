package com.mall.spzx.utils;

import com.mall.spzx.model.PageDomain;

public class TableSupport {

    public static final String PAGE_NUM = "pageNum";

    public static final String PAGE_SIZE = "pageSize";

    public static final String ORDER_BY_COLUMN = "orderBy";

    public static final String IS_ASC = "isAsc";

    public static PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(Convert.toInt(ServeletUtils.getParameter(PAGE_NUM), 1));
        pageDomain.setPageSize(Convert.toInt(ServeletUtils.getParameter(PAGE_SIZE), 10));
        pageDomain.setOrderBy(ServeletUtils.getParameter(ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServeletUtils.getParameter(IS_ASC));
        return pageDomain;
    }

}
