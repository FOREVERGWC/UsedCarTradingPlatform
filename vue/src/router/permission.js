import router from './index.js'
import useUserStore from '@/store/modules/user'
import usePermissionStore from '@/store/modules/permission'

const whiteList = ['/login', '/register', '/reset'];

router.beforeEach((to, from, next) => {
    const userStore = useUserStore();
    const permissionStore = usePermissionStore();
    const token = userStore.token;

    // 白名单直接放行
    if (whiteList.includes(to.path)) {
        return next();
    }

    // 无 token，跳转登录页
    if (!token) {
        return next(`/login?redirect=${to.fullPath}`);
    }

    // 已登录用户访问登录页，重定向首页
    if (to.path === '/login') {
        return next({path: '/'});
    }

    // 获取用户信息并加载权限路由
    if (userStore.roleIdList.length === 0) {
        userStore.getInfo().then(() => {
            loadDynamicRoutes(permissionStore, to, next);
        }).catch(() => {
            next({path: '/'});
        });
    } else if (!permissionStore.routesAdded) {
        // 如果路由还没加载，则加载动态路由
        loadDynamicRoutes(permissionStore, to, next);
    } else {
        // 如果路由已加载，直接放行
        next();
    }
});

const loadDynamicRoutes = (permissionStore, to, next) => {
    permissionStore.generateRoutes().then(accessRoutes => {
        accessRoutes.forEach(route => {
            router.addRoute(route);
        });
        permissionStore.routesAdded = true;
        next({...to, replace: true});
    });
}

router.afterEach((to) => {
    document.title = to.meta?.title || import.meta.env.VITE_APP_TITLE;
})
