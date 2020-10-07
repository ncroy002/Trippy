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
Vue.use(MaterialKit, Vuelidate, Vuex);
Vue.use(Vuex); //didn't add to previous expression
Vue.use(Vuelidate);

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
  store: store,
  render: h => h(App)
}).$mount("#app");

Vue.prototype.$http = Axios;
const token = localStorage.getItem('token')
if (token) {
  Vue.prototype.$http.defaults.headers.common['Authorization'] = token
}


/*
import {
  mapState
} from 'vuex';

const store = new Vuex.Store({
  state: {
    count: 0,
    todos: [{
        id: 1,
        text: '...',
        done: true
      },
      {
        id: 2,
        text: '...',
        done: false
      },
    ]
  },
  actions: { //also an example of how to call from a database
    incrementAsync({
      commit
    }) { //this call is an example of how to do API call as well
      setTimeout(() => {
        commit('increment')
      }, 1000)
    }
  },
  mutations: {
    increment(state) {
      state.count++;
    },
    incrementBy(state, payload) {
      state.count += payload.amount
    },
    decrement(state) {
      state.count--;
    }
  },
  getters: {
    doneToDos: state => {
      return state.todos.filter(todo => todo.done);
    },
    doneToDosCount: (state, getters) => {
      return getters.doneToDos.length;
    },
    getToDoById: (state) => (id) => { //USE THIS FOR FAQ ITEMS
      return state.todos.find(todo => todo.id === id);
    },
  }
});

// store.commit('increment');
store.commit({
  type: 'incrementBy',
  amount: 40
})
// store.commit('incrementBy', {amount: 29});
console.log(store.state.count);
console.log(store.getters.doneToDos);
console.log(store.getters.doneToDosCount);
console.log(store.getters.getToDoById(2));
// Vue.set(obj, 'new prop', 123) how I can create a new object (FAQ)

*/