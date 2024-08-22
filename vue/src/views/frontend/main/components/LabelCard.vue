<template>
  <el-card class="label-card">
    <template #header>
      <span class="card-header-title">
        <i class="iconfont icon-biaoqian"/>
        标签
      </span>
    </template>
    <div v-if="list.length > 0">
      <el-button plain v-for="item in list" :key="item.id">
        <span>{{ item.name }}</span>
      </el-button>
    </div>
    <el-empty v-else description="暂时没有标签哦！"/>
  </el-card>
</template>

<script setup>
import {defineProps, onMounted, ref} from 'vue';
import {getArticleLabelList} from '@/api/articleLabel.js';

const props = defineProps({
  userId: {
    type: String,
    required: true,
  }
});

const list = ref([]);

const getList = () => {
  getArticleLabelList({userId: props.userId}).then(res => {
    list.value = res.data || [];
  });
};

onMounted(() => {
  getList();
});
</script>

<style scoped lang="scss">
.label-card {
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
