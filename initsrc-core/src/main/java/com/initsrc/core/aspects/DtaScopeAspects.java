package com.initsrc.core.aspects;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.initsrc.common.annotation.DataScope;
import com.initsrc.common.base.BaseEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 数据权限拼接处理类
 *
 * @author INITSRC (启源)
 */
@Aspect
@Component
public class DtaScopeAspects {
    /**
     * 全部查询权限
     */
    public static final String DATA_SCOPE_ALL = "0";

    /**
     * 全部查询权限
     */
    public static final String DATA_SCOPE_CUSTOMIZE = "1";

    /**
     * 本部门及以下
     */
    public static final String DATA_SCOPE_DEPT_AND_CHILD = "2";

    /**
     * 本部门
     */
    public static final String DATA_SCOPE_DEPT = "3";

    /**
     * 数据权限过滤关键字
     */
    public static final String DATA_SCOPE = "dataScope";

    // 配置织入点
    @Pointcut("@annotation(com.initsrc.common.annotation.DataScope)")
    public void dataScopePointCut() {
    }

    @Before("dataScopePointCut()")
    public void doBefore(JoinPoint point) throws Throwable {
        handleDataScope(point);
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private DataScope getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(DataScope.class);
        }
        return null;
    }

    protected void handleDataScope(final JoinPoint joinPoint) {
        // 获得注解
        // 获得注解
        DataScope dataScope = getAnnotationLog(joinPoint);
        if (dataScope == null) {
            return;
        }
        this.dataScopeFilter(joinPoint, dataScope);
    }

    /**
     * 数据范围过滤
     *
     * @param joinPoint 切点
     */
    private void dataScopeFilter(JoinPoint joinPoint, DataScope dataScope) {
        StringBuilder sqlString = new StringBuilder();
        String deptAlias = dataScope.deptAlias();

        BaseEntity baseDto = (BaseEntity) joinPoint.getArgs()[0];

        String isSearch = String.valueOf(baseDto.getScopeType());
        if (DATA_SCOPE_ALL.equals(isSearch)) {
            sqlString = new StringBuilder();
        } else if (DATA_SCOPE_CUSTOMIZE.equals(isSearch)) {
            sqlString.append(StringUtils.format(" AND %s.dept_id in (%s) ", deptAlias,baseDto.getScopeIds()));
        } else if (DATA_SCOPE_DEPT_AND_CHILD.equals(isSearch)) {
            //本公司及其子部门(部门主管)
            sqlString.append(StringUtils.format(" AND %s.dept_id in (SELECT dept_id FROM is_sys_dept WHERE find_in_set( '%s' , search_code )) ", deptAlias, baseDto.getScopeId()));
        } else if (DATA_SCOPE_DEPT.equals(isSearch)) {
            sqlString.append(StringUtils.format(" AND %s.dept_id = '%s' ", deptAlias, baseDto.getScopeId()));
        } else {
            sqlString.append(StringUtils.format(" AND 1=0 "));

        }

        baseDto.getParams().put(DATA_SCOPE, sqlString.toString());

    }
}
