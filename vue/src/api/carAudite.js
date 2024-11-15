import request from '@/utils/request';

/**
 * 添加、修改二手车审核
 * @param data 二手车审核
 * @returns {*} 结果
 */
export const saveCarAudite = (data) => {
    return request({
        url: '/car/audite', method: 'POST', data: data
    })
}

/**
 * 删除二手车审核
 * @param ids ID列表
 * @returns {*} 结果
 */
export const removeCarAuditeBatchByIds = (ids) => {
    return request({
        url: `/car/audite/${ids}`, method: 'DELETE'
    })
}

/**
 * 查询二手车审核列表
 * @param params 二手车审核
 * @returns {*} 结果
 */
export const getCarAuditeList = (params) => {
    return request({
        url: '/car/audite/list', method: 'GET', params: params
    })
}

/**
 * 查询二手车审核分页
 * @param params 二手车审核
 * @returns {*} 结果
 */
export const getCarAuditePage = (params) => {
    return request({
        url: '/car/audite/page', method: 'GET', params: params
    })
}

/**
 * 查询二手车审核
 * @param params 二手车审核
 * @returns {*} 结果
 */
export const getCarAuditeOne = (params) => {
    return request({
        url: '/car/audite', method: 'GET', params: params
    })
}
