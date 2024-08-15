import request from '@/utils/request';

/**
 * 添加、修改聊天
 * @param data 聊天
 * @returns {*} 结果
 */
export const saveChat = (data) => {
    return request({
        url: '/chat', method: 'POST', data: data
    })
}

/**
 * 删除聊天
 * @param ids ID列表
 * @returns {*} 结果
 */
export const removeChatBatchByIds = (ids) => {
    return request({
        url: `/chat/${ids}`, method: 'DELETE'
    })
}

/**
 * 查询聊天列表
 * @param params 聊天
 * @returns {*} 结果
 */
export const getChatList = (params) => {
    return request({
        url: '/chat/list', method: 'GET', params: params
    })
}

/**
 * 查询聊天分页
 * @param params 聊天
 * @returns {*} 结果
 */
export const getChatPage = (params) => {
    return request({
        url: '/chat/page', method: 'GET', params: params
    })
}

/**
 * 查询聊天
 * @param params 聊天
 * @returns {*} 结果
 */
export const getChatOne = (params) => {
    return request({
        url: '/chat', method: 'GET', params: params
    })
}
