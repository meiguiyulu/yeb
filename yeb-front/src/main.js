import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import {postRequest} from "@/utils/api";
import {putRequest} from "@/utils/api";
import {getRequest} from "@/utils/api";
import {deleteRequest} from "@/utils/api";
// import {downloadRequest} from "@/utils/download";

Vue.config.productionTip = false

Vue.use(ElementUI,{size:'small'});
// 插件形式使用请求
Vue.prototype.postRequest = postRequest
Vue.prototype.putRequest = putRequest
Vue.prototype.getRequest = getRequest
Vue.prototype.deleteRequest = deleteRequest
// Vue.prototype.downloadRequest = downloadRequest // 以插件的形式使用下载相关请求

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
