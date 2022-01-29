import indexEdit from '@/views/demo/index3/page/indexEdit.vue'
import indexImport from '@/views/demo/index/page/indexImport.vue'
import indexExport from '@/views/demo/index/page/indexExport.vue'

export default {
  components: {
    indexEdit,
    indexImport,
    indexExport
  },
  data() {
    return {
      params: {
        name: null
      },
      tapName: "1",
      isExpand: false, //展开更多搜索条件
      removeScHeight: 330,
      isExpand: false, //展开更多搜索条件
      table: null, //table
      options: {
        data: [{
          date: '2016-05-02 00:00:00',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-04 00:00:00',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1517 弄'
        }, {
          date: '2016-05-01 00:00:00',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1519 弄'
        }, {
          date: '2016-05-03 00:00:00',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }, {
          date: '2016-05-03 00:00:00',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }, {
          date: '2016-05-03 00:00:00',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }, {
          date: '2016-05-03 00:00:00',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }, {
          date: '2016-05-03 00:00:00',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }, {
          date: '2016-05-03 00:00:00',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }, {
          date: '2016-05-03 00:00:00',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }],
        page: true,
        operates: {
          width: 200, //操作栏宽
          fixed: 'right',
          hideCount: 3, //按钮更多
          list: [{
              label: '详情', //按钮名称
              type: 'text', //按钮类型
              show: (index, row) => { //按钮是否展示
                return true
              },
              icon: '', //按钮图标
              plain: true, //按钮样式
              disabled: false, //按钮禁用
              method: (index, row) => { //按钮方法
                this.handleDetail(index, row, '/demo/index2/detail')
              }
            },
            {
              label: '编辑',
              type: 'text',
              show: (index, row) => {
                return true
              },
              icon: '',
              plain: true,
              disabled: false,
              method: (index, row) => {
                this.handleEdit(index, row)
              }
            },
            {
              label: '删除',
              type: 'text',
              icon: '',
              show: (index, row) => {
                return true
              },
              plain: false,
              disabled: false,
              method: (index, row) => {
                this.handleDel(index, row)
              }
            }
          ]
        },
        columns: [{ //表头展示
            prop: 'name',
            label: '员工',
            width: 250,
          },
          {
            prop: 'address',
            label: '地址',
          },
          {
            prop: 'state',
            label: '状态',
            width: 250,
            render: (h, params) => {
              return h('el-tag', {
                props: {
                  type: params.row.state === 0 ? 'success' : params.row.state === 1 ? 'info' : 'danger'
                } // 组件的props
              }, params.row.state === 0 ? '启用' : params.row.state === 1 ? '禁用' : '审核中')
            }
          },
          {
            prop: 'date',
            label: '时间',
            width: 400,
          },
        ],
      },
    }
  },
  methods: {
    //初始化
    init() {
      let that = this;
      this.table = this.$refs.table;
      this.table.init()
    },
    //查询
    search() {
      let that = this;
      that.table.reload()
    },
    //重置查询
    reload() {
      let that = this;
      that.params = {
        name: null
      }
      that.table.reload()
    },
    //新增
    handleAdd(path) {
      this.$refs.indexEdit.init();
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
      this.$router.push({
        path: path,
        query: {
          id: row.id
        }
      })
    },
    // 编辑
    handleEdit(index, row, path) {
      this.$refs.indexEdit.init(row.id);
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
          that.table.reload();
          that.$message({
            type: 'success',
            message: '删除成功!'
          });
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
        that.table.reload();
        that.$message({
          type: 'success',
          message: '删除成功!'
        });
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
        str += item.id + ",";
      })
      if (str.length > 0) {
        that.multipleId = str.substr(0, str.length - 1);
      } else {
        that.multipleId = []
      }
    },
  },
  mounted() {
    this.init();
  }
}
