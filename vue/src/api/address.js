import request from '@/utils/request';

/**
 * 添加、修改用户地址
 * @param data 用户地址
 * @returns {*} 结果
 */
export const saveAddress = (data) => {
    return request({
        url: '/address', method: 'POST', data: data
    })
}

/**
 * 删除用户地址
 * @param ids ID列表
 * @returns {*} 结果
 */
export const removeAddressBatchByIds = (ids) => {
    return request({
        url: `/address/${ids}`, method: 'DELETE'
    })
}

/**
 * 查询用户地址列表
 * @param params 用户地址
 * @returns {*} 结果
 */
export const getAddressList = (params) => {
    return request({
        url: '/address/list', method: 'GET', params: params
    })
}

/**
 * 查询用户地址分页
 * @param params 用户地址
 * @returns {*} 结果
 */
export const getAddressPage = (params) => {
    return request({
        url: '/address/page', method: 'GET', params: params
    })
}

/**
 * 查询用户地址
 * @param params 用户地址
 * @returns {*} 结果
 */
export const getAddressOne = (params) => {
    return request({
        url: '/address', method: 'GET', params: params
    })
}
