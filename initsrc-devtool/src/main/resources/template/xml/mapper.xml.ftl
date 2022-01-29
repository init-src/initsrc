<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.${moduleName}.dao.${className}Mapper">

    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${packageName}.${moduleName}.entity.${bizName}.${className}">
        <#list columns as field>
        <#if field.isKey == "1"><#--生成主键排在第一位-->
        <id column="${field.columnName}" property="${field.javaField}" />
        </#if>
        </#list>
        <#list columns as field>
        <#if field.isKey != "1"><#--生成普通字段 -->
        <result column="${field.columnName}" property="${field.javaField}" />
        </#if>
        </#list>
    </resultMap>

    <!-- 列表查询映射结果 -->
    <resultMap id="ListResultMap" type="${packageName}.${moduleName}.entity.${bizName}.vo.${className}ListVo">
        <#list columns as field>
        <#if field.isKey == "1"><#--生成主键排在第一位-->
        <id column="${field.columnName}" property="${field.javaField}" />
        </#if>
        </#list>
        <#list listColumns as field>
        <#if field.isKey != "1"><#--生成普通字段 -->
        <result column="${field.columnName}" property="${field.javaField}" />
        </#if>
        </#list>
        <#list listColumns as field>
        <#if field.isSearch == '1'>
        <result column="${field.leftAlias}" property="${field.leftAlias}" />
        </#if>
        </#list>
    </resultMap>

    <!-- 详情查询映射结果 -->
    <resultMap id="DetailResultMap" type="${packageName}.${moduleName}.entity.${bizName}.vo.${className}DetailVo">
        <#list columns as field>
        <#if field.isKey == "1"><#--生成主键排在第一位-->
        <id column="${field.columnName}" property="${field.javaField}" />
        </#if>
        </#list>
        <#list detailColumns as field>
        <#if field.isKey != "1"><#--生成普通字段 -->
        <result column="${field.columnName}" property="${field.javaField}" />
        </#if>
        </#list>
        <#list detailColumns as field>
        <#if field.isSearch == '1'>
        <result column="${field.leftAlias}" property="${field.leftAlias}" />
        </#if>
        </#list>
        <#if subTable??>
        <collection property="${subTable.className?uncap_first}s" javaType="java.util.List" resultMap="${subTable.className}Result"/>
        </#if>
    </resultMap>


    <#if subTable??>
     <!-- 子表详情查询映射结果 -->
    <resultMap id="${subTable.className}Result" type="${subTable.packageName}.${subTable.moduleName}.entity.${subTable.bizName}.${subTable.className}">
        <#list subTable.columns as field>
        <#if field.isKey == "1"><#--生成主键排在第一位-->
        <id column="${field.columnName}" property="${field.javaField}" />
        </#if>
        </#list>
        <#list subTable.columns as field>
        <#if field.isKey != "1" || field.isVersion != "1" || field.isFillCreate != "1" || field.isFillUpdate != "1" || field.isTableLogic != "1"><#--生成普通字段 -->
        <result column="${field.columnName}" property="${field.javaField}" />
        </#if>
        </#list>
    </resultMap>
    </#if>

    <!-- 列表展示字段 -->
    <sql id="List_Column_List">
        <trim  suffixOverrides=",">
        <#list listColumns as field>a.${field.columnName},</#list>
        <#list listColumns as field>
        <#if field.isSearch == '1'>
        (select ${field.leftLabel} from ${field.leftTable} where ${field.leftKey}  = a.${field.columnName} limit 1) as ${field.leftAlias},
        </#if>
        </#list>
        </trim>
    </sql>

    <!-- 详情展示字段 -->
    <sql id="Detail_Column_List">
        <trim  suffixOverrides=",">
        <#list detailColumns as field>a.${field.columnName},</#list><#if subTable??><#list subTable.detailColumns as field>b.${field.columnName},</#list></#if>
        <#list detailColumns as field>
        <#if field.isSearch == '1'>
        (select ${field.leftLabel} from ${field.leftTable} where ${field.leftKey}  = a.${field.columnName} limit 1) as ${field.leftAlias},
        </#if>
        </#list>
        </trim>
    </sql>

    <!-- 查询${functionName}列表-->
    <select id="findList" resultMap="ListResultMap">
        select
        <include refid="List_Column_List"/>
        from ${tableName} a
        <where>
        <#list columns as field>
        <#if field.isTableLogic == "1">
               and a.${field.columnName} = 1
        </#if>
        <#if field.isQuery == "1">
        <#if field.queryType == "1">
            <if test="${field.javaField} != null  and ${field.javaField} != ''"> and a.${field.columnName} = ${'#{'+field.javaField+'}'}</if>
        </#if>
        <#if field.queryType == "2">
            <if test="${field.javaField} != null  and ${field.javaField} != ''"> and  a.${field.columnName} != ${'#{'+field.javaField+'}'}</if>
        </#if>
        <#if field.queryType == "3">
            <if test="${field.javaField} != null  and ${field.javaField} != ''"> and a.${field.columnName} &gt; ${'#{'+field.javaField+'}'}</if>
        </#if>
        <#if field.queryType == "4">
            <if test="${field.javaField} != null  and ${field.javaField} != ''"> and a.${field.columnName}  &gt;= ${'#{'+field.javaField+'}'}</if>
        </#if>
        <#if field.queryType == "5">
            <if test="${field.javaField} != null  and ${field.javaField} != ''"> and a.${field.columnName} &lt; ${'#{'+field.javaField+'}'}</if>
        </#if>
        <#if field.queryType == "6">
              <if test="${field.javaField} != null  and ${field.javaField} != ''"> and a.${field.columnName} &lt;= ${'#{'+field.javaField+'}'}</if>
        </#if>
        <#if field.queryType == "7">
            <if test="${field.javaField} != null  and ${field.javaField} != ''"> and a.${field.columnName} like concat('%', ${'#{'+field.javaField+'}'}, '%')</if>
        </#if>
        <#if field.queryType == "8">
            <if test="${"begin"+field.javaField?cap_first!} != null  and  ${"end"+field.javaField?cap_first!} != null  "> and  a.${field.columnName} between ${'#{'+"begin"+field.javaField?cap_first!+'}'}}  and ${'#{'+"end"+field.javaField?cap_first!+'}'}} </if>
        </#if>
        </#if>
        </#list>
        </where>
    </select>

    <!-- 查询${functionName}详情 -->
    <select id="selectDetailById" resultMap="DetailResultMap">
        select
        <include refid="Detail_Column_List"/>
        from ${tableName} a
        <#if subTable??>
            left join ${subTable.tableName} b on a. <#list columns as field><#if field.isKey == '1'>${field.columnName}</#if></#list> = <#list subTable.detailColumns as field><#if field.columnId == subKey>b.${field.columnName}</#if></#list>
        </#if>
        <trim prefix="where" prefixOverrides="and">
        <#list columns as field>
        <#if field.isTableLogic == "1">
            and a.${field.columnName} = 1
        </#if>
        <#if field.isKey == "1">
            and  a.${field.columnName} = ${'#{'+field.javaField+'}'}
        </#if>
        </#list>
        </trim>
    </select>

    <#list columns as field>
    <#if field.isOnly !="0">
    <!-- ${field.columnName}字段验证唯一 -->
    <select id="checker${field.javaField?cap_first}Only" resultType="java.lang.Integer">
        select count(1) from ${tableName}
        <trim prefix="where" prefixOverrides="and">
    <#list columns as field><#if field.isTableLogic == "1">
          and ${field.columnName} = 1</#if></#list> and ${field.columnName} = ${'#{'+field.javaField+'}'}
        </trim>
    </select>

    </#if>
    </#list>
    <#if isCategory == "2">
    <!--根据父类ID获取子类数量 -->
    <select id="selectByParendId" resultType="java.lang.Integer">
        select count(1) from ${tableName}
        <trim prefix="where" prefixOverrides="and">
        <#list columns as field><#if field.isTableLogic == "1">
            and ${field.columnName} = 1
        </#if></#list>
            and ${parentKey} = ${'#{'+'id'+'}'}
        </trim>
    </select>

    </#if>
    <#list columns as field>
    <#if field.isSearch == '1'>
    <!--  ${field.columnName}字段关联查询列表-->
    <select id="${field.javaField}SelectData" resultType="com.initsrc.common.base.NodeEntity">
        select a.${field.leftKey} as id,<#if field.leftParent??>a.${field.leftParent} as parentId,</#if>a.${field.leftLabel} as label from ${field.leftTable} a where a.dr = 1
    </select>
    </#if>
    </#list>
</mapper>
