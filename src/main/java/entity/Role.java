package entity;
/**
 * 角色类，对应role表
 * @author happyChicken
 *
 */
public class Role {
	
	//角色id
	private int roleId;
	
	//角色
	private String role;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", role=" + role + "]";
	}
}
