import { http } from "@/plugins/axios";

export const onsiteLogin = async (email, password) => {
  return await http().post('/api/auth/login', {
    email: email,
    password: password
  });
};