<template>
  <div style="margin-top: 20px">
    <el-form ref="formRef" :model="form.data" :rules="rules" label-width="80px">
      <el-form-item label="标题" prop="title">
        <el-input v-model="form.data.title" autocomplete="new"></el-input>
      </el-form-item>
      <el-form-item label="内容" prop="content">
        <v-md-editor v-model="form.data.content" height="100vh"></v-md-editor>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="form.data.remark" :rows="5" autocomplete="new" type="textarea"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="handleSave">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {getArticleOne, saveArticle} from "@/api/article";

export default {
  name: "BlogView",
  data () {
    return {
      queryParams: {
        id: this.$route.params.id
      },
      form: {
        visible: false,
        title: '',
        data: {}
      },
      rules: {
        title: [{required: true, message: '请输入标题', trigger: 'blur'}],
        content: [{required: true, message: '请输入内容', trigger: 'blur'}]
      }
    }
  },
  watch: {
    '$route.params.id': 'getDetail'
  },
  created () {
    this.getDetail()
  },
  methods: {
    getDetail () {
      this.queryParams.id = this.$route.params.id
      if (this.queryParams.id === undefined || this.queryParams.id === null) {
        return
      }
      getArticleOne(this.queryParams).then(res => {
        this.form.data = res.data || {}
      })
    },
    handleSave () {
      this.$refs.formRef.validate(valid => {
        if (!valid) return
        saveArticle(this.form.data).then(res => {
          if (res.code === 200) {
            this.$message.success('保存成功！')
            this.form.visible = false
          } else {
            this.$message.error(res.msg)
          }
        }).finally(() => {
          this.$router.push('/index')
        })
      })
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
