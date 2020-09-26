<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-50 md-small-size-70 md-xsmall-size-100"
          >
            <h1 class="title">Events</h1>

            <br />
          </div>
        </div>
      </div>
    </parallax>
    <div class="main main-raised">
      <div class="section">
        <div class="container">
          <div class="md-layout">
            <div
              class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center"
            >
              <h2 class="title text-center">Up Coming Events</h2>
            </div>

            <div
              class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center"
            >
              <h5 class="description">
               LIST Events here
              </h5>

			<div class="card">
  				<img src="@/assets/img/tomorrowland.jpg" alt="Avatar" style="width:100%">
  				<div class="container">
    			<h4><b>TOMORROWLAND 2021</b></h4>
    			<p>July 16-18, Belgium</p>
  				</div>
			</div>  
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  bodyClass: "Events-page",
  props: {
    header: {
      type: String,
      default: require("@/assets/img/newEvents.jpg"),
    },
  },
  data() {
    return {
      name: null,
      email: null,
      message: null,
  
    };
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
};
</script>

<style lang="scss" scoped>
.md-card-actions.text-center {
  display: flex;
  justify-content: center !important;
}
.contact-form {
  margin-top: 30px;
}

.md-has-textarea + .md-layout {
  margin-top: 15px;
}
</style>