import request from '@/utils/request'

// 查询竣工验收管理列表
export function listAcceptance(query) {
  return request({
    url: '/acceptance/acceptance/list',
    method: 'get',
    params: query
  })
}

// 查询竣工验收管理详细
export function getAcceptance(reportId) {
  return request({
    url: '/acceptance/acceptance/' + reportId,
    method: 'get'
  })
}

// 新增竣工验收管理
export function addAcceptance(data) {
  return request({
    url: '/acceptance/acceptance',
    method: 'post',
    data: data
  })
}

// 修改竣工验收管理
export function updateAcceptance(data) {
  return request({
    url: '/acceptance/acceptance',
    method: 'put',
    data: data
  })
}

// 删除竣工验收管理
export function delAcceptance(reportId) {
  return request({
    url: '/acceptance/acceptance/' + reportId,
    method: 'delete'
  })
}
