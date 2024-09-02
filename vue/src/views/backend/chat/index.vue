<template>
  <div>
    <el-row>
      <el-col :span="24">
        <el-card>
          <el-row :gutter="20">
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-select v-model='queryParams.senderId' clearable filterable placeholder='请选择发送者'>
                <el-option v-for='item in senderList' :key='item.id' :label='item.name' :value='item.id'/>
              </el-select>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-select v-model='queryParams.receiverId' clearable filterable placeholder='请选择接收者'>
                <el-option v-for='item in receiverList' :key='item.id' :label='item.name' :value='item.id'/>
              </el-select>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-input v-model="queryParams.message" clearable placeholder="请输入消息内容"/>
            </el-col>
            <el-col :lg="2" :md="2" :sm="12" :xl="2" :xs="12">
              <el-button icon="Search" plain type="info" @click="getPage">查询</el-button>
            </el-col>
            <el-col :lg="2" :md="2" :sm="12" :xl="2" :xs="12">
              <el-button icon="Refresh" plain type="warning" @click="resetQuery">
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
              <vue3-json-excel :fields="chatFields" :json-data="chatList" name='聊天列表.xls'>
                <el-button icon="Download" plain>导出</el-button>
              </vue3-json-excel>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-card>
      <el-table v-loading="loading" :cell-style="{ textAlign: 'center' }" :data="chatList"
                :header-cell-style="{ textAlign: 'center' }" stripe
                @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"/>
        <el-table-column label="序号" type="index" width="70"/>
        <el-table-column label="发送者">
          <template v-slot="{ row }">
            {{ row.sender.name || row.sender.username }}
          </template>
        </el-table-column>
        <el-table-column label="接收者">
          <template v-slot="{ row }">
            {{ row.receiver.name || row.receiver.username }}
          </template>
        </el-table-column>
        <el-table-column label="消息内容" prop="message"/>
        <el-table-column label="发送时间" prop="createTime"/>
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

    <el-dialog v-model="form.visible" :title="form.title" destroy-on-close width="40%">
      <el-form ref="formRef" :model="form.data" :rules="rules" label-width="80px">
        <el-form-item label="发送者" prop="senderId">
          <el-select v-model='form.data.senderId' clearable filterable placeholder='请选择发送者'>
            <el-option v-for='item in senderList' :key='item.id' :label='item.name || item.username' :value='item.id'/>
          </el-select>
        </el-form-item>
        <el-form-item label="接收者" prop="receiverId">
          <el-select v-model='form.data.receiverId' clearable filterable placeholder='请选择接收者'>
            <el-option v-for='item in receiverList' :key='item.id' :label='item.name || item.username'
                       :value='item.id'/>
          </el-select>
        </el-form-item>
        <el-form-item label="消息内容" prop="message">
          <el-input v-model="form.data.message" autocomplete="new"/>
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
import {getChatOne, getChatPage, removeChatBatchByIds, saveChat} from '@/api/chat'
import {getUserList} from '@/api/user'
import {ElMessage} from "element-plus"

const loading = ref(true)
const queryParams = reactive({
  pageNo: 1,
  pageSize: 20,
  senderId: null,
  receiverId: null,
  message: ''
})
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const senderList = ref([])
const receiverList = ref([])
const chatList = ref([])
const total = ref(0)
const chatFields = {
  '序号': {
    field: 'id',
    callback: (id) => {
      const index = chatList.value.findIndex(item => item.id === id)
      return index + 1
    }
  },
  '主键ID': 'id',
  '发送者ID': 'senderId',
  '接收者ID': 'receiverId',
  '消息内容': 'message'
}
const form = ref({
  visible: false,
  title: '',
  data: {}
})
const formRef = ref(null)
const rules = {
  senderId: [{required: true, message: '请输入发送者ID', trigger: 'blur'}],
  receiverId: [{required: true, message: '请输入接收者ID', trigger: 'blur'}],
  message: [{required: true, message: '请输入消息内容', trigger: 'blur'}]
}

const getPage = () => {
  loading.value = true
  getUserList({}).then(res => {
    senderList.value = res.data || []
  })
  getUserList({}).then(res => {
    receiverList.value = res.data || []
  })
  getChatPage(queryParams).then(res => {
    chatList.value = res.data?.records || []
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
    title: '添加聊天',
    data: {
      senderId: null,
      receiverId: null,
      message: '',
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
  getChatOne(params).then(res => {
    if (res.code !== 200) return
    form.value = {
      visible: true,
      title: '编辑聊天',
      data: {
        ...res.data
      }
    }
  })
}

const handleSave = () => {
  formRef.value.validate(valid => {
    if (!valid) return
    saveChat(form.value.data).then(res => {
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
  removeChatBatchByIds(params).then(res => {
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

const resetQuery = () => {
  queryParams.pageNo = 1
  queryParams.pageSize = 20
  queryParams.senderId = null
  queryParams.receiverId = null
  queryParams.message = ''
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

const getUrl = computed(() => (path) => {
  return import.meta.env.VITE_APP_BASE_API + path
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
