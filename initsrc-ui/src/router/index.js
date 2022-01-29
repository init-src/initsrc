import Vue from 'vue'
import Router from 'vue-router'
import routerSrc from '@/router/router.js' // 导入routerSrc
import store from '../store'
import api from '@/api' // 导入api接口
//引入nprogress
import NProgress from 'nprogress' // 进度条
import 'nprogress/nprogress.css' //这个样式必须引入
Vue.use(Router)

export const constantRouterMap = [{
  path: '/login',
  name: 'login',
  component: routerSrc.login,
  hidden: true
}, {
  path: '/403',
  component: routerSrc.error403,
  hidden: true,
  children: []
}]

const createRouter = () => new Router({
  routes: constantRouterMap
})

const router = createRouter()
// 解决ElementUI导航栏中的vue-router在3.0版本以上重复点菜单报错问题
const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

router.$addRoutes = (params) => {
  const newRouter = createRouter();
  router.matcher = newRouter.matcher // the relevant part
  router.addRoutes(params)
}

router.beforeEach((to, from, next) => {
  NProgress.start()
  store.commit("_SET_BY_STORAGE");
  if (store.getters._GET_USER_TOKEN == null) {
    if (to.path == '/login') { //如果是登录页面路径，就直接next()
      next();
    } else { //不然就跳转到登录；
      next('/login');
      NProgress.done()
    }
    return;
  }
  if (to.path === '/login') {
    next({
      path: '/'
    })
    NProgress.done()
    return;
  }
  if (store.getters._GET_ROUTER_MENU == null || store.getters._GET_ROUTER_MENU.length <= 0) {
    api.baseRequest.refreshAuth({})
      .then(res => {
        res = res.data;
        if (res.code == 0) {
          store.commit("_SET_REF_INFO", res.data)
          initMenu(router, store.state.ps.MENU_LIST);
          next({ ...to,
            replace: true
          })
        } else {
           store.commit("_SET_LOGIN_OUT")
            next('/login');
          NProgress.done()
        }
      })
  } else {
    next()
  }
})
router.afterEach((to, from, next) => {
  store.commit("_SET_ACTION", to)
  NProgress.done()
})

export default router

let aRouter = []
//初始化菜单栏格式
export const initMenu = (router, menu) => {
  let menus = formatRoutes(menu);
  let fullMenus = formatFullRoutes(menu);
  let unfound = {
    path: '*',
    component: routerSrc.error404,
    hidden: true,
    children: []
  }
  let menuRoter = [{
    path: "/",
    component: routerSrc.layout,
    children: []
  }]
  menuRoter[0].children = menus
  menuRoter = menuRoter.concat(fullMenus);
  menuRoter.push(unfound)
  router.$addRoutes(menuRoter)
  store.commit("_SET_ROUTER_MENU", store.state.ps.MENU_LIST.concat(router.options.routes))
}

//把菜单栏转换成路由格式
export const formatRoutes = (aMenu) => {
  if (aMenu != null) {
    aMenu.forEach(oMenu => {
      const {
        id,
        path,
        parentId,
        component,
        name,
        icon,
        resouce,
        linkType,
        isCache,
        children
      } = oMenu
      let filePath;
      let oRouter;
      var cache = false;
      if (isCache == 0) {
        cache = false;
      } else {
        cache = true;
      }
      if (oMenu.component != null) {
        oRouter = {
          id: id,
          path: path,
          component(resolve) {
            require([`@/${component}`], resolve)
          },
          icon: icon,
          meta: {
            // 头部标识显示
            title: name,
            isCache: cache, // 需要登录的页面
          },
        }
        if (oMenu.linkType != "2"  && oMenu.path != "-" && oMenu.component != "-" && oMenu.linkType != "1") {
          aRouter.push(oRouter)
        }
      }
      if (children != null) {
        formatRoutes(children)
      }
    })
  }
  return aRouter
}

let aFullRouter = [];
//把菜单栏转换成路由格式 满屏
export const formatFullRoutes = (aMenu) => {
  if (aMenu != null) {
    aMenu.forEach(oMenu => {
      const {
        id,
        path,
        parentId,
        component,
        name,
        icon,
        resouce,
        linkType,
        isCache,
        children
      } = oMenu
      let filePath;
      let oRouter;
      var cache = false;
      if (isCache == 0) {
        cache = false;
      } else {
        cache = true;
      }
      if (oMenu.component != null) {
        oRouter = {
          id: id,
          path: path,
          component(resolve) {
            require([`@/${component}`], resolve)
          },
          icon: icon,
          meta: {
            // 头部标识显示
            title: name,
            isCache: cache, // 需要登录的页面
          },
        }

        if (oMenu.linkType == 1 ) {
          aFullRouter.push(oRouter)
        }
      }
      if (children != null) {
        formatFullRoutes(children)
      }
    })
  }
  return aFullRouter
}
