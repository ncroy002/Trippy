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
          <md-button @click='toggle = !toggle' v-on:click="updateUserDetails" class="md-warning">{{toggle ? 'save' : 'edit'}}</md-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  components: {
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
    }
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
