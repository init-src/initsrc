import hljs from 'highlight.js';
import 'highlight.js/styles/atom-one-dark.css' //样式
import Clipboard from 'clipboard'; //代码复制
hljs.registerLanguage("java", require("highlight.js/lib/languages/java"));
hljs.registerLanguage("xml", require("highlight.js/lib/languages/xml"));
hljs.registerLanguage("html", require("highlight.js/lib/languages/xml"));
hljs.registerLanguage("vue", require("highlight.js/lib/languages/xml"));
hljs.registerLanguage("javascript", require("highlight.js/lib/languages/javascript"));
hljs.registerLanguage("sql", require("highlight.js/lib/languages/sql"));
export default {
  data() {
    return {
      loading: false,
      form: {},
      activeName: "0",
    }
  },
  methods: {
    init() {
      let that = this;
      that.loading = false;
      var id = this.$route.query.id
      that.$api.devRequest.tablePreview({
          id: id
        })
        .then(res => {
		  res =res.data
          if (res.code == 0) {
            that.loading = false;
            that.form = res.data;
          } else {
            this.$notify.error({
              title: '错误提示',
              message: res.msg
            });
          }
        })
    },
    /** 高亮显示 */
    showCode(code, key) {
      var language = key.substring(key.indexOf(".") + 1, key.length);
      const result = hljs.highlight(language, code || "", true);
      return result.value || '&nbsp;';
    },
    copy(key) {
      var copybtn = document.getElementsByClassName(key)
      var clipboard = new Clipboard(copybtn);
      this.$nextTick(() => {
          clipboard.on("success", e => {
              this.$message.success({
                message: '复制成功',
                type: 'success'
              });
            // 释放内存
            clipboard.destroy();
          }); clipboard.on("error", e => {
          // 释放内存
          clipboard.destroy();
        });
      })
  },
  //返回上一页
  goBack() {
    this.$router.go(-1)
  },
},
created() {
  this.init();
}
}
