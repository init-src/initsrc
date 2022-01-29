<template>
    <el-drawer class="dshfn-commonDrawer" :title="formName" :visible.sync="dialogFormVisible" direction="rtl"
               :wrapperClosable="true" :before-close="closeDialog" append-to-body :size="$store.state.ts.ISDEVICE==0?'1000px':'100%'">
        <el-form :model="form" :rules="rules" ref="ruleForm" label-position="top" >
            <el-row :gutter="20">
                <#list saveColumns as field>
                <#if field.isKey != '1'>
                <el-col :span="20">
                    <el-form-item label="${field.columnComment}:" prop="${field.javaField}">
                    <#if field.htmlType == '1'>
                        <el-input v-model="form.${field.javaField}" placeholder="请输入${field.columnComment}" clearable
                        <#if field.isEdit == '0'>:disabled="form.${keyLabel} != null?true:false"</#if> maxlength="30" show-word-limit></el-input>
                    </#if>
                    <#if field.htmlType == '3'>
                        <el-input-number size="medium" v-model="form.${field.javaField}" controls-position="right" placeholder="请输入${field.columnComment}"
                         <#if field.isEdit == '0'>:disabled="form.${keyLabel} != null?true:false"</#if> style="width: 100%;" :min="0">
                        </el-input-number>
                    </#if>
                    <#if field.htmlType == '4'>
                        <el-input-number size="medium" v-model="form.${field.javaField}" :precision="2"  controls-position="right" placeholder="请输入${field.columnComment}"
                         <#if field.isEdit == '0'>:disabled="form.${keyLabel} != null?true:false"</#if> style="width: 100%;" :min="0">
                        </el-input-number>
                    </#if>
                    <#if field.htmlType == '6'>
                        <el-date-picker v-model="form.${field.javaField}" value-format="timestamp" type="datetime"
                        <#if field.isEdit == '0'>:disabled="form.${keyLabel} != null?true:false"</#if> placeholder="选择${field.columnComment}时间" clearable style="width: 100%;">
                        </el-date-picker>
                    </#if>
                    <#if field.htmlType == '10' || field.htmlType == '9'>
                        <el-select v-model="form.${field.javaField}" placeholder="请选择${field.columnComment}" <#if field.isEdit == '0'>:disabled="form.${keyLabel} != null?true:false"</#if>
                                   style="width: 100%;" clearable>
                            <el-option v-for="item in ${field.javaField}List" :key="item.key" :label="item.label" <#if field.isSearch != '1'>:value="item.key"<#else >:value="item.id"</#if> >
                            </el-option>
                        </el-select>
                    </#if>
                    <#if field.htmlType == '11'>
                        <i-tree-select v-model="form.${field.javaField}" :normalizer="normalizer" placeholder="请选择${field.columnComment}" :options="${field.javaField}List"></i-tree-select>
                    </#if>
                    <#if field.htmlType == '12'>
                        <el-checkbox-group v-model="form.${field.javaField}" style="width: 100%;"  <#if field.isEdit == '0'>:disabled="form.${keyLabel} != null?true:false"</#if>>
                            <template v-for="(item, index) in ${field.javaField}List">
                                <el-checkbox <#if field.isSearch != '1'>:label="item.key"<#else >:label="item.id"</#if>>{{item.label}}</el-checkbox>
                            </template>
                        </el-checkbox-group>
                    </#if>
                    <#if field.htmlType == '13'>
                        <el-radio-group v-model="form.${field.javaField}" style="width: 100%;"  <#if field.isEdit == '0'>:disabled="form.${keyLabel} != null?true:false"</#if>>
                            <template v-for="(item, index) in ${field.javaField}List">
                                <el-radio-button <#if field.isSearch != '1'>:label="item.key"<#else >:label="item.id"</#if>>{{item.label}}</el-radio-button>
                            </template>
                        </el-radio-group>
                    </#if>
                    <#if field.htmlType == '2'>
                        <el-input type="textarea" v-model="form.${field.javaField}" placeholder="请输入${field.columnComment}"
                                  <#if field.isEdit == '0'>:disabled="form.${keyLabel} != null?true:false"</#if> maxlength="150" show-word-limit></el-input>
                    </#if>
                    <#if field.htmlType == '7'>
                        <el-upload class="avatar-uploader" action="" :auto-upload="false" :show-file-list="false" :on-change="handle${field.javaField?cap_first}">
                            <img v-if="form.${field.javaField}" :src="form.${field.javaField}" class="avatar">
                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </#if>
                    <#if field.htmlType == '8'>
                        <el-upload class="image-uploader" :multiple="false" :auto-upload="true" list-type="text"
                                   :show-file-list="true" :before-upload="common.isFileSize" :drag="true" action :limit="1"
                                   :http-request="upload${field.javaField?cap_first}File">
                            <i class="el-icon-upload"></i>
                            <div class="el-upload__text">
                                将文件拖到此处，或
                                <em>点击上传</em>
                            </div>
                            <div class="el-upload__tip" slot="tip">一次只能上传一个文件，单文件不超过10MB</div>
                        </el-upload>
                    </#if>
                    <#if field.htmlType == '5'>
                        <editor-bar v-model="form.${field.javaField}"></editor-bar>
                    </#if>
                    </el-form-item>
                </el-col>
                </#if>
                </#list>
            </el-row>
            <#if subTable??>
            <el-alert title="${subTable.functionName}"type="info"></el-alert>
            <el-row :gutter="20">
                <template v-for="(item,index) in form.${subTable.className?uncap_first}s">
                <#list subTable.columns as field>
                <#if field.isKey != '1'>
                <el-col :span="20">
                    <el-form-item label="${field.columnComment}:" prop="${subTable.className?uncap_first}s.${field.javaField}">
                        <el-input v-model="item.${field.javaField}" placeholder="请输入${field.columnComment}" clearable
                                  maxlength="30" show-word-limit></el-input>
                    </el-form-item>
                </el-col>
                </#if>
                </#list>
                </template>
            </el-row>
            </#if>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="save('ruleForm')" :loading="loading">确 定</el-button>
        </div>
    </el-dialog>
</template>

<script>
const baseForm = {
    <#list saveColumns as field>
    ${field.javaField}:null,
    <#if field.htmlType == '3' || field.htmlType == '4' || field.htmlType == '11'>
    ${field.javaField}:"0",
    </#if>
    </#list>
    <#if subTable??>
    ${subTable.className?uncap_first}s:[{
        <#list subTable.columns  as field>
        ${field.javaField}:null,
        <#if field.htmlType == '3' || field.htmlType == '4' || field.htmlType == '11'>
        ${field.javaField}:"0",
        </#if>
        </#list>
    }],
    </#if>
}
const formName = "新增${functionName}"
export default {
    data() {
        return {
            <#list saveColumns as field>
            <#if  field.htmlType == '9' || field.htmlType == '10' || field.htmlType == '11' || field.htmlType == '12' || field.htmlType == '13'>
            ${field.javaField}List:[],
            </#if>
            </#list>
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
                <#list saveColumns as field>
                <#if field.isRequired == '1'>
                ${field.javaField}: [{
                    required: true,
                    <#if field.verifyName ??>
                    validator: this.ruleCommon.${field.verifyName},
                    <#else >
                    message: "<#if field.htmlType == '1' || field.htmlType == '2' || field.htmlType == '3' || field.htmlType == '4' || field.htmlType == '5'>请输入<#else>请选择</#if>${field.columnComment}",
                    </#if>
                    trigger:  <#if field.htmlType == '1' || field.htmlType == '2' || field.htmlType == '3' || field.htmlType == '4' || field.htmlType == '5'>"blur"<#else>"change"</#if>,
                }, ],
                </#if>
                </#list>
                <#if subTable??>
                ${subTable.className?uncap_first}s:{
                    <#list subTable.saveColumns as field>
                    <#if field.isRequired == '1'>
                    ${field.javaField}: [{
                        required: true,
                        <#if field.verifyName ??>
                        validator: this.ruleCommon.${field.verifyName},
                        <#else >
                        message: "<#if field.htmlType == '1' || field.htmlType == '2' || field.htmlType == '3' || field.htmlType == '4' || field.htmlType == '5'>请输入<#else>请选择</#if>${field.columnComment}",
                        </#if>
                        trigger:  <#if field.htmlType == '1' || field.htmlType == '2' || field.htmlType == '3' || field.htmlType == '4' || field.htmlType == '5'>"blur"<#else>"change"</#if>,
                    }, ],
                    </#if>
                    </#list>
                },
                </#if>
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
            <#list columns as field>
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
                        <#list columns as field>
                        <#if field.htmlType == '9' ||  field.htmlType == '10' || field.htmlType == '12' || field.htmlType == '13'>
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
            if (data != null) {
                that.formName = "编辑${functionName}";
                that.$api.${moduleName}Request.${bizName}Detail({
                    id:data
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
            }else{
                that.loading = false;
            }
        },
        //保存
        save(formName) {
            let that = this;
            that.$refs[formName].validate((valid) => {
                if (valid) {
                    that.loading = true;
                    if(that.form.${keyLabel} != null){
                        that.$api.${moduleName}Request.${bizName}Update(that.form)
                            .then(res => {
                                res = res.datas
                                that.loading = false;
                                if (res.code == 0) {
                                    that.$parent.reload();
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
                    }else{
                        that.$api.${moduleName}Request.${bizName}Save(that.form)
                            .then(res => {
                                res = res.data
                                that.loading = false;
                                if (res.code == 0) {
                                    that.$parent.reload();
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
        <#list columns as field>
        <#if field.htmlType == '7'>
        async handle${field.javaField?cap_first}(file){
            let that = this;
            if (this.common.isFileSize(file) && this.common.imageUploadType(file)) {
                var url = await this.common.uploadFile(file, "image");
                if(url != null){
                    that.form.${field.javaField} = url;
                    that.$refs["ruleForm"].validateField('${field.javaField}')
                }
            }
        },
        </#if>
        <#if field.htmlType == '8'>
        // 上传文件
        async upload${field.javaField?cap_first}File(item) {
            let that = this;
            const fileObj = item.file;
            var url = await this.common.uploadFile(fileObj, "file");
            if(url != null){
                that.form.${field.javaField} = url;
                that.$refs["ruleForm"].validateField('${field.javaField}')
            }
        },
        </#if>
        <#if field.htmlType == '11'>
        //去掉children=[]的children属性
        normalizer(node) {
            if (node.children == null) {
                delete node.children;
            }
        },
        </#if>
        </#list>
        closeDialog() {
            this.dialogFormVisible = false
            this.form = {
                ...baseForm,
            };
            this.loading = false;
            this.$refs["ruleForm"].resetFields();
        },
    }
};
</script>

<style scoped>
</style>
