import _ from 'lodash-es'

export const statusList = [
    {label: '禁用', value: '0'},
    {label: '正常', value: '1'},
]

export const formatDate = (datetime) => {
    return _.split(datetime, ' ')[0]
}