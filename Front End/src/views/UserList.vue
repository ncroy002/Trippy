<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-50 md-small-size-70 md-xsmall-size-100"
          >
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
            <div
              class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center"
            >
              <h2 class="title text-center">Account Modification</h2>
            </div>
            
            <div class="form-group bmd-form-group">
              <label class="bmd-label-floating">Fist Name</label>
              <input type="text" class="form-control" />
            </div>

            <div
              class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center"
            >
              <div class="container">
                <h2>User List</h2>
                <ul class="responsive-table">
                  <li class="table-header">
                    <div class="col col-1">User ID</div>
                    <div class="col col-2">Email</div>
                    <div class="col col-3">First Name</div>
                    <div class="col col-4">Last Name</div>
                    <div class="col col-5"></div>
                  </li>
                  <li
                    class="table-row"
                    v-for="user in users"
                    v-bind:key="user.id"
                  >
                    <div class="col col-1" data-label="User ID">
                      {{ user.id }}
                    </div>
                    <div class="col col-2" data-label="Email">
                      {{ user.email }}
                    </div>
                    <div class="col col-3" data-label="First Name">
                      {{ user.firstName }}
                    </div>
                    <div class="col col-4" data-label="Last Name">
                      {{ user.lastName }}
                    </div>
                    <div class="col col-5" data-label="Reset/Delete">
                      <md-button class="md-warning md-sm"
                        >Reset Password</md-button
                      >
                      <md-button
                        class="md-danger md-sm"
                        @click="deleteUser(user.id)"
                        >Delete Account</md-button
                      >
                    </div>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>



<script>
import Axios, { axios } from "axios";

export default {
  bodyClass: "UserList-page",
  props: {
    header: {
      type: String,
      default: require("@/assets/img/nature.jpg")
    }
  },
  data() {
    return {
      name: null,
      email: null,
      message: null,
      users: {}
    };
  },
  mounted: function() {
    this.userList();
  },
  methods: {
    deleteUser(id) {
      // console.log("yeeeeeeeeeeeeetttttttttt");
      // const url = "http://localhost:8081/user/deleteUser";
      // Axios.post(url, id, {
      //   params: {
      //     header: {
      //       "Content-Type": "application/json"
      //     }
      //   }
      // })
      //   .then(response => {
      //     console.log(response);
      //   })
      //   .catch(error => {
      //     console.log("This is an error: " + error);
      //   });

      // Axios({
      //   url: "http://localhost:8080/user/deleteUser",
      //   method: "post",
      //   headers: { "Content-Type": "application/json" },
      //   body: {}
      // })
      //   .then(response => {
      //     console.log(response.data);
      //   })
      //   .catch(error => {
      //     console.error(error);
      //   });

      url: "http://localhost:8080/user/deleteUser";
      Axios.post(url, {
        ID: id
      })
        .then(response => {
          console.log(response);
        })
        .catch(error => {
          console.log("Error encountered: " + error);
        });
    },
    userList: function() {
      const url = "http://localhost:8081/user/listUsers";
      Axios.get(url)
        .then(response => (this.users = response.data))

        .catch(function(error) {
          console.log("error occured" + error);
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

ul {
  margin: 0;
  padding: 0;
  list-style: none;
}

body {
  font-family: "lato", sans-serif;
}
.container {
  max-width: 1000px;
  margin-left: auto;
  margin-right: auto;
  padding-left: 10px;
  padding-right: 10px;
}

h2 {
  font-size: 26px;
  margin: 20px 0;
  text-align: center;
  small {
    font-size: 0.5em;
  }
}

.responsive-table {
  li {
    border-radius: 3px;
    padding: 25px 30px;
    display: flex;
    justify-content: space-between;
    margin-bottom: 25px;
  }
  .table-header {
    background-color: #95a5a6;
    font-size: 14px;
    text-transform: uppercase;
    letter-spacing: 0.03em;
  }
  .table-row {
    background-color: #ffffff;
    box-shadow: 0px 0px 9px 0px rgba(0, 0, 0, 0.1);
  }
  .col-1 {
    flex-basis: 10%;
  }
  .col-2 {
    flex-basis: 30%;
  }
  .col-3 {
    flex-basis: 20%;
  }
  .col-4 {
    flex-basis: 20%;
  }
  .col-5 {
    flex-basis: 20%;
  }

  @media all and (max-width: 767px) {
    .table-header {
      display: none;
    }

    li {
      display: block;
    }
    .col {
      flex-basis: 100%;
    }
    .col {
      display: flex;
      padding: 10px 0;
      &:before {
        color: #6c7a89;
        padding-right: 10px;
        content: attr(data-label);
        flex-basis: 50%;
        text-align: right;
      }
    }
  }
}
</style>
