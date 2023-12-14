<template>
  <div class="login">
    <div class="login_form">
      <p>在线购物系统</p>
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="登录" name="first">
          <el-form :model="loginForm" :rules="rules" ref="loginForm">
            <el-form-item label="" prop="username" class="elItem">
              <el-input
                type="text"
                autocomplete="off"
                v-model="loginForm.username"
                prefix-icon="el-icon-user-solid"
                placeholder="请输入用户名"
              ></el-input>
            </el-form-item>
            <el-form-item label="" prop="password">
              <el-input
                type="password"
                autocomplete="off"
                v-model="loginForm.password"
                prefix-icon="el-icon-lock"
                placeholder="请输入密码"
              ></el-input>
            </el-form-item>
            <el-form-item class="btns">
              <el-button type="primary" @click="goToLogin">登录</el-button>
              <el-button @click="resetLoginForm">重置</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="注册" name="second">
          <el-form
            :model="registerForm"
            :rules="registerRules"
            ref="registerForm"
          >
            <!-- 注册表单内容 -->
            <el-form-item label="" prop="username">
              <el-input
                type="text"
                autocomplete="off"
                v-model="registerForm.username"
                prefix-icon="el-icon-user-solid"
                placeholder="请输入用户名"
              ></el-input>
            </el-form-item>
            <el-form-item label="" prop="password">
              <el-input
                type="password"
                autocomplete="off"
                v-model="registerForm.password"
                prefix-icon="el-icon-lock"
                placeholder="请输入密码"
              ></el-input>
            </el-form-item>
            <el-form-item label="" prop="phone">
              <el-input
                type="text"
                autocomplete="off"
                v-model="registerForm.phone"
                placeholder="请输入手机号"
              ></el-input>
            </el-form-item>
            <el-form-item label="" prop="address">
              <el-input
                type="text"
                autocomplete="off"
                v-model="registerForm.address"
                placeholder="请输入地址"
              ></el-input>
            </el-form-item>
            <el-form-item class="btns">
              <el-button type="primary" @click="goToRegister">注册</el-button>
              <el-button @click="resetRegisterForm">重置</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    var validateAccount = (rule, value, callback) => {
      if (value === "") {
        return callback(new Error("账号不能为空"));
      }
    };
    var validatePassword = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      }
    };
    return {
      registerForm: {
        username: "",
        password: "",
        phone: "",
        address: "",
      },
      registerRules: {
        username: [
          // 校验规则
        ],
        password: [
          // 校验规则
        ],
      },
      loginForm: {
        username: "",
        password: "",
      },
      query: {
        role: "",
        userId: "",
      },
      activeName: "first", //默认显示登录页面
      rules: {
        username: [
          {
            validator: validateAccount,
            trigger: "blur",
          },
        ],
        password: [
          {
            validator: validatePassword,
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    //固定的账户密码判断实现简单的登录跳转功能，只能测试用
    goToRegister() {
      // 注册逻辑
      this.axios
        .post("http://localhost:8542/sysUser/register/", this.registerForm)
        .then((resp) => {
          let data = resp.data;
          console.log(data.msg, "返回结果");
          if (data.msg) {
            this.$message({
              message: "恭喜你，操作成功",
              type: "success",
            });
            // this.$router.push({path: '/'});
            this.$router.push({ path: "/home" });
            SessionStorage.set("USERNAME", this.registerForm.username);
          } else {
            this.$message({
              message: data.message,
              type: "error",
            });
          }
        });
    },
    goToLogin() {
      this.axios
        .post("http://localhost:8542/sysUser/login/", this.loginForm)
        .then((resp) => {
          let data = resp.data;
          console.log(data, "返回结果");
          this.query.role = data.content.role;
          this.query.userId = data.content.id;
          if (data.success) {
            this.$message({
              message: "恭喜你，操作成功",
              type: "success",
            });
            // this.$router.push({path: '/'});
            this.$router.push({ path: "/home" });
            SessionStorage.set("ROLE", this.query.role);
            SessionStorage.set("USERID", this.query.userId);
            SessionStorage.set("USERNAME", this.loginForm.username);
          } else {
            this.$message({
              message: data.message,
              type: "error",
            });
          }
        });
    },
    resetLoginForm() {
      this.$refs["loginForm"].resetFields();
    },
    resetRegisterForm() {
      this.$refs["registerForm"].resetFields();
    },
    handleClick() {},
  },
};
</script>

<style scoped lang='less'>
.login {
  width: 100%;
  height: 100vh;
  background-image: url("../assets/background.png"); //背景图
  background-size: 100% 100%;
  background-position: center center;
  overflow: auto;
  position: relative;
  .login_form {
    width: 400px;
    height: 360px;
    position: absolute;
    left: 50%;
    top: 50%;
    margin-left: -200px;
    margin-top: -150px;
    padding: 10px;
    background: #fff;
    border-radius: 10px;
    box-shadow: 0 0 10px #ddd;
    .btns {
      display: flex;
      justify-content: flex-end;
    }
  }
  p {
    font-size: 24px;
    text-align: center;
    font-weight: 600;
  }
}
</style>
