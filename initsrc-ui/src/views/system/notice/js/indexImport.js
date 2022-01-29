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
        return this.common.excelUploadType(file) && this.common.isFileSize(file);
      },
      // 上传文件个数超过定义的数量
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 1 个文件，请删除后继续上传`);
      },
      // 上传文件
      uploadFile(item) {
        let that = this;
        const fileObj = item.file;
        that.loading = true;
        that.$api.systemRequest.noticeImport(fileObj).then(res => {
          that.loading = false;
          var reader = new FileReader();
          reader.readAsText(res.data, 'utf-8');
          reader.onload = function() {
            try {
              let data = JSON.parse(reader.result);
              if (data.code == 0) {
                that.$message.success(data.msg);
                that.$parent.init();
                that.dialogFormVisible = false;
              } else {
                that.$message.error(data.msg);
              }
            } catch (e) {
              that.common.fileDownloads(res, "xls")
              that.$message.error("导入失败，请查看下载下来的Excel核对");
            }
          }
        });
        that.fileList = [];
      },
      download() {
        let that = this;
        that.$api.systemRequest.noticeDownloadExcel(that.form)
          .then(res => {
            that.loading = false;
            that.common.fileDownloads(res, "xls");
            that.dialogFormVisible = false;
          })
      }
    }
  };
