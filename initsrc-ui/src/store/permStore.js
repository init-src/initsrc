import routerSrc from '@/router/router.js' // 导入routerSrc
import router from '../router'
const permStore = {
  state: {
    USER_INFO: null, //用户信息
    USER_TOKEN: null, //token
    USER_TICKET: null, //门票
    ROUTER_MENU: [], //当前路由菜单栏
    ACTIVE_MENU: "/", //路由路径
    ACTIVE_TABS: "/", //路由路径
    PERM_BTN: [], //当前页面权限
    MENU_LIST: [], //登录返回权限
    DICT_LIST: [], //字典
    TABS_LIST: [{
      title: "首页",
      name: "/"
    }], //便捷导航
  },
  mutations: {
    // 刷新获取用户信息以及权限
    _SET_REF_INFO(state, value) {
      state.MENU_LIST = value.permVoList
      state.USER_INFO = value.authInfoVo
      state.DICT_LIST = value.dictList
    },
    //获取权限和路由
    _SET_ACTION(state, value) {
      if (value.path != "/") {
        var data = value.path.split("/");
        state.ACTIVE_MENU = "/" + data[1] + "/" + data[2];
      } else {
        state.ACTIVE_MENU = "/"
      }
      var isTrue = false;
      state.ACTIVE_TABS = value.fullPath
      state.TABS_LIST.forEach(function(item) {
        if (item.name == value.fullPath) {
          isTrue = true;
        }
      })
      if (!isTrue) {
        state.TABS_LIST.push({
          title: value.meta.title,
          name: value.fullPath
        })
      }
      let forEc = function(data, to) {
        data.forEach(function(c) {
          if (c.path == to.path) {
            if (c.resource == 0) {
              state.PERM_BTN = c.children;
            }
          }
          if (c.children != null) {
            if (c.children.length > 0) {
              forEc(c.children, to);
            }
          }
        })
      }
      forEc(state.ROUTER_MENU, value)
    },
    //设置编辑导航
    _SET_TABS_LIST(state, value) {
      let tabs = state.TABS_LIST;
      let activeName = state.ACTIVE_TABS;
      if (activeName === value) {
        tabs.forEach((tab, index) => {
          if (tab.name === value) {
            let nextTab = tabs[index + 1] || tabs[index - 1];
            if (nextTab) {
              activeName = nextTab.name;
            }
          }
        });
      }
      state.ACTIVE_TABS = activeName;
      state.TABS_LIST = tabs.filter(tab => tab.name !== value);
      router.push(activeName);
    },
    //设置编辑导航关闭方式
    _SET_TABS_M(state, command) {
      if (command == "other") {
        let tabs = state.TABS_LIST;
        state.TABS_LIST = tabs.filter(tab => tab.name === state.ACTIVE_TABS || tab.name === "/");
      } else if (command == "left") {
        var indexTag = 0;
        let tabs = state.TABS_LIST;
        tabs.forEach((tab, index) => {
          if (tab.name === state.ACTIVE_TABS) {
            indexTag = index
          }
        });
        if (indexTag > 1) {
          state.TABS_LIST = tabs.filter(function(currentValue, index, arr) {
            return index >= indexTag || currentValue.name === "/"
          });
        }
      } else if (command == "right") {
        var indexTag = 0;
        let tabs = state.TABS_LIST;
        tabs.forEach((tab, index) => {
          if (tab.name === state.ACTIVE_TABS) {
            indexTag = index
          }
        });
        state.TABS_LIST = tabs.filter(function(currentValue, index, arr) {
          return index <= indexTag
        });
      } else if (command == "all") {
        state.TABS_LIST = [{
          title: "首页",
          name: "/"
        }]
        router.push("/");
      }
    },
    //获取本地localStorage值
    _SET_BY_STORAGE(state, value) {
      state.USER_TOKEN = sessionStorage.getItem("_storeToken");
      if (localStorage.getItem("_theam") != null) {
        this.commit('theamSetting', JSON.parse(localStorage.getItem("_theam")))
      }
    },
    //设置登录存储
    _SET_LOGIN_STORAGE(state, value) {
      sessionStorage.setItem("_storeToken", value.token);
      sessionStorage.setItem("_storeTicket", value.ticket);
      state.USER_TICKET = value.ticket
    },
    //设置退出清空本地缓存
    _SET_LOGIN_OUT(state) {
      state.USER_TOKEN = null;
      sessionStorage.clear();
    },
    //设置路由菜单
    _SET_ROUTER_MENU(state, value) {
      state.ROUTER_MENU = value
    },
  },
  actions: {

  },
  getters: {
    //获取路由菜单
    _GET_ROUTER_MENU(state) {
      return state.ROUTER_MENU
    },
    //获取用户token
    _GET_USER_TOKEN(state) {
      return state.USER_TOKEN
    },
  },
}
export default permStore;
