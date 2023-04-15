import { defineStore } from "pinia";
import { computed, ref } from "vue";

export const useUserStore = defineStore("user", () => {
  const id = ref(0);
  const firstName = ref("Петро");
  const lastName = ref("Петров");
  const email = ref("");
  const shortFullName = computed(() => `${lastName.value} ${firstName.value}`);

  const resetUser = () => {
    id.value = 0;
    firstName.value = undefined;
    lastName.value = undefined;
    email.value = undefined;
  }

  return { id, firstName, lastName, shortFullName, email, resetUser };
});