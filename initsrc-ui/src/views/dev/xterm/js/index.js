// 引入xterm，请注意这里和3.x版本的引入路径不一样
  import {
    Terminal
  } from "xterm";
  import "xterm/css/xterm.css";
  import "xterm/lib/xterm.js";

  export default {
    name: "Shell",
    data() {
      return {
        loading: false,
        shellWs: "",
        term: "", // 保存terminal实例
        rows: 45,
        cols: 100,
        option: {
          operate: 'connect',
          host: '127.0.0.1', //你要连接的终端的ip
          port: '22',
          username: 'root', //你要连接的终端的用户名和密码
          password: ''
        },
        rules: {

        }
      };
    },

    created() {
      // this.wsShell();
    },

    mounted() {
      let _this = this;
      // 获取容器宽高/字号大小，定义行数和列数
      this.rows = document.querySelector(".term_content_tab").offsetHeight / 16 - 5;
      this.cols = document.querySelector(".term_content_tab").offsetWidth / 14;

      let term = new Terminal({
        rendererType: "canvas", //渲染类型
        rows: parseInt(_this.rows), //行数
        cols: parseInt(_this.cols), // 不指定行数，自动回车后光标从下一行开始
        convertEol: true, //启用时，光标将设置为下一行的开头
        //   scrollback: 50, //终端中的回滚量
        disableStdin: false, //是否应禁用输入。
        cursorStyle: "underline", //光标样式
        cursorBlink: true, //光标闪烁
        theme: {
          foreground: "#7e9192", //字体
          background: "#000", //背景色
          cursor: "help", //设置光标
          lineHeight: 16
        }
      });

      // 创建terminal实例
      term.open(this.$refs["terminal"]);

      // 换行并输入起始符“$”
      term.prompt = () => {
        term.write("\r\n$ ");
      };
      term.prompt();

      // // canvas背景全屏
      // var fitAddon = new FitAddon();
      // term.loadAddon(fitAddon);
      // fitAddon.fit();

      window.addEventListener("resize", resizeScreen);

      // 内容全屏显示
      function resizeScreen() {
        // 不传size

        try {
          fitAddon.fit();

          // 窗口大小改变时触发xterm的resize方法，向后端发送行列数，格式由后端决定
          // 这里不使用size默认参数，因为改变窗口大小只会改变size中的列数而不能改变行数，所以这里不使用size.clos,而直接使用获取我们根据窗口大小计算出来的行列数
          term.onResize(() => {
            _this.onSend({
              Op: "resize",
              Cols: term.cols,
              Rows: term.rows
            });
          });
        } catch (e) {
          console.log("e", e.message);
        }
      }

      function runFakeTerminal(_this) {
        if (term._initialized) {
          return;
        }
        // 初始化
        term._initialized = true;

        term.writeln("Welcome to use Initsrc. ");
        term.write("Waiting to connect to the server")

        term.prompt();

        // / **
        //     *添加事件监听器，用于按下键时的事件。事件值包含
        //     *将在data事件以及DOM事件中发送的字符串
        //     *触发了它。
        //     * @返回一个IDisposable停止监听。
        //  * /
        //   / ** 更新：xterm 4.x（新增）
        //  *为数据事件触发时添加事件侦听器。发生这种情况
        //  *用户输入或粘贴到终端时的示例。事件值
        //  *是`string`结果的结果，在典型的设置中，应该通过
        //  *到支持pty。
        //  * @返回一个IDisposable停止监听。
        //  * /
        // 支持输入与粘贴方法
        term.onData(function(key) {
          _this.onSend(JSON.stringify({
            'operate': 'command',
            'command': key
          }));
        });

        _this.term = term;
      }
      runFakeTerminal(_this);

    },

    methods: {

      /**
       * **wsShell 创建页面级别的websocket,加载页面数据
       * ws 接口:/xxx/xxx/xxx
       * 参数:无
       * ws参数:
       * @deployId   任务id
       * @tagString  当前节点
       * 返回:无
       * **/
      wsShell() {
        const _this = this;
        let url = 'webssh'; // websocket连接接口

        this.shellWs = this.baseWs.WS({
          url,
          isInit: true,
          openFn() {
            _this.shellWs.onSend(JSON.stringify(_this.option));
            // _this.term.write("Waiting to connect to the server")
            //   _this.term.resize({ rows: _this.rows, cols: 100 }); //终端窗口重新设置大小 并触发term.on("resize")
          },
          messageFn(e) {
            // console.log("message", e);
            if (e) {
              // let data = JSON.parse(e.data);
              // if (data.Data == "\n" || data.Data == "\r\nexit\r\n") {
              //   _this.$message("连接已关闭");
              // }
              // 打印后端返回数据
              _this.term.write(e.data);
            }
          },
          errorFn(e) {
            //出现错误关闭当前ws,并且提示
            console.log("error", e);
            _this.$message.error({
              message: "ws 请求失败,请刷新重试~",
              duration: 5000
            });
          }
        });
      },

      onSend(data) {
        let that = this;
        if (that.shellWs != "") {
          if (that.shellWs.readyState() != 1) {
            that.term.write("connect error, please try connect again")
          } else {
            that.shellWs.onSend(data);
          }
        } else {
          that.term.write("no connect")
        }
      },
      connect() {
        const _this = this;
        if ("" != _this.shellWs) {
          if (_this.shellWs.readyState() == 1) {
            _this.shellWs.onClose();
          }
        }
        _this.wsShell();
      },

      //删除左右两端的空格
      trim(str) {
        return str.replace(/(^\s*)|(\s*$)/g, "");
      }
    },
  }