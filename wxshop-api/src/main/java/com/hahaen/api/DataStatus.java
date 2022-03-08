package com.hahaen.api;

public enum DataStatus {
    OK(),
    DELETED(),

    PENDING(),
    PAID(),
    DELIVERED(),
    RECEIVED();

    public String getName() {
        return name().toLowerCase();
    }
}
