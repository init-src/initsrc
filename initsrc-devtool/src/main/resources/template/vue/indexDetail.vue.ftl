<template>
<el-drawer class="dshfn-commonDrawer" title="${functionName}详情"  :visible.sync="drawer" :direction="direction" :wrapperClosable="true"
           :before-close="handleClose" append-to-body :size="$store.state.ts.ISDEVICE==0?'560px':'100%'">
    <div>
        <div class="detail-header">
            ${functionName}基本信息
        </div>
        <div class="detail-content">
            <#list detailColumns as field>
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
            </#list>
        </div>
        <#if subTable??>
        <div class="detail-header">
        ${subTable.functionName}基本信息
        </div>
        <div class="detail-content">
        <#list subTable.detailColumns as field>
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
            <label class="dshfn-label-item__content">{{form.${field.leftAlias}}}</label>
        </#if>
            </div>
        </#list>
        </div>
        </#if>
    </div>
</el-drawer>
</template>

<script>
export default {
    data() {
        return {
            loading: true,
            drawer: false,
            form: {},
            direction: 'rtl',
        }
    },
    methods: {
        init(data) {
            let that = this;
            that.drawer = true
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
        // 关闭右侧公司详情
        handleClose(done) {
            this.drawer = false
        },
    },
}
</script>

<style>
</style>
