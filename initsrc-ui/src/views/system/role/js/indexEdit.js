const baseForm = {
  roleId: null,
  roleName: null,
  isSearch: "0",
  powerDepts: null,
  powerDepts: [],
  status: 1,
  des: null,
}
const formName = "新增角色"
export default {
  data() {
    return {
      isSearchList: [],
      powerDeptsList: [],
      statusList: [],
      roleIdList: [],
      permList: [],
      permL: [], //获取权限列表
      checkAll: false,
      //加载
      loading: true,
      //表单
      form: {
        ...baseForm,
      },
      formName: formName,
      rules: {
        roleName: [{
          required: true,
          message: "请输入角色名称",
          trigger: "blur",
        }, ],
        isSearch: [{
          required: true,
          message: "请选择查询权限",
          trigger: "change",
        }, ],
        status: [{
          required: true,
          message: "请选择状态",
          trigger: "change",
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
        loading: this.loading,
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
        loading: this.loading,
        plain: false, //按钮样式
        disabled: false, //按钮禁用
        method: (index, row) => { //按钮方法
          this.common.goBack();
        }
      }, ],
    };
  },
  methods: {
    //初始化
    init() {
      let that = this;
      var data = that.$route.query.id
      that.loading = true;
      that.form = { ...baseForm
      };
      that.formName = formName;
      that.isSearchList = JSON.parse(that.$store.state.ps.DICT_LIST.isSearchType);
      that.statusList = JSON.parse(that.$store.state.ps.DICT_LIST.statusType);
      that.$api.systemRequest.roleSelectData({
          id: data
        })
        .then(res => {
          res = res.data
          that.loading = false;
          if (res.code == 0) {
            that.powerDeptsList = res.data.powerDeptsList
            that.permList = res.data.permList;
            that.roleIdList = res.data.roleIdList;
            if (data != null) {
              that.formName = "编辑角色";
              that.$api.systemRequest.roleDetail({
                  id: data
                })
                .then(res => {
                  res = res.data
                  that.loading = false;
                  if (res.code == 0) {
                    that.form = res.data
                    if(null != that.form.powerDepts && that.form.powerDepts != ""){
                       that.form.powerDepts = that.form.powerDepts.split(",");
                    }else{
                      that.form.powerDepts = [];
                    }
                     that.$nextTick(() => {
                      that.forEach(that.form.permVos);
                    })
                  } else {
                    this.$notify.error({
                      title: '错误提示',
                      message: res.msg
                    });
                    that.common.goBack();
                  }
                })
            } else {
              that.loading = false;
            }
          } else {
            this.$notify.error({
              title: '错误提示',
              message: res.msg
            });
            that.common.goBack();
          }
        })

    },
    //保存
    save(formName) {
      let that = this;
      that.$refs[formName].validate((valid) => {
        if (valid) {
          that.loading = true;
          that.form.permList = that.getPermList();
          if (that.form.roleId != null) {
            that.$api.systemRequest.roleUpdate(that.form)
              .then(res => {
                res = res.data
                that.loading = false;
                if (res.code == 0) {
                  that.common.goBack();
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
            that.$api.systemRequest.roleSave(that.form)
              .then(res => {
                res = res.data
                that.loading = false;
                if (res.code == 0) {
                  that.common.goBack();
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
          }
        } else {
          return false;
        }
      });
    },
    //去掉children=[]的children属性
    normalizer(node) {
      if (node.children == null) {
        delete node.children;
      }
    },
    closeDialog() {
      this.form = {
        ...baseForm,
      };
      this.loading = false;
      this.$refs["ruleForm"].resetFields();
    },
    //点击勾选
    checkPerm(value, node) {
      if (node.children != null && node.children.length > 0) {
        this.forEachPerm(node.children, value);
      }
      this.getParent(this.permList, node.parentId, value)
    },
    //点击勾选全部
    checkAllPerm(value, node) {
      let that = this;
      var forFn = function(arr) {
        arr.forEach(function(item) {
          item.checked = that.$set(item, 'checked', value)
          if (item.children != undefined && item.children.length > 0) {
            forFn(item.children);
          }
        })
      }
      forFn(node);
    },
    //遍历权限
    forEachPerm(data, value) {
      let that = this;
      data.forEach(function(item) {
        if (value != undefined) {
          item.checked = that.$set(item, 'checked', value)
        }
        if (item.checked) {
          that.permL.push(item.id)
        }
        if (item.children != undefined && item.children.length > 0) {
          that.forEachPerm(item.children, value);
        }
      })
      return that.permL;
    },
    //获取父节点
    getParent(arr1, id) {
      let that = this;
      var temp = []
      var forFn = function(arr, id) {
        for (var i = 0; i < arr.length; i++) {
          var item = arr[i]
          if (item.id === id) {
            arr[i].checked = that.$set(arr[i], 'checked', true)
            temp.push(item)
            forFn(arr1, item.parentId)
            break
          } else {
            if (item.children) {
              forFn(item.children, id)
            }
          }
        }
      }
      forFn(arr1, id)
      return temp
    },
    //遍历权限
    async forEach(data) {
      let that = this;
      var forFns = function(arr) {
        arr.forEach(function(item) {
          that.permL.push(item.id);
          if (item.children != null && item.children.length > 0) {
            forFns(item.children);
          }
        })
      }
      forFns(data);
      await this.initMenu(that.permList);
    },
    //初始化菜单
    initMenu(data) {
      let that = this;

      console.log(11)
      data.forEach(function(item){
        that.permL.forEach(function(it) {
          if (item.id == it) {
            that.$set(item, 'checked', true)
          }
        })
        if (item.children != null && item.children.length > 0) {
          that.initMenu(item.children);
        }
      })
    },
    getPermList(data) {
      let that = this;
      var list = [];
      var forFn = function(arr) {
        for (var i = 0; i < arr.length; i++) {
          if (arr[i].checked) {
            list.push(arr[i].id)
          }
          if (arr[i].children != null && arr[i].children.length > 0) {
            forFn(arr[i].children);
          }
        }
      }
      forFn(that.permList)
      return list;
    },
  },
  mounted() {
    this.init();
  }
};
