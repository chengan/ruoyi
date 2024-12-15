package com.ruoyi.QualityCheckPro.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.QualityCheckPro.domain.QualityCheckPro;
import com.ruoyi.QualityCheckPro.service.IQualityCheckProService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 质量检查上报Controller
 * 
 * @author taotao
 * @date 2024-11-13
 */
@RestController
@RequestMapping("/QualityCheckPro/QualityCheckPro")
public class QualityCheckProController extends BaseController
{
    @Autowired
    private IQualityCheckProService qualityCheckProService;

    /**
     * 查询质量检查上报列表
     */
    @PreAuthorize("@ss.hasPermi('QualityCheckPro:QualityCheckPro:list')")
    @GetMapping("/list")
    public AjaxResult list(QualityCheckPro qualityCheckPro)
    {
        List<QualityCheckPro> list = qualityCheckProService.selectQualityCheckProList(qualityCheckPro);
        return success(list);
    }

    /**
     * 导出质量检查上报列表
     */
    @PreAuthorize("@ss.hasPermi('QualityCheckPro:QualityCheckPro:export')")
    @Log(title = "质量检查上报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QualityCheckPro qualityCheckPro)
    {
        List<QualityCheckPro> list = qualityCheckProService.selectQualityCheckProList(qualityCheckPro);
        ExcelUtil<QualityCheckPro> util = new ExcelUtil<QualityCheckPro>(QualityCheckPro.class);
        util.exportExcel(response, list, "质量检查上报数据");
    }

    /**
     * 获取质量检查上报详细信息
     */
    @PreAuthorize("@ss.hasPermi('QualityCheckPro:QualityCheckPro:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return success(qualityCheckProService.selectQualityCheckProByTaskId(taskId));
    }

    /**
     * 新增质量检查上报
     */
    @PreAuthorize("@ss.hasPermi('QualityCheckPro:QualityCheckPro:add')")
    @Log(title = "质量检查上报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QualityCheckPro qualityCheckPro)
    {
        return toAjax(qualityCheckProService.insertQualityCheckPro(qualityCheckPro));
    }

    /**
     * 修改质量检查上报
     */
    @PreAuthorize("@ss.hasPermi('QualityCheckPro:QualityCheckPro:edit')")
    @Log(title = "质量检查上报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QualityCheckPro qualityCheckPro)
    {
        return toAjax(qualityCheckProService.updateQualityCheckPro(qualityCheckPro));
    }

    /**
     * 删除质量检查上报
     */
    @PreAuthorize("@ss.hasPermi('QualityCheckPro:QualityCheckPro:remove')")
    @Log(title = "质量检查上报", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(qualityCheckProService.deleteQualityCheckProByTaskIds(taskIds));
    }
}
