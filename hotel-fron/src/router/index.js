import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: ()=> import("../views/user/login.vue")
  },
  {
    path: '/userList',
    name: 'userList',
    component: ()=> import("../views/user/list.vue")
  },
]

const router = new VueRouter({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes
})

export default router
