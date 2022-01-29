<template>
  <el-drawer class="dshfn-commonDrawer" title="部门详情" :visible.sync="drawer" :direction="direction" :wrapperClosable="true"
    :before-close="handleClose" append-to-body :size="$store.state.ts.ISDEVICE==0?'560px':'100%'">
    <div>
      <div class="detail-header">
        部门基本信息
      </div>
      <div class="detail-content">
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">序号：</label>
          <label class="dshfn-label-item__content">{{form.deptId}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">上级部门：</label>
          <label class="dshfn-label-item__content">{{form.fdeptName}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">部门编号：</label>
          <label class="dshfn-label-item__content">{{form.code}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">部门名称：</label>
          <label class="dshfn-label-item__content">{{form.name}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">负责人：</label>
          <label class="dshfn-label-item__content">{{form.linkMan}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">联系电话：</label>
          <label class="dshfn-label-item__content">{{form.linkMobile}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">排序：</label>
          <label class="dshfn-label-item__content">{{form.sort}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">状态：</label>
          <label class="dshfn-label-item__content">{{form.status}}</label>
        </div>
      </div>
    </div>
  </el-drawer>
</template>

<script src="../js/indexDetail.js">
  
</script>

<style>
</style>
