package com.qx.core.utils;


public enum TimeUnits{

    /** 分 */
    minute("minute", "分"),

    /** 时 */
    hour("hour", "时"),

    /** 天 */
    day("day", "天"),

    /** 周 */
    week("week", "周"),

    /** 月 */
    month("month", "月"),

    /** 年 */
    year("year", "年");

    private String value;
    private String text;

    private TimeUnits(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
