import request from '@/utils/request'

// 查询质量检查管理列表
export function listQualitycheck(query) {
  return request({
    url: '/qualitycheck/qualitycheck/list',
    method: 'get',
    params: query
  })
}

// 查询质量检查管理详细
export function getQualitycheck(checkId) {
  return request({
    url: '/qualitycheck/qualitycheck/' + checkId,
    method: 'get'
  })
}

// 新增质量检查管理
export function addQualitycheck(data) {
  return request({
    url: '/qualitycheck/qualitycheck',
    method: 'post',
    data: data
  })
}

// 修改质量检查管理
export function updateQualitycheck(data) {
  return request({
    url: '/qualitycheck/qualitycheck',
    method: 'put',
    data: data
  })
}

// 删除质量检查管理
export function delQualitycheck(checkId) {
  return request({
    url: '/qualitycheck/qualitycheck/' + checkId,
    method: 'delete'
  })
}
