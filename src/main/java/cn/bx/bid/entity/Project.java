package cn.bx.bid.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Project implements Serializable {
	private static final long serialVersionUID = 6212359870439080517L;
	private long id;// 项目编号
	private String projectName;// 项目名称
	private String projectCategory;// 项目类型
	private int professorNum;// 专家人数（需要评标人数：自定义）
	private String isEvaluated;// 项目状态：1待抽签 ;2随机抽签中；3补抽中； 4抽签已完成（待登记） 5登记成功（已经登记完专家到场情况，项目结束
	private String prochaseMode;// 采购方式
	private long departmentId;// 采购部门id（外键：引用department表的id）
	private double budget;// 预算
	private Date announceDate;// 采购公告时间
	private Date openDate;// 开标时间
	private String address;// 开标地点 可以找到本地专家
	private Date dealDate;// 成交公告时间
	private String url;// 公告发布网址
	private String dealCompany;// 成交单位 （成交公司）
	private double settlePrice;// 成交价
	private String linkMan;// 项目联系人姓名
	private String mobliePhone;// 项目联系人电话
	private String projectFilePath;// 项目文件路径
	private String createBy;// 创建者
	private Date createDate;// 创建时间
	private String department;// 采购部门
	private String updateBy;// 修改者
	private Date updateDate;// 修改时间
	private Date registerTime;// 报道时间
	private String registerAddress;// 报道地点
	private long committee;// 评标委员会组成人数
	private String professional;// 采购代表
	private long status;// 0未完成1已完成

	/**
	 * 额外添加
	 */
	String departmentName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectCategory() {
		return projectCategory;
	}

	public void setProjectCategory(String projectCategory) {
		this.projectCategory = projectCategory;
	}

	public int getProfessorNum() {
		return professorNum;
	}

	public void setProfessorNum(int professorNum) {
		this.professorNum = professorNum;
	}

	public String getIsEvaluated() {
		return isEvaluated;
	}

	public void setIsEvaluated(String isEvaluated) {
		this.isEvaluated = isEvaluated;
	}

	public String getProchaseMode() {
		return prochaseMode;
	}

	public void setProchaseMode(String prochaseMode) {
		this.prochaseMode = prochaseMode;
	}

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public Date getAnnounceDate() {
		return announceDate;
	}

	public void setAnnounceDate(Date announceDate) {
		this.announceDate = announceDate;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDealDate() {
		return dealDate;
	}

	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDealCompany() {
		return dealCompany;
	}

	public void setDealCompany(String dealCompany) {
		this.dealCompany = dealCompany;
	}

	public double getSettlePrice() {
		return settlePrice;
	}

	public void setSettlePrice(double settlePrice) {
		this.settlePrice = settlePrice;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getMobliePhone() {
		return mobliePhone;
	}

	public void setMobliePhone(String mobliePhone) {
		this.mobliePhone = mobliePhone;
	}

	public String getProjectFilePath() {
		return projectFilePath;
	}

	public void setProjectFilePath(String projectFilePath) {
		this.projectFilePath = projectFilePath;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getRegisterAddress() {
		return registerAddress;
	}

	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}

	public long getCommittee() {
		return committee;
	}

	public void setCommittee(long committee) {
		this.committee = committee;
	}

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public String toString() {
		return "Project{" +
				"id=" + id +
				", projectName='" + projectName + '\'' +
				", projectCategory='" + projectCategory + '\'' +
				", professorNum=" + professorNum +
				", isEvaluated='" + isEvaluated + '\'' +
				", prochaseMode='" + prochaseMode + '\'' +
				", departmentId=" + departmentId +
				", budget=" + budget +
				", announceDate=" + announceDate +
				", openDate=" + openDate +
				", address='" + address + '\'' +
				", dealDate=" + dealDate +
				", url='" + url + '\'' +
				", dealCompany='" + dealCompany + '\'' +
				", settlePrice=" + settlePrice +
				", linkMan='" + linkMan + '\'' +
				", mobliePhone='" + mobliePhone + '\'' +
				", projectFilePath='" + projectFilePath + '\'' +
				", createBy='" + createBy + '\'' +
				", createDate=" + createDate +
				", department='" + department + '\'' +
				", updateBy='" + updateBy + '\'' +
				", updateDate=" + updateDate +
				", registerTime=" + registerTime +
				", registerAddress='" + registerAddress + '\'' +
				", committee=" + committee +
				", professional='" + professional + '\'' +
				", status=" + status +
				", departmentName='" + departmentName + '\'' +
				'}';
	}

	public Project() {
		super();
	}

	public Project(long id, String projectName, String projectCategory, int professorNum, String isEvaluated,
			String prochaseMode, long departmentId, double budget, Date announceDate, Date openDate, String address,
			Date dealDate, String url, String dealCompany, double settlePrice, String linkMan, String mobliePhone,
			String projectFilePath, String createBy, Date createDate, String department, String updateBy,
			Date updateDate, Date registerTime, String registerAddress, long committee, String professional,
			long status) {
		super();
		this.id = id;
		this.projectName = projectName;
		this.projectCategory = projectCategory;
		this.professorNum = professorNum;
		this.isEvaluated = isEvaluated;
		this.prochaseMode = prochaseMode;
		this.departmentId = departmentId;
		this.budget = budget;
		this.announceDate = announceDate;
		this.openDate = openDate;
		this.address = address;
		this.dealDate = dealDate;
		this.url = url;
		this.dealCompany = dealCompany;
		this.settlePrice = settlePrice;
		this.linkMan = linkMan;
		this.mobliePhone = mobliePhone;
		this.projectFilePath = projectFilePath;
		this.createBy = createBy;
		this.createDate = createDate;
		this.department = department;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		this.registerTime = registerTime;
		this.registerAddress = registerAddress;
		this.committee = committee;
		this.professional = professional;
		this.status = status;
	}

}
