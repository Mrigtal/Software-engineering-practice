<template>
  <div class="card mb-4 shadow-sm course">
    <img
      style="width: 400px; height: 300px"
      class="img-fluid"
      v-bind:src="getFirstImageUrl(course.shoppoto)"
    />
    <div class="card-body">
      <h4 class="">{{ course.shopname }}</h4>
      <p class="card-text" style="color: #e1251b">￥{{ course.shoppice }}</p>
      <p>库存{{ course.count }}</p>
      <div class="d-flex justify-content-between align-items-center">
        <div class="btn-group">
          <router-link
            v-bind:to="'/detail?id=' + course.id"
            class="btn btn-outline-secondary"
            >商品详情</router-link
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "the-course",
  props: {
    course: {},
  },
  methods: {
    getFirstImageUrl(shoppoto) {
      // 检查 shoppoto 是否存在且不为空
      if (!shoppoto) {
        return ""; // 或返回默认图片 URL
      }
      // 分割字符串并获取第一个 URL
      const urls = shoppoto.split(",");
      return urls[0]; // 返回第一个 URL
    },
  },
  created() {
    console.log("00", this.course);
    let zhi = SessionStorage.get("USERNAME");
    console.log(zhi, "username");
    if (typeof zhi == "undefined") {
      this.formInline.zhi = "meiYouZhi";
    } else {
      this.formInline.zhi = "youZhi";
    }
    if (this.course.shopname.length > 10) {
      this.course.shopname = this.course.shopname.substring(0, 15) + "";
    }
  },
  data: function () {
    return {
      formInline: {
        username: "",
        content: "",
        zhi: "",
        label: "",
      },
    };
  },
};
</script>

<style>
.course h4 {
  font-size: 1.25rem;
  margin: 15px 0;
}

.course .text-muted {
  color: red;
  font-size: 1rem;
}
</style>
