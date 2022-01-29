const baseForm = {
  columns: [],
  refTables: []
}
const formName = "新增数据库表"
export default {
  data() {
    return {
      loading: true,
      formName: formName,
      dialogFormVisible: false,
      type: "1",
      subKeyList: [],
      leftTreeList: [],
      permList:[],
      ruleCommonList:[],
      dictList: this.$store.state.ps.DICT_LIST,
      formSearch: {

      },
      form: {
        ...baseForm,
      },
      //生成模板
      genModule: [{
          key: "1",
          label: "单表"
        },
        {
          key: "2",
          label: "树形"
        },
      ],
      //vue编辑模块类型
      vueModule: [{
          key: "1",
          label: "弹窗"
        },
        {
          key: "2",
          label: "抽屉"
        },
        {
          key: "3",
          label: "新页面"
        },
      ],
      //vue详情模块类型
      vueDetailModule: [{
          key: "1",
          label: "抽屉"
        },
        {
          key: "3",
          label: "新页面"
        },
      ],
      //所有表格列表
      tables: [],
      //表格基本参数
      options: {
        data: [],
        columns: [{
          prop: 'columnName',
          label: '字段列名',
          width: 110,
          formatter: (row, column, cellValue) => {
            return this.common.isNull(cellValue)
          }
        },
        {
          prop: 'columnComment',
          label: '字段描述',
          width: 150,
          render: (h, params) => {
            return h('el-input', {
              props: {
                value: params.row.columnComment,
                size: 'small'
              },
              on: {
                input: (value) => { //触发事件是输入,用双引号括起来，
                  params.row.columnComment = value
                }
              }
            })
          }
        },
        {
          prop: 'columnTypeName',
          label: '数据库类型',
          width: 100,
          formatter: (row, column, cellValue) => {
            return this.common.isNull(cellValue)
          }
        },
        {
          prop: 'javaField',
          label: 'JAVA别名',
          width: 120,
          render: (h, params) => {
            return h('el-input', {
              props: {
                value: params.row.javaField,
                size: 'small'
              },
              on: {
                input: (value) => { //触发事件是输入,用双引号括起来，
                  params.row.javaField = value
                }
              }
            })
          }
        },
        {
          prop: 'javaType',
          label: 'JAVA类型',
          width: 100,
          render: (h, params) => {
            return h('el-select', {
                props: {
                  value: params.row.javaType,
                  size: 'small'
                },
                on: {
                  change: (value) => { //触发事件是on-change,用双引号括起来，
                    params.row.javaType = value
                  }
                },
              },
              [
                h('el-option', {
                  props: {
                    value: 'Long'
                  }
                }, 'Long'),
                h('el-option', {
                  props: {
                    value: 'String'
                  }
                }, 'String'),
                h('el-option', {
                  props: {
                    value: 'Integer'
                  }
                }, 'Integer'),
                h('el-option', {
                  props: {
                    value: 'Double'
                  }
                }, 'Double'),
                h('el-option', {
                  props: {
                    value: 'BigDecimal'
                  }
                }, 'BigDecimal'),
                h('el-option', {
                  props: {
                    value: 'Date'
                  }
                }, 'Date'),
              ])
          }
        },
        {
          prop: 'isList',
          label: '列表',
          width: 50,
          render: (h, params) => {
            return h('el-switch', {
              props: {
                value: params.row.isList, //控制开关的打开或关闭状态，官网文档属性是value
                'active-value': "1",
                'inactive-value': "0",
              },
              on: {
                change: (value) => { //触发事件是on-change,用双引号括起来，
                  params.row.isList = value
                }
              }
            })
          }
        },
        {
          prop: 'isInsert',
          label: '新增',
          width: 50,
          render: (h, params) => {
            return h('el-switch', {
              props: {
                value: params.row.isInsert, //控制开关的打开或关闭状态，官网文档属性是value
                'active-value': "1",
                'inactive-value': "0",
              },
              on: {
                change: (value) => { //触发事件是on-change,用双引号括起来，
                  params.row.isInsert = value
                }
              }
            })
          }
        },
        {
          prop: 'isEdit',
          label: '编辑',
          width: 50,
          render: (h, params) => {
            return h('el-switch', {
              props: {
                value: params.row.isEdit, //控制开关的打开或关闭状态，官网文档属性是value
                'active-value': "1",
                'inactive-value': "0",
              },
              on: {
                change: (value) => { //触发事件是on-change,用双引号括起来，
                  params.row.isEdit = value
                }
              }
            })
          }
        },
        {
          prop: 'isDetail',
          label: '详情',
          width: 50,
          render: (h, params) => {
            return h('el-switch', {
              props: {
                value: params.row.isDetail, //控制开关的打开或关闭状态，官网文档属性是value
                'active-value': "1",
                'inactive-value': "0",
              },
              on: {
                change: (value) => { //触发事件是on-change,用双引号括起来，
                  params.row.isDetail = value
                }
              }
            })
          }
        },
        {
          prop: 'isQuery',
          label: '查询',
          width: 50,
          render: (h, params) => {
            return h('el-switch', {
              props: {
                value: params.row.isQuery, //控制开关的打开或关闭状态，官网文档属性是value
                'active-value': "1",
                'inactive-value': "0",
              },
              on: {
                change: (value) => { //触发事件是on-change,用双引号括起来，
                  params.row.isQuery = value
                }
              }
            })
          }
        },
        {
          prop: 'isRequired',
          label: '必填',
          width: 50,
          render: (h, params) => {
            return h('el-switch', {
              props: {
                value: params.row.isRequired, //控制开关的打开或关闭状态，官网文档属性是value
                'active-value': "1",
                'inactive-value': "0",
              },
              on: {
                change: (value) => { //触发事件是on-change,用双引号括起来，
                  params.row.isRequired = value
                }
              }
            })
          }
        },
        {
          prop: 'isOnly',
          label: '是否唯一',
          width: 100,
          render: (h, params) => {
            return h('el-select', {
                props: {
                  value: params.row.isOnly,
                  size: 'small'
                },
                on: {
                  change: (value) => { //触发事件是on-change,用双引号括起来，
                    params.row.isOnly = value
                  }
                },
              },
              [
                h('el-option', {
                  props: {
                    label: "否",
                    value: '0'
                  }
                }, '否'),
                h('el-option', {
                  props: {
                    label: "是",
                    value: '1'
                  }
                }, '是')
              ])
          }
        },
        {
          prop: 'queryType',
          label: '查询方式',
          width: 80,
          render: (h, params) => {
            return h('el-select', {
                props: {
                  value: params.row.queryType,
                  size: 'small'
                },
                on: {
                  change: (value) => { //触发事件是on-change,用双引号括起来，
                    params.row.queryType = value
                  }
                },
              },
              [
                h('el-option', {
                  props: {
                    label: "=",
                    value: '1'
                  }
                }, '='),
                h('el-option', {
                  props: {
                    label: "!=",
                    value: '2'
                  }
                }, '!='),
                h('el-option', {
                  props: {
                    label: ">",
                    value: '3'
                  }
                }, '>'),
                h('el-option', {
                  props: {
                    label: ">=",
                    value: '4'
                  }
                }, '>='),
                h('el-option', {
                  props: {
                    label: "<",
                    value: '5'
                  }
                }, '<'),
                h('el-option', {
                  props: {
                    label: "<=",
                    value: '6'
                  }
                }, '<='),
                h('el-option', {
                  props: {
                    label: "LIKE",
                    value: '7'
                  }
                }, 'LIKE'),
                h('el-option', {
                  props: {
                    label: "BETWEEN",
                    value: '8'
                  }
                }, 'BETWEEN'),
              ])
          }
        },
        {
          prop: 'htmlType',
          label: '显示类型',
          width: 120,
          render: (h, params) => {
            return h('el-select', {
                props: {
                  value: params.row.htmlType,
                  size: 'small'
                },
                on: {
                  change: (value) => { //触发事件是on-change,用双引号括起来，
                    params.row.htmlType = value
                  }
                },
              },
              [
                h('el-option', {
                  props: {
                    label: "文本框",
                    value: '1'
                  }
                }, '文本框'),
                h('el-option', {
                  props: {
                    label: "文本域",
                    value: '2'
                  }
                }, '文本域'),
                h('el-option', {
                  props: {
                    label: "数字文本框",
                    value: '3'
                  }
                }, '数字文本框'),
                h('el-option', {
                  props: {
                    label: "浮点型文本框",
                    value: '4'
                  }
                }, '浮点型文本框'),
                h('el-option', {
                  props: {
                    label: "富文本框",
                    value: '5'
                  }
                }, '富文本框'),
                h('el-option', {
                  props: {
                    label: "日期选择器",
                    value: '6'
                  }
                }, '日期选择器'),
                h('el-option', {
                  props: {
                    label: "图片选择器",
                    value: '7'
                  }
                }, '图片选择器'),
                h('el-option', {
                  props: {
                    label: "文件选择器",
                    value: '8'
                  }
                }, '文件选择器'),
                h('el-option', {
                  props: {
                    label: "选择器",
                    value: '9'
                  }
                }, '选择器'),
                h('el-option', {
                  props: {
                    label: "远程选择器",
                    value: '10'
                  }
                }, '远程选择器'),
                h('el-option', {
                  props: {
                    label: "树形选择器",
                    value: '11'
                  }
                }, '树形选择器'),
                h('el-option', {
                  props: {
                    label: "复选框",
                    value: '12'
                  }
                }, '复选框'),
                h('el-option', {
                  props: {
                    label: "单选框",
                    value: '13'
                  }
                }, '单选框'),
              ])
          }
        },
        {
          prop: 'isSearch',
          label: '设置',
          render: (h, params) => {
            var content = '';
            if (params.row.htmlType == '9' || params.row.htmlType == '10' || params.row.htmlType == '11' || params.row
              .htmlType == '12' || params.row.htmlType == '13') {
              if (params.row.isSearch == 0) {
                content = " | 字典类型：" + params.row.dictType;
                if(params.row.isRequired == '1'){
                  content +="、正则名称：" + params.row.verifyName;
                }
              } else if (params.row.isSearch == '1') {
                if (params.row.htmlType == '11') {
                  content = " | 关联表：" + params.row.leftTable + "、关联主键字段：" + params.row.leftKey +
                    "、关联父类字段：" + params.row.leftParent + "、关联名称字段：" + params.row.leftLabel + "、关联名称字段别名：" + params.row.leftAlias
                    + "、正则名称：" + params.row.verifyName;
                } else {
                  content = " | 关联表：" + params.row.leftTable + "、关联主键字段：" + params.row.leftKey +
                    "、关联名称字段：" + params.row.leftLabel + "、关联名称字段别名：" + params.row.leftAlias ;
                }
                if(params.row.isRequired == '1'){
                  content +="、正则名称：" + params.row.verifyName;
                }
              }
              return h('span', {
                attrs: {
                  "style": "cursor:pointer"
                },
                on: {
                  click: (value) => { //触发事件是on-change,用双引号括起来，
                    this.setSearch(params.row)
                  }
                }
              }, '点击设置' + content)
            } else {
              if(params.row.isRequired == '1'){
                return h('span', {
                  attrs: {
                    "style": "cursor:pointer"
                  },
                  on: {
                    click: (value) => { //触发事件是on-change,用双引号括起来，
                      this.setSearch(params.row)
                    }
                  }
                }, '点击设置' + "  |  正则名称：" + params.row.verifyName )
              }else{
                return h('span', {}, '无需设置')
              }

            }
          }
        },
        ],
      },
      rules: {
        tableName: [{
          required: true,
          message: "请输入表名称",
          trigger: "blur",
        }, ],
        tableComment: [{
          required: true,
          message: "请输入表描述",
          trigger: "blur",
        }, ],
        className: [{
          required: true,
          message: "请输入实体类名称",
          trigger: "blur",
        }, ],
        genAuthor: [{
          required: true,
          message: "请输入作者",
          trigger: "blur",
        }, ],
        viewPath: [{
          required: true,
          message: "请输入页面路径",
          trigger: "blur",
        }, ],
        packageName: [{
          required: true,
          message: "请输入生成包路径",
          trigger: "blur",
        }, ],
        moduleName: [{
          required: true,
          message: "请输入生成模块名称",
          trigger: "blur",
        }, ],
        functionName: [{
          required: true,
          message: "请输入生成功能名称",
          trigger: "blur",
        }, ],
        bizName: [{
          required: true,
          message: "请输入业务名称",
          trigger: "blur",
        }, ],
        parentKey: [{
          required: true,
          message: "请选择父类字段",
          trigger: "change",
        }, ],
        subTableId: [{
          required: true,
          message: "请选择关联子表的表名",
          trigger: "change",
        }, ],
        subKey: [{
          required: true,
          message: "请选择子表关联的外键",
          trigger: "change",
        }, ],
        treeParent: [{
          required: true,
          message: "请选择树形父类字段",
          trigger: "change",
        }, ],
        treeLabel: [{
          required: true,
          message: "请选择树形名称字段",
          trigger: "change",
        }, ],
      },
      searchRules: {
        leftTable: [{
          required: true,
          message: "请选择关关联表",
          trigger: "change",
        }, ],
        leftKey: [{
          required: true,
          message: "请选择关关联主键字段",
          trigger: "change",
        }, ],
        leftParent: [{
          required: true,
          message: "请选择关联父类字段",
          trigger: "change",
        }, ],
        leftLabel: [{
          required: true,
          message: "请选择关联名称字段",
          trigger: "change",
        }, ],
        leftAlias:[{
          required: true,
          message: "请输入关联名称字段别名",
          trigger: "blur",
        }, ],
      },
      //footerbtn
      footerList: [{
        label: '保存', //按钮名称
        type: 'primary', //按钮类型
        show: (index, row) => { //按钮是否展示
          return true
        },
        icon: '', //按钮图标
        loading: false,
        plain: false, //按钮样式
        disabled: false, //按钮禁用
        method: (index, row) => { //按钮方法
          this.save('ruleForm')
        }
      }, {
        label: '取消', //按钮名称
        type: 'danger', //按钮类型
        show: (index, row) => { //按钮是否展示
          return true
        },
        icon: '', //按钮图标
        loading: false,
        plain: false, //按钮样式
        disabled: false, //按钮禁用
        method: (index, row) => { //按钮方法
          this.common.goBack();
        }
      }, ],
    };
  },
  methods: {
    init() {
      let that = this;
      var id = this.$route.query.id
      that.loading = true;
      that.options.loading = true;
      that.form = { ...baseForm
      };
      that.ruleCommonList = JSON.parse(that.$store.state.ps.DICT_LIST.ruleCommon);
      that.formName = formName;
      if (id != null) {
        that.formName = "编辑代码生成"
        that.$api.devRequest.tableDetail({
            id: id
          })
          .then(res => {
            res =res.data
            if (res.code == 0) {
              that.loading = false;
              that.options.loading = false;
              that.form = res.data;
              that.tables = that.form.tables;
              that.options.data = that.form.columns;
              var baseTree =[{
                id:"0",
                label:"顶层",
                children:that.form.permList
              }]
              that.permList = baseTree;
              that.selectSub(that.form.subTableId)
              that.selectTableType(that.form.vueTableType);
            } else {
              this.$notify.error({
                title: '错误提示',
                message: res.msg
              });
            }
          })
      }
    },
    //保存
    save(formName) {
      let that = this;
      that.$refs[formName].validate((valid) => {
        if (valid) {
          that.loading = true;
          that.options.loading = true;
          that.$api.devRequest.tableUpdate(that.form)
            .then(res => {
              res =res.data
              that.loading = false;
              if (res.code == 0) {
                that.options.loading = false;
                that.common.goBack();
                that.dialogFormVisible = false;
                that.$notify({
                  title: '成功',
                  message: res.msg,
                  type: 'success'
                });
              } else {
                this.$notify.error({
                  title: '错误提示',
                  message: res.msg
                });
              }
            })
        } else {
          return false;
        }
      });
    },
    //主子关联表联动选择
    selectSub(value) {
      let that = this;
      that.form.tables.forEach(function(item) {
        if (value == item.tableId) {
          that.subKeyList = item.columns
        }
      })
    },
    //左侧列表联动
    selectLeftTree(value,formSearch) {
      let that = this;
      that.form.tables.forEach(function(item) {
        if (value == item.tableName) {
         if(formSearch == null){
           that.formSearch.leftKey = null;
           that.formSearch.leftParent = null;
           that.formSearch.leftLabel = null;
           that.formSearch.leftAlias = null;
         }
          that.leftTreeList = item.columns
        }
      })
    },
    //选择列表页类型
    selectTableType(type) {
      let that = this;
      if (type == '2') {
        that.form.leftTable = that.form.tableId;
        that.form.tables.forEach(function(item) {
          if (that.form.tableName == item.tableName) {
            that.leftTreeList = item.columns
          }
        })
      }
    },
    normalizer(node) {
      //去掉children=[]的children属性
      if (node.children == null) {
        delete node.children;
      }
    },
    //选项卡选择
    selectType(type) {
      this.type = type
    },
    //设置字典内容弹窗
    setSearch(row) {
      this.formSearch = row
      this.selectLeftTree(row.leftTable,this.formSearch)
      this.dialogFormVisible = true;
    },
    saveSearch(formName) {
      let that = this;
      that.$refs[formName].validate((valid) => {
        if (valid) {
          this.dialogFormVisible = false;
        } else {
          return false;
        }
      });
    },
    closeDialog() {
      let that = this;
      that.$refs['ruleSearchForm'].validate((valid) => {
        if (valid) {
          this.dialogFormVisible = false;
        } else {
          that.formSearch.isSearch = "0";
          this.dialogFormVisible = false;
        }
      });
    },
    handleRemove(file, fileList) {},
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    }
  },
  created() {
    this.init();
  }
};
