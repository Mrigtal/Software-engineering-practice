<template>
  <main role="main">
    <section class="jumbotron text-center" style="background: white">
      <div class="container">
        <h1>购衣网</h1>
        <p class="lead text-muted m-3">
          欢迎光临购衣网 你可以在这里挑选发布的衣服,选择之后,耐心等待商家联系。
        </p>
      </div>
    </section>

    <div class="album py-5 bg-light">
      <div class="container">
        <div class="search">
          <el-input
            v-model="query.shopname"
            style="width: 200px"
            placeholder="请输入商品名称"
          ></el-input>
          <el-button type="info" @click="listNew()">查询</el-button>
        </div>
        <div class="row">
          <div v-for="o in news" class="col-md-4">
            <the-course v-bind:course="o"></the-course>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
import TheCourse from "@/components/about/the-course";

export default {
  name: "theIndex",
  components: { TheCourse },
  data: function () {
    return {
      news: [],
      total: 0,
      query: {
        page: 1,
        size: 6,
        title: "",
        name: "",
        phone: "",
        shopname: "",
        createUserId: "",
      },
    };
  },
  mounted() {
    let _this = this;
    _this.listNew();
  },
  methods: {
    getList() {
      this.axios
        .get("http://124.221.174.197:8542/sysShop/getlist", {
          params: {
            page: this.query.page,
            size: this.query.size,
            shopname: this.query.shopname,
          },
        })
        .then((resp) => {
          console.log(resp);
          this.tableData = resp.data.content;
          this.total = resp.data.content.total;
        });
    },
    /**
     * 查询
     */
    listNew() {
      let _this = this;

      this.axios
        .get("http://124.221.174.197:8542/sysShop/getlist", {
          params: {
            page: this.query.page,
            size: this.query.size,
            shopname: this.query.shopname,
          },
        })
        .then((resp) => {
          _this.news = resp.data.content;
          console.log(_this.news, "_this.news");

          this.total = resp.data.content.count;
        });
    },
  },
};
</script>

<style>
.album {
  padding-top: 10px !important;
}
.search {
  padding: 4px;
  padding-bottom: 20px;
}
.search div {
  margin-right: 4px;
}
.search button {
  padding: 10.5px 20px !important;
}
</style>
