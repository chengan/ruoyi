package com.ruoyi.tracks1.mapper;

import java.util.List;
import com.ruoyi.tracks1.domain.Tracks1;

/**
 * 工作轨迹1Mapper接口
 * 
 * @author ruoyi
 * @date 2024-11-19
 */
public interface Tracks1Mapper 
{
    /**
     * 查询工作轨迹1
     * 
     * @param id 工作轨迹1主键
     * @return 工作轨迹1
     */
    public Tracks1 selectTracks1ById(Long id);

    /**
     * 查询工作轨迹1列表
     * 
     * @param tracks1 工作轨迹1
     * @return 工作轨迹1集合
     */
    public List<Tracks1> selectTracks1List(Tracks1 tracks1);

    /**
     * 新增工作轨迹1
     * 
     * @param tracks1 工作轨迹1
     * @return 结果
     */
    public int insertTracks1(Tracks1 tracks1);

    /**
     * 修改工作轨迹1
     * 
     * @param tracks1 工作轨迹1
     * @return 结果
     */
    public int updateTracks1(Tracks1 tracks1);

    /**
     * 删除工作轨迹1
     * 
     * @param id 工作轨迹1主键
     * @return 结果
     */
    public int deleteTracks1ById(Long id);

    /**
     * 批量删除工作轨迹1
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTracks1ByIds(Long[] ids);
}
