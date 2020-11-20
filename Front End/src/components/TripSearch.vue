<template>
  <div id="airlineTicketSearch">
    <!-- This is for searching and getting the airports from a city -->
    <div id="airportSearch">
      <template>
        <div>
          <nav-tabs-card no-label>
            <template slot="content">
              <md-tabs class="md-primary" md-alignment="centered">
                <!-- Flight & Hotel Tab -->
                <md-tab id="tab-home" md-label="Flight & Hotel">
                  <div class="section text-center">
                    <div class="container">
                      <h2 class="title">Flight & Hotel Search</h2>
                      <div id="luckySearch">
                        <center>
                          <button
                            v-on:click="getRandom()"
                            type="button"
                            class="md-button md-success md-round md-theme-default"
                          >
                            <div class="md-ripple">
                              <div class="md-button-content">
                                TAKE ME ON A TRIP!
                              </div>
                            </div>
                          </button>
                        </center>
                      </div>
                      <div class="team">
                        <md-card class="md-card-plain">
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
                                this.city1Locations.length !== 0 &&
                                this.city2Locations.length !== 0
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
                                  <md-datepicker
                                    v-model="selectedDepartureDate"
                                  >
                                    <label>Departure date</label>
                                  </md-datepicker>
                                </div>
                                <div class="md-layout-item">
                                  <md-datepicker v-model="selectedReturnDate">
                                    <label>Return date</label>
                                  </md-datepicker>
                                </div>
                              </div>
                              <div class="md-layout">
                                <div class="md-layout-item">
                                  <md-field>
                                    <label for="adults">
                                      number of adults:
                                    </label>
                                    <md-select v-model="adults" name="adults">
                                      <md-option value="0"> 0 </md-option>
                                      <md-option value="1"> 1 </md-option>
                                      <md-option value="2"> 2 </md-option>
                                      <md-option value="3"> 3 </md-option>
                                      <md-option value="4"> 4 </md-option>
                                      <md-option value="5"> 5 </md-option>
                                      <md-option value="6"> 6 </md-option>
                                      <md-option value="7"> 7 </md-option>
                                      <md-option value="8"> 8 </md-option>
                                      <md-option value="9"> 9 </md-option>
                                    </md-select>
                                  </md-field>
                                </div>
                                <div class="md-layout-item">
                                  <md-field>
                                    <label for="children">
                                      number of children:
                                    </label>
                                    <md-select
                                      v-model="children"
                                      name="children"
                                    >
                                      <md-option value="0"> 0 </md-option>
                                      <md-option value="1"> 1 </md-option>
                                      <md-option value="2"> 2 </md-option>
                                      <md-option value="3"> 3 </md-option>
                                      <md-option value="4"> 4 </md-option>
                                      <md-option value="5"> 5 </md-option>
                                      <md-option value="6"> 6 </md-option>
                                      <md-option value="7"> 7 </md-option>
                                      <md-option value="8"> 8 </md-option>
                                      <md-option value="9"> 9 </md-option>
                                    </md-select>
                                  </md-field>
                                </div>
                              </div>
                              <div class="md-layout">
                                <div class="md-layout-item">
                                  <md-field>
                                    <label for="seniors">
                                      number of senior citizens:
                                    </label>
                                    <md-select v-model="seniors" name="seniors">
                                      <md-option value="0"> 0 </md-option>
                                      <md-option value="1"> 1 </md-option>
                                      <md-option value="2"> 2 </md-option>
                                      <md-option value="3"> 3 </md-option>
                                      <md-option value="4"> 4 </md-option>
                                      <md-option value="5"> 5 </md-option>
                                      <md-option value="6"> 6 </md-option>
                                      <md-option value="7"> 7 </md-option>
                                      <md-option value="8"> 8 </md-option>
                                      <md-option value="9"> 9 </md-option>
                                    </md-select>
                                  </md-field>
                                </div>
                                <div class="md-layout-item">
                                  <md-field>
                                    <label for="cabin"> Cabin Class </label>
                                    <md-select v-model="cabin" name="cabin">
                                      <md-option value="economy">
                                        Economy
                                      </md-option>
                                      <md-option value="first">
                                        First Class</md-option
                                      >
                                      <md-option value="premium">
                                        Premium Economy</md-option
                                      >
                                      <md-option value="business">
                                        Business</md-option
                                      >
                                    </md-select>
                                  </md-field>
                                </div>
                                <div class="md-layout-item">
                                  <md-field class="md-form-group">
                                    <label>Number of Travelers</label>
                                    <md-input
                                      type="number"
                                      v-model="noOfTravelers"
                                      placeholder="Number of Travelers"
                                    >
                                      {{
                                        (noOfTravelers =
                                          parseFloat(this.adults) +
                                          parseFloat(this.children) +
                                          parseFloat(this.seniors))
                                      }}
                                    </md-input>
                                  </md-field>
                                </div>
                              </div>

                              <div class="md-layout md-alignment-center-center">
                                <md-button v-on:click="getFlightDetails()"
                                  >Find Flight Details</md-button
                                >
                              </div>
                              <div class="md-layout md-alignment-center-center">
                                <md-button v-on:click="getHotels()"
                                  >find Hotels</md-button
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
                        </md-card>
                      </div>
                    </div>
                  </div>
                </md-tab>

                <!-- Interests Tab -->
                <md-tab id="tab-pages" md-label="Interest">
                  <div class="section text-center">
                    <div class="container">
                      <h2 class="title">Interest Search</h2>
                      <div class="team">
                        <md-card class="md-card-plain">
                          <div id="interestSearch">
                            <form>
                              <div class="md-layout">
                                <div class="md-layout-item">
                                  <md-field class="md-form-group">
                                    <md-icon>explore</md-icon>
                                    <md-input
                                      v-model="destination"
                                      placeholder="Destination"
                                    ></md-input>
                                  </md-field>
                                </div>
                                <div>
                                  <div class="md-layout">
                                    <div class="md-layout-item">
                                      <md-field class="md-form-group">
                                        <md-icon>favorite</md-icon>
                                        <label for="interest">Interests</label>
                                        <md-select
                                          v-model="interest"
                                          name="interest"
                                          id="interest"
                                        >
                                          <md-option value="national_park"
                                            >National Parks</md-option
                                          >
                                          <md-option value="beach"
                                            >Beaches</md-option
                                          >
                                          <md-option
                                            value="commercial.outdoor_and_sport.ski"
                                            >Skiing</md-option
                                          >
                                          <md-option value="entertainment.zoo"
                                            >Zoos</md-option
                                          >
                                          <md-option
                                            value="entertainment.museum"
                                            >Museums</md-option
                                          >
                                          <md-option
                                            value="entertainment.theme_park"
                                            >Theme Parks</md-option
                                          >
                                          <md-option
                                            value="entertainment.water_park"
                                            >Water Parks</md-option
                                          >
                                          <md-option value="camping"
                                            >Camping</md-option
                                          >
                                          <md-option value="adult.nightclub"
                                            >Nightlife</md-option
                                          >
                                        </md-select>
                                      </md-field>
                                    </div>
                                  </div>
                                </div>
                              </div>
                              <center>
                                <md-button v-on:click="getInterestResults()"
                                  >Search Interests</md-button
                                >
                              </center>
                            </form>
                          </div>
                        </md-card>
                      </div>
                    </div>
                  </div>
                </md-tab>
              </md-tabs>
            </template>
          </nav-tabs-card>
        </div>
      </template>
    </div>
  </div>
</template>






<script>
import Axios from "axios";
import { required, minLength } from "vuelidate/lib/validators";
import { NavTabsCard } from "@/components";

export default {
  components: {
    NavTabsCard,
  },
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
      selectedReturnDate: null,
      destination: "",
      interest: "",
      features: [],
      Places: [],
      flightData: undefined,
      noOfTravelers: null,
      adults: null,
      seniors: null,
      children: null,
      cabin: null,
    };
  },
  validations: {
    departureLocation: {
      required,
      minLength: minLength(3),
    },
    destinationLocation: {
      required,
      minLength: minLength(3),
    },
  },

  methods: {
    setAdults(value) {
      let adults = value;
      this.adults = adults;
    },
    getAirportLocations() {
      const url = "http://localhost:8081/flight/find/airports";
      const city1 = this.departureLocation;
      const city2 = this.destinationLocation;
      Axios({
        url: url,
        method: "get",
        headers: {
          "Content-Type": "application/json",
        },
        params: {
          city1: city1,
          city2: city2,
        },
      })
        .then((result) => {
          let { city1: value1, city2: value2 } = result.data;
          let city1Places = Object.values(JSON.parse(value1).Places);
          let city2Places = Object.values(JSON.parse(value2).Places);
          city1Places.forEach((element) => {
            this.city1Locations.push(element);
          });

          city2Places.forEach((element) => {
            this.city2Locations.push(element);
          });
        })
        .catch((err) => {
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
      let adults = this.adults;
      let children = this.children;
      let seniors = this.seniors;
      let cabin = this.cabin;
      let outbountDate = this.selectedDepartureDate.toISOString().split("T")[0];
      let inboundDate = this.selectedReturnDate.toISOString().split("T")[0];
      let noOfpassengers = this.noOfTravelers;
      const url = "http://localhost:8081/flight/browse/routes";
      const LinkUrl = "http://localhost:8081/flight/generate/url";

      Axios({
        url: url,
        method: "get",
        headers: {
          "Content-Type": "application/json",
        },
        params: {
          city1: city1PlaceId,
          city2: city2PlaceId,
          date1: outbountDate,
          date2: inboundDate,
          noOfTravelers: noOfpassengers,
        },
      })
        .then((result) => {
          let { Quotes, Carriers, Places } = result.data;

          this.flightData = {
            Quotes: Quotes,
            Carriers: Carriers,
            Places: Places,
            noOfTravelers: noOfpassengers,
          };
          console.log(this.flightData);
          this.$emit("flightData", this.flightData);

          Axios({
            url: LinkUrl,
            method: "get",
            headers: {
              "Content-Type": "application/json",
            },
            params: {
              city1: this.flightData.Places[1].CityName,
              city1ID: city1PlaceId,
              city2: this.flightData.Places[0].CityName,
              city2ID: city2PlaceId,
              cabin: cabin,
              children: children,
              adults: adults,
              seniors: seniors,
              date1: outbountDate,
              date2: inboundDate,
            },
          })
            .then((result2) => {
              console.log(result2);
              let links = result2.data;
              this.$emit("links", links);
            })
            .catch((error) => {
              console.log(error);
            });
        })
        .catch((err) => {
          console.log(err);
        });
    },

    getInterestResults() {
      const url = "http://localhost:8081/filter/interests";

      let destination = this.destination;
      let interest = this.interest;
      console.log(destination);
      console.log(interest);

      Axios({
        url: url,
        method: "get",
        headers: {
          "Content-Type": "application/json",
        },
        params: {
          destination: destination,
          interest: interest,
        },
      })
        .then((result) => {
          this.features = result.data.features;
          console.log(this.features);
          let interestData = this.features;
          this.$emit("interestData", interestData);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    getHotels() {
      let cityName = this.destinationLocation;
      let outbountDate = this.selectedDepartureDate.toISOString().split("T")[0];
      let inboundDate = this.selectedReturnDate.toISOString().split("T")[0];
      const url = "http://localhost:8081/hotels/test";
      Axios({
        url: url,
        method: "get",
        headers: {
          "Content-Type": "application/json",
        },
        params: {
          cityName: cityName,
          date1: outbountDate,
          date2: inboundDate,
        },
      })
        .then((result) => {
          console.log(result);
          let HotelArray = result.data;
          this.$emit("HotelData", HotelArray);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    getRandom() {
      const url = "http://localhost:8081/randomTrip/test";
      var random = Math.floor(Math.random() * Math.floor(25));
      Axios({
        url: url,
        method: "get",
        headers: {
          "Content-Type": "application/json",
        },
        params: {
          randomNum: random,
        },
      })
        .then((result) => {
          let TripArray = result.data;
          console.log(TripArray);
          this.$emit("tripData", TripArray);
          //let tripArray = result.data;
          //this.$emit('TripData',tripArray);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>
<style></style>