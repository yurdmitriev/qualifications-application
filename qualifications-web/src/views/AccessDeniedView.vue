<template>
  <main class="d-flex flex-grow-1 flex-column">
    <div class="container my-auto text-center">
      <h1 class="">Доступ заборонено!</h1>
      <h5 class="mt-3">У Вас немає права доступу до цієї сторінки</h5>
      <RouterLink to="/" class="btn btn-primary btn-lg mt-5" replace>Перейти на головну</RouterLink>
    </div>
  </main>
</template>

<script>
import { useUserStore } from "@/stores/user";

export default {
  name: "AccessDeniedView",
  props: ["query"],
  beforeMount() {
    const userStore = useUserStore();

    if (!userStore.isAuthenticated() || !this.query) {
      this.$router.replace("/");
    }
  }
};
</script>

<style scoped>

</style>