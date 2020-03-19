<template>
  <div class="q-pa-md">
    <q-form @submit="setUserValidate" style="padding-bottom:10px;">
      <div class="row q-col-gutter-sm">
        <div class="col-md-10 col-sm-10 col-xs-10">
          <q-input
            filled
            dense
            standout
            placeholder="请输入用户id/姓名/功能角色/操作资料"
            v-model="queryValue"
            input-class="text-left"
            style="background-color:white;border-radius:2px;"
          >
            <template v-slot:append>
              <q-icon name="search" style="color:#333;" />
            </template>
          </q-input>
        </div>
        <div class="col-md-2 col-sm-2 col-xs-2">
          <q-btn label="新增 +" color="primary" @click="dialog = true" class="fit" />
          <q-dialog v-model="dialog" >
              <q-card style="min-width:50%;">
                <q-card-section >
                  <q-input  label="用户id" />
                  <q-input label="密码" />
                  <q-input label="姓名" />
                  <q-input label="手机" />
                  <q-input label="功能角色" />
                  <q-input label="操作资料角色" />
                </q-card-section>

                <q-card-actions align="right">
                  <q-btn flat label="取消" color="primary" v-close-popup />
                  <q-btn flat label="保存" color="primary" v-close-popup @click="newAdd()" />
                </q-card-actions>
              </q-card>
          </q-dialog>
        </div>
      </div>
    </q-form>

    <q-table
      :data="getData(data)"
      :columns="columns"
      title="用户权限设定资料库"
      :rows-per-page-options="[]"
      row-key="name"
    >
      <template v-slot:body="props">
        <q-tr :props="props">
          <q-td key="userId" :props="props">
            {{ props.row.userId }}
            <q-popup-edit v-model="props.row.userId">
              <q-input v-model="props.row.userId" dense autofocus />
            </q-popup-edit>
          </q-td>

          <q-td key="userPwd" :props="props">
            {{ props.row.userPwd }}
            <q-popup-edit v-model.number="props.row.userPwd">
              <q-input v-model.number="props.row.userPwd" dense autofocus />
            </q-popup-edit>
          </q-td>
          <q-td key="userName" :props="props">
            <div class="text-pre-wrap">{{ props.row.userName }}</div>
            <q-popup-edit v-model.number="props.row.userName">
              <q-input v-model.number="props.row.userName" dense autofocus />
            </q-popup-edit>
          </q-td>
          <q-td key="userPhone" :props="props">
            {{ props.row.userPhone }}
            <q-popup-edit v-model.number="props.row.userPhone">
              <q-input v-model.number="props.row.userPhone" dense autofocus />
            </q-popup-edit>
          </q-td>
          <q-td key="func" :props="props">
            {{ props.row.func }}
            <q-popup-edit v-model.number="props.row.func">
              <q-input v-model.number="props.row.func" dense autofocus />
            </q-popup-edit>
          </q-td>
          <q-td key="role" :props="props">
            {{ props.row.role }}
            <q-popup-edit v-model.number="props.row.role">
              <q-input v-model.number="props.row.role" dense autofocus />
            </q-popup-edit>
          </q-td>
          <q-td>
            <q-btn @click="deleteUserByuserId(props.row.userId)">删除</q-btn>
            <q-btn @click="saveUserByuserId(props.row.userId)">保存</q-btn>
          </q-td>
        </q-tr>
      </template>
    </q-table>
  </div>
</template>

<script>
// 用户权限设定资料库
const columns = [
  { name: "userId", align: "left", label: "用户id", field: "userId" },
  { name: "userPwd", align: "center", label: "密码", field: "userPwd" },
  { name: "userName", label: "姓名", field: "userName" },
  { name: "userPhone", label: "手机", field: "userPhone" },
  { name: "func", label: "功能角色", field: "func" },
  { name: "role", label: "操作资料角色", field: "role" },
  { name: "", label: "", field: "" }
];
const data = [
  {
    userId: "77123456",
    userPwd: "1234567890",
    userName: "小红",
    userPhone: "177272890736",
    func: "客户",
    role: "中真"
  },
  {
    userId: "88123456",
    userPwd: "1234567890",
    userName: "小明",
    userPhone: "177272890736",
    func: "物流部",
    role: "合德"
  },
  {
    userId: "9923456",
    userPwd: "1234567890",
    userName: "小红",
    userPhone: "177272890736",
    func: "客户",
    role: "中真"
  },
  {
    userId: "1123456",
    userPwd: "1234567890",
    userName: "小红",
    userPhone: "177272890736",
    func: "客户",
    role: "中真"
  },
  {
    userId: "1123456",
    userPwd: "1234567890",
    userName: "小红",
    userPhone: "177272890736",
    func: "客户",
    role: "中真"
  }
];
export default {
  data() {
    return {
      data,
      columns,
      queryValue: "",
       dialog: false,
      cancelEnabled: false
    };
  },
  methods: {
    getData(data){
       return this.data.filter(item => {
        if (item.userId.includes(this.queryValue)) {
          return item;
        }
      });
    },
    newAdd() {
      alert("保存成功");
    },
    deleteUserByuserId(id){
      var index = this.data.findIndex(item => {
          if (item.userId == id) {
            return true;
          }
        });
      this.data.splice(index, 1);
    },
    saveUserByuserId(id){
      alert("id:"+id+"的用户信息保存成功");
    },
    setUserValidate() {}
    
    
  }
};
</script>
<style>
.text-right {
  text-align: center;
}
</style>