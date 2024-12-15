package com.ruoyi.CheckViews1.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.CheckViews1.mapper.CheckViews1Mapper;
import com.ruoyi.CheckViews1.domain.CheckViews1;
import com.ruoyi.CheckViews1.service.ICheckViews1Service;

/**
 * CheckViews1Service业务层处理
 * 
 * @author taotao
 * @date 2024-12-09
 */
@Service
public class CheckViews1ServiceImpl implements ICheckViews1Service 
{
    @Autowired
    private CheckViews1Mapper checkViews1Mapper;

    /**
     * 查询CheckViews1
     * 
     * @param wbs CheckViews1主键
     * @return CheckViews1
     */
    @Override
    public CheckViews1 selectCheckViews1ByWbs(String wbs)
    {
        return checkViews1Mapper.selectCheckViews1ByWbs(wbs);
    }

    /**
     * 查询CheckViews1列表
     * 
     * @param checkViews1 CheckViews1
     * @return CheckViews1
     */
    @Override
    public List<CheckViews1> selectCheckViews1List(CheckViews1 checkViews1)
    {
        return checkViews1Mapper.selectCheckViews1List(checkViews1);
    }

    /**
     * 新增CheckViews1
     * 
     * @param checkViews1 CheckViews1
     * @return 结果
     */
    @Override
    public int insertCheckViews1(CheckViews1 checkViews1)
    {
        return checkViews1Mapper.insertCheckViews1(checkViews1);
    }

    /**
     * 修改CheckViews1
     * 
     * @param checkViews1 CheckViews1
     * @return 结果
     */
    @Override
    public int updateCheckViews1(CheckViews1 checkViews1)
    {
        return checkViews1Mapper.updateCheckViews1(checkViews1);
    }

    /**
     * 批量删除CheckViews1
     * 
     * @param wbss 需要删除的CheckViews1主键
     * @return 结果
     */
    @Override
    public int deleteCheckViews1ByWbss(String[] wbss)
    {
        return checkViews1Mapper.deleteCheckViews1ByWbss(wbss);
    }

    /**
     * 删除CheckViews1信息
     * 
     * @param wbs CheckViews1主键
     * @return 结果
     */
    @Override
    public int deleteCheckViews1ByWbs(String wbs)
    {
        return checkViews1Mapper.deleteCheckViews1ByWbs(wbs);
    }
}
