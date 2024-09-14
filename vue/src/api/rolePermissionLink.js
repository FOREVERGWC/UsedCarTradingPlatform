import request from '@/utils/request';

/**
 * 添加、修改角色、权限关系
 * @param data 角色、权限关系
 * @returns {*} 结果
 */
export const saveRolePermissionLink = (data) => {
    return request({
        url: '/rolePermissionLink', method: 'POST', data: data
    })
}

/**
 * 删除角色、权限关系
 * @param ids ID列表
 * @returns {*} 结果
 */
export const removeRolePermissionLinkBatchByIds = (ids) => {
    return request({
        url: `/rolePermissionLink/${ids}`, method: 'DELETE'
    })
}

/**
 * 查询角色、权限关系列表
 * @param params 角色、权限关系
 * @returns {*} 结果
 */
export const getRolePermissionLinkList = (params) => {
    return request({
        url: '/rolePermissionLink/list', method: 'GET', params: params
    })
}

/**
 * 查询角色、权限关系分页
 * @param params 角色、权限关系
 * @returns {*} 结果
 */
export const getRolePermissionLinkPage = (params) => {
    return request({
        url: '/rolePermissionLink/page', method: 'GET', params: params
    })
}

/**
 * 查询角色、权限关系
 * @param params 角色、权限关系
 * @returns {*} 结果
 */
export const getRolePermissionLinkOne = (params) => {
    return request({
        url: '/rolePermissionLink', method: 'GET', params: params
    })
}
