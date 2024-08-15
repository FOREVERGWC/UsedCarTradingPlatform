import request from '@/utils/request';

/**
 * 添加、修改文章、文章标签关系
 * @param data 文章、文章标签关系
 * @returns {*} 结果
 */
export const saveArticleLabelLink = (data) => {
    return request({
        url: '/articleLabelLink', method: 'POST', data: data
    })
}

/**
 * 删除文章、文章标签关系
 * @param ids ID列表
 * @returns {*} 结果
 */
export const removeArticleLabelLinkBatchByIds = (ids) => {
    return request({
        url: `/articleLabelLink/${ids}`, method: 'DELETE'
    })
}

/**
 * 查询文章、文章标签关系列表
 * @param params 文章、文章标签关系
 * @returns {*} 结果
 */
export const getArticleLabelLinkList = (params) => {
    return request({
        url: '/articleLabelLink/list', method: 'GET', params: params
    })
}

/**
 * 查询文章、文章标签关系分页
 * @param params 文章、文章标签关系
 * @returns {*} 结果
 */
export const getArticleLabelLinkPage = (params) => {
    return request({
        url: '/articleLabelLink/page', method: 'GET', params: params
    })
}

/**
 * 查询文章、文章标签关系
 * @param params 文章、文章标签关系
 * @returns {*} 结果
 */
export const getArticleLabelLinkOne = (params) => {
    return request({
        url: '/articleLabelLink', method: 'GET', params: params
    })
}
