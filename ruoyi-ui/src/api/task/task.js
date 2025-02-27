import request from '@/utils/request'

// 查询任务管理列表
export function listTask(query) {
  return request({
    url: '/task/task/list',
    method: 'get',
    params: query
  })
}

// 查询任务管理详细
export function getTask(taskId) {
  return request({
    url: '/task/task/' + taskId,
    method: 'get'
  })
}

// 新增任务管理
export function addTask(data) {
  return request({
    url: '/task/task',
    method: 'post',
    data: data
  })
}

// 修改任务管理
export function updateTask(data) {
  return request({
    url: '/task/task',
    method: 'put',
    data: data
  })
}

// 删除任务管理
export function delTask(taskId) {
  return request({
    url: '/task/task/' + taskId,
    method: 'delete'
  })
}
