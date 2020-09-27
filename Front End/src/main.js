// =========================================================
// * Vue Material Kit - v1.2.2
// * Copyright 2019 Creative Tim (https://www.creative-tim.com)
// * Licensed under MIT (https://github.com/creativetimofficial/vue-material-kit/blob/master/LICENSE.md)
// =========================================================


import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import Vuelidate from 'vuelidate';
import MaterialKit from "./plugins/material-kit";

Vue.config.productionTip = false;

//Added Vuelidate for validation on register page
Vue.use(MaterialKit, Vuelidate);

const NavbarStore = {
  showNavbar: false
};

Vue.mixin({
  data() {
    return {
      NavbarStore
    };
  }
});

new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
