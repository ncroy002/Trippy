<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="container">
          <div class="md-layout">
            <div class="md-layout-item md-size-50 mx-auto">
              <div class="profile">
                <div class="avatar">
                  <img :src="img" alt="Circle Image" class="img-raised rounded-circle img-fluid" />
                </div>
                <div class="name">
                  <div id="edit-name" v-show="toggle">
                    <h4>Edit Name</h4>
                    <form>
                      <input type="text" v-model.lazy="user.firstname" required />
                      <input type="text" v-model.lazy="user.lastname" required />
                    </form>
                  </div>
                  <div id="name">
                    <h3 class="title">{{user.firstname}} {{user.lastname}}</h3>
                  </div>
                  <h6>New User</h6>
                </div>
              </div>
            </div>
          </div>
          <div class="description text-center">
            <div id="edit-description" v-show="toggle">
              <h4>Edit Description</h4>
              <form>
                <input type="text" v-model.lazy="user.descrip" required />
              </form>
            </div>
            <div id="description">
              <h4>Description</h4>
              <p>{{user.descrip}}</p>
            </div>
          </div>
                    <div class="profile-tabs">
            <tabs
              :tab-name="['Recently viewed', 'Shared trips', 'Favorite']"
              :tab-icon="['explore', 'share', 'favorite']"
              plain
              nav-pills-icons
              color-button="success"
            >
              <!-- here you can add your content for tab-content -->
              <template slot="tab-pane-1">
                <div class="md-layout">
                  <div class="md-layout-item md-size-25 ml-auto">
                    <img :src="tabPane1[0].image" class="rounded" />
                    <img :src="tabPane1[1].image" class="rounded" />
                  </div>
                  <div class="md-layout-item md-size-25 mr-auto">
                    <img :src="tabPane1[3].image" class="rounded" />
                    <img :src="tabPane1[2].image" class="rounded" />
                  </div>
                </div>
              </template>
              <template slot="tab-pane-2">
                <div class="md-layout">
                  <div class="md-layout-item md-size-25 ml-auto">
                    <img :src="tabPane2[0].image" class="rounded" />
                    <img :src="tabPane2[1].image" class="rounded" />
                    <img :src="tabPane2[2].image" class="rounded" />
                  </div>
                  <div class="md-layout-item md-size-25 mr-auto">
                    <img :src="tabPane2[3].image" class="rounded" />
                    <img :src="tabPane2[4].image" class="rounded" />
                  </div>
                </div>
              </template>
              <template slot="tab-pane-3">
                <div class="md-layout">
                  <div class="md-layout-item md-size-25 ml-auto">
                    <img :src="tabPane3[0].image" class="rounded" />
                    <img :src="tabPane3[1].image" class="rounded" />
                  </div>
                  <div class="md-layout-item md-size-25 mr-auto">
                    <img :src="tabPane3[2].image" class="rounded" />
                    <img :src="tabPane3[3].image" class="rounded" />
                    <img :src="tabPane3[4].image" class="rounded" />
                  </div>
                </div>
              </template>
            </tabs>
          </div>
          <md-button @click='toggle = !toggle' v-on:click="updateUserDetails" class="md-warning">{{toggle ? 'save' : 'edit'}}</md-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { Tabs } from "@/components";
export default {
  components: {
    Tabs
  },
  bodyClass: "profile-page",
  data() {
    return {
      toggle: false,
      user: {
        firstname: 'Test',
        lastname: 'Test',
        descrip: 'Test',
        email:'',
        id:''
      },
      tabPane1: [
        { image: require("@/assets/img/examples/studio-1.jpg") },
        { image: require("@/assets/img/examples/studio-2.jpg") },
        { image: require("@/assets/img/examples/studio-4.jpg") },
        { image: require("@/assets/img/examples/studio-5.jpg") }
      ],
      tabPane2: [
        { image: require("@/assets/img/examples/olu-eletu.jpg") },
        { image: require("@/assets/img/examples/clem-onojeghuo.jpg") },
        { image: require("@/assets/img/examples/cynthia-del-rio.jpg") },
        { image: require("@/assets/img/examples/mariya-georgieva.jpg") },
        { image: require("@/assets/img/examples/clem-onojegaw.jpg") }
      ],
      tabPane3: [
        { image: require("@/assets/img/examples/mariya-georgieva.jpg") },
        { image: require("@/assets/img/examples/studio-3.jpg") },
        { image: require("@/assets/img/examples/clem-onojeghuo.jpg") },
        { image: require("@/assets/img/examples/olu-eletu.jpg") },
        { image: require("@/assets/img/examples/studio-1.jpg") }
      ]
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/city-profile.jpg")
    },
    img: {
      type: String,
      default: require("@/assets/img/faces/christian.jpg")
    }
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    }
  },mounted () {
    this.getUserDetails();
  },
  methods:{
    getUserDetails() {
      let url = "http://localhost:8081/user/getuser";
     // let url = "/user/getuser";
      axios.get(url).then((response) => {
        this.user.id = response.data.id
        this.user.email = response.data.email
        this.user.firstname = response.data.firstname
        this.user.lastname = response.data.lastname
        this.user.descrip = response.data.description
      }).catch( error => { console.log(error); });
    },
    updateUserDetails(){
      console.log('CALLED')
      let url = "http://localhost:8081/user/update";
      axios.post(url, {
        "id" : this.user.id,
        "email" : this.user.email,
        "firstname" : this.user.firstname,
        "lastname" : this.user.lastname,
        "description" : this.user.descrip
      }).then((response) => {
        console.log(response);
      }, (error) => {
        console.log(error);
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.section {
  padding: 0;
}
.profile-tabs::v-deep {
  .md-card-tabs .md-list {
    justify-content: center;
  }
  [class*="tab-pane-"] {
    margin-top: 3.213rem;
    padding-bottom: 50px;
    img {
      margin-bottom: 2.142rem;
    }
  }
}
</style>