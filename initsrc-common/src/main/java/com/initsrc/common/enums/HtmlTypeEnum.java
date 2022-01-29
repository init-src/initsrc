package com.initsrc.common.enums;

/**
 * 描述： 代码生成前端输入类型
 * 作者：INITSRC
 */
public enum HtmlTypeEnum {
    INPUT("1","文本框"),
    TEXTAREA("2","文本框"),
    INPUTNUM("3","数字文本框"),
    INPUTDOUBLE("4","浮点型文本框"),
    RICHEDIT("5","富文本框"),
    DATEPICKER("6","日期选择器"),
    IMAGEPICKER("7","图片选择器"),
    FILEPICKER("8","文件选择器"),
    SELECT("9","选择器"),
    REMOTESELECT("10","远程选择器"),
    TREESELECT("11","树形选择器"),
    CHECKBOX("12","复选框"),
    RADIO("13","单选框");

    HtmlTypeEnum(String value, String des) {
        this.value = value;
        this.des = des;
    }

    private String value;
    private String des;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
