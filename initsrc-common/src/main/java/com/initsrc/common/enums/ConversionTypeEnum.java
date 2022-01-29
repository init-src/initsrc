package com.initsrc.common.enums;

/**
 * 描述：数据库与JAVA类型转换
 * 作者：INITSRC
 */
public enum ConversionTypeEnum {
    CHAR("char","String"),
    VARCHAR("varchar","String"),
    BLOB("blob","String"),
    TEXT("text","String"),
    ENUM("enum","String"),
    SET("set","String"),
    FLOAT("float","float"),
    REAL("real","String"),
    DOUBLE("double","Double"),
    PRECISION("precision","String"),
    NUMERIC("numeric","String"),
    DECIMAL("decimal","BigDecimal"),
    INT("int","Integer"),
    TINYINT("tinyint","String"),
    SMALLINT("smallint","Integer"),
    MEDIUMINT("mediumint","Integer"),
    INTEGER("Integer","Integer"),
    BIGINT("bigint","String"),
    DATE("date","Date"),
    TIME("time","Date"),
    DATETIME("datetime","Date"),
    TIMESTAMP("timestamp","Date");

    private  String columnType;
    private  String javaType;

    ConversionTypeEnum(String columnType, String javaType) {
        this.columnType = columnType;
        this.javaType = javaType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }
}
