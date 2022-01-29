const baseForm = {
  name: '',
  code: '',
  region: '',
  date1: '',
  date2: '',
  delivery: true,
  type: [],
  resource: '',
  desc: '',
  value: null,
  value2: null,
}
const formName = "新增商品"
export default {
  data() {
    return {
      loading: true,
      formName: formName,
      dialogVisible: false,
      dialogImageUrl:'',
      //footer
      footerList: [{
          label: '保存', //按钮名称
          type: 'primary', //按钮类型
          show: (index, row) => { //按钮是否展示
            return true
          },
          icon: '', //按钮图标
          loading:false,
          plain: false, //按钮样式
          disabled: false, //按钮禁用
          method: (index, row) => { //按钮方法
            this.save('ruleForm')
          }
        },{
          label: '重置', //按钮名称
          type: 'danger', //按钮类型
          show: (index, row) => { //按钮是否展示
            return true
          },
          icon: '', //按钮图标
          loading:false,
          plain: false, //按钮样式
          disabled: false, //按钮禁用
          method: (index, row) => { //按钮方法
            this.resetForm('ruleForm')
          }
        },
      ],
      form: {
        ...baseForm,
      },
      options: [{
        value: '选项1',
        label: '黄金糕'
      }, {
        value: '选项2',
        label: '双皮奶'
      }, {
        value: '选项3',
        label: '蚵仔煎'
      }, {
        value: '选项4',
        label: '龙须面'
      }, {
        value: '选项5',
        label: '北京烤鸭'
      }],
      rules: {
        name: [{
          required: true,
          message: "请输入商品名称",
          trigger: "blur",
        }, ],
        type: [{
          required: true,
          message: "请勾选活动性质",
          trigger: "change",
        }, ],
      }
    };
  },
  methods: {
    init() {
      let that = this;
      var id = this.$route.query.id
      that.loading = false;
      that.dialogFormVisible = true;
      that.form = { ...baseForm
      };
      that.formName = formName;
      if (id != null) {
        that.formName = "编辑商品"
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
              that.goBack();
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
    //重置表单
    resetForm(formName) {
      this.form = { ...baseForm
      };
      this.loading = false;
      this.$refs[formName].resetFields();
    },
    //返回上一页
    goBack() {
      this.$router.go(-1)
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
