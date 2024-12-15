package com.ruoyi.sguser.controller;

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
import com.ruoyi.sguser.domain.SgUser;
import com.ruoyi.sguser.service.ISgUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户管理Controller
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
@RestController
@RequestMapping("/sguser/sguser")
public class SgUserController extends BaseController
{
    @Autowired
    private ISgUserService sgUserService;

    /**
     * 查询用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('sguser:sguser:list')")
    @GetMapping("/list")
    public TableDataInfo list(SgUser sgUser)
    {
        startPage();
        List<SgUser> list = sgUserService.selectSgUserList(sgUser);
        return getDataTable(list);
    }

    /**
     * 导出用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('sguser:sguser:export')")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SgUser sgUser)
    {
        List<SgUser> list = sgUserService.selectSgUserList(sgUser);
        ExcelUtil<SgUser> util = new ExcelUtil<SgUser>(SgUser.class);
        util.exportExcel(response, list, "用户管理数据");
    }

    /**
     * 获取用户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('sguser:sguser:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(sgUserService.selectSgUserByUserId(userId));
    }

    /**
     * 新增用户管理
     */
    @PreAuthorize("@ss.hasPermi('sguser:sguser:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SgUser sgUser)
    {
        return toAjax(sgUserService.insertSgUser(sgUser));
    }

    /**
     * 修改用户管理
     */
    @PreAuthorize("@ss.hasPermi('sguser:sguser:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SgUser sgUser)
    {
        return toAjax(sgUserService.updateSgUser(sgUser));
    }

    /**
     * 删除用户管理
     */
    @PreAuthorize("@ss.hasPermi('sguser:sguser:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(sgUserService.deleteSgUserByUserIds(userIds));
    }
}
