export default {
    data() {
      return {
        loading: true,
        drawer: false,
        form: {},
        direction: 'rtl',
      }
    },
    methods: {
      init(data) {
        let that = this;
        that.drawer = true
        that.$api.systemRequest.logDetail({
            id: data
          })
          .then(res => {
            res = res.data
            that.loading = false;
            if (res.code == 0) {
              that.form = res.data
              that.form.bizType = this.common.transformDict(that.form.bizType, JSON.parse(that.$store.state.ps.DICT_LIST
                .sysLogType));
              that.form.platformType = this.common.transformDict(that.form.platformType, JSON.parse(that.$store.state
                .ps.DICT_LIST.platformType));
              that.form.statusName = this.common.transformDict(that.form.status, JSON.parse(that.$store.state.ps.DICT_LIST
                .sysLogStatus));
            } else {
              this.$notify.error({
                title: '错误提示',
                message: res.msg
              });
              that.drawer = true
            }
          })
      },
      // 关闭右侧公司详情
      handleClose(done) {
        this.drawer = false
      },
    },
  }