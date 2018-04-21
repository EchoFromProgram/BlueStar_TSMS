package entity;
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
	
	//权限id
	private int powerId;

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

	public int getPowerId() {
		return powerId;
	}

	public void setPowerId(int powerId) {
		this.powerId = powerId;
	}

	@Override
	public String toString() {
		return "RolePower [rolePowerId=" + rolePowerId + ", roleId=" + roleId + ", powerId=" + powerId + "]";
	}
}
