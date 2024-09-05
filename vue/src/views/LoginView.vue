<template>
  <div class="main-container">
    <el-card class="main-card">
      <h2 class="title">{{ title }}</h2>
      <el-form :model="form" :rules="rules" ref="formRef" size="default">
        <el-form-item prop="username">
          <el-input v-model="form.username" placeholder="用户名" prefix-icon="User"/>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" type="password" placeholder="密码" prefix-icon="Lock" show-password/>
        </el-form-item>
        <el-form-item prop="code" class="code" v-if="enabled">
          <el-input v-model="form.code" placeholder="验证码" prefix-icon="Message"/>
          <img :src="captcha" @click="handleCaptcha" alt="验证码"/>
        </el-form-item>
        <el-form-item class="remember-forgot">
          <div class="remember-me">
            <el-checkbox v-model="form.rememberMe">记住我</el-checkbox>
          </div>
          <div class="forgot-password">
            <el-link href="/reset-password" type="primary" :underline="false">忘记密码</el-link>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleLogin">登录</el-button>
        </el-form-item>
      </el-form>
      <div class="register-link">
        <span>还没有账号？请</span>
        <el-link type="primary" :underline="false" href="/register">注册</el-link>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue';
import {useRouter} from 'vue-router';
import {getCaptcha} from "@/api/auth.js";
import {ElMessage} from "element-plus";
import useUserStore from "@/store/modules/user.js";

const router = useRouter();
const userStore = useUserStore()

const title = ref(import.meta.env.VITE_APP_TITLE);
const enabled = ref(true)
const captcha = ref('')
const form = ref({
  username: '',
  password: '',
  code: '',
  rememberMe: true,
  uuid: ''
});
const rules = {
  username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
  password: [{required: true, message: '请输入密码', trigger: 'blur'}],
  code: [{required: true, message: '请输入验证码', trigger: 'blur'}]
};
const formRef = ref(null)

const handleCaptcha = () => {
  getCaptcha().then(res => {
    if (res.code !== 200) {
      ElMessage.error(res.msg);
      return
    }
    enabled.value = res.data.enabled
    form.value.uuid = res.data.uuid
    captcha.value = res.data.img
  })
}

const handleLogin = () => {
  formRef.value.validate(valid => {
    if (!valid) return
    userStore.handleLogin(form.value).then(() => {
      router.replace('/')
    })
  });
};

onMounted(() => {
  handleCaptcha()
})
</script>

<style scoped lang="scss">
.main-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #F3F8FF;
  padding: 20px;

  .main-card {
    width: 100%;
    max-width: 400px;
    padding: 20px;
    box-sizing: border-box;

    .title {
      text-align: center;
      font-weight: bold;
      margin-bottom: 20px;
    }

    .code {
      display: flex;
      justify-content: space-between;
      align-items: center;

      .el-input {
        flex: 1;
        margin-right: 10px;
      }

      img {
        cursor: pointer;
        width: 100px;
        height: 32px;
        flex-shrink: 0;
      }
    }

    .remember-forgot {
      display: flex;
      justify-content: space-between;
      align-items: center;

      .remember-me {
        flex: 1;
        text-align: left;
      }

      .forgot-password {
        flex: 1;
        text-align: right;
      }
    }

    .el-button {
      width: 100%;
    }

    .register-link {
      display: flex;
      justify-content: flex-end;
      margin-top: 10px;

      span {
        margin-right: 2px;
      }
    }
  }
}
</style>
