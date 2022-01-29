/**
 * 系统管理模块API
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
const systemRequest = {
  /**
   * 字典表接口
   */
  // 查询字典表列表
  dictPage(params) {
    return axios.get(base.bd + `webApi/system/dict/page`, {
      params
    });
  },
  //查询字典表详情
  dictDetail(params) {
    return axios.get(base.bd + `webApi/system/dict/detail`, {
      params
    });
  },
  // 保存字典表数据
  dictSave(params) {
    return axios.post(base.bd + `webApi/system/dict/save`, params, config);
  },
  // 更新字典表数据
  dictUpdate(params) {
    return axios.post(base.bd + `webApi/system/dict/update`, params, config);
  },
  // 删除字典表数据
  dictDelete(params) {
    return axios.post(base.bd + `webApi/system/dict/delete`, qs.stringify(params));
  },
  /**
   * 菜单接口
   */
  // 查询菜单列表
  permPage(params) {
    return axios.get(base.bd + `webApi/system/perm/page`, {
      params
    });
  },
  //查询菜单左侧列表
  permLeftData(params) {
    return axios.get(base.bd + `webApi/system/perm/leftPage`, {
      params
    });
  },
  //查询菜单下拉框列表
  permSelectData(params) {
    return axios.get(base.bd + `webApi/system/perm/selectPage`, {
      params
    });
  },
  //查询菜单详情
  permDetail(params) {
    return axios.get(base.bd + `webApi/system/perm/detail`, {
      params
    });
  },
  // 保存菜单数据
  permSave(params) {
    return axios.post(base.bd + `webApi/system/perm/save`, params, config);
  },
  // 更新菜单数据
  permUpdate(params) {
    return axios.post(base.bd + `webApi/system/perm/update`, params, config);
  },
  // 删除菜单数据
  permDelete(params) {
    return axios.post(base.bd + `webApi/system/perm/delete`, qs.stringify(params));
  },
  // 批量删除菜单数据
  permDeletes(params) {
    return axios.post(base.bd + `webApi/system/perm/deletes`, qs.stringify(params));
  },
  /**
   * 用户接口
   */
  // 查询用户列表
  userPage(params) {
    return axios.get(base.bd + `webApi/system/user/page`, {
      params
    });
  },
  //查询用户左侧列表
  userLeftData(params) {
    return axios.get(base.bd + `webApi/system/user/leftPage`, {
      params
    });
  },
  //查询用户下拉框列表
  userSelectData(params) {
    return axios.get(base.bd + `webApi/system/user/selectPage`, {
      params
    });
  },
  //查询用户详情
  userDetail(params) {
    return axios.get(base.bd + `webApi/system/user/detail`, {
      params
    });
  },
  // 保存用户数据
  userSave(params) {
    return axios.post(base.bd + `webApi/system/user/save`, params, config);
  },
  // 更新用户数据
  userUpdate(params) {
    return axios.post(base.bd + `webApi/system/user/update`, params, config);
  },
  // 删除用户数据
  userDelete(params) {
    return axios.post(base.bd + `webApi/system/user/delete`, qs.stringify(params));
  },
  /**
   * 角色接口
   */
  // 查询角色列表
  rolePage(params) {
    return axios.get(base.bd + `webApi/system/role/page`, {
      params
    });
  },
  //查询角色下拉框列表
  roleSelectData(params) {
    return axios.get(base.bd + `webApi/system/role/selectPage`, {
      params
    });
  },
  //查询角色详情
  roleDetail(params) {
    return axios.get(base.bd + `webApi/system/role/detail`, {
      params
    });
  },
  // 保存角色数据
  roleSave(params) {
    return axios.post(base.bd + `webApi/system/role/save`, params, config);
  },
  // 更新角色数据
  roleUpdate(params) {
    return axios.post(base.bd + `webApi/system/role/update`, params, config);
  },
  // 删除角色数据
  roleDelete(params) {
    return axios.post(base.bd + `webApi/system/role/delete`, qs.stringify(params));
  },
  // 批量删除角色数据
  roleDeletes(params) {
    return axios.post(base.bd + `webApi/system/role/deletes`, qs.stringify(params));
  },
  /**
   * 部门接口
   */
  // 查询部门列表
  deptPage(params) {
    return axios.get(base.bd + `webApi/system/dept/page`, {
      params
    });
  },
  //查询部门左侧列表
  deptLeftData(params) {
    return axios.get(base.bd + `webApi/system/dept/leftPage`, {
      params
    });
  },
  //查询部门下拉框列表
  deptSelectData(params) {
    return axios.get(base.bd + `webApi/system/dept/selectPage`, {
      params
    });
  },
  //查询部门详情
  deptDetail(params) {
    return axios.get(base.bd + `webApi/system/dept/detail`, {
      params
    });
  },
  // 保存部门数据
  deptSave(params) {
    return axios.post(base.bd + `webApi/system/dept/save`, params, config);
  },
  // 更新部门数据
  deptUpdate(params) {
    return axios.post(base.bd + `webApi/system/dept/update`, params, config);
  },
  // 删除部门数据
  deptDelete(params) {
    return axios.post(base.bd + `webApi/system/dept/delete`, qs.stringify(params));
  },
  /**
   * 操作日志接口
   */
  // 查询操作日志列表
  logPage(params) {
    return axios.get(base.bd + `webApi/system/log/page`, {
      params
    });
  },
  //查询操作日志详情
  logDetail(params) {
    return axios.get(base.bd + `webApi/system/log/detail`, {
      params
    });
  },
  /**
   * 通知公告接口
   */
  // 查询通知公告列表
  noticePage(params) {
    return axios.get(base.bd + `webApi/system/notice/page`, {
      params
    });
  },
  // 通知公告下载模板
  noticeDownloadExcel(params) {
  return axios.get(base.bd+`webApi/system/notice/downloadExcel`,{
      responseType: "blob",
      params: params
  });
  },
  // 通知公告导出
  noticeExport(params) {
    return axios.get(base.bd + `webApi/system/notice/export`, {
      responseType: "blob",
      params: params
    });
  },
  // 通知公告导入
  noticeImport(params) {
    let formData = new FormData();
    formData.append("file", params);
    return axios.post(
      base.bd + `webApi/system/notice/import`,
      formData, {
        responseType: "blob",
        headers: {
          "Content-Type": "multipart/form-data"
        }
      }
    );
  },
  //查询通知公告详情
  noticeDetail(params) {
    return axios.get(base.bd + `webApi/system/notice/detail`, {
      params
    });
  },
  // 保存通知公告数据
  noticeSave(params) {
    return axios.post(base.bd + `webApi/system/notice/save`, params, config);
  },
  // 更新通知公告数据
  noticeUpdate(params) {
    return axios.post(base.bd + `webApi/system/notice/update`, params, config);
  },
  // 删除通知公告数据
  noticeDelete(params) {
    return axios.post(base.bd + `webApi/system/notice/delete`, qs.stringify(params));
  },
  // 批量删除通知公告数据
  noticeDeletes(params) {
    return axios.post(base.bd + `webApi/system/notice/deletes`, qs.stringify(params));
  },

}

export default systemRequest;
