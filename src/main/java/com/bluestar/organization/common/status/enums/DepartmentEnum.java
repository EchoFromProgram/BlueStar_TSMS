package com.bluestar.organization.common.status.enums;

import com.bluestar.organization.common.status.Statusable;

/**
 * 部门业务状态类
 *
 * @author Fish
 * ------> 1149062639@qq.com
 * created by 2018/07/18 15:15:13
 */
public enum DepartmentEnum implements Statusable {

    SUCCESS(Statusable.SUCCESS_CODE, "操作成功");

    private int code;
    private String msg;

    DepartmentEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
