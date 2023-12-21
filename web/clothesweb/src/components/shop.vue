<template>
  <div>
    <!--    面包屑导航区域-->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>发布商品</el-breadcrumb-item>
    </el-breadcrumb>
    <br />

    <!--    卡片视图区域-->
    <el-card class="box-card">
      <el-input
        v-model="query.productName"
        style="width: 200px"
        placeholder="请输入商品名称"
      ></el-input>
      <el-select v-model="query.status" placeholder="请选择商品状态">
        <el-option label="上架" value="0"></el-option>
        <el-option label="冻结" value="1"></el-option>
        <el-option label="下架" value="2"></el-option>
      </el-select>
      <el-button @click="getList()" type="primary">查询</el-button>
      <el-button @click="handleCreate()" type="primary">新增</el-button>
      <el-button @click="handleCreate1()" type="primary">修改密码</el-button>
      <el-button @click="openCategoryManagement()" type="primary"
        >分类管理</el-button
      >

      <!-- 分类管理弹窗 -->
      <el-dialog
        title="分类管理"
        width="60%"
        :visible.sync="dialogFormVisible2"
      >
        <el-form :model="form2">
          <el-form-item label="选择分类">
            <el-select
              v-model="form2.selectedCategory"
              placeholder="请选择分类"
            >
              <el-option
                v-for="category in categories"
                :key="category.id"
                :label="category.name"
                :value="category.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="修改分类名称">
            <el-input
              v-model="form2.newCategoryName"
              placeholder="请输入新的分类名称"
              @input="someMethod"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="addCategory()">新增</el-button>
            <el-button type="success" @click="modifyCategory()">修改</el-button>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible2 = false">取 消</el-button>
        </div>
      </el-dialog>

      <el-table
        :data="tableData"
        style="width: 100%"
        :cell-style="{ 'text-align': 'center' }"
      >
        <el-table-column prop="shopname" label="商品名称" width="180">
        </el-table-column>
        <el-table-column
          prop="count"
          label="库存数量"
          width="180"
        ></el-table-column>
        <el-table-column prop="shoppice" label="商品价格"> </el-table-column>
        <el-table-column prop="shopmessage" label="商品详情">
          <template slot-scope="scope">
            <div class="overflow-content">{{ scope.row.shopmessage }}</div>
          </template>
        </el-table-column>
        <el-table-column label="商品图片" prop="shoppoto" style="align: center">
          <template slot-scope="scope">
            <el-popover placement="top-start" title="" trigger="hover">
              <div
                v-for="(image, index) in processImageUrls(scope.row.shoppoto)"
                :key="index"
                style="float: left"
              >
                <img :src="image" alt="" style="width: 150px; height: 150px" />
                &nbsp;
              </div>
              <!-- 预览图 -->
              <img
                slot="reference"
                :src="processImageUrls(scope.row.shoppoto)[0]"
                style="width: 50px; height: 50px"
              />
            </el-popover>
          </template>
        </el-table-column>

        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.row)"
              >编辑</el-button
            >
            <el-button size="mini" type="danger" @click="handleDelete(scope)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="this.query.page"
        :page-sizes="[5, 10, 20, 30]"
        :page-size="this.query.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="numericTotal"
      >
      </el-pagination>
    </el-card>

    <el-dialog title="商品信息" width="60%" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="商品名称">
          <el-input
            style="width: 90%"
            v-model="form.shopname"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="商品价格">
          <el-input
            style="width: 90%"
            v-model="form.shoppice"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="修改状态">
          <el-select v-model="form.status" placeholder="请选择商品状态">
            <el-option label="上架" value="0"></el-option>
            <el-option label="冻结" value="1"></el-option>
            <el-option label="下架" value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="分类">
          <el-select v-model="form.categoryId" placeholder="请选择分类">
            <el-option
              v-for="category in categories"
              :key="category.id"
              :label="category.name"
              :value="category.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商品详情">
          <mavon-editor
            v-model="form.shopmessage"
            ref="md"
            @change="change"
            style="min-height: 600px"
          />
        </el-form-item>
        <el-form-item label="商品图片:">
          <el-upload
            class="avatar-uploader"
            action="http://124.221.174.197:8542/file"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
          >
            <div
              v-if="shoppotoUrls.length"
              style="display: flex; flex-wrap: wrap"
            >
              <img
                v-for="(image, index) in shoppotoUrls"
                :key="index"
                :src="image"
                class="avatar"
                style="width: 150px; height: 150px; padding: 10px"
              />
            </div>
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm()">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="修改密码" width="40%" :visible.sync="dialogFormVisible1">
      <el-form :model="form1">
        <el-form-item label="新密码">
          <el-input
            style="width: 90%"
            v-model="form1.password"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="submitForm1()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "shop",
  data() {
    return {
      total: 0,
      dialogFormVisible2: false,
      categories: [], // 存储分类列表
      form2: {
        selectedCategory: null, // 选中的分类 ID
        newCategoryName: "", // 新的分类名称
      },
      tableData: [],
      imgdata: [],
      total: 0,
      query: {
        page: 1,
        size: 6,
        name: "",
        phone: "",
        createUserId: "",
      },
      allClasses: [
        {
          //班级选择器
          id: "", //存储班级id
          typeName: "", //存储班级的名称
        },
      ],
      dialogFormVisible: false,
      dialogFormVisible1: false,
      form: {
        shopname: "",
        shoppice: "",
        status: "",
        shopmessage: "",
        shoppoto: "",
        id: "",
        name: "",
        age: "",
        phone: "",
        sex: "",
        city: "",
        categoryId: null, // 新增的分类 ID 字段
      },
      form1: {},
    };
  },
  computed: {
    shoppotoUrls() {
      // 检查 this.form.shoppoto 是否为空或只包含空白字符
      if (!this.form.shoppoto || !this.form.shoppoto.trim()) {
        return [];
      }
      return this.form.shoppoto.split(",");
    },
  },

  mounted() {
    let userid = SessionStorage.get("USERID");
    this.query.createUserId = userid;
    this.getList();
    this.fetchCategories();
    this.loadAllClasses(); //页面加载时调用
  },
  methods: {
    handleCategoryChange(value) {
      this.form2.selectedCategory = value;
    },
    modifyCategory() {
      this.axios
        .put("http://124.221.174.197:8542/sysCategory/", {
          id: this.form2.selectedCategory,
          name: this.form2.newCategoryName, // 新的分类名称
        })
        .then((response) => {
          // 请求成功后的逻辑
          this.categories = response.data.data; // 假设响应数据是分类数组
          console.log(this.categories);
          this.dialogFormVisible2 = false;
          // 显示成功消息
          this.$message({
            message: "分类修改成功",
            type: "success",
          });
          this.form2 = {};
        })
        .catch((error) => {
          // 请求失败后的逻辑
          console.error("Error updating category:", error);

          // 显示错误消息
          this.$message.error("分类修改失败：" + error.message);
        });
      this.fetchCategories();
    },
    someMethod(value) {
      this.form2.newCategoryName = value;
      // 处理输入变化的逻辑
    },
    fetchCategories() {
      // 从服务器加载分类列表
      this.axios
        .get("http://124.221.174.197:8542/sysCategory/")
        .then((response) => {
          this.categories = response.data.data; // 假设响应数据是分类数组
          console.log(this.categories);
        })
        .catch((error) => {
          console.error("Error fetching categories:", error);
        });
    },
    getList() {
      this.axios
        .get("http://124.221.174.197:8542/sysShop/getlist", {
          params: {
            page: this.query.page,
            size: this.query.size,
            shopname: this.query.productName, // 更改为 query.productName
            status: this.query.status,
          },
        })
        .then((resp) => {
          this.tableData = resp.data.content.map((item) => {
            if (item.shoppoto) {
              let imageNames = item.shoppoto.split(",");
              item.shoppoto = imageNames
                // .map((img, index) =>
                //   index == 0 ? img : `http://124.221.174.197:8542/file/${img}`
                // )
                .join(",");
            }
            return item;
          });
          this.total = resp.data.content.total;
        });
    },
    openCategoryManagement() {
      this.dialogFormVisible2 = true;
      this.fetchCategories();
    },

    addCategory() {
      // 处理新增分类的逻辑
      this.axios
        .post("http://124.221.174.197:8542/sysCategory/", {
          name: this.form2.newCategoryName, // 新的分类名称
        })
        .then((response) => {
          this.categories = response.data.data; // 假设响应数据是分类数组
          console.log(this.categories);
          this.dialogFormVisible2 = false;
          // 显示成功消息
          this.$message({
            message: "分类新增成功",
            type: "success",
          });
          this.form2 = {};
        })
        .catch((error) => {
          // 请求失败后的逻辑
          console.error("Error updating category:", error);

          // 显示错误消息
          this.$message.error("分类修改失败：" + error.message);
        });
      this.fetchCategories();
    },
    processImageUrls(shoppoto) {
      if (!shoppoto) return [];
      const baseUrl = "http://124.221.174.197:8542/file/";
      return shoppoto.split(",").map((img, index) => {
        if (index == 0) {
          return img;
        } else {
          return img;
        }
      });
    },
    loadAllClasses() {
      this.allClasses = []; //将班级信息先置空
      this.axios
        .get("http://124.221.174.197:8542/sysShop/getlist", {
          params: {
            page: this.query.page,
            size: this.query.size,
          },
        })
        .then((res) => {
          console.log(res.data.content);
          let tmp = res.data.content; //将表单信息提
          // console.log(tmp, "tmp");
          for (let i = 0; i < tmp.length; i++) {
            //循环，表单的个数即为tmp的长度
            this.allClasses.push({ name: tmp[i].typeName, id: tmp[i].id });
          }
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
    handleDelete(scope) {
      let id = scope.row.id;
      this.axios
        .delete("http://124.221.174.197:8542/sysShop/delete/" + id)
        .then((resp) => {
          let data = resp.data;
          if (data.success) {
            this.getList();
            this.$message({
              message: "恭喜你，删除成功",
              type: "success",
            });
          }
        });
    },
    handleCreate() {
      this.form = {
        shopname: "",
        shoppice: "",
        status: "",
        shopmessage: "",
        shoppoto: "",
      };
      this.imgdata = []; // 重置图片数据
      this.dialogFormVisible = true;
    },

    handleCreate1() {
      this.form1 = {};
      this.dialogFormVisible1 = true;
    },
    handleEdit(row) {
      this.form = row;
      this.dialogFormVisible = true;
    },
    submitForm1() {
      this.form1.id = this.query.createUserId;
      this.axios
        .post("http://124.221.174.197:8542/sysUser/save/", this.form1)
        .then((resp) => {
          let data = resp.data;
          if (data.success) {
            this.dialogFormVisible = false;
            this.form = {};
            this.getList();
            this.$message({
              message: "恭喜你，操作成功",
              type: "success",
            });
          }
        });
    },
    submitForm() {
      // 添加 createUserId 到提交的表单数据
      const dataToSubmit = {
        ...this.form,
        createUserId: this.query.createUserId,
        categoryId: this.form.categoryId, // 确保包括分类 ID
      };

      // 发送 POST 请求到服务器
      this.axios
        .post("http://124.221.174.197:8542/sysShop/save/", dataToSubmit)
        .then((resp) => {
          let data = resp.data;
          if (data.success || data.msg == "操作成功") {
            // 关闭对话框并重置表单
            this.dialogFormVisible = false;
            this.resetForm();
            // 重新加载商品列表
            this.getList();
            // 显示成功消息
            this.$message({
              message: "操作成功",
              type: "success",
            });
          }
        })
        .catch((error) => {});
    },
    handleAvatarSuccess(res, file) {
      // 添加新图片文件名到 imgdata 数组
      this.imgdata.push(`http://124.221.174.197:8542/file/${res.data}`);

      // 格式化 form.shoppoto 字符串
      if (this.imgdata.length > 0) {
        // 对于第一张图片，包含完整 URL
        this.form.shoppoto = this.imgdata[0];

        // 如果有更多图片，只添加文件名
        if (this.imgdata.length > 1) {
          this.form.shoppoto += "," + this.imgdata.slice(1).join(",");
        }
      }

      this.$forceUpdate();
    },

    /**
     * 上传校验
     * @param file
     */
    beforeAvatarUpload(file) {
      console.log(file);
      const isPng = file.type === "image/jpeg";
      if (!isPng) {
        this.$message.error("上传的图片只能是png格式哟！");
      }
      return isPng;
    },
  },
};
</script>


<style >
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9 !important;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}

.avatar-uploader .avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 78px;
  line-height: 78px;
  text-align: center;
}

.avatar-uploader img {
  width: 178px;
  height: 178px;
  display: block;
}
.overflow-content {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-line-clamp: 3;
}
th > div {
  text-align: center;
}
</style>