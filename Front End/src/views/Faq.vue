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
            <div
              class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center"
            >
              <h2 class="title text-center">Frequently Asked Questions</h2>
            </div>
             <div
              class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center"
            >
              <md-field class="md-form-group" slot="inputs">
                <md-icon>search</md-icon>
                <label>SEARCH FAQS</label>
                <md-input v-model="faq" type="faq"></md-input>
              </md-field>
            </div>

            <div
              class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center"
            >
              <md-field class="md-form-group" slot="inputs">
                <md-icon>add</md-icon>
                <label>NEW FAQ</label>
                <md-input v-model="faq" type="faq"></md-input>
              </md-field>
            </div>

            <div
              class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center"
            >
              <h5 class="description">
               {{faq}}
              </h5>


              ********TESTING

               <div id="app">
            {{count}}
            <button @click='increment'>+</button>
        </div>


          <div id="app2">
            {{count}}
            <button @click='decrement'>-</button>
        </div>


             count: {{count}}
             Completed todos: {{doneToDosCount}} 

             *************TESTING


              <h5 class="description">
                How do I update my email address or account information?
              </h5>
              <h5 class="description">
                I had a great price yesterday, but now the rate is higher. Can you give me the original price?
              </h5>
               <h5 class="description">
                Where is my reservation?
              </h5>
            </div>
             <div
              class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center"
            >
              <h2 class="title text-center">Need Help?</h2>
            </div>
  <div
              class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center"
            >
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
        </div>
      </div>
    </div>
  </div>
</template>



<script>
import { mapState, mapMutations} from 'vuex'
import {mapGetters} from 'vuex';
export default {
  bodyClass: "FAQ_page-page",
  props: {
    header: {
      type: String,
      default: require("@/assets/img/bg3.jpg"),
    },
  },
  data() {
    return {
      name: null,
      email: null,
      message: null,
      faq: "",
      localCount: 4,
    };
  },

  methods: mapMutations([
    'increment',
    'incrementBy',
    'decrement', 
    ]),
  computed: 
    // mapGetters([
    //   'doneToDos', 'doneToDosCount', 'getToDoById',
    //   ]),
    mapState ([
        'count'
      ]),
  
  //  headerStyle() {
  //     return {
  //       backgroundImage: `url(${this.header})`,
  //      };
  //    },
  //   doneToDosCount() {
  //     return this.$store.getters.doneToDosCount;
  //   }
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
</style>