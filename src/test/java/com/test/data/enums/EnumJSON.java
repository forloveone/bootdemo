package com.test.data.enums;

import com.google.gson.annotations.SerializedName;

public enum EnumJSON {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    @SerializedName("6")
    FRIDAY,
    SATURDAY,
    SUNDAY;

//
//    private EnumJSON(String name) {
//        this.name = name;
//    }
    private String name;

    public String getName() {
        return name;
    }

}
