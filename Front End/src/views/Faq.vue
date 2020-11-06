<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-50 md-small-size-70 md-xsmall-size-100"
          >
            <h1 class="title">Frequently Asked Questions</h1>

            <br />
          </div>
        </div>
      </div>
    </parallax>
    <div class="main main-raised">
      <div class="section">
        <div class="container">
          <div class="md-layout">
            <div class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center" >
              <h2 class="title text-center">Frequently Asked Questions</h2>
            </div>

            <div class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center">

              <form @submit.prevent="addFaq" 
              slot="inputs">
                
              <md-field v-if="isAdmin"
              class="md-form-group" 
              slot="inputs">

              <md-icon >add</md-icon>
              <label>NEW FAQ QUESTION</label>

              <md-input 
              type="text"
              v-model="$v.message.$model"
              >
              </md-input>
              <br />

              </md-field>

              <div
                  class="form_error"
                  v-if="submitted && !$v.message.required"
                >
                  *This field is required.
                </div>

                  <!-- <div
                  class="form__error"
                  v-if="!$v.message.isUnique"
                >
                  *This question is already saved.
                </div> -->

              <md-field v-if="isAdmin"
              class="md-form-group" 
              slot="inputs">

               <md-icon >add</md-icon>
              <label>NEW FAQ ANSWER</label>

               <md-input 
               v-model="$v.answer.$model"
               type="type">
               </md-input>
              </md-field>
              <br />

              <div
                  class="form_error"
                  v-if="submitted && !$v.answer.required"
                >
                  *This field is required.
                </div>
               
                <!-- <div
                  class="form__error"
                  v-if="!$v.answer.isUnique"
                >
                  *This answer is already saved.
                </div> -->

              <md-button 
              v-if="isAdmin" 
              
              slot="footer" 
              type = "submit"
              class="md-simple md-success md-lg">Add</md-button>
              </form>
              </div>
              </div>

              <div class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center" >
              <md-field 
              class="md-form-group" 
              slot="inputs">

                <md-icon>search</md-icon>
                <label>SEARCH FAQS</label>

                <md-input 
                v-model="search" 
                type="search"
                ></md-input>
              </md-field>
            </div>

             <div class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-left" >

              <div class="container">

                <ul class="responsive-table">
                
                  <li
                    class="table-row"
                    v-for="faq in filteredFaqs"
                    v-bind:key="faq.id"
                  >
                    <div class="col col-11" data-label="Faq">
                     <span style='font-weight: bold'> {{ faq.message }} </span>
                     <p> {{faq.answer}} </p>
                    </div>
                     <md-button v-if="isAdmin"
                        class="md-danger md-sm"
                        @click="deleteFaq(faq.id)">
                        Delete Faq</md-button>
                      
					</li>
					</ul>
           	 </div>
			</div>
      <div class="container" v-if="isUser">
             <div  class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center">
              <h2 class="title text-center">Need Help?</h2>
            </div>
          <div  class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center">
                      <form class="contact-form">
                <div class="md-layout">
                  <div class="md-layout-item md-size-50">
                    <md-field>
                      <label>Your Name</label>
                      <md-input v-model="name" type="text"></md-input>
                    </md-field>
                  </div>
                  <div class="md-layout-item md-size-50">
                    <md-field>
                      <label>Your Email</label>
                      <md-input v-model="email" type="email"></md-input>
                    </md-field>
                  </div>
                </div>
                <md-field maxlength="5">
                  <label>Your Message</label>
                  <md-textarea v-model="message"></md-textarea>
                </md-field>
                <div class="md-layout">
                  <div class="md-layout-item md-size-33 mx-auto text-center">
                    <md-button class="md-success">Send Message</md-button>
                  </div>
                </div>
              </form>
            </div>
          </div>

 <!-- <div class="container" v-if="isAdmin">
             <div  class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center">
              <h2 class="title text-center">Help</h2>
            </div>
          <div  class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center">
          <ul class="responsive-table">
                  <li class="table-header">
                    <div class="col col-1">Name</div>
                    <div class="col col-2">Email</div>
                    <div class="col col-3">Question</div>
                    <div class="col col-4">Completed</div>
                    <div class="col col-5"></div>
                  </li>
                  <li
                    class="table-row"
                    v-for="help in helps"
                    v-bind:key="help.id"
                  >
                    <div class="col col-1" data-label="User ID">
                      {{ help.name }}
                    </div>
                    <div class="col col-2" data-label="Email">
                      {{ help.email }}
                    </div>
                    <div class="col col-3" data-label="First Name">
                      {{ help.question }}
                    </div>
                    <div class="col col-4" data-label="Last Name">
                      {{ question.completed }}
                    </div> -->

                    <!-- <div class="col col-5" data-label="Reset/Delete">
                      <md-dialog-prompt
                        :md-active.sync="active"
                        v-model="newPassword"
                        md-title="Enter New Password"
                        md-input-maxlength="15"
                        md-input-placeholder="Password..."
                        md-confirm-text="Confirm"
                        @md-confirm="resetPassword(user.id)"
                      />
                      <md-button class="md-warning md-sm" @click="active = true"
                        >Reset Password</md-button
                      >

                      <md-button
                        class="md-danger md-sm"
                        @click="deleteUser(user.id)"
                        >Delete Account</md-button
                      >
                    </div>
                  </li>
                </ul>
                </div>
                </div> -->
        </div>
        </div>
      </div>
    </div>
  <!-- </div> -->
</template>



<script>
import Axios, { axios } from "axios";
import { Faq } from "../models/Faq";
import { Help } from "../models/Help"
import { required, email, minLength, sameAs } from "vuelidate/lib/validators";
export default {
  bodyClass: "FAQ_page-page",
  name: "AddFaq",
  props: {
    header: {
      type: String,
      default: require("@/assets/img/bg3.jpg"),
    },
  },
  data() {
    return {
      submitted: false,
      name: null,
      email: null,
      message: "",
      answer: "",
      search: "",
      faqs: [],
      questions: [],
      response: null,
      error: "",
      success: false,
      fail: false,
    };
  },

  validations: {
    name: {
      required
    },
    email: {
      required
    },
    question: {
      required,
    },
    answer:{
      required,
    //  minLength: minLength(15),
      // async isUnique (value) {
      // if (value === '') return true
      // const response = await fetch(`/api/unique/${value}`)
      // return Boolean(await response.json())
      // }
    },
    message: {
      required
    
        //  minLength: minLength(15),
      // async isUnique (value) {
      // if (value === '') return true
      // const response = await fetch(`/api/unique/${value}`)
      // return Boolean(await response.json())
      // }
      },
  },
 computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    },
    isAdmin : function(){ return this.$store.getters.isAdmin},
    isUser : function() {return this.$store.getters.isUser},
    filteredFaqs: function() {
      return this.faqs.filter((faq) => { 
        return faq.message.toLowerCase().match(this.search.toLowerCase());
      })
    }
    
 },
 mounted: function() {
    this.faqList();
    // this.helpList();
  },
  methods: {
    addQuestion() {
      console.log(this.question, this.name, this.email);
      const url = "http://localhost:8081/help/newHelp";
      const help = new Help(this.question, this.name, this.email);
      Axios.post(url, help, {params: {
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
    },
    addFaq() {
      this.submitted = true;
      console.log("submit");
      console.log(this.message, this.answer);

      // this.$v.$touch()
      if (this.$v.$invalid) {
        console.log("NOT VALID ENTRY");
      }
      else{
      const url = "http://localhost:8081/faq/newFaq";
      const faq = new Faq(this.message, this.answer);
      Axios.post(url, faq, {params: {
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
      }
	},
    faqList: function() {

	  const url = "http://localhost:8081/faq/listFaqs";

      Axios.get(url)
        .then(response => (this.faqs = response.data))

        .catch(function(error) {
          console.warn("error occured" + error);
      });
		},
  	deleteFaq(id) {
      if (confirm("Confirm faq deletion: " + id )) {
        const url = "http://localhost:8081/faq/deleteFaq";
        Axios.delete(url, {
          params: {
            ID: id
          },
          headers: {
            "Content-Type": "application/json"
          }
        })
          .then(response => {
            console.log(response);
            this.faqs = this.faqs.filter(e => e.id != id);
          })
          .catch(error => {
            console.warn("Error: " + error);
          });
      }
    },
  },
    // helpList: function() {

	  // const url = "http://localhost:8081/help/listHelps";

    //   Axios.get(url)
    //     .then(response => (this.helps = response.data))

    //     .catch(function(error) {
    //       console.warn("error occured" + error);
    //   });
		// },

};

</script>

<style lang="scss" scoped>
.md-card-actions.text-center {
  display: flex;
  justify-content: center !important;
}
.contact-form {
  margin-top: 30px;
}

.md-has-textarea + .md-layout {
  margin-top: 15px;
}

.md-layout-item {
  margin-bottom: 15px;
}
ul {
  margin: 0;
  padding: 0;
  list-style: none;
}

body {
  font-family: "lato", sans-serif;
}
.container {
  max-width: 1000px;
  margin-left: auto;
  margin-right: auto;
  padding-left: 10px;
  padding-right: 10px;
}

h2 {
  font-size: 26px;
  margin: 20px 0;
  text-align: center;
  small {
    font-size: 0.5em;
  }
}

.responsive-table {
  li {
    border-radius: 3px;
    padding: 25px 30px;
    display: flex;
    justify-content: space-between;
    margin-bottom: 25px;
  }
  .table-header {
    background-color: #95a5a6;
    font-size: 14px;
    text-transform: uppercase;
    letter-spacing: 0.03em;
  }
  .table-row {
    background-color: #ffffff;
    box-shadow: 0px 0px 9px 0px rgba(0, 0, 0, 0.1);
  }
  .col-1 {
    flex-basis: 10%;
  }
  .col-2 {
    flex-basis: 30%;
  }
  .col-3 {
    flex-basis: 20%;
  }
  .col-4 {
    flex-basis: 20%;
  }
  .col-5 {
    flex-basis: 20%;
  }

  @media all and (max-width: 767px) {
    .table-header {
      display: none;
    }

    li {
      display: block;
    }
    .col {
      flex-basis: 100%;
    }
    .col {
      display: flex;
      padding: 10px 0;
      &:before {
        color: #6c7a89;
        padding-right: 10px;
        content: attr(data-label);
        flex-basis: 50%;
        text-align: right;
      }
    }
  }
}
.space {
  padding: 25px;
  margin: 25px;
}
</style>

<style scoped>
.form_error {
  color: red;
  font-size: 0.75em;
  padding-left: 10px;
}
</style>
