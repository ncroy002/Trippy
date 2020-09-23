import Vue from "vue";
import Router from "vue-router";
import Index from "./views/Index.vue";

import Login from "./views/Login.vue";
import Profile from "./views/Profile.vue";
import MainNavbar from "./layout/MainNavbar.vue";
import MainFooter from "./layout/MainFooter.vue";
import FAQ from "./views/Faq.vue"
import About from "./views/About.vue"
import Trips from "./views/Trips.vue"
import Recommend from "./views/Recommend.vue"
import UserList from "./views/UserList.vue"
import Forums from "./views/Forums.vue"
import Register from "./views/Register.vue"

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      name: "index",
      components: { default: Index, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/trips",
      name: "trips",
      components: { default: Trips, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/login",
      name: "login",
      components: { default: Login, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 }
      }
    },
    {
      path: "/profile",
      name: "profile",
      components: { default: Profile, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/faq",
      name: "faq",
      components: { default: FAQ, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/about",
      name: "about",
      components: { default: About, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/recommendations",
      name: "recommendations",
      components: {
        default: Recommend,
        header: MainNavbar,
        footer: MainFooter
      }
    },
    {
      path: "/forums",
      name: "forums",
      components: {
        default: Forums,
        header: MainNavbar,
        footer: MainFooter
      }
    },

    {
      path: "/userlist",
      name: "userlist",
      components: {
        default: UserList,
        header: MainNavbar,
        footer: MainFooter
      }
    },

  {
      }
        footer: MainFooter
        header: MainNavbar,
        default: Register,
      components: {
      name: "register",
      path: "/register",
    }

    
  ],
  scrollBehavior: to => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  }
});
