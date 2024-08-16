<template>
  <div>
    <el-row :gutter="20" justify="center">
      <el-col :lg="6" :md="20" :sm="20" :xl="6" :xs="20">
        <el-card class="profile-card">
          <div>
            <el-avatar :size="120" :src="avatar" alt="" @error="() => true">
              <img alt="" src="@/assets/imgs/profile.png"/>
            </el-avatar>
          </div>
          <h2 class="profile-name">{{ user.name || user.username }}</h2>
          <p class="profile-description">{{ user.remark || '这个人很懒，还没有添加简介' }}</p>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-statistic :value="articleList.length" title="文章"/>
            </el-col>
            <el-col :span="8">
              <el-statistic :value="268500" title="粉丝"/>
            </el-col>
            <el-col :span="8">
              <el-statistic :value="268500" title="关注"/>
            </el-col>
          </el-row>
          <el-button icon="Plus" type="primary">关注</el-button>
          <div class="icon-list">
            <i class="iconfont icon-bilibili-line"/>
            <i class="iconfont icon-github"/>
            <i class="iconfont icon-gitee-fill-round"/>
            <i class="iconfont icon-youxiang"/>
          </div>
        </el-card>

        <el-card class="category-card">
          <template #header>
            <span class="card-header-title">
              <i class="iconfont icon-fenlei"/>
              分类
            </span>
          </template>
          <ul>
            <li v-for="item in categoryList" :key="item.id">
              {{ item.name }}
            </li>
          </ul>
        </el-card>

        <el-card class="category-card">
          <template #header>
            <span class="card-header-title">
              <i class="iconfont icon-biaoqian"/>
              标签
            </span>
          </template>
          <ul>
            <li v-for="item in categoryList" :key="item.id">
              {{ item.name }}
            </li>
          </ul>
        </el-card>

        <el-card class="category-card">
          <template #header>
            <span class="card-header-title">
              <i class="iconfont icon-guidang"/>
              归档
            </span>
          </template>
          <ul>
            <li v-for="item in categoryList" :key="item.id">
              {{ item.name }}
            </li>
          </ul>
        </el-card>

        <el-card class="category-card">
          <template #header>
            <span class="card-header-title">
              <i class="iconfont icon-lianjie"/>
              常用链接
            </span>
          </template>
          <ul>
            <li v-for="item in categoryList" :key="item.id">
              {{ item.name }}
            </li>
          </ul>
        </el-card>
      </el-col>

      <el-col :lg="12" :md="20" :sm="20" :xl="12" :xs="20">
        <el-card v-for="(item, index) in articleList" :key="item.id" class="article-item">
          <div :class="['article-item-wrapper', {'content-reverse': index % 2 === 1}]">
            <el-image class="article-image" fit="cover" src="https://via.placeholder.com/400x252"/>
            <div class="article-content">
              <el-link :href="`/detail/${item.id}`" :underline="false" target="_blank">
                <h2 class="article-title">{{ item.title }}</h2>
              </el-link>
              <p class="article-info">
                <span class="article-info-item">
                  <i class="iconfont icon-24gl-calendar"/>
                  {{ item.releaseTime }}
                </span>
                <span class="article-info-item">
                  <i class="iconfont icon-icon-"/>
                  {{ item.viewCount }}
                </span>
                <span class="article-info-item">
                  <i class="iconfont icon-zan"/>
                  {{ item.likeCount }}
                </span>
                <span class="article-info-item">
                  <i class="iconfont icon-pinglun"/>
                  {{ item.commentCount }}
                </span>
                <span class="article-info-item">
                  <i class="iconfont icon-aixin"/>
                  {{ item.collectionCount }}
                </span>
              </p>
              <p class="article-summary" v-html="`摘要：${item.content}`"/>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import {computed, onMounted, reactive, ref} from "vue";
import useUserStore from "@/store/modules/user.js";
import {useRoute} from "vue-router";
import {getUserOne} from "@/api/user.js";
import {getArticlePage} from "@/api/article.js";
import {getArticleCategoryList} from "@/api/articleCategory.js";

const route = useRoute()
const userStore = useUserStore()

const userId = route.params.id
const loading = ref(true)
const queryParams = reactive({
  pageNo: 1,
  pageSize: 20,
  userId: userId
})
const user = ref({
  id: userId,
  username: '',
  name: '',
  remark: ''
})
const avatar = computed(() => import.meta.env.VITE_APP_BASE_API + user.avatar)
const articleList = ref([])
const categoryList = ref([])

const getDetail = () => {
  getUserOne({id: userId}).then(res => {
    user.value = res.data
  })
  getArticlePage(queryParams).then(res => {
    articleList.value = res.data?.records || []
  })
  getArticleCategoryList({}).then(res => {
    categoryList.value = res.data || []
  })
  // getFollowList({
  //   followerId: this.user.id
  // }).then(res => {
  //   this.followerList = res.data || []
  // })
  // getFollowList({
  //   followedId: this.user.id
  // }).then(res => {
  //   this.followedList = res.data || []
  // })
  // getChatList({}).then(res => {
  //   this.chatList = res.data || []
  // })
}

// TODO 查询当前用户的信息和文章
onMounted(() => {
  getDetail()
})
</script>

<style lang="scss" scoped>
.el-card {
  margin-bottom: 16px;
  border-radius: 8px;
}

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

.profile-card {
  text-align: center;

  .profile-name {
    cursor: pointer;

    &:hover {
      color: #409EFF;
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

.category-card {
  :deep(.el-card__header) {
    border-bottom: none;
  }
}

.article-item {
  :deep(.el-card__body) {
    padding: 0;
  }

  .article-item-wrapper {
    display: flex;
    align-items: center;
    height: 252px;

    .article-image {
      width: 40%;
      min-width: 40%;
      height: 100%;
    }

    .article-content {
      padding: 24px;
      flex-grow: 1;

      .article-title {
        margin-bottom: 12px;
      }

      .article-info {
        margin-bottom: 12px;
        color: #858585;

        .article-info-item {
          margin-right: 10px;
        }
      }

      .article-summary {
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 2; /* 控制显示的行数，例如3行 */
        overflow: hidden;
        text-overflow: ellipsis; /* 超出部分用省略号表示 */
        max-height: 72px; /* 设置最大高度，根据需要调整 */
        margin-bottom: 12px; /* 确保简介和下面的内容有一定的间距 */
      }
    }
  }

  .content-reverse {
    flex-direction: row-reverse;
  }
}
</style>
