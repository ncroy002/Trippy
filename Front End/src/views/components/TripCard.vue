<template>
  <div>
    <div class="md-layout md-alignment-center-center">
      <h3>This is your trip:</h3>
    </div>
    <div>
      <md-card
        class="md-layout md-with-hover"
        
      >
        <md-card-content>
          <h2>Congrats! you are going to: {{trip_data[2]}}</h2>
          <h3>Hotel Name: {{ trip_data[0].name }}</h3>
          <p>
            Hotel Rating:
            {{trip_data[0].starRating}}
          </p>
          <p>
            Address:
            {{trip_data[0].address}}
          </p>
          <p>
            Price:
              {{ trip_data[0].price }}
          </p>
          <h3>Your flight info:</h3>
          <p>
            Min Price:
            {{trip_data[1][0]}}
          </p>
          <p>
            Carrier:
            {{trip_data[1][1]}}
          </p>
          <p>
            Departure Date:
            {{trip_data[1][2]}}
          </p>

        </md-card-content>
        
      </md-card>
    </div>
  </div>
</template>

<script>
import Axios from "axios";
export default {
  name: "trip-card",
  props: {
    trip_data: Array
  },
  data() {
    return {
    };
  },
  methods: {

  }
};
</script>

<style lang="scss" scoped>
</style>
