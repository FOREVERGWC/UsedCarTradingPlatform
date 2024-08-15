import request from "@/utils/request";

/**
 * PC端登录
 * @param data PC端登录请求体
 * @returns {*} 结果
 */
export const login = (data) => {
    return request({
        url: '/login', method: 'POST', data: data
    })
}

/**
 * 注册用户
 * @param data 用户信息
 * @returns {*} 结果
 */
export const register = (data) => {
    return request({
        url: '/register', method: 'POST', data: data
    })
}

/**
 * 修改密码
 * @param data 密码信息
 * @returns {*} 结果
 */
export const updatePassword = (data) => {
    return request({
        url: '/password/update', method: 'PUT', data: data
    })
}

/**
 * 获取当前用户信息
 * @returns {*} 结果
 */
export const getByToken = () => {
    return request({
        url: '/token', method: 'GET'
    })
}

/**
 * 根据邮箱发送注册验证码邮件
 * @param params 邮箱
 * @returns {*} 结果
 */
export const getRegisterCodeByEmail = (params) => {
    return request({
        url: '/register/email/code', method: 'GET', params: params
    })
}

/**
 * 根据邮箱发送找回验证码邮件
 * @param params 邮箱
 * @returns {*} 结果
 */
export const getRetrieveCodeByEmail = (params) => {
    return request({
        url: '/retrieve/email/code', method: 'GET', params: params
    })
}
