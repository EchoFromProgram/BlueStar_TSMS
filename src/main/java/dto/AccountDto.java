package dto;

import enums.LoginStatus;

/**
 * 用于账号登陆的传输信息类
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
    private LoginStatus status = null;

    public AccountDto()
    {}

    public AccountDto(T data, LoginStatus status)
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

    public LoginStatus getStatus()
    {
        return status;
    }

    public void setStatus(LoginStatus status)
    {
        this.status = status;
    }
}
