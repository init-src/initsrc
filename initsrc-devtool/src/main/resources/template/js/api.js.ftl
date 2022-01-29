/**
* ${functionName}接口
*/
// 查询${functionName}列表
${bizName}Page(params) {
return axios.get(base.bd+`webApi/${moduleName}/${bizName}/page`,{params});
},
<#if isExcel == '1'>
// ${functionName}导出
${bizName}Export(params) {
return axios.get(base.bd+`webApi/${moduleName}/${bizName}/export`,{
  responseType: "blob",
  params: params
});
},
// ${functionName}下载模板
${bizName}DownloadExcel(params) {
return axios.get(base.bd+`webApi/${moduleName}/${bizName}/downloadExcel`,{
    responseType: "blob",
    params: params
});
},
// ${functionName}导入
${bizName}Import(params) {
    let formData = new FormData();
        formData.append("file", params);
            return axios.post(
            base.bd+`webApi/${moduleName}/${bizName}/import`,
            formData,
            {
            responseType: "blob",
            headers: {
            "Content-Type": "multipart/form-data"
            }
        }
    );
},
</#if>
<#if vueTableType =='2'>
//查询${functionName}左侧列表
${bizName}LeftData(params) {
return axios.get(base.bd+`webApi/${moduleName}/${bizName}/leftPage`,{params});
},
</#if>
<#if isSelect == '1'>
//查询${functionName}下拉框列表
${bizName}SelectData(params) {
return axios.get(base.bd+`webApi/${moduleName}/${bizName}/selectPage`,{params});
},
</#if>
//查询${functionName}详情
${bizName}Detail(params) {
return axios.get(base.bd+`webApi/${moduleName}/${bizName}/detail`,{params});
},
// 保存${functionName}数据
${bizName}Save(params) {
return axios.post(base.bd+`webApi/${moduleName}/${bizName}/save`, params,config);
},
// 更新${functionName}数据
${bizName}Update(params) {
return axios.post(base.bd+`webApi/${moduleName}/${bizName}/update`, params,config);
},
// 删除${functionName}数据
${bizName}Delete(params) {
return axios.post(base.bd+`webApi/${moduleName}/${bizName}/delete`, qs.stringify(params));
},
<#if isCategory != "2">
// 批量删除${functionName}数据
${bizName}Deletes(params) {
return axios.post(base.bd+`webApi/${moduleName}/${bizName}/deletes`, qs.stringify(params));
},
</#if>
