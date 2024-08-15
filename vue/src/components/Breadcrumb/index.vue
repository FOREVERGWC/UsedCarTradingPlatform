<template>
  <el-breadcrumb :separator-icon="ArrowRight">
    <el-breadcrumb-item :to="{ path: '/backend' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>
      {{ itemList.meta?.name }}
    </el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script setup>
import {defineProps} from "vue";
import {ArrowRight} from "@element-plus/icons-vue";

const props = defineProps({
  itemList: {
    type: Object,
    required: true,
  }
});
</script>

<style lang="scss" scoped>

</style>
