<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto"
          >
            <login-card header-color="green">
              <h4 slot="title" class="card-title">Create Account</h4>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>account_circle</md-icon>
                <label>First Name</label>
                <md-input type="text" v-model="firstName"></md-input>
                <br>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>account_circle</md-icon>
                <label>Last Name</label>
                <md-input type="text" v-model="lastName"></md-input>
                <br>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>email</md-icon>
                <label>Email</label>
                <md-input type="text" v-model="email"></md-input>
                <br>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label>New Password</label>
                <md-input :type="'password'" v-model="password"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label>Confirm Password</label>
                <md-input :type="'password'" v-model="confirmPassword"></md-input>
              </md-field>
              <md-button v-on:click="createUser()" slot="footer" class="md-simple md-success md-lg">Submit</md-button>
            </login-card>
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
  name:'Register',
  data() {

  return {
      firstName: "",
      lastName: "",
      email: "",
      password: "",
      confirmPassword: ""

    };
  },


  props: {
    header: {
      type: String,
      default: require("@/assets/img/profile_city.jpg")
    }
  },
  computed: {

    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    }
  },

  methods: {

    createUser() {
      let data = {
          firstName: this.firstName,
          lastName: this.lastName,
          email: this.email,
          password: this.password
        }
        this.$store.dispatch('register', data)
       .then(() => this.$router.push('/'))
       .catch(err => console.log(err))
      }
    }
  
  
};
</script>

<style lang="css"></style>
