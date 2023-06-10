import { http } from "@/plugins/axios";

const token = localStorage.getItem("token") ?? undefined;

export const listPublishedVacancies = async (page, size) => {
  return await http().get(`/api/vacancies?size=${size}&page=${page}`);
}

export const listAllVacancies = async (page, size) => {
  return await http(token).get(`/api/vacancies/all?size=${size}&page=${page}`);
}

export const deleteById = async (id) => {
  return await http(token).delete(`/api/vacancies/${id}`);
}

export const getById = async (id) => {
  return await http().get(`/api/vacancies/${id}`);
};

export const setPublishedState = async (id, state) => {
  return await http(token).post(`/api/vacancies/${id}/published`, {
    published: state ?? false
  });
}

export const createVacancy = async (data) => {
  return await http(token).post('/api/vacancies', data);
}