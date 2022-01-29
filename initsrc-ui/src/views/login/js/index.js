import base from "@/api/base.js";
export default {
  data() {
    return {
      form: {
        userName: "initsrc",
        password: "a123456"
      },
      loading: false,
      imgCode: null,
      errorMsg: "",
      curcolor: this.$store.state.THEME_STYLE,
      rules: {
        password: [{
          required: true,
          message: '请输入密码',
          trigger: 'blur'
        }],
        userName: [{
          required: true,
          message: '请输入账号',
          trigger: 'blur'
        }],
        code: [{
          required: true,
          message: '请输入验证码',
          trigger: 'blur'
        }]
      },
    }
  },
  methods: {
    getVerifyCode() {
      let that = this;
      let timestamp = Date.parse(new Date());
      that.imgCode = base.bd + "common/captcha/getVerifyCode?" + timestamp;
    },
    keyupEnter() {
      document.onkeydown = e => {
        var keycode = document.all ? event.keyCode : e.which;
        if (keycode == 13) {
          this.onSubmit('ruleForm'); // 登录方法名
          return false;
        }
      };
    },
    // 代码提交
    onSubmit(formName) {
      let that = this;
      that.$refs[formName].validate((valid) => {
        if (valid) {
          that.loading = true;
          this.$api.baseRequest
            .login(this.form)
            .then(res => {
			  res =res.data
              if (res.code == 0) {
                this.loading = false;
                this.$store.commit("_SET_LOGIN_STORAGE", { ...res.data
                });
                location.reload();
              } else {
                this.errorMsg = res.msg;
                this.getVerifyCode();
                this.loading = false;
                this.form.code = null
              }
            })
        } else {
          return false;
        }
      });
    },
  },
  mounted() {
    this.getVerifyCode();
  },
  created() {
    this.keyupEnter();
  },
  beforeDestroy() {
    document.onkeydown = function(e) {
      var key = window.event.keyCode;

      if (key === 13) {}
    };
  },
}
