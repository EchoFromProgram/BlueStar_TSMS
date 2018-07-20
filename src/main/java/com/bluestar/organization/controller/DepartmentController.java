package com.bluestar.organization.controller;

import com.bluestar.organization.dto.ServerResponse;
import com.bluestar.organization.entity.Department;
import com.bluestar.organization.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 部门类控制器
 *
 * @author Fish
 * ------> 1149062639@qq.com
 * created by 2018/07/18 15:23:27
 */
@Controller("dept")
public class DepartmentController {

    private DepartmentService departmentService = null;

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * 验证用户名是否可用
     *
     * @param deptCode 要被验证的部门编号
     * @return 返回验证情况
     */
    @RequestMapping(path = "checkDeptCode.do")
    @ResponseBody
    public ServerResponse checkDeptCode(String deptCode) {
        return departmentService.checkDepartmentCode(deptCode);
    }

    /**
     * 新增一个部门
     *
     * @param department 要新增的部门
     * @return 返回新增的情况
     */
    @RequestMapping(path = "addDepartment.do")
    @ResponseBody
    public ServerResponse addANewDepartment(Department department) {
        return departmentService.addDepartment(department);
    }

    /**
     * 删除指定的部门
     *
     * @param deptId 指定部门的 id
     * @return 返回删除的情况
     */
    @RequestMapping(path = "deleteDepartment.do")
    @ResponseBody
    public ServerResponse deleteDepartment(String deptId) {
        return departmentService.deleteDepartment(deptId);
    }

    /**
     * 更新指定的部门信息
     *
     * @param department 指定的部门信息，这里的 id 必须得有
     * @param oldDeptCode 旧的 id 部门编号
     * @return 返回更新情况
     */
    @RequestMapping(path = "updateDepartment.do")
    @ResponseBody
    public ServerResponse updateDepartment(Department department, String oldDeptCode) {
        return departmentService.updateDepartment(department, oldDeptCode);
    }

    /**
     * 通过部门编号得到这个部门旗下的所有子部门
     *
     * @param deptCode 指定的部门编号
     * @param isGetAllDepartments 是否要显示所有部门信息，包括无效，这个参数不是必须的
     * @return 返回查询的结果
     */
    @RequestMapping(path = "getChildrenDepartments.do")
    @ResponseBody
    public ServerResponse getChildrenDepartments(String deptCode, @RequestParam(required = false) Boolean isGetAllDepartments) {
        return departmentService.getChildrenDepartments(deptCode, isGetAllDepartments);
    }
}
