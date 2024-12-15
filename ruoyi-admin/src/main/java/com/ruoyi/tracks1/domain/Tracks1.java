package com.ruoyi.tracks1.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工作轨迹1对象 tracks1
 * 
 * @author ruoyi
 * @date 2024-11-19
 */
public class Tracks1 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 项目wbs */
    @Excel(name = "项目wbs")
    private String wbs;

    /** 项目id */
    @Excel(name = "项目id")
    private Long itemId;

    /** 检查项目 */
    @Excel(name = "检查项目")
    private String checkItem;

    /** 施工单位 */
    @Excel(name = "施工单位")
    private String company;

    /** 检查结果 */
    @Excel(name = "检查结果")
    private String result;

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

    /** 开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String startDate;

    /** 结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String endDate;

    /** 负责人 */
    @Excel(name = "负责人")
    private Long picNumber;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
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
    public void setResult(String result) 
    {
        this.result = result;
    }

    public String getResult() 
    {
        return result;
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
    public void setPicNumber(Long picNumber) 
    {
        this.picNumber = picNumber;
    }

    public Long getPicNumber() 
    {
        return picNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("wbs", getWbs())
            .append("itemId", getItemId())
            .append("checkItem", getCheckItem())
            .append("company", getCompany())
            .append("result", getResult())
            .append("photo", getPhoto())
            .append("questionDescription", getQuestionDescription())
            .append("revise", getRevise())
            .append("checkTime", getCheckTime())
            .append("picNumber", getPicNumber())
            .toString();
    }
}
