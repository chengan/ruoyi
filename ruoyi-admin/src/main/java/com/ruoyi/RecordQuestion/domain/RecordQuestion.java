package com.ruoyi.RecordQuestion.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 整改记录对象 record_question
 * 
 * @author zwt
 * @date 2024-11-13
 */
public class RecordQuestion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    private Long taskId;

    /** 检查时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检查时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time;

    /** 问题描述 */
    @Excel(name = "问题描述")
    private String questionDescription;

    /** 照片存放 */
    @Excel(name = "照片存放")
    private String photos;

    /** 整改措施 */
    @Excel(name = "整改措施")
    private String rectificationMeasures;

    /** 检查时间 */
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
    public void setTime(Date time) 
    {
        this.time = time;
    }

    public Date getTime() 
    {
        return time;
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
            .append("time", getTime())
            .append("questionDescription", getQuestionDescription())
            .append("photos", getPhotos())
            .append("rectificationMeasures", getRectificationMeasures())
            .append("checkTime", getCheckTime())
            .append("chairman", getChairman())
            .toString();
    }
}
