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
 * 退出登录
 * @returns {*} 结果
 */
export const logout = () => {
    return request({
        url: '/logout', method: 'POST'
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
 * 获取验证码图片
 * @returns {*} 结果
 */
export const getCaptcha = () => {
    return request({
        url: '/captcha', method: 'GET'
    })
}

/**
 * 根据邮箱发送注册验证码邮件
 * @param data 注册邮箱
 * @returns {*} 结果
 */
export const sendRegisterCodeByEmail = (data) => {
    return request({
        url: '/register/code', method: 'POST', data: data
    })
}

/**
 * 根据邮箱发送改密验证码邮件
 * @param data 注册邮箱
 * @returns {*} 结果
 */
export const sendResetCodeByEmail = (data) => {
    return request({
        url: '/reset/code', method: 'POST', data: data
    })
}

/**
 * 重置密码
 * @param data 密码信息
 * @returns {*} 结果
 */
export const resetPassword = (data) => {
    return request({
        url: '/password/reset', method: 'PUT', data: data
    })
}