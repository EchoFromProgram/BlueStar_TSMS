package enums.impl;

import enums.Statusable;

/**
 * 签到情况枚举类
 *
 * @author Fish
 * */
public enum SignStatus implements Statusable
{
    SUCCESS(0, "签到成功！"); // TODO 还没完工...

    // 签到情况状态码
    private int code;

    // 签到情况信息
    private String info = null;

    SignStatus()
    {}

    SignStatus(int code, String info)
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

    @Override
    public String toString()
    {
        return "SignStatus{" +
                "code=" + code +
                ", info='" + info + '\'' +
                '}';
    }
}
