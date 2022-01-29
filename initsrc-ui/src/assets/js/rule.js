/**
 * 作者：李楚汉
 * 作用：正则类
 * 邮箱：502863488@qq.com
 * 昵称：大神很烦恼（dshfn)
 */

export default {
  //正整数正则
  checkPositiveInteger: function(rule, value, callback) {
    if (!value) {
      if(rule.required){
        return callback(new Error('数字不能为空'));
      }else{
        callback();
      }
    } else {
      const reg = /(^[1-9]\d*$)/
      if (reg.test(value)) {
        callback();
      } else {
        return callback(new Error('请输入正整数'));
      }
    }
  },
  //金额正则
  checkMoney: function(rule, value, callback) {
    if (!value) {
     if(rule.required){
       return callback(new Error('金额不能为空'));
     }else{
       callback();
     }
    } else {
      const reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/
      if (reg.test(value)) {
        callback();
      } else {
        return callback(new Error('请输入正确的金额'));
      }
    }
  },
  //手机号正则
  checkMobile: function(rule, value, callback) {
    if (!value) {
      if(rule.required){
        return callback(new Error('手机号不能为空'));
      }else{
        callback();
      }
    } else {
      const reg = /^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/
      if (reg.test(value)) {
        callback();
      } else {
        return callback(new Error('请输入正确的手机号'));
      }
    }
  },
  //密码正则
  checkerPassword: function(rule, value, callback) {
    if (!value) {
      if(rule.required){
        return callback(new Error('密码不能为空'));
      }else{
        callback();
      }
    } else {
      const reg =  /^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*?. ]).*$/;
      if (reg.test(value)) {
         callback();
      } else {
        return callback(new Error("最少6位，包括需大写字母、小写字母、数字、特殊字符"));
      }
    }
  },
  //邮箱正则
  checkEmail: function(rule, value, callback) {
    if (!value) {
      if(rule.required){
        return callback(new Error('邮箱不能为空'));
      }else{
        callback();
      }
    } else {
      const reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
      if (reg.test(value)) {
        callback();
      } else {
        return callback(new Error('请输入正确的邮箱号'));
      }
    }
  },
  //车牌正则
  checkCar: function(rule, value, callback) {
    if (!value) {
      if(rule.required){
        return callback(new Error('车牌号不能为空'));
      }else{
        callback();
      }
    } else {
      const reg =
        /^(([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领][A-Z](([0-9]{5}[DF])|([DF]([A-HJ-NP-Z0-9])[0-9]{4})))|([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领][A-Z][A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳使领]))$/
      if (reg.test(value)) {
        callback();
      } else {
        return callback(new Error('请输入正确的车牌号'));
      }
    }
  }
}
