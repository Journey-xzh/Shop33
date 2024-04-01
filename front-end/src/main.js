import './assets/reset.css';

import { createApp } from 'vue';
import App from './App.vue';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import router from './router';
import store from "@/vuex/store";

const app = createApp(App);

// 动态导入Element Plus的图标
import('@element-plus/icons-vue').then(ElementPlusIconsVue => {
    // 注册所有图标
    for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
        app.component(key, component);
    }
});
app.use(router);
app.use(store);
app.use(ElementPlus);
app.mount('#app');
