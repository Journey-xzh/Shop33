import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/user',
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
      component: () => import('../views/Login.vue'),
      meta: {side: 'login'}
    }
  ]
})

router.beforeEach((to, from, next) => {
  // 检查是否从用户端导航到管理端
  if (from.meta.side === 'user' && to.meta.side === 'admin') {
    // 在新标签页中打开链接
    window.open(to.fullPath, '_blank');
    next(false); // 阻止导航
  } else if (from.meta.side === 'admin' && to.meta.side === 'user') {
    // 在新标签页中打开链接
    window.open(to.fullPath, '_blank');
    next(false); // 阻止导航
  } else if (from.meta.side === 'user' && to.meta.side === 'login') {
    // 在新标签页中打开链接
    window.open(to.fullPath, '_blank');
    next(false); // 阻止导航
  } else {
    // 正常导航
    next();
  }
});

export default router
