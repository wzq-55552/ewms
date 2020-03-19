<template>
        <div class="q-pa-md">
          <q-layout
            view="hHh Lpr lff"
            container
            style="height: 340px;"
            class="shadow-2 rounded-borders"
          >
            <q-page-container>
              <q-page padding >
               <q-form
                @submit="setUserValidate"
                class="q-gutter-md q-mr-lg q-mt-lg"
              >
                <q-input
                  filled
                  dense
                  label="用户id"
                  readonly
                  v-model="userID"
                />

                <q-input
                  filled
                  dense
                  readonly
                  label="参数设定日期时间"
                  v-model="setTmie"
                />
                  <q-input
                  filled
                  dense
                  label="画面停滞逾时时间"
                  v-model="overTime"
                  suffix="(分钟)"
                />
                 <q-input
                  filled
                  dense
                  label="定期更改密码提醒时间"
                  v-model="updatePasswordTime"
                  suffix="(天数)"
                />
                <div>
                  <q-btn label="提交" type="submit" color="primary"  class="full-width lt-md inline"/>
                     <q-btn color="primary" type="submit" label="提交" class="saveButton gt-sm inline"></q-btn>
                </div>
              </q-form>
              </q-page>
            </q-page-container>
          </q-layout>
        </div>
</template>

<script>
import Mock from 'mockjs'
import axios from 'axios'
import api from '../utils/api.js'
Mock.mock('/user/get/time/all',{
  'code':0,
  'data':{
    'userId':'001',
  'argsDate':'2001-5-8',
  // 定时修改密码时间
  'remindTime':'15',
  // 画面滞留时间
  'stayTime':'30'
  },
  'message':'success!'
})
Mock.mock('/user/time/insert',{
  'code':0,
  'message':'修改成功'
})
export default {
  data() {
    return {
      userID:'',
      setTmie:'',
      overTime:'',
      updatePasswordTime:''
    }
  },
    methods: {
    setUserValidate(){
       let vm = this
       api.setRequestHeader(this)
        axios.post('/user/time/insert',{
          userId:vm.userID,
          stayTime:vm.overTime,
          remindTime:vm.updatePasswordTime
        }).then(function(response){
          if(response.data.code===0){
             vm.$q.notify({
              color: 'secondary',
              textColor: 'white',
              icon: 'check',
              message: response.data.message   
             })
          vm.getTimeAll()
          }else{
            vm.$q.notify({
              color: 'red-14',
              textColor: 'white',
              icon: 'close',
              message: response.data.message
        })
          }
        }).catch(function(error){
          console.log(error)
           vm.$q.notify({
              color: 'red-14',
              textColor: 'white',
              icon: 'close',
              message: '资料获取失败'
        })
        })
    },
    // 获取所以系统参数
    getTimeAll(){
      this.userID = sessionStorage.getItem('userid')
      let vm = this
      api.setRequestHeader(this)
      axios.get('/user/get/time/all').then(function(response){
        vm.setTmie = response.data.data.argsDate
        vm.overTime = response.data.data.stayTime
        vm.updatePasswordTime = response.data.data.remindTime
      }).catch(function(error){
        console.log(error)
        vm.$q.notify({
              color: 'red-14',
              textColor: 'white',
              icon: 'cloud_done',
              message: '资料获取失败'
        })
      })
    }
  },
  mounted(){
    this.getTimeAll()
  }
};
</script>
<style>
</style>
