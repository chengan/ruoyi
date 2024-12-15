package com.ruoyi.qualitycheck.service;

import java.util.List;
import com.ruoyi.qualitycheck.domain.QualityCheck;

/**
 * 质量检查管理Service接口
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
public interface IQualityCheckService 
{
    /**
     * 查询质量检查管理
     * 
     * @param checkId 质量检查管理主键
     * @return 质量检查管理
     */
    public QualityCheck selectQualityCheckByCheckId(Long checkId);

    /**
     * 查询质量检查管理列表
     * 
     * @param qualityCheck 质量检查管理
     * @return 质量检查管理集合
     */
    public List<QualityCheck> selectQualityCheckList(QualityCheck qualityCheck);

    /**
     * 新增质量检查管理
     * 
     * @param qualityCheck 质量检查管理
     * @return 结果
     */
    public int insertQualityCheck(QualityCheck qualityCheck);

    /**
     * 修改质量检查管理
     * 
     * @param qualityCheck 质量检查管理
     * @return 结果
     */
    public int updateQualityCheck(QualityCheck qualityCheck);

    /**
     * 批量删除质量检查管理
     * 
     * @param checkIds 需要删除的质量检查管理主键集合
     * @return 结果
     */
    public int deleteQualityCheckByCheckIds(Long[] checkIds);

    /**
     * 删除质量检查管理信息
     * 
     * @param checkId 质量检查管理主键
     * @return 结果
     */
    public int deleteQualityCheckByCheckId(Long checkId);
}
