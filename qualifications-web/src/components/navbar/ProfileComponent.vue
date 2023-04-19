<template>
  <div class="dropdown">
    <a href="#" class="d-flex align-items-center link-dark text-decoration-none dropdown-toggle" role="button"
       data-bs-toggle="dropdown" aria-expanded="false">
      <UserIcon class="user-icon" />
      <span class="mx-2 fw-bold d-none d-md-inline">{{ fullName }}</span>
    </a>
    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-md-start text-small">
      <li>
        <RouterLink class="dropdown-item" to="/profile">Мій профіль</RouterLink>
      </li>
      <li>
        <RouterLink class="dropdown-item" to="/dashboard">Панель керування</RouterLink>
      </li>
      <li>
        <hr class="dropdown-divider">
      </li>
      <li><a class="dropdown-item" href="#" @click.prevent="logout">Вийти з акаунта</a></li>
    </ul>
  </div>
</template>

<script>
import {useUserStore} from "@/stores/user";
import UserIcon from "@/components/icons/UserIcon.vue";
export default {
  name: "ProfileComponent",
  components: { UserIcon },
  props: ["fullName"],
  methods: {
    logout() {
      const store = useUserStore();
      store.resetUser();
      this.$router.push("/");
      localStorage.removeItem("token");
      localStorage.removeItem("expires");
    }
  }
};
</script>

<style scoped lang="scss">
.user-icon {
  width: 30px;
  height: 30px;
}
</style>