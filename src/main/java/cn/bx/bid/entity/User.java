package cn.bx.bid.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = -5072909497444198831L;
	private long id, role;
	private String userName, password, name, telephone, unit, landMark;
	private Date enterdate;

	private String roleName;

	public User() {
		// TODO 自动生成的构造函数存根
	}

	public User(long role, String userName, String password, String name, String telephone, String unit,
			String landMark, Date enterdate) {
		super();

		this.role = role;
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.telephone = telephone;
		this.unit = unit;
		this.landMark = landMark;
		this.enterdate = enterdate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRole() {
		return role;
	}

	public void setRole(long role) {
		this.role = role;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public Date getEnterdate() {
		return enterdate;
	}

	public void setEnterdate(Date enterdate) {
		this.enterdate = enterdate;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", role=" + role +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", name='" + name + '\'' +
				", telephone='" + telephone + '\'' +
				", unit='" + unit + '\'' +
				", landMark='" + landMark + '\'' +
				", enterdate=" + enterdate +
				", roleName='" + roleName + '\'' +
				'}';
	}
}
