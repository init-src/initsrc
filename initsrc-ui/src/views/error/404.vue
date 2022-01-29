<template>
  <div class="error-content">
    <div class="maincontainer">
      <div class="bat">
        <img class="wing leftwing" src="../../assets/img/bat-wing.png">
        <img class="body" src="../../assets/img/bat-body.png" alt="bat">
        <img class="wing rightwing" src="../../assets/img/bat-wing.png">
      </div>
      <div class="bat">
        <img class="wing leftwing" src="../../assets/img/bat-wing.png">
        <img class="body" src="../../assets/img/bat-body.png" alt="bat">
        <img class="wing rightwing" src="../../assets/img/bat-wing.png">
      </div>
      <div class="bat">
        <img class="wing leftwing" src="../../assets/img/bat-wing.png">
        <img class="body" src="../../assets/img/bat-body.png" alt="bat">
        <img class="wing rightwing" src="../../assets/img/bat-wing.png">
      </div>
      <img class="foregroundimg" src="../../assets/img/HauntedHouseForeground.png"
        alt="haunted house">
  
    </div>
    <h1 class="errorcode">ERROR 404</h1>
    <div class="errortext">{{info}}
      <a class="bullshit-return-home" href="#/">
        {{ jumpTime }}s&nbsp;{{ btn }}
      </a></div>

  </div>
</template>

<script>
  export default {
    name: 'Page404',
    data() {
      return {
        jumpTime: 5,
        info: '请检查您输入的网址是否正确，或点击下面的按钮返回首页。',
        btn: '返回',
        timer: 0,
      }
    },
    mounted() {
      this.timeChange()
    },
    beforeDestroy() {
      clearInterval(this.timer)
    },
    methods: {
      timeChange() {
        let that = this;
        this.timer = setInterval(() => {
          if (this.jumpTime) {
            this.jumpTime--
          } else {
            that.common.goBack();
            clearInterval(this.timer)
          }
        }, 1000)
      },
    },
  }
</script>

<style lang="scss" scoped>
  @font-face {
    font-family: 'zapf dingbats';
    src: url('../../assets/font/AlZy_zVUqJz4yMrniH4Rcn35.woff2');
  }

  .error-content {
    position: relative;
    height: 100%;
    width: 100%;
    background-color: #000121;
  }

  .maincontainer {
    position: absolute;
    top: -50px;
    transform: scale(0.8);
    background: url("https://www.blissfullemon.com/wp-content/uploads/2018/09/HauntedHouseBackground.png");
    background-repeat: no-repeat;
    background-position: center;
    background-size: 40rem 35rem;
    width: 50rem;
    height: 45rem;
    margin: 0px auto;
    top: 30px;
    left: 30%;
    display: grid;
  }

  .foregroundimg {
    position: relative;
    width: 100%;
    top: -230px;
    z-index: 5;
  }

  .errorcode {
    position: absolute;
    top: 500px;
    left: 40%;
    font-family: "zapf dingbats";
    color: white;
    text-align: center;
    font-size: 6em;
    letter-spacing: 0.1em;
  }

  .errortext {
    position: absolute;
    top: 700px;
    left: 32%;
    color: #FBD130;
    text-align: center;
    text-transform: uppercase;
    font-size: 1.8em;
  }

  .bat {
    opacity: 0;
    position: relative;
    transform-origin: center;
    z-index: 3;
  }

  .bat:nth-child(1) {
    top: 380px;
    left: 120px;
    transform: scale(0.5);
    animation: 13s 1s flyBat1 infinite linear;
  }

  .bat:nth-child(2) {
    top: 280px;
    left: 80px;
    transform: scale(0.3);
    animation: 8s 4s flyBat2 infinite linear;
  }

  .bat:nth-child(3) {
    top: 200px;
    left: 150px;
    transform: scale(0.4);
    animation: 12s 2s flyBat3 infinite linear;
  }

  .body {
    position: relative;
    width: 50px;
    top: 12px;
  }

  .wing {
    width: 150px;
    position: relative;
    transform-origin: right center;
  }

  .leftwing {
    left: 30px;
    animation: 0.8s flapLeft infinite ease-in-out;
  }

  .rightwing {
    left: -180px;
    transform: scaleX(-1);
    animation: 0.8s flapRight infinite ease-in-out;
  }

  @keyframes flapLeft {
    0% {
      transform: rotateZ(0);
    }

    50% {
      transform: rotateZ(10deg) rotateY(40deg);
    }

    100% {
      transform: rotateZ(0);
    }
  }

  @keyframes flapRight {
    0% {
      transform: scaleX(-1) rotateZ(0);
    }

    50% {
      transform: scaleX(-1) rotateZ(10deg) rotateY(40deg);
    }

    100% {
      transform: scaleX(-1) rotateZ(0);
    }
  }

  @keyframes flyBat1 {
    0% {
      opacity: 1;
      transform: scale(0.5)
    }

    25% {
      opacity: 1;
      transform: scale(0.5) translate(-400px, -330px)
    }

    50% {
      opacity: 1;
      transform: scale(0.5) translate(400px, -800px)
    }

    75% {
      opacity: 1;
      transform: scale(0.5) translate(600px, 100px)
    }

    100% {
      opacity: 1;
      transform: scale(0.5) translate(100px, 300px)
    }
  }

  @keyframes flyBat2 {
    0% {
      opacity: 1;
      transform: scale(0.3)
    }

    25% {
      opacity: 1;
      transform: scale(0.3) translate(200px, -330px)
    }

    50% {
      opacity: 1;
      transform: scale(0.3) translate(-300px, -800px)
    }

    75% {
      opacity: 1;
      transform: scale(0.3) translate(-400px, 100px)
    }

    100% {
      opacity: 1;
      transform: scale(0.3) translate(100px, 300px)
    }
  }

  @keyframes flyBat3 {
    0% {
      opacity: 1;
      transform: scale(0.4)
    }

    25% {
      opacity: 1;
      transform: scale(0.4) translate(-350px, -330px)
    }

    50% {
      opacity: 1;
      transform: scale(0.4) translate(400px, -800px)
    }

    75% {
      opacity: 1;
      transform: scale(0.4) translate(-600px, 100px)
    }

    100% {
      opacity: 1;
      transform: scale(0.4) translate(100px, 300px)
    }
  }

  .bullshit-return-home {
    margin-top: 10px;
    margin-left: 40%;
    display: block;
    width: 110px;
    height: 36px;
    font-size: 14px;
    line-height: 36px;
    color: rgb(255, 255, 255);
    text-align: center;
    cursor: pointer;
    background: rgb(24, 144, 255);
    border-radius: 100px;
    animation-delay: 0.3s;
    animation-fill-mode: forwards;
  }

  @media (max-width: 992px) {
    .maincontainer {
      background-size: 25rem 20rem;
      width: 25rem;
      height: 20rem;
      top: 30%;
      left: 26%;
    }

    .errorcode {
      top: 55%;
      left: 37%;
      font-size: 3em;
      letter-spacing: 0.1em;
    }

    .errortext {
      position: absolute;
      top: 65%;
      left: 22%;
      color: #FBD130;
      text-align: center;
      text-transform: uppercase;
      font-size: 1.2em;
    }
    .bullshit-return-home{
      float: inherit;
      margin-top: 10px;
      margin-left: 36%;
    }
  }

  @media (max-width: 762px) {
    .maincontainer {
      background-size: 25rem 20rem;
      width: 25rem;
      height: 20rem;
      top: 20%;
      left: 5%;
    }

    .errorcode {
      top: 55%;
      left: 25%;
      font-size: 3em;
      letter-spacing: 0.1em;
    }

    .errortext {
      position: absolute;
      top: 70%;
      left: 0%;
      color: #FBD130;
      text-align: center;
      text-transform: uppercase;
      font-size: 1.2em;
    }
    .bullshit-return-home{
      float: inherit;
      margin-top: 10px;
      margin-left: 40%;
    }
  }
</style>
