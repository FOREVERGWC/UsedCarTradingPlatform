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
              <el-input v-model="queryParams.brand" clearable placeholder="请输入品牌"/>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-input v-model="queryParams.model" clearable placeholder="请输入型号"/>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-input v-model="queryParams.year" clearable placeholder="请输入生产年份"/>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-input v-model="queryParams.mileage" clearable placeholder="请输入行驶里程"/>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-input v-model="queryParams.price" clearable placeholder="请输入价格"/>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-input v-model="queryParams.color" clearable placeholder="请输入颜色"/>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-input v-model="queryParams.fuelType" clearable placeholder="请输入燃料类型(1汽油、2柴油、3电动、4混动、5其他)"/>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-input v-model="queryParams.transmissionType" clearable placeholder="请输入变速器类型(1自动档、2手动档)"/>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-input v-model="queryParams.condition" clearable placeholder="请输入车况(1九成新女生自用、2良好、3完好、4轻微刮擦、5叙利亚成色)"/>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-input v-model="queryParams.licenseDate" clearable placeholder="请输入上牌日期"/>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-select v-model="queryParams.hasSold" clearable filterable placeholder="请选择是否是否售出">
                <el-option v-for="item in hasSoldList" :key="item.value" :label="item.label" :value="item.value"/>
              </el-select>
            </el-col>
            <el-col :lg="4" :md="4" :sm="12" :xl="4" :xs="12">
              <el-select v-model="queryParams.hasCheck" clearable filterable placeholder="请选择是否是否验车">
                <el-option v-for="item in hasCheckList" :key="item.value" :label="item.label" :value="item.value"/>
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
              <vue3-json-excel :fields="carFields" :json-data="carList" name='二手车列表.xls'>
                <el-button icon="Download" plain>导出</el-button>
              </vue3-json-excel>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-card>
      <el-table v-loading="loading" :cell-style="{ textAlign: 'center' }" :data="carList"
                :header-cell-style="{ textAlign: 'center' }" stripe
                @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"/>
        <el-table-column label="序号" type="index" width="70"/>
        <el-table-column label="用户ID" prop="userId"/>
        <el-table-column label="品牌" prop="brand"/>
        <el-table-column label="型号" prop="model"/>
        <el-table-column label="生产年份" prop="year"/>
        <el-table-column label="行驶里程" prop="mileage"/>
        <el-table-column label="价格" prop="price"/>
        <el-table-column label="颜色" prop="color"/>
        <el-table-column label="燃料类型" prop="fuelType"/>
        <el-table-column label="变速器类型" prop="transmissionType"/>
        <el-table-column label="车况" prop="condition"/>
        <el-table-column label="上牌日期" prop="licenseDate"/>
        <el-table-column label="是否售出" prop="hasSold"/>
        <el-table-column label="是否验车" prop="hasCheck"/>
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
        <el-form-item label="品牌" prop="brand">
          <el-input v-model="form.data.brand" autocomplete="new"/>
        </el-form-item>
        <el-form-item label="型号" prop="model">
          <el-input v-model="form.data.model" autocomplete="new"/>
        </el-form-item>
        <el-form-item label="生产年份" prop="year">
          <el-input v-model="form.data.year" autocomplete="new"/>
        </el-form-item>
        <el-form-item label="行驶里程" prop="mileage">
          <el-input v-model="form.data.mileage" autocomplete="new"/>
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="form.data.price" autocomplete="new"/>
        </el-form-item>
        <el-form-item label="颜色" prop="color">
          <el-input v-model="form.data.color" autocomplete="new"/>
        </el-form-item>
        <el-form-item label="燃料类型(1汽油、2柴油、3电动、4混动、5其他)" prop="fuelType">
          <el-input v-model="form.data.fuelType" autocomplete="new"/>
        </el-form-item>
        <el-form-item label="变速器类型(1自动档、2手动档)" prop="transmissionType">
          <el-input v-model="form.data.transmissionType" autocomplete="new"/>
        </el-form-item>
        <el-form-item label="车况(1九成新女生自用、2良好、3完好、4轻微刮擦、5叙利亚成色)" prop="condition">
          <el-input v-model="form.data.condition" autocomplete="new"/>
        </el-form-item>
        <el-form-item label="是否售出" prop="hasSold">
          <el-select v-model="form.data.hasSold" clearable filterable placeholder="请选择是否是否售出">
            <el-option v-for="item in hasSoldList" :key="item.value" :label="item.label" :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="是否验车" prop="hasCheck">
          <el-select v-model="form.data.hasCheck" clearable filterable placeholder="请选择是否是否验车">
            <el-option v-for="item in hasCheckList" :key="item.value" :label="item.label" :value="item.value"/>
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
import {getCarOne, getCarPage, removeCarBatchByIds, saveCar} from '@/api/car'
import {getUserList} from '@/api/user'
import {ElMessage} from "element-plus"

const loading = ref(true)
const queryParams = reactive({
  pageNo: 1,
  pageSize: 20,
  userId: null,
  brand: '',
  model: '',
  year: null,
  mileage: null,
  price: null,
  color: '',
  fuelType: null,
  transmissionType: null,
  condition: '',
  licenseDate: '',
  hasSold: null,
  hasCheck: null
})
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const userList = ref([])
const carList = ref([])
const total = ref(0)
const carFields = {
  '序号': {
    field: 'id',
    callback: (id) => {
      const index = carList.value.findIndex(item => item.id === id)
      return index + 1
    }
  },
  '主键ID': 'id',
  '用户ID': 'userId',
  '品牌': 'brand',
  '型号': 'model',
  '生产年份': 'year',
  '行驶里程': 'mileage',
  '价格': 'price',
  '颜色': 'color',
  '燃料类型': 'fuelType',
  '变速器类型': 'transmissionType',
  '车况': 'condition',
  '上牌日期': 'licenseDate',
  '是否售出': 'hasSold',
  '是否验车': 'hasCheck'
}
const hasSoldList = [
  { label: '是', value: true },
  { label: '否', value: false }
]
const hasCheckList = [
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
  brand: [{required: true, message: '请输入品牌', trigger: 'blur'}],
  model: [{required: true, message: '请输入型号', trigger: 'blur'}],
  year: [{required: true, message: '请输入生产年份', trigger: 'blur'}],
  mileage: [{required: true, message: '请输入行驶里程', trigger: 'blur'}],
  price: [{required: true, message: '请输入价格', trigger: 'blur'}],
  color: [{required: true, message: '请输入颜色', trigger: 'blur'}],
  fuelType: [{required: true, message: '请输入燃料类型(1汽油、2柴油、3电动、4混动、5其他)', trigger: 'blur'}],
  transmissionType: [{required: true, message: '请输入变速器类型(1自动档、2手动档)', trigger: 'blur'}],
  condition: [{required: true, message: '请输入车况(1九成新女生自用、2良好、3完好、4轻微刮擦、5叙利亚成色)', trigger: 'blur'}],
  licenseDate: [{required: true, message: '请输入上牌日期', trigger: 'blur'}],
  hasSold: [{required: true, message: '请输入是否售出', trigger: 'blur'}],
  hasCheck: [{required: true, message: '请输入是否验车', trigger: 'blur'}]
}

const getPage = () => {
  loading.value = true
  getUserList({}).then(res => {
    userList.value = res.data || []
  })
  getCarPage(queryParams).then(res => {
    carList.value = res.data?.records || []
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
    title: '添加二手车',
    data: {
      userId: null,
      brand: '',
      model: '',
      year: null,
      mileage: null,
      price: null,
      color: '',
      fuelType: null,
      transmissionType: null,
      condition: '',
      licenseDate: '',
      hasSold: null,
      hasCheck: null,
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
  getCarOne(params).then(res => {
    if (res.code !== 200) return
    form.value = {
      visible: true,
      title: '编辑二手车',
      data: {
        ...res.data
      }
    }
  })
}

const handleSave = () => {
  formRef.value.validate(valid => {
    if (!valid) return
    saveCar(form.value.data).then(res => {
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
  removeCarBatchByIds(params).then(res => {
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
  queryParams.brand = ''
  queryParams.model = ''
  queryParams.year = null
  queryParams.mileage = null
  queryParams.price = null
  queryParams.color = ''
  queryParams.fuelType = null
  queryParams.transmissionType = null
  queryParams.condition = ''
  queryParams.hasSold = null
  queryParams.hasCheck = null
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
