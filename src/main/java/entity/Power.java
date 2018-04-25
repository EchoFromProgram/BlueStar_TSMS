package entity;
/**
 * 权限类，对应power表
 * @author happyChicken
 *
 */
public class Power {
	
	// 权限id
	private Integer powerId;
	
	// 权限
	private String power;

	public Integer getPowerId() {
		return powerId;
	}

	public void setPowerId(Integer powerId) {
		this.powerId = powerId;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	@Override
	public String toString() {
		return "Power [powerId=" + powerId + ", power=" + power + "]";
	}
	
}
