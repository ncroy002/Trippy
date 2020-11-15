<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto"
          >
            <login-card header-color="green">
              <h4 slot="title" class="card-title">Delete Account</h4>
              <p slot="description" class="description">Click the button below to permanently delete your account</p>
                
                <md-button
                  slot="footer"
                  class="md-button md-danger md-theme-default"
                  type="submit"
                  v-on:click="submit()"
                  >Delete Account</md-button
                >
              
            </login-card>
            <div class="modal-mask" v-if="submitted && modal">
              <div class="modal-wrapper">
                <div class="modal-container">
                  <div class="modal-header">
                    <h4 class="modal-title" style="color:black">
                      Confirmation
                    </h4>
                  </div>
                  <div class="modal-body text-center">
                    <p style="color:black">
                      Are you sure you want to delete your account? <br> 
                    </p>
                  </div>
                  <div class="modal-footer">
                    ><button
                      type="button"
                      @click="deleteAccount()"
                      class="md-button md-danger md-theme-default"
                    >
                      <div class="md-ripple">
                        <div class="md-button-content">Yes Delete It!</div>
                      </div>
                    </button>
                    <button
                      type="button"
                      @click="close()"
                      class="md-button md-theme-default"
                    >
                      <div class="md-ripple">
                        <div class="md-button-content">Cancel</div>
                      </div>
                    </button>
                  </div>
                </div>
              </div>
            </div>
            <div class="modal-mask" v-if="submitted && modal && error">
              <div class="modal-wrapper">
                <div class="modal-container">
                  <div class="modal-header">
                    <h4 class="modal-title" style="color:black">
                      Account Deletion failed
                    </h4>
                  </div>
                  <div class="modal-body text-center">
                    <p style="color:black">
                      Your account could not be deleted <br> Please try again or contact a site administrator.
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
import { Modal } from "@/components";
import Axios, { axios } from "axios";
import { Account } from "../models/Account";
import { required, email, minLength, sameAs } from "vuelidate/lib/validators";

export default {
  components: {
    LoginCard
  },
  bodyClass: "login-page",
  name: "DeleteAccount",
  data() {
    return {
      email: "",
      submitted: false,
      response: null,
      error: false,
      success: false,
      modal: false
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
    submit(){
      this.submitted = true;
      this.modal = true;

    },
    deleteAccount(){
      this.email = this.$store.state.user.email;
      console.log("Email is: " + this.email);
      const url = "http://localhost:8081/user/deleteUserByUser";
      Axios.delete(url, {
          params: {
            email: this.email
          },
          headers: {
            "Content-Type": "application/json"
          }
        })
          .then(response => {
            console.log(response);
            this.$store.dispatch('logout')
        .then(() => this.$router.push('/login'))
          })
          .catch(error => {
            console.warn("Error encountered: " + error);
            this.error = true;
          });
          
    },

    goToLogin(){
      this.$router.push("/login");
    },
    close(){
      this.modal = false;
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
