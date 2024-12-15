import request from '@/utils/request'

// 查询质量检查上报列表
export function listQualityCheckPro(query) {
  return request({
    url: '/QualityCheckPro/QualityCheckPro/list',
    method: 'get',
    params: query
  })
}

// 查询质量检查上报详细
export function getQualityCheckPro(taskId) {
  return request({
    url: '/QualityCheckPro/QualityCheckPro/' + taskId,
    method: 'get'
  })
}

// 新增质量检查上报
export function addQualityCheckPro(data) {
  return request({
    url: '/QualityCheckPro/QualityCheckPro',
    method: 'post',
    data: data
  })
}

// 修改质量检查上报
export function updateQualityCheckPro(data) {
  return request({
    url: '/QualityCheckPro/QualityCheckPro',
    method: 'put',
    data: data
  })
}

// 删除质量检查上报
export function delQualityCheckPro(taskId) {
  return request({
    url: '/QualityCheckPro/QualityCheckPro/' + taskId,
    method: 'delete'
  })
}
