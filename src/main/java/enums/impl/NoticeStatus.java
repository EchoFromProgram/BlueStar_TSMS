package enums.impl;

import enums.Statusable;

/**
 * 通知相关枚举类
 *
 * @author Fish
 * created by 2018-05-07
 */
public enum NoticeStatus implements Statusable
{
    SUCCESS(0, "发布成功！");

    // 状态码
    private int code;

    // 返回信息
    private String info = null;

    NoticeStatus()
    {}

    NoticeStatus(int code, String info)
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
}
