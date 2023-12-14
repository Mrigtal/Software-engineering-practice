<template>
  <div>
    <!--    面包屑导航区域-->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>下单管理</el-breadcrumb-item>
      <el-breadcrumb-item>下单信息</el-breadcrumb-item>
    </el-breadcrumb>
    <br />

    <!--    卡片视图区域-->
    <el-card class="box-card">
      <el-input
        v-model="query.xiadanname"
        style="width: 200px"
        placeholder="请输入下单人姓名"
      ></el-input>
      <el-button @click="getList()" type="primary">查询</el-button>

      <el-table :data="tableData" style="width: 100%">
        <el-table-column
          prop="shopid"
          label="商品id"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column prop="xiadanname" label="下单人" align="center">
        </el-table-column>
        <el-table-column prop="xiadanphone" label="下单电话" align="center">
        </el-table-column>
        <el-table-column prop="xiadanmark" label="下单备注" align="center">
        </el-table-column>

        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
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
        :total="this.total"
      >
      </el-pagination>
    </el-card>

    <!-- <el-dialog title="商品信息" width="40%" :visible.sync="dialogFormVisible">
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
        &lt;!&ndash;<el-form-item label="商品类型"
          >&ndash;&gt; &lt;!&ndash;
          <el-input
            style="width: 90%"
            v-model="form.typeId"
            autocomplete="off"
          ></el-input
          >&ndash;&gt; &lt;!&ndash;</el-form-item
        >&ndash;&gt;
        <el-form-item label="商品详情">
          <el-input
            style="width: 90%"
            type="textarea"
            v-model="form.shopmessage"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="商品图片:">
          <el-upload
            class="avatar-uploader"
            action="http://localhost:8542/oss/file/upload?module=shoppoto"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
          >
            <img v-if="form.shoppoto" :src="form.shoppoto" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm()">确 定</el-button>
      </div>
    </el-dialog> -->
  </div>
</template>

<script>
export default {
  name: "shop",
  data() {
    return {
      tableData: [],
      total: 0,
      query: {
        page: 1,
        size: 6,
        name: "",
        phone: "",
        xiadanname: "",
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
      form: {
        id: "",
        name: "",
        age: "",
        phone: "",
        sex: "",
        city: "",
      },
    };
  },
  mounted() {
    let userid = SessionStorage.get("USERID");
    this.query.createUserId = userid;
    console.log(userid, "userid");
    this.getList();
    this.loadAllClasses(); //页面加载时调用
  },
  methods: {
    getList() {
      this.axios
        .get("http://localhost:8542/sysXiadan/getlist", {
          params: {
            page: this.query.page,
            size: this.query.size,
            xiadanname: this.query.xiadanname,
          },
        })
        .then((resp) => {
          console.log(resp);
          this.tableData = resp.data.content.list;
          this.total = resp.data.content.total;
        });
    },

    loadAllClasses() {
      this.allClasses = []; //将信息先置空
      this.axios
        .get("http://localhost:8542/sysXiadan/getlist", {
          params: {
            page: this.query.page,
            size: this.query.size,
            xiadanname: this.query.xiadanname,
          },
        })
        .then((resp) => {
          console.log(resp);
          this.tableData = resp.data.content.list;
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
    handleDelete(scope) {
      let id = scope.row.id;
      this.axios
        .delete("http://localhost:8542/sysXiadan/delete/" + id)
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
      this.form = {};
      this.dialogFormVisible = true;
    },
    handleEdit(row) {
      this.form = row;
      this.dialogFormVisible = true;
    },
    // submitForm(){
    //   this.form.createUserId = this.query.createUserId;
    //   this.axios.post('http://localhost:8542/sysShop/save/',this.form).then((resp)=>{
    //     let data = resp.data;
    //     if(data.success){
    //       this.dialogFormVisible = false;
    //       this.form = {};
    //       this.getList();
    //       this.$message({
    //         message: '恭喜你，操作成功',
    //         type: 'success'
    //       });
    //     }
    //   })
    // },
    handleAvatarSuccess(res, file) {
      console.log(res, "oss1");
      this.form.shoppoto = res.content;
      console.log(res, "oss");
      console.log(this.form.shoppoto, "oss12312");
      //强制重新渲染
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
</style>