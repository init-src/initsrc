/**
 * 作者：李楚汉
 * 作用：工具类
 * 邮箱：502863488@qq.com
 * 昵称：大神很烦恼（dshfn)
 */
import {
  Message,
  MessageBox
} from "element-ui";

import JSEncrypt from 'jsencrypt';

import OSS from 'ali-oss'

//引入API
import api from '@/api' // 导入api接口

import router from '../../router'

import store from '../../store'

export default {
  /**
   * 转换区
   */
  // 数据为空时显示默认值"-"
  isNull: function(value) {
    var data = value;
    if (data == undefined || data.length <= 0) {
      return "-";
    }
    return data;
  },
  // 数据超出多少之后小点展示
  isLongToShort: function(value, width) {
    var data = "-";
    if (value == undefined || value.length <= 0) {
      return "-";
    }
    return `<span title="${value}" class="text-long-short">` + value + `</span>`;
  },
  // 图片展示
  isImageShow: function(value, width) {
    var data = "-";
    if (value == undefined || value.length <= 0) {
      return "-";
    }
    return `<img src="${value}" style="width:60px;height:60px"></img>`;
  },
  //字典转换
  transformDict: function(value, dictType) {
    var data = "-";
    if (value == undefined || value.length <= 0) {
      return "-";
    }
    dictType.forEach(function(item) {
      if (value == item.key) {
        data = item.label
      }
    })
    return data;
  },
  // 时间戳转换为年月日格式
  transformShortTime: function(value) {
    var data = value;
    if (data == undefined || data.length <= 0) {
      return "-";
    }
    var date = new Date(data);
    var YY = date.getFullYear() + "-";
    var MM =
      (date.getMonth() + 1 < 10 ?
        "0" + (date.getMonth() + 1) :
        date.getMonth() + 1) + "-";
    var DD = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var hh =
      (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":";
    var mm =
      (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) +
      ":";
    var ss =
      date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
    return YY + MM + DD;
  },
  // 时间戳转换为年月日时分秒格式
  transformTime: function(value) {
    var data = value;
    if (data == undefined || data.length <= 0) {
      return "-";
    }
    var date = new Date(data);
    var YY = date.getFullYear() + "-";
    var MM =
      (date.getMonth() + 1 < 10 ?
        "0" + (date.getMonth() + 1) :
        date.getMonth() + 1) + "-";
    var DD = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var hh =
      (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":";
    var mm =
      (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) +
      ":";
    var ss =
      date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
    return YY + MM + DD + " " + hh + mm + ss;
  },
  //浮点型数据转换保留N位，没有四舍五入(默认2)
  transformDoubleByTwo(value, n) {
    var data = value;
    if (!(/(^[0-9]\d*$)/.test(n))) {
      n = 2
    }
    if (data == undefined || data.length <= 0) {
      return "0";
    }
    if (n == 0) {
      return Number(data).toFixed(n + 1).slice(0, -2);
    } else {
      return Number(data).toFixed(n + 1).slice(0, -1);
    }
  },
  //浮点型数据转换保留N位,向上取整(默认2)
  transformDoubleByTwoAndUp(value, n) {
    var data = value;
    if (!(/(^[0-9]\d*$)/.test(n))) {
      n = 2
    }
    var size = Math.pow(10, n)
    if (data == undefined || data.length <= 0) {
      return "0";
    }
    return Math.ceil(Number(data) * size) / size;;
  },
  //浮点型数据转换保留N位,四舍五入(默认2)
  transformDoubleByTwoAndRound(value, n) {
    var data = value;
    if (!(/(^[0-9]\d*$)/.test(n))) {
      n = 2
    }
    if (data == undefined || data.length <= 0) {
      return "0";
    }
    return Number(data).toFixed(n);
  },
  //秒 => 转时分秒
  transformHMSBySecond: (second = 0) => {
    let h = Math.floor((second / 3600));
    let m = Math.floor((second / 60) % 60);
    let s = Math.floor(second % 60);
    return h + "小时 " + m + "分钟 " + s + "秒";
  },
  //秒 => 转时分，不足向上取整
  transformHMBySecond: (second = 0) => {
    let h = Math.floor((second / 3600));
    let m = Math.floor((second / 60) % 60);
    let s = Math.floor(second % 60);
    if (s > 0) {
      m = m + 1;
    }
    return h + "小时 " + m + "分钟 ";
  },
  //秒 => 转时，不足向上取整
  transformHBySecond: (second = 0) => {
    let h = Math.floor((second / 3600));
    let m = Math.floor((second / 60) % 60);
    if (m > 0) {
      h = h + 1;
    }
    return h + "小时";
  },
  //米 => 转公里，四舍五入
  transformKmByMetre: (metre = 0, unit) => {
    let data = (metre / 1000).toFixed(1);
    if (unit == undefined || unit == null) {
      return data;
    }
    return data + unit;
  },
  /**
   * 判读区
   */
  // 判断文件大小限制
  isFileSize: function(file, size) {
    if (!(/(^[0-9]\d*$)/.test(size))) {
      size = 10
    }
    const isLtM = file.size / 1024 / 1024 < size;
    if (!isLtM) {
      Message({
        message: "上传文件大小不能超过 " + size + "MB!",
        type: "error",
        duration: 5 * 1000
      });
    }
    return isLtM;
  },
  //文件下载方式
  fileDownloads: function(res,type) {
    if (!res) {
      return;
    }
    let url = window.URL.createObjectURL(new Blob([res.data]));
    let link = document.createElement("a");
    link.style.display = "none";
    link.href = url;
    var patt = new RegExp('filename=([^;]+\\.[^\\.;]+);*')
    var contentDisposition = decodeURI(res.headers['content-disposition'])
    var result = patt.exec(contentDisposition)
    var fileName = result[1]
    fileName = fileName.replace(/\"/g, '')
    link.setAttribute("download",fileName);
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
    window.URL.revokeObjectURL(url);
  },
  // 判断图片限制
  imageUploadType: function(file) {
    let type = file.name.split(".");
    const isJPG = type[1] === "jpg" || type[1] === "png" || type[1] === "gif" || type[1] === "jpeg";
    if (!isJPG) {
      Message({
        message: "上传图片只能是 JPG/PNG/GIF/JPEG 格式!",
        type: "error",
        duration: 5 * 1000
      });
    }
    return isJPG;
  },
  // 判断Excel限制
  excelUploadType: function(file) {
    let type = file.name.split(".");
    const isExcel = type[1] === "xls" || type[1] === "xlsx";
    if (!isExcel) {
      Message({
        message: "上传文件只能是 xls/xlsx 格式!",
        type: "error",
        duration: 5 * 1000
      });
    }
    return isExcel;
  },
  async uploadFile(file, path) {
    let that = this;
    let res = await api.commonRequest.getAliOssToken({})
    res = res.data
    if (res.code != 0) {
      Message({
        message: "获取阿里云OSS失败",
        type: "error",
        duration: 5 * 1000
      });
    }
    let client = OSS({
      accessKeyId: res.data.AccessKeyId, //你的ak
      accessKeySecret: res.data.accessKeySecret, //你的secret
      stsToken: res.data.securityToken,
      bucket: res.data.bucket //你的oss名字
    });
    const point = file.name.lastIndexOf('.')
    let suffix = file.name.substr(point)
    let fileName = file.name.substr(0, point)
    let store = path + "/" + fileName + suffix
    let resdata = await client.multipartUpload(store, file)
    if (resdata.res.statusCode != 200) {
      Message({
        message: "获取阿里云OSS失败",
        type: "error",
        duration: 5 * 1000
      });
    }
    var url = res.data.url + store;
    return url;
  },
  //RSA工具
  // 加密对象处理
  getRSApassword: function(data) {
    let pubKey =
      `MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDYhFXoDrY6JuzTpjUq9eQJXu6tOb9IUrTRHYqVUiAv3u4UWQmCDOj4g/RWrSuwM9//4CkdS/3SPARegjPigubFLpyo9LMzTC0C5MCxQZ38iaV4MHlX0TxO5hj+pfvRIIaxm95HAyAC+g5oq91byg1+aaohqZ7TYXQThCr1DKPf7QIDAQAB`; // ES6 模板字符串 引用 rsa 公钥
    let encryptStr = new JSEncrypt();
    encryptStr.setPublicKey(pubKey); // 设置 加密公钥
    return encryptStr.encrypt(data)
  },
  //回退
  goBack(){
    router.go(-1)
    store.commit("_SET_TABS_LIST", router.currentRoute.fullPath);
  },
};
