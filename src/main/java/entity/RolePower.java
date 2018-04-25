package entity;

import java.util.List;

/**
 * 角色权限表,对应role_power表
 * @author happyChicken
 *
 */
public class RolePower {
	
	//角色权限id
	private Integer rolePowerId;
	
	//角色id
	private Integer roleId;
	
	//一组权限id
	private List<Integer> powerId;

	public Integer getRolePowerId() {
		return rolePowerId;
	}

	public void setRolePowerId(Integer rolePowerId) {
		this.rolePowerId = rolePowerId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
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
