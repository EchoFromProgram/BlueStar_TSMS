package entity;
/**
 * 用户-权限类
 * @author 王鸿175
 *
 */

import java.util.List;

public class RolePower {
	
	//角色id
	private Integer roleId;
	
	//权限id集合
	private List<PowerId> powerIds;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public List<PowerId> getPowerIds() {
		return powerIds;
	}

	public void setPowerIds(List<PowerId> powerIds) {
		this.powerIds = powerIds;
	}

	@Override
	public String toString() {
		return "RolePower [roleId=" + roleId + ", powerIds=" + powerIds + "]";
	}

	

	
}
