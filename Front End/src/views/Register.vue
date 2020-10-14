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
              <form @submit.prevent="register" slot="inputs">
                <md-field class="md-form-group" slot="inputs">
                  <md-icon>account_circle</md-icon>
                  <label>First Name</label>
                  <md-input
                    type="text"
                    v-model.trim="$v.firstName.$model"
                  ></md-input>
                  <br />
                </md-field>
                <div
                  class="form__error"
                  v-if="submitted && !$v.firstName.required"
                >
                  *This field is required.
                </div>
                <md-field class="md-form-group" slot="inputs">
                  <md-icon>account_circle</md-icon>
                  <label>Last Name</label>
                  <md-input
                    type="text"
                    v-model.trim="$v.lastName.$model"
                  ></md-input>
                  <br />
                </md-field>
                <div
                  class="form__error"
                  v-if="submitted && !$v.lastName.required"
                >
                  *This field is required.
                </div>
                <md-field class="md-form-group" slot="inputs">
                  <md-icon>email</md-icon>
                  <label>Email</label>
                  <md-input
                    type="text"
                    v-model.trim="$v.email.$model"
                  ></md-input>
                  <br />
                </md-field>
                <div class="form__error" v-if="submitted && !$v.email.required">
                  *This field is required.
                </div>
                <div class="form__error" v-if="submitted && !$v.email.email">
                  *Please enter a valid email.
                </div>
                <md-field class="md-form-group" slot="inputs">
                  <md-icon>lock_outline</md-icon>
                  <label>New Password</label>
                  <md-input
                    :type="'password'"
                    v-model="$v.password.$model"
                  ></md-input>
                </md-field>
                <div
                  class="form__error"
                  v-if="submitted && !$v.password.required"
                >
                  *This field is required.
                </div>
                <div
                  class="form__error"
                  v-if="submitted && !$v.password.minLength"
                >
                  *Password must be at least 6 characters.
                </div>
                <md-field class="md-form-group" slot="inputs">
                  <md-icon>lock_outline</md-icon>
                  <label>Confirm Password</label>
                  <md-input
                    :type="'password'"
                    v-model="$v.confirmPassword.$model"
                  ></md-input>
                </md-field>
                <div
                  class="form__error"
                  v-if="submitted && !$v.confirmPassword.required"
                >
                  *This field is required.
                </div>
                <div
                  class="form__error"
                  v-if="submitted && !$v.confirmPassword.sameAsPassword"
                >
                  *Passwords must match.
                </div>
                <md-button
                  slot="footer"
                  class="md-simple md-success md-lg"
                  type="submit"
                  >Submit</md-button
                >
              </form>
            </login-card>
            <!-- <div class="modal-mask" v-if="submitted && modal">
              <div class="modal-wrapper">
                <div class="modal-container">
                  <div class="modal-header">
                    <h4 class="modal-title">Modal Title</h4>
                  </div>
                </div>
              </div>
            </div> -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { LoginCard } from "@/components";
import { Modal } from "@/components";
import Axios, { axios } from "axios";
import { Account } from "../models/Account";
import { required, email, minLength, sameAs } from "vuelidate/lib/validators";

export default {
  components: {
    LoginCard
  },
  bodyClass: "login-page",
  name: "Register",
  data() {
    return {
      firstName: "",
      lastName: "",
      email: "",
      password: "",
      confirmPassword: "",
      submitted: false,
      modal: false,
      response: null,
      error: "",
      success: false,
      fail: false,
    };
  },

  validations: {
    firstName: {
      required
    },
    lastName: {
      required
    },
    email: {
      required,
      email
    },
    password: {
      required,
      minLength: minLength(6)
    },
    confirmPassword: {
      required,
      sameAsPassword: sameAs("password")
    }
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
    register() {
      this.submitted = true;
      console.log("submit");
      if (this.$v.$invalid) {
        //alert("Please fix errors");
      } else {
        let data = {
          firstName: this.firstName,
          lastName: this.lastName,
          email: this.email,
          password: this.password
        };
        this.$store
          .dispatch("register", data)
          .then(resp => alert(resp.data.message))
          .then(this.$router.push("/login"))
          .catch(err => alert("Something went wrong: " + err))
      }
    }
  }
};
</script>

<style scoped>
.form__error {
  color: red;
  font-size: 0.75em;
  padding-left: 10px;
}
</style>
