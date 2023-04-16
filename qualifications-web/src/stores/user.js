import { defineStore } from "pinia";
import { computed, ref } from "vue";

export const useUserStore = defineStore("user", () => {
  const id = ref(0);
  const firstName = ref("");
  const lastName = ref("");
  const patronymic = ref("");
  const shortFullName = computed(() => `${lastName.value} ${firstName.value}`);
  const email = ref("");
  const role = ref("guest")

  const resetUser = () => {
    id.value = 0;
    firstName.value = undefined;
    lastName.value = undefined;
    email.value = undefined;
    role.value = "guest";
  };

  const setUser = (user) => {
    id.value = user.id;
    firstName.value = user.firstName;
    lastName.value = user.lastName;
    patronymic.value = user.patronymic;
    email.value = user.email;
    role.value = user.role;
  }

  return {
    info: {
      id, firstName, lastName, shortFullName, email, role
    }, resetUser, setUser
  };
});