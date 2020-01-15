package cn.bx.bid.entity;


import java.io.Serializable;
import java.util.*;

public class Professor implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String professorName;

    private String gender;

    private Date birthday;

    private String identityNumber;

    private String workStatus;

    private String institution;

    private String education;

    private String major;

    private String employCompany;

    private String administrative;

    private String technicalPost;

    private String phone;

    private String mobliePhone;

    private String address;

    private String projectCategory;

    private String createBy;

    private Date createDate;

    private String modifyBy;

    private Date modifyDate;

    private Byte workDate;

    private Integer lostNum;

    private List<WorkHistory> workhistory=new ArrayList<>(0);
    public Professor() {

    }

    public Professor(Long id, String professorName, String gender, Date birthday, String identityNumber, String workStatus, String institution,
                     String education, String major, String employCompany, String administrative, String technicalPost, String phone,
                     String mobliePhone, String address, String projectCategory, String createBy, Date createDate, String modifyBy, Date modifyDate,
                     Byte workDate, Integer lostNum) {
        super();
        this.id = id;
        this.professorName = professorName;
        this.gender = gender;
        this.birthday = birthday;
        this.identityNumber = identityNumber;
        this.workStatus = workStatus;
        this.institution = institution;
        this.education = education;
        this.major = major;
        this.employCompany = employCompany;
        this.administrative = administrative;
        this.technicalPost = technicalPost;
        this.phone = phone;
        this.mobliePhone = mobliePhone;
        this.address = address;
        this.projectCategory = projectCategory;
        this.createBy = createBy;
        this.createDate = createDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.workDate = workDate;
        this.lostNum = lostNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName==null ? null : professorName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender==null ? null : gender.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber==null ? null : identityNumber.trim();
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus==null ? null : workStatus.trim();
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution==null ? null : institution.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education==null ? null : education.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major==null ? null : major.trim();
    }

    public String getEmployCompany() {
        return employCompany;
    }

    public void setEmployCompany(String employCompany) {
        this.employCompany = employCompany==null ? null : employCompany.trim();
    }

    public String getAdministrative() {
        return administrative;
    }

    public void setAdministrative(String administrative) {
        this.administrative = administrative==null ? null : administrative.trim();
    }

    public String getTechnicalPost() {
        return technicalPost;
    }

    public void setTechnicalPost(String technicalPost) {
        this.technicalPost = technicalPost==null ? null : technicalPost.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone==null ? null : phone.trim();
    }

    public String getMobliePhone() {
        return mobliePhone;
    }

    public void setMobliePhone(String mobliePhone) {
        this.mobliePhone = mobliePhone==null ? null : mobliePhone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address==null ? null : address.trim();
    }

    public String getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(String projectCategory) {
        this.projectCategory = projectCategory==null ? null : projectCategory.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy==null ? null : createBy.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy==null ? null : modifyBy.trim();
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Byte getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Byte workDate) {
        this.workDate = workDate;
    }

    public Integer getLostNum() {
        return lostNum;
    }

    public void setLostNum(Integer lostNum) {
        this.lostNum = lostNum;
    }

    public List<WorkHistory> getWorkhistory() {
        return workhistory;
    }

    public void setWorkhistory(List<WorkHistory> workhistory) {
        this.workhistory = workhistory;
    }

    @Override
    public String toString() {
        return "Professor [id="+id+", professorName="+professorName+", gender="+gender+", birthday="+birthday+", identityNumber="+identityNumber
               +", workStatus="+workStatus+", institution="+institution+", education="+education+", major="+major+", employCompany="+employCompany
               +", administrative="+administrative+", technicalPost="+technicalPost+", phone="+phone+", mobliePhone="+mobliePhone+", address="+address
               +", projectCategory="+projectCategory+", createBy="+createBy+", createDate="+createDate+", modifyBy="+modifyBy+", modifyDate="
               +modifyDate+", workDate="+workDate+", lostNum="+lostNum+"]";
    }
}