<template>
  <div>
    <el-menu :default-active="menu" mode="horizontal" router>
      <el-sub-menu v-if="user.token" index="#">
        <template #title>
          <el-avatar :src="avatar" alt="" @error="() => true">
            <img alt="" src="@/assets/imgs/profile.png"/>
          </el-avatar>
        </template>
        <el-menu-item v-if="user.role === '0'" index="/backend">返回后台</el-menu-item>
        <el-menu-item index="/center">个人中心</el-menu-item>
        <el-menu-item index="#" @click.native="handleLogout">退出登录</el-menu-item>
      </el-sub-menu>
      <el-menu-item v-else index="/login">登录</el-menu-item>
      <el-menu-item index="/index">首页</el-menu-item>
      <el-menu-item :index="`/main/${user.id}`">个人主页</el-menu-item>
      <div style="flex-grow: 1"/>
      <el-menu-item index="/blog/null">发布文章</el-menu-item>
    </el-menu>
    <el-main>
      <router-view/>
    </el-main>
  </div>
</template>

<script setup>
import {computed, reactive, ref} from "vue";
import useUserStore from "@/store/modules/user.js";
import useNavigationStore from "@/store/modules/navigation.js";
import {useRouter} from "vue-router";

const router = useRouter()
const userStore = useUserStore()
const navigationStore = useNavigationStore()

const menu = ref(navigationStore.menu)
const user = reactive(userStore.getUser)
const avatar = computed(() => import.meta.env.VITE_APP_BASE_API + user.avatar)

const toCenter = () => {
  router.push('/center')
}
const handleLogout = () => {
  localStorage.clear()
  userStore.clearUser()
  router.replace('/')
}
</script>

<style lang="scss" scoped>
.el-menu {
  height: 80px;
  //background-color: #1a7cff;

  .el-menu-item {
    font-size: large;
    font-weight: bolder;
    //color: white;
    //background-color: #1a7cff;
  }
}

.el-main {
  background-color: #F3F8FF;
  padding: 16px;
}
</style>
