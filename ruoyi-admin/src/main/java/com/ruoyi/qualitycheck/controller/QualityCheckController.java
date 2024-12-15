package com.ruoyi.qualitycheck.controller;

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
import com.ruoyi.qualitycheck.domain.QualityCheck;
import com.ruoyi.qualitycheck.service.IQualityCheckService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 质量检查管理Controller
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
@RestController
@RequestMapping("/qualitycheck/qualitycheck")
public class QualityCheckController extends BaseController
{
    @Autowired
    private IQualityCheckService qualityCheckService;

    /**
     * 查询质量检查管理列表
     */
    @PreAuthorize("@ss.hasPermi('qualitycheck:qualitycheck:list')")
    @GetMapping("/list")
    public TableDataInfo list(QualityCheck qualityCheck)
    {
        startPage();
        List<QualityCheck> list = qualityCheckService.selectQualityCheckList(qualityCheck);
        return getDataTable(list);
    }

    /**
     * 导出质量检查管理列表
     */
    @PreAuthorize("@ss.hasPermi('qualitycheck:qualitycheck:export')")
    @Log(title = "质量检查管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QualityCheck qualityCheck)
    {
        List<QualityCheck> list = qualityCheckService.selectQualityCheckList(qualityCheck);
        ExcelUtil<QualityCheck> util = new ExcelUtil<QualityCheck>(QualityCheck.class);
        util.exportExcel(response, list, "质量检查管理数据");
    }

    /**
     * 获取质量检查管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('qualitycheck:qualitycheck:query')")
    @GetMapping(value = "/{checkId}")
    public AjaxResult getInfo(@PathVariable("checkId") Long checkId)
    {
        return success(qualityCheckService.selectQualityCheckByCheckId(checkId));
    }

    /**
     * 新增质量检查管理
     */
    @PreAuthorize("@ss.hasPermi('qualitycheck:qualitycheck:add')")
    @Log(title = "质量检查管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QualityCheck qualityCheck)
    {
        return toAjax(qualityCheckService.insertQualityCheck(qualityCheck));
    }

    /**
     * 修改质量检查管理
     */
    @PreAuthorize("@ss.hasPermi('qualitycheck:qualitycheck:edit')")
    @Log(title = "质量检查管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QualityCheck qualityCheck)
    {
        return toAjax(qualityCheckService.updateQualityCheck(qualityCheck));
    }

    /**
     * 删除质量检查管理
     */
    @PreAuthorize("@ss.hasPermi('qualitycheck:qualitycheck:remove')")
    @Log(title = "质量检查管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{checkIds}")
    public AjaxResult remove(@PathVariable Long[] checkIds)
    {
        return toAjax(qualityCheckService.deleteQualityCheckByCheckIds(checkIds));
    }
}
