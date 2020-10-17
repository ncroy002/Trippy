<template>
  <div>
    <div v-bind:key="commentcard.idc" v-for="commentcard in commentcard">
      <div>
        <p>
          {{ commentcard.commentUser }}, {{ commentDate() }}
          <br />
          {{ commentcard.comment }}
        </p>
        <md-button class="md-default md-sm">Reply</md-button>
      </div>
      <md-field></md-field>
    </div>
  </div>
</template>

<script>
export default {
  name: "Comment-Card",
  props: ["commentcard"],
  components: {},
  data() {
    return {};
  },
  methods: {
    commentDate() {
      var n = new Date();
      var month = n.getMonth() + 1;
      var day = n.getDate();
      var year = n.getFullYear();
      var time = n.getSeconds();
      var myCommentDate = month + "/" + day + "/" + year + " ";
      return myCommentDate;
    }
  },
  computed: {}
};
</script>

<style lang="scss" scoped></style>