package com.ruoyi.AcceptanceReport.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 竣工验收上报对象 acceptance_report
 * 
 * @author taotao
 * @date 2024-11-14
 */
public class AcceptanceReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 验收ID */
    private Long acceptanceId;

    /** 项目wbs */
    @Excel(name = "项目wbs")
    private String wbs;

    /** 项目id */
    @Excel(name = "项目id")
    private Long itemId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String itemName;

    /** 检查项目 */
    @Excel(name = "检查项目")
    private String checkItem;

    /** 施工单位 */
    @Excel(name = "施工单位")
    private String company;

    /** 检查结果 */
    @Excel(name = "检查结果")
    private String checkResult;

    /** 现场照片 */
    @Excel(name = "现场照片")
    private String photo;

    /** 问题描述 */
    @Excel(name = "问题描述")
    private String questionDescription;

    /** 整改措施 */
    @Excel(name = "整改措施")
    private String revise;

    /** 检查时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检查时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkTime;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;

    /** 负责人 */
    @Excel(name = "负责人")
    private Long picNumber;

    public void setAcceptanceId(Long acceptanceId) 
    {
        this.acceptanceId = acceptanceId;
    }

    public Long getAcceptanceId() 
    {
        return acceptanceId;
    }
    public void setWbs(String wbs) 
    {
        this.wbs = wbs;
    }

    public String getWbs() 
    {
        return wbs;
    }
    public void setItemId(Long itemId) 
    {
        this.itemId = itemId;
    }

    public Long getItemId() 
    {
        return itemId;
    }
    public void setItemName(String itemName) 
    {
        this.itemName = itemName;
    }

    public String getItemName() 
    {
        return itemName;
    }
    public void setCheckItem(String checkItem) 
    {
        this.checkItem = checkItem;
    }

    public String getCheckItem() 
    {
        return checkItem;
    }
    public void setCompany(String company) 
    {
        this.company = company;
    }

    public String getCompany() 
    {
        return company;
    }
    public void setCheckResult(String checkResult) 
    {
        this.checkResult = checkResult;
    }

    public String getCheckResult() 
    {
        return checkResult;
    }
    public void setPhoto(String photo) 
    {
        this.photo = photo;
    }

    public String getPhoto() 
    {
        return photo;
    }
    public void setQuestionDescription(String questionDescription) 
    {
        this.questionDescription = questionDescription;
    }

    public String getQuestionDescription() 
    {
        return questionDescription;
    }
    public void setRevise(String revise) 
    {
        this.revise = revise;
    }

    public String getRevise() 
    {
        return revise;
    }
    public void setCheckTime(Date checkTime) 
    {
        this.checkTime = checkTime;
    }

    public Date getCheckTime() 
    {
        return checkTime;
    }
    public void setUploadTime(Date uploadTime) 
    {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() 
    {
        return uploadTime;
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
            .append("acceptanceId", getAcceptanceId())
            .append("wbs", getWbs())
            .append("itemId", getItemId())
            .append("itemName", getItemName())
            .append("checkItem", getCheckItem())
            .append("company", getCompany())
            .append("checkResult", getCheckResult())
            .append("photo", getPhoto())
            .append("questionDescription", getQuestionDescription())
            .append("revise", getRevise())
            .append("checkTime", getCheckTime())
            .append("uploadTime", getUploadTime())
            .append("picNumber", getPicNumber())
            .toString();
    }
}
