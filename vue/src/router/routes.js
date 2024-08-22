const routes = [
    {
        path: '/backend',
        name: 'backend-home',
        meta: {name: '首页', roles: ['1', '2'], icon: 'House', hidden: false},
        redirect: '/dashboard',
        component: () => import('@/views/backend/index.vue'),
        children: [
            {
                path: '/dashboard',
                name: 'dashboard',
                meta: {name: '仪表盘', roles: ['1', '2'], icon: 'Odometer', hidden: false},
                component: () => import('@/views/backend/dashboard/index.vue')
            },
            {
                path: '/system',
                name: 'backend-system',
                meta: {name: '系统管理', roles: ['1'], icon: 'House', hidden: false},
                redirect: '/user',
                children: [
                    {
                        path: '/user',
                        name: 'user',
                        meta: {name: '用户管理', roles: ['1'], icon: 'User', hidden: false},
                        component: () => import('@/views/backend/user/index.vue')
                    },
                    {
                        path: '/role',
                        name: 'role',
                        meta: {name: '角色管理', roles: ['1'], icon: 'User', hidden: false},
                        component: () => import('@/views/backend/role/index.vue')
                    },
                ]
            },
            {
                path: '/article',
                name: 'backend-article',
                meta: {name: '文章管理', roles: ['1', '2'], icon: 'Stopwatch', hidden: false},
                redirect: '/article-index',
                children: [
                    {
                        path: '/article-index',
                        name: 'article-index',
                        meta: {name: '文章', roles: ['1', '2'], icon: 'Stopwatch', hidden: false},
                        component: () => import('@/views/backend/article/index.vue')
                    },
                    {
                        path: '/category',
                        name: 'article-category',
                        meta: {name: '文章类别', roles: ['1', '2'], icon: 'Stopwatch', hidden: false},
                        component: () => import('@/views/backend/article/category/index.vue')
                    },
                    {
                        path: '/label',
                        name: 'article-label',
                        meta: {name: '文章标签', roles: ['1', '2'], icon: 'Stopwatch', hidden: false},
                        component: () => import('@/views/backend/article/label/index.vue')
                    },
                    {
                        path: '/label-link',
                        name: 'article-label-link',
                        meta: {name: '文章、文章标签', roles: ['1', '2'], icon: 'Stopwatch', hidden: false},
                        component: () => import('@/views/backend/article/label/link/index.vue')
                    }
                ]
            },
            {
                path: '/chat',
                name: 'chat',
                meta: {name: '聊天', roles: ['1', '2'], icon: 'ChatLineRound', hidden: false},
                component: () => import('@/views/backend/chat/index.vue')
            },
            {
                path: '/comment',
                name: 'comment',
                meta: {name: '评论', roles: ['1', '2'], icon: 'ChatDotSquare', hidden: false},
                component: () => import('@/views/backend/comment/index.vue')
            },
            {
                path: '/follow',
                name: 'follow',
                meta: {name: '关注信息', roles: ['1'], icon: 'Stopwatch', hidden: false},
                component: () => import('@/views/backend/follow/index.vue')
            },
            {
                path: '/center2',
                name: 'center2',
                meta: {name: '个人中心', roles: [], icon: 'Stopwatch', hidden: false},
                component: () => import('@/views/backend/user/center/index.vue')
            },
            {
                path: '/statistics',
                name: 'statistics',
                meta: {name: '统计信息', roles: ['1'], icon: 'Stopwatch', hidden: false},
                component: () => import('@/views/backend/statistics/index.vue')
            }
        ]
    },
    {
        path: '/',
        name: 'home',
        meta: {name: '首页', roles: ['1', '2'], icon: 'Home', hidden: true},
        redirect: '/index',
        component: () => import('@/views/frontend/index.vue'),
        children: [
            {
                path: '/index',
                name: 'index',
                meta: {name: '首页', roles: [], icon: 'Stopwatch', hidden: false},
                component: () => import('@/views/frontend/index/index.vue')
            },
            {
                path: '/detail/:id',
                name: 'blog-detail',
                meta: {name: '博客详情', roles: [], icon: 'Stopwatch', hidden: false},
                component: () => import('@/views/frontend/index/detail.vue')
            },
            {
                path: '/main/:id',
                name: 'main',
                meta: {name: '个人主页', roles: [], icon: 'Stopwatch', hidden: false},
                component: () => import('@/views/frontend/main/index.vue')
            },
            {
                path: '/blog/:id',
                name: 'blog',
                meta: {name: '发布文章', roles: [], icon: 'Stopwatch', hidden: false},
                component: () => import('@/views/frontend/blog/index.vue')
            },
            {
                path: '/center',
                name: 'center',
                meta: {name: '个人中心', roles: [], icon: 'Stopwatch', hidden: false},
                component: () => import('@/views/backend/user/center/index.vue')
            }
        ]
    },
    {
        path: '/login',
        name: 'Login',
        meta: {name: '登录', roles: [], icon: 'Stopwatch', hidden: true},
        component: () => import('@/views/LoginView.vue')
    },
    {
        path: '/register',
        name: 'Register',
        meta: {name: '注册', roles: [], icon: 'Stopwatch', hidden: true},
        component: () => import('@/views/RegisterView.vue')
    },
    {
        path: '/:pathMatch(.*)*',
        meta: {name: '404', roles: [], icon: 'Stopwatch', hidden: true},
        component: () => import('@/views/404.vue')
    }
]

export default routes
