<template>
  <md-toolbar
    id="toolbar"
    md-elevation="0"
    class="md-transparent md-absolute"
    :class="extraNavClasses"
    :color-on-scroll="colorOnScroll"
  >
    <div class="md-toolbar-row md-collapse-lateral">
      <div class="md-toolbar-section-start">
        <h3 class="md-title"><a href="#/">Trippy</a></h3>
      </div>
      <div class="md-toolbar-section-end">
        <md-button
          class="md-just-icon md-simple md-toolbar-toggle"
          :class="{ toggled: toggledClass }"
          @click="toggleNavbarMobile()"
        >
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </md-button>

        <div class="md-collapse">
          <div class="md-collapse-wrapper">
            <mobile-menu nav-mobile-section-start="false">
              <!-- Here you can add your items from the section-start of your toolbar -->
            </mobile-menu>
            <md-list>

               <md-list-item href="#/">
                <i class="material-icons">explore</i>
                <p>Home</p>
              </md-list-item>

               <md-list-item href="#/faq">
                <i class="material-icons">help_outline</i>
                <p>FAQ</p>
              </md-list-item>


              <li class="md-list-item" v-if="currentUser">
                <a
                  href="javascript:void(0)"
                  class="md-list-item-router md-list-item-container md-button-clean dropdown"
                >
                  <div class="md-list-item-content">
                    <drop-down direction="down">
                      <md-button
                        slot="title"
                        class="md-button md-button-link md-white md-simple dropdown-toggle"
                        data-toggle="dropdown"
                      >
                        <i class="material-icons">account_circle</i>
                        <p>My Account</p>
                      </md-button>
                      <ul class="dropdown-menu dropdown-with-icons">
                        <li>
                          <a href="#/profile">
                            <i class="material-icons">face</i>
                            <p>Profile</p>
                          </a>
                        </li>
                        <li>
                          <a href="#/events">
                            <i class="material-icons">event_note</i>
                            <p>Events</p>
                          </a>
                        </li>
                        <li>
                          <a href="#/userlist">
                            <i class="material-icons">people</i>
                            <p>User List</p>
                          </a>
                        </li>

                      </ul>
                    </drop-down>
                  </div>
                </a>
              </li>

              <md-list-item href @click.prevent="logOut" v-if="currentUser">
                <i class="material-icons">logout</i>
                <p>Logout</p>
                <md-tooltip md-direction="bottom"
                  >See ya next time</md-tooltip>
              </md-list-item>

              <md-list-item href="#/login" v-if="!currentUser">
                <i class="material-icons">login</i>
                <p>Login</p>
                <md-tooltip md-direction="bottom">Already have an account</md-tooltip>
              </md-list-item>

              <md-list-item href="#/register" v-if="!currentUser">
                <i class="material-icons">person_add</i>
                <p>Create Account</p>
                <md-tooltip md-direction="bottom">Create account</md-tooltip>
              </md-list-item>

              <md-list-item href="#/forums">
                <i class="material-icons">chat</i>
                <p>Forums</p>
              </md-list-item>

               <md-list-item href="#/recommendations">
                <i class="material-icons">assistant</i>
                <p>Recommendations</p>
              </md-list-item>



            </md-list>
          </div>
        </div>
      </div>
    </div>
  </md-toolbar>
</template>

<script>
let resizeTimeout;
function resizeThrottler(actualResizeHandler) {
  // ignore resize events as long as an actualResizeHandler execution is in the queue
  if (!resizeTimeout) {
    resizeTimeout = setTimeout(() => {
      resizeTimeout = null;
      actualResizeHandler();

      // The actualResizeHandler will execute at a rate of 15fps
    }, 66);
  }
}

import MobileMenu from "@/layout/MobileMenu";
export default {
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
  /*  showAdminBoard() {
      if (this.currentUser && this.currentUser.roles){
        return this.currentUser.roles.includes('ROLE_ADMIN');
      }
      return false;
    }*/
  },
  components: {
    MobileMenu
  },
  props: {
    type: {
      type: String,
      default: "white",
      validator(value) {
        return [
          "white",
          "default",
          "primary",
          "danger",
          "success",
          "warning",
          "info"
        ].includes(value);
      }
    },
    colorOnScroll: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {
      extraNavClasses: "",
      toggledClass: false
    };
  },


  methods: {
    logout() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    },

    toggleNavbarMobile() {
      this.NavbarStore.showNavbar = !this.NavbarStore.showNavbar;
      this.toggledClass = !this.toggledClass;
      this.bodyClick();
    },
    handleScroll() {
      let scrollValue =
        document.body.scrollTop || document.documentElement.scrollTop;
      let navbarColor = document.getElementById("toolbar");
      this.currentScrollValue = scrollValue;
      if (this.colorOnScroll > 0 && scrollValue > this.colorOnScroll) {
        this.extraNavClasses = `md-${this.type}`;
        navbarColor.classList.remove("md-transparent");
      } else {
        if (this.extraNavClasses) {
          this.extraNavClasses = "";
          navbarColor.classList.add("md-transparent");
        }
      }
    },

    scrollListener() {
      resizeThrottler(this.handleScroll);
    },

    
  },

  mounted() {
    document.addEventListener("scroll", this.scrollListener);
  },

  beforeDestroy() {
    document.removeEventListener("scroll", this.scrollListener);
  }
};
</script>