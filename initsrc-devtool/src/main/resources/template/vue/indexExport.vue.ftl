<template>
    <el-dialog title="${functionName}导出" width="400px" :fullscreen="$store.state.ts.ISDEVICE==0?false:true"
               :visible.sync="dialogFormVisible" :close-on-click-modal="false"
               v-loading="loading">
        <el-form :model="form" ref="ruleForm" label-position="top">
            <el-row :gutter="20">
            <#list queryColumns as field>
                <#if field.isKey != '1'>
                    <el-col :span="20">
                        <el-form-item label="${field.columnComment}:" prop="${field.javaField}">
                            <#if field.htmlType == '1'>
                                <el-input v-model="form.${field.javaField}" placeholder="请输入${field.columnComment}" clearable maxlength="30" show-word-limit></el-input>
                            <#elseif field.htmlType == '3'>
                                <el-input-number size="medium" v-model="form.${field.javaField}" controls-position="right" placeholder="请输入${field.columnComment}" style="width: 100%;" :min="0">
                                </el-input-number>
                            <#elseif field.htmlType == '4'>
                                <el-input-number size="medium" v-model="form.${field.javaField}" :precision="2"  controls-position="right" placeholder="请输入${field.columnComment}" style="width: 100%;" :min="0">
                                </el-input-number>
                            <#elseif field.htmlType == '6'>
                            <#if field.queryType == "8">
                                <el-date-picker v-model="form.${field.javaField}" @change="search()" value-format="timestamp" type="daterange"
                                                range-separator="-"
                                                start-placeholder="开始日期"
                                                end-placeholder="结束日期"
                                                placeholder="选择${field.columnComment}时间" clearable style="width: 100%;" clearable>
                                </el-date-picker>
                            <#else >
                                <el-date-picker v-model="form.${field.javaField}" @change="search()" value-format="timestamp" type="datetime"
                                                placeholder="选择${field.columnComment}时间" clearable style="width: 100%;" clearable>
                                </el-date-picker>
                            </#if>
                            <#elseif field.htmlType == '10' || field.htmlType == '9'>
                                <el-select v-model="form.${field.javaField}" placeholder="请选择${field.columnComment}" <#if field.isEdit == '0'>:disabled="form.${keyLabel} != null?true:false"</#if>
                                           style="width: 100%;" clearable>
                                    <el-option v-for="item in ${field.javaField}List" :key="item.key" :label="item.label" <#if field.isSearch != '1'>:value="item.key"<#else >:value="item.id"</#if> >
                                    </el-option>
                                </el-select>
                            <#elseif field.htmlType == '11'>
                                <i-tree-select v-model="form.${field.javaField}" :normalizer="normalizer" placeholder="请选择${field.columnComment}" :options="${field.javaField}List"></i-tree-select>
                            <#elseif field.htmlType == '12'>
                                <el-checkbox-group v-model="form.${field.javaField}" style="width: 100%;"  <#if field.isEdit == '0'>:disabled="form.${keyLabel} != null?true:false"</#if>>
                                    <template v-for="(item, index) in ${field.javaField}List">
                                        <el-checkbox <#if field.isSearch != '1'>:label="item.key"<#else >:label="item.id"</#if>>{{item.label}}</el-checkbox>
                                    </template>
                                </el-checkbox-group>
                            <#elseif field.htmlType == '13'>
                                <el-radio-group v-model="form.${field.javaField}" style="width: 100%;"  <#if field.isEdit == '0'>:disabled="form.${keyLabel} != null?true:false"</#if>>
                                    <template v-for="(item, index) in ${field.javaField}List">
                                        <el-radio-button <#if field.isSearch != '1'>:label="item.key"<#else >:label="item.id"</#if>>{{item.label}}</el-radio-button>
                                    </template>
                                </el-radio-group>
                            <#else >
                                <el-input v-model="form.${field.javaField}" placeholder="请输入${field.columnComment}" clearable maxlength="30" show-word-limit></el-input>
                            </#if>
                        </el-form-item>
                    </el-col>
                </#if>
            </#list>
            </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="save('ruleForm')" :loading="loading">确 定</el-button>
        </div>
    </el-dialog>
</template>

<script>
const baseForm = {
    <#list queryColumns as field >
    <#if field.queryType == "8">
    begin${field.javaField?cap_first}:null,
    end${field.javaField?cap_first}:null,
    <#else >
    ${field.javaField}: null,
    </#if>
    </#list>
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
            <#list queryColumns as field>
            <#if  field.htmlType == '9' || field.htmlType == '10' || field.htmlType == '11' || field.htmlType == '12' || field.htmlType == '13'>
            ${field.javaField}List:[],
            </#if>
            </#list>
        };
    },
    methods: {
        init(data) {
            let that = this;
            that.dialogFormVisible = true;
            <#list queryColumns as field>
            <#if  field.htmlType == '9' || field.htmlType == '10' || field.htmlType == '11' || field.htmlType == '12' || field.htmlType == '13'>
            <#if field.isSearch != '1'>
            <#if field.dictType ??>
            that.${field.javaField}List = JSON.parse(that.$store.state.ps.DICT_LIST.${field.dictType});
            </#if>
            </#if>
            </#if>
            </#list>
            <#if isSelect == '1'>
            that.$api.${moduleName}Request.${bizName}SelectData({
                id:data
            })
                .then(res => {
                    that.loading = false;
                    res = res.data
                    if (res.code == 0) {
                        <#list queryColumns as field>
                        <#if  field.htmlType == '9' || field.htmlType == '10' || field.htmlType == '12' || field.htmlType == '13'>
                        <#if field.isSearch == '1'>
                        that.${field.javaField}List = res.data.${field.javaField}List
                        </#if>
                        </#if>
                        <#if field.htmlType == '11'>
                        var ${field.javaField}Tree =[{
                            id:"0",
                            label:"顶层",
                            children:res.data.${field.javaField}List
                        }]
                        that.${field.javaField}List = ${field.javaField}Tree
                        </#if>
                        </#list>
                    } else {
                        this.$notify.error({
                            title: '错误提示',
                            message: res.msg
                        });
                        that.dialogFormVisible = false;
                    }
                })
            </#if>
        },
        save(formName) {
            let that = this;
            that.$refs[formName].validate((valid) => {
                if (valid) {
                    that.loading = true;
                    <#list queryColumns as field>
                    <#if field.queryType == "8">
                    if(that.form.${field.javaField} != null){
                        that.form.begin${field.javaField?cap_first} = that.form.${field.javaField}[0];
                        that.form.end${field.javaField?cap_first} = that.form.${field.javaField}[1];
                    }
                    </#if>
                    </#list>
                    that.$api.${moduleName}Request.${bizName}Export(that.form)
                        .then(res => {
                            that.loading = false;
                            that.common.fileDownloads(res,"xls");
                            that.dialogFormVisible = false;
                        })
                } else {
                    return false;
                }
            });
        },
        <#list columns as field>
        <#if field.htmlType == '11'>
        //去掉children=[]的children属性
        normalizer(node) {
            if (node.children == null) {
                delete node.children;
            }
        },
        </#if>
        </#list>
    }
};
</script>

<style scoped>
    /deep/ .el-dialog__body {
        height: 30vh;
        overflow: auto;
    }
</style>
