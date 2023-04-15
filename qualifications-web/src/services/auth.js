import { useUserStore } from "@/stores/user";

export const onsiteLogin = (email, password) => {
  const userStore = useUserStore();
  userStore.id = 1;
  userStore.email = email;
  return true;
};

export const logout = () => {

};