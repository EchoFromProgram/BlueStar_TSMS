package com.bluestar.organization.dao;

import com.bluestar.organization.common.DepartmentConst;
import com.bluestar.organization.entity.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

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
        department.setDeptId(UUID.randomUUID().toString());
        department.setDeptCode("RA9-001");
        department.setDeptName("福州开发部");
        department.setDeptLevel("1");
        department.setDeptOrder(1);
        department.setDeptStatus(DepartmentConst.DEPT_STATUS_NORMAL);
        department.setDeptRemark("福州开发总部....");

        int affect = departmentDao.saveDepartment(department);
        System.out.println(affect);
    }
}
