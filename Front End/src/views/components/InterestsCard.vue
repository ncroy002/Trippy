<template>
  <div>
    <div class="md-layout md-alignment-center-center">
      <h3>Activities You'll Love:</h3>

      <md-field>
        <label for="selectedTripList">
         <h6> Save Interest To: </h6>
        </label>
        <md-select v-model="selectedTripList" name="selectedTripList">
          <md-option
            v-for="(list, index) in tripsList"
            :key="index"
            :value="list"
          >
            {{ list }}
          </md-option>

          <md-button v-on:click="isOpenC = !isOpenC"> new trip list </md-button>
        </md-select>
      </md-field>

      <md-field v-show="isOpenC">
        <label>New List Name</label>
        <md-input v-model="newList"></md-input>
        <md-button
          v-on:click="
            isOpenC = !isOpenC;
            createTripList();
            getTripList();
          "
        >
          create
        </md-button>
      </md-field>
    </div>
    <div>
      <md-card
        class="md-layout md-with-hover"
        v-for="(interest, index) in interest_data"
        :key="index"
      >
        <md-card-content>
          <h3>{{ interest.properties.name }}</h3>
          <p>
            State:
            {{ interest.properties.state }}
          </p>
          <p>
            County:
            {{ interest.properties.county }}
          </p>
          <p>
            Address:
            {{ interest.properties.address_line2 }}
          </p>
        </md-card-content>
        <md-card-actions>
          <div class="flex content-start items-start" v-if="userEmail">
            <md-button
              class="md-primary"
              v-on:click="
                getTripList;
                saveInterest(index);
              "
              @click="isOpenB = !isOpenB"
            >
              Save
            </md-button>
          </div>
        </md-card-actions>
      </md-card>
    </div>
  </div>
</template>

<script>
import Axios from "axios";
export default {
  name: "interest-card",
  props: {
    interest_data: Array,
  },
  data() {
    return {
      selectedInterest: undefined,
      userEmail: undefined,
      valid: false,
      isOpenB: false,
      isOpenC: false,
      selectedTripList: null,
      newList: null,
      tripsList: this.savedTripList,
    };
  },
  methods: {
    getTripList() {
      const url = "http://localhost:8081/trip/get/all";
      if (this.userEmail !== undefined) {
        this.valid = true;
      } else {
        this.valid = false;
      }
      if (this.valid) {
        Axios({
          url: url,
          method: "get",
          headers: {
            "Content-Type": "application/json",
            email: this.userEmail,
          },
          data: {},
        })
          .then((result) => {
            console.log(result);
            this.tripsList = result.data;
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    createTripList() {
      const url = "http://localhost:8081/trip/create";
      let newList = this.newList;
      if (this.userEmail !== undefined) {
        this.valid = true;
      } else {
        this.valid = false;
      }
      if (this.valid) {
        Axios({
          url: url,
          method: "post",
          headers: {
            "Content-Type": "application/json",
            email: this.userEmail,
            trip_name: newList,
          },
          data: {},
        })
          .then((result) => {
            console.log(result);
            this.tripsList = this.getTripList();
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    saveInterest(index) {
      if (this.selectedTripList) {
        let interest = this.interest_data[index];
        console.log(index);
        console.log("interest: " + interest);
        let address = interest.properties.address_line2;
        let name = interest.properties.name;
        let state = interest.properties.state;
        let county = interest.properties.county;
        let listName = this.selectedTripList;

        if (this.userEmail !== undefined) {
          this.valid = true;
        } else {
          this.valid = false;
        }
        const url = "http://localhost:8081/interest/save";
        if (this.valid) {
          Axios({
            url: url,
            method: "post",
            headers: {
              "Content-Type": "application/json",
              email: this.userEmail,
              list: listName,
            },
            data: {
              name: name,
              state: state,
              county: county,
              address: address,
            },
          })
            .then((result) => {
              console.log(result);
            })
            .catch((err) => {
              console.log(err);
            });
        }
      }
    },
  },
  mounted() {
    this.userEmail = this.$store.getters.getEmail;
    this.tripsList = this.getTripList();
  },
};
</script>

<style lang="scss" scoped>
</style>
