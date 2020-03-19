<template>
  <div id="login-container">
   <h5 align="center" class="center">eWMS进销存系统</h5>

    <q-form
      @submit="login"
      class="q-gutter-md q-mr-lg q-mt-lg"
    >
      <q-input
        filled
        v-model="userid"
        label="账号"
        lazy-rules
        :rules="[ val =>val.length > 0 || '请输入账号名']"
      />

      <q-input
        filled
        v-model="password"
        label="密码"
        lazy-rules
        type="password"
        :rules="[ val =>val.length > 0 || '请输入密码']"
      />
      <div class="row">
      <div class="col-8">
      <q-input
        filled
        v-model="checkCode"
        label="请输入验证码"
        lazy-rules
        type="text"
        :rules="[ val =>val.length > 0 || '请输入验证码']"
        ref="inputCode"
      />
      </div>
       <!--<div class="col-4"><div id="checkCode" ref="checkCode" class="code" @click="createCode(4,'checkCode',this)" ></div></div>-->
        <div class="col-4"><img src="http://localhost:8080/createImg" alt=""></div>
       <!--<div class="col-3 self-center q-pa-md"><span @click="createCode(4)" style="margin-top:10px">换一张</span></div>-->
      </div>
      <div class="row">
      <p class="p-input" id="forgetpassword" style="margin-top:0px;"><a href="javascript:void(0);">忘了密码</a></p>
      </div>
      <div>
        <q-btn label="登录" type="submit" color="primary" class="full-width"/>
      </div>
    </q-form>

     <q-dialog v-model="persistent" persistent transition-show="scale" transition-hide="scale">
      <q-card class="bg-teal text-white" style="width: 300px">
        <q-card-section>
          <div class="text-h6">提醒</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
         请及时更新您的密码
        </q-card-section>

        <q-card-actions align="right" class="bg-white text-teal">
          <q-btn flat label="OK" v-close-popup @click="toMain()"/>
        </q-card-actions>
      </q-card>
    </q-dialog>
  </div>
</template>
<script>
import Mock from 'mockjs'
import axios from 'axios'
import api from '../utils/api.js'
    Mock.mock('/oauth/login',{
        'message':'令牌生成成功',
        'code':0,
        'data':{
          'accessToken':'sertehirhawrairhwthortwhtet68u7966743634713449614481',
          'refreshToken':'awe2q34492449847rwa4eawr7ter+ar4wr+wr4rq7rr7qe89q74'
        },
        'isRemind':1
    })
export default {
  data () {
    return {
      userid: '',
      password: '',
      checkCode: '',
      code: '',
      ip:'',
       // 验证码初始值
      identifyCode: '1234',
      // 验证码的随机取值范围
      identifyCodes: '1234567890',
       persistent: false
    }
  },
  methods: {
   // 生成验证码的方法
   createCode(length,name,vm){
     api.createCode(4,"checkCode",this)
   },
  // 检查验证码是否正确
  // validateCode () {
  //       // 获取显示区生成的验证码
  //       var checkCode = this.$refs.checkCode.innerHTML
  //       // 获取输入的验证码
  //       var inputCode = this.checkCode
  //       console.log(checkCode)
  //       console.log(inputCode)
  //        if (inputCode.toUpperCase() !== checkCode.toUpperCase()) {
  //             this.$q.notify({
  //             color: 'red-5',
  //             textColor: 'white',
  //             icon: 'warning',
  //             message: '验证码错误'
  //       })
  //           this.createCode(4)
  //       } else {
  //          this.login()

  //       }
  //   },
    login () {
          var vm = this
          // 验证账号密码是否正确
          axios.post('http://localhost:8089/oauth/login',{
          userID:vm.userid,
          userPsw:vm.password,
          ip:localStorage.getItem("ip")
        }).then(function(response){
          if(response.data.code===0){
            if(response.data.isRemind===1){
              vm.persistent = true
              vm.LoginSet(vm,response)
            }else{
              vm.$router.push('/Main/main')
              vm.LoginSet(vm,response)
            }

          }else{
             vm.$q.notify({
              color: 'red-5',
              textColor: 'white',
              icon: 'warning',
              message: response.data.message
        })
          }
        }).catch(function (error) {
          console.log(error);
        })

    },
      // 点击验证码刷新验证码
    changeCode () {
      this.identifyCode = ''
      this.makeCode(this.identifyCodes, 4)
    },
    // 生成一个随机整数  randomNum(0, 10) 0 到 10 的随机整数， 包含 0 和 10
    randomNum (min, max) {
      max = max + 1
      return Math.floor(Math.random() * (max - min) + min)
    },
    // 随机生成验证码字符串
    makeCode (data, len) {
      for (let i = 0; i < len; i++) {
        this.identifyCode += data[this.randomNum(0, data.length - 1)]
      }
    },
    // 跳转至主页
    toMain(){
       this.$router.push('/Main/main')
    },
    LoginSet(vm,response){
       sessionStorage.setItem('userid',vm.userid)
       sessionStorage.setItem('token',response.data.data.accessToken)
       axios.defaults.headers.common['Authorization'] = 'bearer '+response.data.data.accessToken
    }
  },
  mounted () {
      this.createCode(4,"checkCode",this)
       // 刷新页面就生成随机验证码
      this.identifyCode = ''
      this.makeCode(this.identifyCodes, 4)
  }
}
</script>

<style>
    .code
    {
         font-family:Arial;
         font-style:italic;
         color:blue;
         font-size:30px;
         border:0;
         padding:2px 3px;
         letter-spacing:3px;
         font-weight:bolder;
         cursor:pointer;
         width: 100%;
         height: 70%;
         overflow: hidden;
         line-height:45px;
         text-align:center;
         vertical-align:middle;
         background-color:#D8B7E3;
     }
     span {
        text-decoration:none;
        font-size:12px;
        color:#288bc4;
    }

    span:hover {
        text-decoration:underline;
        cursor:pointer;
    }
     #login-container{
        background-color: #fff;
        padding: 10px;
    }
    h5{
        font-size: 20px;
      }
    .p-input a:hover{
        text-decoration:underline;
     }
</style>
