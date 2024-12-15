package com.ruoyi.QualityCheckPro.service.impl;

import java.util.List;

import com.ruoyi.QualityCheckPro.domain.Vo.CheckVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.QualityCheckPro.mapper.QualityCheckProMapper;
import com.ruoyi.QualityCheckPro.domain.QualityCheckPro;
import com.ruoyi.QualityCheckPro.service.IQualityCheckProService;

/**
 * 质量检查上报Service业务层处理
 * 
 * @author taotao
 * @date 2024-11-13
 */
@Service
public class QualityCheckProServiceImpl implements IQualityCheckProService 
{
    @Autowired
    private QualityCheckProMapper qualityCheckProMapper;

    /**
     * 查询质量检查上报
     * 
     * @param taskId 质量检查上报主键
     * @return 质量检查上报
     */
    @Override
    public QualityCheckPro selectQualityCheckProByTaskId(Long taskId)
    {
        return qualityCheckProMapper.selectQualityCheckProByTaskId(taskId);
    }

    /**
     * 查询质量检查上报列表
     * 
     * @param qualityCheckPro 质量检查上报
     * @return 质量检查上报
     */
    @Override
    public List<QualityCheckPro> selectQualityCheckProList(QualityCheckPro qualityCheckPro)
    {
        return qualityCheckProMapper.selectQualityCheckProList(qualityCheckPro);
    }

    /**
     * 新增质量检查上报
     * 
     * @param qualityCheckPro 质量检查上报
     * @return 结果
     */
    @Override
    public int insertQualityCheckPro(QualityCheckPro qualityCheckPro)
    {
        return qualityCheckProMapper.insertQualityCheckPro(qualityCheckPro);
    }

    /**
     * 修改质量检查上报
     * 
     * @param qualityCheckPro 质量检查上报
     * @return 结果
     */
    @Override
    public int updateQualityCheckPro(QualityCheckPro qualityCheckPro)
    {
        return qualityCheckProMapper.updateQualityCheckPro(qualityCheckPro);
    }

    /**
     * 批量删除质量检查上报
     * 
     * @param taskIds 需要删除的质量检查上报主键
     * @return 结果
     */
    @Override
    public int deleteQualityCheckProByTaskIds(Long[] taskIds)
    {
        return qualityCheckProMapper.deleteQualityCheckProByTaskIds(taskIds);
    }

    /**
     * 删除质量检查上报信息
     * 
     * @param taskId 质量检查上报主键
     * @return 结果
     */
    @Override
    public int deleteQualityCheckProByTaskId(Long taskId)
    {
        return qualityCheckProMapper.deleteQualityCheckProByTaskId(taskId);
    }

    @Override
    public List<CheckVo> selectQualityCheckPro(QualityCheckPro qualityCheckPro) {
        return qualityCheckProMapper.selectQualityCheckPro(qualityCheckPro);
    }


}
