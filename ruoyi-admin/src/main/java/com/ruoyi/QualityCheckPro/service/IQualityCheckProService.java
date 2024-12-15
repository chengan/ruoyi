package com.ruoyi.QualityCheckPro.service;

import java.util.List;
import com.ruoyi.QualityCheckPro.domain.QualityCheckPro;
import com.ruoyi.QualityCheckPro.domain.Vo.CheckVo;

/**
 * 质量检查上报Service接口
 * 
 * @author taotao
 * @date 2024-11-13
 */
public interface IQualityCheckProService 
{
    /**
     * 查询质量检查上报
     * 
     * @param taskId 质量检查上报主键
     * @return 质量检查上报
     */
    public QualityCheckPro selectQualityCheckProByTaskId(Long taskId);

    /**
     * 查询质量检查上报列表
     * 
     * @param qualityCheckPro 质量检查上报
     * @return 质量检查上报集合
     */
    public List<QualityCheckPro> selectQualityCheckProList(QualityCheckPro qualityCheckPro);

    /**
     * 新增质量检查上报
     * 
     * @param qualityCheckPro 质量检查上报
     * @return 结果
     */
    public int insertQualityCheckPro(QualityCheckPro qualityCheckPro);

    /**
     * 修改质量检查上报
     * 
     * @param qualityCheckPro 质量检查上报
     * @return 结果
     */
    public int updateQualityCheckPro(QualityCheckPro qualityCheckPro);

    /**
     * 批量删除质量检查上报
     * 
     * @param taskIds 需要删除的质量检查上报主键集合
     * @return 结果
     */
    public int deleteQualityCheckProByTaskIds(Long[] taskIds);

    /**
     * 删除质量检查上报信息
     * 
     * @param taskId 质量检查上报主键
     * @return 结果
     */
    public int deleteQualityCheckProByTaskId(Long taskId);



    public List<CheckVo> selectQualityCheckPro(QualityCheckPro qualityCheckPro );
}
