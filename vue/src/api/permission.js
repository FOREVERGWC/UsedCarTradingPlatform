import request from '@/utils/request';

/**
 * 添加、修改权限
 * @param data 权限
 * @returns {*} 结果
 */
export const savePermission = (data) => {
    return request({
        url: '/permission', method: 'POST', data: data
    })
}

/**
 * 删除权限
 * @param ids ID列表
 * @returns {*} 结果
 */
export const removePermissionBatchByIds = (ids) => {
    return request({
        url: `/permission/${ids}`, method: 'DELETE'
    })
}

/**
 * 查询权限列表
 * @param params 权限
 * @returns {*} 结果
 */
export const getPermissionList = (params) => {
    return request({
        url: '/permission/list', method: 'GET', params: params
    })
}

/**
 * 查询权限分页
 * @param params 权限
 * @returns {*} 结果
 */
export const getPermissionPage = (params) => {
    return request({
        url: '/permission/page', method: 'GET', params: params
    })
}

/**
 * 查询权限
 * @param params 权限
 * @returns {*} 结果
 */
export const getPermissionOne = (params) => {
    return request({
        url: '/permission', method: 'GET', params: params
    })
}
