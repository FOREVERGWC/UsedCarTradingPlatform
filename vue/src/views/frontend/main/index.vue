<template>
  <div>
    <el-row :gutter="20" justify="center">
      <el-col :lg="6" :md="20" :sm="22" :xl="6" :xs="22">
        <UserProfile
            :user="user"
            :articleCount="articleList.length"
        />

        <CategoryCard :userId="user.id"/>

        <LabelCard :userId="user.id"/>

        <el-card class="category-card">
          <template #header>
            <span class="card-header-title">
              <i class="iconfont icon-guidang"/>
              归档
            </span>
          </template>
          <ul>
            <li v-for="item in articleList" :key="item.id">
              {{ item.title }}
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
            <li v-for="item in articleList" :key="item.id">
              {{ item.title }}
            </li>
          </ul>
        </el-card>
      </el-col>

      <el-col :lg="12" :md="20" :sm="22" :xl="12" :xs="22" v-loading="loading">
        <div v-if="articleList && articleList.length > 0">
          <ArticleItem
              v-for="(item, index) in articleList"
              :key="item.id"
              :article="item"
              :reverse="index % 2 === 1"
          />
          <div class="pagination-container">
            <el-pagination class="hidden-sm-and-down"
                           :current-page="queryParams.pageNo"
                           :page-size="queryParams.pageSize"
                           :page-sizes="[5, 10, 15, 20]"
                           :total="total"
                           layout="total, sizes, prev, pager, next, jumper"
                           @size-change="handleSizeChange"
                           @current-change="handleCurrentChange">
            </el-pagination>
            <el-pagination class="hidden-md-and-up"
                           :current-page="queryParams.pageNo"
                           :page-size="queryParams.pageSize"
                           :page-sizes="[5, 10, 15, 20]"
                           :total="total"
                           layout="prev, pager, next"
                           @size-change="handleSizeChange"
                           @current-change="handleCurrentChange">
            </el-pagination>
          </div>
        </div>
        <el-empty v-else description="暂时没有文章哦！"/>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import {UserProfile, CategoryCard, LabelCard, ArticleItem} from "@/views/frontend/main/components";
import {onMounted, reactive, ref} from "vue";
import useUserStore from "@/store/modules/user.js";
import {useRoute} from "vue-router";
import {getUserOne} from "@/api/user.js";
import {getArticlePage} from "@/api/article.js";

const route = useRoute()
const userStore = useUserStore()

const userId = route.params.id
const loading = ref(true)
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  userId: userId
})
const user = ref({
  id: userId,
  username: '',
  name: '',
  avatar: '',
  remark: ''
})
const articleList = ref([])
const total = ref(0)

const getDetail = () => {
  loading.value = true
  getUserOne({id: userId}).then(res => {
    user.value = res.data
  })
  getArticlePage(queryParams).then(res => {
    articleList.value = res.data?.records || []
    total.value = res.data?.total || 0
    loading.value = false
  })
}

const handleSizeChange = (val) => {
  queryParams.pageSize = val
  getDetail()
}

const handleCurrentChange = (val) => {
  queryParams.pageNo = val
  getDetail()
}

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

.category-card {
  :deep(.el-card__header) {
    border-bottom: none;
  }
}

.pagination-container {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}
</style>
