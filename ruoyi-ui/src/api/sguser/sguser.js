import request from '@/utils/request'

// 查询用户管理列表
export function listSguser(query) {
  return request({
    url: '/sguser/sguser/list',
    method: 'get',
    params: query
  })
}

// 查询用户管理详细
export function getSguser(userId) {
  return request({
    url: '/sguser/sguser/' + userId,
    method: 'get'
  })
}

// 新增用户管理
export function addSguser(data) {
  return request({
    url: '/sguser/sguser',
    method: 'post',
    data: data
  })
}

// 修改用户管理
export function updateSguser(data) {
  return request({
    url: '/sguser/sguser',
    method: 'put',
    data: data
  })
}

// 删除用户管理
export function delSguser(userId) {
  return request({
    url: '/sguser/sguser/' + userId,
    method: 'delete'
  })
}
