<template>
  <el-container>
    <el-aside class="hidden-sm-and-down">
      <el-menu :default-active='menu' router>
        <div class="title">{{ title }}</div>
        <menu-item v-for="(item, index) in menuList"
                   :key="index"
                   :item="item"
                   :user="user"
                   @click="handleClickMenu"/>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header>
        <Breadcrumb :itemList="breadcrumbStore.itemList"/>

        <el-dropdown>
          <span class="header-dropdown">
            <el-avatar :src="avatar" alt="" @error="() => true">
              <img alt="" src="@/assets/imgs/profile.png"/>
            </el-avatar>
            <span class="header-username">{{ user.username }}</span>
            <el-icon class="el-icon--right">
              <ArrowDown/>
            </el-icon>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click.native.prevent='toFrontend'><span>前台</span></el-dropdown-item>
              <el-dropdown-item @click.native.prevent='toCenter'><span>个人中心</span></el-dropdown-item>
              <el-dropdown-item @click.native.prevent='handleLogout'><span>退出登录</span></el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-header>

      <el-main>
        <router-view/>
      </el-main>

      <el-footer class="hidden-md-and-up">
        <el-menu :default-active='menu' mode="horizontal" router>
          <div v-for="(item, index) in menuList" :key="index">
            <el-sub-menu v-if="item?.children?.length > 0"
                         v-show='!item?.meta?.hidden && item.meta.roles.includes(user.role)'
                         :index='item.path'>
              <template #title>
                <el-icon>
                  <component :is="item.meta?.icon"/>
                </el-icon>
                <span>{{ item.meta?.name }}</span>
              </template>
              <el-menu-item v-for='(item, index) in item.children'
                            v-show='!item?.meta?.hidden && item.meta.roles.includes(user.role)'
                            :key='index' :index='item.path'
                            @click='handleClickMenu(item.path)'>
                <el-icon>
                  <component :is="item.meta?.icon"/>
                </el-icon>
                {{ item.meta.name }}
              </el-menu-item>
            </el-sub-menu>
            <el-menu-item v-else v-show='!item?.meta?.hidden && item.meta.roles.includes(user.role)' :index="item.path"
                          @click='handleClickMenu(item.path)'>
              <el-icon>
                <component :is="item.meta?.icon"/>
              </el-icon>
              <span>{{ item.meta?.name }}</span>
            </el-menu-item>
          </div>
        </el-menu>
      </el-footer>
    </el-container>
  </el-container>
</template>

<script setup>
import {computed, reactive, ref} from "vue";
import {useRouter} from "vue-router";
import useUserStore from "@/store/modules/user.js";
import useBreadcrumbStore from "@/store/modules/breadcrumb.js";
import useNavigationStore from "@/store/modules/navigation.js";

const router = useRouter()
const userStore = useUserStore()
const breadcrumbStore = useBreadcrumbStore()
const navigationStore = useNavigationStore()

const title = ref(import.meta.env.VITE_APP_TITLE || '后台管理系统')
const menu = ref(navigationStore.menu)
const menuList = ref(router.options.routes || [])
const user = reactive(userStore.getUser)
const avatar = computed(() => import.meta.env.VITE_APP_BASE_API + user.avatar)

const handleClickMenu = (item) => {
  console.log(item)
}

const toFrontend = () => {
  router.push('/')
}

const toCenter = () => {
  router.push('/center')
}

const handleLogout = () => {
  localStorage.clear()
  router.push('/login')
}

router.afterEach((to, from) => {
  breadcrumbStore.setItemList(to);
  navigationStore.setMenu(to.path)
});

</script>

<style lang="scss" scoped>
.el-container {
  height: 100%;

  .el-aside {
    width: 256px;
    height: 100%;

    .el-menu {
      height: 100%;

      .title {
        font-family: '微软雅黑', sans-serif;
        font-size: 26px;
        font-weight: bolder;
        height: 60px;
        line-height: 60px;
        text-align: center;
      }
    }

    &::-webkit-scrollbar {
      display: none;
    }
  }

  .el-container {
    .el-header {
      box-shadow: 0 0 12px rgba(0, 0, 0, 0.12);
      display: flex;
      align-items: center;
      justify-content: space-between;

      .el-dropdown {
        .header-dropdown {
          display: flex;
          align-items: center;
          user-select: none;

          .header-username {
            margin-left: 10px;
            margin-right: 10px;
          }
        }
      }
    }

    .el-main {
      background-color: #F3F8FF;
      padding: 16px;
    }
  }
}
</style>
