/**
* 作者：大神很烦恼
* 邮箱：502863488@qq.com
* 昵称：INITSRC
*/
<template>
  <div>
    <el-table id="iTable" v-loading.iTable="loading" :data="options.data" :stripe="options.stripe" ref="table"
      @selection-change="handleSelectionChange" :height="options.isFixed && THEAM.ISDEVICE==0?height:null"
      :row-key="options.rowKey" :default-expand-all="options.isExpandAll"
      :tree-props="{children: options.children, hasChildren: options.hasChildren}"
      :header-cell-style="THEAM.THEAM_TABLE">
      <template slot="empty">
        <div style="width: 100%;padding-top: 50px;">
          <img class="data-pic"
            src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iNjQiIGhlaWdodD0iNDEiIHZpZXdCb3g9IjAgMCA2NCA0MSIgIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CiAgPGcgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoMCAxKSIgZmlsbD0ibm9uZSIgZmlsbC1ydWxlPSJldmVub2RkIj4KICAgIDxlbGxpcHNlIGZpbGw9IiNGNUY1RjUiIGN4PSIzMiIgY3k9IjMzIiByeD0iMzIiIHJ5PSI3Ii8+CiAgICA8ZyBmaWxsLXJ1bGU9Im5vbnplcm8iIHN0cm9rZT0iI0Q5RDlEOSI+CiAgICAgIDxwYXRoIGQ9Ik01NSAxMi43Nkw0NC44NTQgMS4yNThDNDQuMzY3LjQ3NCA0My42NTYgMCA0Mi45MDcgMEgyMS4wOTNjLS43NDkgMC0xLjQ2LjQ3NC0xLjk0NyAxLjI1N0w5IDEyLjc2MVYyMmg0NnYtOS4yNHoiLz4KICAgICAgPHBhdGggZD0iTTQxLjYxMyAxNS45MzFjMC0xLjYwNS45OTQtMi45MyAyLjIyNy0yLjkzMUg1NXYxOC4xMzdDNTUgMzMuMjYgNTMuNjggMzUgNTIuMDUgMzVoLTQwLjFDMTAuMzIgMzUgOSAzMy4yNTkgOSAzMS4xMzdWMTNoMTEuMTZjMS4yMzMgMCAyLjIyNyAxLjMyMyAyLjIyNyAyLjkyOHYuMDIyYzAgMS42MDUgMS4wMDUgMi45MDEgMi4yMzcgMi45MDFoMTQuNzUyYzEuMjMyIDAgMi4yMzctMS4zMDggMi4yMzctMi45MTN2LS4wMDd6IiBmaWxsPSIjRkFGQUZBIi8+CiAgICA8L2c+CiAgPC9nPgo8L3N2Zz4K"
            alt="" />
        </div>
        <div style="line-height: 0px;padding: 10px 10px 50px 10px;">
          <span>暂无数据</span>
        </div>
      </template>
      <!--region 选择框-->
      <el-table-column v-if="options.mutiSelect" type="selection" style="width: 55px;"
        v-bind:selectable="options.checkBoxStatus"></el-table-column>
      <!--endregion-->
      <!--region 数据列-->
      <el-row :gutter="24" v-if="THEAM.ISDEVICE==1">
        <el-table-column key="内容" label="内容">
          <template slot-scope="scope">
            <template v-for="(column, index) in options.columns">
              <el-col :span="24">
                <div class="dshfn-label-item">
                  <label class="dshfn-label-item__label" style="width: unset !important;">{{column.label}}</label>
                  <label class="dshfn-label-item__content">
                    <template v-if="!column.render">
                      <template v-if="column.formatter">
                        <span v-html="column.formatter(scope.row, column,scope.row[column.prop])"></span>
                      </template>
                      <template v-else>
                        <span>{{scope.row[column.prop]}}</span>
                      </template>
                    </template>
                    <template v-else>
                      <expand-dom :column="column" :row="scope.row" :render="column.render" :index="index"></expand-dom>
                    </template>
                  </label>
                </div>
              </el-col>
            </template>
            <el-col :span="24">
              <div class="operate-group dshfn-label-item">
                <div class="dshfn-label-item__content">
                  <template v-for="(btn, key) in options.operates.list">
                    <div class="op-item"
                      v-if="(btn.show != true && btn.show !=false)?btn.show(scope.$index,scope.row):btn.show"
                      :key="btn.id">
                      <el-button :type="btn.type" size="mini" :icon="btn.icon"
                        :disabled="(btn.disabled != true && btn.disabled !=false)?btn.disabled(scope.$index,scope.row):btn.disabled"
                        :plain="btn.plain" @click.native.prevent="btn.method(key,scope.row)">{{ btn.label }}</el-button>
                    </div>
                  </template>
                </div>
              </div>
            </el-col>
          </template>
        </el-table-column>
      </el-row>
      <template v-for="(column, index) in options.columns" v-else>
        <el-table-column :prop="column.prop" :key="column.label" :label="column.label" :align="column.align"
          :fixed="column.fixed" :width="column.width">
          <template slot-scope="scope">
            <template v-if="!column.render">
              <template v-if="column.formatter">
                <span v-html="column.formatter(scope.row, column,scope.row[column.prop])"></span>
              </template>
              <template v-else>
                <span>{{scope.row[column.prop]}}</span>
              </template>
            </template>
            <template v-else>
              <expand-dom :column="column" :row="scope.row" :render="column.render" :index="index"></expand-dom>
            </template>
          </template>
        </el-table-column>
      </template>
      <!--region 按钮操作组-->
      <el-table-column ref="fixedColumn" label="操作" :width="options.operates.width" :fixed="options.operates.fixed"
        v-if="options.operates.list.length > 0 && THEAM.ISDEVICE==0">
        <template slot-scope="scope">
          <div class="operate-group">
            <template v-for="(btn, key) in options.operates.list">
              <div v-if="key < options.operates.hideCount">
                <div class="op-item"
                  v-if="(btn.show != true && btn.show !=false)?btn.show(scope.$index,scope.row):btn.show" :key="btn.id">
                  <el-button :type="btn.type" size="mini" :icon="btn.icon"
                    :disabled="(btn.disabled != true && btn.disabled !=false)?btn.disabled(scope.$index,scope.row):btn.disabled"
                    :plain="btn.plain" @click.native.prevent="btn.method(key,scope.row)">{{ btn.label }}</el-button>
                </div>
              </div>
            </template>
            <div v-if="options.operates.list.length > options.operates.hideCount">
              <el-dropdown style="padding-left: 5px;">
                <el-button type="text" size="mini">
                  更多
                  <i class="el-icon-arrow-down el-icon--right"></i>
                </el-button>
                <el-dropdown-menu slot="dropdown">
                  <template v-for="(btn, key) in options.operates.list">
                    <div v-if="key >=options.operates.hideCount">
                      <div v-if="(btn.show != true && btn.show !=false)?btn.show(scope.$index,scope.row):btn.show">
                        <el-dropdown-item @click.native.prevent="btn.method(key,scope.row)" :disabled="btn.disabled">
                          {{ btn.label }}
                        </el-dropdown-item>
                      </div>
                    </div>
                  </template>
                </el-dropdown-menu>
              </el-dropdown>
            </div>
          </div>
        </template>
      </el-table-column>
      <!--endregion-->
      <!--endregion-->
    </el-table>
    <!--region 分页-->
    <div style=" padding: 10px;float: right;">
      <el-pagination v-if="options.page" @size-change="handleSizeChange" @current-change="handleIndexChange"
        :page-size="tableCurrentPagination.pageSize" :page-sizes="tableCurrentPagination.pageArray"
        :current-page="tableCurrentPagination.pageIndex" layout="total,sizes, prev, pager, next,jumper"
        :total="tableCurrentPagination.total"></el-pagination>
    </div>
    <!--endregion-->
  </div>
</template>

<script>
  export default {
    props: {
      removeScHeight: {
        type: Number,
        default: 200
      }, // 如果固定高，全屏高差值，默认200px:
      params: {
        type: Object,
      }, // 查询参数
      optionsObj: {
        type: Object,
        default: {}
      } // table 表格的控制参数
    },
    components: {
      expandDom: {
        functional: true,
        props: {
          row: Object,
          render: Function,
          index: Number,
          column: {
            type: Object,
            default: null
          }
        },
        render: (h, ctx) => {
          const params = {
            row: ctx.props.row,
            index: ctx.props.index
          };
          if (ctx.props.column) params.column = ctx.props.column;
          return ctx.props.render(h, params);
        }
      }
    },
    data() {
      return {
        tableCurrentPagination: {}, // 分页参数
        loading: false, //表格loading
        THEAM: {}, //样式参数
        height: null,
        result: {
          id: 1
        },
      }
    },
    methods: {
      //初始化
      init() {
        let that = this;
        that.$nextTick(() => {
          that.loading = true;
          if (that.options.api != null && that.options.page) {
            //请求参数设置
            let map = new Map();
            if (that.options.page) {
              map.set(that.options.pageParam.page, that.tableCurrentPagination.pageIndex);
              map.set(that.options.pageParam.limit, that.tableCurrentPagination.pageSize);
            }
            const pageParams = Array.from(map).reduce((obj, [key, value]) =>
              Object.assign(obj, {
                [key]: value
              }), {})
            const params = Object.assign(pageParams, that.params)
            that.options.api(params)
              .then(res => {
                that.loading = false;
                const data = that.options.pareData(res.data);
                if (data.code == 0) {
                  that.options.data = data.data;
                  that.tableCurrentPagination.total = data.count
                } else {
                  that.$notify.error({
                    title: '错误提示',
                    message: res.msg
                  });
                }
              })
          } else {
            that.loading = false;
          }
        });
      },
      //重置
      reload() {
        let that = this;
        if (that.options.page) {
          that.tableCurrentPagination = {
            pageIndex: 1,
            pageSize: that.tableCurrentPagination.pageSize,
            pageArray: that.options.pageArray,
            total: 0
          }
        }
        that.init();
      },
      // 切换每页显示的数量
      handleSizeChange(size) {
        let that = this;
        if (that.options.page) {
          that.tableCurrentPagination = {
            pageIndex: 1,
            pageSize: size,
            pageArray: that.options.pageArray,
            total: 0
          }
        }
        that.init();
      },
      // 切换页码
      handleIndexChange(currnet) {
        let that = this;
        if (that.options.page) {
          this.tableCurrentPagination.pageIndex = currnet;
        }
        that.init();
      },
      // 多行选中
      handleSelectionChange(val) {
        this.multipleSelection = val;
        this.$emit("handleSelectionChange", val);
      },
      //获取table 高
      getHeight(data) {
        if (window.innerWidth < 769) {
          this.THEAM.ISDEVICE = 1
        }
        if (null != data && data.type == null) {
          this.height = window.innerHeight - data;
        } else {
          this.height = window.innerHeight - this.removeScHeight;
        }
        if (this.$refs.mutipleTable != undefined) {
          this.$refs.mutipleTable.doLayout();
        }
      },
      //json转map
      _objToStrMap(obj) {
        let strMap = new Map();
        for (let k of Object.keys(obj)) {
          strMap.set(k, obj[k]);
        }
        return strMap;
      }
    },
    mounted() {
      let that = this;
      that.THEAM = this.$store.state.ts
      if (that.options.page) {
        that.tableCurrentPagination = {
          pageIndex: 1,
          pageSize: that.options.pageArray[0],
          pageArray: that.options.pageArray,
          total: 0
        }
      }
    },
    computed: {
      options() {
        return Object.assign({
          api: null, //请求方法
          page: false, //是否开启分页
          data: [], //表格数据
          pageArray: [10, 20, 50, 100], //分页属性重新设置[]
          stripe: false, // 是否为斑马纹 table
          highlightCurrentRow: false, // 是否支持当前行高亮显示
          rowKey: "id", //key值
          mutiSelect: false, // 是否支持列表项选中功能
          isFixed: false, //是否固定高
          isExpandAll: false, //是否默认树形table展开
          children: "children", //树形table的子节点名称
          hasChildren: "hasChildren",
          columns: [], //表头
          operates: {
            width: 150,
            fixed: 'right',
            list: [],
          }, //操作按钮对象
          pageParam: {
            page: "page",
            limit: "limit"
          },
          checkBoxStatus: function(row, index) { //选择按钮禁用或启用
            return true;
          },
          pareData: function(res) {
            return {
              "code": res.code, //解析接口状态
              "msg": res.msg, //解析提示文本
              "count": res.data.total, //解析数据长度
              "data": res.data.pageList //解析数据列表
            }
          },
        }, this.optionsObj);
      }
    },
    created() {
      window.addEventListener("resize", this.getHeight);
      this.getHeight(null);
    },
    destroyed() {
      window.removeEventListener("resize", this.getHeight);
    },
    watch:{
      removeScHeight(val){
        this.getHeight(val)
      }
    }
  }
</script>

<style scoped>
  .op-item {
    float: left;
    padding-left: 10px;
  }

  /* 解决element-ui的table表格控件表头与内容列不对齐问题 */
  /deep/ .el-table th.gutter {
    display: table-cell !important;
    width: 15px !important;
  }
</style>
