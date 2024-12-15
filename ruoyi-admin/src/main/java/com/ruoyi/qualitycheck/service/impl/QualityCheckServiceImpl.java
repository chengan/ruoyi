package com.ruoyi.qualitycheck.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qualitycheck.mapper.QualityCheckMapper;
import com.ruoyi.qualitycheck.domain.QualityCheck;
import com.ruoyi.qualitycheck.service.IQualityCheckService;

/**
 * 质量检查管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
@Service
public class QualityCheckServiceImpl implements IQualityCheckService 
{
    @Autowired
    private QualityCheckMapper qualityCheckMapper;

    /**
     * 查询质量检查管理
     * 
     * @param checkId 质量检查管理主键
     * @return 质量检查管理
     */
    @Override
    public QualityCheck selectQualityCheckByCheckId(Long checkId)
    {
        return qualityCheckMapper.selectQualityCheckByCheckId(checkId);
    }

    /**
     * 查询质量检查管理列表
     * 
     * @param qualityCheck 质量检查管理
     * @return 质量检查管理
     */
    @Override
    public List<QualityCheck> selectQualityCheckList(QualityCheck qualityCheck)
    {
        return qualityCheckMapper.selectQualityCheckList(qualityCheck);
    }

    /**
     * 新增质量检查管理
     * 
     * @param qualityCheck 质量检查管理
     * @return 结果
     */
    @Override
    public int insertQualityCheck(QualityCheck qualityCheck)
    {
        return qualityCheckMapper.insertQualityCheck(qualityCheck);
    }

    /**
     * 修改质量检查管理
     * 
     * @param qualityCheck 质量检查管理
     * @return 结果
     */
    @Override
    public int updateQualityCheck(QualityCheck qualityCheck)
    {
        return qualityCheckMapper.updateQualityCheck(qualityCheck);
    }

    /**
     * 批量删除质量检查管理
     * 
     * @param checkIds 需要删除的质量检查管理主键
     * @return 结果
     */
    @Override
    public int deleteQualityCheckByCheckIds(Long[] checkIds)
    {
        return qualityCheckMapper.deleteQualityCheckByCheckIds(checkIds);
    }

    /**
     * 删除质量检查管理信息
     * 
     * @param checkId 质量检查管理主键
     * @return 结果
     */
    @Override
    public int deleteQualityCheckByCheckId(Long checkId)
    {
        return qualityCheckMapper.deleteQualityCheckByCheckId(checkId);
    }
}
