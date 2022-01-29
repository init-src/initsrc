/**
 * 开发管理模块API
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
const devRequest = {
  /**
   * 代码生成接口
   */
  // 代码生成查询列表
  tablePage(params) {
    return axios.get(base.bd+`devApi/generator/page`,{params});
  },
  // 代码生成查询详情
  tableDetail(params) {
    return axios.get(base.bd+`devApi/generator/detail`,{params});
  },
  // 更新数据
  tableUpdate(params) {
    return axios.post(base.bd+`devApi/generator/update`, params,config);
  },
  // 代码生成代码预览
  tablePreview(params) {
    return axios.get(base.bd+`devApi/generator/preview`,{params});
  },
  // 代码生成同步数据库
  tableSysnc(params) {
    return axios.post(base.bd+`devApi/generator/syncDbByTableName`,qs.stringify(params));
  },
  // 代码生成同步数据库
  tableDownload(params) {
    return axios.get(base.bd+`devApi/generator/download`,{
      responseType: "blob",
      params: params
    });
  },
  /**
   * 系统监控接口
   */
  // 系统监控接口
  serverDetail(params) {
    return axios.get(base.bd+`devApi/monitor/serverDetail`,{params});
  },
  // 缓存监控接口
  cacheDetail(params) {
    return axios.get(base.bd+`devApi/monitor/cacheDetail`,{params});
  },
}

export default devRequest;
