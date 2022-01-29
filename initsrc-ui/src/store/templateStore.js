import {
  toggleClass
} from "@/assets/js/utils/toggleClass.js";
//菜单风格
const LIGHT_THEAM_MENU = {
  bc: "#fff",
  tc: "#707d8a",
  atc: "#707d8a"
}
const DARK_THEAM_MENU = {
  bc: "#1e2938",
  tc: "#7f879c",
  atc: "#3b5de7"
}
//表格风格
const LIGHT_THEAM_STYLE = {
  background: '#fff',
  color: '#515a6e',
  'border-top': '1px dashed #e8eaec',
  tableloading: ''
}
const DARK_THEAM_STYLE = {
  background: '',
  color: '#969aa5',
  'border-top': '1px dashed #17212f',
  tableloading: 'rgba(0, 0, 0, 0.8)'
}
const templateStore = {
  state: {
    ISDEVICE: 0, //0：PC 1:APP
    THEME_STYLE: "light", //风格
    THEAM_MENU: { //菜单风格
      ...LIGHT_THEAM_MENU
    }, //菜单栏颜色
    THEAM_TABLE: {
      ...LIGHT_THEAM_STYLE
    }, //表格颜色
    THEME_HEADER_STYLE: 1, //头部颜色
    ISCOLLAPSE: false, //是否展开菜单栏
    ISCOLLAPSESIZE: "200px", //展开菜单栏宽度
    ISCROSS: false, //是否横向展示
    ISFIXED: 1, //1:固定 2：不固定
    ISDEVICEMENU:false,
    ISSCREENFULL:false,
    ISCROSSPROP:false, //横向属性
  },
  mutations: {
    theamMethods(state, curcolor) {
      localStorage.setItem("_storeTheam", curcolor);
      toggleClass(document.body, 'custom-' + curcolor);
      if (curcolor == "dark") {
        state.THEAM_MENU = {
          ...DARK_THEAM_MENU
        }
        state.THEAM_TABLE = { ...DARK_THEAM_STYLE
        }
        document.getElementById('themeLinkId').href = './css/dark-myadmin.css';
      } else {
        state.THEAM_MENU = {
          ...LIGHT_THEAM_MENU
        }
        state.THEAM_TABLE = { ...LIGHT_THEAM_STYLE
        }
        document.getElementById('themeLinkId').href = './css/light-myadmin.css';
      }
    },
    theamSetting(state, value) {
      this.commit("theamMethods", value.THEME_STYLE);
      localStorage.setItem("_theam", JSON.stringify(value));
      state.THEME_STYLE = value.THEME_STYLE;
      state.THEME_HEADER_STYLE = value.THEME_HEADER_STYLE;
      state.ISCOLLAPSE = value.ISCOLLAPSE;
      state.ISCROSS = value.ISCROSS;
      state.ISFIXED = value.ISFIXED;
      state.ISCROSSPROP = value.ISCROSSPROP
    },
    _SET_IS_DEVICE(state, value) {
      if (value > 768) {
        state.ISDEVICE = 0
      } else {
        state.ISDEVICE = 1
      }
      if (value > 992) {
        state.ISCOLLAPSESIZE = "200px";
        state.ISCOLLAPSE = false;
      } else {
        state.ISCOLLAPSESIZE = "64px";
        state.ISCOLLAPSE = true;
      }
    }
  },
  actions: {

  },
  getters: {
    _GET_THEME_STYLE(state) {
      return state.THEME_STYLE
    }
  }
}
export default templateStore;
