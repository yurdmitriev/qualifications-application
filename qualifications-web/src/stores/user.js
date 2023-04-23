import { defineStore } from "pinia";
import { computed, ref } from "vue";

export const useUserStore = defineStore("user", () => {
  const info = {
    id: ref(0),
    firstName: ref(""),
    lastName: ref(""),
    patronymic: ref(""),
    email: ref(""),
    role: ref("GUEST"),
    shortFullName: computed(() => `${info.lastName.value} ${info.firstName.value}`)
  };

  const resetUser = () => {
    info.id.value = 0;
    info.firstName.value = "";
    info.lastName.value = "";
    info.patronymic.value = "";
    info.email.value = "";
    info.role.value = "GUEST";
  };

  const setUser = (user) => {
    info.id.value = user.id ?? 0;
    info.firstName.value = user.firstName ?? "";
    info.lastName.value = user.lastName ?? "";
    info.patronymic.value = user.patronymic ?? "";
    info.email.value = user.email ?? "";
    info.role.value = user.role ?? "GUEST";
  }

  const isAuthenticated = () => {
    return info.email.value && info.id.value;
  }

  const hasCompetencies = () => {
    return info.role.value === 'LECTURER' || info.role.value === 'STUDENT';
  }

  return {
    info, resetUser, setUser, isAuthenticated, hasCompetencies
  };
});