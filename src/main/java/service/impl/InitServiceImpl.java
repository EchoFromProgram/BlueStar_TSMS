package service.impl;

import dao.AccountDao;
import entity.Power;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.InitService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提供服务器初始化的业务实现类
 *
 * @author Fish
 */
@Service
public class InitServiceImpl implements InitService
{
    private AccountDao accountDao = null;

    @Autowired
    public void setAccountDao(AccountDao accountDao)
    {
        this.accountDao = accountDao;
    }

    /**
     * 得到整张权限表
     *
     * @return 整张权限表，以 Map 封装，key 值为 权限 id，value 值为权限（这里主要是 URL）
     */
    public Map<Integer, String> getAllPowers()
    {
        Map<Integer, String> powersMap = new HashMap<>();
        List<Power> powers = accountDao.getPowers();

        if (powers != null)
        {
            // 如果成功得到权限表，就将它转成 Map
            for (Power power : powers)
            {
                powersMap.put(power.getPowerId(), power.getPower());
            }
        }

        return powersMap;
    }
}
