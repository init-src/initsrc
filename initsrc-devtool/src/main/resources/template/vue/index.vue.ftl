<template>
    <div class="dshfn-content">
        <div class="dshfn-content-header dshfn-card">
            <el-col :span="24">
                <div>
                    <h4 class="dshfn-content-title">${functionName}管理</h4>
                    <div class="dshfn-content-right">
                        <div class="opr-btn">
                            <template v-for="(column, index) in headBtn">
                                <el-button size="mini" :type="column.type" :icon="column.icon" @click="column.method()" :plain="column.plain">
                                    {{column.label}}
                                </el-button>
                            </template>
                        </div>
                    </div>
                </div>
            </el-col>
        </div>
        <el-row class="dshfn-content-main ">
            <el-col :md="24" :lg="24" :xl="24">
                <div class="dshfn-card dshfn-table-content">
                    <!-- 查询条件区域 -->
                    <div class="operate-btn">
                        <el-row :gutter="24">
                            <#list queryColumns as field>
                            <#if (field_index <=4)>
                            <el-col :md="24" :lg="3" :xl="3" class="input-padding">
                                <#if field.htmlType == '9' || field.htmlType == '10' || field.htmlType == '12'|| field.htmlType == '13'>
                                <#if field.isSearch != '1'>
                                <#if field.dictType ??>
                                <el-select v-model="params.${field.javaField}" @change="search()" size="small" style="width: 100%;" placeholder="请选择${field.columnComment}" clearable>
                                    <el-option v-for="item in ${field.javaField}List" :key="item.key" :label="item.label" :value="item.key">
                                    </el-option>
                                </el-select>
                                </#if>
                                <#else>
                                <el-input size="small" placeholder="请输入${field.columnComment}" @change="search()" v-model="params.${field.javaField}" clearable>
                                </el-input>
                                </#if>
                                <#elseif field.htmlType=="6">
                                <#if field.queryType == "8">
                                <el-date-picker v-model="params.${field.javaField}" size="small"  @change="search()" value-format="timestamp" type="daterange"
                                                range-separator="-"
                                                start-placeholder="开始日期"
                                                end-placeholder="结束日期"
                                                placeholder="选择${field.columnComment}时间" clearable style="width: 100%;" clearable>
                                </el-date-picker>
                                <#else >
                                <el-date-picker v-model="params.${field.javaField}" size="small"  @change="search()" value-format="timestamp" type="datetime"
                                                placeholder="选择${field.columnComment}时间" clearable style="width: 100%;" clearable>
                                </el-date-picker>
                                </#if>
                                <#else>
                                <el-input size="small" placeholder="请输入${field.columnComment}" @change="search()" v-model="params.${field.javaField}" clearable>
                                </el-input>
                                </#if>
                            </el-col>
                            </#if>
                            </#list>
                            <el-col :md="24" :lg="8" :xl="8" <#if (queryColumns?size>4)>style="margin-top: -5px;"</#if>>
                                <#if (queryColumns?size>4)>
                                <el-button type="text" @click="expand()" icon="el-icon-d-caret">展开</el-button>
                                </#if>
                                <el-button type="danger" icon="el-icon-refresh" size="mini" @click="reload()">重置
                                </el-button>
                            </el-col>
                        </el-row>
                        <#if (queryColumns?size>4)>
                        <el-row :gutter="24" v-if="isExpand">
                            <#list queryColumns as field>
                            <#if (field_index >4)>
                            <el-col :md="24" :lg="3" :xl="3" class="input-padding">
                                <#if field.htmlType == '9' || field.htmlType == '10' || field.htmlType == '12'|| field.htmlType == '13'>
                                <#if field.isSearch != '1'>
                                <#if field.dictType ??>
                                <el-select v-model="params.${field.javaField}" @change="search()" size="small" style="width: 100%;" placeholder="请选择${field.columnComment}" clearable>
                                    <el-option v-for="item in ${field.javaField}List" :key="item.key" :label="item.label" :value="item.key">
                                    </el-option>
                                </el-select>
                                </#if>
                                <#else>
                                <el-input size="small" placeholder="请输入${field.columnComment}" @change="search()" v-model="params.${field.javaField}" clearable>
                                </el-input>
                                </#if>
                                <#elseif field.htmlType=="6">
                                <#if field.queryType == "8">
                                    <el-date-picker v-model="params.${field.javaField}" size="small"  @change="search()" value-format="timestamp" type="daterange"
                                                    range-separator="-"
                                                    start-placeholder="开始日期"
                                                    end-placeholder="结束日期"
                                                    placeholder="选择${field.columnComment}时间" clearable style="width: 100%;" clearable>
                                    </el-date-picker>
                                <#else >
                                    <el-date-picker v-model="params.${field.javaField}" size="small"  @change="search()" value-format="timestamp" type="datetime"
                                                    placeholder="选择${field.columnComment}时间" clearable style="width: 100%;" clearable>
                                    </el-date-picker>
                                </#if>
                                <#else>
                                <el-input size="small" placeholder="请输入${field.columnComment}" @change="search()" v-model="params.${field.javaField}" clearable>
                                </el-input>
                                </#if>
                            </el-col>
                            </#if>
                            </#list>
                        </el-row>
                        </#if>
                    </div>
                    <!--region table 表格-->
                    <i-table ref="table" :optionsObj="options" :params="params" :removeScHeight="removeScHeight"
                             @handleSelectionChange="handleSelectionChange"></i-table>
                    <!--endregion-->
                </div>
            </el-col>
        </el-row>
        <#if vueEditType !="3">
        <index-edit ref="indexEdit"></index-edit>
        </#if>
        <#if vueDetailType !="3">
        <index-detail ref="indexDetail"></index-detail>
        </#if>
        <#if isExcel=="1">
        <index-import ref="indexImport"></index-import>
        <index-export ref="indexExport"></index-export>
        </#if>
    </div>
</template>

<script>
<#if vueDetailType != "3" >
import indexDetail from '@/${viewPath}/page/indexDetail.vue'
</#if>
<#if vueEditType != "3" >
import indexEdit from '@/${viewPath}/page/indexEdit.vue'
</#if>
<#if isExcel == "1" >
import indexImport from '@/${viewPath}/page/indexImport.vue'
import indexExport from '@/${viewPath}/page/indexExport.vue'
</#if>
export default {
    components: {
        <#if vueDetailType != "3" >
        indexDetail,
        </#if>
        <#if vueEditType != "3" >
        indexEdit,
        </#if>
        <#if isExcel == "1" >
        indexImport,
        indexExport,
        </#if>
    },
    data() {
        return {
            params: {
                <#list queryColumns as field >
                <#if field.queryType == "8">
                begin${field.javaField?cap_first}:null,
                end${field.javaField?cap_first}:null,
                <#else >
                ${field.javaField}: null,
                </#if>
                </#list>
            },
            //头部按钮权限
            headBtn:[],
            <#if (queryColumns ? size > 4) >
            isExpand: false, //展开更多搜索条件
            </#if>
            //查询列表
            <#list queryColumns as field>
            <#if field.htmlType == '9' || field.htmlType == '10' || field.htmlType == '12' || field.htmlType == '13'>
            <#if field.isSearch != '1'>
            <#if field.dictType ??>
            ${field.javaField}List:[],
            </#if>
            </#if>
            </#if>
            </#list>
            <#if isCategory != '2'>
            //批量删除列
            multipleId: [],
            </#if>
            removeScHeight: 330,
            table: null, //表格
            //表格基本参数
            options: {
                api: this.$api.${moduleName}Request.${bizName}Page, //请求API
                page: true,
                mutiSelect: true,
                isFixed: true,
                operates: {
                    width: 200, //操作栏宽
                    fixed: 'right',
                    hideCount: 3, //按钮更多
                    list: []
                },
                columns: [
                    <#list listColumns as field >
                    {
                        <#if field.isSearch == '1'>
                        prop: '${field.leftAlias}',
                        label: '${field.columnComment}',
                        <#else>
                        prop: '${field.javaField}',
                        label: '${field.columnComment}',
                        </#if>
                        <#if field.htmlType == "3" || field.htmlType == "4">
                        formatter: (row, column, cellValue) => {
                            return this.common.transformDoubleByTwoAndUp(cellValue)
                        },
                        <#elseif field.htmlType == "6" >
                        formatter: (row, column, cellValue) => {
                            return this.common.transformTime(cellValue)
                        },
                        <#elseif field.htmlType == "7" >
                        formatter: (row, column, cellValue) => {
                            return this.common.isImageShow(cellValue)
                        },
                        <#elseif field.htmlType == "2" || field.htmlType == "5" || field.htmlType == "8" >
                        formatter: (row, column, cellValue) => {
                            return this.common.isLongToShort(cellValue)
                        }
                        <#else>
                        <#if field.isSearch != '1'>
                        formatter: (row, column, cellValue) => {
                            <#if field.dictType ??>
                            return this.common.transformDict(cellValue, JSON.parse(this.$store.state.ps.DICT_LIST.${field.dictType}))
                            <#else >
                            return this.common.isNull(cellValue)
                            </#if>
                        },
                        <#else>
                        formatter: (row, column, cellValue) => {
                            return this.common.isNull(cellValue)
                        },
                        </#if>
                        </#if>
                    },
                    </#list>
                ],
            },
        }
    },
    methods: {
        //初始化
        init() {
            let that = this;
            that.options.loading = true;
            <#list queryColumns as field>
            <#if field.htmlType == '9' || field.htmlType == '10' || field.htmlType == '12' || field.htmlType == '13'>
            <#if field.isSearch != '1'>
            <#if field.dictType ??>
            that.${field.javaField}List = JSON.parse(that.$store.state.ps.DICT_LIST.${field.dictType});
            </#if>
            </#if>
            </#if>
            </#list>
            that.table = that.$refs.table;
            that.table.init();
        },
        //查询
        search() {
            let that = this;
            <#list queryColumns as field>
            <#if field.queryType == "8">
            if(that.params.${field.javaField} != null){
                that.params.begin${field.javaField?cap_first} = that.params.${field.javaField}[0];
                that.params.end${field.javaField?cap_first} = that.params.${field.javaField}[1];
            }else{
                that.params.begin${field.javaField?cap_first} = null;
                that.params.end${field.javaField?cap_first} = null;
            }
            </#if>
            </#list>
            that.table.reload();
        },
        //重置查询
        reload() {
            let that = this;
            that.params = {
                <#list queryColumns as field >
                <#if field.queryType == "8">
                begin${field.javaField?cap_first}:null,
                end${field.javaField?cap_first}:null,
                <#else >
                ${field.javaField}: null,
                </#if>
                </#list>
            }
            that.table.reload();
        },
        //新增
        handleAdd(path) {
            <#if vueEditType == "3" >
            this.$router.push({
                path: path
            })
            <#else >
            this.$refs.indexEdit.init();
            </#if>
        },
        <#if isExcel == "1" >
        //导入
        handleImport(index, row, path) {
            this.$refs.indexImport.init(row.${keyLabel});
        },
        //导出
        handleExport(index, row, path) {
            this.$refs.indexExport.init(row.${keyLabel});
        },
        </#if>
        // 详情
        handleDetail(index, row, path) {
            <#if vueDetailType == "3" >
            this.$router.push({
                path: path,
                query: {
                    id: row.${keyLabel}
                }
            })
            <#else >
            this.$refs.indexDetail.init(row.${keyLabel});
            </#if>
        },
        // 编辑
        handleEdit(index, row, path) {
            <#if vueEditType == "3" >
            this.$router.push({
                path: path,
                query: {
                    id: row.${keyLabel}
                }
            })
            <#else >
            this.$refs.indexEdit.init(row.${keyLabel});
            </#if>
        },
        <#if isCategory != '2'>
        //批量删除
        handleDels() {
            let that = this;
            that.$confirm('此操作将删除该数据, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: 'true'
            }).then(() => {
                if (that.multipleId.length > 0) {
                    that.$api.${moduleName}Request.${bizName}Deletes({
                        ids: that.multipleId
                    })
                        .then(res => {
                            res = res.data
                            if (res.code == 0) {
                                that.search();
                                this.$notify.success({
                                    title: '成功提示',
                                    message: res.msg
                                });
                            } else {
                                this.$notify.error({
                                    title: '错误提示',
                                    message: res.msg
                                });
                            }
                        })
                } else {
                    that.$message.warning("请选择要删除的数据");
                }
            }).catch(() => {
                that.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        },
        </#if>
        // 删除
        handleDel(index, row) {
            let that = this;
            that.$confirm('此操作将删除该数据, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: 'true'
            }).then(() => {
                that.$api.${moduleName}Request.${bizName}Delete({
                    id: row.${keyLabel}
                })
                    .then(res => {
                        res = res.data
                        if (res.code == 0) {
                            that.search();
                            this.$notify.success({
                                title: '成功提示',
                                message: res.msg
                            });
                        } else {
                            this.$notify.error({
                                title: '错误提示',
                                message: res.msg
                            });
                        }
                    })
            }).catch(() => {
                that.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        },
        <#if (queryColumns ? size > 4) >
        //展开更多条件查询
        expand() {
            let that = this;
            if (that.isExpand) {
                that.isExpand = false;
                that.removeScHeight = 330;
            } else {
                that.isExpand = true;
                that.removeScHeight = 370;
            }
        },
        </#if>
        // 选中行
        handleSelectionChange(val) {
            let that = this;
            let str = "";
            let strname = "";
            val.forEach(function(item) {
                str += item.${keyLabel} + ",";
            })
            if (str.length > 0) {
                that.multipleId = str.substr(0, str.length - 1);
            } else {
                that.multipleId = []
            }
        },
    },
    mounted() {
        let that = this
        that.init();
        //权限初始化
        var oper = this.powerCommon.powerSet(that.$store.state.ps.PERM_BTN, that);
        that.options.operates.list = oper.tableOper
        that.headBtn = oper.headerOper
    }
}
</script>

<style>
</style>
