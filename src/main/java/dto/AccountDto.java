package dto;

import enums.Statusable;

/**
 * 用于账号登陆的传输信息类
 * T 是被携带数据的类型
 *
 * @author Fish
 * */
public class AccountDto<T>
{
    /**
     * 传输的主要数据
     * 如果是成功就返回正常的数据 Object
     * 如果错误，就返回错误信息 String
     * */
    private T data = null;

    /**
     * 登陆状态
     * 这是一个枚举类，包含有登陆的状态码和状态信息
     * */
    private Statusable status = null;

    public AccountDto()
    {}

    public AccountDto(T data, Statusable status)
    {
        this.data = data;
        this.status = status;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public Statusable getStatus()
    {
        return status;
    }

    public void setStatus(Statusable status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "AccountDto{" +
                "data=" + data +
                ", status=" + status +
                '}';
    }
}
