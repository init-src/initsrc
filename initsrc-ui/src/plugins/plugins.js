import Vue from 'vue'

/**
 * js区
 */
//element组件
import ElementUI from 'element-ui'
Vue.use(ElementUI)

//ws公共类
import baseWs from "@/assets/js/ws/index.js"; //导入公共方法
Vue.use(baseWs); //ui

//引入API
import api from '@/api' // 导入api接口
Vue.prototype.$api = api;

//布局的组件
import InitSrcLayout from '@/plugins/layout.js'
Vue.use(InitSrcLayout)

//自己的组件
import InitSrcComp from '@/plugins/components.js'
Vue.use(InitSrcComp)

//全局工具类
import common from '@/assets/js/common.js'
Vue.prototype.common = common;

//全局正则类
import ruleCommon from '@/assets/js/rule.js'
Vue.prototype.ruleCommon = ruleCommon;

//全局权限过滤器
import powerCommon from '@/assets/js/power.js'
Vue.prototype.powerCommon = powerCommon;


/**
 * css 区
 */
//adminCSS
import "@/assets/style/myadmin.css"
//饿了么CSS
import "element-ui/lib/theme-chalk/index.css";
import '@/assets/style/template/dark/index.css';
import '@/assets/style/template/light/index.css';

import 'normalize.css'

//treeselect组件
import "@riophae/vue-treeselect/dist/vue-treeselect.css"
