package com.ruoyi.CheckViews.mapper;

import java.util.List;
import com.ruoyi.CheckViews.domain.CheckViews;

/**
 * CheckViewsMapper接口
 * 
 * @author taotao
 * @date 2024-12-03
 */
public interface CheckViewsMapper 
{
    /**
     * 查询CheckViews
     * 
     * @param wbs CheckViews主键
     * @return CheckViews
     */
    public CheckViews selectCheckViewsByWbs(String wbs);

    /**
     * 查询CheckViews列表
     * 
     * @param checkViews CheckViews
     * @return CheckViews集合
     */
    public List<CheckViews> selectCheckViewsList(CheckViews checkViews);

    /**
     * 新增CheckViews
     * 
     * @param checkViews CheckViews
     * @return 结果
     */
    public int insertCheckViews(CheckViews checkViews);

    /**
     * 修改CheckViews
     * 
     * @param checkViews CheckViews
     * @return 结果
     */
    public int updateCheckViews(CheckViews checkViews);

    /**
     * 删除CheckViews
     * 
     * @param wbs CheckViews主键
     * @return 结果
     */
    public int deleteCheckViewsByWbs(String wbs);

    /**
     * 批量删除CheckViews
     * 
     * @param wbss 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCheckViewsByWbss(String[] wbss);
}
