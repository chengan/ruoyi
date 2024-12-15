package com.ruoyi.ReviseRecord.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 整改结果上报对象 revise_record
 * 
 * @author taotao
 * @date 2024-11-14
 */
public class ReviseRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 整改ID */
    private Long reviseId;

    /** 项目wbs */
    @Excel(name = "项目wbs")
    private String wbs;

    /** 检查项目 */
    @Excel(name = "检查项目")
    private String checkItem;

    /** 问题描述 */
    @Excel(name = "问题描述")
    private String questionDescription;

    /** 整改后照片 */
    @Excel(name = "整改后照片")
    private String photos;

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

    public void setReviseId(Long reviseId) 
    {
        this.reviseId = reviseId;
    }

    public Long getReviseId() 
    {
        return reviseId;
    }
    public void setWbs(String wbs) 
    {
        this.wbs = wbs;
    }

    public String getWbs() 
    {
        return wbs;
    }
    public void setCheckItem(String checkItem) 
    {
        this.checkItem = checkItem;
    }

    public String getCheckItem() 
    {
        return checkItem;
    }
    public void setQuestionDescription(String questionDescription) 
    {
        this.questionDescription = questionDescription;
    }

    public String getQuestionDescription() 
    {
        return questionDescription;
    }
    public void setPhotos(String photos) 
    {
        this.photos = photos;
    }

    public String getPhotos() 
    {
        return photos;
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
            .append("reviseId", getReviseId())
            .append("wbs", getWbs())
            .append("checkItem", getCheckItem())
            .append("questionDescription", getQuestionDescription())
            .append("photos", getPhotos())
            .append("checkTime", getCheckTime())
            .append("uploadTime", getUploadTime())
            .append("picNumber", getPicNumber())
            .toString();
    }
}
