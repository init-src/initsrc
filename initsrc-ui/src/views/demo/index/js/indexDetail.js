 export default {
    data() {
      return {
        loading: true,
        drawer: false,
        form: {
          img: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
        },
        direction: 'rtl',
      }
    },
    methods: {
      init(data) {
       let that = this;
       that.drawer = true
       that.loading = false;
      },
      // 关闭右侧公司详情
      handleClose(done) {
        this.drawer = false
      },
    }
  }