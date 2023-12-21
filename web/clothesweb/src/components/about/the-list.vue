<template>
  <main role="main">
    <div class="header-nav">
      <div class="clearfix">
        <div class="container">
          <div class="row">
            <div class="col-12">
              <a v-on:click="listCourse()" href="javascript:;" class="cur">全部</a>
              <a v-for="o in level1" v-on:click="listCourse(o.id)"  href="javascript:;">
                {{o.typeName}}
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="album py-5 bg-light">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="this.query.page"
                :page-sizes="[5, 10, 20, 30]"
                :page-size="this.query.size"
                layout="total, sizes, prev, pager, next, jumper"
                :total="this.total">
            </el-pagination>
          </div>
        </div>
        <br>
        <div class="row">
          <div v-for="o in courses" class="col-md-4">
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
    components: {TheCourse},

    name: 'the-list',
    data: function () {
      return {
        courses: [],
        level1: [

        ],
        level2: [],
        categorys: [],
        level1Id: "",
        level2Id: "",
        total:0,
        query:{
          page:1,
          size:6,
          label:'',
        },
      }
    },
    mounted() {
      let _this = this;
      // _this.listCourselevel();
      _this.listCourse();

      // _this.allCategory();

    },
    methods: {
      /**
       * 查询列表
       */
      // listCourselevel() {
      //   let _this = this;
      //   this.axios.get('http://124.221.174.197:8542/sysShop/getlist',{
      //     params:{
      //       page:this.query.page,
      //       size:this.query.size,
      //     }
      //   }).then((resp)=>{
      //     _this.level1 = resp.data.content.list;
      //     console.log(_this.level1,"_this.level1");
      //   })
      //
      // },
      /**
       * 查询列表
       */
      listCourse(id) {
        let _this = this;
        this.query.typeId = id;
        this.axios.get('http://124.221.174.197:8542/sysShop/getlist',{
          params:{
            page:this.query.page,
            size:this.query.size,
            typeId:this.query.typeId,

          }
        }).then((resp)=>{
          _this.courses = resp.data.content.list;
          console.log(_this.courses,"_this.new1s");

          this.total = resp.data.content.total;

        })

      },

      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
        this.query.size = val;
        this.listCourse();
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
        this.query.page = val;
        this.listCourse();
      },

      /**
       * 点击一级分类时
       * @param level1Id
       */
      onClickLevel1(level1Id) {
        let _this = this;

      },

      /**
       * 点击二级分类时
       * @param level1Id
       */
      onClickLevel2(level2Id) {
        let _this = this;
        $("#category-" + level2Id).siblings("a").removeClass("on");
        $("#category-" + level2Id).addClass("on");

        // 点击二级分类时，设置变量，用于课程筛选
        // 如果点击的是【无限】，则二级分类id为空
        if (level2Id === "11111111") {
          _this.level2Id = null;
        } else {
          _this.level2Id = level2Id;
        }

        // 重新加载列表
        _this.listCourse(1);
      },

    }
  }
</script>
<style>
  /* 头部 一级分类 */
  .header-nav {
    height: auto;
    background: #fff;
    box-shadow: 0 8px 16px 0 rgba(28,31,33,.1);
    padding: 16px 0;
    box-sizing: border-box;
    position: relative;
    z-index: 1;
    /*background-color: #d6e9c6;*/
  }
  .header-nav>div {
    width: 100%;
    padding-left: 12px;
    box-sizing: border-box;
    margin-left: auto;
    margin-right: auto;
    /*background-color: #B4D5AC;*/
  }
  .header-nav a {
    float: left;
    font-size: 16px;
    color: #07111b;
    line-height: 50px;
    height: 45px;
    position: relative;
    margin-right: 46px;
    font-weight: 700;
  }
  .header-nav a:hover {
    color: #c80;
  }
  .header-nav a.cur {
    color: #c80;
  }
  .header-nav a.cur:before {
    display: block;
  }
  .header-nav a:before {
    display: none;
    content: ' ';
    position: absolute;
    bottom: 0;
    background: #c80;
    width: 16px;
    height: 3px;
    left: 50%;
    margin-left: -8px;
  }
  /* 二级分类 */
  .skill {
    width: 100%;
    padding: 24px 0 0;
    position: relative;
    margin: 0 auto;
  }
  .skill a.on {
    color: #c80;
    background: rgba(204,136,0,.1);
  }
  .skill a {
    float: left;
    margin-right: 20px;
    padding: 0 12px;
    font-size: 14px;
    color: #4d555d;
    line-height: 32px;
    border-radius: 6px;
    margin-bottom: 12px;
  }
  .skill a:hover {
    background: #d9dde1;
  }
</style>
