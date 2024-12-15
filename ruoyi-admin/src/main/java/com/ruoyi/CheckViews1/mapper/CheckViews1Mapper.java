package com.ruoyi.CheckViews1.mapper;

import java.util.List;
import com.ruoyi.CheckViews1.domain.CheckViews1;

/**
 * CheckViews1Mapper接口
 * 
 * @author taotao
 * @date 2024-12-09
 */
public interface CheckViews1Mapper 
{
    /**
     * 查询CheckViews1
     * 
     * @param wbs CheckViews1主键
     * @return CheckViews1
     */
    public CheckViews1 selectCheckViews1ByWbs(String wbs);

    /**
     * 查询CheckViews1列表
     * 
     * @param checkViews1 CheckViews1
     * @return CheckViews1集合
     */
    public List<CheckViews1> selectCheckViews1List(CheckViews1 checkViews1);

    /**
     * 新增CheckViews1
     * 
     * @param checkViews1 CheckViews1
     * @return 结果
     */
    public int insertCheckViews1(CheckViews1 checkViews1);

    /**
     * 修改CheckViews1
     * 
     * @param checkViews1 CheckViews1
     * @return 结果
     */
    public int updateCheckViews1(CheckViews1 checkViews1);

    /**
     * 删除CheckViews1
     * 
     * @param wbs CheckViews1主键
     * @return 结果
     */
    public int deleteCheckViews1ByWbs(String wbs);

    /**
     * 批量删除CheckViews1
     * 
     * @param wbss 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCheckViews1ByWbss(String[] wbss);
}
