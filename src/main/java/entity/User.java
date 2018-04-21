package entity;
/**
 * 用户类，对应user表
 * @author happyChicken
 *
 */
public class User {

	//id
	private int userId;
	
	//用户名
	private String userName;
	
	//密码
	private String password;
	
	//姓名
	private String name;
	
	//角色id
	private int roleId;
	
	//信息id
	private int infoId;
	
	//用户类型
	private int typeId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getInfoId() {
		return infoId;
	}

	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", name=" + name
				+ ", roleId=" + roleId + ", infoId=" + infoId + ", typeId=" + typeId + "]";
	}
	
}
