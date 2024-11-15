<template>
  <div>
    <el-row>
      <el-col :span="24">
        <el-card>
          <el-row :gutter="20">
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-select v-model="queryParams.userId" clearable filterable placeholder="请选择用户">
                <el-option v-for="item in userList" :key="item.id" :label="item.name" :value="item.id"/>
              </el-select>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-input v-model="queryParams.info" clearable placeholder="请输入地址详情"/>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-select v-model="queryParams.delFlag" clearable filterable placeholder="请选择是否逻辑删除">
                <el-option v-for="item in delFlagList" :key="item.value" :label="item.label" :value="item.value"/>
              </el-select>
            </el-col>
            <el-col :lg="2" :md="2" :sm="12" :xl="2" :xs="12">
              <el-button icon="Search" plain type="info" @click="getPage">查询</el-button>
            </el-col>
            <el-col :lg="2" :md="2" :sm="12" :xl="2" :xs="12">
              <el-button icon="Refresh" plain type="warning" @click="handleReset">
                重置
              </el-button>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="24">
        <el-card>
          <el-row :gutter="20">
            <el-col :lg="2" :md="2" :sm="12" :xl="2" :xs="12">
              <el-button icon="Plus" plain type="primary" @click="showAdd">新增</el-button>
            </el-col>
            <el-col :lg="2" :md="2" :sm="12" :xl="2" :xs="12">
              <el-button :disabled="single" icon="Edit" plain type="success" @click="showEdit">
                修改
              </el-button>
            </el-col>
            <el-col :lg="2" :md="2" :sm="12" :xl="2" :xs="12">
              <el-popconfirm title="确认删除选中数据吗？" @confirm="handleDelete(null)">
                <template #reference>
                  <el-button :disabled="multiple" icon="Delete" plain type="danger">
                    删除
                  </el-button>
                </template>
              </el-popconfirm>
            </el-col>
            <el-col :lg="2" :md="2" :sm="12" :xl="2" :xs="12">
              <vue3-json-excel :fields="addressFields" :json-data="addressList" name='用户地址列表.xls'>
                <el-button icon="Download" plain>导出</el-button>
              </vue3-json-excel>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-card>
      <el-table v-loading="loading" :cell-style="{ textAlign: 'center' }" :data="addressList"
                :header-cell-style="{ textAlign: 'center' }" stripe
                @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"/>
        <el-table-column label="序号" type="index" width="70"/>
        <el-table-column label="用户ID" prop="userId"/>
        <el-table-column label="地址详情" prop="info"/>
        <el-table-column label="逻辑删除" prop="delFlag"/>
        <el-table-column label="操作" width="180">
          <template v-slot="{ row }">
            <el-button icon="Edit" plain type="primary" @click="showEdit(row)">编辑</el-button>
            <el-popconfirm title="确认删除该行吗？" @confirm="handleDelete(row.id)">
              <template #reference>
                <el-button icon="Delete" plain style="margin-left: 10px" type="danger">
                  删除
                </el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
          :current-page="queryParams.pageNo"
          :page-size="queryParams.pageSize"
          :page-sizes="[20, 30, 40, 50]"
          :total="total"
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange">
      </el-pagination>
    </el-card>

    <el-dialog :title="form.title" v-model="form.visible" destroy-on-close width="40%">
      <el-form ref="formRef" :model="form.data" :rules="rules" label-width="80px">
        <el-form-item label="用户" prop="userId">
          <el-select v-model="form.data.userId" clearable filterable placeholder="请选择用户">
            <el-option v-for="item in userList" :key="item.id" :label="item.name" :value="item.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="地址详情" prop="info">
          <el-input v-model="form.data.info" autocomplete="new"/>
        </el-form-item>
        <el-form-item label="逻辑删除" prop="delFlag">
          <el-select v-model="form.data.delFlag" clearable filterable placeholder="请选择是否逻辑删除">
            <el-option v-for="item in delFlagList" :key="item.value" :label="item.label" :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.data.remark" :rows="5" autocomplete="new" type="textarea"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="form.visible = false">取 消</el-button>
        <el-button type="primary" @click="handleSave">确 定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import {computed, nextTick, onMounted, reactive, ref, toRaw} from 'vue'
import {getAddressOne, getAddressPage, removeAddressBatchByIds, saveAddress} from '@/api/address'
import {getUserList} from '@/api/user'
import {ElMessage} from "element-plus"

const loading = ref(true)
const queryParams = reactive({
  pageNo: 1,
  pageSize: 20,
  userId: null,
  info: '',
  delFlag: null
})
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const userList = ref([])
const addressList = ref([])
const total = ref(0)
const addressFields = {
  '序号': {
    field: 'id',
    callback: (id) => {
      const index = addressList.value.findIndex(item => item.id === id)
      return index + 1
    }
  },
  '主键ID': 'id',
  '用户ID': 'userId',
  '地址详情': 'info',
  '逻辑删除': 'delFlag'
}
const delFlagList = [
  { label: '是', value: true },
  { label: '否', value: false }
]
const form = ref({
  visible: false,
  title: '',
  data: {}
})
const formRef = ref(null)
const rules = {
  userId: [{required: true, message: '请输入用户ID', trigger: 'blur'}],
  info: [{required: true, message: '请输入地址详情', trigger: 'blur'}],
  delFlag: [{required: true, message: '请输入逻辑删除', trigger: 'blur'}]
}

const getPage = () => {
  loading.value = true
  getUserList({}).then(res => {
    userList.value = res.data || []
  })
  getAddressPage(queryParams).then(res => {
    addressList.value = res.data?.records || []
    total.value = res.data?.total || 0
    loading.value = false
  })
}

const showAdd = () => {
  nextTick(() => {
    if (!formRef.value) return
    formRef.value.resetFields()
  })
  form.value = {
    visible: true,
    title: '添加用户地址',
    data: {
      userId: null,
      info: '',
      delFlag: null,
      remark: ''
    }
  }
}

const showEdit = (row) => {
  nextTick(() => {
    if (!formRef.value) return
    formRef.value.resetFields()
  })
  const params = {id: row.id || ids.value[0]}
  getAddressOne(params).then(res => {
    if (res.code !== 200) return
    form.value = {
      visible: true,
      title: '编辑用户地址',
      data: {
        ...res.data
      }
    }
  })
}

const handleSave = () => {
  formRef.value.validate(valid => {
    if (!valid) return
    saveAddress(form.value.data).then(res => {
      if (res.code !== 200) {
        ElMessage.error(res.msg)
        return
      }
      ElMessage.success('保存成功！')
      form.value.visible = false
    }).finally(() => {
      getPage()
    })
  })
}

const handleDelete = (id) => {
  const params = id || ids.value
  removeAddressBatchByIds(params).then(res => {
    if (res.code !== 200) {
      ElMessage.error(res.msg)
      return
    }
    ElMessage.success('删除成功！')
  }).finally(() => {
    getPage()
  })
}

const handleSelectionChange = (selection) => {
  ids.value = selection.map(item => toRaw(item).id)
  single.value = selection.length !== 1
  multiple.value = !selection.length
}

const handleReset = () => {
  queryParams.pageNo = 1
  queryParams.pageSize = 20
  queryParams.userId = null
  queryParams.info = ''
  queryParams.delFlag = null
  getPage()
}

const handleSizeChange = (val) => {
  queryParams.pageSize = val
  getPage()
}

const handleCurrentChange = (val) => {
  queryParams.pageNo = val
  getPage()
}

onMounted(() => {
  getPage()
})
</script>

<style lang="scss" scoped>
.el-select, :deep(.el-date-editor) {
  width: 100%;
}

.el-col {
  margin-bottom: 8px;

  .el-button {
    width: 100%;
  }
}

.el-pagination {
  margin-top: 8px;
}
</style>
