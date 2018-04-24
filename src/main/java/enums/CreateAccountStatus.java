package enums;

/**
 * 创建用户的状态枚举类
 *
 * @author Fish
 * */
public enum CreateAccountStatus
{
    SUCCESS(0, "创建成功！"),
    USERNAME_IS_NULL(-1, "用户名为空！"),
    PASSWORD_IS_NULL(-2, "密码为空！"),
    NAME_IS_NULL(-3, "名字为空！"),
    INFO_IS_NOT_COMPLETED(-4, "用户信息不完整！"),
    USER_IS_NULL(-5, "用户对象为空！"),
    USERNAME_EXISTED(-6, "用户名已经存在！");

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
