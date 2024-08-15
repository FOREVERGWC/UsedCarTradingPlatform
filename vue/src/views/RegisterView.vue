<template>
	<div class='container'>
		<div class='main'>
			<div class='header'>{{ title }}</div>
			<el-form ref='formRef' :model='form' :rules='rules'>
				<el-form-item prop='username'>
					<el-input v-model='form.username' placeholder='请输入账号' prefix-icon='User'></el-input>
				</el-form-item>
				<el-form-item prop='password'>
					<el-input v-model='form.password' placeholder='请输入密码' prefix-icon='Lock'
										show-password></el-input>
				</el-form-item>
				<el-form-item prop='confirmPass'>
					<el-input v-model='form.confirmPass' placeholder='请确认密码' prefix-icon='Lock'
										show-password></el-input>
				</el-form-item>
				<el-form-item>
					<el-button class='btn-register' @click='register'>注 册</el-button>
				</el-form-item>
				<div style='display: flex; align-items: center'>
					<div style='flex: 1'></div>
					<div style='flex: 1; text-align: right'>
						已有账号？请 <a href='/login'>登录</a>
					</div>
				</div>
			</el-form>
		</div>
	</div>
</template>

<script>
import { register } from '@/api/web'

export default {
	name: 'RegisterView',
	data() {
		// 验证码校验
		const validatePassword = (rule, confirmPass, callback) => {
			if (confirmPass === '') {
				callback(new Error('请确认密码'))
			} else if (confirmPass !== this.form.password) {
				callback(new Error('两次输入的密码不一致'))
			} else {
				callback()
			}
		}
		return {
			title: import.meta.env.VITE_APP_TITLE,
			form: {
				username: '',
				password: '',
				confirmPass: '',
				role: 'USER'
			},
			rules: {
				username: [{ required: true, message: '请输入账号', trigger: 'blur' }],
				password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
				confirmPass: [{ required: true, validator: validatePassword, trigger: 'blur' }]
			}
		}
	},
	created() {

	},
	methods: {
		register() {
			this.$refs.formRef.validate(valid => {
				if (!valid) return
				register(this.form).then(res => {
					if (res.code === 200) {
						this.$router.push('/login')
						this.$message.success('注册成功')
					} else {
						this.$message.error(res.msg)
					}
				})
			})
		}
	}
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

		.btn-register {
			width: 100%;
			background-color: #333;
			border-color: #333;
			color: white;
		}
	}
}

a {
	color: #2a60c9;
}
</style>
