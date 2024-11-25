<template>
  <el-card>
    <div class="background-container" />
    <div style="display: flex; justify-content: space-between; padding: 16px;">
      <div>
        <p>品牌特约店</p>
        <p>省心服务悦心体验</p>
      </div>
      <el-button>了解详情</el-button>
    </div>
  </el-card>
</template>

<script setup>

</script>

<style lang="scss" scoped>
.el-card {
  :deep(.el-card__body) {
    padding: 0;
  }
}

.background-container {
  position: relative;
  background-image: url('@/assets/imgs/brand_store.jpg');
  background-size: cover;
  background-position: center;
  height: 500px;
  width: 100%;
}
</style>
