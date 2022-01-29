const baseForm = {
  title: null,
  beginCreateTime: null,
  endCreateTime: null,
}
export default {
  name: "",
  data() {
    return {
      dialogFormVisible: false,
      loading: null,
      form: {
        ...baseForm
      },
    };
  },
  methods: {
    init() {
      this.dialogFormVisible = true;
    },
    save(formName) {
      let that = this;
      that.$refs[formName].validate((valid) => {
        if (valid) {
          that.loading = true;
          if (that.form.createTime != null) {
            that.form.beginCreateTime = that.form.createTime[0];
            that.form.endCreateTime = that.form.createTime[1];
          }
          that.$api.systemRequest.noticeExport(that.form)
            .then(res => {
              that.loading = false;
              that.common.fileDownloads(res, "xls");
              that.dialogFormVisible = false;
            })
        } else {
          return false;
        }
      });
    }
  }
};
