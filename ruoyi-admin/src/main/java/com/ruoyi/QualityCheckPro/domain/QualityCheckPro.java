package com.ruoyi.QualityCheckPro.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 质量检查上报对象 quality_check_pro
 * 
 * @author taotao
 * @date 2024-11-13
 */
public class QualityCheckPro extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    private Long taskId;

    /** 任务wbs */
    @Excel(name = "任务wbs")
    private String itemWbs;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String taskName;

    /** 检查项目 */
    @Excel(name = "检查项目")
    private String itemName;

    /** 施工单位 */
    @Excel(name = "施工单位")
    private String companyName;

    /** 检查结果 */
    @Excel(name = "检查结果")
    private String checkResult;

    /** 照片存放 */
    @Excel(name = "照片存放")
    private String photos;

    /** 问题描述 */
    @Excel(name = "问题描述")
    private String questionDescription;

    /** 整改措施 */
    @Excel(name = "整改措施")
    private String rectificationMeasures;

    /** 检查时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检查时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkTime;

    /** 负责人 */
    @Excel(name = "负责人")
    private Long chairman;

    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setItemWbs(String itemWbs) 
    {
        this.itemWbs = itemWbs;
    }

    public String getItemWbs() 
    {
        return itemWbs;
    }
    public void setTaskName(String taskName) 
    {
        this.taskName = taskName;
    }

    public String getTaskName() 
    {
        return taskName;
    }
    public void setItemName(String itemName) 
    {
        this.itemName = itemName;
    }

    public String getItemName() 
    {
        return itemName;
    }
    public void setCompanyName(String companyName) 
    {
        this.companyName = companyName;
    }

    public String getCompanyName() 
    {
        return companyName;
    }
    public void setCheckResult(String checkResult) 
    {
        this.checkResult = checkResult;
    }

    public String getCheckResult() 
    {
        return checkResult;
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
    public void setRectificationMeasures(String rectificationMeasures) 
    {
        this.rectificationMeasures = rectificationMeasures;
    }

    public String getRectificationMeasures() 
    {
        return rectificationMeasures;
    }
    public void setCheckTime(Date checkTime) 
    {
        this.checkTime = checkTime;
    }

    public Date getCheckTime() 
    {
        return checkTime;
    }
    public void setChairman(Long chairman) 
    {
        this.chairman = chairman;
    }

    public Long getChairman() 
    {
        return chairman;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("taskId", getTaskId())
            .append("itemWbs", getItemWbs())
            .append("taskName", getTaskName())
            .append("itemName", getItemName())
            .append("companyName", getCompanyName())
            .append("parentId", getParentId())
            .append("checkResult", getCheckResult())
            .append("photos", getPhotos())
            .append("questionDescription", getQuestionDescription())
            .append("rectificationMeasures", getRectificationMeasures())
            .append("checkTime", getCheckTime())
            .append("chairman", getChairman())
            .toString();
    }
}
