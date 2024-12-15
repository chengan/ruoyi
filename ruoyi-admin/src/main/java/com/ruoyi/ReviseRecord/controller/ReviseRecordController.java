package com.ruoyi.ReviseRecord.controller;

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
import com.ruoyi.ReviseRecord.domain.ReviseRecord;
import com.ruoyi.ReviseRecord.service.IReviseRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 整改结果上报Controller
 * 
 * @author taotao
 * @date 2024-11-14
 */
@RestController
@RequestMapping("/ReviseRecord/ReviseRecord")
public class ReviseRecordController extends BaseController
{
    @Autowired
    private IReviseRecordService reviseRecordService;

    /**
     * 查询整改结果上报列表
     */
    @PreAuthorize("@ss.hasPermi('ReviseRecord:ReviseRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(ReviseRecord reviseRecord)
    {
        startPage();
        List<ReviseRecord> list = reviseRecordService.selectReviseRecordList(reviseRecord);
        return getDataTable(list);
    }

    /**
     * 导出整改结果上报列表
     */
    @PreAuthorize("@ss.hasPermi('ReviseRecord:ReviseRecord:export')")
    @Log(title = "整改结果上报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ReviseRecord reviseRecord)
    {
        List<ReviseRecord> list = reviseRecordService.selectReviseRecordList(reviseRecord);
        ExcelUtil<ReviseRecord> util = new ExcelUtil<ReviseRecord>(ReviseRecord.class);
        util.exportExcel(response, list, "整改结果上报数据");
    }

    /**
     * 获取整改结果上报详细信息
     */
    @PreAuthorize("@ss.hasPermi('ReviseRecord:ReviseRecord:query')")
    @GetMapping(value = "/{reviseId}")
    public AjaxResult getInfo(@PathVariable("reviseId") Long reviseId)
    {
        return success(reviseRecordService.selectReviseRecordByReviseId(reviseId));
    }

    /**
     * 新增整改结果上报
     */
    @PreAuthorize("@ss.hasPermi('ReviseRecord:ReviseRecord:add')")
    @Log(title = "整改结果上报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ReviseRecord reviseRecord)
    {
        return toAjax(reviseRecordService.insertReviseRecord(reviseRecord));
    }

    /**
     * 修改整改结果上报
     */
    @PreAuthorize("@ss.hasPermi('ReviseRecord:ReviseRecord:edit')")
    @Log(title = "整改结果上报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ReviseRecord reviseRecord)
    {
        return toAjax(reviseRecordService.updateReviseRecord(reviseRecord));
    }

    /**
     * 删除整改结果上报
     */
    @PreAuthorize("@ss.hasPermi('ReviseRecord:ReviseRecord:remove')")
    @Log(title = "整改结果上报", businessType = BusinessType.DELETE)
	@DeleteMapping("/{reviseIds}")
    public AjaxResult remove(@PathVariable Long[] reviseIds)
    {
        return toAjax(reviseRecordService.deleteReviseRecordByReviseIds(reviseIds));
    }
}
