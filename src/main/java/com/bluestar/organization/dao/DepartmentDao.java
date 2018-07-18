package com.bluestar.organization.dao;

import com.bluestar.organization.entity.Department;
import org.apache.ibatis.annotations.Param;

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
     * @param all 是否要查询出无效的
     * @return 返回这个部门下一级的信息
     */
    List<Department> listDepartmentByDeptCode(@Param("deptCode") String deptCode, @Param("all") boolean all);

    /**
     * 通过 deptLevel 获得相同等级的组织信息
     *
     * @param deptLevel 组织级别
     * @param all 是否要查询出无效
     * @return 返回和 deptLevel 相同级别的组织信息
     */
    List<Department> listDepartmentByDeptLevel(@Param("deptLevel") String deptLevel, @Param("deptPCode") String deptPCode,
                                               @Param("all") boolean all);

    /**
     * 保存一个新的组织部门信息
     *
     * @param department 要保存的部门信息
     * @return 0 表示失败，大于 0 表示成功，通常这个值就是 1
     */
    int saveDepartment(Department department);

    /**
     * 通过 deptId 找到并删除一个部门信息，这里的“删除”仅仅只是把状态改为“无效”
     *
     * @param DeptId 部门信息主键
     * @return 返回 0 表示操作失败，大于 0 表示成功
     */
    int removeDepartment(String DeptId);
}
