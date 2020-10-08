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
        <div class="md-layout md-alignment-center-center">
          <md-button v-on:click="getAirportLocations()">Search</md-button>
        </div>
      </form>
    </div>
	<!-- This will show the dropdown of getting correct locations -->
	<div v-if="(this.city1Locations !== undefined && city2Locations !== undefined)">
		<p>this is showing if not empty</p>
		<div class="md-layout">
			<div class="md-layout-item">
				<AirportLocations v-bind:locations="city1Locations"></AirportLocations>
			</div>
			<div class="md-layout-item">
				<AirportLocations v-bind:locations="city2Locations"></AirportLocations>
			</div>
		</div>
	</div>
  </div>
</template>
<script>
import Axios from "axios";
import AirportLocation from '../views/components/AirportLocations';
import { required, minLength } from "vuelidate/lib/validators";

export default {
  name: "trip-search",
  data() {
    return {
      departureLocation: "",
      destinationLocation: "",
      city1Locations: [],
      city2Locations: []
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
      console.log(city1 + "," + city2);
      Axios({
        url: url,
        method: "get",
        headers: {
          "Content-Type": "applicaton/json"
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
		  console.log(this.city1Locations);
        })
        .catch(err => {
          console.log(err);
        });
    }
  }
};
</script>
<style></style>