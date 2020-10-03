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
    user: new User('','',''),
    submitted: false,
    successful: false,
    message: ''
  }
  /*return {
      firstName: null,
      lastName: null,
      email: null,
      password: null,
      confirmPassword: null,

    };*/
  },
/*
  watch: {
    email(value){
      this.email = value;
      this.validateEmail(value);
    }
  },*/

  props: {
    header: {
      type: String,
      default: require("@/assets/img/profile_city.jpg")
    }
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    }
  },
    mounted() {
      if(this.loggedIn){
        this.$router.push('/');
      }
    },
  methods: {
  handleRegister() {
        this.message = '';
        this.submitted = true;
        this.$validator.validate().then(isValid => {
          if (isValid) {
            this.$store.dispatch('auth/register', this.user).then(
              data => {
                this.message = data.message;
                this.successful = true;
              },
              error => {
                this.message =
                  (error.response && error.response.data) ||
                  error.message ||
                  error.toString();
                this.successful = false;
              }
            );
          }
        });
      }
    },
/*
    createUser() {
      const url = "http://localhost:8081/user/create";
      const account = new Account(this.email, this.password, this.firstName, this.lastName);
      Axios.post(url, account, {params: {
        header: {
          "Content-Type": "application/json",
        }
      }})
        .then(reponse => {
          console.log(reponse);
        })
        .catch(error => {
          console.log(error);
        });
        this.$router.push({path: "/login"});
    }
  },*/
  
};
</script>

<style lang="css"></style>
