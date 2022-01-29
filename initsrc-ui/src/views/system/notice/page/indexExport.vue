<template>
    <el-dialog title="导出账单合计" width="400px" :fullscreen="$store.state.ts.ISDEVICE==0?false:true"
               :visible.sync="dialogFormVisible" :close-on-click-modal="false"
               v-loading="loading">
        <el-form :model="form" ref="ruleForm" label-position="top">
            <el-row :gutter="20">
                                <el-col :span="20">
                        <el-form-item label="公告标题:" prop="title">
                                <el-input v-model="form.title" placeholder="请输入公告标题" clearable maxlength="30" show-word-limit></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="20">
                        <el-form-item label="创建时间:" prop="createTime">
                                <el-date-picker v-model="form.createTime" @change="search()" value-format="timestamp" type="daterange"
                                                range-separator="-"
                                                start-placeholder="开始日期"
                                                end-placeholder="结束日期"
                                                placeholder="选择创建时间时间" clearable style="width: 100%;" clearable>
                                </el-date-picker>
                        </el-form-item>
                    </el-col>
            </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="save('ruleForm')" :loading="loading">确 定</el-button>
        </div>
    </el-dialog>
</template>

<script src="../js/indexExport.js">

</script>

<style scoped>
    /deep/ .el-dialog__body {
        height: 30vh;
        overflow: auto;
    }
</style>
