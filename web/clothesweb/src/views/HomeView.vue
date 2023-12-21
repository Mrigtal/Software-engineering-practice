<template>
  <div class="home">
    <el-container class="home-container">
      <!--    头部-->
      <el-header>
        <div>
          <span> 购衣网 </span>
          <el-button type="info" @click="qiantai()">返回前台</el-button>
        </div>

        <el-button type="info" @click="tuichu()">退出登录</el-button>
      </el-header>
      <el-container>
        <!--    左侧菜单栏-->

        <el-aside width="200px">
          <!--        侧边栏菜单-->
          <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
            unique-opened
            router
            :default-active="$router.currentRoute.path"
          >
            <!--          一级菜单-->
            <!--          防止index="1"  每一个都打开  将它改成动态的 :index="item.id"-->
            <el-submenu
              :index="item.id.toString()"
              v-for="item in menulist"
              :key="item.id"
            >
              <!--            一级菜单模版区域-->
              <template slot="title">
                <!--              图标-->
                <i :class="iconsObj[item.id]"></i>
                <!--              文本-->
                <span>{{ item.authName }}</span>
              </template>
              <!--            二级菜单-->
              <el-menu-item
                :index="'/' + subItem.path"
                v-for="subItem in item.children"
                :key="subItem.id"
              >
                <!--            二级菜单模版区域-->
                <!--              图标-->
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <i class="el-icon-menu"></i>
                <!--              文本-->
                <span>{{ subItem.authName }}</span>
              </el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "HomeView",
  data() {
    return {
      //左侧菜单栏
      menulist: [],
      //一级菜单图标库
      iconsObj: {
        1: "el-icon-user-solid",
        2: "el-icon-s-order",
        3: "el-icon-star-on",
        4: "el-icon-upload",
        5: "el-icon-phone",
      },
      dialogFormVisible: false,
      form1: {},
      form: {},
    };
  },
  created() {
    let role = SessionStorage.get("ROLE");
    console.log(role == undefined, "role");
    if (role == undefined) {
      role = 1;
    }
    this.getMenulist(role);
  },
  methods: {
    xiugai() {
      this.form = {};
      this.dialogFormVisible = true;
    },
    getMenulist(role) {
      this.axios
        .get(" http://124.221.174.197:8542/sysMenu/getMenulist", {
          params: {
            role: role,
          },
        })
        .then((resp) => {
          console.log(resp.data.content, "路由");
          this.menulist = resp.data.content;
        });
    },
    handleOpen(key, keyPath) {
      console.log(key + "1", keyPath + "2");
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    //链接激活状态
    // saveNavState(activePath){
    //   this.activePath = activePath;
    // }
    tuichu() {
      SessionStorage.clearAll();
      this.$router.push("/");
    },
    qiantai() {
      this.$router.push("/");
    },
  },
};
</script>
<style scoped>
.el-header {
  background-color: #545c64;
  /*弹性布局*/
  display: flex;
  /* 左右贴边布局 */
  justify-content: space-between;
  /*让el-head距离左边没有空白/*/
  /*padding-left: 0;*/
  /*水平居中*/
  align-items: center;
  /*将文本颜色变成白色*/
  color: #fff;
  /*文字大小修改*/
  font-size: 30px;
  /*margin-bottom: 10px;*/
}
.el-aside {
  background-color: #545c64;
}
.el-menu {
  border-right: none;
}
.el-main {
  background-color: #eaedf1;
}
.home-container {
  height: 100vh;
}
</style>