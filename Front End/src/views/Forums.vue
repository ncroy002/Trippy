<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-50 md-small-size-70 md-small-size-100"
          >
            <h1 class="title">Forums</h1>
            <h4>
              Here you will be able to create posts about your trips to share
              your experience with other travelers. You can even ask other
              travelers about their experiences.
            </h4>
            <!--
            <md-button href="#/recommend" class="md-success md-lg" target="_blank">
              <i class="fas fa-play"></i> Watch video
            </md-button>
            -->
            
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
              <div
                class="md-layout-item md-size-100 md-small-size-100 mx-auto text-center"
              >
                <nav-tabs-card no-label v-if="isLoggedIn">
                  <template slot="content">
                    <md-tabs class="md-primary" md-alignment="left">
                      <md-tab id="tab-post" md-label="Post" md-icon="chat">
                        
                        <!--
                        <span>Multiline message is:</span>
                          <p style="white-space: pre-line;">{{ message }}</p>
                            <br>
                        -->
                        
                        <md-field>
                          <label>Title...</label>
                          <md-input v-model="forumTitle" type="forumTitle"></md-input>
                        </md-field>

                        <md-field>
                          <label>Post...</label>
                          <md-input v-model="forumPost" type="forumPost"></md-input>
                        </md-field>

                        <md-button :disabled="!isDisabled" v-on:click="addForumsBackend()" class="md-success">post</md-button>
                      </md-tab>

                      <md-tab id="tab-images" md-label="Images" md-icon="chat">
                        <md-field>
                          <label>Title...</label>
                          <md-input v-model="forumTitle"></md-input>
                        </md-field>
                        <br />
                        <!--
                        <md-field>
                          <md-file v-model="imageData" type="imageData" ref="fileInput" accept="image/png,image/jpeg" />
                        </md-field>
                        <br />
                        <md-button v-on:click="onSelectFile" class="md-success">post</md-button>
                        -->
                        <div>
                          <input type="button" value="Import Image" @click="chooseImage"/>
                          <input class="file-input" ref="fileInput" type="file" accept="image/png, image/jpeg">
                        </div>
                        <md-button v-on:click="addForumsBackend2()" class="md-success">post</md-button>
                      </md-tab>
                    </md-tabs>
                  </template>
                </nav-tabs-card>
              </div>
            </div>
          </div>
          
          
            <div v-bind:key="forumspostcard.id" v-for="forumspostcard in forumspostcard">
              <ForumsPostCard v-bind:forumspostcard="forumspostcard" v-on:add-comment="addComment($event, forumspostcard.id)"/>
              <CommentCard v-bind:commentcard="forumspostcard.commentcard"/>
            </div>
          
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Axios, { axios } from "axios";
import { Forums } from "../models/Forums";
import { NavTabsCard } from "@/components";
import { ForumsPostCard } from "@/components";
import { CommentCard } from "@/components";
export default {

  components: {
    NavTabsCard,
    ForumsPostCard,
    CommentCard
  },

  bodyClass: "forums-page",
  props: {
    header: {
      type: String,
      default: require("@/assets/img/bg7.jpg")
    }
  },
  data() {
    return {
      forumTitle: null,
      forumPost: null,
      forumPostDate: null,
      forumUser: null,
      forumImage: null,
      forumspostcard:[{commentcard:[{}]}],
    };
  },
  mounted: function(){
    this.forumsList();
  },
  methods:{
    chooseImage () {
      this.$refs.fileInput.click()
    },
    getCurrentDate(){
      var n = new Date();
      var month = n.getMonth() + 1;
      var day = n.getDate();
      var year = n.getFullYear();
      var time = n.getSeconds();
      var myDate = month + "/" + day + "/" + year + " ";
      return myDate;
    },
    addForumsBackend(){
      this.forumUser = this.$store.getters.getEmail;
      this.forumPostDate = this.getCurrentDate();
      //Send the information of the post to the database
      console.log(this.forumTitle, this.forumPostDate, this.forumPost, this.forumUser, this.forumImage);
      const url = "http://localhost:8081/forums/newForums";
      const forum = new Forums(this.forumTitle, this.forumPostDate, this.forumPost, this.forumUser, this.forumImage);
      Axios.post(url, forum, {params: {
        header: {
          "Content-Type": "application/json",
        }
      }})
      .then(reponse => {
        console.log(reponse);
      })
      .catch(error => {
        console.log(error);
      })

      const newForumsPost = {
        id: 4,
        forumTitle: this.forumTitle,
        forumPost: this.forumPost,
        forumPostDate: this.forumPostDate,
        forumImage: null,
        forumUser: this.forumUser,
        commentcard:[{
          commentComment: "Comment",
          commentUser: "User"
        }]
      }
      this.forumspostcard = [...this.forumspostcard, newForumsPost];
      this.forumTitle = null
      this.forumPost = null
    },
    addForumsBackend2() {
      this.forumUser = this.$store.getters.getEmail;
      this.forumPostDate = this.getCurrentDate();
      //Send the information of the post to the database
      this.forumImage = null;
      console.log(this.forumTitle, this.forumPostDate, this.forumPost, this.forumUser, this.forumImage);
      const url = "http://localhost:8081/forums/newForums";
      const forum = new Forums(this.forumTitle, this.forumPostDate, this.forumPost, this.forumUser, this.forumImage);
      Axios.post(url, forum, {params: {
        header: {
          "Content-Type": "application/json",
        }
      }})
      .then(reponse => {
        console.log(reponse);
      })
      .catch(error => {
        console.log(error);
      })
      const input = this.$refs.fileInput
      const files = input.files
      if (files && files[0]) {
        const reader = new FileReader
        reader.onload = event => {
          this.forumImage = event.target.result
          const newForumsPost = {
            id: 5,
            forumTitle: this.forumTitle,
            forumPost: null,
            forumImage: this.forumImage,
            forumPostDate: this.forumPostDate,
            forumUser: this.forumUser,
            commentcard:[{
              commentComment: "Comment",
              commentUser: "User"
            }]  
          }
        this.forumspostcard = [...this.forumspostcard, newForumsPost];
        }
        reader.readAsDataURL(files[0])
        this.$emit('input', files[0])  
      }
    },
    
    addComment(myComment, id){
     const newComment = {
       id: 3,
       commentComment: myComment,
       commentUser: "NewCommentUser"
     }
     
     this.forumspostcard[id-1].commentcard = [...this.forumspostcard[id-1].commentcard, newComment];
    },
    
    forumsList : function(){
      const url = "http://localhost:8081/forums/getForums";
        Axios.get(url)
          .then(response => (this.forumspostcard = response.data))
          .catch(error => console.log(error));
    }
  },
  computed: {
    isLoggedIn : function(){ return this.$store.getters.isLoggedIn},
    getFirstName: function(){ return this.$store.getters.getFirstName},
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    },
    isDisabled(){
      return this.forumTitle && this.forumPost;
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
.centered {
  position: absolute;
  top: 0%;
  left: 15%;
  transform: translate(-0%, 15%);
}
.img-wrapper {
  display: inline-block;
  height: 100px;
  overflow: hidden;
  width: 100px;
}
.base-image-input {
  display: block;
  width: 200px;
  height: 200px;
  cursor: pointer;
  background-size: cover;
  background-position: center center;
}.placeholder {
  background: #F0F0F0;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #333;
  font-size: 18px;
  font-family: Helvetica;
}.placeholder:hover {
  background: #E0E0E0;
}.file-input {
  display: none;
}
</style>
