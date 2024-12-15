import request from '@/utils/request'

// 查询CheckViews1列表
export function listCheckViews1(query) {
  return request({
    url: '/CheckViews1/CheckViews1/list',
    method: 'get',
    params: query
  })
}

// 查询CheckViews1详细
export function getCheckViews1(wbs) {
  return request({
    url: '/CheckViews1/CheckViews1/' + wbs,
    method: 'get'
  })
}

// 新增CheckViews1
export function addCheckViews1(data) {
  return request({
    url: '/CheckViews1/CheckViews1',
    method: 'post',
    data: data
  })
}

// 修改CheckViews1
export function updateCheckViews1(data) {
  return request({
    url: '/CheckViews1/CheckViews1',
    method: 'put',
    data: data
  })
}

// 删除CheckViews1
export function delCheckViews1(wbs) {
  return request({
    url: '/CheckViews1/CheckViews1/' + wbs,
    method: 'delete'
  })
}
