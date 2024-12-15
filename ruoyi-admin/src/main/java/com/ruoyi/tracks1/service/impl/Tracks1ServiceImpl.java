package com.ruoyi.tracks1.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.tracks1.mapper.Tracks1Mapper;
import com.ruoyi.tracks1.domain.Tracks1;
import com.ruoyi.tracks1.service.ITracks1Service;

/**
 * 工作轨迹1Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-11-19
 */
@Service
public class Tracks1ServiceImpl implements ITracks1Service 
{
    @Autowired
    private Tracks1Mapper tracks1Mapper;

    /**
     * 查询工作轨迹1
     * 
     * @param id 工作轨迹1主键
     * @return 工作轨迹1
     */
    @Override
    public Tracks1 selectTracks1ById(Long id)
    {
        return tracks1Mapper.selectTracks1ById(id);
    }

    /**
     * 查询工作轨迹1列表
     * 
     * @param tracks1 工作轨迹1
     * @return 工作轨迹1
     */
    @Override
    public List<Tracks1> selectTracks1List(Tracks1 tracks1)
    {
        return tracks1Mapper.selectTracks1List(tracks1);
    }

    /**
     * 新增工作轨迹1
     * 
     * @param tracks1 工作轨迹1
     * @return 结果
     */
    @Override
    public int insertTracks1(Tracks1 tracks1)
    {
        return tracks1Mapper.insertTracks1(tracks1);
    }

    /**
     * 修改工作轨迹1
     * 
     * @param tracks1 工作轨迹1
     * @return 结果
     */
    @Override
    public int updateTracks1(Tracks1 tracks1)
    {
        return tracks1Mapper.updateTracks1(tracks1);
    }

    /**
     * 批量删除工作轨迹1
     * 
     * @param ids 需要删除的工作轨迹1主键
     * @return 结果
     */
    @Override
    public int deleteTracks1ByIds(Long[] ids)
    {
        return tracks1Mapper.deleteTracks1ByIds(ids);
    }

    /**
     * 删除工作轨迹1信息
     * 
     * @param id 工作轨迹1主键
     * @return 结果
     */
    @Override
    public int deleteTracks1ById(Long id)
    {
        return tracks1Mapper.deleteTracks1ById(id);
    }
}
