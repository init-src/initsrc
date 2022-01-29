<template>
  <el-drawer class="dshfn-commonDrawer" title="角色详情" :visible.sync="drawer" :direction="direction" :wrapperClosable="true"
    :before-close="handleClose" append-to-body :size="$store.state.ts.ISDEVICE==0?'460px':'100%'">
    <div>
      <div class="detail-header">
        角色基本信息
      </div>
      <div class="detail-content">
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">角色名称：</label>
          <label class="dshfn-label-item__content">{{form.roleName}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">查询权限：</label>
          <label class="dshfn-label-item__content">{{form.isSearchName}}</label>
        </div>
        <div class="dshfn-label-item" v-if="form.isSearch == '1'">
          <label class="dshfn-label-item__label">自定义权限：</label>
          <label class="dshfn-label-item__content">{{form.powerDeptsName}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">状态：</label>
          <label class="dshfn-label-item__content">{{form.status}}</label>
        </div>
        <div class="dshfn-label-item">
          <label class="dshfn-label-item__label">描述：</label>
          <label class="dshfn-label-item__content">{{form.des}}</label>
        </div>
      </div>
      <div class="detail-header">
        管理用户
      </div>
      <div class="detail-content">
          <div class="dshfn-label-item" v-if="form.sysUserListVo">
            <div class="permission" style="padding: 0 10px 0 0;display: inline;" v-for="(item, index) in form.sysUserListVo">
             <el-tag :type="'warn'" >
             {{item.nickName}}
             </el-tag>
            </div>
            <div v-if="form.sysUserListVo.length <= 0">暂无用户</div>
          </div>
        </div>
      </div>
      <div class="detail-header">
        角色权限
      </div>
      <div class="detail-content">
        <div class="dshfn-label-item" v-if="form.isSystem == 0">
          <div class="permission" style="padding: 0 20px 0 0;" v-for="(item, index) in form.permVos">
            <h4>{{item.name}} <i class="el-icon-check"></i></h4>
            <ul>
              <template v-for="(it, index) in item.children">
                <li>{{it.name}}
                  <i class="el-icon-check"></i>
                  <p>
                    <template v-for="(its, index) in it.children">
                      <el-tag :type="'success'">{{its.name}}</el-tag>&nbsp;
                    </template>
                  </p>
                </li>
              </template>
            </ul>
          </div>
        </div>
        <div class="dshfn-label-item" v-else>
          系统默认权限
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
