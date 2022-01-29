/**
 * 工具管理模块API
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
const commonRequest = {
  // 获取验证码
  getVerifyCode(params) {
    return axios.post(`${base.bd}common/captcha/getVerifyCode`, qs.stringify(params));
  },
  // 阿里云OSS获取临时TOKEN
  getAliOssToken(params) {
    return axios.post(`${base.bd}common/file/getAliOssToken`, qs.stringify(params));
  },
}

export default commonRequest;
