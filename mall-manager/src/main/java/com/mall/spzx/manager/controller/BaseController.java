package com.mall.spzx.manager.controller;

import com.mall.spzx.utils.PageUtil;

import java.util.List;

public class BaseController {

    protected void startPage() {
        PageUtil.startPage();
    }

    protected void clearPage() {
        PageUtil.clearPage();
    }

    protected void getDataTableInfo(List<?> list) {

    }
}
