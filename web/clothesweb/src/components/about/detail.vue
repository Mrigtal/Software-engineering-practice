<template>
  <div id="all">
    <the-header></the-header>
    <div style="clear: both"></div>
    <div id="content">
      <div class="a1">
        <img class="big" v-if="imgUrls.length > 0" :src="imgUrls[0]" />
        <div class="a11">
          <ul>
            <li v-for="(url, index) in imgUrls" :key="index">
              <img :src="url" @click="updateBigImageUrl(url)" />
            </li>
          </ul>
        </div>
      </div>
      <div class="a2">
        <h2>{{ course.shopname }}</h2>
        <div class="a21">
          <div class="a211">
            <span class="color">售价</span>
            <span class="prices">￥{{ course.shoppice }}</span>
          </div>
          <div class="a22">
            <p></p>
            <span class="color">备注</span>
            <span class="neir">{{ course.shopname }}</span>
          </div>
          <div class="a25">
            <button class="a25color1" v-if="this.course.status == '1'">
              已冻结
            </button>
            <button
              class="a25color1"
              v-if="this.course.status == '0'"
              @click="handleCreate()"
            >
              我要购买
            </button>
          </div>
        </div>
      </div>
    </div>
    <div style="width: 1200px; margin: 0 auto">
      <h2 class="color">商品详情</h2>
      <mavon-editor
        v-model="course.shopmessage"
        :subfield="false"
        :boxShadow="false"
        defaultOpen="preview"
        :toolbarsFlag="false"
      />
    </div>
    <div style="clear: both"></div>

    <el-dialog title="填写信息" :visible.sync="dialogVisible" width="30%">
      <el-form :model="form">
        <el-form-item label="姓名">
          <el-input
            style="width: 90%"
            v-model="form.xiadanname"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input
            style="width: 90%"
            v-model="form.xiadanphone"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input
            style="width: 90%"
            v-model="form.xiadanmark"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import TheHeader from "@/components/about/the-header";

export default {
  components: { TheHeader },
  name: "detail",
  data: function () {
    return {
      imgUrls: [],
      dialogVisible: false,
      dialogVisible1: false,
      formInline: {
        username: "",
        content: "",
        mediaid: "",
      },
      form: {
        username: "",
      },
      total: 0,
      query: {
        page: 1,
        size: 6,
        id: "",
      },
      commentData: [],
      id: "",
      course: {},
      download: "",
      commentList: {},
    };
  },
  mounted() {
    let _this = this;
    _this.query.id = _this.$route.query.id;

    _this.findCourse();
    // _this.getUserList();

    let username = SessionStorage.get("USERNAME");
    this.formInline.username = username;
  },
  methods: {
    submitForm() {
      this.form.shopid = this.query.id;
      console.log(SessionStorage.get("USERNAME"));
      this.form.username = SessionStorage.get("USERNAME");
      this.axios
        .post("http://localhost:8542/sysXiadan/save/", this.form)
        .then((resp) => {
          let data = resp.data;
          if (data.success) {
            this.dialogVisible = false;
            this.form = {};
            this.$message({
              message: "恭喜你，操作成功",
              type: "success",
            });
          }
        });
    },
    updateBigImageUrl(newUrl) {
      if (this.imgUrls.length > 0 && newUrl) {
        // 创建一个新数组，将点击的图片放在第一位
        const updatedUrls = [
          newUrl,
          ...this.imgUrls.filter((url) => url !== newUrl),
        ];
        // 更新 imgUrls 数组
        this.imgUrls = updatedUrls.slice(0, 4); // 保持最多四个 URL
      }
    },
    handleCreate() {
      this.form = {};
      this.dialogVisible = true;
    },
    handleClose() {
      this.dialogVisible = false;
    },
    findCourse() {
      let _this = this;

      this.axios
        .get("http://localhost:8542/sysShop/getlist", {
          params: {
            page: this.query.page,
            size: this.query.size,
            id: this.query.id,
          },
        })
        .then((resp) => {
          this.course = resp.data.content[0];
          console.log(this.course, "this.course");
          this.total = resp.data.content.total;
          if (this.course.shoppoto) {
            this.imgUrls = this.course.shoppoto.split(",").slice(0, 4); // 只保留前四个 URL
          }
        });
    },

    getUserList() {
      this.axios
        .get("http://localhost:8735/comment/getlist", {
          params: {
            page: this.query.page,
            size: this.query.size,
            mediaid: this.query.mediaid,
          },
        })
        .then((resp) => {
          console.log(this.commentList, "this.commentList");
          this.commentList = resp.data.content.list;
          this.total = resp.data.content.total;
        });
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.query.size = val;
      this.getList();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.query.page = val;
      this.getList();
    },
  },
};
</script>

<style scoped>
.all {
  width: 100%;
  height: 810px;
  padding-bottom: 50px;
  margin-top: 80px;
  margin-bottom: 1px;
}
.all .content {
  margin-top: 0px;
  float: left;
}
.all iframe {
  /* border: 2px solid red; */
}
#content {
  /* 	border: 1px solid red; */
  width: 1200px;
  margin: 40px auto;
  display: flex;
  justify-content: space-around;
}
#content .a1 {
  float: left;
  margin-left: 10px;
}
#content .a1 .big {
  /* float: left; */
  width: 450px;
  height: 400px;
}
#content .a11 ul li {
  list-style: none;
  float: left;
  margin: 9px 4px;
}
.a11 ul li img {
  border: 3px white solid;
  width: 80px;
  height: 80px;
}
.a11 ul li img:hover {
  border: 3px #ff4f0d solid;
}
.a2 {
  display: flex;
  flex-direction: column;
  justify-content: space-around;

  flex: 2;
  float: left;
  margin-left: 90px;
}
.a21 .color {
  color: #758188;
}
.a21 .a211 {
  background-color: #f2f5f8;
  color: #758188;
}
.a21 .a211 .prices,
.shic {
  margin-left: 40px;
}
.a21 .a211 .prices {
  color: #ff4f0d;
  font-size: 35px;
  font-weight: bold;
}
.a21 .a211 .prices2 {
  text-decoration: line-through;
}
.a21 .a211 .yis {
  margin-left: 230px;
}
.a22 {
  background-color: #f6f9fb;
}
.a22 span,
p {
  margin-top: 10px;
}
.a22 .neir {
  margin-left: 50px;
}
.a23 {
  margin-top: 10px;
}
.a24 {
  margin-top: 10px;
}
.a24 .bang {
  display: inline-block;
  border: 3px #edf0f5 solid;
  width: 50px;
  line-height: 40px;
  margin-left: 30px;
  text-align: center;
}
.a24 .bang:hover {
  border: 3px #ff4f0d solid;
}
.a25 {
  margin-top: 30px;
}
.a25 button {
  display: inline-block;
  width: 200px;
  height: 60px;
  border-radius: 30px;
  margin-left: 30px;
}
.a25 .a25color1 {
  background-color: #ffeee8;
  color: #ff4f0d;
  font-size: 20px;
  font-weight: bolder;
  border: 1px #ff4f0d solid;
}
.a25 .a25color2 {
  background-color: #ff4f0d;
  color: white;
  font-size: 20px;
  font-weight: bolder;
  border: 1px #ff4f0d solid;
}
/* .all .iheader{
	margin: auto;
} */
</style>
