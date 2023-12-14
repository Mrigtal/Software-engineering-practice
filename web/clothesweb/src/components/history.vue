<template>
  <div>
    <h1>购买历史</h1>
    <!-- 在表格或列表中展示购买历史 -->
    <el-table :data="purchaseHistory" style="width: 100%">
      <el-table-column
        prop="shop.shopname"
        label="商品名称"
        align="center"
      ></el-table-column>
      <el-table-column label="商品图片" align="center">
        <template slot-scope="scope">
          <el-popover placement="top-start" title="" trigger="hover">
            <div
              v-for="(image, index) in processImageUrls(
                scope.row.shop.shoppoto
              )"
              :key="index"
              style="float: left"
            >
              <img :src="image" alt="" style="width: 150px; height: 150px" />
              &nbsp;
            </div>
            <!-- 预览图 -->
            <img
              slot="reference"
              :src="processImageUrls(scope.row.shop.shoppoto)[0]"
              style="width: 50px; height: 50px"
            />
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        prop="username"
        label="购买日期"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="shop.count"
        label="数量"
        align="center"
      ></el-table-column>
      <!-- 根据需要添加更多列 -->
    </el-table>
  </div>
</template>

<script>
export default {
  name: "HistoryPage",
  data() {
    return {
      purchaseHistory: [],
    };
  },
  mounted() {
    this.getList();
  },
  methods: {
    processImageUrls(shoppoto) {
      if (!shoppoto) return [];
      const baseUrl = "http://localhost:8542/file/";
      return shoppoto.split(",").map((img, index) => {
        if (index == 0) {
          return img;
        } else {
          return img;
        }
      });
    },
    getList() {
      this.axios
        .get(
          "http://localhost:8542/sysXiadan/query/" +
            SessionStorage.get("USERNAME"),
          {}
        )
        .then((resp) => {
          console.log(resp.data.data);
          this.purchaseHistory = resp.data.data;
        });
    },
  },
};
</script>

<style scoped>
/* 在这里为您的历史页面添加样式 */
</style>
