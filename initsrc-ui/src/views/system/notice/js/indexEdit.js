const baseForm = {
    noticeId: null,
    title: null,
    content: null,
    status: "1",
  }
  const formName = "新增通知公告"
  export default {
    data() {
      return {
        statusList: [],
        //加载
        loading: true,
        //表单
        form: {
          ...baseForm,
        },
        formName: formName,
        rules: {
          title: [{
            required: true,
            message: "请输入公告标题",
            trigger: "blur",
          }, ],
          content: [{
            required: true,
            message: "请输入公告内容",
            trigger: "blur",
          }, ],
          status: [{
            required: true,
            message: "请选择公告状态",
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
        that.statusList = JSON.parse(that.$store.state.ps.DICT_LIST.statusType);
        if (data != null) {
          that.formName = "编辑通知公告";
          that.$api.systemRequest.noticeDetail({
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
            if (that.form.noticeId != null) {
              that.$api.systemRequest.noticeUpdate(that.form)
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
              that.$api.systemRequest.noticeSave(that.form)
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