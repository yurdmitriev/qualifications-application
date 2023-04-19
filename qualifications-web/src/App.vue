<script setup>
import {RouterView} from 'vue-router'
import ProfileComponent from "@/components/navbar/ProfileComponent.vue";
import {useUserStore} from "@/stores/user";
import LoginComponent from "@/components/navbar/LoginComponent.vue";
import LogoComponent from "@/components/navbar/LogoComponent.vue";
import jwt from "@/plugins/jwt";

const userStore = useUserStore();
const token = localStorage.getItem("token");
const expires = localStorage.getItem("expires");

// TODO: clear expired token

if (token) {
    const userInfo = jwt.decode(token);
    console.log(userInfo);
    userStore.setUser(userInfo);
}
</script>

<template>
    <header>
        <nav class="navbar sticky-top navbar-expand-lg bg-body-tertiary border-bottom bg-light">
            <div class="container">
                <button class="navbar-toggler d-md-none collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#topMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <LogoComponent class="me-auto me-md-0 ms-2" />
                <div class="collapse navbar-collapse me-auto ms-md-3 order-last order-md-0" id="topMenu">
                    <ul class="navbar-nav me-auto my-2 my-md-0">
                        <li class="nav-item text-center">
                            <RouterLink class="nav-link fw-semibold" to="/vacancies">Вакансії</RouterLink>
                        </li>
                        <li class="nav-item text-center">
                            <RouterLink class="nav-link fw-semibold" to="/events">Події</RouterLink>
                        </li>
                    </ul>
                </div>
                <ProfileComponent v-if="userStore.info.email" :full-name="userStore.info.shortFullName" />
                <LoginComponent v-else />
            </div>
        </nav>
    </header>

    <RouterView/>
</template>

<style scoped lang="scss">

</style>
