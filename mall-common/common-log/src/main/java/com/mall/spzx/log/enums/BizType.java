package com.mall.spzx.log.enums;

public enum BizType {
    ADD(0),
    QUERY(1),
    UPDATE(2),
    DELETE(3);

    final Integer value;

    BizType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {

        return this.value;
    }
}
