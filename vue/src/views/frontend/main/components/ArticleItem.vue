<template>
  <el-card class="article-item">
    <el-row :class="{ reverse }">
      <el-col class="image-col" :lg="8" :md="8" :sm="24" :xl="8" :xs="24">
        <div class="image-container">
          <el-image class="hover-zoom" fit="cover" :src="preview()"/>
        </div>
      </el-col>
      <el-col class="content-col" :lg="16" :md="16" :sm="24" :xl="16" :xs="24">
        <el-link :href="`/detail/${article.id}`" :underline="false" target="_blank">
          <h2>{{ article.title }}</h2>
        </el-link>
        <p class="article-info">
          <span class="article-info-item">
            <i class="iconfont icon-24gl-calendar"/>
            {{ article.releaseTime }}
          </span>
          <span class="article-info-inline">
            <span class="article-info-item">
              <i class="iconfont icon-icon-"/>
              {{ article.viewCount }}
            </span>
            <span class="article-info-item">
              <i class="iconfont icon-zan"/>
              {{ article.likeCount }}
            </span>
            <span class="article-info-item">
              <i class="iconfont icon-pinglun"/>
              {{ article.commentCount }}
            </span>
            <span class="article-info-item">
              <i class="iconfont icon-aixin"/>
              {{ article.collectionCount }}
            </span>
          </span>
        </p>
        <p class="article-summary" v-html="`摘要：${article.content}`"/>
      </el-col>
    </el-row>
  </el-card>
</template>

<script setup>
import {defineProps} from 'vue'

const props = defineProps({
  article: {
    type: Object,
    required: true,
  },
  reverse: {
    type: Boolean,
    default: false,
  },
})

const preview = () => {
  return 'https://via.placeholder.com/400x252'
}
</script>

<style scoped lang="scss">
.el-card {
  height: 252px;

  :deep(.el-card__body) {
    padding: 0;
  }

  .image-container {
    position: relative;
    overflow: hidden;
    height: 100%;
    width: 100%;

    .el-image {
      width: 100%;
      height: 100%;

      transition: transform 0.4s ease;
      transform-origin: center center;

      &:hover {
        transform: scale(1.2);
      }
    }
  }

  .el-link {
    h2 {
      margin-bottom: 12px;
    }
  }

  .article-info {
    margin-bottom: 12px;
    color: #858585;
    display: flex;
    flex-wrap: wrap;

    .article-info-item {
      margin-right: 10px;
    }
  }

  .article-summary {
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
    line-clamp: 2;
    overflow: hidden;
    text-overflow: ellipsis;
    max-height: 72px;
    margin-bottom: 12px;
  }
}

.article-item .el-row {
  display: flex;
  align-items: center;

  &.reverse .image-col {
    order: 2;
  }

  &.reverse .content-col {
    order: 1;
  }
}

.article-item .content-col {
  padding: 24px;
}

@media screen and (max-width: 768px) {
  .article-item {
    height: auto;
  }

  .article-item .el-row {
    flex-direction: column !important;
  }

  .article-item .image-col,
  .article-item .content-col {
    width: 100%;
    padding: 0;
  }

  .el-image {
    transform: none !important;
  }

  .article-item .content-col {
    padding: 16px;
  }

  .article-item .el-row.reverse .image-col,
  .article-item .el-row.reverse .content-col {
    order: unset !important;
  }

  .article-summary {
    max-height: 48px;
  }
}
</style>
