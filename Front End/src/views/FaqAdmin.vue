<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-50 md-small-size-70 md-xsmall-size-100"
          >
            <h1 class="title">FAQ Admin Page</h1>

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
                <md-icon>add</md-icon>
                <label>NEW FAQ</label>
                <md-input v-model="message" type="message"></md-input>
              </md-field>
              <md-button v-on:click="addFaq()" slot="footer" class="md-simple md-success md-lg">Add</md-button>
            <div
              class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center"
            >
              <div>
				
                <ul class="responsive-table">
                  <li class="table-header">
                    <div class="col col-5"></div>
                  </li>
                  <li
                    class="table-row"
                    v-for="faq in faqs"
                    v-bind:key="faq.id"
                  >
                    <div class="col col-2" data-label="Faq">
                      {{ faq.message }}
                    </div>

					<div class="col col-5" data-label="delete">
					<md-button
                        class="d-raised md-primary"
                        @click="deleteFaq(faq.id)">
						Remove Faq
						</md-button>
					</div>
					</li>
					</ul>
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
import Axios, { axios } from "axios";
import { Faq } from "../models/Faq";
export default {
  bodyClass: "FaqAdmin",
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
      message: "",
	  search: "",
	  faqs: {},
    };
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
	},
  },
  mounted: function() {
    this.faqList();
  },
  methods:   {
      addFaq() {
      console.log(this.message);
      const url = "http://localhost:8081/faq/newFaq";
      const faq = new Faq(this.message);
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
 	},
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
