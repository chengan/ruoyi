import request from '@/utils/request'

// 查询CheckViews列表
export function listCheckViews(query) {
  return request({
    url: '/CheckViews/CheckViews/list',
    method: 'get',
    params: query
  })
}

// 查询CheckViews详细
export function getCheckViews(wbs) {
  return request({
    url: '/CheckViews/CheckViews/' + wbs,
    method: 'get'
  })
}

// 新增CheckViews
export function addCheckViews(data) {
  return request({
    url: '/CheckViews/CheckViews',
    method: 'post',
    data: data
  })
}

// 修改CheckViews
export function updateCheckViews(data) {
  return request({
    url: '/CheckViews/CheckViews',
    method: 'put',
    data: data
  })
}

// 删除CheckViews
export function delCheckViews(wbs) {
  return request({
    url: '/CheckViews/CheckViews/' + wbs,
    method: 'delete'
  })
}
