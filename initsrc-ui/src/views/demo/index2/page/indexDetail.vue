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
    <el-row :gutter="24" class="dshfn-content-main">
      <el-col :md="24" :lg="18" :xl="18" style="padding: 0px;">
        <el-col :span="24" class="col-padding">
          <div class="dshfn-card dshfn-card-bottom">
            <div class="dshfn-card-body">
              <h4 class="card-title">Order Detail</h4>
              <el-row :gutter="24" class="dshfn-detail-info">
                <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="6">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">订单号：</label>
                    <label class="dshfn-label-item__content">202007311118325193</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="6">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">订单类型:</label>
                    <label class="dshfn-label-item__content">普通订单</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="6">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">发送方式:</label>
                    <label class="dshfn-label-item__content">快递发送</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="6">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">退货理由:</label>
                    <label class="dshfn-label-item__content">不想买了</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="6">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">下单时间:</label>
                    <label class="dshfn-label-item__content">2020-07-31 11:18:32</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="6">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">订单状态:</label>
                    <label class="dshfn-label-item__content">发货中</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="6">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">发货时间:</label>
                    <label class="dshfn-label-item__content">不想买了</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="6">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">退货理由:</label>
                    <label class="dshfn-label-item__content">2020-07-31 11:18:32</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="6">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">快递公司:</label>
                    <label class="dshfn-label-item__content">友邻之物网络科技有限公司</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="6">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">购买用户：</label>
                    <label class="dshfn-label-item__content">大神很烦恼</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="6">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">支付方式：</label>
                    <label class="dshfn-label-item__content">支付宝支付</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="6">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">到货时间：</label>
                    <label class="dshfn-label-item__content">2020-07-31 11:18:32</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="6">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">快递单号：</label>
                    <label class="dshfn-label-item__content">166688888</label>
                  </div>
                </el-col>
              </el-row>
            </div>
          </div>
        </el-col>
        <el-col :span="24" class="col-padding">
          <div class="dshfn-card dshfn-card-bottom">
            <div class="dshfn-card-body">
              <h4 class="card-title">User Detail</h4>
              <el-row :gutter="24" class="dshfn-detail-info">
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">收货人：</label>
                    <label class="dshfn-label-item__content">大神很烦恼</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">联系电话：</label>
                    <label class="dshfn-label-item__content">15260295836</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">收货地址：</label>
                    <label class="dshfn-label-item__content">福建省厦门市思明区明发园</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="6">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">票据图片：</label>
                    <el-image :preview-src-list="[form.img]" :src="form.img" style="width: 100px; height: 100px" alt>
                      <div slot="error" class="image-slot ">
                        暂无图片
                      </div>
                    </el-image>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="6">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">收货图片：</label>
                    <el-image :preview-src-list="[form.img]" :src="form.img" style="width: 100px; height: 100px" alt>
                      <div slot="error" class="image-slot ">
                        暂无图片
                      </div>
                    </el-image>
                  </div>
                </el-col>
              </el-row>
            </div>
          </div>
        </el-col>
        <el-col :span="24" class="col-padding">
          <div class="dshfn-card dshfn-card-bottom">
            <div class="dshfn-card-body">
              <h4 class="card-title">Order Fee</h4>
              <el-row :gutter="24" class="dshfn-detail-info" style="padding: 0px;">
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                  <div class="dshfn-label-item">
                    <label class="label-money">10.25<span>元</span></label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="6">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label left width-label">起步价</label>
                    <label class="dshfn-label-item__content  right">11.50元</label>
                    <label class="dshfn-label-item_min">
                      <label class="left min">含时长8分钟，含里程3.2公里</label>
                    </label>
                  </div>
                </el-col>
              </el-row>
              <el-row :gutter="24" class="dshfn-detail-info" style="padding: 0px;">
                <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="6">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label left width-label">里程费（3.5公里）</label>
                    <label class="dshfn-label-item__content  right">0.63元</label>
                    <label class="dshfn-label-item_min">
                      <label class="left min">起步里程（3.2公里）</label>
                    </label>
                    <label class="dshfn-label-item_min">
                      <label class="left min">17:29-17:42（0.3公里）</label>
                      <label class="right min">0.63元</label>
                    </label>
                  </div>
                </el-col>
              </el-row>
              <el-row :gutter="24" class="dshfn-detail-info" style="padding: 0px;">
                <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="6">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label left width-label">时长费（13分钟）</label>
                    <label class="dshfn-label-item__content  right">2.90元</label>
                    <label class="dshfn-label-item_min">
                      <label class="left min">起步时长（13分钟）</label>
                    </label>
                    <label class="dshfn-label-item_min">
                      <label class="left min">17:29-17:42（5分钟）</label>
                      <label class="right min">2.90元</label>
                    </label>
                  </div>
                </el-col>
              </el-row>
            </div>
          </div>
        </el-col>
      </el-col>
      <el-col :md="24" :lg="6" :xl="6" class="col-padding">
        <div class="dshfn-card">
          <div class="dshfn-card-body">
            <h4 class="card-title">Order Flow</h4>
            <div>
              <el-row :gutter="24" class="dshfn-detail-info">
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                  <el-timeline style="padding-top: 10px;">
                    <el-timeline-item timestamp="2018/4/12" placement="top">
                      <el-card>
                        <h4>更新 Github 模板</h4>
                        <p>王小虎 提交于 2018/4/12 20:46</p>
                      </el-card>
                    </el-timeline-item>
                    <el-timeline-item timestamp="2018/4/3" placement="top">
                      <el-card>
                        <h4>更新 Github 模板</h4>
                        <p>王小虎 提交于 2018/4/3 20:46</p>
                      </el-card>
                    </el-timeline-item>
                    <el-timeline-item timestamp="2018/4/2" placement="top">
                      <el-card>
                        <h4>更新 Github 模板</h4>
                        <p>王小虎 提交于 2018/4/2 20:46</p>
                      </el-card>
                    </el-timeline-item>
                  </el-timeline>
                </el-col>
              </el-row>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script src="../js/indexDetail.js">
</script>

<style scoped>
  .width-label {
    width: 120px !important;
  }
</style>
