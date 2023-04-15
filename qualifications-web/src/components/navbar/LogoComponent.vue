<template>
    <RouterLink class="navbar-brand" to="/">
        <img alt="Vue logo" src="@/assets/logo.svg" />
    </RouterLink>
</template>

<script>
import {RouterLink} from "vue-router";

export default {
    name: "LogoComponent",
    components: {RouterLink}
}
</script>

<style scoped>
img {
    height: 30px;
}
</style>