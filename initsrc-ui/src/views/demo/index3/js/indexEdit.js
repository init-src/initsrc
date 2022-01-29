import ruleCommon from '@/assets/js/rule.js'
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
        drawer: false,
        direction:'rtl',
        form: {
          ...baseForm,
        },
        formName: formName,
        rules: {
          name: [{
           required: true,
           validator: ruleCommon.checkCar,
           trigger: 'blur'
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
        that.drawer = true;
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
              that.drawer = false;
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
      // 关闭右侧公司详情
      handleClose(done) {
        this.drawer = false
        this.form = {
          ...baseForm,
        };
        this.loading = false;
        this.$refs["ruleForm"].resetFields();
      },
    }
  };
