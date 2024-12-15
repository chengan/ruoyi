import request from '@/utils/request'

// 查询整改记录列表
export function listRecordQuestion(query) {
  return request({
    url: '/RecordQuestion/RecordQuestion/list',
    method: 'get',
    params: query
  })
}

// 查询整改记录详细
export function getRecordQuestion(taskId) {
  return request({
    url: '/RecordQuestion/RecordQuestion/' + taskId,
    method: 'get'
  })
}

// 新增整改记录
export function addRecordQuestion(data) {
  return request({
    url: '/RecordQuestion/RecordQuestion',
    method: 'post',
    data: data
  })
}

// 修改整改记录
export function updateRecordQuestion(data) {
  return request({
    url: '/RecordQuestion/RecordQuestion',
    method: 'put',
    data: data
  })
}

// 删除整改记录
export function delRecordQuestion(taskId) {
  return request({
    url: '/RecordQuestion/RecordQuestion/' + taskId,
    method: 'delete'
  })
}
