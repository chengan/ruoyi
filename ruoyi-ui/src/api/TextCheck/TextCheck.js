import request from '@/utils/request'

// 查询检查测试列表
export function listTextCheck(query) {
  return request({
    url: '/TextCheck/TextCheck/list',
    method: 'get',
    params: query
  })
}

// 查询检查测试详细
export function getTextCheck(taskId) {
  return request({
    url: '/TextCheck/TextCheck/' + taskId,
    method: 'get'
  })
}

// 新增检查测试
export function addTextCheck(data) {
  return request({
    url: '/TextCheck/TextCheck',
    method: 'post',
    data: data
  })
}

// 修改检查测试
export function updateTextCheck(data) {
  return request({
    url: '/TextCheck/TextCheck',
    method: 'put',
    data: data
  })
}

// 删除检查测试
export function delTextCheck(taskId) {
  return request({
    url: '/TextCheck/TextCheck/' + taskId,
    method: 'delete'
  })
}
