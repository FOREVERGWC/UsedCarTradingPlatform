<template>
  <div>
    <CarCarousel/>

    <el-row justify="center">
      <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="16">
        <p class="text-xl font-bold leading-10 pb-10 text-center">
          我要卖车
        </p>
        <el-card>
          <el-form :model="form" :rules="rules" ref="formRef">
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item prop="brand">
                  <el-select size="large" v-model="form.brand" clearable filterable placeholder="请选择品牌"
                             @change="handleBrandChange">
                    <el-option v-for="item in brandList" :key="item.id" :label="item.name" :value="item.name"/>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item prop="model">
                  <el-select size="large" v-model="form.model" clearable filterable placeholder="请选择型号">
                    <el-option v-for="item in modelList" :key="item.id" :label="item.name" :value="item.name"/>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-form-item prop="year">
              <el-select size="large" v-model="form.year" clearable filterable placeholder="请选择生产年份">
                <el-option v-for="item in yearList" :key="item.value" :label="item.label" :value="item.label"/>
              </el-select>
            </el-form-item>
            <el-form-item prop="mileage">
              <el-input size="large" v-model="form.mileage" placeholder="请输入行驶里程" autocomplete="new" clearable/>
            </el-form-item>
            <el-form-item prop="price">
              <el-input size="large" v-model="form.price" placeholder="请输入价格" autocomplete="new" clearable/>
            </el-form-item>
            <el-form-item prop="color">
              <el-input size="large" v-model="form.color" placeholder="请输入颜色" autocomplete="new" clearable/>
            </el-form-item>
            <el-form-item prop="fuelType">
              <DictSelect type="fuel_type" size="large" v-model="form.fuelType" placeholder="请选择燃料类型"/>
            </el-form-item>
            <el-form-item prop="condition">
              <DictSelect type="car_condition" size="large" v-model="form.condition" placeholder="请选择车况"/>
            </el-form-item>
            <el-form-item prop="transmissionType">
              <DictSelect type="transmission_type" size="large" v-model="form.transmissionType" placeholder="请选择变速器类型"/>
            </el-form-item>
            <el-form-item>
              <el-date-picker
                  size="large"
                  v-model="form.licenseDate"
                  placeholder="请选择上牌日期"
                  type="date"
                  value-format="YYYY-MM-DD"
                  :disabled-date="disabledAfterToday"
              />
            </el-form-item>
            <el-form-item>
              <el-button size="large" type="primary" @click="handleSubmit">提交</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import CarCarousel from '@/components/CarCarousel.vue'
import {onMounted, reactive, ref} from 'vue'
import {brandList, disabledAfterToday, getYearRange, modelMap} from "@/utils/common.js";
import {saveCar} from "@/api/car.js";
import {ElMessage} from "element-plus";

const form = reactive({
  brand: '',
  model: '',
  year: null,
  mileage: '',
  price: null,
  color: '',
  fuelType: '',
  transmissionType: '',
  condition: '',
  licenseDate: ''
})
const rules = {
  brand: [{required: true, message: '请选择品牌', trigger: 'change'}],
  model: [{required: true, message: '请选择型号', trigger: 'change'}],
  year: [{required: true, message: '请选择生产年份', trigger: 'change'}],
  mileage: [{required: true, message: '请输入行驶里程', trigger: 'blur'}],
  price: [{required: true, message: '请输入价格', trigger: 'blur'}],
  color: [{required: true, message: '请输入颜色', trigger: 'blur'}],
  fuelType: [{required: true, message: '请选择燃料类型', trigger: 'change'}],
  transmissionType: [{required: true, message: '请选择变速器类型', trigger: 'change'}],
  condition: [{required: true, message: '请选择车况', trigger: 'change'}]
};
const formRef = ref(null)

const modelList = ref([])

const yearList = ref([])

const handleBrandChange = (val) => {
  form.model = ''
  modelList.value = modelMap[val]
}

const handleSubmit = () => {
  formRef.value.validate(valid => {
    if (!valid) return
    console.log('提交表单', form)
    saveCar(form).then(res => {
      if (res.code !== 200) {
        ElMessage.error(res.msg)
        return
      }
      ElMessage.success('保存成功！')
    })
  });
}

onMounted(() => yearList.value = getYearRange())
</script>

<style lang="scss" scoped>
:deep(.el-date-editor) {
  width: 100%;
}
</style>
