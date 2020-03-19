<template>
  <div style="width:100%; margin:0 auto;">
    <q-layout view="hHh lpR fFf">
      <!-- 权限设置主功能界面 -->
      <q-page-container>
        <div class="q-pa-md" @click="change">
          <q-btn-toggle
            v-model="model"
            toggle-color="primary"
            :options="[
            {label: '功能角色设定权限', value: 'one'},
            {label: '操作资料角色设定权限', value: 'two'},
            {label: '权限角色分配',value:'three'}
          ]"
          />
        </div>

        <!-- 权限设置菜单和内容 -->
        <div v-if="model=='one'||model=='two'" class="q-pa-md">
          <q-layout
            view="hHh Lpr lff"
            container
            class="shadow-2 rounded-borders"
            style="height: 400px;"
          >
            <q-drawer
              v-model="drawer"
              show-if-above
              :width="200"
              :breakpoint="500"
              bordered
              content-class="bg-grey-3"
            >
              <q-scroll-area class="fit">
                <q-input
                  dark
                  dense
                  standout
                  v-model="searchfcunText"
                  input-class="text-left"
                  style="background-color:white;border-radius:2px;"
                >
                  <template v-slot:append>
                    <q-icon name="search" style="color:#333;" />
                  </template>
                </q-input>
                <q-list
                  v-for="(menuItem, index) in (model=='one'?searchfunc(funcList):searchfunc(roleList))"
                  :key="index"
                >
                  <q-item
                    clickable
                    :active="index === funcIndex"
                    v-ripple
                    @click="changefunction(index)"
                  >
                    <q-item-section v-if="!menuItem.editable">{{ menuItem.name }}</q-item-section>
                    <input
                      v-else
                      class="menuItemFunction"
                      type="text"
                      v-model="menuItem.name"
                      v-myfocus
                      @blur="menuItem.editable=false;"
                    />
                    <q-icon
                      name="clear"
                      class="cursor-pointer"
                      @click="deletefunc(menuItem.name)"
                      style="width:35px;height:35px;"
                    />
                    <q-icon
                      name="edit"
                      class="cursor-pointer"
                      @click="menuItem.editable=true;"
                      style="width:35px;height:32px;margin-left:-5px;"
                    />
                  </q-item>
                </q-list>
                <q-btn
                  color="primary"
                  icon-right="img:statics/add.png"
                  label="添加"
                  style="width:100%;"
                  push
                >
                  <q-popup-proxy>
                    <q-banner>
                      <q-input v-model="addInstance.label" label="角色名" />
                      <q-btn
                        color="primary"
                        label="添加"
                        style="width:60px;height:30px;font-size:12px;float:right;margin-top:10px;"
                        @click="addfunc"
                      ></q-btn>
                    </q-banner>
                  </q-popup-proxy>
                </q-btn>
              </q-scroll-area>
            </q-drawer>

            <q-page-container>
              <q-page padding>
                <div class="clearfix buttons" v-show="model=='two'">
                  <div
                    class="q-gutter-sm"
                    v-for="(s,i) in selectedButton"
                    :key="i"
                    style="float:left;"
                  >
                    <q-btn
                      v-if="s.selected==true"
                      color="primary"
                      :label="s.label"
                      @click="changeType(s)"
                    />
                    <q-btn
                      v-else
                      color="white"
                      text-color="black"
                      :label="s.label"
                      @click="changeType(s)"
                    />
                  </div>
                </div>
                <div
                  class="q-gutter-sm"
                  v-for="(item,index) in (model=='one'? userTypeOne:selectedTypeFunc(userTypeTwo))"
                  :key="index"
                  style="float:left;"
                >
                  <q-checkbox
                    v-model="item.val"
                    :label="item.name"
                    style="max-width:250px;min-width:200px;"
                  />
                </div>
                <q-btn color="primary" label="保存修改" class="saveButton"></q-btn>
              </q-page>
            </q-page-container>
          </q-layout>
        </div>
        <div v-else-if="model=='three'" >
          <assignmentPermission></assignmentPermission>
        </div>
      </q-page-container>
    </q-layout>
  </div>
</template>



<script>

import assignmentPermission from 'components/assignmentPermission.vue'
// 假数据

var funcList = [
  {
    name: "物流部",
    editable: false,
    userTypeList: [
      {
        name: "进销货查询",
        val: false
      },
      {
        name: "设定系统参数",
        val: false
      },
      {
        name: "权限分配",
        val: false
      },
      {
        name: "订单查询",
        val: false
      },
      {
        name: "进销货查询",
        val: false
      },
      {
        name: "设定系统参数",
        val: false
      },
      {
        name: "权限分配",
        val: false
      },
      {
        name: "订单查询",
        val: false
      },
      {
        name: "进销货查询",
        val: false
      },
      {
        name: "设定系统参数",
        val: false
      },
      {
        name: "权限分配",
        val: false
      },
      {
        name: "订单查询",
        val: false
      }
    ]
  },
  {
    name: "客户",
    editable: false,
    userTypeList: [
      {
        name: "进销货查询",
        val: false
      },
      {
        name: "设定系统参数",
        val: false
      },
      {
        name: "权限分配",
        val: false
      },
      {
        name: "订单查询",
        val: false
      }
    ]
  }
];

var roleList = [
  {
    name: "中真",
    editable: false,
    userTypeList: [
      {
        name: "仓库1",
        val: false,
        label: "其他"
      },
      {
        name: "仓库2",
        val: false,
        label: "自营"
      },
      {
        name: "仓库3",
        val: false,
        label: "加盟"
      },
      {
        name: "仓库1",
        val: false,
        label: "其他"
      },
      {
        name: "仓库2",
        val: false,
        label: "自营"
      },
      {
        name: "仓库3",
        val: false,
        label: "加盟"
      },
      {
        name: "仓库1",
        val: false,
        label: "其他"
      },
      {
        name: "仓库2",
        val: false,
        label: "自营"
      },
      {
        name: "仓库3",
        val: false,
        label: "加盟"
      },
      {
        name: "仓库1",
        val: false,
        label: "其他"
      },
      {
        name: "仓库2",
        val: false,
        label: "自营"
      },
      {
        name: "仓库3",
        val: false,
        label: "加盟"
      }
    ]
  },
  {
    name: "合德",
    editable: false,
    userTypeList: [
      {
        name: "仓库1",
        val: false,
        label: "其他"
      },
      {
        name: "仓库2",
        val: false,
        label: "自营"
      },
      {
        name: "仓库3",
        val: false,
        label: "加盟"
      }
    ]
  }
];

export default {
  components:{
    assignmentPermission
  },
  data() {
    return {
      

      // 角色权限切换
      model: "one", //选择功能角色设定权限 或者 操作角色设定权限
      funcIndex: 0, //选择第几个功能
      userTypeOne: [
        {
          name: "进销货查询",
          val: false
        },
        {
          name: "设定系统参数",
          val: false
        },
        {
          name: "权限分配",
          val: false
        },
        {
          name: "订单查询",
          val: false
        },
        {
          name: "进销货查询",
          val: false
        },
        {
          name: "设定系统参数",
          val: false
        },
        {
          name: "权限分配",
          val: false
        },
        {
          name: "订单查询",
          val: false
        },
        {
          name: "进销货查询",
          val: false
        },
        {
          name: "设定系统参数",
          val: false
        },
        {
          name: "权限分配",
          val: false
        },
        {
          name: "订单查询",
          val: false
        }
      ],
      userTypeTwo: [
        {
          name: "仓库1",
          val: false,
          label: "其他"
        },
        {
          name: "仓库2",
          val: false,
          label: "自营"
        },
        {
          name: "仓库3",
          val: false,
          label: "加盟"
        },
        {
          name: "仓库1",
          val: false,
          label: "其他"
        },
        {
          name: "仓库2",
          val: false,
          label: "自营"
        },
        {
          name: "仓库3",
          val: false,
          label: "加盟"
        },
        {
          name: "仓库1",
          val: false,
          label: "其他"
        },
        {
          name: "仓库2",
          val: false,
          label: "自营"
        },
        {
          name: "仓库3",
          val: false,
          label: "加盟"
        },
        {
          name: "仓库1",
          val: false,
          label: "其他"
        },
        {
          name: "仓库2",
          val: false,
          label: "自营"
        },
        {
          name: "仓库3",
          val: false,
          label: "加盟"
        }
      ],
      // 添加功能
      addInstance: {
        label: ""
      },

      // 查找功能
      searchfcunText: "",

      // 编辑功能

      // 选择类型
      selectedButton: [
        { label: "所有", val: "all", selected: true },
        { label: "自营", val: "selfSupport", selected: false },
        { label: "加盟", val: "joinIn", selected: false },
        { label: "其他", val: "others", selected: false }
      ],
      selectedType: "所有",

      left: false,
      val: false,
      userType: "",
      drawer: false,
      funcList,
      roleList
    };
  },
  methods: {
    // 类型选择
    changeType(s) {
      s.selected = true;
      this.selectedType = s.label;
      for (let i = 0; i < this.selectedButton.length; i++) {
        if (this.selectedButton[i] != s) {
          this.selectedButton[i].selected = false;
        }
      }
    },
    selectedTypeFunc(list) {
      // console.
      if (this.selectedType === "所有") return list;
      else {
        return list.filter(item => {
          if (item.label.includes(this.selectedType)) {
            return item;
          }
        });
      }
    },

    // 角色权限切换
    changefunction(index) {
      this.funcIndex = index;
      if (this.model === "one") {
        this.userTypeOne = funcList[index].userTypeList;
      } else {
        this.userTypeTwo = roleList[index].userTypeList;
        // changeType(this.selectedButton[0]);
      }
    },

    // 添加功能
    addfunc() {
      if (this.model == "one" && this.addInstance.label != "") {
        var instance = {
          name: this.addInstance.label,
          editable: false,
          userTypeList: [
            {
              name: "进销货查询",
              val: false
            },
            {
              name: "设定系统参数",
              val: false
            },
            {
              name: "权限分配",
              val: false
            },
            {
              name: "订单查询",
              val: false
            }
          ]
        };
        this.funcList.push(instance);
        this.addInstance.label = "";
        alert("添加成功");
      } else if (this.model === "two") {
        var instance = {
          name: this.addInstance.label,
          editable: false,
          userTypeList: [
            {
              name: "仓库1",
              val: false,
              label: "其他"
            },
            {
              name: "仓库2",
              val: false,
              label: "自营"
            },
            {
              name: "仓库3",
              val: false,
              label: "加盟"
            }
          ]
        };
        this.roleList.push(instance);
        this.addInstance.label = "";
        alert("添加成功");
      }
    },

    // 删除功能
    deletefunc(name) {
      if (this.model === "one") {
        var index = this.funcList.findIndex(item => {
          if (item.name == name) {
            return true;
          }
        });
        this.funcList.splice(index, 1);
      } else if (this.model === "two") {
        var index = this.roleList.findIndex(item => {
          if (item.name == name) {
            return true;
          }
        });
        this.roleList.splice(index, 1);
      }
    },
    // 查找功能
    searchfunc(list) {
      return list.filter(item => {
        if (item.name.includes(this.searchfcunText)) {
          return item;
        }
      });
    },

    change() {
      console.log(this.model);
      if (this.model === "one") {
        this.userType = this.userTypeOne;
      } else if (this.model === "two") {
        this.userType = this.userTypeTwo;
      }
    }
  },
  directives: {
    // 编辑功能
    myfocus: {
      inserted: function(el) {
        el.focus();
      }
    }
  }
};
</script>

<style>
/* .searchInputTwo {
  position: fixed;
  top: 1%;
  right: 2%;
  background-color: white;
  border: 1px solid #eee;
  border-radius: 5px;
  width: 20%;
} */
.selectType {
  position: relative;
  top: -16px;
  left: -16px;
}
.selectType button {
  font-size: 12px;
}

.saveButton {
  position: fixed;
  bottom: 5px;
  right: 5px;
}
.funcIcon {
  float: left;
  width: 15px;
  height: 15px;
  margin: 8px;
}
.menuItemFunction {
  width: 100%;
  padding-left: 0.5em;
  border: 0;
  border-radius: 5px;
  background-color: white;
}
.menuItemFunction:focus {
  outline: none;
}

/* 清除浮动 */

.clearfix:after {
  content: "";
  display: block;
  height: 0;
  clear: both;
  visibility: hidden;
}
.clearfix {
  *zoom: 1;
}
.buttons {
  margin-bottom: 15px;
}
</style>