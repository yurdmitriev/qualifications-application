import { defineStore } from "pinia";
import { computed, ref } from "vue";

export const useUserStore = defineStore("user", () => {
  const id = ref(0);
  const firstName = ref("");
  const lastName = ref("");
  const patronymic = ref("");
  const shortFullName = computed(() => `${lastName.value} ${firstName.value}`);
  const email = ref("");
  const role = ref("GUEST")

  const resetUser = () => {
    id.value = 0;
    firstName.value = "";
    lastName.value = "";
    patronymic.value = "";
    email.value = "";
    role.value = "GUEST";
  };

  const setUser = (user) => {
    id.value = user.id ?? 0;
    firstName.value = user.firstName ?? "";
    lastName.value = user.lastName ?? "";
    patronymic.value = user.patronymic ?? "";
    email.value = user.email ?? "";
    role.value = user.role ?? "GUEST";
  }

  return {
    info: {
      id, firstName, lastName, shortFullName, email, role
    }, resetUser, setUser
  };
});