package com.ruoyi.RecordQuestion.controller;

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
import com.ruoyi.RecordQuestion.domain.RecordQuestion;
import com.ruoyi.RecordQuestion.service.IRecordQuestionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 整改记录Controller
 * 
 * @author zwt
 * @date 2024-11-13
 */
@RestController
@RequestMapping("/RecordQuestion/RecordQuestion")
public class RecordQuestionController extends BaseController
{
    @Autowired
    private IRecordQuestionService recordQuestionService;

    /**
     * 查询整改记录列表
     */
    @PreAuthorize("@ss.hasPermi('RecordQuestion:RecordQuestion:list')")
    @GetMapping("/list")
    public TableDataInfo list(RecordQuestion recordQuestion)
    {
        startPage();
        List<RecordQuestion> list = recordQuestionService.selectRecordQuestionList(recordQuestion);
        return getDataTable(list);
    }

    /**
     * 导出整改记录列表
     */
    @PreAuthorize("@ss.hasPermi('RecordQuestion:RecordQuestion:export')")
    @Log(title = "整改记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RecordQuestion recordQuestion)
    {
        List<RecordQuestion> list = recordQuestionService.selectRecordQuestionList(recordQuestion);
        ExcelUtil<RecordQuestion> util = new ExcelUtil<RecordQuestion>(RecordQuestion.class);
        util.exportExcel(response, list, "整改记录数据");
    }

    /**
     * 获取整改记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('RecordQuestion:RecordQuestion:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return success(recordQuestionService.selectRecordQuestionByTaskId(taskId));
    }

    /**
     * 新增整改记录
     */
    @PreAuthorize("@ss.hasPermi('RecordQuestion:RecordQuestion:add')")
    @Log(title = "整改记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RecordQuestion recordQuestion)
    {
        return toAjax(recordQuestionService.insertRecordQuestion(recordQuestion));
    }

    /**
     * 修改整改记录
     */
    @PreAuthorize("@ss.hasPermi('RecordQuestion:RecordQuestion:edit')")
    @Log(title = "整改记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RecordQuestion recordQuestion)
    {
        return toAjax(recordQuestionService.updateRecordQuestion(recordQuestion));
    }

    /**
     * 删除整改记录
     */
    @PreAuthorize("@ss.hasPermi('RecordQuestion:RecordQuestion:remove')")
    @Log(title = "整改记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(recordQuestionService.deleteRecordQuestionByTaskIds(taskIds));
    }
}
