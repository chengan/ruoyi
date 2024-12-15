import request from '@/utils/request'

// 查询竣工验收上报列表
export function listAcceptanceReport(query) {
  return request({
    url: '/AcceptanceReport/AcceptanceReport/list',
    method: 'get',
    params: query
  })
}

// 查询竣工验收上报详细
export function getAcceptanceReport(acceptanceId) {
  return request({
    url: '/AcceptanceReport/AcceptanceReport/' + acceptanceId,
    method: 'get'
  })
}

// 新增竣工验收上报
export function addAcceptanceReport(data) {
  return request({
    url: '/AcceptanceReport/AcceptanceReport',
    method: 'post',
    data: data
  })
}

// 修改竣工验收上报
export function updateAcceptanceReport(data) {
  return request({
    url: '/AcceptanceReport/AcceptanceReport',
    method: 'put',
    data: data
  })
}

// 删除竣工验收上报
export function delAcceptanceReport(acceptanceId) {
  return request({
    url: '/AcceptanceReport/AcceptanceReport/' + acceptanceId,
    method: 'delete'
  })
}
