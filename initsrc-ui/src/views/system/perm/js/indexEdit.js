const baseForm = {
    permId: null,
    parentId: "0",
    name: null,
    path: null,
    resource: 0,
    component: null,
    perm: null,
    icon: null,
    color: null,
    isCache: 0,
    sort: 0,
    linkType: 0,
  }
  const formName = "新增菜单"
  export default {
    data() {
      return {
        parentIdList: [],
        resourceList: [],
        iconList: [],
        colorList: [],
        isCacheList: [],
        linkTypeList: [],
        //加载
        loading: true,
        //弹窗展示
        dialogFormVisible: false,
        //表单
        form: {
          ...baseForm,
        },
        formName: formName,
        rules: {
          parentId: [{
            required: true,
            message: "请选择所属菜单",
            trigger: "change",
          }, ],
          name: [{
            required: true,
            message: "请输入菜单名称",
            trigger: "blur",
          }, ],
          path: [{
            required: true,
            message: "请输入菜单路径",
            trigger: "blur",
          }, ],
          resource: [{
            required: true,
            message: "请选择菜单类型",
            trigger: "change",
          }, ],
          perm: [{
            required: true,
            message: "请输入shiro权限",
            trigger: "blur",
          }, ],
          isCache: [{
            required: true,
            message: "请选择是否缓存",
            trigger: "change",
          }, ],
          linkType: [{
            required: true,
            message: "请选择链接类型",
            trigger: "change",
          }, ],
        }
      };
    },
    methods: {
      //初始化
      init(data) {
        let that = this;
        that.dialogFormVisible = true;
        that.loading = true;
        that.form = { ...baseForm
        };
        that.formName = formName;
        that.resourceList = JSON.parse(that.$store.state.ps.DICT_LIST.permResourceType);
        that.iconList = JSON.parse(that.$store.state.ps.DICT_LIST.iconList);
        that.colorList = JSON.parse(that.$store.state.ps.DICT_LIST.permColorType);
        that.isCacheList = JSON.parse(that.$store.state.ps.DICT_LIST.isTrueType);
        that.linkTypeList = JSON.parse(that.$store.state.ps.DICT_LIST.permLinkType);
        that.$api.systemRequest.permSelectData({
            id: data
          })
          .then(res => {
            res = res.data
            that.loading = false;
            if (res.code == 0) {
              var baseTree = [{
                id: "0",
                label: "顶层",
                children: res.data.parentIdList
              }]
              that.parentIdList = baseTree
            } else {
              this.$notify.error({
                title: '错误提示',
                message: res.msg
              });
              that.dialogFormVisible = false;
            }
          })
        if (data != null) {
          that.formName = "编辑菜单";
          that.$api.systemRequest.permDetail({
              id: data
            })
            .then(res => {
              res = res.data
              that.loading = false;
              if (res.code == 0) {
                that.form = res.data
              } else {
                this.$notify.error({
                  title: '错误提示',
                  message: res.msg
                });
                that.dialogFormVisible = false;
              }
            })
        } else {
          that.loading = false;
        }
      },
      //保存
      save(formName) {
        let that = this;
        that.$refs[formName].validate((valid) => {
          if (valid) {
            that.loading = true;
            if (that.form.permId != null) {
              that.$api.systemRequest.permUpdate(that.form)
                .then(res => {
                  res = res.data
                  that.loading = false;
                  if (res.code == 0) {
                    that.$parent.reload();
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
              that.$api.systemRequest.permSave(that.form)
                .then(res => {
                  res = res.data
                  that.loading = false;
                  if (res.code == 0) {
                    that.$parent.reload();
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
    }
  };