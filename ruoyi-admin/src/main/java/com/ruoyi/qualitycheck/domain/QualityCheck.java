package com.ruoyi.qualitycheck.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 质量检查管理对象 quality_check
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
public class QualityCheck extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 检查编号 */
    private Long checkId;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private Long projectId;

    /** 检查项目 */
    @Excel(name = "检查项目")
    private String checkItem;

    /** 检查标准 */
    @Excel(name = "检查标准")
    private String checkStandard;

    /** 照片 */
    @Excel(name = "照片")
    private String photos;

    /** 问题描述 */
    @Excel(name = "问题描述")
    private String questionDescription;

    /** 检查结果 */
    @Excel(name = "检查结果")
    private String checkResult;

    /** 整改措施 */
    @Excel(name = "整改措施")
    private String rectificationMeasures;

    /** 整改效果 */
    @Excel(name = "整改效果")
    private String rectificationEffect;

    /** 检查时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检查时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkTime;

    /** 检察人员编号 */
    @Excel(name = "检察人员编号")
    private Long picNumber;

    public void setCheckId(Long checkId) 
    {
        this.checkId = checkId;
    }

    public Long getCheckId() 
    {
        return checkId;
    }
    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }
    public void setCheckItem(String checkItem) 
    {
        this.checkItem = checkItem;
    }

    public String getCheckItem() 
    {
        return checkItem;
    }
    public void setCheckStandard(String checkStandard) 
    {
        this.checkStandard = checkStandard;
    }

    public String getCheckStandard() 
    {
        return checkStandard;
    }
    public void setPhotos(String photos) 
    {
        this.photos = photos;
    }

    public String getPhotos() 
    {
        return photos;
    }
    public void setQuestionDescription(String questionDescription) 
    {
        this.questionDescription = questionDescription;
    }

    public String getQuestionDescription() 
    {
        return questionDescription;
    }
    public void setCheckResult(String checkResult) 
    {
        this.checkResult = checkResult;
    }

    public String getCheckResult() 
    {
        return checkResult;
    }
    public void setRectificationMeasures(String rectificationMeasures) 
    {
        this.rectificationMeasures = rectificationMeasures;
    }

    public String getRectificationMeasures() 
    {
        return rectificationMeasures;
    }
    public void setRectificationEffect(String rectificationEffect) 
    {
        this.rectificationEffect = rectificationEffect;
    }

    public String getRectificationEffect() 
    {
        return rectificationEffect;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("checkId", getCheckId())
            .append("projectId", getProjectId())
            .append("checkItem", getCheckItem())
            .append("checkStandard", getCheckStandard())
            .append("photos", getPhotos())
            .append("questionDescription", getQuestionDescription())
            .append("checkResult", getCheckResult())
            .append("rectificationMeasures", getRectificationMeasures())
            .append("rectificationEffect", getRectificationEffect())
            .append("checkTime", getCheckTime())
            .append("picNumber", getPicNumber())
            .toString();
    }
}
