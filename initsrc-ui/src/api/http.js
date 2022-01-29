/**
 * axios封装
 * 请求拦截、响应拦截、错误统一处理
 */
import axios from 'axios';
import router from '@/router';
import store from '@/store/index.js';
import common from '@/assets/js/common.js';
import qs from 'qs';
import {
  Message,
  MessageBox,
  Notification
} from 'element-ui'

/**
 * 跳转登录页
 * 携带当前页面路由，以期在登录页面完成登录后返回当前页面
 */
const toLogin = () => {
  router.replace({
    path: '/login',
    query: {
      redirect: router.currentRoute.fullPath
    }
  });
}

/**
 * 请求失败后的错误统一处理
 * @param {Number} status 请求失败的状态码
 */
const errorHandle = (status, other) => {
  // 状态码判断
  switch (status) {
    // 401: 未登录状态，跳转登录页
    case 401:
      store.commit("_SET_LOGIN_OUT");
      setTimeout(() => {
        toLogin();
      }, 1000);
      break;
    case 403:
      router.replace({
        path: '/403'
      });
      break;
      // 404请求不存在
    case 404:
      router.replace({
        path: '/404'
      });
      break;
    case 500:
      Notification.error({
        title: '错误',
        message: '服务器端出错'
      })
      break;
    case 504:
      Notification.error({
        title: '错误',
        message: '网络超时'
      })
      break;
    default:
  }
}

/**
 * 请求失败后的错误统一处理
 * @param {Number} status 请求失败的状态码
 */
const codeHandle = (res) => {
  var status = res.data.code;
  var msg = res.data.msg;
  // 状态码判断
  switch (status) {
    //后台系统异常
    case 500:
      Notification.error({
        title: '系统异常',
        message: msg
      })
      break
      //客户端请求的语法错误，服务器无法理解
    case 400:
      Notification.error({
        title: '参数错误',
        message: msg
      })
      break;
      // 401: 未登录状态，跳转登录页
    case 401:
      store.commit("_SET_LOGIN_OUT");
      MessageBox.confirm('登录状态已过期，您可以继续留在该页面，或者重新登录', '系统提示', {
        confirmButtonText: '重新登录',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        toLogin();
      })
      break;
      // 403无权限，跳转403页面
    case 403:
      router.replace({
        path: '/403'
      });
      break;
    case 407:
      store.commit("_SET_LOGIN_OUT");
      MessageBox.confirm('您的账号在其他设备登录，您可以继续留在该页面，或者重新登录', '系统提示', {
        confirmButtonText: '重新登录',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        toLogin();
      })
      break;
    default:
     return true;
  }
}

// 创建axios实例
var instance = axios.create({
  timeout: 1000 * 12
});
// 设置post请求头
instance.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
/**
 * 请求拦截器
 * 每次请求前，如果存在token则在请求头中携带token
 */
instance.interceptors.request.use(
  config => {
    // 登录流程控制中，根据本地是否存在token判断用户的登录情况
    // 但是即使token存在，也有可能token是过期的，所以在每次的请求头中携带token
    // 后台根据携带的token判断用户的登录情况，并返回给我们对应的状态码
    // 而后我们可以在响应拦截器中，根据状态码进行一些统一的操作。
    let timestamp = Date.parse(new Date()); // 获取当前时间戳
    let ticket =  sessionStorage.getItem("_storeTicket");
    let secret = {
      ticket: ticket,
      version: '1.0',
    }
    secret = common.getRSApassword(JSON.stringify(secret))
    const token = sessionStorage.getItem("_storeToken");
    config.headers.sc = secret
    if(token == "INITSRC"){
      config.headers.sc = "1221";
    }
    // config.headers.plf = 4
    token && (config.headers.token = token);
    return config;
  },
  error => Promise.error(error))

// 响应拦截器
instance.interceptors.response.use(
  // 请求成功
  res => {
    //替换新的token
    if (res.headers.token != null) {
      sessionStorage.setItem("_storeToken", res.headers.token);
      store.state.USER_TOKEN = res.headers.token;
    }
    if(res.status != 200){
      return Promise.reject(res);
    }else{
      var flag = codeHandle(res);
      if(flag){
        return Promise.resolve(res)
      }
    }
  },
  // 请求失败
  error => {
    const {
      response
    } = error;
    if (response) {
      // 请求已发出，但是不在2xx的范围
      errorHandle(response.status, response.data.msg);
      return Promise.reject(response);
    } else {
      // 处理断网的情况
      // eg:请求超时或断网时，更新state的network状态
      // network状态在app.vue中控制着一个全局的断网提示组件的显示隐藏
      // 关于断网组件中的刷新重新获取数据，会在断网组件中说明
      console.log("网络异常")
      Notification.error({
        title: '错误',
        message: '网络异常'
      })
    }
  });

export default instance;
