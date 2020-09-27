<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div class="md-layout-item md-size-50 md-small-size-70 md-xsmall-size-100">
            <h1 class="title">Recommendations</h1>
            <h4>
              Here you will be able to view, purchase tickets, and save trips 
              from a selection of popular destinations
              from both around the world and places within the United States 
              including a small description of each destination with some points
              of interets you might want to visit when staying in these areas.
            </h4>
            <br />
          </div>
        </div>
      </div>
    </parallax>

    <nav-tabs-card no-label>
      <template slot="content">
        <md-tabs class="md-primary" md-alignment="left">
          <md-tab id="tab-home" md-label="World">
            <div class="section text-center">
              <div class="container">
                <h2 class="title">World Destinations</h2>
                <div class="team">
                  <md-card class="md-card-plain">
                    <div class="md-layout-item md-size-50 mx-auto">
                      <img  :src="teamImg1" />
                    </div>
                    <h4 class="card-title">Gramado, Brazil</h4>

                    <md-card-content>
                      <p class="card-description">
                        The small Brazilian village of Gramado is a quaint and woodsy 
                        respite that boasts some delightful surprises. After you’ve fully 
                        enjoyed the paddleboats and surrounding Black Forest pines of Lago Negro, 
                        pay a visit to Snowland, a unique indoor snow park. Kids will love 
                        a visit to Mini Mundo, a miniature park that features tiny replicas of famous landmarks.
                      </p>
                      <ul>
                        <p> Points of Interest </p>
                        <li>Mini Mundo</li>
                        <li>Lago Negro</li>
                        <li>Rua Coberta</li>
                      </ul>
                    </md-card-content>

                    <div class="md-layout">
                      <div class="md-layout-item md-size-33 mx-auto text-center">
                        <md-button class="md-success">Purchase Ticket</md-button>
                        <md-button class="md-success">Save Trip</md-button>
                      </div>
                    </div>
                    <md-card-actions class="text-center"> </md-card-actions>
                  </md-card>
                </div>

                <div class="team">
                  <md-card class="md-card-plain">
                    <div class="md-layout-item md-size-50 mx-auto">
                      <img  :src="teamImg2" />
                    </div>
                    <h4 class="card-title">Buenos Aires, Argentina</h4>

                    <md-card-content>
                      <p class="card-description">
                        The birthplace of the tango is, like the dance itself, captivating, 
                        seductive and bustling with excited energy. Atmospheric old 
                        neighborhoods are rife with romantic restaurants and 
                        thumping nightlife, and Buenos Aires' European heritage is evident 
                        in its architecture, boulevards and parks. Cafe Tortoni, 
                        the city's oldest bar, will transport you back to 1858, 
                        and the spectacular Teatro Colon impresses just as it did in 1908. 
                        Latin America's shopping capital offers the promise of premium 
                        retail therapy along its grand, wide boulevards.
                      </p>
                      <ul>
                        <p> Points of Interest </p>
                        <li> Teatro </li>
                        <li> Puerto Madero </li>
                        <li> Recoleta </li>
                      </ul>
                    </md-card-content>

                    <div class="md-layout">
                      <div class="md-layout-item md-size-33 mx-auto text-center">
                        <md-button class="md-success">Purchase Ticket</md-button>
                        <md-button class="md-success">Save Trip</md-button>
                      </div>
                    </div>
                    <md-card-actions class="text-center"> </md-card-actions>
                  </md-card>
                </div>

                <div class="team">
                  <md-card class="md-card-plain">
                    <div class="md-layout-item md-size-50 mx-auto">
                      <img  :src="teamImg3" />
                    </div>
                    <h4 class="card-title">Budapest, Hungary</h4>

                    <md-card-content>
                      <p class="card-description">
                        Over 15 million gallons of water bubble daily into 
                        Budapest's 118 springs and boreholes. The city of spas 
                        offers an astounding array of baths, from the sparkling 
                        Gellert Baths to the vast 1913 neo-baroque Szechenyi 
                        Spa to Rudas Spa, a dramatic 16th-century Turkish pool 
                        with original Ottoman architecture. The "Queen of the Danube" 
                        is also steeped in history, culture and natural beauty. 
                        Get your camera ready for the Roman ruins of the Aquincum Museum, 
                        Heroes' Square and Statue Park, and the 300-foot dome of St. Stephen's Basilica.
                      </p>
                      <ul>
                        <p> Points of Interest </p>
                        <li> Hungarian Parliament Building </li>
                        <li> Halaszbastya </li>
                        <li> St. Stephen's Basilica </li>
                      </ul>
                    </md-card-content>

                    <div class="md-layout">
                      <div class="md-layout-item md-size-33 mx-auto text-center">
                        <md-button class="md-success">Purchase Ticket</md-button>
                        <md-button class="md-success">Save Trip</md-button>
                      </div>
                    </div>
                    <md-card-actions class="text-center"> </md-card-actions>
                  </md-card>
                </div>
              </div>
            </div>
          </md-tab>

          <!-- NEW TAB 
          ************
          ************
          ************
          -->

          <md-tab id="tab-pages" md-label="United States">
            <div class="section text-center">
              <div class="container">
                <h2 class="title">Destinations within the United States</h2>
                <div class="team">
                  <md-card class="md-card-plain">
                    <div class="md-layout-item md-size-50 mx-auto">
                      <img  :src="teamImg4" />
                    </div>
                    <h4 class="card-title">Key Largo, Florida</h4>

                    <md-card-content>
                      <p class="card-description">
                        Just a few minutes' drive but a world away from the mainland,
                        Key Largo has been the setting of movies, the home to abundant
                        wildlife and a favorite destination for scuba diving.
                        Lush flora and lively fauna abound in the Dagny Johnson Key Largo
                        Hammock Botanical State Park, John Pennekamp
                        Coral Reef State Park and Everglades National Park. Underwater,
                        the Florida Keys National Marine Sanctuary teems with life while the
                        American Crocodile thrives at Crocodile Lake National Wildlife Refuge.
                      </p>
                      <ul>
                        <p> Points of Interest </p>
                        <li>John Pennekamp Coral Reef State Park</li>
                        <li>African Queen Canal Cruise</li>
                        <li>Dolphins Plus Marine Mammal Responder</li>
                      </ul>
                    </md-card-content>

                    <div class="md-layout">
                      <div class="md-layout-item md-size-33 mx-auto text-center">
                        <md-button class="md-success">Purchase Ticket</md-button>
                        <md-button class="md-success">Save Trip</md-button>
                      </div>
                    </div>
                    <md-card-actions class="text-center"> </md-card-actions>
                  </md-card>
                </div>

                <div class="team">
                  <md-card class="md-card-plain">
                    <div class="md-layout-item md-size-50 mx-auto">
                      <img  :src="teamImg5" />
                    </div>
                    <h4 class="card-title">Brooklyn, New York</h4>

                    <md-card-content>
                      <p class="card-description">
                        It seems like most folks have a grandmother, great-uncle or some other distant 
                        relative that used to live in Brooklyn, or perhaps a friend that lives there now. 
                        In the early 1900s, it was a mecca for immigrants arriving via Ellis Island. 
                        A hundred years later, young professionals and artists left pricey 
                        Manhattan digs for Brooklyn's cheaper and more expansive space. 
                        Neighborhoods like Brooklyn Heights and Park Slope, which had fallen 
                        into disrepair over the years, were restored and reborn as funky enclaves. 
                        Walk or bike over the historic Brooklyn Bridge (or ride the subway) to 
                        Brooklyn Heights for a stroll along the Promenade and breathtaking views of the Manhattan skyline. 
                        Meander through Prospect Park and the Brooklyn Botanical Gardens 
                        for a taste of nature in the urban wilds. Catch a performance at the 
                        world-famous Brooklyn Academy of Music. From the delicious Italian restaurants 
                        of Bensonhurst to the Irish bars that line the avenues of Bay Ridge, 
                        from the hotdogs and rollercoasters of Coney Island to the bagels and handball 
                        courts of Greenpoint, Brooklyn is a state of mind as well as a dynamic community. 
                        Discover why, no matter where people move on to, they remain Brooklynites at heart.
                      </p>
                      <ul>
                        <p> Points of Interest </p>
                        <li> Dumbo </li>
                        <li> Brooklyn Heights Promenade</li>
                        <li> Brooklyn Botanic Garden</li>
                      </ul>
                    </md-card-content>

                    <div class="md-layout">
                      <div class="md-layout-item md-size-33 mx-auto text-center">
                        <md-button class="md-success">Purchase Ticket</md-button>
                        <md-button class="md-success">Save Trip</md-button>
                      </div>
                    </div>
                    <md-card-actions class="text-center"> </md-card-actions>
                  </md-card>
                </div>

                <div class="team">
                  <md-card class="md-card-plain">
                    <div class="md-layout-item md-size-50 mx-auto">
                      <img  :src="teamImg6" />
                    </div>
                    <h4 class="card-title">Moab, Utah</h4>

                    <md-card-content>
                      <p class="card-description">
                        Moab attracts all types of outdoor adventurers. 
                        Mountain biking is huge here—experts claim it has 
                        some of the best trails in the world—as are rock-climbing and hiking. 
                        And the setting, with its gorgeous red rocks, can't be beat.
                      </p>
                      <ul>
                        <p> Points of Interest </p>
                        <li> Arches National Park </li>
                        <li> Canyonlands National Park</li>
                        <li> Dead Horse Point State Park</li>
                      </ul>
                    </md-card-content>

                    <div class="md-layout">
                      <div class="md-layout-item md-size-33 mx-auto text-center">
                        <md-button class="md-success">Purchase Ticket</md-button>
                        <md-button class="md-success">Save Trip</md-button>
                      </div>
                    </div>
                    <md-card-actions class="text-center"> </md-card-actions>
                  </md-card>
                </div>
              </div>
            </div>
          </md-tab>
        </md-tabs>
      </template>
    </nav-tabs-card>
  </div>
</template>

<script>
import { NavTabsCard } from "@/components";

export default {
  bodyClass: "landing-page",
  components: {
    NavTabsCard
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/recommend.png")
    },
    teamImg1: {
      type: String,
      default: require("@/assets/img/gramado.jpg")
    },
    teamImg2: {
      type: String,
      default: require("@/assets/img/buenosaires.jpg")
    },
    teamImg3: {
      type: String,
      default: require("@/assets/img/budapest.jpg")
    },
    teamImg4: {
      type: String,
      default: require("@/assets/img/KeyLargo.jpg")
    },
    teamImg5: {
      type: String,
      default: require("@/assets/img/brooklyn.jpg")
    },
    teamImg6: {
      type: String,
      default: require("@/assets/img/moab.jpg")
    }
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
