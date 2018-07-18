package com.bluestar.organization.service.impl;

import com.bluestar.common.utils.CodeUtil;
import com.bluestar.organization.common.status.enums.DepartmentEnum;
import com.bluestar.organization.dao.DepartmentDao;
import com.bluestar.organization.dto.ServerResponse;
import com.bluestar.organization.entity.Department;
import com.bluestar.organization.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 部门业务具体实现类
 *
 * @author Fish
 * ------> 1149062639@qq.com
 * created by 2018/07/18 14:31:43
 */
@Service
/*
 * @Transactional中的的属性 propagation :事务的传播行为 isolation :事务的隔离级别 readOnly :只读
 *                     rollbackFor :发生哪些异常回滚 noRollbackFor :发生哪些异常不回滚
 *                     rollbackForClassName 根据异常类名回滚
 */
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentDao departmentDao = null;

    @Autowired
    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    /**
     * 新增一个部门
     *
     * @param department 要被添加的部门
     * @return 返回添加状态
     */
    public ServerResponse addDepartment(Department department) {
        // 首先进行合法性检验
        if (department == null) {
            return ServerResponse.response(DepartmentEnum.PARAMETER_UNCOMPLETED);
        }

        boolean isParameterUncompleted = (department.getDeptCode() == null ||
                department.getDeptName() == null || department.getDeptLevel() == null);
        if (isParameterUncompleted) {
            return ServerResponse.response(DepartmentEnum.PARAMETER_UNCOMPLETED);
        }

        // 检查部门编号是否可用
        ServerResponse resp = checkDepartmentCode(department.getDeptCode());
        if (!resp.isSuccess()) {
            return resp;
        }

        // 参数合法
        department.setDeptId(CodeUtil.getId());

        int affect = departmentDao.saveDepartment(department);
        if (affect <= 0) {
            return ServerResponse.response(DepartmentEnum.SAVE_FAILED);
        }

        // 保存成功
        return ServerResponse.response(DepartmentEnum.SUCCESS);
    }

    /**
     * 检查部门编号是否可用
     *
     * @param deptCode 部门编号
     * @return 返回状态信息
     */
    public ServerResponse checkDepartmentCode(String deptCode) {
        int count = departmentDao.countDepartmentCode(deptCode);
        if (count > 0) {
            return ServerResponse.response(DepartmentEnum.CODE_EXISTED);
        }

        return ServerResponse.response(DepartmentEnum.SUCCESS);
    }

    /**
     * 删除一个部门
     *
     * @param deptId 要被删除的部门 id
     * @return 返回删除情况
     */
    @Override
    public ServerResponse deleteDepartment(String deptId) {

        // 判断参数合法性
        if (CodeUtil.isBlank(deptId)) {
            return ServerResponse.response(DepartmentEnum.PARAMETER_UNCOMPLETED);
        }

        // TODO 删除目前是置于无效状态，但是部门编号肯定是唯一的，那如果一个部门被删除了（但这时数据库还在），
        // 那这个编号还是用不了。。。会一直提示编号存在
        int affect = departmentDao.removeDepartment(deptId);
        if (affect <= 0) {
            return ServerResponse.response(DepartmentEnum.REMOVE_FAILED);
        }

        // 删除成功
        return ServerResponse.response(DepartmentEnum.SUCCESS);
    }

    /**
     * 更新部门信息
     * 这里的业务较为复杂，因为要涉及 3 个数据库操作
     * 1. 首先是部门表更新，具体一条信息的更新
     * 2. 如果修改涉及编号的修改，还要把这张表其他父节点是这个编号的都改了
     * 3. 如果修改涉及编号的修改，还要把用户部门表中的关系约一起改了
     *
     * @param department 要被更新的部门信息
     * @param oldDepartmentCode 原来的部门编号
     * @return 返回部门信息
     */
    @Override
    public ServerResponse updateDepartment(Department department, String oldDepartmentCode) {
        // 检验参数合法性
        if (department == null || department.getDeptId() == null) {
            return ServerResponse.response(DepartmentEnum.PARAMETER_UNCOMPLETED);
        }

        boolean isParameterUncompleted = (department.getDeptCode() == null ||
                department.getDeptName() == null || department.getDeptLevel() == null);
        if (isParameterUncompleted) {
            return ServerResponse.response(DepartmentEnum.PARAMETER_UNCOMPLETED);
        }

        // 参数合法
        

        return null;
    }
}
