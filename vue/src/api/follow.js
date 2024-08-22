import request from '@/utils/request';

/**
 * 添加、修改关注
 * @param data 关注
 * @returns {*} 结果
 */
export const saveFollow = (data) => {
    return request({
        url: '/follow', method: 'POST', data: data
    })
}

/**
 * 删除关注
 * @param ids ID列表
 * @returns {*} 结果
 */
export const removeFollowBatchByIds = (ids) => {
    return request({
        url: `/follow/${ids}`, method: 'DELETE'
    })
}

/**
 * 查询关注列表
 * @param params 关注
 * @returns {*} 结果
 */
export const getFollowList = (params) => {
    return request({
        url: '/follow/list', method: 'GET', params: params
    })
}

/**
 * 查询关注分页
 * @param params 关注
 * @returns {*} 结果
 */
export const getFollowPage = (params) => {
    return request({
        url: '/follow/page', method: 'GET', params: params
    })
}

/**
 * 查询关注
 * @param params 关注
 * @returns {*} 结果
 */
export const getFollowOne = (params) => {
    return request({
        url: '/follow', method: 'GET', params: params
    })
}

/**
 * 查询粉丝数量和关注数量
 * @param userId 用户ID
 * @returns {*} 结果
 */
export const getFollowInfo = (userId) => {
    return request({
        url: `/follow/info/${userId}`, method: 'GET'
    })
}

/**
 * 关注或取关用户
 * @param userId 用户ID
 * @returns {*} 结果
 */
export const doFollowTo = (userId) => {
    return request({
        url: `/follow/to/${userId}`, method: 'POST'
    })
}