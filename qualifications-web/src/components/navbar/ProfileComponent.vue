<template>
    <div class="dropdown">
        <a href="#" class="d-flex align-items-center link-dark text-decoration-none dropdown-toggle" role="button" data-bs-toggle="dropdown"
           aria-expanded="false">
            <UserIcon class="user-icon"/>
            <span class="mx-2 fw-bold">{{ fullName }}</span>
        </a>
        <ul class="dropdown-menu text-small">
            <li><RouterLink class="dropdown-item" to="/profile">Мій профіль</RouterLink></li>
            <li><RouterLink class="dropdown-item" to="/dashboard">Панель керування</RouterLink></li>
            <li>
                <hr class="dropdown-divider">
            </li>
            <li><a class="dropdown-item" href="#">Вийти з акаунта</a></li>
        </ul>
    </div>
</template>

<script>
import UserIcon from "@/components/icons/UserIcon.vue";

export default {
    name: "ProfileComponent",
    components: {UserIcon},
    props: ['fullName']
}
</script>

<style scoped lang="scss">
.user-icon {
  width: 30px;
  height: 30px;
}
</style>