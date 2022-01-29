 const baseForm = {
    id: null,
    dictKey: null,
    type: 1,
    value: null,
    des: null,
    item: [{
      key: '',
      label: '',
    }]
  }
  const formName = "新增字典"
  export default {
    data() {
      return {
        typeList: [],
        drList: [],
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
          dictKey: [{
            required: true,
            message: "请输入字典名称",
            trigger: "blur",
          }, ],
          type: [{
            required: true,
            message: "请输入字典类型",
            trigger: "change",
          }, ],
          value: [{
            required: true,
            message: "请输入字典值",
            trigger: "blur",
          }, ],
          key: [{
            required: true,
            message: '请输入Key',
            trigger: 'change'
          }],
          label: [{
            required: true,
            message: '请输入label',
            trigger: 'change'
          }],
        }
      };
    },
    methods: {
      //初始化
      init(data) {
        let that = this;
        that.dialogFormVisible = true;
        this.$nextTick(() => {
          that.form = { ...baseForm
          };
          this.form.item = [{
            key: '',
            label: '',
          }]
          that.formName = formName;
          that.typeList = JSON.parse(that.$store.state.ps.DICT_LIST.dictType);
          if (data != null) {
            that.formName = "编辑字典";
            that.$api.systemRequest.dictDetail({
                id: data
              })
              .then(res => {
                res = res.data;
                that.loading = false;
                if (res.code == 0) {
                  that.form = res.data
                  if (that.form.type == "2") {
                    that.$set(that.form,"item",JSON.parse(that.form.value))
                  }
                } else {
                  that.$notify.error({
                    title: '错误提示',
                    message: res.msg
                  });
                  that.dialogFormVisible = false;
                }
              })
          } else {
            that.loading = false;
          }
        })
      },
      //保存
      save(formName) {
        let that = this;
        that.$refs[formName].validate((valid) => {
          if (valid) {
            that.loading = true;
            if (that.form.type == 2) {
              that.form.value = JSON.stringify(that.form.item)
            }
            if (that.form.dictId != null) {
              that.$api.systemRequest.dictUpdate(that.form)
                .then(res => {
                   res = res.data;
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
                    that.form.value = JSON.parse(that.form.item)
                    this.$notify.error({
                      title: '错误提示',
                      message: res.msg
                    });
                  }
                })
            } else {
              that.$api.systemRequest.dictSave(that.form)
                .then(res => {
                  that.loading = false;
                   res = res.data;
                  if (res.code == 0) {
                    that.$parent.init();
                    that.dialogFormVisible = false;
                    that.$notify({
                      title: '成功',
                      message: res.msg,
                      type: 'success'
                    });
                  } else {
                    that.form.value = JSON.parse(that.form.item)
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
      //新增节点
      addTap() {
        this.form.item.push({
          key: '',
          label: '',
        })
      },
      //移除节点
      remTap(index) {
        this.form.item.splice(index, 1);
      },
      //上移节点
      upMove(index) {
        if (index === 0) {
          return
        }
        //在上一项插入该项
        this.form.item.splice(index - 1, 0, (this.form.item[index]))
        //删除后一项
        this.form.item.splice(index + 1, 1)
      },
      //下移节点
      downMove(index) {
        if (index === (this.form.item.length - 1)) {
          return
        }
        // 在下一项插入该项
        this.form.item.splice(index + 2, 0, (this.form.item[index]))
        // 删除前一项
        this.form.item.splice(index, 1)
      },
      changeType() {
        this.$refs["ruleForm"].clearValidate();
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
