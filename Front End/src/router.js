import Vue from "vue";
import Router from "vue-router";
import Index from "./views/Index.vue";
import Landing from "./views/Landing.vue";
import Login from "./views/Login.vue";
import UserList from "./views/UserList.vue";
import Profile from "./views/Profile.vue";
import FAQ from "./views/FAQ_page.vue";
import MainNavbar from "./layout/MainNavbar.vue";


Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      name: "index",
      components: { default: Index, header: MainNavbar },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/landing",
      name: "landing",
      components: { default: Landing, header: MainNavbar },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/login",
      name: "login",
      components: { default: Login, header: MainNavbar },
      props: {
        header: { colorOnScroll: 400 }
      }
    },
    {
      path: "/profile",
      name: "profile",
      components: { default: Profile, header: MainNavbar },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/FAQ_page",
      name: "FAQ_page",
      components: { default: FAQ, header: MainNavbar },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/UserList",
      name: "userlist",
      components: { default: UserList, header: MainNavbar },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },

      props: {
        header: {
          colorOnScroll: 400
        },
        footer: {
          backgroundColor: "black"
        }
      }
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
