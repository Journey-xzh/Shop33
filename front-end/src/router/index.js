import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            redirect: '/login',
        },
        {
            path: '/user',
            component: () => import('../views/user/MainPage.vue'),
            meta: { side: 'user' }
        },
        {
            path: '/user/product/:pid?',
            component: () => import('../views/user/ProductDetail.vue'),
            meta: { side: 'user' }
        },
        {
            path: '/user/category/:catid?',
            component: () => import('../views/user/CategoryDetail.vue'),
            meta: { side: 'user' }
        },
        {
            path: '/admin',
            redirect: '/admin/category'
        },
        {
            path: '/admin/category',
            component: () => import('../views/admin/CategoryManage.vue'),
            meta: { side: 'admin' }
        },
        {
            path: '/admin/product',
            component: () => import('../views/admin/ProductManage.vue'),
            meta: { side: 'admin' }
        },
        {
            path: '/login',
            component: () => import('../views/LoginPage.vue'),
            meta: {side: 'login'}
        }
    ]
})

router.beforeEach((to, from, next) => {
    // const userName = store.state.userName
    // console.log("userName: ", userName)
    const userName = localStorage.getItem("userName")
    console.log("userName: ", userName)
    // 检查角色
    if (to.meta.side === 'admin' && userName !== 'admin') {
        alert('Access Denied !');
        next(false); // 阻止导航
    } else if (to.meta.side === 'user' && userName !== 'user') {
        alert('Access Denied !');
        next(false); // 阻止导航
    } else {
        next(); // 正常导航
    }

    // 检查是否从用户端导航到管理端
    // if (from.meta.side === 'user' && to.meta.side === 'admin') {
    //     // 在新标签页中打开链接
    //     window.open(to.fullPath, '_blank');
    //     next(false); // 阻止导航
    // } else if (from.meta.side === 'admin' && to.meta.side === 'user') {
    //     // 在新标签页中打开链接
    //     window.open(to.fullPath, '_blank');
    //     next(false); // 阻止导航
    // } else if (from.meta.side === 'user' && to.meta.side === 'login') {
    //     // 在新标签页中打开链接
    //     window.open(to.fullPath, '_blank');
    //     next(false); // 阻止导航
    // } else {
    //     // 正常导航
    //     next();
    // }
});

export default router
