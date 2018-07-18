package com.bluestar.organization.dao;

import com.bluestar.common.utils.CodeUtil;
import com.bluestar.organization.common.DepartmentConst;
import com.bluestar.organization.entity.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 组织部门 dao 接口测试类
 *
 * @author Fish
 * ------> 1149062639@qq.com
 * created by 2018/07/16 15:10:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml"
})
public class DepartmentDaoTest {

    @Autowired
    DepartmentDao departmentDao = null;

    @Test
    public void testSaveDepartment() {
        Department department = new Department();
        department.setDeptId(CodeUtil.getId());
        department.setDeptCode("RA9-001-001");
        department.setDeptName("福州开发部维护小组");
        department.setDeptPCode("RA9-001");
        department.setDeptLevel("2");
        department.setDeptOrder(1);
        department.setDeptStatus(DepartmentConst.DEPT_STATUS_NORMAL);
        department.setDeptRemark("福州开发部维护小组....");

        int affect = departmentDao.saveDepartment(department);
        System.out.println(affect);
    }

    @Test
    public void testListDepartmentByDeptCode() {
        String deptCode = "RA9-002-003";
        System.out.println(departmentDao.listDepartmentByDeptCode(deptCode, false));
    }

    @Test
    public void testListDepartmentByDeptLevel() {
        String deptLevel = "1";
        String deptPCode = "RA9-001";
        System.out.println(departmentDao.listDepartmentByDeptLevel(deptLevel, null, false));
    }

    @Test
    public void testRemoveDepartment() {
        String deptId = "d6eae030-24e0-466a-bce0-9e61ac074381";
        System.out.println(departmentDao.removeDepartment(deptId));
    }

    @Test
    public void testUpdateDepartment() {
        Department department = new Department();
        department.setDeptId("d6eae030-24e0-466a-bce0-9e61ac074381");
        department.setDeptCode("RA9-001-001");
        //department.setDeptName("福州开发部维护小组");
        //department.setDeptPCode("RA9-001");
        department.setDeptLevel("2");
        //department.setDeptOrder(1);
        department.setDeptStatus(DepartmentConst.DEPT_STATUS_NORMAL);
        department.setDeptRemark("福州开发部维护小组....");

        int affect = departmentDao.updateDepartment(department);
        System.out.println(affect);
    }

    @Test
    public void testUpdateDepartmentPCode() {
        String oldDeptPCode = "xxxxxx";
        String newDeptPCode = "RA9-002";

        int affect = departmentDao.updateDepartmentPCode(oldDeptPCode, newDeptPCode);
        System.out.println(affect);
    }

    @Test
    public void testCountDepartmentCode() {
        String deptCode = "RA9-001";
        System.out.println(departmentDao.countDepartmentCode(deptCode));
    }
}
