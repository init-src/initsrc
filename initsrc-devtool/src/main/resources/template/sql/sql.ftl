INSERT INTO `is_sys_perm`(`perm_id`, `parent_id`, `name`, `path`, `resource`, `component`, `perm`, `icon`, `color`, `is_cache`, `sort`, `link_type`, `dr`) VALUES (${ids[0]}, ${permId},'${functionName}', '${"/"+moduleName+"/"+bizName}', 0, '${viewPath+"/page/index"}', '${"p:"+moduleName+":"+bizName+":page"}', null, null, 0, 0, 0, 1);

INSERT INTO `is_sys_perm`(`perm_id`, `parent_id`, `name`, `path`, `resource`, `component`, `perm`, `icon`, `color`, `is_cache`, `sort`, `link_type`, `dr`) VALUES (${ids[1]}, ${ids[0]},'新增','${'/'+moduleName+'/'+bizName+'/add'}', 1, '${viewPath+'/page/indexEdit'}', '${'p:'+moduleName+':'+bizName+":add"}', 'initsrc-icon iconshuru', 'primary', 0, 0, 0, 1);
<#if isCategory != "2">

INSERT INTO `is_sys_perm`(`perm_id`, `parent_id`, `name`, `path`, `resource`, `component`, `perm`, `icon`, `color`, `is_cache`, `sort`, `link_type`, `dr`) VALUES (${ids[2]}, ${ids[0]},'批量删除', '-', 1,  '-', '${'p:'+moduleName+':'+bizName+":dels"}', 'initsrc-icon iconshanchu', 'danger', 0, 1, 0, 1);
</#if>

INSERT INTO `is_sys_perm`(`perm_id`, `parent_id`, `name`, `path`, `resource`, `component`, `perm`, `icon`, `color`, `is_cache`, `sort`, `link_type`, `dr`) VALUES (${ids[3]}, ${ids[0]},'详情', '${'/'+moduleName+'/'+bizName+'/detail'}', 2, '${viewPath+'/page/indexDetail'}', '${'p:'+moduleName+':'+bizName+":detail"}', null, 'text', 0, 0, 0, 1);

INSERT INTO `is_sys_perm`(`perm_id`, `parent_id`, `name`, `path`, `resource`, `component`, `perm`, `icon`, `color`, `is_cache`, `sort`, `link_type`, `dr`) VALUES (${ids[4]}, ${ids[0]},'编辑', '${'/'+moduleName+'/'+bizName+'/indexEdit'}', 2, '${viewPath+'/page/indexEdit'}', '${'p:'+moduleName+':'+bizName+":edit"}', null, 'text', 0, 1, 0, 1);

INSERT INTO `is_sys_perm`(`perm_id`, `parent_id`, `name`, `path`, `resource`, `component`, `perm`, `icon`, `color`, `is_cache`, `sort`, `link_type`, `dr`) VALUES (${ids[5]}, ${ids[0]},'删除',  '-', 2,  '-', '${'p:'+moduleName+':'+bizName+":del"}', null, 'text', 0, 2, 0, 1);
<#if isExcel == '1'>

INSERT INTO `is_sys_perm`(`perm_id`, `parent_id`, `name`, `path`, `resource`, `component`, `perm`, `icon`, `color`, `is_cache`, `sort`, `link_type`, `dr`) VALUES (${ids[6]}, ${ids[0]},'导出',  '-', 1,  '${viewPath+'/page/indexExport'}', '${'p:'+moduleName+':'+bizName+":export"}', 'initsrc-icon icondaochu', 'success', 0, 3, 0, 1);

INSERT INTO `is_sys_perm`(`perm_id`, `parent_id`, `name`, `path`, `resource`, `component`, `perm`, `icon`, `color`, `is_cache`, `sort`, `link_type`, `dr`) VALUES (${ids[7]}, ${ids[0]},'导入',  '-', 1,  '${viewPath+'/page/indexImport'}', '${'p:'+moduleName+':'+bizName+":import"}', 'initsrc-icon icondaoru', 'success', 0, 4, 0, 1);
</#if>
