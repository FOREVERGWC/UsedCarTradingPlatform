<template>
  <el-sub-menu v-if="item?.children?.length > 0"
               v-show="!item?.meta?.hidden && item.meta.roles.includes(user.role)"
               :index="item.path">
    <template #title>
      <el-icon>
        <component :is="item.meta?.icon"/>
      </el-icon>
      <span>{{ item.meta?.name }}</span>
    </template>
    <menu-item v-for="(child, index) in item.children"
               :key="index"
               :item="child"
               :user="user"
               @click="handleClickMenu(child.path)">
    </menu-item>
  </el-sub-menu>
  <el-menu-item v-else
                v-show="!item?.meta?.hidden && item.meta.roles.includes(user.role)"
                :index="item.path"
                @click="handleClickMenu(item.path)">
    <el-icon>
      <component :is="item.meta?.icon"/>
    </el-icon>
    <span>{{ item.meta?.name }}</span>
  </el-menu-item>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue';

const props = defineProps({
  item: {
    type: Object,
    required: true
  },
  user: {
    type: Object,
    required: true
  }
});

const emit = defineEmits(['click']);

const handleClickMenu = (path) => {
  emit('click', path);
};
</script>

<style lang="scss" scoped>

</style>
