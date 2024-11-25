<template>
  <div>
    <CarCarousel />

    <div>
      我要卖车
      <el-card>
        <el-form>
          <el-row>
            <el-col :span="12">
              <el-form-item>
                <el-input size="large" v-model="form.brand" placeholder="请选择品牌" clearable />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item>
                <el-input size="large" v-model="form.model" placeholder="请选择型号" clearable />
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item>
            <el-input size="large" v-model="form.year" placeholder="请选择生产年份" clearable />
          </el-form-item>
          <el-form-item>
            <el-input size="large" v-model="form.mileage" placeholder="请输入行驶里程" autocomplete="new" clearable />
          </el-form-item>
          <el-form-item>
            <el-input size="large" v-model="form.color" placeholder="请输入颜色" autocomplete="new" clearable />
          </el-form-item>
          <el-form-item>
            <el-input size="large" v-model="form.licenseDate" placeholder="请选择上牌日期"
                      clearable />
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script setup>
import CarCarousel from '@/components/CarCarousel.vue'
import { reactive } from 'vue'

const form = reactive({
  brand: '',
  model: '',
  year: null,
  mileage: '',
  color: '',
  licenseDate: ''
})
</script>

<style lang="scss" scoped>

</style>
