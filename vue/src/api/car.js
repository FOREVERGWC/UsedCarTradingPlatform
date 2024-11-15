import request from '@/utils/request';

/**
 * 添加、修改二手车
 * @param data 二手车
 * @returns {*} 结果
 */
export const saveCar = (data) => {
    return request({
        url: '/car', method: 'POST', data: data
    })
}

/**
 * 删除二手车
 * @param ids ID列表
 * @returns {*} 结果
 */
export const removeCarBatchByIds = (ids) => {
    return request({
        url: `/car/${ids}`, method: 'DELETE'
    })
}

/**
 * 查询二手车列表
 * @param params 二手车
 * @returns {*} 结果
 */
export const getCarList = (params) => {
    return request({
        url: '/car/list', method: 'GET', params: params
    })
}

/**
 * 查询二手车分页
 * @param params 二手车
 * @returns {*} 结果
 */
export const getCarPage = (params) => {
    return request({
        url: '/car/page', method: 'GET', params: params
    })
}

/**
 * 查询二手车
 * @param params 二手车
 * @returns {*} 结果
 */
export const getCarOne = (params) => {
    return request({
        url: '/car', method: 'GET', params: params
    })
}
