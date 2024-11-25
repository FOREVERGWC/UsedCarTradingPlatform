<template>
  <div>
    <div>
      <CarCarousel />

      <el-tabs v-model="actionTab">
        <el-tab-pane label="我要买车" name="buy"></el-tab-pane>
        <el-tab-pane label="我要卖车" name="sell"></el-tab-pane>
        <el-tab-pane label="我要置换" name="change"></el-tab-pane>
      </el-tabs>
      <el-form size="large" :inline="true" :model="form">
        <el-row gutter="20">
          <el-col :xs="24" :sm="12" :md="6" :lg="6" :xl="6">
            <el-form-item prop="name">
              <el-input size="large" v-model="form.name" placeholder="请输入姓名" autocomplete="new" clearable />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="6" :lg="6" :xl="6">
            <el-form-item prop="phone">
              <el-input size="large" v-model="form.phone" placeholder="请输入手机" autocomplete="new" clearable />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="6" :lg="6" :xl="6">
            <el-form-item prop="city">
              <el-input size="large" v-model="form.city" placeholder="请输入城市" autocomplete="new" clearable />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="6" :lg="6" :xl="6">
            <el-form-item prop="code">
              <el-input size="large" v-model="form.code" placeholder="请输入验证码" autocomplete="new" clearable />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div style="display: flex; align-items: center;">
        <el-radio-group v-model="userFlag">
          <el-radio :value="true" size="large" />
        </el-radio-group>
        <span>
          我已阅读并同意
          <el-link type="danger" :underline="false">《二手车交易平台隐私政策》</el-link>
          和
          <el-link type="danger" :underline="false">《二手车交易平台服务协议》</el-link>
        </span>
      </div>
    </div>

    <div>
      我要买车
    </div>

    <CarEvaluation />

    <BrandService />

    <div style="display: flex; justify-content: space-between;">
      <div style="width: 48%;">
        <PlatformDynamics />
      </div>
      <div style="width: 48%;">
        <BrandStore />
      </div>
    </div>
  </div>
  <!-- 车辆表、车辆详情表(key、value)存储车辆参数(由管理员验车补充)【验车状态(未验车、已验车)】、订单表【配送状态(未配送、已配送)、付款状态(未付款、已付款)、退款状态(未退款、已退款)、】、订单详情表 -->
  <!-- 卖车填车辆信息 -->
</template>

<script setup>
import { reactive, ref } from 'vue'
import CarEvaluation from '@/views/frontend/home/components/CarEvaluation.vue'
import CarCarousel from '@/components/CarCarousel.vue'
import BrandService from '@/views/frontend/home/components/BrandService.vue'
import PlatformDynamics from '@/views/frontend/home/components/PlatformDynamics.vue'
import BrandStore from '@/views/frontend/home/components/BrandStore.vue'


const actionTab = ref('buy')
const form = reactive({
  name: '',
  phone: '',
  city: '',
  code: ''
})
const userFlag = ref(false)
</script>

<style lang="scss" scoped>
:deep(.el-tabs__nav-scroll) {
  display: flex;
  justify-content: space-around;
}

:deep(.el-form-item) {
  width: 100%;
}
</style>
