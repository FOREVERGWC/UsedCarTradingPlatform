<!--<template>-->
<!--  <div class="background-image-container">-->
<!--    <el-row :gutter="10">-->
<!--      <el-col :lg="6" :md="6" :sm="24" :xl="6" :xs="24">-->
<!--        <el-card style="text-align: center">-->
<!--          <el-avatar :src="getAvatar(detail.avatar)" size="large"></el-avatar>-->
<!--          <h2>{{ detail.name || detail.username }}</h2>-->
<!--          <span>-->
<!--            {{ detail.remark || '这个人很懒，还没有添加简介' }}-->
<!--          </span>-->
<!--          <div v-if="user.id !== detail.id">-->
<!--            <el-button :type="isAttention(detail.id) ? '' : 'success'" @click="handleAttention(detail.id)">-->
<!--              {{ isAttention(detail.id) ? '取关' : '关注' }}-->
<!--            </el-button>-->
<!--          </div>-->
<!--          <el-statistic :value="blogList.length" style="margin-top: 80px" title="文章"/>-->
<!--          <el-divider/>-->
<!--          <el-statistic :value="followerList.length" title="关注数"/>-->
<!--          <el-divider/>-->
<!--          <el-statistic :value="followedList.length" title="被关注数"/>-->
<!--        </el-card>-->
<!--      </el-col>-->
<!--      <el-col :lg="4" :md="4" :sm="24" :xl="4" :xs="24">-->
<!--        <el-card>-->
<!--          <div v-for="item in followerList" :key="item.id" style="cursor: pointer; margin-bottom: 10px;"-->
<!--               @click="selectChat(item.followed)">-->
<!--            <el-avatar :src="getAvatar(item.followed.avatar)"/>-->
<!--            <span style="margin-right: 10px">{{ item.followed.name || item.followed.username }}</span>-->
<!--          </div>-->
<!--        </el-card>-->
<!--      </el-col>-->
<!--      <el-col :lg="14" :md="14" :sm="24" :xl="14" :xs="24">-->
<!--        <el-card>-->
<!--          <template #header>-->
<!--            {{ chatUser.name || chatUser.username }}-->
<!--          </template>-->
<!--          <p>-->
<!--            <div v-for="msg in chatMessages" :key="msg.id" class="message-container">-->
<!--              <div v-if="msg.senderId !== user.id" class="message-left">-->
<!--                <el-avatar :src="getAvatar(msg.sender.avatar)"/>-->
<!--                <span class="message-content">{{ msg.message }}</span>-->
<!--              </div>-->
<!--              <div v-else class="message-right">-->
<!--                <el-avatar :src="getAvatar(msg.sender.avatar)"/>-->
<!--                <span class="message-content">{{ msg.message }}</span>-->
<!--              </div>-->
<!--            </div>-->
<!--          </p>-->
<!--          <el-input v-model="newMessage" placeholder="输入消息" @keyup.enter="sendMessage"></el-input>-->
<!--        </el-card>-->
<!--      </el-col>-->
<!--    </el-row>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->

<!--import {getUserOne} from "@/api/user.js";-->
<!--import {getChatList, saveChat} from "@/api/chat.js";-->
<!--import {getFollowList, removeFollowBatchByIds, saveFollow} from "@/api/follow.js";-->
<!--import {getArticleList} from "@/api/article.js";-->

<!--export default {-->
<!--  name: "MainView",-->
<!--  data () {-->
<!--    return {-->
<!--      user: JSON.parse(localStorage.getItem('user') || '{}'),-->
<!--      queryParams: {-->
<!--        id: this.$route.params.id-->
<!--      },-->
<!--      detail: {},-->
<!--      blogList: [],-->
<!--      followerList: [],-->
<!--      followedList: [],-->
<!--      chatList: [],-->
<!--      selectedChatUserId: null,-->
<!--      chatUser: {},-->
<!--      newMessage: ''-->
<!--    }-->
<!--  },-->
<!--  watch: {-->
<!--    '$route.params.id': 'getDetail'-->
<!--  },-->
<!--  created () {-->
<!--    this.getDetail()-->
<!--    setInterval(() => {-->
<!--      getChatList({}).then(res => {-->
<!--        this.chatList = res.data || []-->
<!--      })-->
<!--    }, 5000)-->
<!--  },-->
<!--  computed: {-->
<!--    getUrl () {-->
<!--      return (path) => {-->
<!--        return import.meta.env.VITE_APP_BASE_API + path-->
<!--      }-->
<!--    },-->
<!--    getAvatar () {-->
<!--      return (path) => {-->
<!--        return path ? this.getUrl(path) : (path ? this.getUrl(path) : new URL('@/assets/imgs/profile.png', import.meta.url))-->
<!--      }-->
<!--    },-->
<!--    filteredChatList () {-->
<!--      return this.chatList.filter(chat => chat.senderId === this.user.id || chat.receiverId === this.user.id);-->
<!--    },-->
<!--    chatMessages () {-->
<!--      return this.chatList.filter(chat =>-->
<!--          (chat.senderId === this.user.id && chat.receiverId === this.chatUser.id) ||-->
<!--          (chat.senderId === this.chatUser.id && chat.receiverId === this.user.id)-->
<!--      )-->
<!--    },-->
<!--    isAttention () {-->
<!--      return (id) => {-->
<!--        return this.followerList.find(i => i.followedId === id)-->
<!--      }-->
<!--    }-->
<!--  },-->
<!--  methods: {-->
<!--    getDetail () {-->
<!--      this.queryParams.id = this.$route.params.id-->
<!--      getUserOne(this.queryParams).then(res => {-->
<!--        this.detail = res.data-->
<!--      })-->
<!--      getArticleList({-->
<!--        userId: this.user.id-->
<!--      }).then(res => {-->
<!--        this.blogList = res.data || []-->
<!--      })-->
<!--      getFollowList({-->
<!--        followerId: this.user.id-->
<!--      }).then(res => {-->
<!--        this.followerList = res.data || []-->
<!--      })-->
<!--      getFollowList({-->
<!--        followedId: this.user.id-->
<!--      }).then(res => {-->
<!--        this.followedList = res.data || []-->
<!--      })-->
<!--      getChatList({}).then(res => {-->
<!--        this.chatList = res.data || []-->
<!--      })-->
<!--    },-->
<!--    selectChat (chat) {-->
<!--      this.chatUser = chat-->
<!--    },-->
<!--    sendMessage () {-->
<!--      if (!this.newMessage.trim()) return;-->
<!--      const params = {-->
<!--        receiverId: this.chatUser.id,-->
<!--        message: this.newMessage-->
<!--      }-->
<!--      saveChat(params).then(res => {-->
<!--        this.newMessage = ''-->
<!--      }).finally(() => {-->
<!--        getChatList({}).then(res => {-->
<!--          this.chatList = res.data || []-->
<!--        })-->
<!--      })-->
<!--    },-->
<!--    handleAttention (id) {-->
<!--      const attentionId = this.isAttention(id)?.id-->
<!--      attentionId ? removeFollowBatchByIds(attentionId).then(res => {-->
<!--        this.$message.success('取关成功！')-->
<!--      }).finally(() => {-->
<!--        this.getDetail()-->
<!--      }) : saveFollow({followedId: this.detail.id}).then(res => {-->
<!--        this.$message.success('关注成功！')-->
<!--      }).finally(() => {-->
<!--        this.getDetail()-->
<!--      })-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--<style lang="scss" scoped>-->
<!--.background-image-container {-->
<!--  background-image: url('@/assets/imgs/xnwkj.webp');-->
<!--  background-size: cover;-->
<!--  background-position: center;-->
<!--  background-repeat: no-repeat;-->
<!--  min-height: 100vh;-->
<!--  padding: 20px;-->
<!--}-->

<!--.el-card {-->
<!--  margin: 10px 10px;-->
<!--}-->

<!--:deep(.el-card__header) {-->
<!--  text-align: center;-->
<!--}-->

<!--.message-container {-->
<!--  display: flex;-->
<!--  margin-bottom: 10px;-->
<!--}-->

<!--.message-left {-->
<!--  background-color: #f2f2f2;-->
<!--  padding: 10px;-->
<!--  border-radius: 10px;-->
<!--  max-width: 60%;-->
<!--  display: flex;-->
<!--  align-items: center;-->
<!--}-->

<!--.message-right {-->
<!--  background-color: #1a7cff;-->
<!--  color: white;-->
<!--  padding: 10px;-->
<!--  border-radius: 10px;-->
<!--  max-width: 60%;-->
<!--  margin-left: auto;-->
<!--  display: flex;-->
<!--  align-items: center;-->
<!--}-->

<!--.message-content {-->
<!--  margin-left: 10px;-->
<!--}-->
<!--</style>-->
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
              <el-link :href="`/detail/${item.id}`" :underline="false">
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
              <v-md-preview :text="item.content" class="article-summary"/>
              <!--              <p class="article-summary" v-html="item.content"></p>-->
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
        
      }
    }
  }

  .content-reverse {
    flex-direction: row-reverse;
  }
}
</style>
