package com.bluestar.organization.service;

import com.bluestar.organization.dto.ServerResponse;
import com.bluestar.organization.entity.Department;

/**
 * 部门业务接口类
 *
 * @author Fish
 * ------> 1149062639@qq.com
 * created by 2018/07/18 14:31:13
 */
public interface DepartmentService {

    /**
     * 新增一个部门
     *
     * @param department 要被添加的部门
     * @return 返回添加状态
     */
    public ServerResponse addANewDepartment(Department department);

    /**
     * 检查部门编号是否可用
     *
     * @param deptCode 部门编号
     * @return 返回状态信息
     */
    public ServerResponse checkDepartmentCode(String deptCode);
}
