import { http } from "@/plugins/axios";

const token = localStorage.getItem("token") ?? undefined;

export const listVacancies = async (page, size) => {
  return await http().get(`/api/vacancies?size=${size}&page=${page}`);
}

export const getById = async (id) => {
  return await http().get(`/api/vacancies/${id}`);
};