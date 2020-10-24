// =========================================================
// * Vue Material Kit - v1.2.2
// * Copyright 2019 Creative Tim (https://www.creative-tim.com)
// * Licensed under MIT (https://github.com/creativetimofficial/vue-material-kit/blob/master/LICENSE.md)
// =========================================================


import Vue from "vue";
import Vuex from "vuex";
import App from "./App.vue";
import router from "./router";
import Vuelidate from 'vuelidate';
import MaterialKit from "./plugins/material-kit";
import 'es6-promise/auto';
import store from './store/store.js'

Vue.config.productionTip = false;

//Added Vuelidate for validation on register page
Vue.use(MaterialKit);
Vue.use(Vuex); //didn't add to previous expression
Vue.use(Vuelidate); //Multiple parameters in Vue.use() doesn't work

const NavbarStore = {
  showNavbar: false
};

import { mapState } from 'vuex';

Vue.mixin({
  data() {
    return {
      NavbarStore
    };
  }
});

new Vue({

 // store: store,
  router,
  store: store,
  render: h => h(App)
}).$mount("#app");

Vue.prototype.$http = Axios;
const token = localStorage.getItem('token')
if (token) {
  Vue.prototype.$http.defaults.headers.common['Authorization'] = token
}
