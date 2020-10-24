<template>
  <div>
    <div class="md-layout md-alignment-center-center">
      <h3>Links:</h3>
      <a class="md-layout md-alignment-center-center" v-for="(link,index) in links"
       :key="index" :href="link">
       {{link.split('/')[2]}}
       </a>
      
    </div>
    <div class="md-layout md-alignment-center-center">
      <h3>Flights:</h3>
    </div>
    <div>
      <md-card
        class="md-layout md-with-hover"
        v-for="flight in flight_data.Quotes"
        :key="flight.QuoteId"
      >
        <md-card-content>
          <h3>Minimum Price: ${{ flight.MinPrice }}</h3>
          <p>
            Departure Place:
            {{
              flight_data.Places.find(
                e => e.PlaceId == flight.OutboundLeg.OriginId
              ).Name
            }}
          </p>
          <p>
            Destination Place:
            {{
              flight_data.Places.find(
                e => e.PlaceId == flight.OutboundLeg.DestinationId
              ).Name
            }}
          </p>
          <p>
            Carrier:
            <span
              v-for="carrier in flight_data.Carriers.filter(e =>
                flight.OutboundLeg.CarrierIds.includes(e.CarrierId)
              )"
              :key="carrier.Id"
            >
              {{ carrier.Name }}
            </span>
          </p>
          <p>Departure Date: {{ flight.OutboundLeg.DepartureDate }}</p>
          <p>Number of Travelers: {{ flight_data.noOfTravelers }}</p>
        </md-card-content>
        <md-card-actions>
          <md-button
            v-if="userEmail"
            class="md-primary"
            v-on:click="saveFlight(flight.QuoteId)"
            >Save</md-button
          >
        </md-card-actions>
      </md-card>
    </div>
  </div>
</template>

<script>
import Axios from "axios";
export default {
  name: "flight-card",
  props: {
    flight_data: Object,
    links: Array
  },
  data() {
    return {
      selectedFlight: undefined,
      userEmail: undefined,
      valid: false
    };
  },
  methods: {
    saveFlight(quoteId) {
      let flight = this.flight_data.Quotes.find(
        element => element.QuoteId === quoteId
      );
      let city1Id = flight.OutboundLeg.OriginId;
      let city2Id = flight.OutboundLeg.DestinationId;
      let city1Name = this.flight_data.Places.find(e => e.PlaceId == city1Id)
        .Name;
      let city2Name = this.flight_data.Places.find(e => e.PlaceId == city2Id)
        .Name;
      let carrier = this.flight_data.Carriers.filter(e =>
        flight.OutboundLeg.CarrierIds.includes(e.CarrierId)
      )[0].Name;
      let saveDate = new Date().toUTCString();
      let minCost = flight.MinPrice;
      let noOfTravelers = this.flight_data.noOfTravelers;

      if (this.userEmail !== undefined) {
        this.valid = true;
      } else {
        this.valid = false;
      }
      const url = "http://localhost:8081/flight/save";
      if (this.valid) {
        Axios({
          url: url,
          method: "post",
          headers: {
            "Content-Type": "application/json",
            email: this.userEmail
          },
          data: {
            city1ID: city1Id,
            city2ID: city2Id,
            city1Name: city1Name,
            city2Name: city2Name,
            minCost: minCost,
            carrierName: carrier,
            saveDate: saveDate,
            noOfTravelers: noOfTravelers,
          }
        })
          .then(result => {
            console.log(result);
          })
          .catch(err => {
            console.log(err);
          });
      }
    }
  },
  mounted() {
    this.userEmail = this.$store.getters.getEmail;
  }
};
</script>

<style lang="scss" scoped>
</style>
