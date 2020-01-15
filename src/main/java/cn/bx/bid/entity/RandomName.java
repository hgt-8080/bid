package cn.bx.bid.entity;

import java.io.Serializable;
import java.util.Date;

public class RandomName implements Serializable {
    private static final long serialVersionUID = 2146723684415993680L;
    private Long id,professorid,projectid;
    private String reason,msg,status,isNotice,bePresent;
    private Date createdate,replyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProfessorid() {
        return professorid;
    }

    public void setProfessorid(Long professorid) {
        this.professorid = professorid;
    }

    public Long getProjectid() {
        return projectid;
    }

    public void setProjectid(Long projectid) {
        this.projectid = projectid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsNotice() {
        return isNotice;
    }

    public void setIsNotice(String isNotice) {
        this.isNotice = isNotice;
    }

    public String getBePresent() {
        return bePresent;
    }

    public void setBePresent(String bepresent) {
        this.bePresent = bepresent;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replytime) {
        this.replyTime = replytime;
    }

    @Override
    public String toString() {
        return "RandomName{" +
                "id=" + id +
                ", professorid=" + professorid +
                ", projectid=" + projectid +
                ", reason='" + reason + '\'' +
                ", msg='" + msg + '\'' +
                ", status='" + status + '\'' +
                ", isNotice='" + isNotice + '\'' +
                ", bepresent='" + bePresent + '\'' +
                ", createdate=" + createdate +
                ", replytime=" + replyTime +
                '}';
    }
}
