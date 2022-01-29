<template>
  <el-drawer class="dshfn-commonDrawer" title="企业详情"  :visible.sync="drawer" :direction="direction" :wrapperClosable="true"
    :before-close="handleClose" append-to-body :size="$store.state.ts.ISDEVICE==0?'560px':'100%'">
    <div class="addfooter">
      <div class="detail-header">
        基本信息
      </div>
      <div class="detail-content">
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">公司名称</label>
          <label class="dshfn-label-item__content">友邻之物网络科技有限公司</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">所属地区</label>
          <label class="dshfn-label-item__content">福建省-厦门市-湖里区</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">具体地址</label>
          <label class="dshfn-label-item__content">万物社</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">公司状态</label>
          <label class="dshfn-label-item__content">启用</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">联系人</label>
          <label class="dshfn-label-item__content">李大神</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">联系电话</label>
          <label class="dshfn-label-item__content">1526029536</label>
        </div>
      </div>
      <div class="detail-header">
        公司概况
      </div>
      <div class="detail-content">
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">公司总数</label>
          <label class="dshfn-label-item__content">1</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">员工总数</label>
          <label class="dshfn-label-item__content">2</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">订单总数</label>
          <label class="dshfn-label-item__content">3</label>
        </div>
      </div>
      <div class="detail-content">
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">公司总数</label>
          <label class="dshfn-label-item__content">1</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">员工总数</label>
          <label class="dshfn-label-item__content">2</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">订单总数</label>
          <label class="dshfn-label-item__content">3</label>
        </div>
      </div>
      <div class="detail-header">
        组织架构
      </div>
      <div class="detail-content">
        <div class="dshfn-label-item">
          <el-image :preview-src-list="[form.img]" :src="form.img" alt />
        </div>
      </div>
    </div>
    <div class="footer">
      <el-button type="primary" plain>设置预算</el-button>
      <el-button type="danger" plain>删除</el-button>
    </div>
  </el-drawer>
</template>

<script src="../js/indexDetail.js">
</script>

<style>
</style>
