const baseForm = {
    name: null,
    code: null,
    parentId: null,
    region: null,
    resource: "不限",
  }
  const formName = "新增部门"
  export default {
    data() {
      return {
        loading: false,
        dialogFormVisible: false,
        form: {
          ...baseForm,
        },
        formName: formName,
        rules: {
          name: [{
            required: true,
            message: "请输入部门名称",
            trigger: "blur",
          }, ],
          parentId: [{
            required: true,
            message: "请选择上级部门",
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
        that.form = { ...baseForm
        };
        that.formName = formName;
        if (data != null) {
          that.formName = "编辑部门";
        }
      },
      //保存
      save(formName) {
        let that = this;
        that.$refs[formName].validate((valid) => {
          if (valid) {
            that.loading = true;
            setTimeout(function() {
              that.loading = false;
              that.$parent.init();
              that.dialogFormVisible = false;
              that.$notify({
                title: '成功',
                message: '这是一条成功的提示消息',
                type: 'success'
              });
            }, "1000");
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
    }
  };