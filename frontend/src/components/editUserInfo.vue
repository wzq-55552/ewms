<template>
        <div class="q-pa-md">
          <q-layout
            view="hHh Lpr lff"
            container
            style="height: 440px;"
            class="shadow-2 rounded-borders"
          >
            <q-page-container>
              <q-page padding >
               <q-form
                @submit="editinformation"
                class="q-gutter-md q-mr-lg q-mt-lg"
              >
                <q-input
                  filled
                  dense
                  v-model="userID"
                  label="账号"
                  readonly
                />

                <q-input
                  filled
                  dense
                  v-model="password"
                  type="password"
                  label="密码"
                />
                  <q-input
                  filled
                  dense
                  v-model="name"
                  label="姓名"
                />
                 <q-input
                  filled
                  dense
                  v-model="phone"
                  label="手机"
                />
                 <q-input
                  filled
                  dense
                  v-model="functionName"
                  label="功能角色"
                  readonly
                />
                 <q-input
                  filled
                  dense
                  v-model="RoName"
                  label="操作角色"
                  readonly
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
  Mock.mock(/^\/user\/select\//,{
        code:0,
        data:{
          'frId':'001481',
        'frIdDescription':'物流部',
        'roidId':'18831',
        'roidIdDescription':'中真',
        'tel':'12345678978',
        'userId':'466',
        'userName':'XXXS'
        },
        message:'获取数据成功'
    })
    Mock.mock('/user/update',{
        'code':0,
        'message':'修改成功'
    })
export default {
  data() {
    return {
      userID:'',
      password:'',
      name:'',
      phone:'',
      functionName:'',
      RoName:'',
      // 日期更改时间
      pswDate:''
    };
  },mounted(){
     this.getUser()
  },
  methods: {
    getUser(){
      this.userID = sessionStorage.getItem('userid')
      api.setRequestHeader(this)
      let url = 'http//localhost:8080/user/select/'+this.userID
       axios.get(url,{
      }).then((response)=>{
        // this.userID =response.data.userId
        this.name = response.data.data.userName
        this.phone = response.data.data.tel
        this.functionName = response.data.data.frIdDescription
        this.RoName = response.data.data.roidIdDescription
        this.pswDate = response.data.data.pswDate
      }).catch(function(error){
        console.log(error)
      })
    },
    editinformation(){
      let vm = this
      api.setRequestHeader(this)
       axios.post('/user/update',{
        userName:vm.names,
        userPsw:vm.password,
        tel:vm.phone,
        userId:vm.userID
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
