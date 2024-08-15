<template>
  <div class='container'>
    <div class='main'>
      <div class='header'>{{ title }}</div>
      <el-form ref='formRef' :model='loginForm' :rules='rules'>
        <el-form-item prop='username'>
          <el-input v-model='loginForm.username' placeholder='请输入账号' prefix-icon='User'/>
        </el-form-item>
        <el-form-item prop='password'>
          <el-input v-model='loginForm.password' placeholder='请输入密码' prefix-icon='Lock' show-password/>
        </el-form-item>
        <el-form-item>
          <div class='tip'>
            <el-checkbox v-model='loginForm.remember'>记住我</el-checkbox>
            <span class='forgot-password' @click='showRetrieve'>忘记密码</span>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button class='btn-login' @click='handleLogin'>登 录</el-button>
        </el-form-item>
        <div style='display: flex; align-items: center'>
          <div style='flex: 1; text-align: right'>
            还没有账号？请 <a href='/register'>注册</a>
          </div>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import {ref} from 'vue'
import {useRouter} from 'vue-router'
import {login} from '@/api/web.js'
import {ElMessage} from 'element-plus'
import useUserStore from "@/store/modules/user.js";

const userStore = useUserStore()

// 组件名称
const title = ref(import.meta.env.VITE_APP_TITLE)

// 表单数据
const loginForm = ref({
  username: '',
  password: '',
  remember: false
})

// 表单验证规则
const rules = {
  username: [{required: true, message: '请输入账号', trigger: 'blur'}],
  password: [{required: true, message: '请输入密码', trigger: 'blur'}]
}

// 路由和消息提示
const router = useRouter()

// 表单引用
const formRef = ref(null)

// 登录处理函数
const handleLogin = () => {
  formRef.value.validate((valid) => {
    if (!valid) return
    login(loginForm.value).then(res => {
      if (res.code !== 200) {
        ElMessage.error(res.msg)
        return
      }
      userStore.setUser(res.data)
      // localStorage.setItem('user', JSON.stringify(res.data))
      if (res.data.role === '0') {
        router.push('/backend')
      } else {
        router.push('/')
      }
      ElMessage.success('登录成功')
    })
  })
}

// 找回密码处理函数
const showRetrieve = () => {
  console.log('找回密码')
}
</script>

<style lang='scss' scoped>
.container {
  height: 100vh;
  overflow: hidden;
  background-image: url("@/assets/imgs/bg.jpeg");
  background-size: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #666;
  user-select: none;

  .main {
    width: 400px;
    padding: 30px;
    background-color: white;
    border-radius: 5px;

    .header {
      text-align: center;
      font-weight: bold;
      font-size: 20px;
      margin-bottom: 20px;
      color: #333
    }

    .tip {
      display: flex;
      justify-content: space-between;
      align-items: center;
      font-size: 13px;

      .forgot-password {
        color: #889aa4;

        &:hover {
          text-decoration: underline;
          cursor: pointer;
        }
      }
    }

    .btn-login {
      width: 100%;
      background-color: #817a70;
      border-color: #817a70;
      color: white;
    }
  }
}

a {
  color: #2a60c9;
}
</style>
