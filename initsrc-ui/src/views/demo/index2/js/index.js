export default {
  components: {},
  data() {
    return {
      params: {
        name: null
      },
      isExpand: false, //展开更多搜索条件
      //树形加载
      treeloading: true,
      //树形数据
      treeData: [{
        id: 0,
        label: '启源科技',
        children: [{
          id: 1,
          label: '总裁办',
          children: [{
            id: 2,
            label: '财务部',
            children: null,
          }],
        }, {
          id: 3,
          label: '技术部',
          children: null,
        }, {
          id: 13,
          label: '技术部',
          children: null,
        }, {
          id: 23,
          label: '技术部',
          children: null,
        }, {
          id: 33,
          label: '技术部',
          children: null,
        }, {
          id: 43,
          label: '技术部',
          children: null,
        }, {
          id: 63,
          label: '技术部',
          children: null,
        }, {
          id: 53,
          label: '技术部',
          children: null,
        }, {
          id: 153,
          label: '技术部',
          children: null,
        }, {
          id: 253,
          label: '技术部',
          children: null,
        }, {
          id: 353,
          label: '技术部',
          children: null,
        }, {
          id: 453,
          label: '技术部',
          children: null,
        }, {
          id: 1453,
          label: '技术部',
          children: null,
        }, {
          id: 2453,
          label: '技术部',
          children: null,
        }, {
          id: 3453,
          label: '技术部',
          children: null,
        }, {
          id: 4453,
          label: '技术部1',
          children: null,
        }, {
          id: 14453,
          label: '技术部1',
          children: null,
        }, {
          id: 24453,
          label: '技术部1',
          children: null,
        }, {
          id: 34453,
          label: '技术部1',
          children: null,
        }, {
          id: 44453,
          label: '技术部1',
          children: null,
        }]
      }],
      table: null, //table
      removeScHeight: 334,
      options: {
        data: [{
          id: 1,
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          id: 2,
          date: '2016-05-04',
          name: '王小虎',
          state: '789',
          address: '上海市普陀区金沙江路 1517 弄'
        }, {
          id: 3,
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1519 弄'
        }, {
          id: 4,
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }], //请求API
        page: true,
        isFixed: true,
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
                this.handleDetail(index, row, "/demo/index2/detail")
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
                this.handleEdit(index, row, "/demo/index2/edit")
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
        columns: [{
          prop: 'id',
          label: 'ID',
          width: 250,
          formatter: (row, column, cellValue) => {
            return this.common.isNull(cellValue)
          }
        },{
          prop: 'name',
          label: '员工',
          width: 250,
          formatter: (row, column, cellValue) => {
            return this.common.isNull(cellValue)
          }
        },
        {
          prop: 'address',
          label: '地址',
          formatter: (row, column, cellValue) => {
            return this.common.isLongToShort(cellValue)
          }
        },
        {
          prop: 'state',
          label: '里程',
          width: 250,
         formatter: (row, column, cellValue) => {
           return this.common.transformKmByMetre(cellValue,"Km")
         }
        },
        {
          prop: 'date',
          label: '时间',
          width: 400,
          formatter: (row, column, cellValue) => {
            return this.common.transformShortTime(cellValue)
          }
        },
        ]
      },
      // 树形默认参数
      defaultProps: {
        children: 'children',
        label: 'label'
      },
    }
  },
  methods: {
    //初始化
    init() {
      let that = this;
      this.table = this.$refs.table;
      that.table.init()
      that.treeInit();
    },
    treeInit() {
      let that = this;
      that.treeloading = true;
      setTimeout(function() {
        that.treeloading = false;
        that.$refs.tree.setCurrentKey(0)
      }, "2000");
    },
    //树形点击
    handleNodeClick(data) {
      let that = this;
      if (data.id == 0) {
        that.init();
      } else {
        that.table.reload()
      }
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
        name:null,
      }
     that.table.reload()
    },
    //新增
    handleAdd(path) {
      this.$router.push({
        path: "/demo/index2/edit"
      })
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
      this.$router.push({
        path: path,
        query: {
          id: row.id
        }
      })
    },
    // 删除
    handleDel(index, row) {
      let that = this;
      that.$confirm('此操作将删除该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
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
    //展开更多条件查询
    expand() {
      let that = this;
      if (that.isExpand) {
        that.isExpand = false;
        that.removeScHeight = 340;
      } else {
        that.isExpand = true;
        that.removeScHeight = 380;
      }
      that.table.getHeight(that.removeScHeight);
    },
  },
  mounted() {
    let that = this;
    this.init();
  }
}
