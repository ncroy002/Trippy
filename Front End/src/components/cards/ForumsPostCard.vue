<template>
  <div class="wrapper">
    <div class="container">
      <div class="md-layout">
        <div
          class="md-layout-item md-size-100 md-small-size-100 mx-auto text-left"
        >
        <div>
          <!--
          <div
            v-bind:key="forumspostcard.id"
            v-for="forumspostcard in forumspostcard"
          >
          -->
            <md-card>
              <md-field>
                <div id="post-content">
                  <div class="img-wrapper">
                    <img
                      src="@/assets/img/bg.jpg"
                      alt="Avatar"
                      style="width:100px;height:100px;"
                    />
                  </div>
                  <p>{{ forumspostcard.forumUser }}</p>
                </div>
                <p>
                  <strong> {{ forumspostcard.forumTitle }} </strong>
                  <br />
                  {{ currentDate() }} <br />
                  {{ forumspostcard.forumPost }}
                </p>
              </md-field>
              
              <md-field>
                <label>Reply</label>
                <md-textarea v-model="myComment" type="myComment"></md-textarea>
              </md-field>

              <div class = "md-layout-item text-right">
                <md-button v-on:click="$emit('add-comment', myComment)" type="addComment" class="md-success md-sm">Reply</md-button>
              </div>   
            </md-card>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
//import Axios, { axios } from "axios";
//import { Account } from "../models/Account";

export default {
  name: "Forum-Post-Card",
  props: ["forumspostcard"],
  components: {
  },
  data() {
    return {
      myComment: ""
     /*
      commentcard:[
        {
          id: 1,
          commentComment: "Comment",
          commentUser: "User 10"
        }
      ]
      */
    };
  },
  methods: {
    currentDate() {
      var n = new Date();
      var month = n.getMonth() + 1;
      var day = n.getDate();
      var year = n.getFullYear();
      var time = n.getSeconds();
      var myDate = month + "/" + day + "/" + year + " ";
      return myDate;
    }
    /*,
    addCommentReply(){
      const newComment = {
        commentReal: this.commentReal,
        commentUser: ""
      }
      this.$emit('add-comment', this.newComment);
    }
    */
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
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
</style>