import request from '@/utils/request';

/**
 * 添加、修改文章
 * @param data 文章
 * @returns {*} 结果
 */
export const saveArticle = (data) => {
    return request({
        url: '/article', method: 'POST', data: data
    })
}

/**
 * 删除文章
 * @param ids ID列表
 * @returns {*} 结果
 */
export const removeArticleBatchByIds = (ids) => {
    return request({
        url: `/article/${ids}`, method: 'DELETE'
    })
}

/**
 * 查询文章列表
 * @param params 文章
 * @returns {*} 结果
 */
export const getArticleList = (params) => {
    return request({
        url: '/article/list', method: 'GET', params: params
    })
}

/**
 * 查询文章分页
 * @param params 文章
 * @returns {*} 结果
 */
export const getArticlePage = (params) => {
    return request({
        url: '/article/page', method: 'GET', params: params
    })
}

/**
 * 查询文章
 * @param params 文章
 * @returns {*} 结果
 */
export const getArticleOne = (params) => {
    return request({
        url: '/article', method: 'GET', params: params
    })
}
