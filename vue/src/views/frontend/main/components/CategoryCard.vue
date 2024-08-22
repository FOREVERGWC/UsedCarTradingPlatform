<template>
  <el-card class="category-card">
    <template #header>
      <span class="card-header-title">
        <i class="iconfont icon-fenlei"/>
        分类
      </span>
    </template>
    <ul v-if="list.length > 0">
      <li v-for="item in list" :key="item.id">
        {{ item.name }}
      </li>
    </ul>
    <el-empty v-else description="暂时没有分类哦！"/>
  </el-card>
</template>

<script setup>
import {defineProps, onMounted, ref} from 'vue';
import {getArticleCategoryList} from '@/api/articleCategory.js';

const props = defineProps({
  userId: {
    type: String,
    required: true,
  }
});

const list = ref([]);

const getList = () => {
  getArticleCategoryList({userId: props.userId}).then(res => {
    list.value = res.data || [];
  });
};

onMounted(() => {
  getList();
});
</script>

<style scoped lang="scss">
.category-card {
  margin-bottom: 16px;
  border-radius: 8px;

  :deep(.el-card__header) {
    border-bottom: none;

    .card-header-title {
      font-size: 22px;
      padding-left: 8px;
      border-left: #409EFF solid 4px;
      border-top-left-radius: 4px;
      border-bottom-left-radius: 4px;

      .iconfont {
        font-size: 22px;
      }
    }
  }
}
</style>
