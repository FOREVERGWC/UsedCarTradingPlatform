import {createRouter, createWebHistory} from 'vue-router'
import {constantRoutes} from "@/router/routes.js";

const router = createRouter({
    history: createWebHistory(),
    routes: constantRoutes
})

// /**
//  * 路由守卫
//  */
// const whiteList = ['/login', '/register', '/index']
// router.beforeEach((to, from, next) => {
//     // if (whiteList.indexOf(to.path) !== -1) {
//     //     next()
//     //     return
//     // }
//     // if (to.path.includes('/detail/') || to.path.includes('/main/')) {
//     //     next()
//     //     return
//     // }
//     // const token = JSON.parse(localStorage.getItem('user') || '{}').token
//     // if (!token) {
//     //     next('/login')
//     //     return
//     // }
//     next()
// })
//
// router.afterEach((to) => {
//     document.title = to.meta?.title || import.meta.env.VITE_APP_TITLE;
// });

export default router
