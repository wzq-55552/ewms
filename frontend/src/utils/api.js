/*
    封装方法
    vm是调用方的this

*/ 
import axios from 'axios'
import Mock from 'mockjs'
Mock.mock('/user/time/lasted',{
  'code':0,
  'data':{
    'argsDate':'2020-3-16',
    'remindTime':'30',
    'stayTime':'15',
     'userid':'admin'
  },
  'messgae':'success'
})
let api={

      // 生成验证码的方法
  createCode (lenght,name,vm) {
    console.log(lenght+","+name+","+vm)
        var code = ''
        var codeLength = parseInt(4) // 验证码的长度
        var checkCode = vm.$refs[name]

        // console.log(checkCode)

        /// /所有候选组成验证码的字符，当然也可以用中文的
        var codeChars = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
        // 循环组成验证码的字符串
        for (var i = 0; i < codeLength; i++) {
            // 获取随机验证码下标
            var charNum = Math.floor(Math.random() * 62)
            // 组合成指定字符验证码
            code += codeChars[charNum]
        }
        if (checkCode) {
            // 为验证码区域添加样式名
            checkCode.className = 'code'
            // 将生成验证码赋值到显示区
            checkCode.innerHTML = code
        }
  },

  // 请求头设置 
  setRequestHeader(vm){
    if(sessionStorage.getItem('token')===null){
      console.log(vm)
      vm.$q.notify({
        color: 'red-5',
        textColor: 'white',
        icon: 'error_outline',
        message: '非法操作，请重新登录'
        })
        setTimeout(function(){ 
          console.log(vm)
          vm.Slogout()
          console.log('setRequestHeaderfail')
         }, 2000); 
    }else{
      axios.defaults.headers.common['Authorization'] = 'bearer '+sessionStorage.getItem('token')
    }
  },
    // 超时退出
    Timeout(vm){
      var count = 0;
      var dataline = 0;
      api.setRequestHeader(vm)
      axios.get('/user/time/lasted').then(function(res){
         dataline = res.data.data.stayTime
         dataline *= 60
         console.log(dataline)
      }).catch(function(error){
        console.log(error)
      })
      // 定时器
      var TimeId = setInterval(function(){
          document.onmousemove=function(){
          count=0
        }
        count++
        // console.log(count)
        if(count===dataline){
          clearInterval(TimeId)
          // 系统退出记录参数
         vm.Slogout()
        }
      },1000)
    }

  }
  
  export default api
