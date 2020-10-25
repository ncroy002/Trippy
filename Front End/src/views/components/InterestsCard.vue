<template>
  <div>
    <div class="md-layout md-alignment-center-center">
      <h3>Activities You'll Love:</h3>
    </div>
    <div>
      <md-card
        class="md-layout md-with-hover"
        v-for="(interest, index) in interest_data"
        :key="index"
      >
        <md-card-content>
          <h3> {{ interest.properties.name }}</h3>
          <p>
            State:
            {{
              interest.properties.state
            }}
          </p>
          <p>
            County:
            {{
              interest.properties.county
            }}
          </p>
          <p>
            Address:
              {{ interest.properties.address_line2 }}
          </p>
        </md-card-content>
        <md-card-actions>
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
    interest_data: Array
  },
  data() {
    return {
      selectedFlight: undefined,
      userEmail: undefined,
      valid: false
    };
  },
  methods: {
  },
  mounted() {
   
  }
};
</script>

<style lang="scss" scoped>
</style>
