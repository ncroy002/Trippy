<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto"
          >
            <login-card header-color="green">
              <h4 slot="title" class="card-title">Login</h4>
              <p slot="description" class="description">Or Be Classical</p>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>email</md-icon>
                <label>Email...</label>
                <md-input
                  aria-required="true"
                  type="email"
                  v-model="email"
                ></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label>Password...</label>
                <md-input
                  aria-required="true"
                  type="password"
                  v-model="password"
                ></md-input>
              </md-field>
              <md-button
                v-on:click="login()"
                slot="footer"
                class="md-simple md-success md-lg"
                >Login</md-button
              >
              <md-button
                v-on:click="forgotPassword()"
                slot="belowfooter"
                class="md-simple md-success md-lg"
              >
                Forgot Password?
              </md-button>
            </login-card>
 <md-snackbar :md-position="position" :md-duration="isInfinity ? Infinity : duration" :md-active.sync="showSnackbar" md-persistent>
      <span>{{alert}}</span>
      <md-button class="md-primary" @click="showSnackbar = false">close</md-button>
    </md-snackbar>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { LoginCard } from "@/components";
import Axios, { axios } from "axios";
import { Account } from "../models/Account";

export default {
  components: {
    LoginCard
  },
  bodyClass: "login-page",
  data() {
    return {
      email: "",
      password: "", 
      /*snackbar*/
      showSnackbar: false,
      position: 'center',
      duration: 4000,
      isInfinity: false,
      alert: "",
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/profile_city.jpg")
    }
  },
  methods: {
    login: function() {
      let email = this.email;
      let password = this.password;
      this.$store
        .dispatch("login", { email, password })
        .then(() => this.$router.push("/"))
        .catch( 
        this.alert = "You have entered an incorrect email or password!",
        this.showSnackbar = true,
        console.log());
    },

    forgotPassword() {
      this.$router.push({ path: "/forgotpassword" });
    }
  },

  removeNotify(e, notifyClass) {
    var target = e.target;
    while (target.className.indexOf(notifyClass) === -1) {
      target = target.parentNode;
    }
    return target.parentNode.removeChild(target);
  },

  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    }
  }
};
</script>

<style lang="css"></style>