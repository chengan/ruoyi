package com.ruoyi.acceptance.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 竣工验收管理对象 completion_acceptance
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
public class CompletionAcceptance extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 验收编号 */
    private Long reportId;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private Long projectId;

    /** 验收项目 */
    @Excel(name = "验收项目")
    private String acceptanceItem;

    /** 验收结果 */
    @Excel(name = "验收结果")
    private String acceptanceResult;

    /** 验收时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "验收时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date acceptanceTime;

    /** 验收人员编号 */
    @Excel(name = "验收人员编号")
    private Long picNumber;

    public void setReportId(Long reportId) 
    {
        this.reportId = reportId;
    }

    public Long getReportId() 
    {
        return reportId;
    }
    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }
    public void setAcceptanceItem(String acceptanceItem) 
    {
        this.acceptanceItem = acceptanceItem;
    }

    public String getAcceptanceItem() 
    {
        return acceptanceItem;
    }
    public void setAcceptanceResult(String acceptanceResult) 
    {
        this.acceptanceResult = acceptanceResult;
    }

    public String getAcceptanceResult() 
    {
        return acceptanceResult;
    }
    public void setAcceptanceTime(Date acceptanceTime) 
    {
        this.acceptanceTime = acceptanceTime;
    }

    public Date getAcceptanceTime() 
    {
        return acceptanceTime;
    }
    public void setPicNumber(Long picNumber) 
    {
        this.picNumber = picNumber;
    }

    public Long getPicNumber() 
    {
        return picNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("reportId", getReportId())
            .append("projectId", getProjectId())
            .append("acceptanceItem", getAcceptanceItem())
            .append("acceptanceResult", getAcceptanceResult())
            .append("acceptanceTime", getAcceptanceTime())
            .append("picNumber", getPicNumber())
            .toString();
    }
}
