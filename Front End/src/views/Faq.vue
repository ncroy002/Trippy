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

              <form @submit.prevent="addFaq" slot="inputs">
                
              <md-field v-if="isAdmin" class="md-form-group"  slot="inputs">

              <md-icon >add</md-icon>
              <label>NEW FAQ QUESTION</label>

              <md-input  type="text"  v-model="$v.message.$model"  >
              </md-input>
              <br />

              </md-field>

              <div class="form_error" v-if="!$v.message.required && isAdmin" >
                  *This field is required.
                </div>
                <div class="form_error" v-if="!$v.message.maxLength"  >
                  *No longer than 100 characters allowed.
                </div>
                 <div class="form_error" v-if="!$v.message.minLength" >
                  *No less than 50 characters allowed.
                </div>

              <md-field v-if="isAdmin" class="md-form-group"  slot="inputs">

               <md-icon >add</md-icon>
              <label>NEW FAQ ANSWER</label>

               <md-input  v-model="$v.answer.$model" type="type">
               </md-input>
              </md-field>
              <br />

              <div class="form_error" v-if="!$v.answer.required && isAdmin"  >
                  *This field is required.
                </div>
                <div class="form_error" v-if="!$v.answer.maxLength">
                  *No longer than 100 characters allowed.
                </div>
                 <div class="form_error" v-if="!$v.answer.minLength" >
                  *No less than 50 characters allowed.
                </div>

              <md-button v-if="isAdmin" slot="footer" type = "submit" class="md-simple md-success md-lg">Add</md-button>
              </form>
              </div>
              </div>

              <div class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center" >
              <md-field class="md-form-group" slot="inputs">

                <md-icon>search</md-icon>
                <label>SEARCH FAQS</label>

                <md-input v-model="search" type="search"></md-input>
              </md-field>
            </div>

             <div class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-left" >

              <div class="container">

                <ul class="responsive-table">
                
                  <li class="table-row" v-for="faq in filteredFaqs" v-bind:key="faq.id" >
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
  <div class="container" v-if="!isAdmin">
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
                  <md-textarea v-model="question"></md-textarea>
                   <input type="hidden" v-model="completed" />
                </md-field>
                <div class="md-layout">
                  <div class="md-layout-item md-size-33 mx-auto text-center">
                    <md-button class="md-success"  v-on:click="addHelp()">Send Message</md-button>
                  </div>
                </div>
               
              </form>
            </div>
          </div>

    <md-snackbar :md-position="position" :md-duration="isInfinity ? Infinity : duration" :md-active.sync="showSnackbar" md-persistent>
      <span>{{alert}}</span>
      <md-button class="md-primary" @click="showSnackbar = false">close</md-button>
    </md-snackbar>
</div>
 <div class="container" v-if="isAdmin">
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
                    <div class="col col-1" data-label="Name">
                      {{ help.name }}
                    </div>
                    <div class="col col-2" data-label="Email">
                      {{ help.email }}
                    </div>
                    <div class="col col-3" data-label="Question">
                      {{ help.question }}
                    </div>
                    <div class="col col-4" data-label="Completed">
                      {{ help.completed }}
                    </div>

                    <div class="col col-5" data-label="Reset/Delete">
                    

                      <md-button
                        class="md-info md-sm"
                        @click="markComplete(help.id)"
                        >Mark Complete</md-button
                      >
                    </div>
                  </li>
                </ul>
          </div>
        </div>
        </div>
      </div>
    </div>

</template>



<script>
import Axios, { axios } from "axios";
import { Faq } from "../models/Faq";
import { Help } from "../models/Help"
import { required, email, minLength, sameAs, maxLength } from "vuelidate/lib/validators";
export default {
  bodyClass: "FAQ_page-page",
  name: "Faq",
  props: {
    header: {
      type: String,
      default: require("@/assets/img/faqbg.jpg"),
    },
  },
  data() {
    return {
     /*faq*/
      faqs: [],
      message: "",
      answer: "",
      /*help*/
       helps: [],
       email: "",
       name: "",
       question: "",
       completed: "",
      /*validation*/
      response: null,
      error: "",
      success: false,
      fail: false,
      search: "",
      submitted:false,
      /*snackbar*/
      showSnackbar: false,
      position: 'center',
      duration: 4000,
      isInfinity: false,
      alert: null,
      isAdmin: false,
    };
    
  },

  validations: {
    name: {
      required
    },
    email: {
      required,
      email
    },
    question: {
      required,
      minLength: minLength(10),
      maxLength: maxLength(255)
    },
    answer:{
      required,
      maxLength: maxLength(100),
      minLength: minLength(50),
    },
    message: {
      required,
      maxLength: maxLength(100),
      minLength: minLength(50),
      }
  },
  beforeMount() {
    this.checkIfAdmin()
  },
 computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    },
    
    filteredFaqs: function() {
      return this.faqs.filter((faq) => { 
        return faq.message.toLowerCase().match(this.search.toLowerCase());
      })
    } 
 },
  mounted: function() {
    this.faqList();
    this.helpList();
  },
  methods: {
     checkIfAdmin(){
      if(localStorage.getItem('role') === 'ROLE_ADMIN'){
        this.isAdmin = true;
      }
  },
    /*faq*/
  addFaq() {
      console.log(this.message, this.answer);
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
   /*help*/
      helpList: function() {
	  const url = "http://localhost:8081/help/listHelps";
      Axios.get(url)
        .then(response => (this.helps = response.data))
        .catch(function(error) {
          console.warn("error occured" + error);
      });
		},
    addHelp() {

      Axios.post('http://localhost:8081/help/newHelp',
      {
        name: this.name,
        email: this.email,
        question: this.question,
        completed: 0
      }).then(reponse => {
          this.alert = 'Message posted succesfully'
          this.showSnackbar = true
            this.name = ''
            this.email = ''
            this.question = ''
          console.log(reponse);
        }, (error) => {
          this.alert = 'Question failed to submit!'
          this.showSnackbar = true
          console.log(error);
        });
    },
   
    
  }

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

.form_error {
  color: red;
  font-size: 0.75em;
  padding-left: 10px;
}
</style>
