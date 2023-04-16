import { http } from "@/plugins/axios";

export const onsiteLogin = async (email, password) => {
  return await http().get('users/test');
};

export const logout = () => {

};