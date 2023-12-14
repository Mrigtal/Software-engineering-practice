<template>
  <div>
    <h1>购买历史</h1>
    <!-- 在表格中展示购买历史 -->
    <el-table :data="purchaseHistory" style="width: 100%">
      <el-table-column prop="id" label="ID" align="center"></el-table-column>
      <el-table-column
        prop="username"
        label="用户名"
        align="center"
      ></el-table-column>
      <el-table-column prop="role" label="角色" align="center">
        <template v-slot:default="scope">
          <!-- 当角色为1时显示el-tag，否则显示原始角色值 -->
          <el-tag v-if="scope.row.role == 1">客户</el-tag>
          <span v-else>{{ scope.row.role }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="phone"
        label="电话"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="address"
        label="地址"
        align="center"
      ></el-table-column>
      <!-- 根据需要添加更多居中的列 -->
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
    getList() {
      this.axios
        .get("http://localhost:8542/sysUser/list/custom/", {})
        .then((resp) => {
          console.log(resp.data.data);
          this.purchaseHistory = resp.data.data.map((item) => ({
            ...item,
            purchaseDate: item.username, // 假设购买日期保存在 username 字段中，需要根据实际情况修改
          }));
        });
    },
  },
};
</script>

<style scoped>
</style>
