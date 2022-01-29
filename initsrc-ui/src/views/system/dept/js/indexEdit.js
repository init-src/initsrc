const baseForm = {
  deptId: null,
  parentId: null,
  parentId: "0",
  code: null,
  name: null,
  linkMan: null,
  linkMobile: null,
  sort: null,
  sort: "0",
  status: "1",
}
const formName = "新增部门"
export default {
  data() {
    return {
      parentIdList: [],
      statusList: [],
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
          message: "请选择上级部门",
          trigger: "change",
        }, ],
        code: [{
          required: true,
          message: "请输入部门编号",
          trigger: "blur",
        }, ],
        name: [{
          required: true,
          message: "请输入部门名称",
          trigger: "blur",
        }, ],
        linkMan: [{
          required: true,
          message: "请输入负责人",
          trigger: "blur",
        }, ],
        linkMobile: [{
          required: true,
          validator: this.ruleCommon.checkMobile,
          trigger: "blur",
        }, ],
        status: [{
          required: true,
          message: "请选择状态（0：正常 1：停用）",
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
      that.statusList = JSON.parse(that.$store.state.ps.DICT_LIST.statusType);
      that.$api.systemRequest.deptSelectData({
          id: data
        })
        .then(res => {
          res = res.data
          that.loading = false;
          if (res.code == 0) {
            var parentIdTree = [{
              id: "0",
              label: "顶层",
              children: res.data.parentIdList
            }]
            that.parentIdList = parentIdTree
          } else {
            this.$notify.error({
              title: '错误提示',
              message: res.msg
            });
            that.dialogFormVisible = false;
          }
        })
      if (data != null) {
        that.formName = "编辑部门";
        that.$api.systemRequest.deptDetail({
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
          if (that.form.deptId != null) {
            that.$api.systemRequest.deptUpdate(that.form)
              .then(res => {
                res = res.data
                that.loading = false;
                if (res.code == 0) {
                  that.$parent.init();
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
            that.$api.systemRequest.deptSave(that.form)
              .then(res => {
                res = res.data
                that.loading = false;
                if (res.code == 0) {
                  that.$parent.init();
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
