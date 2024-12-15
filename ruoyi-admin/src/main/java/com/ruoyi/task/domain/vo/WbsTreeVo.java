package com.ruoyi.task.domain.vo;

import java.util.List;

public class WbsTreeVo {
    private Long id;
    private String wbs;
    private Long parentId;
    private List<WbsTreeVo> children;
    private String taskName;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWbs() {
        return wbs;
    }

    public void setWbs(String wbs) {
        this.wbs = wbs;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<WbsTreeVo> getChildren() {
        return children;
    }

    public void setChildren(List<WbsTreeVo> children) {
        this.children = children;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
} 