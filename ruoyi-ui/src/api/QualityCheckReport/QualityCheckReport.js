import request from '@/utils/request'

export function selectQualityCheckReport(query) {


  return request({
    url: '/QualityCheckReport/QualityCheckReport/select',
    method: 'get',
    params: query
  })
}

// 查询施工质量上报列表
export function listQualityCheckReport(query) {


  return request({
    url: '/QualityCheckReport/QualityCheckReport/list',
    method: 'get',
    params: query
  })
}

// 查询施工质量上报详细
export function getQualityCheckReport(checkId) {
  return request({
    url: '/QualityCheckReport/QualityCheckReport/' + checkId,
    method: 'get'
  })
}

// 新增施工质量上报
export function addQualityCheckReport(data) {
  return request({
    url: '/QualityCheckReport/QualityCheckReport',
    method: 'post',
    data: data
  })
}

// 修改施工质量上报
export function updateQualityCheckReport(data) {
  return request({
    url: '/QualityCheckReport/QualityCheckReport',
    method: 'put',
    data: data
  })
}

// 删除施工质量上报
export function delQualityCheckReport(checkId) {
  return request({
    url: '/QualityCheckReport/QualityCheckReport/' + checkId,
    method: 'delete'
  })
}

// 获取WBS列表
export function getWbsList() {
  return request({
    url: '/task/task/queryWbsList',
    method: 'get'
  })
}


// //详情
// export function  viewRecords(){
//   return request({
//     url: '/CheckViews/CheckViews/' + wbs,
//     method: 'get'
//   })
// }


