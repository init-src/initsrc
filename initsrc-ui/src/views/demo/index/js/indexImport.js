export default {
  name: "",
  data() {
    return {
      dialogFormVisible: false,
      fileList: [],
      loading: null,
      importModule: "#",
    };
  },
  methods: {
    init() {
      this.dialogFormVisible = true;
    },
    beforeUpload(file) {
      return this.common.beforeExcelUpload(file);
    },
    // 上传文件个数超过定义的数量
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，请删除后继续上传`);
    },
    // 上传文件
    uploadFile(item) {
      let that = this;
      const fileObj = item.file;
      that.loading = false;
      that.fileList = [];
    },
  }
};
