<template>
  <div class="wrapper">
    <div>
      <md-dialog class="md-layout" :md-active.sync="showDialog">
        <md-dialog-title>Create New Event</md-dialog-title>

        <form novalidate class="md-layout-item" @submit.prevent="create">
          <md-field>
              <label for="event-name">Event Name</label>
              <md-input v-model="form.eventname" name="event-name" id="event-name" autocomplete="event-name" />
            </md-field>
            <md-field>
              <label for="event-name">Event Date</label>
              <md-input  v-model="form.eventdate" name="event-name" id="event-name" autocomplete="event-name" />
            </md-field>
            <md-field>
              <label for="event-name">Event Website</label>
              <md-input  v-model="form.eventwebsite" name="event-name" id="event-name" autocomplete="event-name" />
            </md-field>
            <md-field>
              <label for="event-image">Event Image</label>
              <md-input  v-model="form.eventimage" name="event-image" id="event-image" autocomplete="event-image" />
            </md-field>
            <md-dialog-actions>
            <md-button class="md-primary" @click="showDialog = false">Cancel</md-button>
            <!--<md-button class="md-primary" @click="showDialog = false">Save</md-button> -->
            <md-button type="submit" class="md-primary"  @click="showDialog = false">Save</md-button>
          </md-dialog-actions>
        </form>
      </md-dialog>
      
     <!-- <md-dialog class="md-layout" :md-active.sync="showDialog">
        <md-dialog-title>Create New Event</md-dialog-title>

        <form novalidate class="md-layout-item" @submit.prevent="create">
          <md-field>
              <label for="event-name">Event Name</label>
              <md-input v-model="form.eventname" name="event-name" id="event-name" autocomplete="event-name" />
            </md-field>
            <md-field>
              <label for="event-name">Event Date</label>
              <md-input  v-model="form.eventdate" name="event-name" id="event-name" autocomplete="event-name" />
            </md-field>
            <md-field>
              <label for="event-name">Event Website</label>
              <md-input  v-model="form.eventwebsite" name="event-name" id="event-name" autocomplete="event-name" />
            </md-field>
            <md-field>
              <label for="event-image">Event Image</label>
              <md-input type="file"  accept="image/*"  v-model="form.eventimage" name="event-image" id="event-image" />
            </md-field>
            <md-dialog-actions>
            <md-button class="md-primary" @click="showDialog = false">Cancel</md-button>
            <md-button type="submit" class="md-primary"  @click="showDialog = false">Save</md-button>
          </md-dialog-actions>
        </form>
      </md-dialog> -->



    </div>
    <parallax class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-50 md-small-size-70 md-xsmall-size-100"
          >
            <h1 class="title">Events</h1>

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
              <h2 class="title text-center">Up Coming Events</h2>
              <div v-if="isAdmin" class="md-layout-item md-size-33 mx-auto text-center">
                    <md-button class="md-success" @click="showDialog = true">ADD POST</md-button>
                  </div>
            </div>

            <div
              class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center"
            >
            <div v-if="available">
      <div v-if="result">

      <event-card
            v-bind:key="result.id"
            v-bind:id="result.id"
            v-bind:name="result.name"
            v-bind:date="result.date"
            v-bind:website="result.website"
            v-bind:image="result.image"
          ></event-card>
              </div>
            </div>

          <event-card
            v-for="event in eventCards"
            v-bind:key="event.id"
            v-bind:id="event.id"
            v-bind:name="event.name"
            v-bind:date="event.date"
            v-bind:website="event.website"
            v-bind:image="event.image"
          ></event-card>
            </div>
          </div>
        </div>
      </div>
    </div>
    <md-snackbar :md-position="position" :md-duration="isInfinity ? Infinity : duration" :md-active.sync="showSnackbar" md-persistent>
      <span>{{message}}</span>
      <md-button class="md-primary" @click="showSnackbar = false">close</md-button>
    </md-snackbar>
  </div>
</template>

<style>
.card {
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  transition: 0.3s;
  border-radius: 5px;
  width: 110%;
}

.card:hover {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}

.card{
    margin-bottom: 10px;
}

</style>

<script>
import axios from 'axios'
import { EventCard } from "@/components";
export default {
  components: {
    EventCard
  },
  created(){
    this.fetchEvents()
  },
  bodyClass: "Events-page",
  props: {
    header: {
      type: String,
      default: require("@/assets/img/newEvents.jpg"),
    },
  },
  data() {
    return {
      name: null,
      email: null,
      showDialog: false,
      form: {
        eventname: null,
        eventdate: null,
        eventwebsite: null,
        eventimage: null,
      },
      showSnackbar: false,
      position: 'center',
      duration: 4000,
      isInfinity: false,
      message: null,
      result: '',
      available: false,
      eventCards : null,
      isAdmin: false,
    };
  },
  beforeMount() {
    this.checkIfAdmin()
  },
  updated(){
    var eventToScroll = this.$route.query.id
      if(eventToScroll !== null){
         this.scroll(eventToScroll)
      }
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  methods:{
    checkIfAdmin(){
      if(localStorage.getItem('role') === 'ROLE_ADMIN'){
        this.isAdmin = true;
      }
    },
    fetchEvents(){
      let url = "http://localhost:8081/event/list";
      axios.get(url,{
        headers: {
          Authorization: `${this.$store.state.token}`
        }
      }).then((response) => {
        this.message = 'Get successfully'
        this.eventCards = response.data
      }, (error) => {
        this.message = 'Failed to create a post!'
        this.showSnackbar = true
        console.log(error);
      });
    },
    create(){
      let url = "http://localhost:8081/event/create";

     
    //var image = document.getElementById('event-image').files[0];

      axios.post(url, {
        "name" : this.form.eventname,
        "date" : this.form.eventdate,
        "website" : this.form.eventwebsite,
        "image" : this.form.eventimage,//document.getElementById('event-image').files[0],
      },{
        headers: {
          Authorization: `${this.$store.state.token}`
        }
      }).then((response) => {
        this.message = 'Post created successfully'
        this.showSnackbar = true
        this.result = response.data
        this.available = true
      }, (error) => {
        this.message = 'Failed to create a post!'
        this.showSnackbar = true
        console.log(error);
      });
    },
    scroll(eventToScrollId){
       var element = document.getElementById("event_" + eventToScrollId);
       if(element !== null){
         element.scrollIntoView()
       }
    }
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
</style>