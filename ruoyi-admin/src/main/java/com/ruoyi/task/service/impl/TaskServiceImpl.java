package com.ruoyi.task.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.task.mapper.TaskMapper;
import com.alibaba.fastjson2.JSON;
import com.ruoyi.task.domain.Task;
import com.ruoyi.task.service.ITaskService;
import com.ruoyi.task.domain.vo.WbsTreeVo;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 任务管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
@Service
public class TaskServiceImpl implements ITaskService 
{
    @Autowired
    private TaskMapper taskMapper;

    /**
     * 查询任务管理
     * 
     * @param taskId 任务管理主键
     * @return 任务管理
     */
    @Override
    public Task selectTaskByTaskId(Long taskId)
    {
        return taskMapper.selectTaskByTaskId(taskId);
    }

    /**
     * 查询任务管理列表
     * 
     * @param task 任务管理
     * @return 任务管理
     */
    @Override
    public List<Task> selectTaskList(Task task)
    {
        return taskMapper.selectTaskList(task);
    }

    /**
     * 新增任务管理
     * 
     * @param task 任务管理
     * @return 结果
     */
    @Override
    public int insertTask(Task task)
    {
        return taskMapper.insertTask(task);
    }

    /**
     * 修改任务管理
     * 
     * @param task 任务管理
     * @return 结果
     */
    @Override
    public int updateTask(Task task)
    {
        return taskMapper.updateTask(task);
    }

    /**
     * 批量删除任务管理
     * 
     * @param taskIds 需要删除的任务管理主键
     * @return 结果
     */
    @Override
    public int deleteTaskByTaskIds(Long[] taskIds)
    {
        return taskMapper.deleteTaskByTaskIds(taskIds);
    }

    /**
     * 删除任务管理信息
     * 
     * @param taskId 任务管理主键
     * @return 结果
     */
    @Override
    public int deleteTaskByTaskId(Long taskId)
    {
        return taskMapper.deleteTaskByTaskId(taskId);
    }

    @Override
    public List<WbsTreeVo> queryWbsList() {
        // 1. 获取所有任务数据
        List<Task> taskList = taskMapper.selectAllTaskList();
        
        // 2. 构建WBS树
        return buildWbsTree(taskList);
    }

    private List<WbsTreeVo> buildWbsTree(List<Task> taskList) {
        List<WbsTreeVo> result = new ArrayList<>();
        
        // 1. 按项目ID分组
        Map<Long, List<Task>> projectTasks = taskList.stream()
            .collect(Collectors.groupingBy(Task::getProjectId));
        
        // 2. 处理每个项目
        projectTasks.forEach((projectId, tasks) -> {
            // 2.1 找到所有根节点（groupId为空且vId为空的任务）
            List<Task> rootTasks = tasks.stream()
                .filter(task -> task.getGroupId() == null && task.getVId() == null)
                .collect(Collectors.toList());
                
            // 2.2 将所有根节点添加到结果中
            for (Task rootTask : rootTasks) {
                WbsTreeVo rootNode = createWbsNode(rootTask, null);
                result.add(rootNode);
            }
            
            // 2.3 处理所有组
            Map<Long, List<Task>> groupTasks = tasks.stream()
                .filter(task -> task.getGroupId() != null)
                .collect(Collectors.groupingBy(Task::getGroupId));
                
            // 2.4 处理每个组
            groupTasks.forEach((groupId, groupTaskList) -> {
                Task groupTask = groupTaskList.stream()
                    .filter(task -> task.getVId() == null)
                    .findFirst()
                    .orElse(null);
                    
                if (groupTask != null) {
                    WbsTreeVo groupNode = createWbsNode(groupTask, null);
                    
                    // 处理组内的子任务
                    List<WbsTreeVo> taskNodes = groupTaskList.stream()
                        .filter(task -> task.getVId() != null)
                        .sorted((a, b) -> a.getVId().compareTo(b.getVId()))
                        .map(task -> createWbsNode(task, groupNode.getId()))
                        .collect(Collectors.toList());
                        
                    if (!taskNodes.isEmpty()) {
                        groupNode.setChildren(taskNodes);
                    }
                    
                    // 2.5 找到第一个匹配的根节点作为父节点
                    Task parentTask = rootTasks.stream()
                        .filter(root -> root.getProjectId().equals(groupTask.getProjectId()))
                        .findFirst()
                        .orElse(null);
                        
                    if (parentTask != null) {
                        // 找到结果列表中对应的根节点
                        result.stream()
                            .filter(node -> node.getId().equals(parentTask.getTaskId()))
                            .findFirst()
                            .ifPresent(parentNode -> {
                                groupNode.setParentId(parentNode.getId());
                                if (parentNode.getChildren() == null) {
                                    parentNode.setChildren(new ArrayList<>());
                                }
                                parentNode.getChildren().add(groupNode);
                            });
                    } else {
                        result.add(groupNode);
                    }
                }
            });
        });
        
        return result;
    }

    // 辅助方法：创建WBS节点
    private WbsTreeVo createWbsNode(Task task, Long parentId) {
        WbsTreeVo node = new WbsTreeVo();
        node.setId(task.getTaskId());
        node.setWbs(buildWbsCode(task));
        node.setParentId(parentId);
        node.setTaskName(task.getTaskName());
        return node;
    }

    private String buildWbsCode(Task task) {
        StringBuilder wbs = new StringBuilder();
        wbs.append(task.getProjectId());
        
        if (task.getGroupId() != null) {
            wbs.append(".").append(task.getGroupId());
        }
        
        if (task.getVId() != null) {
            wbs.append(".").append(task.getVId());
        }
        
        return wbs.toString();
    }
}
