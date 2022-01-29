import indexDetail from '@/views/system/notice/page/indexDetail.vue'
import indexImport from '@/views/system/notice/page/indexImport.vue'
import indexExport from '@/views/system/notice/page/indexExport.vue'
export default {
  components: {
    indexDetail,
    indexImport,
    indexExport,
  },
  data() {
    return {
      params: {
        title: null,
        beginCreateTime: null,
        endCreateTime: null,
      },
      //头部按钮权限
      headBtn: [],
      //查询列表
      //批量删除列
      multipleId: [],
      removeScHeight: 330,
      table: null, //表格
      //表格基本参数
      options: {
        api: this.$api.systemRequest.noticePage, //请求API
        page: true,
        mutiSelect: true,
        isFixed: true,
        operates: {
          width: 200, //操作栏宽
          fixed: 'right',
          hideCount: 3, //按钮更多
          list: []
        },
        columns: [
          {
            prop: 'title',
            label: '公告标题',
            formatter: (row, column, cellValue) => {
              return this.common.isNull(cellValue)
            },
          },
          {
            prop: 'status',
            label: '公告状态',
            formatter: (row, column, cellValue) => {
              return this.common.transformDict(cellValue, JSON.parse(this.$store.state.ps.DICT_LIST.statusType))
            },
          },
          {
            prop: 'createTime',
            label: '创建时间',
            formatter: (row, column, cellValue) => {
              return this.common.transformTime(cellValue)
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
      that.table = that.$refs.table;
      that.table.init();
    },
    //查询
    search() {
      let that = this;
      if (that.params.createTime != null) {
        that.params.beginCreateTime = that.params.createTime[0];
        that.params.endCreateTime = that.params.createTime[1];
      }
      that.table.reload();
    },
    //重置查询
    reload() {
      let that = this;
      that.params = {
        title: null,
        beginCreateTime: null,
        endCreateTime: null,
      }
      that.table.reload();
    },
    //新增
    handleAdd(path) {
      this.$router.push({
        path: path
      })
    },
    //导入
    handleImport(path) {
      this.$refs.indexImport.init();
    },
    //导出
    handleExport(path) {
      this.$refs.indexExport.init();
    },
    // 详情
    handleDetail(index, row, path) {
      this.$refs.indexDetail.init(row.noticeId);
    },
    // 编辑
    handleEdit(index, row, path) {
      this.$router.push({
        path: path,
        query: {
          id: row.noticeId
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
          that.$api.systemRequest.noticeDeletes({
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
        that.$api.systemRequest.noticeDelete({
            id: row.noticeId
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
        str += item.noticeId + ",";
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
