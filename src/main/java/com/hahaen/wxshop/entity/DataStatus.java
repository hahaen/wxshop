package com.hahaen.wxshop.entity;

public enum DataStatus {
    OK(),
    DELETED();

    public String getName() {
        return name().toLowerCase();
    }
}
