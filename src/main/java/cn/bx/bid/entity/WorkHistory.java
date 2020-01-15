package cn.bx.bid.entity;

import java.io.Serializable;

public class WorkHistory implements Serializable {
    private static final long serialVersionUID = -5119405872909727731L;
    private long id,professorid;
    private String job,startdate,enddate;
    private String jobduty,employcompany;
    public WorkHistory(){

    }

    public WorkHistory(long professorid, String job, String startdate, String enddate, String jobduty, String employcompany) {
        this.professorid = professorid;
        this.job = job;
        this.startdate = startdate;
        this.enddate = enddate;
        this.jobduty = jobduty;
        this.employcompany = employcompany;
    }

    public WorkHistory(long id, long professorid, String job, String startdate, String enddate, String jobduty, String employcompany) {
        this.id = id;
        this.professorid = professorid;
        this.job = job;
        this.startdate = startdate;
        this.enddate = enddate;
        this.jobduty = jobduty;
        this.employcompany = employcompany;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProfessorid() {
        return professorid;
    }

    public void setProfessorid(long professorid) {
        this.professorid = professorid;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getJobduty() {
        return jobduty;
    }

    public void setJobduty(String jobduty) {
        this.jobduty = jobduty;
    }

    public String getEmploycompany() {
        return employcompany;
    }

    public void setEmploycompany(String employcompany) {
        this.employcompany = employcompany;
    }

    @Override
    public String toString() {
        return "WorkHistory{" +
                "id=" + id +
                ", professorid=" + professorid +
                ", job='" + job + '\'' +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", jobduty='" + jobduty + '\'' +
                ", employcompany='" + employcompany + '\'' +
                '}';
    }
}
