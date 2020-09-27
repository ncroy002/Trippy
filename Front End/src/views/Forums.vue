<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div class="md-layout-item md-size-50 md-small-size-70 md-xsmall-size-100">
            <h1 class="title">Forums</h1>
            <h4>
              Here you will be able to create posts about your trips
              to share your experience with other travelers. You can even
              ask other travelers about their experiences.
            </h4>
            <br />
            <md-button href="#/recommend" class="md-success md-lg" target="_blank">
              <i class="fas fa-play"></i> Watch video
            </md-button>
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
                <nav-tabs-card no-label>
                  <template slot="content">
                    <md-tabs class="md-primary" md-alignment="left">
                      <md-tab id="tab-post" md-label="Post" md-icon="chat">
                        <!--
                        <span>Multiline message is:</span>
                          <p style="white-space: pre-line;">{{ message }}</p>
                            <br>
                        -->
                        <input size="66" v-model="message" placeholder="Title..." />
                        <br />
                        <textarea rows="15" cols="50" v-model="message2" placeholder="Post..."></textarea>
                        <br />
                        <md-button class="md-success">Post</md-button>
                      </md-tab>

                      <md-tab id="tab-images-video" md-label="Images & Video" md-icon="chat">
                        <input size="66" v-model="message" placeholder="Title..." />
                        <br />
                        <textarea rows="15" cols="50" v-model="message2" placeholder="Post..."></textarea>
                        <br />
                        <md-button class="md-success">Post</md-button>
                        <br />
                      </md-tab>
                    </md-tabs>
                  </template>
                </nav-tabs-card>
              </div>
            </div>
          </div>
          <template>
            <div>
              <md-card>
                <img src="@/assets/img/bg.jpg" alt="Avatar" style="width:100px;height:100px;" />
                <p>John C.</p>
                <div class="centered">
                  <p>
                    <strong>New York City, New York</strong>
                    <br /> Sep 22, 2019, 8:38 PM
                    <br /> WOW, this is place is nice
                  </p>
                </div>
                <md-card-actions>
                  <md-button class="md-success">Reply</md-button>
                </md-card-actions>
              </md-card>

              <md-card>
                <img src="@/assets/img/bg2.jpg" alt="Avatar" style="width:100px;height:100px;" />
                <p>Thomas K.</p>
                <div class="centered">
                  <p>
                    <strong>Austin, Texas</strong>
                    <br /> Aug 3, 2019, 2:21 PM
                    <br /> I can't believe hot it is over here
                  </p>
                </div>
                <md-card-actions>
                  <md-button class="md-success">Reply</md-button>
                </md-card-actions>
              </md-card>

              <md-card>
                <img src="@/assets/img/bg3.jpg" alt="Avatar" style="width:100px;height:100px;" />
                <p>Andrew F.</p>
                <div class="centered">
                  <p>
                    <strong>Seattle, Washington</strong>
                    <br /> March 9, 2019, 10:00 AM
                    <br /> It's always so cloudy over here
                  </p>
                </div>
                <md-card-actions>
                  <md-button class="md-success">Reply</md-button>
                </md-card-actions>
              </md-card>
            </div>
          </template>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { NavTabsCard } from "@/components";

export default {
  components: {
    NavTabsCard
  },

  bodyClass: "landing-page",
  props: {
    header: {
      type: String,
      default: require("@/assets/img/bg7.jpg")
    },
    teamImg1: {
      type: String,
      default: require("@/assets/img/faces/avatar.jpg")
    },
    teamImg2: {
      type: String,
      default: require("@/assets/img/faces/christian.jpg")
    },
    teamImg3: {
      type: String,
      default: require("@/assets/img/faces/kendall.jpg")
    }
  },
  data() {
    return {
      name: null,
      email: null,
      message: null
    };
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
</style>
