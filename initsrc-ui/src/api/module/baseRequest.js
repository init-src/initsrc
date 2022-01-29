/**
 * 基础API
 */

import base from '../base'; // 导入接口域名列表
import axios from '../http'; // 导入http中创建的axios实例
import store from '../../store/index'
import qs from 'qs';
const config = {
  headers: {
    "Content-Type": "application/json"
  }
};
const baseRequest = {
  // 登录
  login(params) {
    return axios.post(`${base.bd}webApi/base/login`, qs.stringify(params));
  },
  // 获取权限
  refreshAuth(params) {
    return axios.post(`${base.bd}webApi/base/refreshAuth`, qs.stringify(params));
  },
  // 退出登录
  outLogin(params) {
    return axios.post(`${base.bd}webApi/base/outLogin`, qs.stringify(params));
  },
  /**
   * 首页接口
   */
  // 首页接口查询
  homeInfo(params) {
    return axios.get(base.bd+`webApi/home/homeInfo`,{params});
  },
  // 首页通知公告查询
  detailNotice(params) {
    return axios.get(base.bd+`webApi/home/detailNotice`,{params});
  },
  // 首页通知公告查询
  noticeList(params) {
    return axios.get(base.bd+`webApi/home/noticeList`,{params});
  },
  // 首页操作列表查询
  logList(params) {
    return axios.get(base.bd+`webApi/home/logList`,{params});
  },
}

export default baseRequest;
