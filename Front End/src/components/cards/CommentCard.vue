<template>
  <div class="md-layout-item md-size-100 md-small-size-100 mx-auto text-center">
   <div v-bind:key="commentcard.id" v-for="commentcard in commentcard">
      <div>
        <md-card>
          <md-field>
            <div id="comment-content">
              <p>{{ commentcard.commentUser }}, {{ currentDate() }}
              <br />
              {{ commentcard.commentComment }}
              </p>
            </div>
          </md-field>
        </md-card>
     </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Comment-Card',
  props: ["commentcard"],
  methods: {
    currentDate() {
      var n = new Date();
      var month = n.getMonth() + 1;
      var day = n.getDate();
      var year = n.getFullYear();
      var time = n.getSeconds();
      var myDate = month + "/" + day + "/" + year + " ";
      return myDate;
    }
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

.centered {
  position: absolute;
  top: 0%;
  left: 15%;
  transform: translate(-0%, 15%);
}

.img-wrapper {
  display: inline-block;
  height: 100px;
  overflow: hidden;
  width: 100px;
}
</style>