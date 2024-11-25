<template>
  <el-menu mode="horizontal" router>
    <el-menu-item index="0" disabled style="cursor: auto;">
      <img style="width: 100px" src="@/assets/logo.jpg" alt=""/>
    </el-menu-item>
    <el-menu-item index="/">首页</el-menu-item>
    <el-menu-item index="/buy">我要买车</el-menu-item>
    <el-menu-item index="/sell">我要卖车</el-menu-item>
    <el-menu-item index="/change">我要置换</el-menu-item>
    <el-menu-item index="/query">
      <i class="iconfont icon-user"/>
    </el-menu-item>
    <el-menu-item index="/profile">
      <i class="iconfont icon-search"/>
    </el-menu-item>
  </el-menu>
</template>

<script setup>

</script>

<style scoped lang="scss">
.el-menu--horizontal > .el-menu-item:nth-child(5) {
  margin-right: auto;
}
</style>
