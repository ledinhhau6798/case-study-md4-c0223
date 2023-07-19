package com.cg.model.enums;

public enum EStatus {
    ROLE_STOCKING("STOCKING"),
    ROLE_OUT_OF_STOCK("OUT_OF_STOCK");

    private final String value;

    EStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
