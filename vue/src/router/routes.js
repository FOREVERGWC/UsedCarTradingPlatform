export const constantRoutes = [
    {
        path: '/admin',
        name: 'Admin',
        meta: { title: '首页', icon: 'dashboard', hidden: true },
        redirect: '/index',
        component: () => import('@/views/backend/index.vue'),
        children: [
            {
                path: '/index',
                name: 'Index',
                meta: { title: '首页', icon: 'dashboard', hidden: true },
                component: () => import('@/views/backend/index/index.vue')
            },
            {
                path: '/profile',
                name: 'Profile',
                meta: {title: '个人中心', icon: 'Stopwatch', hidden: false},
                component: () => import('@/views/backend/profile/index.vue')
            }
        ]
    },
    {
        path: '/',
        name: 'index',
        meta: { title: '首页', icon: '', hidden: false },
        redirect: '/home',
        component: () => import('@/views/frontend/index.vue'),
        children: [
            {
                path: '/home',
                name: 'home',
                meta: { title: '首页', icon: '', hidden: false },
                component: () => import('@/views/frontend/home/index.vue')
            },
            {
                path: '/buy',
                name: 'buy',
                meta: { title: '我要买车', icon: '', hidden: false },
                component: () => import('@/views/frontend/buy/index.vue')
            },
            {
                path: '/sell',
                name: 'sell',
                meta: { title: '我要卖车', icon: '', hidden: false },
                component: () => import('@/views/frontend/sell/index.vue')
            },
            {
                path: '/change',
                name: 'change',
                meta: { title: '我要置换', icon: '', hidden: false },
                component: () => import('@/views/frontend/change/index.vue')
            },
            {
                path: '/query',
                name: 'query',
                meta: { title: '搜索', icon: '', hidden: false },
                component: () => import('@/views/frontend/query/index.vue')
            },
            {
                path: '/profile',
                name: 'profile',
                meta: { title: '个人中心', icon: '', hidden: false },
                component: () => import('@/views/frontend/profile/index.vue')
            }
        ]
    },
    {
        path: '/login',
        name: 'Login',
        meta: {title: '登录', icon: 'Stopwatch', hidden: true},
        component: () => import('@/views/LoginView.vue')
    },
    {
        path: '/register',
        name: 'Register',
        meta: {title: '注册', icon: 'Stopwatch', hidden: true},
        component: () => import('@/views/RegisterView.vue')
    },
    {
        path: '/reset',
        name: 'Reset',
        meta: {title: '找回密码', icon: 'Stopwatch', hidden: true},
        component: () => import('@/views/ResetView.vue')
    },
    {
        path: '/:pathMatch(.*)*',
        name: 'NotFound',
        meta: {title: '404', icon: 'Stopwatch', hidden: true},
        component: () => import('@/views/404.vue')
    }
]

export const dynamicRoutes = []
