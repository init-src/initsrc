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
        that.$api.systemRequest.userDetail({
            id: data
          })
          .then(res => {
            res = res.data
            that.loading = false;
            if (res.code == 0) {
              that.form = res.data
              that.form.sex = this.common.transformDict(that.form.sex, JSON.parse(that.$store.state.ps.DICT_LIST.sexType));
              that.form.birthday = this.common.transformTime(that.form.birthday);
              that.form.status = this.common.transformDict(that.form.status, JSON.parse(that.$store.state.ps.DICT_LIST
                .statusType));
              that.form.loginDate = this.common.transformTime(that.form.loginDate);
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