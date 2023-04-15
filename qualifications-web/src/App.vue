<script setup>
import {RouterView} from 'vue-router'
import ProfileComponent from "@/components/navbar/ProfileComponent.vue";
import {useUserStore} from "@/stores/user";
import LoginComponent from "@/components/navbar/LoginComponent.vue";
import LogoComponent from "@/components/navbar/LogoComponent.vue";

const userStore = useUserStore();
</script>

<template>
    <header>
        <nav class="navbar sticky-top navbar-expand-lg bg-body-tertiary border-bottom bg-light">
            <div class="container">
                <button v-if="$route.name === 'dashboard'" class="navbar-toggler d-md-none collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <LogoComponent />
                <ProfileComponent v-if="userStore.id !== 0" :full-name="userStore.shortFullName" />
                <LoginComponent v-else />
            </div>
        </nav>
    </header>

    <RouterView/>
</template>

<style scoped lang="scss">

</style>
