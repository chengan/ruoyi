package com.ruoyi.QualityCheckReport.controller;

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
import com.ruoyi.QualityCheckReport.domain.QualityCheckReport;
import com.ruoyi.QualityCheckReport.service.IQualityCheckReportService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 施工质量上报Controller
 * 
 * @author ruoyi
 * @date 2024-11-13
 */
@RestController
@RequestMapping("/QualityCheckReport/QualityCheckReport")
public class QualityCheckReportController extends BaseController
{
    @Autowired
    private IQualityCheckReportService qualityCheckReportService;




    /**
     * 查询施工质量上报列表
     */
    @PreAuthorize("@ss.hasPermi('QualityCheckReport:QualityCheckReport:list')")
    @GetMapping("/list")
    public TableDataInfo list(QualityCheckReport qualityCheckReport)
    {
        startPage();
        List<QualityCheckReport> list = qualityCheckReportService.selectQualityCheckReportList(qualityCheckReport);
        return getDataTable(list);
    }

    /**
     * 导出施工质量上报列表
     */
    @PreAuthorize("@ss.hasPermi('QualityCheckReport:QualityCheckReport:export')")
    @Log(title = "施工质量上报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QualityCheckReport qualityCheckReport)
    {
        List<QualityCheckReport> list = qualityCheckReportService.selectQualityCheckReportList(qualityCheckReport);
        ExcelUtil<QualityCheckReport> util = new ExcelUtil<QualityCheckReport>(QualityCheckReport.class);
        util.exportExcel(response, list, "施工质量上报数据");
    }

    /**
     * 获取施工质量上报详细信息
     */
    @PreAuthorize("@ss.hasPermi('QualityCheckReport:QualityCheckReport:query')")
    @GetMapping(value = "/{checkId}")
    public AjaxResult getInfo(@PathVariable("checkId") Long checkId)
    {
        return success(qualityCheckReportService.selectQualityCheckReportByCheckId(checkId));
    }

    /**
     * 新增施工质量上报
     */
    @PreAuthorize("@ss.hasPermi('QualityCheckReport:QualityCheckReport:add')")
    @Log(title = "施工质量上报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QualityCheckReport qualityCheckReport)
    {
        return toAjax(qualityCheckReportService.insertQualityCheckReport(qualityCheckReport));
    }

    /**
     * 修改施工质量上报
     */
    @PreAuthorize("@ss.hasPermi('QualityCheckReport:QualityCheckReport:edit')")
    @Log(title = "施工质量上报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QualityCheckReport qualityCheckReport)
    {
        return toAjax(qualityCheckReportService.updateQualityCheckReport(qualityCheckReport));
    }

    /**
     * 删除施工质量上报
     */
    @PreAuthorize("@ss.hasPermi('QualityCheckReport:QualityCheckReport:remove')")
    @Log(title = "施工质量上报", businessType = BusinessType.DELETE)
	@DeleteMapping("/{checkIds}")
    public AjaxResult remove(@PathVariable Long[] checkIds)
    {
        return toAjax(qualityCheckReportService.deleteQualityCheckReportByCheckIds(checkIds));
    }
}
