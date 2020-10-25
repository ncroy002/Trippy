<template>
  <div class="wrapper">
    <parallax class="page-header header-filter" :style="headerStyle">
      <div class="md-layout">
        <div class="md-layout-item">
          <div class="image-wrapper">
            <img :src="leaf4" alt="leaf4" class="leaf4" v-show="leafShow" />
            <img :src="leaf3" alt="leaf3" class="leaf3" v-show="leafShow" />
            <img :src="leaf2" alt="leaf2" class="leaf2" v-show="leafShow" />
            <img :src="leaf1" alt="leaf1" class="leaf1" v-show="leafShow" />
            <div class="brand">
              <h1>Trippy Travel</h1>
              <h3>Find the best vacation for YOU.</h3>
            </div>
          </div>
        </div>
      </div>
    </parallax>
    <div class="main main-raised">
      <div class="section section-basic">
        <div class="container">
          <trip-search v-on:flightData="onFlightData"
            v-on:interestData="onInterestData">
          </trip-search>
            v-on:HotelData="onHotelData"></trip-search>
          <trip-search
            v-on:flightData="onFlightData"
            v-on:links="onLink"
          ></trip-search>
        </div>
      </div>
    </div>
    <div v-if="flightData" class="main main-raised">
      <div class="section section-basic">
        <div class="container">
          <flight-card :flight_data="flightData" :links="links"></flight-card>
        </div>
      </div>
    </div>
    <div v-if="hotelData" class="main main-raised">
      <div class="section section-basic">
        <div class="container">
          <hotel-card :hotel_data="hotelData"></hotel-card>
        </div>
      </div>
    </div>
     <div v-if="interestData" class="main main-raised">
      <div class="section section-basic">
        <div class="container">
          <interest-card :interest_data="interestData"></interest-card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { TripSearch } from "@/components";
import FlightCard from "./components/FlightCard";
import InterestCard from "./components/InterestsCard";
import HotelCard from "./components/HotelCard";
export default {
  components: {
    TripSearch,
    FlightCard,
    InterestCard
    HotelCard
  },
  name: "index",
  bodyClass: "index-page",
  props: {
    image: {
      type: String,
      default: require("@/assets/img/vue-mk-header.jpg")
    },
    leaf4: {
      type: String,
      default: require("@/assets/img/leaf4.png")
    },
    leaf3: {
      type: String,
      default: require("@/assets/img/leaf3.png")
    },
    leaf2: {
      type: String,
      default: require("@/assets/img/leaf2.png")
    },
    leaf1: {
      type: String,
      default: require("@/assets/img/leaf1.png")
    },
    signup: {
      type: String,
      default: require("@/assets/img/city.jpg")
    },
    landing: {
      type: String,
      default: require("@/assets/img/landing.jpg")
    },
    profile: {
      type: String,
      default: require("@/assets/img/profile.jpg")
    }
  },
  data() {
    return {
      firstname: null,
      email: null,
      password: null,
      leafShow: false,
      flightData: undefined,
      interestData: undefined
      hotelData: undefined,
      links: undefined
    };
  },
  methods: {
    leafActive() {
      if (window.innerWidth < 768) {
        this.leafShow = false;
      } else {
        this.leafShow = true;
      }
    },
    onFlightData(data) {
      this.flightData = data;
    },
    onInterestData(data) {
      this.interestData = data;
      },
    onHotelData(data){
      this.hotelData = data;
      console.log(this.hotelData)
      console.log("flight data: ->");
      console.log(this.flightData);
    },
    onLink(data) {
      this.links = data;
      console.log(this.links);
    }
    
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.image})`
      };
    },
    signupImage() {
      return {
        backgroundImage: `url(${this.signup})`
      };
    }
  },
  mounted() {
    this.leafActive();
    window.addEventListener("resize", this.leafActive);
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.leafActive);
  }
};
</script>
<style lang="scss">
.section-download {
  .md-button + .md-button {
    margin-left: 5px;
  }
}

@media all and (min-width: 991px) {
  .btn-container {
    display: flex;
  }
}
</style>
