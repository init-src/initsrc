import { WS } from "./wesocket.js";

const install = function(Vue) {
  const baseWs = {
    //参数&方法
    WS({ url, openFn, messageFn, errorFn, isInit = false } = {}) {
      return new WS({ url, openFn, messageFn, errorFn, isInit });
    },
  };
  Vue.prototype.baseWs = baseWs;
};

export default {
  install
};
