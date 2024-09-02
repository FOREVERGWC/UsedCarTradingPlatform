import request from '@/utils/request';

/**
 * 添加、修改角色、菜单关系
 * @param data 角色、菜单关系
 * @returns {*} 结果
 */
export const saveRoleMenuLink = (data) => {
    return request({
        url: '/roleMenuLink', method: 'POST', data: data
    })
}

/**
 * 删除角色、菜单关系
 * @param ids ID列表
 * @returns {*} 结果
 */
export const removeRoleMenuLinkBatchByIds = (ids) => {
    return request({
        url: `/roleMenuLink/${ids}`, method: 'DELETE'
    })
}

/**
 * 查询角色、菜单关系列表
 * @param params 角色、菜单关系
 * @returns {*} 结果
 */
export const getRoleMenuLinkList = (params) => {
    return request({
        url: '/roleMenuLink/list', method: 'GET', params: params
    })
}

/**
 * 查询角色、菜单关系分页
 * @param params 角色、菜单关系
 * @returns {*} 结果
 */
export const getRoleMenuLinkPage = (params) => {
    return request({
        url: '/roleMenuLink/page', method: 'GET', params: params
    })
}

/**
 * 查询角色、菜单关系
 * @param params 角色、菜单关系
 * @returns {*} 结果
 */
export const getRoleMenuLinkOne = (params) => {
    return request({
        url: '/roleMenuLink', method: 'GET', params: params
    })
}
