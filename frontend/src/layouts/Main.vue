<template>
  <div style="width:100%; margin:0 auto;">
    <q-layout view="hHh lpR fFf">
      <q-header elevated class="bg-primary text-white">
        <q-toolbar  class="">
          <q-btn dense flat round icon="menu" @click="left = !left" />
          <q-toolbar-title>{{main}}</q-toolbar-title>
           <q-space />
          <q-tabs v-model="tab" shrink class="gt-sm inline">
          <q-route-tab to="/Main/main" name="main"  label="主页面" exact @click.native=" change('eWMS系统')"/>
          <q-route-tab to="/Main/main" name="order" label="订单状态查询" exact @click.native=" change('eWMS系统')"/> 
          <q-route-tab to="/Main/main" name="huoPinWeiHu" label="货品基本资料维护" exact @click.native=" change('eWMS系统')"/> 
          <q-route-tab to="/Main/main" name="GoodsService" label="商品服务查询" exact @click.native=" change('eWMS系统')"/> 
      </q-tabs>
        </q-toolbar>
      </q-header>

      <q-drawer v-model="left" side="left" bordered>
        <div class="q-pa-md" style="width: 100%;text-align:center;">
          <q-list>
           <q-item clickable v-ripple to="/Main/setUserInfo" @click=" change('设定用户资料')">
              <q-item-section>设定用户资料</q-item-section>
            </q-item>
            <q-item clickable v-ripple to="/Main/editUserInfo" @click=" change('用户资料修改')">
              <q-item-section>用户资料修改</q-item-section>
            </q-item>
            <q-item clickable v-ripple to="/Main/queryLoginState" @click=" change('登录查询')">
              <q-item-section>登录查询</q-item-section>
            </q-item>
            <q-item clickable v-ripple to="/Main/setSystemParm" @click=" change('系统参数设定')">
              <q-item-section>系统参数设定</q-item-section>
            </q-item>
            <q-item clickable v-ripple to="/Main/Permission"  @click=" change('权限分配')" class="gt-sm inline">
              <q-item-section>权限分配</q-item-section>
            </q-item>
            <q-item clickable v-ripple @click=" Ulogout()">
              <q-item-section style="color:#000;">退出登录</q-item-section>
            </q-item>
            <q-separator spaced  class="lt-md inline"/>
            <q-item clickable v-ripple class="lt-md inline">
              <q-item-section>帮助</q-item-section>
            </q-item>
            <q-item clickable v-ripple class="lt-md inline">
              <q-item-section>反馈</q-item-section>
            </q-item>
            <q-item clickable v-ripple class="lt-md inline">
              <q-item-section>联系我们</q-item-section>
            </q-item>
           
          </q-list>
        </div>
      </q-drawer>
        <q-page-container>
      <!-- This is where pages get injected -->
      <router-view />
       <div class="fixed-bottom  lt-md inline">
   <q-tabs
        v-model="tab"
        indicator-color="#fff"
 
        class="bg-primary text-white shadow-2"
      >
        <q-route-tab to="/Main/main" name="main" icon="home" label="主页面" exact @click.native=" change('eWMS系统')"/>
        <q-route-tab to="/Main/main" name="order" icon="description" label="订单查询" exact @click.native=" change('eWMS系统')"/>
        <q-route-tab to="/Main/main" name="huoPinWeiHu"  icon="settings" label="资料维护" exact @click.native=" change('eWMS系统')"/> 
        <q-route-tab to="/Main/main" name="GoodsService" icon="search" label="服务查询" exact @click.native=" change('eWMS系统')"/> 
      </q-tabs>
 </div>
    </q-page-container>
     
    </q-layout>
  </div>
</template>
<script>
import Mock from 'mockjs'
import axios from 'axios'
import api from '../utils/api.js'
Mock.mock('/user/logout/own',{
  'code':0,
  'message':'用户退出登录' 
})
Mock.mock('/user/logout/system',{
  'code':0,
  'message':'系统退出登录'
})
export default {
   data() {
    return {
       flag: true,
      password: '',
      name:'',
      model: "one",
      left: false,
      val: false,
      userType: "",
      drawer: false,
      main:'eWMS系统',
      tab: 'mails'
    };
  },methods:{
    change(name){ 
      this.main=name;
    },
    Slogout(){
      // let vm = this
        this.$router.push('/')
       axios.post('/user/logout/system',{
             userid : sessionStorage.getItem('userid')
          }).then(function(response){
            if(response.data.code===0){
               sessionStorage.removeItem('userid')
               sessionStorage.removeItem('token')
               console.log(response.data.message)
              //  vm.$router.push('/')
            }
          }).catch(function(error){
            console.log(error)
          })
    },
    Ulogout(){
      // var vm=this
      this.$router.push('/')
      axios.post('/user/logout/own',{
        userid : sessionStorage.getItem('userid')
      }).then(function(response){
          if(response.data.code===0){
            sessionStorage.removeItem('userid')
            sessionStorage.removeItem('token')
            console.log(response.data.message)
          }
      }).catch(function(error){
        console.log(error)
      })
    }
  },
  mounted(){
          api.Timeout(this)
  }
}
</script>



