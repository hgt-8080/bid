package cn.bx.bid.entity;

import java.io.Serializable;

public class Userrole implements Serializable {
	private static final long serialVersionUID = -7224062139907597743L;
	private long id;
	private String name, remark;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Userrole{" +
				"id=" + id +
				", name='" + name + '\'' +
				", remark='" + remark + '\'' +
				'}';
	}
}
