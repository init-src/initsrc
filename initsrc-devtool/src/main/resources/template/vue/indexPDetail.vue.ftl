<template>
<div class="dshfn-content">
    <div class="dshfn-content-header dshfn-card">
        <el-row :gutter="24">
            <el-col :span="24" class="col-no-padding">
                <div  style="padding: 4px;">
                    <el-page-header @back="common.goBack" content="${functionName}详情">
                    </el-page-header>
                </div>
            </el-col>
        </el-row>
    </div>
    <el-row :gutter="24" class="dshfn-content-main">
        <el-col :md="24" :lg="24" :xl="24" style="padding: 0px;">
            <el-col :span="24" class="col-padding">
                <div class="dshfn-card">
                    <div class="dshfn-card-body">
                        <h4 class="card-title"> ${functionName}基本信息</h4>
                        <el-row :gutter="24" class="dshfn-detail-info">
                            <#list detailColumns as field>
                            <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="6">
                                <div class="dshfn-label-item">
                                    <label class="dshfn-label-item__label">${field.columnComment}：</label>
                                    <#if field.isSearch != '1'>
                                    <#if field.htmlType != '7'>
                                        <label class="dshfn-label-item__content">{{form.${field.javaField}}}</label>
                                    <#else >
                                        <el-image :preview-src-list="[form.${field.javaField}]" :src="form.${field.javaField}"  alt>
                                            <div slot="error" class="image-slot ">
                                                暂无图片
                                            </div>
                                        </el-image>
                                    </#if>
                                    <#else >
                                        <label class="dshfn-label-item__content">{{form.${field.leftAlias}}}</label>
                                    </#if>
                                </div>
                            </el-col>
                            </#list>
                        </el-row>
                    </div>
                </div>
            </el-col>
            <#if subTable??>
            <el-col :span="24" class="col-padding">
                <div class="dshfn-card">
                    <div class="dshfn-card-body">
                        <h4 class="card-title"> ${subTable.functionName}基本信息</h4>
                        <el-row :gutter="24" class="dshfn-detail-info">
                            <#list subTable.detailColumns as field>
                                <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="6">
                                    <div class="dshfn-label-item">
                                        <label class="dshfn-label-item__label">${field.columnComment}：</label>
                                        <#if field.isSearch != '1'>
                                            <#if field.htmlType != '7'>
                                                <label class="dshfn-label-item__content">{{form.${subTable.className?uncap_first}s.${field.javaField}}}</label>
                                            <#else >
                                                <el-image :preview-src-list="[form.${subTable.className?uncap_first}s.${field.javaField}]" :src="form.${subTable.className?uncap_first}s.${field.javaField}"  alt>
                                                    <div slot="error" class="image-slot ">
                                                        暂无图片
                                                    </div>
                                                </el-image>
                                            </#if>
                                        <#else >
                                            <label class="dshfn-label-item__content">{{form.${subTable.className?uncap_first}s.${field.leftAlias}}}</label>
                                        </#if>
                                    </div>
                                </el-col>
                            </#list>
                        </el-row>
                    </div>
                </div>
            </el-col>
            </#if>
        </el-col>
    </el-row>
</div>
</template>

<script>
export default {
    data() {
        return {
            loading: true,
            drawer: false,
            form: {},
        }
    },
    methods: {
        init() {
            let that = this;
            var data = that.$route.query.id
            that.$api.${moduleName}Request.${bizName}Detail({
                id: data
            })
                .then(res => {
                    res = res.data
                    that.loading = false;
                    if (res.code == 0) {
                        that.form = res.data
                        <#list detailColumns as field>
                        <#if field.htmlType != '9' && field.htmlType != '10' && field.htmlType != '11' && field.htmlType != '12' && field.htmlType != '13'>
                        <#if field.htmlType =='6'>
                        that.form.${field.javaField} = this.common.transformTime(that.form.${field.javaField});
                        <#elseif field.htmlType =='3' || field.htmlType =='4'>
                        that.form.${field.javaField} = this.common.transformDoubleByTwo(that.form.${field.javaField});
                        </#if>
                        <#else >
                        <#if field.isSearch != '1'>
                        <#if field.dictType ??>
                        that.form.${field.javaField} = this.common.transformDict(that.form.${field.javaField}, JSON.parse(that.$store.state.ps.DICT_LIST.${field.dictType}));
                        </#if>
                        </#if>
                        </#if>
                        </#list>
                        <#if subTable??>
                        <#list subTable.detailColumns as field>
                        <#if field.htmlType != '9' && field.htmlType != '10' && field.htmlType != '11' && field.htmlType != '12' && field.htmlType != '13'>
                        <#if field.htmlType =='6'>
                        that.form.${subTable.className?uncap_first}s.${field.javaField} = this.common.transformTime(that.form.${field.javaField});
                        <#elseif field.htmlType =='3' || field.htmlType =='4'>
                        that.form.${subTable.className?uncap_first}s.${field.javaField} = this.common.transformDoubleByTwo(that.form.${field.javaField});
                        </#if>
                        <#else >
                        <#if field.isSearch != '1'>
                        <#if field.dictType ??>
                        that.form.${subTable.className?uncap_first}s.${field.javaField} = this.common.transformDict(that.form.${field.javaField}, JSON.parse(that.$store.state.ps.DICT_LIST.${field.dictType}));
                        </#if>
                        </#if>
                        </#if>
                        </#list>
                        </#if>
                    } else {
                        this.$notify.error({
                            title: '错误提示',
                            message: res.msg
                        });
                        that.drawer = true
                    }
                })
        },
    },
    created() {
        this.init();
    }
}
</script>
