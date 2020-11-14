<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div class="md-layout-item md-size-50 md-small-size-70 md-small-size-100">
            <h1 class="title">Forums</h1>
            <h4>
              Here you will be able to create posts about your trips
              to share your experience with other travelers. You can even
              ask other travelers about their experiences.
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
            <div class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center">
              <div class="md-layout-item md-size-100 md-small-size-100 mx-auto text-center">
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

                        <md-button :disabled='!isDisabled' v-on:click="addForumsBackend()" class="md-success" >post</md-button>
                      </md-tab>

                      <md-tab id="tab-images-video" md-label="Images & Video" md-icon="chat">
                        <md-field>
                          <label>Title...</label>
                          <md-input v-model="forumTitle"></md-input>
                        </md-field>
                        <br />
                        <form action="/action_page.php">
                          <input type="file" id="myPostImage" name="imagename" accept="image/x-png,image/gif,image/jpeg" />
                        </form>
                        <br />
                        <md-button v-on:click="addForumsBackend()" class="md-success">post</md-button>
                      </md-tab>
                    </md-tabs>
                  </template>
                </nav-tabs-card>
              </div>
            </div>
          </div>
          <!--
            <div v-bind:key="forumspostcard.id" v-for="forumspostcard in forumspostcard">
              <ForumsPostCard v-bind:forumspostcard="forumspostcard" v-on:add-comment="addComment($event, forumspostcard.id)"/>
              <CommentCard v-bind:commentcard="forumspostcard.commentcard"/>
            </div>
          -->
          <div v-bind:key="forumspostcard.id" v-for="forumspostcard in forumspostcard">
              <ForumsPostCard v-bind:forumspostcard="forumspostcard"/>
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
    //CommentCard
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
      forumTitle: "",
      forumPost: "",
      forumPostDate: "",
      forumUser: "",
      forumspostcard:[/*{commentcard:[{}]}*/]
    };
  },
  mounted: function(){
    this.forumsList();
  },
  methods:{
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
      
      this.forumPostDate = this.getCurrentDate();
      //Send the information of the post to the database
      console.log(this.forumTitle, this.forumPostDate, this.forumPost, this.forumUser);
      const url = "http://localhost:8081/forums/newForums";
      const forum = new Forums(this.forumTitle, this.forumPostDate, this.forumPost, this.forumUser);
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
        commentcard:[{
          commentComment: "Comment",
          commentUser: "User"
        }]
        
      }
      this.forumspostcard = [...this.forumspostcard, newForumsPost];
      
    },
    /*
    addComment(myComment, id){
     const newComment = {
       id: 3,
       commentComment: myComment,
       commentUser: "NewCommentUser"
     }
     
     this.forumspostcard[id-1].commentcard = [...this.forumspostcard[id-1].commentcard, newComment];
    }
    */
    forumsList : function(){
      const url = "http://localhost:8081/forums/getForums";
        Axios.get(url)
          .then(response => (this.forumspostcard = response.data))
          .catch(error => console.log(error));
      }
  },
  computed: {
    isLoggedIn : function(){ return this.$store.getters.isLoggedIn},
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
</style>
