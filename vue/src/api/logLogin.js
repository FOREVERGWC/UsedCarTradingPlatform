import request from '@/utils/request';

/**
 * 添加、修改登录日志
 * @param data 登录日志
 * @returns {*} 结果
 */
export const saveLogLogin = (data) => {
    return request({
        url: '/logLogin', method: 'POST', data: data
    })
}

/**
 * 删除登录日志
 * @param ids ID列表
 * @returns {*} 结果
 */
export const removeLogLoginBatchByIds = (ids) => {
    return request({
        url: `/logLogin/${ids}`, method: 'DELETE'
    })
}

/**
 * 查询登录日志列表
 * @param params 登录日志
 * @returns {*} 结果
 */
export const getLogLoginList = (params) => {
    return request({
        url: '/logLogin/list', method: 'GET', params: params
    })
}

/**
 * 查询登录日志分页
 * @param params 登录日志
 * @returns {*} 结果
 */
export const getLogLoginPage = (params) => {
    return request({
        url: '/logLogin/page', method: 'GET', params: params
    })
}

/**
 * 查询登录日志
 * @param params 登录日志
 * @returns {*} 结果
 */
export const getLogLoginOne = (params) => {
    return request({
        url: '/logLogin', method: 'GET', params: params
    })
}
