package com.ruoyi.CheckViews1.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * CheckViews1对象 check_views1
 * 
 * @author taotao
 * @date 2024-12-09
 */
public class CheckViews1 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目wbs */
    @Excel(name = "项目wbs")
    private String wbs;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String views;

    public void setWbs(String wbs) 
    {
        this.wbs = wbs;
    }

    public String getWbs() 
    {
        return wbs;
    }
    public void setViews(String views) 
    {
        this.views = views;
    }

    public String getViews() 
    {
        return views;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("wbs", getWbs())
            .append("views", getViews())
            .toString();
    }
}
