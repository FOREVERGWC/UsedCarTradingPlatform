<template>
  <div>
    <el-row>
      <el-col :xs="24" :sm="24" :md="4" :lg="4" :xl="4">
        <div>品牌服务</div>
        <p>二手车交易平台始终体现着三个喜悦“购买的喜悦、销售的喜悦、创造的喜悦”的服务精神。</p>
        <p>一切以用户满意为出发点，通过诚信、专业、便捷的服务理念营造值得信赖的二手车交易平台。</p>
        <el-button>了解详情</el-button>
      </el-col>
      <el-col :xs="24" :sm="24" :md="20" :lg="20" :xl="20" class="background-container">

      </el-col>
    </el-row>
  </div>
</template>

<script setup>

</script>

<style lang="scss" scoped>
.background-container {
  position: relative;
  background-image: url('@/assets/imgs/brand_service.jpg');
  background-size: cover;
  background-position: center;
  height: 500px;
  width: 100%;
}
</style>
