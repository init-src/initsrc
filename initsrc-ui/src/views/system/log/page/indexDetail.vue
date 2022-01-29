<template>
  <el-drawer class="dshfn-commonDrawer" title="操作日志详情" :visible.sync="drawer" :direction="direction" :wrapperClosable="true"
    :before-close="handleClose" append-to-body :size="$store.state.ts.ISDEVICE==0?'560px':'100%'">
    <div>
      <div class="detail-header">
        操作日志基本信息
      </div>
      <div class="detail-content">
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">操作用户：</label>
          <label class="dshfn-label-item__content">{{form.requestName}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">模块标题：</label>
          <label class="dshfn-label-item__content">{{form.title}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">日志类型：</label>
          <label class="dshfn-label-item__content">{{form.bizType}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">访问方法名：</label>
          <label class="dshfn-label-item__content">{{form.method}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">请求方式：</label>
          <label class="dshfn-label-item__content">{{form.requestType}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">设备类型：</label>
          <label class="dshfn-label-item__content">{{form.platformType}}</label>
        </div>
       <div class="dshfn-label-item">
         <label class="dshfn-label-item__label">请求时间：</label>
         <label class="dshfn-label-item__content">{{common.transformTime(form.createTime)}}</label>
       </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">请求路径：</label>
          <label class="dshfn-label-item__content">{{form.requestUrl}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">请求IP：</label>
          <label class="dshfn-label-item__content">{{form.requestIp}}</label>
        </div>
       <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">请求地址：</label>
          <label class="dshfn-label-item__content">{{form.requestAdress}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">请求参数：</label>
          <label class="dshfn-label-item__content">{{form.requestParam}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">请求响应数据：</label>
          <label class="dshfn-label-item__content">{{form.requestResult}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">请求状态：</label>
          <label class="dshfn-label-item__content">{{form.statusName}}</label>
        </div>
        <div class="dshfn-label-item" v-if="form.status == 0">
          <label class="dshfn-label-item__label">错误提示：</label>
          <label class="dshfn-label-item__content">{{form.errorMsg}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">浏览器类型：</label>
          <label class="dshfn-label-item__content">{{form.browser}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">操作系统：</label>
          <label class="dshfn-label-item__content">{{form.os}}</label>
        </div>
      </div>
    </div>
  </el-drawer>
</template>

<script src="../js/indexDetail.js">

</script>

<style scoped>
  .dshfn-label-item__label{
    width: 100px !important;
  }
</style>
