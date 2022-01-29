<template>
  <el-drawer class="dshfn-commonDrawer" title="菜单详情" :visible.sync="drawer" :direction="direction" :wrapperClosable="true"
    :before-close="handleClose" append-to-body :size="$store.state.ts.ISDEVICE==0?'560px':'100%'">
    <div>
      <div class="detail-header">
        菜单基本信息
      </div>
      <div class="detail-content">
        
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">所属菜单：</label>
          <label class="dshfn-label-item__content">{{form.permName}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">菜单名称：</label>
          <label class="dshfn-label-item__content">{{form.name}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">菜单路径：</label>
          <label class="dshfn-label-item__content">{{form.path}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">菜单类型：</label>
          <label class="dshfn-label-item__content">{{form.resource}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">路由组件：</label>
          <label class="dshfn-label-item__content">{{form.component}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">shiro权限：</label>
          <label class="dshfn-label-item__content">{{form.perm}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">菜单图标：</label>
          <label class="dshfn-label-item__content">{{form.icon}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">按钮颜色：</label>
          <label class="dshfn-label-item__content">{{form.color}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">是否缓存：</label>
          <label class="dshfn-label-item__content">{{form.isCache}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">排序：</label>
          <label class="dshfn-label-item__content">{{form.sort}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">链接类型：</label>
          <label class="dshfn-label-item__content">{{form.linkType}}</label>
        </div>
      </div>
    </div>
  </el-drawer>
</template>

<script src="../js/indexDetail.js">
  
</script>

<style>
</style>
