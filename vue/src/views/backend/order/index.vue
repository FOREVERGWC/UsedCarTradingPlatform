<template>
  <div>
    <el-row>
      <el-col :span="24">
        <el-card>
          <el-row :gutter="20">
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-select v-model="queryParams.carId" clearable filterable placeholder="请选择车辆">
                <el-option v-for="item in carList" :key="item.id" :label="item.name" :value="item.id"/>
              </el-select>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-select v-model="queryParams.carAuditeId" clearable filterable placeholder="请选择审核">
                <el-option v-for="item in carAuditeList" :key="item.id" :label="item.name" :value="item.id"/>
              </el-select>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-select v-model="queryParams.sellId" clearable filterable placeholder="请选择卖方">
                <el-option v-for="item in sellList" :key="item.id" :label="item.name" :value="item.id"/>
              </el-select>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-select v-model="queryParams.buyId" clearable filterable placeholder="请选择买方">
                <el-option v-for="item in buyList" :key="item.id" :label="item.name" :value="item.id"/>
              </el-select>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-select v-model="queryParams.addressId" clearable filterable placeholder="请选择送货地址">
                <el-option v-for="item in addressList" :key="item.id" :label="item.name" :value="item.id"/>
              </el-select>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-date-picker v-model="queryParams.payTime" clearable
                              placeholder="请选择付款时间" type="date"
                              value-format="YYYY-MM-DD HH:mm:ss"/>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-input v-model="queryParams.payPrice" clearable placeholder="请输入付款金额"/>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-input v-model="queryParams.payStatus" clearable placeholder="请输入付款状态(0未付款、1已付款)"/>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-date-picker v-model="queryParams.refundTime" clearable
                              placeholder="请选择退款时间" type="date"
                              value-format="YYYY-MM-DD HH:mm:ss"/>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-input v-model="queryParams.refundReason" clearable placeholder="请输入退款原因"/>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-input v-model="queryParams.refundStatus" clearable placeholder="请输入退款状态(0未退款、1已退款)"/>
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
              <vue3-json-excel :fields="orderFields" :json-data="orderList" name='订单列表.xls'>
                <el-button icon="Download" plain>导出</el-button>
              </vue3-json-excel>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-card>
      <el-table v-loading="loading" :cell-style="{ textAlign: 'center' }" :data="orderList"
                :header-cell-style="{ textAlign: 'center' }" stripe
                @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"/>
        <el-table-column label="序号" type="index" width="70"/>
        <el-table-column label="车辆ID" prop="carId"/>
        <el-table-column label="审核ID" prop="carAuditeId"/>
        <el-table-column label="卖方ID" prop="sellId"/>
        <el-table-column label="买方ID" prop="buyId"/>
        <el-table-column label="送货地址ID" prop="addressId"/>
        <el-table-column label="付款时间" prop="payTime"/>
        <el-table-column label="付款金额" prop="payPrice"/>
        <el-table-column label="付款状态" prop="payStatus"/>
        <el-table-column label="退款时间" prop="refundTime"/>
        <el-table-column label="退款原因" prop="refundReason"/>
        <el-table-column label="退款状态" prop="refundStatus"/>
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
        <el-form-item label="车辆" prop="carId">
          <el-select v-model="form.data.carId" clearable filterable placeholder="请选择车辆">
            <el-option v-for="item in carList" :key="item.id" :label="item.name" :value="item.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="审核" prop="carAuditeId">
          <el-select v-model="form.data.carAuditeId" clearable filterable placeholder="请选择审核">
            <el-option v-for="item in carAuditeList" :key="item.id" :label="item.name" :value="item.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="卖方" prop="sellId">
          <el-select v-model="form.data.sellId" clearable filterable placeholder="请选择卖方">
            <el-option v-for="item in sellList" :key="item.id" :label="item.name" :value="item.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="买方" prop="buyId">
          <el-select v-model="form.data.buyId" clearable filterable placeholder="请选择买方">
            <el-option v-for="item in buyList" :key="item.id" :label="item.name" :value="item.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="送货地址" prop="addressId">
          <el-select v-model="form.data.addressId" clearable filterable placeholder="请选择送货地址">
            <el-option v-for="item in addressList" :key="item.id" :label="item.name" :value="item.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="付款时间" prop="payTime">
          <el-date-picker v-model="form.data.payTime" placeholder="请选择付款时间"
                          type="date"
                          value-format="YYYY-MM-DD HH:mm:ss"/>
        </el-form-item>
        <el-form-item label="付款金额" prop="payPrice">
          <el-input v-model="form.data.payPrice" autocomplete="new"/>
        </el-form-item>
        <el-form-item label="付款状态(0未付款、1已付款)" prop="payStatus">
          <el-input v-model="form.data.payStatus" autocomplete="new"/>
        </el-form-item>
        <el-form-item label="退款时间" prop="refundTime">
          <el-date-picker v-model="form.data.refundTime" placeholder="请选择退款时间"
                          type="date"
                          value-format="YYYY-MM-DD HH:mm:ss"/>
        </el-form-item>
        <el-form-item label="退款原因" prop="refundReason">
          <el-input v-model="form.data.refundReason" autocomplete="new"/>
        </el-form-item>
        <el-form-item label="退款状态(0未退款、1已退款)" prop="refundStatus">
          <el-input v-model="form.data.refundStatus" autocomplete="new"/>
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
import {getOrderOne, getOrderPage, removeOrderBatchByIds, saveOrder} from '@/api/order'
import {getCarList} from '@/api/car'
import {getCarAuditeList} from '@/api/carAudite'
import {getSellList} from '@/api/sell'
import {getBuyList} from '@/api/buy'
import {getAddressList} from '@/api/address'
import {ElMessage} from "element-plus"

const loading = ref(true)
const queryParams = reactive({
  pageNo: 1,
  pageSize: 20,
  carId: null,
  carAuditeId: null,
  sellId: null,
  buyId: null,
  addressId: null,
  payTime: '',
  payPrice: null,
  payStatus: null,
  refundTime: '',
  refundReason: '',
  refundStatus: null
})
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const carList = ref([])
const carAuditeList = ref([])
const sellList = ref([])
const buyList = ref([])
const addressList = ref([])
const orderList = ref([])
const total = ref(0)
const orderFields = {
  '序号': {
    field: 'id',
    callback: (id) => {
      const index = orderList.value.findIndex(item => item.id === id)
      return index + 1
    }
  },
  '主键ID': 'id',
  '车辆ID': 'carId',
  '审核ID': 'carAuditeId',
  '卖方ID': 'sellId',
  '买方ID': 'buyId',
  '送货地址ID': 'addressId',
  '付款时间': 'payTime',
  '付款金额': 'payPrice',
  '付款状态': 'payStatus',
  '退款时间': 'refundTime',
  '退款原因': 'refundReason',
  '退款状态': 'refundStatus'
}
const form = ref({
  visible: false,
  title: '',
  data: {}
})
const formRef = ref(null)
const rules = {
  carId: [{required: true, message: '请输入车辆ID', trigger: 'blur'}],
  carAuditeId: [{required: true, message: '请输入审核ID', trigger: 'blur'}],
  sellId: [{required: true, message: '请输入卖方ID', trigger: 'blur'}],
  buyId: [{required: true, message: '请输入买方ID', trigger: 'blur'}],
  addressId: [{required: true, message: '请输入送货地址ID', trigger: 'blur'}],
  payTime: [{required: true, message: '请选择付款时间', trigger: 'change'}],
  payPrice: [{required: true, message: '请输入付款金额', trigger: 'blur'}],
  payStatus: [{required: true, message: '请输入付款状态(0未付款、1已付款)', trigger: 'blur'}],
  refundTime: [{required: true, message: '请选择退款时间', trigger: 'change'}],
  refundReason: [{required: true, message: '请输入退款原因', trigger: 'blur'}],
  refundStatus: [{required: true, message: '请输入退款状态(0未退款、1已退款)', trigger: 'blur'}]
}

const getPage = () => {
  loading.value = true
  getCarList({}).then(res => {
    carList.value = res.data || []
  })
  getCarAuditeList({}).then(res => {
    carAuditeList.value = res.data || []
  })
  getSellList({}).then(res => {
    sellList.value = res.data || []
  })
  getBuyList({}).then(res => {
    buyList.value = res.data || []
  })
  getAddressList({}).then(res => {
    addressList.value = res.data || []
  })
  getOrderPage(queryParams).then(res => {
    orderList.value = res.data?.records || []
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
    title: '添加订单',
    data: {
      carId: null,
      carAuditeId: null,
      sellId: null,
      buyId: null,
      addressId: null,
      payTime: '',
      payPrice: null,
      payStatus: null,
      refundTime: '',
      refundReason: '',
      refundStatus: null,
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
  getOrderOne(params).then(res => {
    if (res.code !== 200) return
    form.value = {
      visible: true,
      title: '编辑订单',
      data: {
        ...res.data
      }
    }
  })
}

const handleSave = () => {
  formRef.value.validate(valid => {
    if (!valid) return
    saveOrder(form.value.data).then(res => {
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
  removeOrderBatchByIds(params).then(res => {
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
  queryParams.carId = null
  queryParams.carAuditeId = null
  queryParams.sellId = null
  queryParams.buyId = null
  queryParams.addressId = null
  queryParams.payPrice = null
  queryParams.payStatus = null
  queryParams.refundReason = ''
  queryParams.refundStatus = null
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
