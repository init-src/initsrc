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
        that.$api.systemRequest.permDetail({
            id: data
          })
          .then(res => {
            res = res.data
            that.loading = false;
            if (res.code == 0) {
              that.form = res.data
              that.form.resource = this.common.transformDict(that.form.resource, JSON.parse(that.$store.state.ps.DICT_LIST
                .permResourceType));
              that.form.isCache = this.common.transformDict(that.form.isCache, JSON.parse(that.$store.state.ps.DICT_LIST
                .isTrueType));
              that.form.sort = this.common.transformDoubleByTwo(that.form.sort);
              that.form.linkType = this.common.transformDict(that.form.linkType, JSON.parse(that.$store.state.ps.DICT_LIST
                .permLinkType));
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