import request from '@/utils/request';

/**
 * 上传文件
 * @param file 文件
 * @returns {*} 结果
 */
export const uploadFile = (file) => {
    const form = new FormData();
    form.append('file', file);

    return request({
        url: '/file/upload',
        method: 'POST',
        data: form,
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    });
};