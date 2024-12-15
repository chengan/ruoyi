import request from '@/utils/request'

// 查询工作轨迹1列表
export function listTracks1(query) {
  return request({
    url: '/tracks1/tracks1/list',
    method: 'get',
    params: query
  })
}

// 查询工作轨迹1详细
export function getTracks1(id) {
  return request({
    url: '/tracks1/tracks1/' + id,
    method: 'get'
  })
}

// 新增工作轨迹1
export function addTracks1(data) {
  return request({
    url: '/tracks1/tracks1',
    method: 'post',
    data: data
  })
}

// 修改工作轨迹1
export function updateTracks1(data) {
  return request({
    url: '/tracks1/tracks1',
    method: 'put',
    data: data
  })
}

// 删除工作轨迹1
export function delTracks1(id) {
  return request({
    url: '/tracks1/tracks1/' + id,
    method: 'delete'
  })
}

// 按日期范围查询工作轨迹
export function listTracks1ByDateRange(startDate, endDate) {
  return request({
    url: '/tracks1/tracks1/listByDateRange',
    method: 'get',
    params: {
      startDate,
      endDate
    }
  })
}
