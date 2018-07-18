package com.bluestar.organization.dto;

import com.bluestar.organization.common.status.Statusable;

/**
 * 部门业务传输类
 *
 * @author Fish
 * ------> 1149062639@qq.com
 * created by 2018/07/18 15:07:53
 */
public class DepartmentDto<T> {

    private int code;
    private String msg = null;
    private T data = null;

    private DepartmentDto(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> DepartmentDto<T> response(Statusable status, T data) {
        return new DepartmentDto<T>(status.getCode(), status.getMsg(), data);
    }

    public static DepartmentDto response(Statusable status) {
        return new DepartmentDto(status.getCode(), status.getMsg(), null);
    }
}
