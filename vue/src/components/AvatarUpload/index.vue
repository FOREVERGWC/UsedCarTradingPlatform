<template>
  <el-upload
      class="avatar-uploader"
      :show-file-list="false"
      :before-upload="beforeUpload"
      :http-request="customUpload"
  >
    <img v-if="url" :src="url" class="avatar" alt=""/>
    <el-icon v-else class="avatar-uploader-icon">
      <Plus/>
    </el-icon>
  </el-upload>
</template>

<script setup>
import {ref, watch, onMounted} from 'vue'
import {ElMessage} from 'element-plus'
import {uploadFile} from '@/api/file'

const props = defineProps({
  modelValue: {
    type: String,
    required: false,
    default: ''
  }
})

const emit = defineEmits(['update:modelValue'])

const url = ref('')

const updateUrl = () => {
  if (props.modelValue) {
    url.value = `${import.meta.env.VITE_APP_BASE_API}${props.modelValue}`
  }
}

watch(() => props.modelValue, updateUrl)

onMounted(updateUrl)

const beforeUpload = (file) => {
  if (file.type !== 'image/jpeg') {
    ElMessage.error('Avatar picture must be JPG format!')
    return false
  } else if (file.size / 1024 / 1024 > 2) {
    ElMessage.error('Avatar picture size can not exceed 2MB!')
    return false
  }
}

const customUpload = (params) => {
  uploadFile(params.file).then(res => {
    if (res.code !== 200) {
      ElMessage.error(res.msg)
      params.onError()
      return
    }
    emit('update:modelValue', res.data)
    url.value = `${import.meta.env.VITE_APP_BASE_API}${res.data}`
    ElMessage.success('上传成功！')
    params.onSuccess()
  })
}
</script>

<style lang="scss" scoped>
.avatar-uploader {
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }

  :deep(.el-upload) {
    border: 1px dashed var(--el-border-color);
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);

    &:hover {
      border-color: var(--el-color-primary);
    }
  }

  :deep(.avatar-uploader-icon) {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    text-align: center;
    line-height: 178px;
  }
}
</style>
