<template>
  	<div :id="'event_' + id" class="card">
  	<img v-bind:src="image" alt="Avatar" style="width:100%">
		<div class="container">
			<h4><b>{{name}}</b></h4>
			<p>{{date}}</p>
			<a v-bind:href="website" target="_blank" class="btn btn-outline-primary">Official Website</a>
      <md-button class="md-success" @click="follow()">Follow</md-button>

		<div>
      <div>
        <div v-for="item in commentList" :key="item.id">
            <md-card style="margin:10px;">
              <md-card-content>
                <p style="text-align:left; font-weight:bold;">
                  <md-avatar>
                    <img src="https://via.placeholder.com/150?text=PIC" alt="Avatar">
                </md-avatar>
                  {{item.account.firstName}} {{item.account.lastName}}</p>
                <p style="text-align:left;">{{ item.commentText }} <a href="javascript:void(0)" style="float:right;" @click="removeComment(item.id)"><md-icon>delete</md-icon> </a></p>
              </md-card-content>
            </md-card>
        </div>
      </div>
      <div>
        <md-field>
          <md-input placeholder="Write a comment" v-model="comment" name="event-comment" id="event-comment" />
          <a href="javascript:void(0)" @click="commentIt"><md-icon>send</md-icon></a>
        </md-field>
      </div>
    </div>

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
    comment: null,
    commentList:null,
    };
  },
  beforeMount() {
    this.checkIfAdmin()
    this.email = this.$store.getters.getEmail
    this.getComments()
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
      }, (error) => {
        this.$parent.message = 'Failed to follow event!'
        this.$parent.showSnackbar = true
        console.log(error);
      });
  },
  commentIt(){
    let url = "http://localhost:8081/event/addcomment/" + this.dId
    axios.post(url,{
      "commentText" : this.comment
    },{
        headers: {
          Authorization: `${this.$store.state.token}`,
          'email' : `${this.email}`,
        }
      }).then((response) => {
        this.$parent.message = 'Commented successfully'
        this.$parent.showSnackbar = true
        this.getComments();
      }, (error) => {
        this.$parent.message = 'Failed to comment!'
        this.$parent.showSnackbar = true
        console.log(error);
      });
  },
  getComments(){
    let url = "http://localhost:8081/event/comments/" + this.id;
      axios.get(url,{
        headers: {
          Authorization: `${this.$store.state.token}`
        }
      }).then((response) => {
        this.commentList = response.data
      }, (error) => {
        this.message = 'Failed to fetch data!'
		    this.$parent.showSnackbar = true
		    this.$parent.message = "Failed to fetch data!"
      });
  },
  removeComment(commentId){
      let url = "http://localhost:8081/event/removecomment/" + commentId;
      axios.get(url,{
        headers: {
          Authorization: `${this.$store.state.token}`
        }
      }).then((response) => {
		this.$parent.showSnackbar = true
		this.$parent.message = "Comment deleted successfully"
		this.getComments();
      }, (error) => {
		this.$parent.showSnackbar = true
		this.$parent.message = "Failed to delete comment"
      });
  }
  }
};
</script>

<style lang="css"></style>
