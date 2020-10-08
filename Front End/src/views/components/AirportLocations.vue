<template>
    <md-field>
        <label for="Airports"></label>
        <md-select v-model="locations" name="locations" multiple>
            <md-option v-for="location in locations" :key="location.CityId" value="location.PlaceName"></md-option>
        </md-select>
    </md-field>
</template>

<script>
export default {
    name: "AirportLocations",
    props: {
        locations: [],
    }
}
</script>

<style scoped>

</style>