package service.impl;

import constant.Role;
import dao.AccountDao;
import dao.SignDao;
import dto.AccountDto;
import entity.Clazz;
import entity.Sign;
import entity.SignData;
import entity.User;
import enums.impl.Common;
import enums.impl.CreateAccountStatus;
import enums.impl.SignStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.SignService;
import utils.PageUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 签到业务实现类
 *
 * @author Fish
 * */
@Service
public class SignServiceImpl implements SignService
{
    // 签到持久层对象
    private SignDao signDao = null;

    @Autowired
    public void setSignDao(SignDao signDao)
    {
        this.signDao = signDao;
    }

    // 账号持久层对象
    private AccountDao accountDao = null;

    @Autowired
    public void setAccountDao(AccountDao accountDao)
    {
        this.accountDao = accountDao;
    }

    /**
     * 获取到一个随机的签到码
     * 范围是 1000 ~ 9999
     *
     * @return 返回生成的签到码
     */
    @Override
    public int getSignCode()
    {
        return 1000 + (int) (9000 * Math.random());
    }

    /**
     * 获取用户所属班级，签到时需要老师指定现在在哪个班级上课
     *
     * @param user 指定的用户
     * @return 返回这个用户所属的班级
     */
    @Override
    public AccountDto getClasses(User user)
    {
        // 避免空指针
        if (user == null || user.getUserId() == null)
        {
            return new AccountDto(Common.ERROR);
        }

        List<Integer> classIds = accountDao.getClassIdsByUserId(user.getUserId());
        if (classIds == null || classIds.size() == 0)
        {
            // 这个用户没有所属班级
            return new AccountDto(Common.ERROR);
        }

        // 遍历去获取所有班级信息
        List<Clazz> clazzes = new ArrayList<>(classIds.size());
        for (Integer classId : classIds)
        {
            clazzes.add(accountDao.getClassByClassId(classId));
        }

        return new AccountDto<List<Clazz>>(clazzes, Common.SUCCESS);
    }

    /**
     * 学生签到方法，传入一个对象和验证码
     *
     * @param user   要签到的用户
     * @param classId  用户所属班级
     *               如果用户没有班级，那就传入一个 null 即可
     * @param reason * 签到原因，（选填）
     *               如果是迟到或者旷课就必须填写
     * @return 返回签到情况
     */
    @Override
    public AccountDto sign(User user, Integer inputCode, Integer realCode, Integer classId, String reason)
    {
        // 必须存在这个用户才能进行签到
        if (user == null || user.getUserId() == null)
        {
            return new AccountDto(SignStatus.ILLEGAL_SIGN);
        }

        if (!realCode.equals(inputCode)) // 判断签到码是否正确
        {
            return new AccountDto(SignStatus.WRONG_CODE);
        }

        // 判断这个班级是否属于这个用户
        List<Clazz> classes = (List<Clazz>) this.getClasses(user).getData();
        Clazz clazz = accountDao.getClassByClassId(classId); // 得到这个班级信息
        if (!classes.contains(clazz))
        {
            return new AccountDto(SignStatus.WRONG_CLASS);
        }

        Sign sign = new Sign();
        sign.setUserId(user.getUserId());
        sign.setClassId(clazz.getClassId());
        sign.setCourseId(clazz.getCourseId());

        Date now = new Date();
        sign.setDate(now);
        sign.setReason(reason);
        // 暂定早上 8：30 - 9：10 签到，下午 13：00 到 13：40 签到
        int hours = now.getHours();
        int minutes = now.getMinutes();
        AccountDto accountDto = null;
        if ((hours <= 8 && minutes < 30) || (hours == 12) || (hours >= 16)) // 签到未开放
        {
            return new AccountDto(SignStatus.TOO_EARLY);
        }
        else if ((hours <= 9 && minutes <= 10) || (hours == 13 && minutes <= 40)) // 正常签到
        {
            sign.setStatus(SignStatus.SUCCESS.getCode());
            sign.setReason("正常签到！");
            accountDto = new AccountDto(SignStatus.SUCCESS);
        }
        else if ((hours <= 10 && minutes <= 10) || (hours <= 14 && minutes <= 40)) // 10：10 和 14：40 迟到
        {
            if (sign.getReason() == null || "".equals(sign.getReason())) // 迟到但是没有填写原因
            {
                return new AccountDto(SignStatus.TOO_LATE);
            }
            // TODO 签到还是没有完成。。。
        }

        signDao.insertIntoSign(sign);

        return accountDto;
    }

    /**
     * 判断签到状态，正常签到还是迟到啥的
     *
     * @return 返回签到状态信息
     */
    private AccountDto checkSignStatus()
    {
        return null;
    }

    /**
     * 根据班级信息获取签到信息
     *
     * @param pageNumber 要显示的页数
     * @param clazz 指定的班级
     * @return 返回指定的签到信息
     */
    @Override
    public AccountDto getClassSigns(Integer pageNumber, Clazz clazz)
    {
        if (pageNumber == null) // 判断页数是否为空
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        if (clazz == null || clazz.getClassId() == null)
        {
            return new AccountDto(SignStatus.WRONG_CLASS);
        }

        PageUtil.toPage(pageNumber);

        return new AccountDto<>(PageUtil.pageInfo(signDao.getSignDatasByClassId(clazz.getClassId())),
                SignStatus.SUCCESS);
    }

    /**
     * 获取指定班级的老师的签到信息
     *
     * @param pageNumber 指定的页数
     * @param clazz 指定班级
     * @return 返回指定班级的老师签到信息
     */
    @Override
    public AccountDto getTeacherSignsByClass(Integer pageNumber, Clazz clazz)
    {
        if (pageNumber == null) // 判断参数是否正确
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        if (clazz == null || clazz.getClassId() == null)
        {
            return new AccountDto(SignStatus.WRONG_CLASS);
        }

        PageUtil.toPage(pageNumber);
        return new AccountDto<>(PageUtil.pageInfo(signDao.getSignDatasByClassIdAndRoleId(clazz.getClassId(), Role.TEACHER)),
                SignStatus.SUCCESS);
    }

    /**
     * 获取指定班级的学生的签到信息
     *
     * @param pageNumber 指定的页数
     * @param clazz 指定班级
     * @return 返回指定班级的学生的签到信息
     */
    @Override
    public AccountDto getStudentSignsByClass(Integer pageNumber, Clazz clazz)
    {
        if (pageNumber == null) // 判断参数是否正确
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        if (clazz == null || clazz.getClassId() == null)
        {
            return new AccountDto(SignStatus.WRONG_CLASS);
        }

        PageUtil.toPage(pageNumber);
        return new AccountDto<>(PageUtil.pageInfo(signDao.getSignDatasByClassIdAndRoleId(clazz.getClassId(), Role.STUDENT)),
                SignStatus.SUCCESS);
    }

    /**
     * 获取整个签到表的信息
     *
     * @param pageNumber 指定的页数
     * @return 返回整个签到表
     */
    @Override
    public AccountDto getSigns(Integer pageNumber)
    {
        if (pageNumber == null) // 判断参数是否正确
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        PageUtil.toPage(pageNumber);
        return new AccountDto<>(PageUtil.pageInfo(signDao.getAllSignDatas()), SignStatus.SUCCESS);
    }

    /**
     * 获取这个用户的所有签到信息
     *
     * @param pageNumber 指定的页数
     * @param user 要被查询的用户
     * @return 返回这个用户的签到信息
     */
    @Override
    public AccountDto getSignsByUser(Integer pageNumber, User user)
    {
        if (pageNumber == null) // 判断参数是否正确
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        // 先判断用户是否可用
        if (user == null || user.getUserId() == null)
        {
            return new AccountDto(CreateAccountStatus.USER_IS_NULL);
        }
        
        PageUtil.toPage(pageNumber);
        return new AccountDto<>(PageUtil.pageInfo(signDao.getSignDatasByUserId(user.getUserId())),
                SignStatus.SUCCESS);
    }

    /**
     * 根据课程获得学生的签到情况
     *
     * @param pageNumber 指定的页数
     * @param courseId 课程id
     * @return 返回这个用户的签到信息
     */
	@Override
	public AccountDto getStudentSignsByCourseId(Integer pageNumber, Integer courseId) {
	    if (pageNumber == null || courseId == null) // 判断参数是否正确
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        
        PageUtil.toPage(pageNumber);
        return new AccountDto<>(PageUtil.pageInfo(signDao.getSignDatasByCourseIdAndRoleId(courseId, Role.STUDENT)),
                SignStatus.SUCCESS);
	}
	
	 /**
     * 根据课程获得老师的签到情况
     *
     * @param pageNumber 指定的页数
     * @param courseId 课程id
     * @return 返回这个用户的签到信息
     */
	@Override
	public AccountDto getTeacherSignsByCourseId(Integer pageNumber, Integer courseId) {
		 if (pageNumber == null || courseId == null) // 判断参数是否正确
	     {
	         return new AccountDto(Common.WRONG_ARGEMENT);
	     }

	        
	     PageUtil.toPage(pageNumber);
	     return new AccountDto<>(PageUtil.pageInfo(signDao.getSignDatasByCourseIdAndRoleId(courseId, Role.TEACHER)),
	             SignStatus.SUCCESS);
	}
	
	 /**
     * 根据课程和班级获得签到情况
     *
     * @param pageNumber 指定的页数
     * @param courseId 课程id
     * @param classId 班级id
     * @return 返回这个用户的签到信息
     */
	@Override
	public AccountDto getSignsByCouseIdAndClassId(Integer pageNumber, Integer courseId, Integer classId) {
		 if (pageNumber == null || courseId == null || classId == null) // 判断参数是否正确
	     {
	         return new AccountDto(Common.WRONG_ARGEMENT);
	     }

	        
	     PageUtil.toPage(pageNumber);
	     return new AccountDto<>(PageUtil.pageInfo(signDao.getSignDatasByClassIdAndCourseId(classId, courseId)),
	             SignStatus.SUCCESS);
	}

    /**
     * 老师通过 userId 获取他的班级的签到信息
     *
     * @param pageNumber 页数
     * @param userId 用户 id
     * @return 返回签到信息
     */
	public AccountDto getSignsByUserId(Integer pageNumber, Integer userId)
    {
        if (userId == null) // 参数错误
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        PageUtil.toPage(pageNumber);
        List<SignData> signs = signDao.getSignDatasByHisClassId(userId);
        if (signs == null) // 没能得到数据
        {
            return new AccountDto(Common.GET_IS_NULL);
        }

        return new AccountDto<>(PageUtil.pageInfo(signs), Common.SUCCESS);
    }

    /**
     * 通过用户 id 和课程 id 查询签到信息
     *
     * @param pageNumber 页数
     * @param userId 用户 id
     * @param courseId 课程 id
     * @return 返回签到信息
     */
    public AccountDto getSignsByCourseIdAndHisClassId(Integer pageNumber, Integer userId, Integer courseId)
    {
        // 参数错误
        if (pageNumber == null || userId == null || courseId == null)
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        PageUtil.toPage(pageNumber); // 开始分页
        List<SignData> signs = signDao.getSignDatasByCourseIdAndHisClassId(userId, courseId);
        if (signs == null) // 没能得到数据
        {
            return new AccountDto(Common.GET_IS_NULL);
        }

        return new AccountDto<>(PageUtil.pageInfo(signs), Common.SUCCESS);
    }

    /**
     * 通过课程 id 获取签到信息
     *
     * @param pageNumber 页数
     * @param courseId 课程 id
     * @return 返回课程信息
     */
    public AccountDto getSignsByCourseId(Integer pageNumber, Integer courseId)
    {
        if (pageNumber == null || courseId == null) // 参数错误
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        PageUtil.toPage(pageNumber); // 开始分页
        List<SignData> signs = signDao.getSignDatasByCourseId(courseId);
        if (signs == null) // 没有返回数据
        {
            return new AccountDto(Common.GET_IS_NULL);
        }

        return new AccountDto<>(PageUtil.pageInfo(signs), Common.SUCCESS);
    }
}
