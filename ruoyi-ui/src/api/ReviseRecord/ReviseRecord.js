import request from '@/utils/request'

// 查询整改结果上报列表
export function listReviseRecord(query) {
  return request({
    url: '/ReviseRecord/ReviseRecord/list',
    method: 'get',
    params: query
  })
}

// 查询整改结果上报详细
export function getReviseRecord(reviseId) {
  return request({
    url: '/ReviseRecord/ReviseRecord/' + reviseId,
    method: 'get'
  })
}

// 新增整改结果上报
export function addReviseRecord(data) {
  return request({
    url: '/ReviseRecord/ReviseRecord',
    method: 'post',
    data: data
  })
}

// 修改整改结果上报
export function updateReviseRecord(data) {
  return request({
    url: '/ReviseRecord/ReviseRecord',
    method: 'put',
    data: data
  })
}

// 删除整改结果上报
export function delReviseRecord(reviseId) {
  return request({
    url: '/ReviseRecord/ReviseRecord/' + reviseId,
    method: 'delete'
  })
}
