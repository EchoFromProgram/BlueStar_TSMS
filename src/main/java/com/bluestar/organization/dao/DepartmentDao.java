package com.bluestar.organization.dao;

import com.bluestar.organization.entity.Department;

import java.util.List;

/**
 * 组织部门 dao 接口
 *
 * @author Fish
 * ------> 1149062639@qq.com
 * created by 2018/07/16 14:17:44
 */
public interface DepartmentDao {

    /**
     * 通过 deptCode 获得这个组织的下一级组织部门信息
     *
     * @param deptCode 组织部门编号
     * @return 返回这个部门下一级的信息
     */
    List<Department> listDepartmentByDeptCode(String deptCode);

    List<Department> listDepartmentByDeptLevel();
}
