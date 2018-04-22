package entity;

import java.util.List;

/**
 * 角色权限表,对应role_power表
 * @author happyChicken
 *
 */
public class RolePower {
	
	//角色权限id
	private int rolePowerId;
	
	//角色id
	private int roleId;
	
	//一组权限id
	private List<Integer> powerId;

	public int getRolePowerId() {
		return rolePowerId;
	}

	public void setRolePowerId(int rolePowerId) {
		this.rolePowerId = rolePowerId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public List<Integer> getPowerId() {
		return powerId;
	}

	public void setPowerId(List<Integer> powerId) {
		this.powerId = powerId;
	}

	@Override
	public String toString() {
		return "RolePower [rolePowerId=" + rolePowerId + ", roleId=" + roleId + ", powerId=" + powerId + "]";
	}
	
	
	

	
}
