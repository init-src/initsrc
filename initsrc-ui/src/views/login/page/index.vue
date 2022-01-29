<template>
  <div class="mains" v-loading="loading">
    <div class="login-content">
      <div>
        <div class="signup-bg-stars"></div>
        <div class="signup-bg-stars-2"></div>
        <div class="signup-bg-stars-3"></div>
      </div>
      <div class="container">
        <el-row class="row justify-content-center">
          <el-col :md="24" :lg="12" :xl="12">
            <div class="card">
              <div class="bg-login text-center">
                <div class="bg-login-overlay"></div>
                <div class="position-relative">
                  <div class="text-white font-size-20">欢迎回来 !</div>
                  <p class="text-white-50 mb-0">INIT SRC 管理后台</p>
                  <a href="index" class="logo logo-admin mt-4">
                    <img src="../../../assets/img/minilogo.png" alt="" height="30">
                  </a>
                </div>
              </div>
              <div class="card-body pt-5">
                <div class="p-2">
                  <el-form :model="form" :rules="rules" ref="ruleForm">
                    <el-form-item label="用户名" prop="userName">
                      <el-input v-model="form.userName" placeholder="请输入用户名"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                      <el-input v-model="form.password" placeholder="请输入密码" show-password></el-input>
                    </el-form-item>
                    <el-form-item label="验证码" prop="code">
                      <el-input v-model="form.code" placeholder="请输入验证码">
                        <img @click="getVerifyCode()" slot="suffix" :src="imgCode" class="login_code_img" />
                      </el-input>
                    </el-form-item>
                  </el-form>
                  <div style="padding-top: 15px;">
                    <div class="login-btn" @click="onSubmit('ruleForm')" @keyup.enter.native="onSubmit('ruleForm')">登
                      录</div>
                    <span style="color: #F56C6C;font-size: 12px;">{{errorMsg}}</span>
                  </div>
                </div>
              </div>
            </div>

          </el-col>
        </el-row>
      </div>
    </div>
    <img src="../../../assets/img/hero-glow.svg" class="home-hero-glow">
  </div>
</template>

<script src="../js/index.js">
</script>

<style>
  /* @import url("@/assets/style/myadmin.css"); */

  .mains {
    position: absolute;
    top: 0;
    left: 0;
    height: 100%;
    width: 100%;
    background-color: #1f1d4b;
  }

  .login-content .card-body .el-input__inner {
    height: 50px !important;
    line-height: 50px !important;
    border: none;
    font-size: 15px;
    background-color: #122346;
    color: #8ba6d5;
  }

  .login-content .card-body .el-form-item__label{
     color: #F0F0F0;
  }

  .login-content {
    margin-bottom: 3rem !important;
    margin-top: 3rem !important;
  }

  .justify-content-center {
    justify-content: center !important;
  }

  .login_code_img {
    width: 110px;
    /* height: 35px; */
    padding: 5px;
    cursor: pointer;
  }

  img {
    vertical-align: middle;
    border-style: none;
  }

  .login-btn {
    width: 100%;
    background-color: #3b5de7;
    border-color: #3b5de7;
    color: white;
    opacity: 0.8;
    display: inline-block;
    line-height: 1;
    white-space: nowrap;
    cursor: pointer;
    -webkit-appearance: none;
    text-align: center;
    box-sizing: border-box;
    outline: 0;
    margin: 0;
    transition: .1s;
    font-weight: 500;
    padding: 18px;
    font-size: 18px;
    border-radius: 4px;
  }

  .login-btn:hover,
  .login-btn:active {
    background-color: #3c5fec;
    border-color: #3c5fec;
    color: white;
  }

  .card-body {
    flex: 1 1 auto;
    min-height: 1px;
    padding-left: 1.25rem;
    padding-right: 1.25rem;
    padding-top: 1.25rem;
    padding-bottom: 40px;
  }

  .card-body .el-form-item {
    margin-bottom: 10px;
  }

  .pt-5,
  .py-5 {
    padding-top: 3rem !important;
  }


  *,
  *::before,
  *::after {
    box-sizing: border-box;
  }

  .container {
    width: 100%;
    padding-right: 12px;
    padding-left: 12px;
    margin-right: auto;
    margin-left: auto;
  }

  .card {
    margin-bottom: 24px;
    box-shadow: 0 0.75rem 1.5rem rgba(18, 38, 63, 0.03);
    overflow: hidden !important;
  }

  .card {
    border-radius: 20px !important;
    margin-bottom: 24px;
    overflow: hidden !important;
    background-image: url(../../../assets/img/login_form.png);
    background-color: rgba(92, 102, 240, 0) !important;
    position: relative;
    flex-direction: column;
    min-width: 0;
    word-wrap: break-word;
    background-color: #fff;
    background-clip: border-box;
    border: 0 solid #f6f6f6;
    border-radius: 0.25rem;
    z-index: 10;
  }

  .row {
    display: flex;
    flex-wrap: wrap;
  }

  .bg-login {
    /* background-image: url(../../../assets/img/logingif.gif); */
    padding: 60px 0px;
    background-size: cover;
    background-position: center center;
    position: relative;
    border-radius: 0px 0px 50% 50%;
  }

  .text-center {
    text-align: center !important;
  }

  .bg-login-overlay {
    position: absolute;
    background: linear-gradient(90deg, #273c92, #293e92);
    top: 0;
    right: 0;
    left: 0;
    bottom: 0;
    width: 100%;
    height: 100%;
    border-radius: 0px 0px 50% 50%;
    opacity: 0.5;
  }

  .position-relative {
    position: relative !important;
  }

  .font-size-20 {
    font-size: 20px !important;
    margin-bottom: 10px;
  }

  .text-white {
    color: #fff !important;
  }

  .text-white-50 {
    margin-top: 0;
    margin-bottom: 1rem;
    font-size: 12px;
    color: rgba(255, 255, 255, 0.5) !important;
  }

  .mb-0,
  .my-0 {
    margin-bottom: 0 !important;
  }

  .logo-admin {
    position: absolute;
    left: 0;
    right: 0;
    margin: 0px auto;
    width: 74px;
    height: 74px;
    line-height: 74px;
    background: #fff;
    border-radius: 50%;
    text-align: center;
    box-shadow: 0.9rem 0.9rem 1.5rem rgba(18, 38, 63, 0.03);
  }

  .mt-4,
  .my-4 {
    margin-top: 1.5rem !important;
  }

  .signup-bg-stars {
    z-index: -200;
    width: 1px;
    height: 1px;
    background: transparent;
    box-shadow: 67px 640px rgb(255 255 255 / 20%), 644px 587px #fff, 868px 669px #fff, 1045px 577px #fff, 526px 1813px #fff, 1504px 1091px #fff, 508px 1382px #fff, 325px 1100px #fff, 397px 1957px #fff, 1041px 281px #fff, 332px 821px #fff, 447px 1536px #fff, 1385px 764px #fff, 651px 1491px #fff, 232px 297px #fff, 178px 1859px #fff, 1375px 19px #fff, 814px 147px #fff, 1990px 1153px #fff, 1197px 134px #fff, 946px 843px #fff, 1842px 616px #fff, 1407px 63px #fff, 1049px 1087px #fff, 209px 1047px #fff, 220px 1950px #fff, 556px 146px #fff, 1130px 51px #fff, 1510px 1087px #fff, 1633px 1880px #fff, 851px 744px #fff, 381px 1877px #fff, 1209px 775px #fff, 1173px 967px #fff, 429px 1121px #fff, 952px 307px #fff, 1909px 218px #fff, 1112px 1813px #fff, 531px 1179px #fff, 1035px 921px #fff, 1982px 1846px #fff, 263px 1389px #fff, 1300px 1941px #fff, 1462px 1699px #fff, 683px 1414px #fff, 1201px 1993px #fff, 405px 1335px #fff, 153px 363px #fff, 276px 1358px #fff, 93px 533px #fff, 1402px 1010px #fff, 336px 198px #fff, 459px 1444px #fff, 564px 1735px #fff, 1149px 530px #fff, 23px 629px #fff, 545px 1541px #fff, 1628px 313px #fff, 985px 262px #fff, 1548px 849px #fff, 314px 554px #fff, 1186px 1678px #fff, 1730px 1482px #fff, 1668px 511px #fff, 68px 446px #fff, 1298px 27px #fff, 1667px 1149px #fff, 1556px 753px #fff, 1857px 542px #fff, 764px 554px #fff;
    animation: animStar 100s linear infinite;
  }

  .signup-bg-stars-2 {
    width: 2px;
    height: 2px;
    box-shadow: 301px 755px rgb(255 255 255 / 20%), 462px 1779px #fff, 620px 767px #fff, 1006px 1065px #fff, 1081px 208px #fff, 239px 1710px #fff, 95px 174px #fff, 53px 850px #fff, 794px 756px #fff, 1503px 639px #fff, 1761px 1458px #fff, 12px 1676px #fff, 1628px 1963px #fff, 1730px 418px #fff, 1937px 1235px #fff, 666px 53px #fff, 979px 868px #fff, 77px 993px #fff, 100px 298px #fff, 489px 537px #fff;
    animation: animStar 150s linear infinite;
  }

  .signup-bg-stars-3 {
    width: 3px;
    height: 3px;
    box-shadow: 392px 921px rgb(255 255 255 / 20%), 196px 312px #fff, 208px 1923px #fff, 539px 1422px #fff, 771px 692px #fff, 1267px 1262px #fff, 883px 989px #fff, 1628px 1220px #fff, 770px 331px #fff, 1975px 30px #fff;
    animation: animStar 200s linear infinite;
  }

  .home-hero-glow {
    top: 50%;
    left: 50%;
    width: 200%;
    transform: translate(-50%, -50%);
    overflow: hidden !important;
    pointer-events: none;
    position: absolute;
    border-style: none;
  }




  @keyframes animStar {
    0% {
      transform: translateY(0);
    }

    100% {
      transform: translateY(-2000px);
    }
  }

  a {
    text-decoration: none !important;
  }

  @media (min-width: 576px) {
    .container {
      max-width: 440px;
    }
  }



  @media (min-width: 768px) {
    .container {
      max-width: 440px;
    }

    .login-content {
      padding-top: 3rem !important;
    }
  }

  @media (min-width: 992px) {
    .container {
      max-width: 440px;
    }

    .login-content {
      padding-top: 3rem !important;
    }
  }

  @media (min-width: 1200px) {
    .container {
      max-width: 1000px;
    }

    .login-content {
      padding-top: 3rem !important;
    }
  }
</style>
