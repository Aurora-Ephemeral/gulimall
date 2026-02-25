package com.mall.spzx.log.enums;

public enum OperatorType {
    OTHER("其他"),
    MANAGER("后台人员"),
    USER("普通用户");

    final String value;

    OperatorType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
