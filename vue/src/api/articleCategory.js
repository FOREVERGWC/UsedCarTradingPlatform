import request from '@/utils/request';

/**
 * 添加、修改文章类别
 * @param data 文章类别
 * @returns {*} 结果
 */
export const saveArticleCategory = (data) => {
    return request({
        url: '/articleCategory', method: 'POST', data: data
    })
}

/**
 * 删除文章类别
 * @param ids ID列表
 * @returns {*} 结果
 */
export const removeArticleCategoryBatchByIds = (ids) => {
    return request({
        url: `/articleCategory/${ids}`, method: 'DELETE'
    })
}

/**
 * 查询文章类别列表
 * @param params 文章类别
 * @returns {*} 结果
 */
export const getArticleCategoryList = (params) => {
    return request({
        url: '/articleCategory/list', method: 'GET', params: params
    })
}

/**
 * 查询文章类别树
 * @param params 文章类别
 * @returns {*} 结果
 */
export const getArticleCategoryTree = (params) => {
    return request({
        url: '/articleCategory/tree', method: 'GET', params: params
    })
}

/**
 * 查询文章类别分页
 * @param params 文章类别
 * @returns {*} 结果
 */
export const getArticleCategoryPage = (params) => {
    return request({
        url: '/articleCategory/page', method: 'GET', params: params
    })
}

/**
 * 查询文章类别
 * @param params 文章类别
 * @returns {*} 结果
 */
export const getArticleCategoryOne = (params) => {
    return request({
        url: '/articleCategory', method: 'GET', params: params
    })
}
