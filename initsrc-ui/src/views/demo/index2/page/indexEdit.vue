<template>
  <div class="dshfn-content">
    <div class="dshfn-content-header dshfn-card">
      <el-row :gutter="24">
        <el-col :span="24" class="col-no-padding">
          <div style="padding: 4px;">
            <el-page-header @back="common.goBack" content="车辆详情">
            </el-page-header>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="dshfn-content-select">
      <div class="dshfn-scenetabs-item"><span class="scenetabs-item--wrapper is-active"><span>轿车</span></span></div>
      <div class="dshfn-scenetabs-item"><span class="scenetabs-item--wrapper"><span>商务车</span></span></div>
      <div class="dshfn-scenetabs-item"><span class="scenetabs-item--wrapper"><span>面包车</span></span></div>
      <div class="dshfn-scenetabs-item"><span class="scenetabs-item--wrapper"><span>柯斯达</span></span></div>
      <div class="dshfn-scenetabs-item"><span class="scenetabs-item--wrapper"><span>SUV</span></span></div>
      <div class="dshfn-scenetabs-item"><span class="scenetabs-item--wrapper"><span>货车</span></span></div>
    </div>
    <el-row :gutter="24" class="dshfn-content-main add-background add-footer" style="top: 25px;">
      <el-form :model="form" :rules="rules" ref="ruleForm" size="medium" class="dshfn-form-content add-top">
        <el-row :gutter="24">
          <el-col :md="24" :lg="16" :xl="8">
            <el-form-item label="商品名称:" prop="name">
              <el-input v-model="form.name" placeholder="请输入部门名称" maxlength="20" show-word-limit></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :md="24" :lg="16" :xl="8">
            <el-form-item label="商品编号:" prop="code">
              <el-input v-model="form.code" placeholder="请输入部门编号" maxlength="20" show-word-limit></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :md="24" :lg="16" :xl="8">
            <el-form-item label="活动时间:" prop="date1">
              <el-date-picker style="width: 100%;" v-model="form.value1" type="daterange" range-separator="-"
                start-placeholder="开始日期" end-placeholder="结束日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :md="24" :lg="16" :xl="8">
            <el-form-item label="活动性质:" prop="type">
              <el-checkbox-group v-model="form.type">
                <el-checkbox label="美食/餐厅线上活动" name="type" style="width: 100%;"></el-checkbox>
                <el-checkbox label="地推活动" name="type" style="width: 100%;"></el-checkbox>
                <el-checkbox label="线下主题活动" name="type" style="width: 100%;"></el-checkbox>
                <el-checkbox label="单纯品牌曝光" name="type" style="width: 100%;"></el-checkbox>
              </el-checkbox-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :md="24" :lg="16" :xl="8">
            <el-form-item label="即时配送:" prop="delivery">
              <el-switch v-model="form.delivery" style="width: 100%;"></el-switch>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :md="24" :lg="16" :xl="8">
            <el-form-item label="特殊资源:" prop="resource">
              <el-radio-group v-model="form.resource" style="width: 100%;">
                <el-radio label="线上品牌商赞助"></el-radio>
                <el-radio label="线下场地免费"></el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :md="24" :lg="16" :xl="8">
            <el-form-item label="活动形式:" prop="desc">
              <el-input type="textarea" v-model="form.desc"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :md="24" :lg="16" :xl="8">
            <el-form-item label="轮播图:">
              <el-upload action="https://jsonplaceholder.typicode.com/posts/" list-type="picture-card" :on-preview="handlePictureCardPreview"
                :on-remove="handleRemove">
                <i class="el-icon-plus"></i>
              </el-upload>
              <el-dialog :visible.sync="dialogVisible">
                <img width="100%" :src="dialogImageUrl" alt="">
              </el-dialog>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :md="24" :lg="16" :xl="8">
            <el-form-item label="运费模板:" prop="value">
              <el-select v-model="form.value" placeholder="请选择" style="width: 100%;">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :md="24" :lg="16" :xl="8">
            <el-form-item label="商品标签:" prop="value2">
              <el-select v-model="form.value2" placeholder="请选择" style="width: 100%;">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-row>
    <!-- <div class="dshfn-footer-btn" >
      <el-button type="primary" size="medium" @click="save('ruleForm')" :loading="loading">保存</el-button>
      <el-button type="danger" size="medium" @click="resetForm('ruleForm')">重置</el-button>
    </div> -->
    <is-footer-btn :option="footerList">
    </is-footer-btn>
  </div>
</template>

<script src="../js/indexEdit.js">
</script>

<style>
</style>
