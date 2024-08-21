import request from '@/utils/request';

/**
 * 添加、修改角色
 * @param data 角色
 * @returns {*} 结果
 */
export const saveRole = (data) => {
    return request({
        url: '/role', method: 'POST', data: data
    })
}

/**
 * 删除角色
 * @param ids ID列表
 * @returns {*} 结果
 */
export const removeRoleBatchByIds = (ids) => {
    return request({
        url: `/role/${ids}`, method: 'DELETE'
    })
}

/**
 * 查询角色列表
 * @param params 角色
 * @returns {*} 结果
 */
export const getRoleList = (params) => {
    return request({
        url: '/role/list', method: 'GET', params: params
    })
}

/**
 * 查询角色分页
 * @param params 角色
 * @returns {*} 结果
 */
export const getRolePage = (params) => {
    return request({
        url: '/role/page', method: 'GET', params: params
    })
}

/**
 * 查询角色
 * @param params 角色
 * @returns {*} 结果
 */
export const getRoleOne = (params) => {
    return request({
        url: '/role', method: 'GET', params: params
    })
}
