<template>
  <div id="airlineTicketSearch">
    <!-- This is for searching and getting the airports from a city -->
    <div id="airportSearch">
      <div class="title">
        <h3>Seach for your trip</h3>
      </div>
      <form>
        <div class="md-layout">
          <div class="md-layout-item">
            <md-field class="md-form-group">
              <md-icon>flight_takeoff</md-icon>
              <md-input
                v-model="departureLocation"
                placeholder="Departure Location"
              ></md-input>
            </md-field>
          </div>
          <div class="md-layout-item">
            <md-field class="md-form-group">
              <md-icon>flight_land</md-icon>
              <md-input
                v-model="destinationLocation"
                placeholder="Destination Location"
              ></md-input>
            </md-field>
          </div>
        </div>
        <!-- This will show the dropdown of getting correct locations -->
        <div
          v-if="
            this.city1Locations.length !== 0 && this.city2Locations.length !== 0
          "
        >
          <div class="md-layout">
            <div class="md-layout-item">
              <md-field>
                <md-select v-model="selectedLocation1">
                  <md-option
                    v-for="location1 in city1Locations"
                    :value="location1.PlaceId"
                    :key="location1.PlaceId"
                  >
                    {{ location1.PlaceName }}
                  </md-option>
                </md-select>
              </md-field>
            </div>
            <div class="md-layout-item">
              <md-field>
                <md-select v-model="selectedLocation2">
                  <md-option
                    v-for="location2 in city2Locations"
                    :value="location2.PlaceId"
                    :key="location2.PlaceId"
                  >
                    {{ location2.PlaceName }}
                  </md-option>
                </md-select>
              </md-field>
            </div>
          </div>
          <div class="md-layout">
            <div class="md-layout-item">
              <md-datepicker v-model="selectedDepartureDate">
                <label>Departure date</label>
              </md-datepicker>
            </div>
            <div class="md-layout-item">
              <md-datepicker v-model="selectedReturnDate">
                <label>Return date</label>
              </md-datepicker>
            </div>
          </div>
          <div class="md-layout md-alignment-center-center">
            <md-button v-on:click="getFlightDetails()"
              >Find Flight Details</md-button
            >
          </div>
        </div>
        <div
          v-if="
            !(
              this.city1Locations.length !== 0 &&
              this.city2Locations.length !== 0
            )
          "
          class="md-layout md-alignment-center-center"
        >
          <md-button v-on:click="getAirportLocations()"
            >Search Airports</md-button
          >
        </div>
      </form>
    </div>
  </div>
</template>
<script>
import Axios from "axios";
import { required, minLength } from "vuelidate/lib/validators";

export default {
  name: "trip-search",
  data() {
    return {
      departureLocation: "",
      destinationLocation: "",
      city1Locations: [],
      city2Locations: [],
      selectedLocation1: null,
      selectedLocation2: null,
      selectedDepartureDate: null,
      selectedReturnDate: null
    };
  },
  validations: {
    departureLocation: {
      required,
      minLength: minLength(3)
    },
    destinationLocation: {
      required,
      minLength: minLength(3)
    }
  },
  methods: {
    getAirportLocations() {
      const url = "http://localhost:8081/flight/find/airports";
      const city1 = this.departureLocation;
      const city2 = this.destinationLocation;
      Axios({
        url: url,
        method: "get",
        headers: {
          "Content-Type": "application/json"
        },
        params: {
          city1: city1,
          city2: city2
        }
      })
        .then(result => {
          let { city1: value1, city2: value2 } = result.data;
          let city1Places = Object.values(JSON.parse(value1).Places);
          let city2Places = Object.values(JSON.parse(value2).Places);
          city1Places.forEach(element => {
            this.city1Locations.push(element);
          });

          city2Places.forEach(element => {
            this.city2Locations.push(element);
          });
        })
        .catch(err => {
          console.log(err);
        });
    },
    //The information we want
    //Place1Id and Place2Id
    //Place1Name and Place2Name
    //MinCost
    //Carrier Name
    //Save date
    getFlightDetails() {
      let city1PlaceId = this.selectedLocation1;
      let city2PlaceId = this.selectedLocation2;
      let outbountDate = this.selectedDepartureDate.toISOString().split("T")[0];
      let inboundDate = this.selectedReturnDate.toISOString().split("T")[0];
      const url = "http://localhost:8081/flight/browse/routes";

      Axios({
        url: url,
        method: "get",
        headers: {
          "Content-Type": "application/json"
        },
        params: {
          city1: city1PlaceId,
          city2: city2PlaceId,
          date1: outbountDate,
          date2: inboundDate
        }
      })
        .then(result => {
          console.log(result);
          let { Quotes, Carriers, Places } = result.data;
          let flightData  = { Quotes: Quotes, Carriers: Carriers, Places: Places};
          this.$emit('flightData', flightData);
        })
        .catch(err => {
          console.log(err);
        });
    }
  }
};
</script>
<style></style>