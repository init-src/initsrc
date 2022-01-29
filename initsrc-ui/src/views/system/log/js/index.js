import indexDetail from '@/views/system/log/page/indexDetail.vue'
  export default {
    components: {
      indexDetail,
    },
    data() {
      return {
        params: {
          title: null,
          bizType: null,
          platformType: null,
        },
        //头部按钮权限
        headBtn: [],
        //查询列表
        bizTypeList: [],
        platformTypeList: [],
        //批量删除列
        multipleId: [],
        removeScHeight: 330,
        table: null, //表格
        //表格基本参数
        options: {
          api: this.$api.systemRequest.logPage, //请求API
          page: true,
          mutiSelect: true,
          isFixed: true,
          operates: {
            width: 200, //操作栏宽
            fixed: 'right',
            hideCount: 3, //按钮更多
            list: []
          },
          columns: [{
            prop: 'requestName',
            label: '操作用户',
            formatter: (row, column, cellValue) => {
              return this.common.isNull(cellValue)
            },
          },{
            prop: 'title',
            label: '模块标题',
            formatter: (row, column, cellValue) => {
              return this.common.isNull(cellValue)
            },
          },
          {
            prop: 'bizType',
            label: '日志类型',
            formatter: (row, column, cellValue) => {
              return this.common.transformDict(cellValue, JSON.parse(this.$store.state.ps.DICT_LIST.sysLogType))
            },
          },
          {
            prop: 'method',
            label: '访问方法名',
            formatter: (row, column, cellValue) => {
              return this.common.isNull(cellValue)
            },
          },
          {
            prop: 'requestType',
            label: '请求方式',
            formatter: (row, column, cellValue) => {
              return this.common.isNull(cellValue)
            },
          },
          {
            prop: 'platformType',
            label: '设备类型',
            formatter: (row, column, cellValue) => {
              return this.common.transformDict(cellValue, JSON.parse(this.$store.state.ps.DICT_LIST.platformType))
            },
          },
          {
            prop: 'requestIp',
            label: '请求IP',
            formatter: (row, column, cellValue) => {
              return this.common.isNull(cellValue)
            },
          },
          {
            prop: 'requestUrl',
            label: '请求地址',
            formatter: (row, column, cellValue) => {
              return this.common.isNull(cellValue)
            },
          },
          {
            prop: 'status',
            label: '请求状态',
            formatter: (row, column, cellValue) => {
              return this.common.transformDict(cellValue, JSON.parse(this.$store.state.ps.DICT_LIST.sysLogStatus))
            },
          },
          {
            prop: 'createTime',
            label: '请求时间',
            formatter: (row, column, cellValue) => {
              return this.common.transformTime(cellValue);
            },
          },
          ],
        },
      }
    },
    methods: {
      //初始化
      init() {
        let that = this;
        that.options.loading = true;
        that.bizTypeList = JSON.parse(that.$store.state.ps.DICT_LIST.sysLogType);
        that.platformTypeList = JSON.parse(that.$store.state.ps.DICT_LIST.platformType);
        that.table = that.$refs.table;
        that.table.init();
      },
      //查询
      search() {
        let that = this;
         that.table.reload();
      },
      //重置查询
      reload() {
        let that = this;
        that.params = {
          title: null,
          bizType: null,
          platformType: null,
        }
         that.table.reload();
      },
      //新增
      handleAdd(path) {
        this.$router.push({
          path: path
        })
      },
      // 详情
      handleDetail(index, row, path) {
        this.$refs.indexDetail.init(row.logId);
      },
      // 编辑
      handleEdit(index, row, path) {
        this.$router.push({
          path: path,
          query: {
            id: row.logId
          }
        })
      },
      //批量删除
      handleDels() {
        let that = this;
        that.$confirm('此操作将删除该数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          center: 'true'
        }).then(() => {
          if (that.multipleId.length > 0) {
            that.options.loading = true;
            that.$api.systemRequest.logDeletes({
                ids: that.multipleId
              })
              .then(res => {
                res = res.data
                that.options.loading = false;
                if (res.code == 0) {
                  that.search();
                  this.$notify.success({
                    title: '成功提示',
                    message: res.msg
                  });
                } else {
                  this.$notify.error({
                    title: '错误提示',
                    message: res.msg
                  });
                }
              })
          } else {
            that.$message.warning("请选择要删除的数据");
          }
        }).catch(() => {
          that.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      // 删除
      handleDel(index, row) {
        let that = this;
        that.$confirm('此操作将删除该数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          center: 'true'
        }).then(() => {
          that.options.loading = true;
          that.$api.systemRequest.logDelete({
              id: row.logId
            })
            .then(res => {
              res = res.data
              that.options.loading = false;
              if (res.code == 0) {
                that.search();
                this.$notify.success({
                  title: '成功提示',
                  message: res.msg
                });
              } else {
                this.$notify.error({
                  title: '错误提示',
                  message: res.msg
                });
              }
            })
        }).catch(() => {
          that.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      // 选中行
      handleSelectionChange(val) {
        let that = this;
        let str = "";
        let strname = "";
        val.forEach(function(item) {
          str += item.logId + ",";
        })
        if (str.length > 0) {
          that.multipleId = str.substr(0, str.length - 1);
        } else {
          that.multipleId = []
        }
      },
    },
    mounted() {
      let that = this
      that.init();
      //权限初始化
      var oper = this.powerCommon.powerSet(that.$store.state.ps.PERM_BTN, that);
      that.options.operates.list = oper.tableOper
      that.headBtn = oper.headerOper
    }
  }
