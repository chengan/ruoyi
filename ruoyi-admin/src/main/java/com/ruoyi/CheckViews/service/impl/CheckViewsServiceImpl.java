package com.ruoyi.CheckViews.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.CheckViews.mapper.CheckViewsMapper;
import com.ruoyi.CheckViews.domain.CheckViews;
import com.ruoyi.CheckViews.service.ICheckViewsService;

/**
 * CheckViewsService业务层处理
 * 
 * @author taotao
 * @date 2024-12-03
 */
@Service
public class CheckViewsServiceImpl implements ICheckViewsService 
{
    @Autowired
    private CheckViewsMapper checkViewsMapper;

    /**
     * 查询CheckViews
     * 
     * @param wbs CheckViews主键
     * @return CheckViews
     */
    @Override
    public CheckViews selectCheckViewsByWbs(String wbs)
    {
        return checkViewsMapper.selectCheckViewsByWbs(wbs);
    }

    /**
     * 查询CheckViews列表
     * 
     * @param checkViews CheckViews
     * @return CheckViews
     */
    @Override
    public List<CheckViews> selectCheckViewsList(CheckViews checkViews)
    {
        return checkViewsMapper.selectCheckViewsList(checkViews);
    }

    /**
     * 新增CheckViews
     * 
     * @param checkViews CheckViews
     * @return 结果
     */
    @Override
    public int insertCheckViews(CheckViews checkViews)
    {
        return checkViewsMapper.insertCheckViews(checkViews);
    }

    /**
     * 修改CheckViews
     * 
     * @param checkViews CheckViews
     * @return 结果
     */
    @Override
    public int updateCheckViews(CheckViews checkViews)
    {
        return checkViewsMapper.updateCheckViews(checkViews);
    }

    /**
     * 批量删除CheckViews
     * 
     * @param wbss 需要删除的CheckViews主键
     * @return 结果
     */
    @Override
    public int deleteCheckViewsByWbss(String[] wbss)
    {
        return checkViewsMapper.deleteCheckViewsByWbss(wbss);
    }

    /**
     * 删除CheckViews信息
     * 
     * @param wbs CheckViews主键
     * @return 结果
     */
    @Override
    public int deleteCheckViewsByWbs(String wbs)
    {
        return checkViewsMapper.deleteCheckViewsByWbs(wbs);
    }
}
