import base from '../../../../api/base.js'; // 导入接口域名列表
  export default {
    name: "Druid",
    data() {
      return {
        src: base.bd + "doc.html",
        height: document.documentElement.clientHeight - 94.5 + "px;",
        loading: true
      };
    },
    mounted: function() {
      setTimeout(() => {
        this.loading = false;
      }, 230);
      const that = this;
      window.onresize = function temp() {
        that.height = document.documentElement.clientHeight - 94.5 + "px;";
      };
    }
  };