<template>
  <div>
    <div style="text-align: center">
      <div>爱车评估</div>
      <div>快速在线操作,完成您的爱车评估</div>
    </div>
    <div class="background-container">
      <div style="width: 30%;">
        <el-form>
          <el-row>
            <el-col :span="12">
              <el-form-item>
                <el-select size="large" placeholder="品牌车系" clearable></el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item>
                <el-select size="large" placeholder="年款" clearable></el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item>
                <el-select size="large" placeholder="车型" clearable></el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item>
                <el-select size="large" placeholder="城市" clearable></el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item>
                <el-date-picker size="large" placeholder="上牌日期" clearable />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item>
                <el-input size="large" placeholder="行驶里程" autocomplete="new" clearable></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-text style="color: var(--el-color-white);">
            注:预测结果由第三方服务供应商提供,请以特约店实车检测后的估价结果为准
          </el-text>
          <el-text style="color: var(--el-color-white);">
            若车型信息不明确，请联系人工客股或点击
            <el-text type="danger">我要卖车</el-text>
            留下您的联系方式
          </el-text>
          <el-button size="large">点击查看</el-button>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script setup>

</script>

<style lang="scss" scoped>
.background-container {
  position: relative;
  background-image: url('@/assets/imgs/car_evaluation.jpg');
  background-size: cover;
  background-position: center;
  height: 500px;
  width: 100%;
}

:deep(.el-select__wrapper), :deep(.el-input__wrapper), .el-button {
  background-color: transparent;
}

.el-button {
  color: #fff;
}

:deep(.el-date-editor), .el-button {
  width: 100%;
}
</style>
