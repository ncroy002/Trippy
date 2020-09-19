<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-50 md-small-size-70 md-xsmall-size-100"
          >
            <h1 class="title">Frequently Asked Questions</h1>
            <h4>
              INSERT INFO HERE***************
            </h4>
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
              <h2 class="title text-center">Frequently Asked Questions</h2>
              <h5 class="description">
                BLAH BLAH BLAH
              </h5>
            </div>
          </div>
     	 </div>
		</div>
	  </div>
	</div>
</template>

<script>
export default {
  bodyClass: "FAQ_page-page",
  props: {
    // header: {
    //   type: String,
    //   default: require("@/assets/img/bg7.jpg")
    // },
    // teamImg1: {
    //   type: String,
    //   default: require("@/assets/img/faces/avatar.jpg")
    // },
    // teamImg2: {
    //   type: String,
    //   default: require("@/assets/img/faces/christian.jpg")
    // },
    // teamImg3: {
    //   type: String,
    //   default: require("@/assets/img/faces/kendall.jpg")
    // }
  },
  data() {
    return {
      name: null,
      email: null,
      message: null
    };
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    }
  }
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
