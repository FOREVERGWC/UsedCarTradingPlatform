export const constantRoutes = [
    {
        path: '',
        name: '',
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
    // {
    //     path: '/backend',
    //     name: 'backend-home',
    //     meta: {name: '首页', icon: 'House', hidden: false},
    //     redirect: '/dashboard',
    //     component: () => import('@/views/backend/index.vue'),
    //     children: [
    //         {
    //             path: '/article',
    //             name: 'backend-article',
    //             meta: {name: '文章管理', icon: 'Stopwatch', hidden: false},
    //             redirect: '/article-index',
    //             children: [
    //                 {
    //                     path: '/label-link',
    //                     name: 'article-label-link',
    //                     meta: {name: '文章、文章标签', icon: 'Stopwatch', hidden: false},
    //                     component: () => import('@/views/backend/article/label/link/index.vue')
    //                 }
    //             ]
    //         },
    //         {
    //             path: '/chat',
    //             name: 'chat',
    //             meta: {name: '聊天', icon: 'ChatLineRound', hidden: false},
    //             component: () => import('@/views/backend/chat/index.vue')
    //         },
    //         {
    //             path: '/comment',
    //             name: 'comment',
    //             meta: {name: '评论', icon: 'ChatDotSquare', hidden: false},
    //             component: () => import('@/views/backend/comment/index.vue')
    //         },
    //         {
    //             path: '/follow',
    //             name: 'follow',
    //             meta: {name: '关注信息', icon: 'Stopwatch', hidden: false},
    //             component: () => import('@/views/backend/follow/index.vue')
    //         },
    //         {
    //             path: '/statistics',
    //             name: 'statistics',
    //             meta: {name: '统计信息', icon: 'Stopwatch', hidden: false},
    //             component: () => import('@/views/backend/statistics/index.vue')
    //         }
    //     ]
    // },
    // {
    //     path: '/',
    //     name: 'home',
    //     meta: {name: '首页', icon: 'Home', hidden: true},
    //     redirect: '/index',
    //     component: () => import('@/views/frontend/index.vue'),
    //     children: [
    //         {
    //             path: '/index',
    //             name: 'index',
    //             meta: {name: '首页', icon: 'Stopwatch', hidden: false},
    //             component: () => import('@/views/frontend/index/index.vue')
    //         },
    //         {
    //             path: '/detail/:id',
    //             name: 'blog-detail',
    //             meta: {name: '博客详情', icon: 'Stopwatch', hidden: false},
    //             component: () => import('@/views/frontend/index/detail.vue')
    //         },
    //         {
    //             path: '/main/:id',
    //             name: 'main',
    //             meta: {name: '个人主页', icon: 'Stopwatch', hidden: false},
    //             component: () => import('@/views/frontend/main/index.vue')
    //         },
    //         {
    //             path: '/blog/:id',
    //             name: 'blog',
    //             meta: {name: '发布文章', icon: 'Stopwatch', hidden: false},
    //             component: () => import('@/views/frontend/blog/index.vue')
    //         },
    //         {
    //             path: '/center',
    //             name: 'center',
    //             meta: {name: '个人中心', icon: 'Stopwatch', hidden: false},
    //             component: () => import('@/views/backend/user/center/index.vue')
    //         }
    //     ]
    // },
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
