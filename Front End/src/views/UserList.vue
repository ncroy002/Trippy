<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div class="md-layout-item md-size-50 md-small-size-70 md-xsmall-size-100">
            <h1 class="title">Admin Utilities</h1>
            <br />
          </div>
        </div>
      </div>
    </parallax>
    <div class="main main-raised">
      <div class="section">
        <div class="container">
          <div class="md-layout">
            <div class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center">
              <h2 class="title text-center">User Account Modifcation</h2>
            </div>

            <div class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center">
              <h5 class="description">
                USER LIST
                <!-- <md-button v-on:click="userList()" slot="footer" class="md-simple md-success md-lg">List</md-button> -->
                <template>
                  <div id="list">
                    <ul>
                      <li v-for="user in users" :key="user">
                        {{ user }}
                        <md-button class="md-warning md-sm">Reset Password</md-button>
                        <md-button class="md-danger md-sm">Delete Account</md-button>
                      </li>
                    </ul>
                  </div>
                </template>
              </h5>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Axios, { axios } from "axios";
import UserService from '../services/user.service';
export default {
  bodyClass: "UserList-page",
  
  props: {
    header: {
      type: String,
      default: require("@/assets/img/nature.jpg")
    }
  },
  name: 'User',
  data() {
    return {
      content: '',
      name: null,
      email: null,
      message: null,
      users: ["test1@test.com", "test2@test.com", "test3@test.com"]
    };
  },
  mounted() {
    UserService.getUserBoard().then (
      response => {
        this.content = response.date;
      },
      error => {
        this.content = 
        (error.response && error.response.data) ||
        error.message ||
        error.toString();
      }
    );
  },
  created: function() {
    this.userList();
  },
  methods: {
    userList: function() {
      const url = "http://localhost:8081/user/userlist";
      Axios.get(url)
        .then(function(response){
          users = userList.data[0];
        })
        .catch(function (error) {
          users = ["error occured" + error]
        });
    }
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

#list {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-direction: column;
  padding: 10px;
  width: calc(100% -20px);
  height: calc(100vh - 20px);
  color: #fff;
  background-color: #333;
}

ul {
  margin: 0;
  padding: 0;
  list-style: none;
}
li {
  display: table;
  padding: 15px;
}
</style>
