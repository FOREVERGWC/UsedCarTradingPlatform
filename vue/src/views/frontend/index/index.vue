<template>
  <div class="background-image-container">
    <el-row :gutter="20">
      <el-col :lg="18" :md="18" :sm="24" :xl="18" :xs="24">
        <el-card v-for="item in blogList" v-if="blogList && blogList.length > 0" :key="item.id" shadow="hover">
          <template #header>
            <el-link :href="`/detail/${item.id}`" :underline="false" target="_blank">
              <span>{{ item.title }}</span>
            </el-link>
          </template>
          <p class="fixed-height">
            <div v-if="firstImage">
              <img :src="firstImage(item.content)" alt="" class="first-image"/>
            </div>
            <v-md-preview :text="item.content"/>
          </p>
          <template #footer>
            <span style="margin-right: 10px">
              <i class="iconfont icon-zan" style="margin-right: 5px"></i>0
            </span>
            <span style="margin-right: 10px">
              <i class="iconfont icon-cai" style="margin-right: 5px"></i>0
            </span>
            <span>
               作者：{{ item.user.name }}
            </span>
          </template>
        </el-card>
        <el-empty v-else description="暂时没有文章哦！"/>
      </el-col>
      <el-col :lg="6" :md="6" :sm="24" :xl="6" :xs="24">
        <el-card shadow="hover">
          <template #header>
            <span>最新评论<el-icon style="margin-left: 5px"><ChatDotRound/></el-icon></span>
          </template>
          <p>
            <div v-for="item in commentList" v-if="commentList && commentList.length > 0" :key="item.id"
                 class="comment-item">
              <el-row>
                <el-col :span="6">
                  <el-avatar :src="getAvatar(item.user.avatar)" shape="square" size="large"
                             @click="handleMain(item.user.id)"/>
                </el-col>
                <el-col :span="18">
                  <span class="comment-item-info">{{ item.user.name || item.user.username }}</span>
                  <span class="comment-item-info">{{ item.createTime }}</span>
                  <p v-html="item.content"></p>
                </el-col>
              </el-row>
            </div>
            <el-empty v-else description="暂时没有评论哦！"/>
          </p>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {getArticleList} from "@/api/article";
import {getCommentList} from "@/api/comment.js";

export default {
  name: "IndexView",
  data() {
    return {
      blogLoading: true,
      commentLoading: true,
      blogQueryParams: {
        title: '',
        content: '',
        userId: null
      },
      commentParams: {
        blogId: null,
        userId: null,
        content: ''
      },
      blogList: [],
      commentList: []
    }
  },
  created() {
    this.getList()
  },
  computed: {
    getUrl() {
      return (path) => {
        return import.meta.env.VITE_APP_BASE_API + path
      }
    },
    getAvatar() {
      return (path) => {
        return path ? this.getUrl(path) : (path ? this.getUrl(path) : new URL('@/assets/imgs/profile.png', import.meta.url))
      }
    },
    firstImage() {
      return (content) => {
        const match = content.match(/!\[.*?\]\((.*?)\)/);
        console.log(match ? match[1] : null)
        return match ? match[1] : null;
      }
    }
  },
  methods: {
    getList() {
      this.blogLoading = true
      this.commentLoading = true
      getArticleList(this.blogQueryParams).then(res => {
        this.blogList = res.data || []
        this.blogLoading = false
      })
      getCommentList(this.commentParams).then(res => {
        this.commentList = res.data || []
        this.commentLoading = false
      })
    },
    handleMain(id) {
      this.$router.push(`/main/${id}`)
    }
  }
}
</script>

<style lang="scss" scoped>
.background-image-container {
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  min-height: 100vh;
  padding: 20px;
}

.el-card {
  margin: 60px 80px;
}

.iconfont:hover {
  color: #f00;
}

.comment-item {
  margin-bottom: 5px;

  .comment-item-info {
    color: gray;
    margin-right: 15px;
  }
}

.fixed-height {
  height: 100px;
  overflow: hidden;
  position: relative;
}

.first-image {
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: auto;
  object-fit: contain;
}
</style>
