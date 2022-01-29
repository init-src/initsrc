export default {
  data() {
    return {
      loading: false,
      form: {
        img: "http://47.114.102.111/group1/M00/00/0F/rBDbQl8mk_CAcEvWAAWv7aL9EB8684.jpg"
      }
    }
  },
  methods: {
    init() {
      let that = this;
      this.loading = false;
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
