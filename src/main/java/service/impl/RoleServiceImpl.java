package service.impl;

import java.util.List;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AccountDao;
import dao.RoleDao;
import dto.AccountDto;
import entity.Notice;
import entity.Role;
import entity.RolePower;
import enums.impl.Common;
import service.RoleService;
import utils.PageUtil;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public AccountDto getRoles() {
		
        List<Role> roles = roleDao.getRolesPowerName();
        if (roles == null)
        {
            return new AccountDto(Common.GET_IS_NULL);
        }

        return new AccountDto<List<Role>>(roles, Common.SUCCESS);
	}

	@Override
	
	public AccountDto deleteRole(Integer roleId) {
		//判断参数是否为空
		if(roleId == null)
		{
			return new AccountDto(Common.WRONG_ARGEMENT);
		}
		int num = roleDao.deleteRole(roleId);
		//删除失败
		if(num <=0)
		{
			return new AccountDto(Common.ERROR);
		}
		return new AccountDto(Common.SUCCESS);
	}

	@Override
	@Transactional
	public AccountDto insertRole(Role role, RolePower rolePower) {
		if(role == null || rolePower == null)
		{
			return new AccountDto(Common.WRONG_ARGEMENT);
		}
		int num = roleDao.insertRole(role);
		if(num <= 0)
		{
			return new AccountDto(Common.ERROR);
		}
		rolePower.setRoleId(role.getRoleId());
		num = roleDao.insertRolePower(rolePower);
		if(num <= 0)
		{
			return new AccountDto(Common.ERROR);
		}
		return new AccountDto(Common.SUCCESS);
	}

	@Override
	@Transactional
	public AccountDto updateRole(RolePower rolePower) {
		if(rolePower == null)
		{
			return new AccountDto(Common.WRONG_ARGEMENT);
		}
		if(rolePower.getPowerIds() == null || rolePower.getRoleId() == null )
		{
			return new AccountDto(Common.WRONG_ARGEMENT);
		}
		int num = roleDao.deleteRolePowerByRoleId(rolePower.getRoleId());
		if(num <= 0)
		{
			return new AccountDto(Common.ERROR);
		}
		num = roleDao.insertRolePower(rolePower);
		if(num <= 0)
		{
			return new AccountDto(Common.ERROR);
		}
		return new AccountDto(Common.SUCCESS);
	}

}
