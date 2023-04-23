import { http } from "@/plugins/axios";

const token = localStorage.getItem("token") ?? undefined;

export const onsiteLogin = async (email, password) => {
  return await http().post('/api/auth/login', {
    email: email,
    password: password
  });
};

export const registerUser = async (user) => {
  return await http().post('/api/auth/register', {
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
  return await http(token).get("/api/user");
}