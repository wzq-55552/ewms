<template>
        <div class="q-pa-md">
          <q-layout
            view="hHh Lpr lff"
            container
            style="height:320px;"
            class="shadow-2 rounded-borders"
          >
            <q-page-container>
              <q-page padding >
               <q-form
                @submit="setinformation"
                class="q-gutter-md q-mr-lg q-mt-lg"
              >
                <q-input
                  filled
                  dense
                  v-model="userid"
                  label="用户id"
                  lazy-rules
                  :rules="[ val => val.length > 5 || '用户id至少6个字符']"
                />

                <q-input
                  filled
                  dense
                  v-model="password"
                  label="密码"
                  type="password"
                  lazy-rules
                  :rules="[ val => val.length > 0 || '请输入密码']"
                />
                  <q-input
                  filled
                  dense
                  v-model="name"
                  label="姓名"
                  lazy-rules
                  :rules="[ val => val.length > 0 || '请输入姓名']"
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
  Mock.mock('/user/insert',{
        'message':'操作成功！！',
        'code':0
    })
export default {
  data() {
    return {
       userid: '',
      password: '',
      name:'',
    };
  },
  methods: {
    setinformation(){
      var vm = this
      api.setRequestHeader(this)
       axios.post('/user/insert',{
        userid:vm.userid,
        username:vm.name,
        userPsw:vm.password
      }).then(function(response){
       if(response.data.code===0){
          vm.$q.notify({
              color: 'secondary',
              textColor: 'white',
              icon: 'check',
              message: response.data.message
        })
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
              message: '操作失败，请稍后再试'
        })
      })
    }
  }
};
</script>
<style>
.saveButton {
  width: 88px;
  position: fixed;
  bottom: 5px;
  right: 5px;
}
</style>
