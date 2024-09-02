import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
// 状态存储
import pinia from './store'
// 饿了么UI
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import 'element-plus/dist/index.css'
import 'element-plus/theme-chalk/display.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
// 字体图标
import '@/assets/icons/iconfont.css'
// Excel导出
import vue3JsonExcel from "vue3-json-excel"
// MD文档编辑
import VueMarkdownEditor from '@kangc/v-md-editor'
import '@kangc/v-md-editor/lib/style/base-editor.css'
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js'
import '@kangc/v-md-editor/lib/theme/style/vuepress.css'
import Prism from 'prismjs'
// MD文档预览
import VMdPreview from '@kangc/v-md-editor/lib/preview'
import '@kangc/v-md-editor/lib/style/preview.css'
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js'
import '@kangc/v-md-editor/lib/theme/style/github.css'
import hljs from 'highlight.js'
// 菜单组件
import MenuItem from '@/components/MenuItem/index.js'
// 面包屑导航组件
import Breadcrumb from '@/components/Breadcrumb/index.js'
// 头像上传组件
import AvatarUpload from "@/components/AvatarUpload/index.js";
// 图标选择组件
import IconPicker from "@/components/IconPicker/index.js";

VueMarkdownEditor.use(vuepressTheme, {
    Prism
})
VMdPreview.use(githubTheme, {
    Hljs: hljs
})

const app = createApp(App)
app.use(router)
app.use(pinia)
app.use(ElementPlus, {size: 'small', locale: zhCn})
// 禁止点击空白处关闭对话框
app._context.components.ElDialog.props.closeOnClickModal.default = false
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.use(vue3JsonExcel)
app.use(VueMarkdownEditor)
app.use(VMdPreview)
app.component('MenuItem', MenuItem)
app.component('Breadcrumb', Breadcrumb)
app.component('AvatarUpload', AvatarUpload)
app.component('IconPicker', IconPicker)
app.mount('#app')
