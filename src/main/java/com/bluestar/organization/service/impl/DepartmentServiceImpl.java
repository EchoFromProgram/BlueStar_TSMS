package com.bluestar.organization.service.impl;

import com.bluestar.common.utils.CodeUtil;
import com.bluestar.organization.common.status.enums.DepartmentEnum;
import com.bluestar.organization.dao.DepartmentDao;
import com.bluestar.organization.dto.ServerResponse;
import com.bluestar.organization.entity.Department;
import com.bluestar.organization.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 部门业务具体实现类
 *
 * @author Fish
 * ------> 1149062639@qq.com
 * created by 2018/07/18 14:31:43
 */
@Service
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
    public ServerResponse addANewDepartment(Department department) {
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
}
