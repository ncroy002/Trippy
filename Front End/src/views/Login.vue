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
            </login-card>

            <div v-if="valid" class="alert alert-danger">
              <div class="container">
                <button
                  type="button"
                  aria-hidden="true"
                  class="close"
                  @click="event => removeNotify(event, 'alert-danger')"
                >
                  <md-icon>clear</md-icon>
                </button>
                <div class="alert-icon">
                  <md-icon>info_outline</md-icon>
                </div>
                <b> ERROR ALERT </b> : Please enter a valid username and password.
              </div>
            </div>
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
      email: null,
      password: null,
      valid: false
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/profile_city.jpg")
    }
  },
  methods: {
    login() {
      const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      if (this.email && this.password) {
        if (
          this.password.length >= 6 &&
          re.test(String(this.email).toLowerCase())
        ) {
          this.valid = false;
          const url = "http://localhost:8080/user/login";
          const account = new Account(this.email, this.password);
          Axios.post(url, account, {
            params: {
              header: {
                "Content-Type": "application/json"
              }
            }
          })
            .then(reponse => {
              console.log(reponse);
            })
            .catch(error => {
              console.log(error);
            });
        } else {
          this.valid = true;
        }
      } else {
        this.valid = true;
      }
    },
    removeNotify(e, notifyClass){
      var target = e.target;
      while(target.className.indexOf(notifyClass) === -1){
        target = target.parentNode;
      }
      return target.parentNode.removeChild(target);
    }

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
