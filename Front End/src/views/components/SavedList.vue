<template>
  <div>
    <div class="md-layout md-alignment-center-center mx-auto">
      <h5>Saved Trip Lists</h5>
    </div>
    <div>

        <div class="md-layout">
            <div class="md-layout-item">
              <md-field>
                <md-select v-model="selectedLocation1">
                  <md-option value="0"> 0 </md-option>
                  <md-option value="1"> 1 </md-option>
                  <md-option value="2"> 2 </md-option>
                  <md-option value="3"> 3 </md-option>
                  <!-- <md-option
                    v-for="(list, index) in savedTripList"
                    :key="index"
                  >
                    {{ list.name }}
                  </md-option> -->
                </md-select>
              </md-field>
            </div>
    </div>
  </div>
  </div>
</template>

<script>
import Axios from "axios";
export default {
  name: "TripList",
  props: {
    savedTripList: Array
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