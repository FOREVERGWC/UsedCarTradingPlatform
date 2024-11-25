<template>
  <el-container>
    <el-header>
      <Navbar />
    </el-header>

    <el-main>
      <RouterView />
    </el-main>
  </el-container>
</template>

<script setup>

</script>

<style lang="scss" scoped>
.el-main {
  padding: 0;
}
</style>
