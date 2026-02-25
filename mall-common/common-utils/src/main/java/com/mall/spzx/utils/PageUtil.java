package com.mall.spzx.utils;

import com.github.pagehelper.PageHelper;
import com.mall.spzx.model.PageDomain;

public class PageUtil {


    public static void startPage() {
        PageDomain pageDomain = TableSupport.getPageDomain();
        System.out.println("pageDomain" + pageDomain);
        PageHelper.startPage(pageDomain.getPageNum(), pageDomain.getPageSize()).setOrderBy(pageDomain.getOrderBy());
    }

    public static void clearPage() {
    }
}
