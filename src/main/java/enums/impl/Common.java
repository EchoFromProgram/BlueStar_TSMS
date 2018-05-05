package enums.impl;

import enums.Statusable;

/**
 * 常用状态的枚举类
 *
 * @author Fish
 * */
public enum Common implements Statusable
{
    SUCCESS(0, "成功！"),
    ERROR(-1, "错误！");

    // 创建状态码
    private int code;

    // 创建信息
    private String info = null;

    Common()
    {}

    Common(int code, String info)
    {
        this.code = code;
        this.info = info;
    }

    @Override
    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    @Override
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
        return "Common{" +
                "code=" + code +
                ", info='" + info + '\'' +
                '}';
    }
}
