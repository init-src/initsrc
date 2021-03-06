<template>
    <el-dialog title="导入提示" top="15vh" width="450px" :fullscreen="$store.state.ts.ISDEVICE==0?false:true" :visible.sync="dialogFormVisible" :close-on-click-modal="false"
               v-loading="loading">
        <div>
            <p style="margin-bottom: 1rem;">操作步骤：</p>
            <p style="margin-bottom: 1rem;">1、下载 <a :href="importModule" style="color: #f96332!important;" @click="download()" >《${functionName}导入模板》</a>；</p>
            <p style="margin-bottom: 1rem;">2、打开下载表，将对应字段信息输入或粘贴进本表。为保障导入成功，请按表内填表要求进行填写；</p>
            <p style="margin-bottom: 1rem;">
                3、信息输入完毕，点击 “点击上传或拖拽文件到指定区域”，或点击选择excel文档。
            </p>
            <div>
                <el-upload class="image-uploader" :multiple="false" :auto-upload="true" :file-list="fileList" list-type="text"
                           :show-file-list="true" :before-upload="beforeUpload" :drag="true" action :limit="1" :on-exceed="handleExceed"
                           :http-request="uploadFile">
                    <i class="el-icon-upload"></i>
                    <div class="el-upload__text">
                        将文件拖到此处，或
                        <em>点击上传</em>
                    </div>
                    <div class="el-upload__tip" slot="tip">一次只能上传一个文件，仅限XLS/XLSX格式，单文件不超过10MB</div>
                </el-upload>
            </div>
        </div>
    </el-dialog>
</template>

<script>
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
        init(data) {
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
            that.loading = true;
            that.$api.${moduleName}Request.${bizName}Import(fileObj).then(res => {
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
        download(){
            let that = this;
            that.$api.${moduleName}Request.${bizName}DownloadExcel({})
                .then(res => {
                    that.loading = false;
                    that.common.fileDownloads(res, "xls");
                    that.dialogFormVisible = false;
                })
        }
    }
};
</script>

<style scoped>
    /deep/.el-dialog__body {
        height: 60vh;
        overflow: auto;
    }
</style>
