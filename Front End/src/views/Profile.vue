<template>
  <div class="wrapper">
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
    ></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="container">
          <div class="md-layout">
            <div class="md-layout-item md-size-50 mx-auto">
              <div class="profile">
                <div class="avatar">
                  <img
                    :src="img"
                    alt="Circle Image"
                    class="img-raised rounded-circle img-fluid"
                  />
                </div>
                <div class="name">
                  <div id="edit-name" v-show="toggle">
                    <h4>Edit Name</h4>
                    <form>
                      <input
                        type="text"
                        v-model.lazy="user.firstname"
                        required
                      />
                      <input
                        type="text"
                        v-model.lazy="user.lastname"
                        required
                      />
                    </form>
                  </div>
                  <div id="name">
                    <h3 class="title">
                      {{ user.firstname }} {{ user.lastname }}
                    </h3>
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
              <p>{{ user.descrip }}</p>
            </div>
          </div>
          <div class="profile-tabs">
            <tabs
              :tab-name="[
                'Recently viewed',
                'Shared trips',
                'Saved Trips',
                'Followed Events',
              ]"
              :tab-icon="['explore', 'share', 'favorite', 'rss_feed']"
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
                <div class="full-control" v-if="trips">
                  <md-list>
                    <md-list-item
                      md-expand
                      v-for="(trip, index) in trips"
                      :key="index"
                    >
                      <div class="md-layout md-alignment-center-space-between">
                        <span class="md-layout-item md-list-item-text">{{
                          trip.name
                        }}</span>
                        <md-button
                          class="md-layout-item md-raised md-accent"
                          @click="deleteTripList(index)"
                        >
                          Delete Trip
                        </md-button>
                      </div>

                      <md-list slot="md-expand">
                        <md-list-item>
                          <span class="md-list-item-text"
                            >Flight Information:</span
                          >
                          <div class="md-layout">
                            <md-card
                              v-for="(flight, flightIndex) in trip.flights"
                              :key="flightIndex"
                            >
                              <md-card-content>
                                <h3>Minimum Cost: ${{ flight.minCost }}</h3>
                                <p>
                                  Departure Place:
                                  {{ flight.city1Name }}
                                </p>
                                <p>
                                  Destination Place:
                                  {{ flight.city2Name }}
                                </p>
                                <p>Carrier: {{ flight.carrierName }}</p>
                                <p>
                                  Number of Travelers:
                                  {{ flight.noOfTravelers }}
                                </p>
                              </md-card-content>
                              <md-card-actions>
                                <md-button
                                  class="raised primary"
                                  @click="sendSummaryEmail(index, flightIndex)"
                                >
                                  Send summary email
                                </md-button>
                              </md-card-actions>
                            </md-card>
                          </div>
                        </md-list-item>
                        <md-list-item>
                          <span class="md-list-item-text"
                            >Interest Information:</span
                          >
                          <div class="md-layout">
                            <md-card
                              v-for="(interest,
                              interestIndex) in trip.interests"
                              :key="interestIndex"
                            >
                              <md-card-content>
                                <p>Address: {{ interest.address }}</p>
                                <p>County: {{ interest.county }}</p>
                                <p>State: {{ interest.state }}</p>
                                <p v-if="interest.name">
                                  name: {{ interest.name }}
                                </p>
                              </md-card-content>
                            </md-card>
                          </div>
                        </md-list-item>
                      </md-list>
                    </md-list-item>
                  </md-list>
                </div>
                <modal v-if="modalToggle" v-on:close="closeModal()">
                  <template slot="header">
                    <h4 class="modal-title">{{ modalTitle }}</h4>
                  </template>

                  <template slot="body">
                    <p>{{ modalMessage }}</p>
                  </template>

                  <template slot="footer">
                    <md-button class="md-danger md-simple" v-on:click="closeModal()"> Close </md-button>
                  </template>
                </modal>
              </template>
              <template slot="tab-pane-4">
                <div class="md-layout">
                  <div v-for="event in followedEvents" :key="event.id">
                    <a href="javascript:void(0)" @click="viewEvent(event.id)">
                      <md-card style="margin: 10px">
                        <md-card-content>
                          <p style="text-align: left; font-weight: bold">
                            <md-avatar>
                              <img v-bind:src="event.image" alt="Avatar" />
                            </md-avatar>
                            {{ event.name }}
                          </p>
                        </md-card-content>
                      </md-card>
                    </a>
                  </div>
                </div>
              </template>
            </tabs>
          </div>
          <md-button
            @click="toggle = !toggle"
            v-on:click="updateUserDetails"
            class="md-warning"
            >{{ toggle ? "save" : "edit" }}</md-button
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { Tabs } from "@/components";
import Modal from "../components/Modal.vue";

export default {
  components: {
    Tabs,
    Modal,
  },
  bodyClass: "profile-page",

  data() {
    return {
      toggle: false,
      modalMessage: "",
      modalTitle: "",
      modalToggle: false,
      trips: null,
      userEmail: null,
      followedEvents: null,
      user: {
        firstname: "Test",
        lastname: "Test",
        descrip: "Test",
        email: "",
        id: "",
      },
      tabPane1: [
        { image: require("@/assets/img/examples/studio-1.jpg") },
        { image: require("@/assets/img/examples/studio-2.jpg") },
        { image: require("@/assets/img/examples/studio-4.jpg") },
        { image: require("@/assets/img/examples/studio-5.jpg") },
      ],
      tabPane2: [
        { image: require("@/assets/img/examples/olu-eletu.jpg") },
        { image: require("@/assets/img/examples/clem-onojeghuo.jpg") },
        { image: require("@/assets/img/examples/cynthia-del-rio.jpg") },
        { image: require("@/assets/img/examples/mariya-georgieva.jpg") },
        { image: require("@/assets/img/examples/clem-onojegaw.jpg") },
      ],
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/city-profile.jpg"),
    },
    img: {
      type: String,
      default: require("@/assets/img/faces/christian.jpg"),
    },
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  mounted() {
    this.getUserDetails();
    this.getUserTrips();
    this.getFollowedEvents();
  },
  methods: {
    sendSummaryEmail(index, flightIndex) {
      let foundFlight = this.trips[index].flights[flightIndex];
      console.log(foundFlight);
      console.log(this.userEmail);
      const url = "http://localhost:8081/flight/summary";
      axios({
        url: url,
        method: "post",
        headers: {
          "Content-Type": "application/json",
        },
        params: {
          email: this.userEmail,
        },
        data: {
          city1ID: foundFlight.city1ID,
          city2ID: foundFlight.city2ID,
          city1Name: foundFlight.city1Name,
          city2Name: foundFlight.city2Name,
          minCost: foundFlight.minCost,
          carrierName: foundFlight.carrierName,
          saveDate: foundFlight.saveDate,
        },
      })
        .then((result) => {
          console.log(result);
          this.modalMessage = "Email Successfully sent!";
          this.modalTitle = "Send Summary Email";
          this.modalToggle = true;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getUserTrips() {
      let userEmail = this.$store.getters.getEmail;
      this.userEmail = userEmail;
      if (userEmail !== undefined) {
        const url = "http://localhost:8081/trip/get/trips";
        axios({
          url: url,
          method: "get",
          params: {
            email: userEmail,
          },
        })
          .then((result) => {
            console.log(result.data);
            this.trips = result.data;
            console.log(this.trips);
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    deleteTripList(index) {
      console.log(index);
      let tripListForDeletion = this.trips[index];
      const url = "http://localhost:8081/trip/delete/trip";
      axios
        .delete(url, {
          params: {
            tripName: tripListForDeletion.name,
            userEmail: this.$store.getters.getEmail,
          },
        })
        .then((result) => {
          console.log(result);
          this.trips.splice(index, 1);
          this.modalMessage = result.data;
          this.modalTitle = "Deleted Trip";
          this.modalToggle = true;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getUserDetails() {
      let url = "http://localhost:8081/user/getuser";
      // let url = "/user/getuser";
      axios
        .get(url)
        .then((response) => {
          this.user.id = response.data.id;
          this.user.email = response.data.email;
          this.user.firstname = response.data.firstname;
          this.user.lastname = response.data.lastname;
          this.user.descrip = response.data.description;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    updateUserDetails() {
      console.log("CALLED");
      let url = "http://localhost:8081/user/update";
      axios
        .post(url, {
          id: this.user.id,
          email: this.user.email,
          firstname: this.user.firstname,
          lastname: this.user.lastname,
          description: this.user.descrip,
        })
        .then(
          (response) => {
            console.log(response);
          },
          (error) => {
            console.log(error);
          }
        );
    },

    getFollowedEvents() {
      let url = "http://localhost:8081/event/userevents";
      axios
        .get(url, {
          headers: {
            Authorization: `${this.$store.state.token}`,
            email: `${this.$store.getters.getEmail}`,
          },
        })
        .then((response) => {
          this.followedEvents = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    viewEvent(eventId) {
      this.$router.push({ path: "events", query: { id: eventId } });
    },
    closeModal(){
      this.modalToggle = false;
    }
  },
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
$list-width: 1279px;

.full-control {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap-reverse;
}

.list {
  width: $list-width;
}

.full-control > .md-list {
  width: $list-width;
  max-width: 100%;
  height: 400px;
  display: inline-block;
  overflow: auto;
  border: 1px solid rgba(#000, 0.12);
  vertical-align: top;
}

.control {
  min-width: 250px;
  display: flex;
  flex-direction: column;
  padding: 16px;
}
</style>