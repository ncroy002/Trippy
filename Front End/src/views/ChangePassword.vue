<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto"
          >
            <login-card header-color="green">
              <h4 slot="title" class="card-title">Change Password</h4>
              <p slot="description" class="description">Enter New Password</p>
              <form @submit.prevent="submit()" slot="inputs">
                <md-field class="md-form-group" slot="inputs">
                  <md-icon>lock</md-icon>
                  <label>New Password</label>
                  <md-input
                    :type="'password'"
                    v-model.trim="$v.password.$model"
                  ></md-input>
                  <br />
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
                    v-model.trim="$v.confirmPassword.$model"
                  ></md-input>
                  <br />
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
                  *Passwords must match
                </div>
                <md-button
                  slot="footer"
                  class="md-simple md-success md-lg"
                  type="submit"
                  >Submit</md-button
                >
              </form>
            </login-card>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { LoginCard } from "@/components";
import Axios from "axios";
import { Account } from "../models/Account";
import { required, minLength, sameAs } from "vuelidate/lib/validators";
export default {
  components: {
    LoginCard
  },
  bodyClass: "login-page",
  data() {
    return {
      password: null,
      confirmPassword: null,
      submitted: false,
      parameters: null,
      token: null,
    };
  },
  validations: {
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
  methods: {
    submit() {
      this.submitted = true;
      this.token = this.$route.query.token
      console.log(this.$route.query.token);
      console.log("submit");
      const url = "http://localhost:8081/user/resetPasswordByUser";
      if (!this.$v.$invalid) {
        Axios({
          url: url,
          method: "post",
          headers: {
            "Content-Type": "application/json",
          },
          params: {
            token: this.token,
            password: this.password
          }
        })
          .then(result => {
            console.log(result);
          })
          .catch(err => {
            console.log(err);
          });
      }
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

<style scoped>
.form__error {
  color: red;
  font-size: 0.75em;
  padding-left: 10px;
}
</style>