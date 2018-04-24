package enums.impl;

import enums.Statusable;

/**
 * 创建用户的状态枚举类
 *
 * @author Fish
 * */
public enum CreateAccountStatus implements Statusable
{
    SUCCESS(0, "创建成功！"),
    CORE_INFO_IS_NULL(-1, "用户名或密码为空！"),
    INFO_IS_NOT_COMPLETED(-3, "用户信息不完整！"),
    USER_IS_NULL(-4, "用户对象为空！"),
    USERNAME_EXISTED(-5, "用户名已经存在！");

    // 创建状态码
    private int code;

    // 创建信息
    private String info = null;

    CreateAccountStatus()
    {}

    CreateAccountStatus(int code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }
}
