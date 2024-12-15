package com.ruoyi.TextCheck.controller;

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
import com.ruoyi.TextCheck.domain.TextCheck;
import com.ruoyi.TextCheck.service.ITextCheckService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 检查测试Controller
 * 
 * @author zwt
 * @date 2024-11-12
 */
@RestController
@RequestMapping("/TextCheck/TextCheck")
public class TextCheckController extends BaseController
{
    @Autowired
    private ITextCheckService textCheckService;

    /**
     * 查询检查测试列表
     */
    @PreAuthorize("@ss.hasPermi('TextCheck:TextCheck:list')")
    @GetMapping("/list")
    public AjaxResult list(TextCheck textCheck)
    {
        List<TextCheck> list = textCheckService.selectTextCheckList(textCheck);
        return success(list);
    }

    /**
     * 导出检查测试列表
     */
    @PreAuthorize("@ss.hasPermi('TextCheck:TextCheck:export')")
    @Log(title = "检查测试", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TextCheck textCheck)
    {
        List<TextCheck> list = textCheckService.selectTextCheckList(textCheck);
        ExcelUtil<TextCheck> util = new ExcelUtil<TextCheck>(TextCheck.class);
        util.exportExcel(response, list, "检查测试数据");
    }

    /**
     * 获取检查测试详细信息
     */
    @PreAuthorize("@ss.hasPermi('TextCheck:TextCheck:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return success(textCheckService.selectTextCheckByTaskId(taskId));
    }

    /**
     * 新增检查测试
     */
    @PreAuthorize("@ss.hasPermi('TextCheck:TextCheck:add')")
    @Log(title = "检查测试", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TextCheck textCheck)
    {
        return toAjax(textCheckService.insertTextCheck(textCheck));
    }

    /**
     * 修改检查测试
     */
    @PreAuthorize("@ss.hasPermi('TextCheck:TextCheck:edit')")
    @Log(title = "检查测试", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TextCheck textCheck)
    {
        return toAjax(textCheckService.updateTextCheck(textCheck));
    }

    /**
     * 删除检查测试
     */
    @PreAuthorize("@ss.hasPermi('TextCheck:TextCheck:remove')")
    @Log(title = "检查测试", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(textCheckService.deleteTextCheckByTaskIds(taskIds));
    }
}
