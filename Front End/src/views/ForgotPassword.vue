<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto"
          >
            <login-card header-color="green">
              <h4 slot="title" class="card-title">Password Reset</h4>
              <p slot="description" class="description">
                Enter Email Address to Send Password Reset Email
              </p>
              <form @submit.prevent="submit($v.email.$model)" slot="inputs">
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
                <md-button
                  slot="footer"
                  class="md-simple md-success md-lg"
                  type="submit"
                  >Submit</md-button
                >
              </form>
            </login-card>
            <div class="modal-mask" v-if="submitted && modal">
              <div class="modal-wrapper">
                <div class="modal-container" v-click-outside="closeModal">
                  <div class="modal-header">
                    <h4 class="modal-title" style="color:black">Thank You</h4>
                    <button
                      @click="close()"
                      type="button"
                      class="md-button md-simple md-just-icon md-round modal-default-button md-theme-default"
                    >
                      <div class="md-ripple">
                        <div class="md-button-content">
                          <i class="md-icon md-icon-font md-theme-default"
                            >clear</i
                          >
                        </div>
                      </div>
                    </button>
                  </div>
                  <div class="modal-body text-center">
                    <p style="color:black">
                      If your email exists within our database, then you will
                      receive a password reset email shortly. If you do not
                      receive an email, please check your spam folder or please
                      try again. Thank you.
                    </p>
                  </div>
                  <div class="modal-footer">
                    ><button
                      type="button"
                      @click="close()"
                      class="md-button md-danger md-simple md-theme-default"
                    >
                      <div class="md-ripple">
                        <div class="md-button-content">Close</div>
                      </div>
                    </button>
                  </div>
                </div>
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
import Axios from "axios";
import { Account } from "../models/Account";
import { required, email } from "vuelidate/lib/validators";
import Modal from "@/components";
export default {
  components: {
    LoginCard
  },
  bodyClass: "login-page",
  data() {
    return {
      email: null,
      submitted: false,
      modal: false
    };
  },
  validations: {
    email: {
      required,
      email
    }
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/profile_city.jpg")
    }
  },
  methods: {
    submit(email) {
      this.submitted = true;

      console.log("submit");
      const url = "http://localhost:8081/user/forgotPassword";
      if (!this.$v.$invalid) {
        Axios({
          url: url,
          method: "post",
          headers: {
            "Content-Type": "application/json"
          },
          params: {
            email: this.email
          }
        })
          .then(result => {
            console.log(result);
          })
          .catch(err => {
            console.log(err);
          });
        this.modal = true;
      }
    },
    close(){
      this.modal = false;
    },
    closeModal: function() {
      this.$emit("close");
      this.modal = false;
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