package service.impl;

import constant.Type;
import dao.AccountDao;
import dto.AccountDto;
import entity.*;
import enums.impl.Common;
import enums.impl.CreateAccountStatus;
import enums.impl.LoginStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.AccountService;
import utils.PageUtil;

import java.util.*;

/**
 * 账号业务实现类
 *
 * @author Fish
 */
@Service
public class AccountServiceImpl implements AccountService
{
    // 账号持久层对象
    private AccountDao accountDao = null;

    @Autowired
    public void setAccountDao(AccountDao accountDao)
    {
        this.accountDao = accountDao;
    }

    /**
     * 登陆业务，传入一个用户对象进行登陆
     *
     * @param user 前台传过来的要登陆的用户
     * @return true 登陆验证通过，false 用户名或密码不正确
     */
    public AccountDto login(User user)
    {
        // 如果前台给过来的用户名是空的，返回错误提示
        if (user == null || user.getUserName() == null || "".equals(user.getUserName()))
        {
            return new AccountDto(LoginStatus.WRONG_USERNAME);
        }

        // 从数据库中查出这个账号的密码
        Map u = accountDao.getUserByUserName(user.getUserName());

        // 用户名不存在！
        if (u == null)
        {
            return new AccountDto(LoginStatus.WRONG_USERNAME);
        }

        // 判断前台登陆用户输入的密码和后台数据的密码是否一致
        if (!u.get("password").equals(user.getPassword()))
        {
            return new AccountDto(LoginStatus.WRONG_PASSWORD);
        }

        // 登陆成功，将要携带的信息带给前台
        Map<String, Object> infos = new HashMap<String, Object>();
        infos.put("user", u);
        infos.put("hisPowers", accountDao.getPowerIdByRoleId((Integer) u.get("role_id")));

        Integer[] classIds = accountDao.getClassIdsByUserId((Integer) u.get("user_id")).toArray(new Integer[0]);
        Arrays.sort(classIds); // 给这个列表排序
        List<Clazz> clazzes = new ArrayList<>();
        for (Integer i : classIds) // 通过班级 id 获取班级信息
        {
            clazzes.add(accountDao.getClassByClassId(i));
        }
        infos.put("hisClasses", clazzes);

        return new AccountDto<Map>(infos, LoginStatus.SUCCESS);
    }

    /**
     * 验证用户名是否可用
     * 这里的可用有两种含义：
     * 1）登陆的时候：这个方法用于检测账号是否正确，在数据库中是否存在
     * 2）创建用户的时候：这个方法用于检测账号是否已经存在，存在时不允许创建这个账号
     *
     * @param username 用于验证的账号
     * @return true 账号存在，false 账号不存在
     */
    public boolean userNameExisted(String username)
    {
        return accountDao.userNameIsExit(username) > 0;
    }

    /**
     * 创建一个用户，由前台传过来一个新用户
     *
     * @param user 前台传过来的用户
     * @return 返回创建的信息状态
     */
    public AccountDto createAccount(User user)
    {
        // 如果前台传了一个空对象过来，创建失败
        if (user == null)
        {
            return new AccountDto(CreateAccountStatus.USER_IS_NULL);
        }

        // 如果这个用户的账号或密码为空，返回提示
        if (user.getUserName() == null || "".equals(user.getUserName())
                || user.getPassword() == null || "".equals(user.getPassword()))
        {
            return new AccountDto(CreateAccountStatus.CORE_INFO_IS_NULL);
        }

        // 如果这个用户的其他信息为空，返回提示
        if (user.getName() == null || "".equals(user.getName())
                || user.getRoleId() == null || user.getTypeId() == null)
        {
            return new AccountDto(CreateAccountStatus.INFO_IS_NOT_COMPLETED);
        }

        // 当所有信息都填完整了，就进行数据库查询，看看这个用户是否存在
        if (userNameExisted(user.getUserName()))
        {
            return new AccountDto(CreateAccountStatus.USERNAME_EXISTED);
        }

        switch (user.getTypeId())
        {
            case Type.INNER_STAFF:
                // 内部员工
                Staff staff = new Staff();
                accountDao.insertIntoStaff(staff);
                user.setInfoId(staff.gettId()); // 填充详细信息 id
                break;
            case Type.OUTTER_CLIENT:
                // 外部客户
                Customer customer = new Customer();
                accountDao.inserIntoCustomer(customer);
                System.out.println(customer);
                user.setInfoId(customer.getInfoId()); // 填充详细信息 id
                break;
        }

        int affect = accountDao.insertIntoUser(user);
        if (affect > 0) // 创建成功！
        {
            return new AccountDto(CreateAccountStatus.SUCCESS);
        }

        // 没有新增成功！未知错误！
        return new AccountDto(CreateAccountStatus.UNKNOWN_ERROR);
    }

    /**
     * 得到所有的账户信息，主要供管理员使用
     *
     * @param pageNumber 获取第几页的数据
     * @return 返回所有账户的信息
     */
    @Override
    public AccountDto getAllAccounts(Integer pageNumber)
    {
        if (pageNumber == null) // 如果参数为空，则返回参数错误
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        // pageHelper 中每进行一次分页就要执行一次这个方法
        PageUtil.toPage(pageNumber);

        List<User> users = accountDao.getAllUsers();
        if (users == null) // 如果为空，说明没有获取到数据，有可能是系统错误
        {
            return new AccountDto(Common.ERROR);
        }

        // 这里如果 users 的元素个数为 0 也算成功，只能说没有成员
        return new AccountDto<>(PageUtil.pageInfo(users), Common.SUCCESS);
    }

    /**
     * 通过 roleId 获取用户信息，目前主要是内部和外部
     *
     * @param pageNumber 页数
     * @param typeId 用户类型
     * @return 返回用户信息
     */
    public AccountDto getAccounts(Integer pageNumber, Integer typeId)
    {
        if (pageNumber == null || typeId == null) // 如果参数为空，则返回参数错误
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        // pageHelper 中每进行一次分页就要执行一次这个方法
        PageUtil.toPage(pageNumber);
        List<Map<String, Object>> users = accountDao.getUsersByTypeId(typeId);
        if (users == null) // 如果为空，说明没有获取到数据，有可能是系统错误
        {
            return new AccountDto(Common.ERROR);
        }

        // 这里如果 users 的元素个数为 0 也算成功，只能说没有成员
        return new AccountDto<>(PageUtil.pageInfo(users), Common.SUCCESS);
    }

    /**
     * 根据信息id得到信息工具类
     *
     * @param infoId 信息id
     * @param typeId 类型id
     * @return 返回处理结果
     */
    private AccountDto getCustomerOrStaffByInfoId(Integer infoId, Integer typeId)
    {
        //参数为空
        if (infoId == null)
        {
            return new AccountDto<>(Common.WRONG_ARGEMENT);
        }
        if (Type.INNER_STAFF == typeId)
        {
            //得到员工信息
            Staff staff = accountDao.getStaffDetailByTid(infoId);
            if (staff == null)
            {
                return new AccountDto<>(Common.GET_IS_NULL);
            }
            return new AccountDto<>(staff, Common.SUCCESS);
        }
        else if (Type.OUTTER_CLIENT == typeId)
        {
            //得到客户信息
            Customer customer = accountDao.getCustomerDetailByInfoId(infoId);
            if (customer == null)
            {
                return new AccountDto<>(Common.GET_IS_NULL);
            }
            return new AccountDto<>(customer, Common.SUCCESS);
        }
        else
        {
            return new AccountDto<>(Common.ERROR);
        }
    }

    /**
     * 根据信息id更新员工或客户
     *
     * @param object 信息id
     * @param typeId 类型id
     * @return 处理结果
     */
    private AccountDto updateCustomerOrStaffInfoByInfoId(Object object, Integer typeId)
    {
        //参数为空
        if (object == null)
        {
            return new AccountDto<>(Common.WRONG_ARGEMENT);
        }
        if (Type.INNER_STAFF == typeId)
        {
            //更新员工信息
            int num = accountDao.settingStaffInfo((Staff) object);
            if (num == 0)
            {
                return new AccountDto<>(Common.ERROR);
            }
            return new AccountDto<>(Common.SUCCESS);
        }
        else if (Type.OUTTER_CLIENT == typeId)
        {
            //得到客户信息
            int num = accountDao.settingCustomerInfo((Customer) object);
            if (num == 0)
            {
                return new AccountDto<>(Common.ERROR);
            }
            return new AccountDto<>(Common.SUCCESS);
        }
        else
        {
            return new AccountDto<>(Common.ERROR);
        }
    }

    /**
     * 得到员工信息
     *
     * @param infoId 信息id
     * @return 处理结果状态
     */
    public AccountDto getStaffInfoByInfoId(Integer infoId)
    {
        return getCustomerOrStaffByInfoId(infoId, Type.INNER_STAFF);
    }

    /**
     * 得到客户信息
     *
     * @param infoId 信息id
     * @return 处理结果状态
     */
    public AccountDto getCustomerInfoByInfoId(Integer infoId)
    {
        return getCustomerOrStaffByInfoId(infoId, Type.OUTTER_CLIENT);
    }

    /**
     * 根据信息id更新员工信息
     *
     * @param staff 员工类
     * @return 处理结果
     */
    public AccountDto updateStaffInfoByInfoId(Staff staff)
    {
        return updateCustomerOrStaffInfoByInfoId(staff, Type.INNER_STAFF);
    }

    /**
     * 根据信息id更新客户信息
     *
     * @param customer 客户类
     * @return 处理结果
     */
    public AccountDto updateCustomerInfoByInfoId(Customer customer)
    {
        return updateCustomerOrStaffInfoByInfoId(customer, Type.OUTTER_CLIENT);
    }

    /**
     * 得到所有的省份
     *
     * @return 返回省份集合
     */
    @Override
    public AccountDto getAllProvinces()
    {
        //得到所有省份
        List<Province> provinces = accountDao.getProvinces();
        //获得为空异常
        if (provinces == null)
        {
            return new AccountDto<>(Common.GET_IS_NULL);
        }
        return new AccountDto<>(Common.SUCCESS);
    }

    /**
     * 根据省份id得到对应的所有城市
     *
     * @param provinceId 省份id
     * @return 返回城市集合
     */
    @Override
    public AccountDto getCitysByProvinceId(Integer provinceId)
    {
        //参数为空异常
        if (provinceId == null)
        {
            return new AccountDto<>(Common.WRONG_ARGEMENT);
        }
        //获得城市
        List<City> citys = accountDao.getCitysByProvinceId(provinceId);
        //得到结果为空异常
        if (citys == null)
        {
            return new AccountDto<>(Common.GET_IS_NULL);
        }
        return new AccountDto<>(Common.SUCCESS);
    }

    /**
     * 根据城市id得到所有学校
     *
     * @param cityId 城市id
     * @return 返回城市集合
     */
    @Override
    public AccountDto getSchoolsByCityId(Integer cityId)
    {
        //获得参数为空异常
        if (cityId == null)
        {
            return new AccountDto<>(Common.WRONG_ARGEMENT);
        }
        List<School> schools = accountDao.getSchoolsByCityId(cityId);
        //得到结果为空异常
        if (schools == null)
        {
            return new AccountDto<>(Common.GET_IS_NULL);
        }
        return new AccountDto<>(Common.SUCCESS);
    }
}
