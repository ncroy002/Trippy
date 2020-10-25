<template>
  	<div class="card">
  	<img v-bind:src="image" alt="Avatar" style="width:100%">
		<div class="container">
			<h4><b>{{name}}</b></h4>
			<p>{{date}}</p>
			<a v-bind:href="website" target="_blank" class="btn btn-outline-primary">Official Website</a>
      <md-button class="md-success" @click="follow()">Follow</md-button>
			<div v-if= "isAdmin" class="md-layout-item md-size-33 mx-auto text-center">
          <md-button class="md-success" @click="showDialog = true">Edit</md-button>
				  <md-button class="md-success" @click="remove">DELETE</md-button>
      </div>
		</div>

		<md-dialog class="md-layout" :md-active.sync="showDialog">
        <md-dialog-title>Create New Event</md-dialog-title>

        <form novalidate class="md-layout-item" @submit.prevent="edit">
          <md-field>
              <label for="event-name">Event Name</label>
              <md-input v-model="dName" name="event-name" id="event-name" autocomplete="event-name" />
            </md-field>
            <md-field>
              <label for="event-name">Event Date</label>
              <md-input  v-model="dDate" name="event-name" id="event-name" autocomplete="event-name" />
            </md-field>
            <md-field>
              <label for="event-name">Event Website</label>
              <md-input  v-model="dWebsite" name="event-name" id="event-name" autocomplete="event-name" />
            </md-field>
            <md-field>
              <label for="event-image">Event Image</label>
              <md-input v-model="dImage" name="event-image" id="event-image" autocomplete="event-name" />
            </md-field>
            <md-dialog-actions>
            <md-button class="md-primary" @click="showDialog = false">Cancel</md-button>
            <!--<md-button class="md-primary" @click="showDialog = false">Save</md-button> -->
            <md-button type="submit" class="md-primary"  @click="showDialog = false">Save</md-button>
          </md-dialog-actions>
        </form>
      </md-dialog>

	</div>
</template>

<script>
import axios from 'axios'
export default {
  name: "event-card",
  props: {
	  	id: null,
		name: null,
		date: null,
		website:null,
		image: null,
  },
  data() {
    return {
		dId: this.id,
		dName: this.name,
		dDate : this.date,
		dWebsite: this.website,
		dImage: this.image,
    showDialog: false,
    isAdmin: false,
    email: null,
    };
  },
  beforeMount() {
    this.checkIfAdmin()
    this.email = this.$store.getters.getEmail
  },
  methods: {
  checkIfAdmin(){
      if(localStorage.getItem('role') === 'ROLE_ADMIN'){
        this.isAdmin = true;
      }
    },  
	remove(){
      let url = "http://localhost:8081/event/delete/" + this.id;
      axios.get(url,{
        headers: {
          Authorization: `${this.$store.state.token}`
        }
      }).then((response) => {
		this.message = 'Get successfully'
		this.$parent.showSnackbar = true
		this.$parent.message = "Post deleted successfully"
		this.$parent.fetchEvents();
      }, (error) => {
        this.message = 'Failed to create a post!'
		this.$parent.showSnackbar = true
		this.$parent.message = "Failed to delete the Post"
      });
	},
	edit(){
		let url = "http://localhost:8081/event/edit"
		axios.post(url, {
		"id" : this.dId,
        "name" : this.dName,
        "date" : this.dDate,
        "website" : this.dWebsite,
        "image" : this.dImage,//document.getElementById('event-image').files[0],
      },{
        headers: {
          Authorization: `${this.$store.state.token}`
        }
      }).then((response) => {
        this.$parent.message = 'Post updated successfully'
        this.$parent.showSnackbar = true
		this.dName = response.data.name
		this.dDate = response.data.date
		this.dWebsite = response.data.website
		this.dImage = response.data.image
		this.$parent.fetchEvents();
        console.log(this.result);
      }, (error) => {
        this.$parent.message = 'Failed to update the post!'
        this.$parent.showSnackbar = true
        console.log(error);
      });
  },
  follow(){
    let url = "http://localhost:8081/event/follow/" + this.dId
    console.log(this.$store)
    axios.get(url,{
        headers: {
          Authorization: `${this.$store.state.token}`,
          'email' : `${this.email}`,
        }
      }).then((response) => {
        this.$parent.message = 'Followed successfully'
        this.$parent.showSnackbar = true
        console.log(this.result);
      }, (error) => {
        this.$parent.message = 'Failed to follow event!'
        this.$parent.showSnackbar = true
        console.log(error);
      });
  }
  }
};
</script>

<style lang="css"></style>
