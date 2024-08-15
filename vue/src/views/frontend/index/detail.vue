<template>
  <div>
    <el-row :gutter="10">
      <el-col :lg="18" :md="18" :sm="24" :xl="18" :xs="24">
        <el-card shadow="hover">
          <template #header>
            <span>{{ detail.title }}</span>
          </template>
          <v-md-preview ref="markdown" :text="detail.content"></v-md-preview>
        </el-card>
      </el-col>
      <el-col :lg="6" :md="6" :sm="24" :xl="6" :xs="24">
        <el-card shadow="hover">
          <template #header>
            <span><el-icon style="margin-right: 5px"><Menu/></el-icon>目录</span>
          </template>
          <p>
            <ul>
              <li v-for="item in form.data.directoryList" :key="item">
                {{ item.text }}
              </li>
            </ul>
          </p>
        </el-card>
      </el-col>
    </el-row>
    <el-button v-if="detail.userId === user.id" icon="Edit" plain type="success" @click="handleEdit">修改</el-button>
    <el-popconfirm v-if="detail.userId === user.id" title="确认删除该博客吗？" @confirm="handleDelete">
      <template #reference>
        <el-button icon="Delete" plain style="margin-left: 10px" type="danger">
          删除
        </el-button>
      </template>
    </el-popconfirm>
    <el-button @click="handleCommentList">查看评论</el-button>
    <el-drawer v-model="form.visible" :close-on-click-modal="false" :title="form.title">
      <el-row>
        <el-col :span="2">
          <el-avatar :src="getAvatar(null)"/>
        </el-col>
        <el-col :span="22">
          <el-form ref="formRef" :model="form.data" :rules="rules">
            <el-form-item prop="content">
              <el-input v-model="form.data.content" :rows="8" placeholder="欢迎高质量的评论，低质的评论会被折叠"
                        type="textarea"></el-input>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
      <el-button round size="large" @click="handleComment">评论</el-button>
      <div v-if="form.commentList && form.commentList.length > 0" class="comment-list">
        <div v-for="item in form.commentList" :key="item.id">
          <el-row>
            <el-col :span="2">
              <el-avatar :src="getAvatar(item.user.avatar)"></el-avatar>
            </el-col>
            <el-col :span="21">
              <el-row>
                <span class="comment-item">{{ item.user.name || item.user.username }}</span>
                <span class="comment-item">{{ item.createTime }}</span>
              </el-row>
              <el-row>
                <p v-html="item.content"></p>
              </el-row>
            </el-col>
            <el-col :span="1">
              0<i class="iconfont icon-zan" style="margin-left: 5px"></i>
            </el-col>
          </el-row>
        </div>
      </div>
      <el-empty v-else description="暂时没有评论哦！"/>
    </el-drawer>
  </div>
</template>

<script>
import {getArticleOne, removeArticleBatchByIds} from "@/api/article.js";
import {getCommentList, saveComment} from "@/api/comment.js";

export default {
  name: "BlogDetailView",
  data () {
    return {
      user: JSON.parse(localStorage.getItem('user') || '{}'),
      queryParams: {
        id: this.$route.params.id
      },
      detail: {},
      form: {
        visible: false,
        title: '',
        data: {
          directoryList: []
        },
        commentList: []
      },
      rules: {
        content: [{required: true, message: '请输入评论内容', trigger: 'blur'}]
      }
    }
  },
  watch: {
    '$route.params.id': 'getDetail'
  },
  created () {
    this.getDetail()
  },
  computed: {
    getUrl () {
      return (path) => {
        return import.meta.env.VITE_APP_BASE_API + path
      }
    },
    getAvatar () {
      return (path) => {
        return path ? this.getUrl(path) : (path ? this.getUrl(path) : new URL('@/assets/imgs/profile.png', import.meta.url))
      }
    }
  },
  methods: {
    getDetail () {
      this.queryParams.id = this.$route.params.id
      getArticleOne(this.queryParams).then(res => {
        this.detail = res.data
      }).finally(() => {
        this.$nextTick(() => this.handleDirectory())
      })
    },
    handleCommentList () {
      const params = {
        blogId: this.$route.params.id
      }
      getCommentList(params).then(res => {
        this.form.commentList = res.data || []
        this.form.visible = true
        this.form.title = `评论${res.data.length}`
      })
      this.form.data.blogId = this.$route.params.id
    },
    handleComment () {
      this.$refs.formRef.validate(valid => {
        if (!valid) return
        saveComment(this.form.data).then(res => {
          if (res.code === 200) {
            this.$message.success('保存成功！')
            this.form.data.content = ''
          } else {
            this.$message.error(res.msg)
          }
        }).finally(() => {
          const params = {
            blogId: this.$route.params.id
          }
          getCommentList(params).then(res => {
            this.form.commentList = res.data || []
            this.form.title = `评论${res.data.length}`
          })
          this.form.data.blogId = this.$route.params.id
        })
      })
    },
    handleDirectory () {
      const el = this.$refs.markdown.$el
      const headers = el.querySelectorAll("h1, h2, h3, h4, h5, h6");
      const directoryList = []

      headers.forEach(header => {
        console.log(header)
        directoryList.push({
          id: header.id,
          text: header.innerText,
          depth: parseInt(header.tagName.replace('H', ''), 10)
        })
      })
      this.form.data.directoryList = directoryList
      console.log(directoryList)
    },
    handleEdit () {
      this.$router.push(`/blog/${this.queryParams.id}`)
    },
    handleDelete () {
      removeArticleBatchByIds(this.queryParams.id).then(res => {
        if (res.code === 200) {
          this.$message.success('删除成功！')
        } else {
          this.$message.error(res.msg)
        }
      }).finally(() => {
        this.$router.push('/')
      })
    }
  }
}
</script>


<style lang="scss" scoped>
.el-card {
  margin: 10px 0;
}

.iconfont:hover {
  color: #f00;
}

.comment-list {
  margin-top: 20px;

  .comment-item {
    color: gray;
    margin-right: 15px;
  }
}
</style>
