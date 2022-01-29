export default {
  data() {
    return {
      params: {
        tableName: null,
        tableComment: null
      },
      //表格数据
      list: [],
      //头部按钮权限
      headBtn: [],
      removeScHeight: 330,
      table: null, //表格
      //表格基本参数
      options: {
        api: this.$api.devRequest.tablePage, //请求API
        page: true,
        isFixed: true,
        operates: {
          width: 200, //操作栏宽
          fixed: 'right',
          hideCount: 3, //按钮更多
          list: []
        },
        columns: [{
          prop: 'tableName',
          label: '表名称',
          formatter: (row, column, cellValue) => {
            return this.common.isNull(cellValue)
          }
        },
        {
          prop: 'tableComment',
          label: '表描述',
          formatter: (row, column, cellValue) => {
            return this.common.isNull(cellValue)
          }
        },
        {
          prop: 'createTime',
          label: '创建时间',
          formatter: (row, column, cellValue) => {
            return this.common.transformTime(cellValue)
          }
        },
        {
          prop: 'updateTime',
          label: '更新时间',
          formatter: (row, column, cellValue) => {
            return this.common.transformTime(cellValue)
          }
        },
        ],
      },
    }
  },
  methods: {
    //初始化
    init() {
      let that = this;
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
        tableName: null,
        tableComment: null
      }
      that.table.reload();
    },
    //新增
    handleAdd(path) {
      let that = this;
      that.$prompt('请输入表名称', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: null,
        inputErrorMessage: '表名称格式不正确'
      }).then(({
        value
      }) => {
        that.options.loading = true;
        that.$api.devRequest.tableSysnc({
            tableName: value
          })
          .then(res => {
            res = res.data
            if (res.code == 0) {
              that.options.loading = false;
              that.search();
              that.$notify.success({
                title: '成功提示',
                message: res.msg
              });
            } else {
              that.$notify.error({
                title: '错误提示',
                message: res.msg
              });
            }
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });
    },
    // 详情
    handleDetail(index, row, path) {
      this.$router.push({
        path: path,
        query: {
          id: row.tableId
        }
      })
    },
    //下载
    handleDownload(index, row) {
      let that = this;
      that.$confirm('此操作将下载该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: 'true'
      }).then(() => {
        that.options.loading = true;
        that.$api.devRequest.tableDownload({
            id: row.tableId
          })
          .then(res => {
            that.options.loading = false;
            that.common.fileDownloads(res, 'zip')
          })
      }).catch(() => {
        that.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    //同步
    handleSync(index, row) {
      let that = this;
      that.$confirm('此操作将同步该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: 'true'
      }).then(() => {
        that.options.loading = true;
        that.$api.devRequest.tableSysnc({
            tableName: row.tableName
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
    // 编辑
    handleEdit(index, row, path) {
      this.$router.push({
        path: path,
        query: {
          id: row.tableId
        }
      })
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
        that.devRequest.tableDelete({
            id: row.tableId
          })
          .then(res => {
            res = res.data
            if (res.code == 0) {
              that.options.loading = false;
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
  },
  mounted() {
    let that = this
    that.init();
    var oper = this.powerCommon.powerSet(that.$store.state.ps.PERM_BTN, that);
    that.options.operates.list = oper.tableOper
    that.headBtn = oper.headerOper.reverse()
  }
}
