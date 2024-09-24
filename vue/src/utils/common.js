import _ from 'lodash-es'
import {tansParams} from "@/utils/request.js";
import useUserStore from "@/store/modules/user.js";

export const statusList = [
    {label: '禁用', value: '0'},
    {label: '正常', value: '1'}
]

export const genderList = [
    {label: '女', value: '0'},
    {label: '男', value: '1'},
    {label: '未知', value: '2'}
]

export const formatDate = (datetime) => {
    return _.split(datetime, ' ')[0]
}

export const formatTimestamp = (timestamp) => {
    const date = new Date(Number(timestamp));
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');
    const milliseconds = String(date.getMilliseconds()).padStart(3, '0');
    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}.${milliseconds}`;
};

/**
 * 获取需要展开的节点ID
 * @param data 数据
 * @returns {*[]} 结果
 */
export const getExpandedIds = (data) => {
    const ids = []
    const stack = [...data]

    while (stack.length > 0) {
        const node = stack.pop()
        if (node.children && node.children.length > 0) {
            ids.push(node.id)
            for (let i = node.children.length - 1; i >= 0; i--) {
                stack.push(node.children[i]);
            }
        }
    }

    return ids
}

/**
 * 下载文件
 * @param url 下载地址
 * @param params 参数
 */
export const downloadFile = (url, params = {}) => {
    const userStore = useUserStore()
    window.open(`${import.meta.env.VITE_APP_BASE_API}${url}?${tansParams(params)}&token=${userStore.token}`, '_target')
};
