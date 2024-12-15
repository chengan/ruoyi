package com.ruoyi.task.domain;

import java.util.Date;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 任务管理对象 task
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
public class Task extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务编号 */
    private Long taskId;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private Long projectId;

    /** 组编号 */
    @Excel(name = "组编号")
    private Long groupId;

    /** 版本编号 */
    @Excel(name = "版本编号")
    private Long vId;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskName;

    /** 负责人ID */
    @Excel(name = "负责人ID")
    private Long managerId;

    /** 公司 */
    @Excel(name = "公司")
    private String company;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateDate;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 文件 */
    @Excel(name = "文件")
    private String file;

    /** 持续时间 */
    @Excel(name = "持续时间")
    private Integer duration;

    /** 计划进度 */
    @Excel(name = "计划进度")
    private BigDecimal planProgress;

    /** 项目状态 */
    @Excel(name = "项目状态")
    private String proStatus;

    /** 进度 */
    @Excel(name = "进度")
    private BigDecimal progress;

    /** 前置任务ID */
    @Excel(name = "前置任务ID")
    private String preId;

    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }
    public void setGroupId(Long groupId) 
    {
        this.groupId = groupId;
    }

    public Long getGroupId() 
    {
        return groupId;
    }

    public void setVId(Long vId) 
    {
        this.vId = vId;
    }

    public Long getVId() 
    {
        return vId;
    }

    public void setTaskName(String taskName) 
    {
        this.taskName = taskName;
    }

    public String getTaskName() 
    {
        return taskName;
    }

    public void setManagerId(Long managerId) 
    {
        this.managerId = managerId;
    }

    public Long getManagerId() 
    {
        return managerId;
    }

    public void setCompany(String company) 
    {
        this.company = company;
    }

    public String getCompany() 
    {
        return company;
    }

    public void setStartDate(Date startDate) 
    {
        this.startDate = startDate;
    }

    public Date getStartDate() 
    {
        return startDate;
    }

    public void setUpdateDate(Date updateDate) 
    {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate() 
    {
        return updateDate;
    }

    public void setEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    public Date getEndDate() 
    {
        return endDate;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setFile(String file) 
    {
        this.file = file;
    }

    public String getFile() 
    {
        return file;
    }

    public void setDuration(Integer duration) 
    {
        this.duration = duration;
    }

    public Integer getDuration() 
    {
        return duration;
    }

    public void setPlanProgress(BigDecimal planProgress) 
    {
        this.planProgress = planProgress;
    }

    public BigDecimal getPlanProgress() 
    {
        return planProgress;
    }

    public void setProStatus(String proStatus) 
    {
        this.proStatus = proStatus;
    }

    public String getProStatus() 
    {
        return proStatus;
    }

    public void setProgress(BigDecimal progress) 
    {
        this.progress = progress;
    }

    public BigDecimal getProgress() 
    {
        return progress;
    }

    public void setPreId(String preId) 
    {
        this.preId = preId;
    }

    public String getPreId() 
    {
        return preId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("taskId", getTaskId())
            .append("projectId", getProjectId())
            .append("groupId", getGroupId())
            .append("vId", getVId())
            .append("taskName", getTaskName())
            .append("managerId", getManagerId())
            .append("company", getCompany())
            .append("startDate", getStartDate())
            .append("updateDate", getUpdateDate())
            .append("endDate", getEndDate())
            .append("status", getStatus())
            .append("file", getFile())
            .append("duration", getDuration())
            .append("planProgress", getPlanProgress())
            .append("proStatus", getProStatus())
            .append("progress", getProgress())
            .append("preId", getPreId())
            .toString();
    }
}
