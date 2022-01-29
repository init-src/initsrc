/**
 * 作者：initsrc
 * 邮箱：892500677@qq.com
 * 描述：页面路径注册
 */
/**
 * 布局页面
 */
const layout = resolve => require(['@/layout/layout/index'], resolve)

/**
 *  业务页面
 */
//登录页
const login = resolve => require(['@/views/login/page/index'], resolve)
//403页面
const error403 = resolve => require(['@/views/error/403'], resolve)
//404页面
const error404 = resolve => require(['@/views/error/404'], resolve)
export default {
  login,
  layout,
  error403,
  error404
}
