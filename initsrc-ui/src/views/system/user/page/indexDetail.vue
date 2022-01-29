<template>
  <el-drawer class="dshfn-commonDrawer" title="用户详情" :visible.sync="drawer" :direction="direction" :wrapperClosable="true"
    :before-close="handleClose" append-to-body :size="$store.state.ts.ISDEVICE==0?'460px':'100%'">
    <div>
      <div class="detail-header">
        用户基本信息
      </div>
      <div class="detail-content">
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">所属部门：</label>
          <label class="dshfn-label-item__content">{{form.deptName}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">昵称：</label>
          <label class="dshfn-label-item__content">{{form.nickName}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">性别：</label>
          <label class="dshfn-label-item__content">{{form.sex}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">生日日期：</label>
          <label class="dshfn-label-item__content">{{form.birthday}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">邮箱：</label>
          <label class="dshfn-label-item__content">{{form.email}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">手机号：</label>
          <label class="dshfn-label-item__content">{{form.mobile}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">状态：</label>
          <label class="dshfn-label-item__content">{{form.status}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">角色权限：</label>
          <label class="dshfn-label-item__content">
			  <template v-for="item in form.roles">
			    {{item.roleName}}
			  </template>
		  </label>
        </div>
       <!-- <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">登录日期：</label>
          <label class="dshfn-label-item__content">{{form.loginDate}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">登录IP：</label>
          <label class="dshfn-label-item__content">{{form.loginIp}}</label>
        </div> -->
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">备注：</label>
          <label class="dshfn-label-item__content">{{form.remark}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">头像：</label>
          <el-image :preview-src-list="[form.headImg]" :src="form.headImg" alt>
            <div slot="error" class="image-slot ">
              暂无图片
            </div>
          </el-image>
        </div>
      </div>
    </div>
  </el-drawer>
</template>

<script src="../js/indexDetail.js">
  
</script>

<style>
</style>
