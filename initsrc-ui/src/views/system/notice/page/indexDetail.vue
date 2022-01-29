<template>
  <el-drawer class="dshfn-commonDrawer" title="通知公告详情" :visible.sync="drawer" :direction="direction" :wrapperClosable="true"
    :before-close="handleClose" append-to-body :size="'100%'">
    <article class="details">
      <div class="title" >
        <h1 style="text-align: center;">{{form.title}}</h1>
        <span style="font-size: 15px;" v-html="common.transformTime(form.createTime)"></span>
      </div>
      <div class="w-e-text content" v-html="form.content">
      </div>
    </article>
  </el-drawer>
</template>

<script src="../js/indexDetail.js">

</script>

<style scoped>
  .details {
    width: 80%;
    margin: 0 auto;
  }
  
  .details .title {
    border-bottom: 2px solid red;
    padding-top: 10px;
  }
  
  .details .content{
    padding-top: 20px;
  }
</style>
