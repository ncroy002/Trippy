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

              <li class="md-list-item" v-if="isLoggedIn">
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
                          <a href="#/userlist" v-if="isAdmin">
                            <i class="material-icons">people</i>
                            <p>User List</p>
                          </a>
                        </li>
                       
                      
                      </ul>
                    </drop-down>
                  </div>
                </a>
              </li>

              
 
              <md-list-item href="#/forums">
                <i class="material-icons">chat</i>
                <p>Forums</p>
              </md-list-item>

               <md-list-item href="#/recommendations">
                <i class="material-icons">assistant</i>
                <p>Recommendations</p>
              </md-list-item>


          <!-- login  -->
            <md-list-item href="#/login"  v-if="!isLoggedIn">
                <i class="material-icons">login</i>
                <p>Login</p>
              </md-list-item>
<!-- logout  -->
            <md-list-item v-if="isLoggedIn">
              <a  @click=logout>
                <i class="material-icons">logout</i>
                <p>Logout</p>
                </a>
              </md-list-item>
              
            <md-list-item href="#/register" v-if="!isLoggedIn">
                <i class="material-icons">person_add</i>
                <p>Register</p>
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
   computed : {
      isLoggedIn : function(){ return this.$store.getters.isLoggedIn}
      ,isUser : function(){ return this.$store.getters.isUser}
      ,isAdmin : function(){ return this.$store.getters.isAdmin}
    },
    methods: {
      logout: function () {
        this.$store.dispatch('logout')
        .then(() => this.$router.push('/login'))
      }
    },
    bodyClick() {
      let bodyClick = document.getElementById("bodyClick");

      if (bodyClick === null) {
        let body = document.querySelector("body");
        let elem = document.createElement("div");
        elem.setAttribute("id", "bodyClick");
        body.appendChild(elem);

        let bodyClick = document.getElementById("bodyClick");
        bodyClick.addEventListener("click", this.toggleNavbarMobile);
      } else {
        bodyClick.remove();
      }
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

    scrollToElement() {
      let element_id = document.getElementById("downloadSection");
      if (element_id) {
        element_id.scrollIntoView({ block: "end", behavior: "smooth" });
      }
    },
  

  mounted() {
    document.addEventListener("scroll", this.scrollListener);
  },

  beforeDestroy() {
    document.removeEventListener("scroll", this.scrollListener);
  }
};
</script>