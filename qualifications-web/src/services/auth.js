import { http } from "@/plugins/axios";
import { useUserStore } from "@/stores/user";
import jwt from "@/plugins/jwt";

export const retrieveUser = () => {
  const expires = localStorage.getItem("expires");
  const now = Date.now();
  const token = localStorage.getItem("token");
  const userStore = useUserStore();

  if (+expires <= now) {
    userStore.resetUser();
    localStorage.removeItem("token");
    localStorage.removeItem("expires");
  }

  if (token) {
    const userInfo = jwt.decode(token);
    userStore.setUser(userInfo);
    getCurrentUser().then(r => userStore.setUser(r.data)).catch(err => null /* TODO: catch error */);
  }
};

export const onsiteLogin = async (email, password) => {
  return await http().post("/api/auth/login", {
    email: email,
    password: password
  });
};

export const registerUser = async (user) => {
  return await http().post("/api/auth/register", {
    email: user.email,
    password: user.password,
    passwordConfirm: user.passwordConfirm,
    role: user.role,
    firstName: user.firstName,
    lastName: user.lastName,
    patronymic: user.patronymic,
    company: user.company
  });
};

export const getCurrentUser = async () => {
  const token = localStorage.getItem("token");
  return await http(token).get("/api/user");
};