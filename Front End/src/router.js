import Vue from "vue";
import Router from "vue-router";
import store from './store/store.js'

/*Layout*/
import MainNavbar from "./layout/MainNavbar.vue";
import MainFooter from "./layout/MainFooter.vue";

/*Views*/
import Index from "./views/Index.vue";
import Login from "./views/Login.vue";
import UserList from "./views/UserList.vue";
import Profile from "./views/Profile.vue";
import Recommend from "./views/Recommend.vue";
import About from "./views/About.vue"
import FAQ from "./views/Faq.vue";
import Register from "./views/Register.vue";
import Forums from "./views/Forums.vue"
import Events from "./views/Events.vue"
import ForgotPassword from "./views/ForgotPassword.vue";
import ChangePassword from "./views/ChangePassword.vue";
import DeleteAccount from "./views/DeleteAccount.vue";

Vue.use(Router);

let router = new Router(
  {
    name: 'router',
    routes: [
      {
        path: "/",
        name: "index",
        components: {
          default: Index,
          header: MainNavbar,
          footer: MainFooter
        },
        props: {
          header: { colorOnScroll: 400 },
          footer: { backgroundColor: "black" }
        }
      },
      {
        path: "/login",
        name: "login",

        components: {
          default: Login,
          header: MainNavbar,
          footer: MainFooter
        },
        props: {
          header: { colorOnScroll: 400 },
          footer: { backgroundColor: "black" }
        }
      },
      {
        path: "/profile",
        name: "profile",

        components: {
          meta: {
            requiresAuth: true
          },
          default: Profile,
          header: MainNavbar,
          footer: MainFooter
        },
        props: {
          header: { colorOnScroll: 400 },
          footer: { backgroundColor: "black" }
        }
      },
      {
        path: "/faq",
        name: "faq",

        components: {
          default: FAQ,
          header: MainNavbar,
          footer: MainFooter
        },
        props: {
          header: { colorOnScroll: 400 },
          footer: { backgroundColor: "black" }
        }
      },
      {
        path: "/about",
        name: "about",
        components: {
          default: About,
          header: MainNavbar,
          footer: MainFooter
        },
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
        },
        props: {
          header: { colorOnScroll: 400 },
          footer: { backgroundColor: "black" }
        }
      },
      {
        path: "/forums",
        name: "forums",
        components: {
          default: Forums,
          header: MainNavbar,
          footer: MainFooter
        },
        props: {
          header: { colorOnScroll: 400 },
          footer: { backgroundColor: "black" }
        }
      },
      {
        path: "/userlist",
        name: "userlist",
        components: {
          meta: {
            requiresAuth: true
          },
          default: UserList,
          header: MainNavbar,
          footer: MainFooter
        },
        props: {
          header: {
            colorOnScroll: 400
          },
          footer: {
            backgroundColor: "black"
          }
        }
      },
      {
        path: "/register",
        name: "register",
        components: {
          default: Register,
          header: MainNavbar,
          footer: MainFooter
        },
        props: {
          header: {
            colorOnScroll: 400
          },
          footer: {
            backgroundColor: "black"
          }
        }
      },
      {
        path: "/forgotpassword",
        name: "forgotpassword",
        components: {
          default: ForgotPassword,
          header: MainNavbar,
          footer: MainFooter
        },
        props: {
          header: {
            colorOnScroll: 400
          },
          footer: {
            backgroundColor: "black"
          }
        }
      },
      {
        path: "/changepassword",
        name: "changepassword",
        components: {
          default: ChangePassword,
          header: MainNavbar,
          footer: MainFooter
        },
        props: {
          header: {
            colorOnScroll: 400
          },
          footer: {
            backgroundColor: "black"
          }
        }
      },
      {
        path: "/events",
        name: "events",
        components: {
          default: Events,
          header: MainNavbar,
          footer: MainFooter
        }
      },
      {
        path: "/deleteaccount",
        name: "deleteaccount",
        components: {
          default: DeleteAccount,
          header: MainNavbar,
          footer: MainFooter
        },
        props: {
          header: {
            colorOnScroll: 400
          },
          footer: {
            backgroundColor: "black"
          }
        }
      },
    ],
    scrollBehavior: to => {
      if (to.hash) {
        return { selector: to.hash };
      } else {
        return { x: 0, y: 0 };
      }
    }


  }); export default router;

router.beforeEach((to, from, next) => {
  const publicPages = ['/', '/login', '/home', '/faq', '/about', '/recommendations', '/forums', '/register', '/events'];
  const userPages = ['/profile'];
  const adminPages = ['/userlist',];

  const adminAuth = adminPages.includes(to.path)
  const userAuth = userPages.includes(to.path)

  console.log("isLoggedIn: " + store.getters.isLoggedIn)
  console.log("authStatus: " + store.getters.authStatus)
  console.log("isAdmin: " + store.getters.isAdmin)
  console.log("isUser: " + store.getters.isUser)
  console.log("token: " + store.getters.getJwtToken)

  // trying to access a restricted page + not logged in
  // redirect to login page
  if (adminAuth) {
    if (store.getters.isAdmin) {
      next();
    } else {
      localStorage.removeItem('token')
      localStorage.removeItem('user')
      next('/login');
    }
  } else if (userAuth) {
    if (store.getters.isUser) {
      next();
    } else {
      localStorage.removeItem('token')
      localStorage.removeItem('user')
      next('/login');
    }
  } else {
    next();
  }
});

