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

    /**
     * 修改指定的部门信息
     * 这里主要是通过这个对象的 deptId 来找到旧的对象
     * 部门编号可以改，但是编号一旦改了，所有相同子类的父级编号都得改
     * 这要体现在 Service 层
     *
     * @param department 包含修改信息的部门对象
     * @return 返回 0 表示失败，大于 0 表示成功
     */
    int updateDepartment(Department department);

    /**
     * 根据旧的部门编号修改成新的部门编号
     * 主要是配合上面的一个修改操作
     * 如果这两个编号相同，也就是没有改动编号，这个操作应该要避免
     *
     * @param oldDeptPCode 旧的部门编号
     * @param newDeptPCode 新的部门编号
     * @return 返回 0 表示失败，大于 0 表示成功
     */
    int updateDepartmentPCode(@Param("oldDeptPCode") String oldDeptPCode, @Param("newDeptPCode") String newDeptPCode);
}
