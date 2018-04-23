package service;


import java.util.Map;

/**
 * 提供服务器初始化的业务类
 *
 * @author Fish
 * */
public interface InitService
{
    /**
     * 得到整张权限表
     *
     * @return 整张权限表，以 Map 封装，key 值为 权限 id，value 值为权限（这里主要是 URL）
     */
    public Map<Integer, String> getAllPowers();
}
