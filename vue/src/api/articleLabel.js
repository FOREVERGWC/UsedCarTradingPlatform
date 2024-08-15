import request from '@/utils/request';

/**
 * 添加、修改文章标签
 * @param data 文章标签
 * @returns {*} 结果
 */
export const saveArticleLabel = (data) => {
    return request({
        url: '/articleLabel', method: 'POST', data: data
    })
}

/**
 * 删除文章标签
 * @param ids ID列表
 * @returns {*} 结果
 */
export const removeArticleLabelBatchByIds = (ids) => {
    return request({
        url: `/articleLabel/${ids}`, method: 'DELETE'
    })
}

/**
 * 查询文章标签列表
 * @param params 文章标签
 * @returns {*} 结果
 */
export const getArticleLabelList = (params) => {
    return request({
        url: '/articleLabel/list', method: 'GET', params: params
    })
}

/**
 * 查询文章标签分页
 * @param params 文章标签
 * @returns {*} 结果
 */
export const getArticleLabelPage = (params) => {
    return request({
        url: '/articleLabel/page', method: 'GET', params: params
    })
}

/**
 * 查询文章标签
 * @param params 文章标签
 * @returns {*} 结果
 */
export const getArticleLabelOne = (params) => {
    return request({
        url: '/articleLabel', method: 'GET', params: params
    })
}
