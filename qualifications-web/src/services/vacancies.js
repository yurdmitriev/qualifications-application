import { http } from "@/plugins/axios";

const token = localStorage.getItem("token") ?? undefined;

export const listRecent = async (size) => {
  return await http().get(`/api/vacancies?size=${size}`);
}

export const getById = async (id) => {
  return await http().get(`/api/vacancies/${id}`);
};