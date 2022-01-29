import indexDetail from '@/views/system/perm/page/indexDetail.vue'
import indexEdit from '@/views/system/perm/page/indexEdit.vue'
export default {
  components: {
    indexDetail,
    indexEdit,
  },
  data() {
    return {
      params: {
        parentId: "0",
        name: null,
        sort: "asc",
      },
      //头部按钮权限
      headBtn: [],
      //树形加载
      treeloading: true,
      //树形数据
      treeData: [{
        id: 0,
        label: "我是顶层",
        children: [],
      }],
      //查询列表
      resourceList: [],
      isCacheList: [],
      linkTypeList: [],
      removeScHeight: 330,
      table: null, //表格
      //表格基本参数
      options: {
        api: this.$api.systemRequest.permPage, //请求API
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
            prop: 'name',
            label: '菜单名称',
            formatter: (row, column, cellValue) => {
              return this.common.isNull(cellValue)
            },
          },
          {
            prop: 'permName',
            label: '所属菜单',
            formatter: (row, column, cellValue) => {
              if (row.parentId == 0) {
                return "顶层"
              } else {
                return this.common.isNull(cellValue)
              }
            },
          },
          {
            prop: 'path',
            label: '菜单路径',
            formatter: (row, column, cellValue) => {
              return this.common.isNull(cellValue)
            },
          },
          {
            prop: 'resource',
            label: '菜单类型',
            formatter: (row, column, cellValue) => {
              return this.common.transformDict(cellValue, JSON.parse(this.$store.state.ps.DICT_LIST
                .permResourceType))
            },
          },
          {
            prop: 'component',
            label: '路由组件',
            formatter: (row, column, cellValue) => {
              return this.common.isNull(cellValue)
            },
          },
          {
            prop: 'sort',
            label: '排序',
            formatter: (row, column, cellValue) => {
              return this.common.transformDoubleByTwoAndUp(cellValue)
            },
          },
        ],
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
      that.options.loading = true;
      that.resourceList = JSON.parse(that.$store.state.ps.DICT_LIST.permResourceType);
      that.isCacheList = JSON.parse(that.$store.state.ps.DICT_LIST.isTrueType);
      that.linkTypeList = JSON.parse(that.$store.state.ps.DICT_LIST.permLinkType);
      that.treeInit();
      this.table = this.$refs.table;
      that.table.init();
    },
    //初始化左侧列表
    treeInit() {
      let that = this;
      that.treeloading = true;
      that.$api.systemRequest.permLeftData(that.params)
        .then(res => {
          res = res.data
          if (res.code == 0) {
            that.treeloading = false;
            that.treeData[0].children = res.data;
            that.$refs.tree.setCurrentKey(0)
          } else {
            this.$notify.error({
              title: '错误提示',
              message: res.msg
            });
          }
        })
    },
    //树形点击
    handleNodeClick(data) {
      let that = this;
      if (data.id == 0) {
        that.params.parentId = "0";
        that.init();
      } else {
        that.params.parentId = data.id;
        that.table.reload();
      }
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
        parentId: 0,
        sort: "asc",
      }
      that.table.reload();
    },
    //新增
    handleAdd(path) {
      this.$refs.indexEdit.init();
    },
    // 详情
    handleDetail(index, row, path) {
      this.$refs.indexDetail.init(row.permId);
    },
    // 编辑
    handleEdit(index, row, path) {
      this.$refs.indexEdit.init(row.permId);
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
        that.$api.systemRequest.permDelete({
            id: row.permId
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
