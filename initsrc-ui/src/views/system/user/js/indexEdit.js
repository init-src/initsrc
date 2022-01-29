  const baseForm = {
    userId: null,
    deptId: null,
    deptId: "0",
    nickName: null,
    userName: null,
    userPwd: null,
    headImg: null,
    sex: null,
    birthday: null,
    email: null,
    mobile: null,
    status: null,
    roleId: ["2"],
    remark: null,
  }
  const formName = "新增用户"
  export default {
    data() {
      return {
        deptIdList: [],
        sexList: [],
        roleIdList:[],
        statusList: [],
        //加载
        loading: true,
        //表单
        form: {
          ...baseForm,
        },
        formName: formName,
        rules: {
          deptId: [{
            required: true,
            message: "请选择所属部门",
            trigger: "change",
          }, ],
          nickName: [{
            required: true,
            message: "请输入昵称",
            trigger: "blur",
          }, ],
          userName: [{
            required: true,
            message: "请输入用户名",
            trigger: "blur",
          }, ],
          email: [{
            required: true,
            validator: this.ruleCommon.checkEmail,
            trigger: "blur",
          }, ],
          mobile: [{
            required: true,
            validator: this.ruleCommon.checkMobile,
            trigger: "blur",
          }, ],
          roleId: [{
            required: true,
            message: "请选择角色",
            trigger: "blur",
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
      //初始化
      init() {
        let that = this;
        var data = that.$route.query.id
        that.loading = true;
        that.form = { ...baseForm
        };
        that.formName = formName;
        that.sexList = JSON.parse(that.$store.state.ps.DICT_LIST.sexType);
        that.statusList = JSON.parse(that.$store.state.ps.DICT_LIST.statusType);
        that.$api.systemRequest.userSelectData({
            id: data
          })
          .then(res => {
            res = res.data
            that.loading = false;
            if (res.code == 0) {
              var baseTree = [{
                id: "0",
                label: "顶层",
                children: res.data.deptIdList
              }]
              that.deptIdList = baseTree
              that.roleIdList = res.data.roleIdList
            } else {
              this.$notify.error({
                title: '错误提示',
                message: res.msg
              });
              that.common.goBack();
            }
          })
        if (data != null) {
          that.formName = "编辑用户";
          that.$api.systemRequest.userDetail({
              id: data
            })
            .then(res => {
              res = res.data
              that.loading = false;
              if (res.code == 0) {
                that.form = res.data
                that.$set(that.form,"roleId",[])
                that.form.roles.forEach(function(item){
                  that.form.roleId.push(item.roleId)
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
      },
      //保存
      save(formName) {
        let that = this;
        that.$refs[formName].validate((valid) => {
          if (valid) {
            that.loading = true;
            if (that.form.userId != null) {
              that.$api.systemRequest.userUpdate(that.form)
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
              that.$api.systemRequest.userSave(that.form)
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
      async handleHeadImg(file) {
        let that = this;
        if (this.common.isFileSize(file) && this.common.imageUploadType(file)) {
          var url = await this.common.uploadFile(file, "image");
          if (url != null) {
            that.form.headImg = url;
            that.$refs["ruleForm"].validateField('headImg')
          }
        }
      },
      closeDialog() {
        this.form = {
          ...baseForm,
        };
        this.loading = false;
        this.$refs["ruleForm"].resetFields();
      },
    },
    mounted() {
      this.init();
    }
  };