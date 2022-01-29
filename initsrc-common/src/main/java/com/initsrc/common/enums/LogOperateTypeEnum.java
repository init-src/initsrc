package com.initsrc.common.enums;

/**
 * 描述：日志类型
 * 作者：INITSRC
 */
public enum LogOperateTypeEnum {

    LOGIN("登录", 1),

    SEARCH("查询", 2),

    ADD("新增", 3),

    EDIT("编辑", 4),

    DEL("删除", 5),

    IMPORT("导入", 6),

    EXPORT("导出", 7),

    GENERATOR("生成代码", 8);


    private String operateDesc;
    private int operateCode;


    LogOperateTypeEnum(String operateDesc, int operateCode) {
        this.operateDesc = operateDesc;
        this.operateCode = operateCode;
    }

    public static String getMessage(int operateCode) {
        //通过enum.values()获取所有的枚举值
        for (LogOperateTypeEnum logOperateTypeEnum : LogOperateTypeEnum.values()) {
            //通过enum.get获取字段值
            if (logOperateTypeEnum.getOperateCode() == operateCode) {
                return logOperateTypeEnum.operateDesc;
            }
        }
        return null;
    }

    public String getOperateDesc() {
        return operateDesc;
    }

    public int getOperateCode() {
        return operateCode;
    }

    public void setOperateDesc(String operateDesc) {
        this.operateDesc = operateDesc;
    }

    public void setOperateCode(int operateCode) {
        this.operateCode = operateCode;
    }
}
