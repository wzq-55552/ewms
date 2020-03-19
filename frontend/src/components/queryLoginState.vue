<template>
        <div class="q-pa-md">
          <q-layout
            view="hHh Lpr lff"
          >
            <q-page-container>
              <q-page padding >
               <q-form
                @submit="query"
                class="q-gutter-md q-mr-lg q-mt-lg"
              >
                  <!--PC端-->
                  <div class="gt-sm inline">
                   <div class="row q-col-gutter-sm">
                     <div class="col-md-6">
                      <q-input  filled dense v-model="querycontent" label="设备id">
                    <template v-slot:prepend>
                      <q-icon name="search" />
                    </template>
                    
                  </q-input>
                     </div>
                      <div class="col-md-6">
                      <q-input  filled dense v-model="querycontent" label="用户id">
                    <template v-slot:prepend>
                      <q-icon name="search" />
                    </template>                  
                  </q-input>
                     </div>
                   </div>
                   <div class="row q-col-gutter-sm q-mt-sm">
                   <div class="col-md-6">
                    <q-input filled v-model="beginDate" dense label="初始时间">
                        <template v-slot:prepend>
                          <q-icon name="event" class="cursor-pointer">
                            <q-popup-proxy transition-show="scale" transition-hide="scale">
                              <q-date v-model="beginDate" mask="YYYY-MM-DD HH:mm:ss" />
                            </q-popup-proxy>
                          </q-icon>
                        </template>

                        <template v-slot:append>
                          <q-icon name="access_time" class="cursor-pointer">
                            <q-popup-proxy transition-show="scale" transition-hide="scale">
                              <q-time v-model="beginDate" mask="YYYY-MM-DD HH:mm:ss" with-seconds format24h />
                            </q-popup-proxy>
                          </q-icon>
                        </template>
                       </q-input>
                    </div>
                     <div class="col-md-6">
                      <q-input filled v-model="endDate" dense label="结束时间">
                        <template v-slot:prepend>
                          <q-icon name="event" class="cursor-pointer">
                            <q-popup-proxy transition-show="scale" transition-hide="scale">
                              <q-date v-model="endDate" mask="YYYY-MM-DD HH:mm:ss" />
                            </q-popup-proxy>
                          </q-icon>
                        </template>

                        <template v-slot:append>
                          <q-icon name="access_time" class="cursor-pointer">
                            <q-popup-proxy transition-show="scale" transition-hide="scale">
                              <q-time v-model="endDate" mask="YYYY-MM-DD HH:mm:ss" with-seconds format24h />
                            </q-popup-proxy>
                          </q-icon>
                        </template>
                       </q-input>
                    </div>
                   </div>
                   </div>
                   <!--手机端-->
                   <div class="lt-md inline">
                   <div class="row q-col-gutter-sm">
                     <div class="col-sm-12 col-xs-12">
                      <q-input  filled dense v-model="querycontent" label="设备id">
                    <template v-slot:prepend>
                      <q-icon name="search" />
                    </template>
                    
                  </q-input>
                     </div>
                      <div class="col-sm-12 col-xs-12">
                      <q-input  filled dense v-model="querycontent" label="用户id">
                    <template v-slot:prepend>
                      <q-icon name="search" />
                    </template>                  
                  </q-input>
                     </div>
                   </div>
                   <div class="row q-col-gutter-sm q-mt-sm">
                     <div class="col-sm-12 col-xs-12">
                     <q-input filled v-model="beginDate" dense label="初始时间">
                        <template v-slot:prepend>
                          <q-icon name="event" class="cursor-pointer">
                            <q-popup-proxy transition-show="scale" transition-hide="scale">
                              <q-date v-model="beginDate" mask="YYYY-MM-DD HH:mm:ss" />
                            </q-popup-proxy>
                          </q-icon>
                        </template>

                        <template v-slot:append>
                          <q-icon name="access_time" class="cursor-pointer">
                            <q-popup-proxy transition-show="scale" transition-hide="scale">
                              <q-time v-model="beginDate" mask="YYYY-MM-DD HH:mm:ss" with-seconds format24h />
                            </q-popup-proxy>
                          </q-icon>
                        </template>
                       </q-input>
                    </div>
                   </div>
                    <div class="row q-col-gutter-sm q-mt-sm">
                     <div class="col-sm-12 col-xs-12">
                      <q-input filled v-model="endDate" dense label="结束时间">
                        <template v-slot:prepend>
                          <q-icon name="event" class="cursor-pointer">
                            <q-popup-proxy transition-show="scale" transition-hide="scale">
                              <q-date v-model="endDate" mask="YYYY-MM-DD HH:mm:ss" />
                            </q-popup-proxy>
                          </q-icon>
                        </template>

                        <template v-slot:append>
                          <q-icon name="access_time" class="cursor-pointer">
                            <q-popup-proxy transition-show="scale" transition-hide="scale">
                              <q-time v-model="endDate" mask="YYYY-MM-DD HH:mm:ss" with-seconds format24h />
                            </q-popup-proxy>
                          </q-icon>
                        </template>
                       </q-input>
                    </div>
                   </div>
                   </div>
                   <div class="row" style="width:10%;margin-left:90%;">
                      <q-btn color="primary" type="submit" label="提交" class="fit gt-sm inline"/>
                    </div>
                  
                   <q-btn label="提交" type="submit" color="primary"  class="fit lt-md inline"/>
              </q-form>
              <div class="q-mt-xl">
              <template>
               <div class="q-pa-md">
                 <q-table
                   :data="log"
                   :columns="columns"
                   row-key="firstid"
                   class="q-mb-md"
                 />
               </div>
                </template>
              </div>
              </q-page>
            </q-page-container>
          </q-layout>
        </div>
</template>

<script>
import Mock from 'mockjs'
import axios from 'axios'
import api from '../utils/api.js'
// 查询后的数据
  Mock.mock('/user/login/search',{
        'code':0,
        'data':[
              {
                equipment:"001",
                firstId:1,
                inDate:"2019-4-5",
                outDate:"2019-7-2",
                outType:1,
                userId:"user001"
              },
              {
                equipment:"002",
                firstId:2,
                inDate:"2019-4-5",
                outDate:"2019-7-2",
                outType:2,
                userId:"user002"
              },
              {
                equipment:"003",
                firstId:3,
                inDate:"2019-4-5",
                outDate:"2019-7-2",
                outType:3,
                userId:"user003"
              },
              {
                equipment:"004",
                firstId:4,
                inDate:"2019-4-5",
                outDate:"2019-7-2",
                outType:4,
                userId:"user004"
              }
            ],
        'message':'success!'
    })
    // 初始数据
    Mock.mock('/user/get/login/all',{
        'code':0,
        'data':[
              {
                equipment:"001",
                firstId:1,
                inDate:"2019-4-5",
                outDate:"2019-7-2",
                outType:1,
                userId:"user001"
              },
              {
                equipment:"002",
                firstId:2,
                inDate:"2019-4-5",
                outDate:"2019-7-2",
                outType:2,
                userId:"user002"
              },
              {
                equipment:"003",
                firstId:3,
                inDate:"2019-4-5",
                outDate:"2019-7-2",
                outType:3,
                userId:"user003"
              },
              {
                equipment:"004",
                firstId:4,
                inDate:"2019-4-5",
                outDate:"2019-7-2",
                outType:4,
                userId:"user004"
              },{
                equipment:"005",
                firstId:5,
                inDate:"2019-4-5",
                outDate:"2019-7-2",
                outType:5,
                userId:"user005"
              },{
                equipment:"006",
                firstId:6,
                inDate:"2019-4-5",
                outDate:"2019-7-2",
                outType:6,
                userId:"user006"
              },{
                equipment:"007",
                firstId:7,
                inDate:"2019-7-5",
                outDate:"2019-7-2",
                outType:7,
                userId:"user007"
              },{
                equipment:"008",
                firstId:8,
                inDate:"2019-8-5",
                outDate:"2019-8-2",
                outType:8,
                userId:"user008"
              },
              {
                equipment:"009",
                firstId:9,
                inDate:"2019-9-5",
                outDate:"2019-9-2",
                outType:9,
                userId:"user009"
              },{
                equipment:"010",
                firstId:10,
                inDate:"2019-10-5",
                outDate:"2019-10-2",
                outType:10,
                userId:"user0010"
              }
            ],
        'message':'success!'
    })
export default {
  data() {
    return {
      // 查询条件
       querycontent:'',
      //  初始时间
       beginDate:'',
      //  结束时间
       endDate:'',
      //  log日志
      log:[],
      columns: [
        { name: 'userId', align: 'center', label: '用户id', field: 'userId'},
        { name: 'equipment', label: '登录设备id', field: 'equipment'},
        { name: 'outType', label: '登出触发者', field: 'outType' },
        { name: 'inDate', label: '登入日期与时间', field: 'inDate' },
        { name: 'outDate', label: '登出日期与时间', field: 'outDate' },
      ]
    };
  },
  methods: {
    getLogAll(){
        let vm = this
        api.setRequestHeader(this)
        axios.get('/user/get/login/all').then(function(response){
          vm.log = response.data.data
        }).catch(function(error){
          console.log(error)
          vm.$q.notify({
              color: 'red-14',
              textColor: 'white',
              icon: 'cloud_done',
              message: '查询失败，请稍后再试'
          })
        })
    },
    query(){
      let vm = this
      var begindate = this.beginDate
      var enddate = this.endDate
      var equipment = this.equipment
      var userid = this.userId
      api.setRequestHeader(this)
      axios.post('/user/login/search',{
        beginDate:begindate,
        endDate:enddate,
        equipment:equipment,
        userId:userid
      }).then(function(response){
       if(response.data.code===0){
         var length = response.data.data.length
         if(length){
         console.log(response.data.data)
         vm.log = response.data.data
       }else{
          vm.$q.notify({
              color: 'secondary',
              textColor: 'white',
              icon: 'close',
              message: response.data.message
        })
       }
       }
        
      }).catch(function(error){
        console.log(error)
        vm.$q.notify({
              color: 'red-14',
              textColor: 'white',
              icon: 'close',
              message: '未找到到相关结果'
        })
      })
    }
  },
  mounted(){
    this.getLogAll()
  }
};
</script>
<style>
</style>
