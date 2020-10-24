<template>
  <div>
    <div class="md-layout md-alignment-center-center">
      <h3>Hotels:</h3>
    </div>
    <div>
      <md-card
        class="md-layout md-with-hover"
        v-for="(hotel,index) in hotel_data"
        :key="index"
      >
        <md-card-content>
          <h3>Hotel: {{ hotel.name }}</h3>
          <p>
            Ratings:
            {{hotel.starRating}}
          </p>
          <p>
            Address:
            {{hotel.address}}
          </p>
          <p>
            Price:
              {{ hotel.price }}
          </p>
        </md-card-content>
      </md-card>
    </div>
  </div>
</template>

<script>
import Axios from "axios";
export default {
  name: "hotel-card",
  props: {
    hotel_data: Array
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
