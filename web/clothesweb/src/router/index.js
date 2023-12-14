import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AboutView from '../views/AboutView.vue'
import Login from '../components/login.vue'
import shop from '../components/shop.vue'
import message from '../components/message.vue'
import Welcome from '../components/Welcome.vue'
import TheList from '../components/about/the-list.vue'
import Detail from '../components/about/detail'
import history from '../components/history.vue'
import user from '../components/user.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/home',
    name: 'home',
    component: HomeView,
    redirect: '/welcome',//重定向
    children: [
      { path: '/welcome', component: Welcome },
      //  发布商品
      { path: '/shop', component: shop },
      //  下单人
      { path: '/message', component: message },
      { path: '/history', component: history },
      { path: '/user', component: user },
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/theList',
    name: 'theList',
    component: TheList
  },
  {
    path: '/detail',
    name: 'detail',
    component: Detail
  },
  {
    path: '/',
    name: 'about',
    component: AboutView,
    children: [
      { path: '/welcome', component: Welcome },
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router
