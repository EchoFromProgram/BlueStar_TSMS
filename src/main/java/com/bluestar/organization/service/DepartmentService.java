package com.bluestar.organization.service;

import com.bluestar.organization.dto.ServerResponse;
import com.bluestar.organization.entity.Department;
import com.bluestar.organization.entity.UserDepartment;

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
    public ServerResponse addDepartment(Department department);

    /**
     * 检查部门编号是否可用
     *
     * @param deptCode 部门编号
     * @return 返回状态信息
     */
    public ServerResponse checkDepartmentCode(String deptCode);

    /**
     * 删除一个部门
     *
     * @param deptId 要被删除的部门 id
     * @return 返回删除情况
     */
    public ServerResponse deleteDepartment(String deptId);

    /**
     * 更新部门信息
     * 这里的业务较为复杂，因为要涉及 3 个数据库操作
     * 1. 首先是部门表更新，具体一条信息的更新
     * 2. 如果修改涉及编号的修改，还要把这张表其他父节点是这个编号的都改了
     * 3. 如果修改涉及编号的修改，还要把用户部门表中的关系约一起改了
     *
     * @param department        要被更新的部门信息
     * @param oldDepartmentCode 原来的部门编号
     * @return 返回部门信息
     */
    public ServerResponse updateDepartment(Department department, String oldDepartmentCode);

    /**
     * 通过部门编号得到这个部门旗下的所有子部门
     *
     * @param deptCode 指定的部门编号
     * @param isGetAllDepartments 是否查询出所有部门信息
     * @return 返回得到的所有子部门
     */
    public ServerResponse getChildrenDepartments(String deptCode, boolean isGetAllDepartments);

    /**
     * 通过父级编号找到指定级别的部门，其实只能找到子级
     * 所以这个方法的参数设定其实是有问题的，但以后说不定会有新功能用到
     *
     * @param deptLevel 级别
     * @param deptPCode 父级部门
     * @param isGetAllDepartments 是否查询出所有部门信息
     * @return 返回得到的部门
     */
    public ServerResponse getDepartmentsByLevelAndDeptPCode(String deptLevel, String deptPCode,
                                                            boolean isGetAllDepartments);

    /**
     * 将一个用户放进部门中
     *
     * @param userDepartment 保存着用户 id 和部门编号
     * @return 返回保存结果
     */
    public ServerResponse putUserInDepartment(UserDepartment userDepartment);

    /**
     * 通过部门编号获取这个部门所拥有的用户
     *
     * @param deptCode 部门编号
     * @return 返回这个部门的所有用户
     */
    public ServerResponse getUsersInDepartment(String deptCode);
}
