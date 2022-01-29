<template>
  <div class="dshfn-content" v-loading="loading" style="height: calc(100% - 60px);">
    <el-form :model="option" :rules="rules" class="dshfn-card" ref="ruleForm" label-position="top">
      <el-row :gutter="20" style="padding: 5px;">
        <el-col :md="4" :lg="4" :xl="4">
          <el-form-item prop="host">
            <el-input v-model="option.host" size="small" placeholder="请输入服务器IP"></el-input>
          </el-form-item>
        </el-col>
        <el-col :md="4" :lg="4" :xl="4">
          <el-form-item prop="port">
            <el-input v-model="option.port" size="small" placeholder="请输入服务器端口"></el-input>
          </el-form-item>
        </el-col>
        <el-col :md="4" :lg="4" :xl="4">
          <el-form-item prop="username">
            <el-input v-model="option.username" size="small" placeholder="请输入用户名"></el-input>
          </el-form-item>
        </el-col>
        <el-col :md="4" :lg="4" :xl="4">
          <el-form-item prop="password">
            <el-input v-model="option.password" size="small" placeholder="请输入密码"></el-input>
          </el-form-item>
        </el-col>
        <el-col :md="4" :lg="4" :xl="4" style="margin-top: 5px;">
          <el-button type="danger" icon="el-icon-refresh" size="mini" @click="connect()">连接
          </el-button>
        </el-col>
      </el-row>
    </el-form>
    <div class="term_content_tab">
      <div id="terminal" ref="terminal"></div>
    </div>
  </div>
</template>

<script src="../js/index.js">

</script>

<style>
  .term_content_tab {
    background-color: #000;
    position: absolute;
    top: 60px;
    left: 0;
    right: 0;
    bottom: 0;
    padding: 10px 5px 10px 10px;
    overflow: hidden;
  }

  .xterm {
    height: 100%;
  }

  .xterm-viewport {
    height: 100%;
  }
</style>
