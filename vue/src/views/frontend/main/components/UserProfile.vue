<template>
  <el-card class="profile-card">
    <div>
      <el-avatar :size="120" :src="avatar()" alt="" @error="() => true">
        <img alt="" src="@/assets/imgs/profile.png"/>
      </el-avatar>
    </div>
    <h3>{{ user.name || user.username }}</h3>
    <p class="profile-description">{{ user.remark || '这个人很懒，还没有添加简介' }}</p>
    <el-row>
      <el-col :span="8">
        <el-statistic :value="articleCount" title="文章"/>
      </el-col>
      <el-col :span="8">
        <el-statistic :value="info.followerCount" title="粉丝"/>
      </el-col>
      <el-col :span="8">
        <el-statistic :value="info.followedCount" title="关注"/>
      </el-col>
    </el-row>
    <el-button
        :icon="info.isFollowing ? 'Minus' : 'Plus'"
        :type="info.isFollowing ? '' : 'primary'"
        @click="handleFollow">
      {{ info.isFollowing ? '取关' : '关注' }}
    </el-button>
    <div class="icon-list">
      <i class="iconfont icon-bilibili-line"/>
      <i class="iconfont icon-github"/>
      <i class="iconfont icon-gitee-fill-round"/>
      <i class="iconfont icon-youxiang"/>
    </div>
  </el-card>
</template>

<script setup>
import {defineProps, onMounted, reactive} from 'vue'
import {doFollowTo, getFollowInfo} from "@/api/follow.js";
import useUserStore from "@/store/modules/user.js";
import {ElMessage} from "element-plus";

const props = defineProps({
  user: {
    type: Object,
    required: true
  },
  articleCount: {
    type: Number,
    required: true
  }
})

const userStore = useUserStore()

const info = reactive({
  followerCount: 0,
  followedCount: 0,
  isFollowing: false
})
const avatar = () => import.meta.env.VITE_APP_BASE_API + props.user.avatar

const getInfo = () => {
  getFollowInfo(props.user.id).then(res => {
    info.followerCount = +res.data?.followerCount || 0
    info.followedCount = +res.data?.followedCount || 0
    info.isFollowing = res.data?.isFollowing || false
  })
}

const handleFollow = () => {
  const operation = info.isFollowing ? '取关' : '关注';
  if (userStore.id === props.user.id) {
    ElMessage.error(`${operation}失败！不能对自己进行此操作`)
    return
  }
  doFollowTo(props.user.id).then(res => {
    if (res.code !== 200) {
      ElMessage.error(res.msg)
      return
    }
    ElMessage.success(`${operation}成功！`)
  }).finally(() => {
    getInfo()
  })
}

onMounted(() => {
  getInfo()
})
</script>

<style scoped lang="scss">
.profile-card {
  text-align: center;

  .el-avatar {
    transition: transform 0.5s ease-out;
    -webkit-transition: transform 0.6s ease-out;
    -moz-transition: transform 0.5s ease-out;
    -ms-transition: transform 0.5s ease-out;
    -o-transition: transform 0.5s ease-out;

    &:hover {
      -webkit-transform: rotate(360deg);
      -moz-transform: rotate(360deg);
      -ms-transform: rotate(360deg);
      -o-transform: rotate(360deg);
      transform: rotate(360deg);
    }
  }

  .profile-description {
    margin-top: 16px;
    margin-bottom: 16px;
    color: #555555;
  }

  .el-button {
    margin-top: 16px;
    margin-bottom: 16px;
    width: 100%;
  }

  .icon-list {
    display: flex;
    justify-content: center;
    gap: 10px;

    .iconfont {
      font-size: 24px;
      cursor: pointer;

      &:hover {
        color: #409EFF;
      }
    }
  }
}
</style>
