import { http } from "@/plugins/axios";

const token = localStorage.getItem("token") ?? undefined;

export const listPublishedEvents = async (page, size) => {
  return await http().get(`/api/events?size=${size}&page=${page}`);
}

export const listAllEvents = async (page, size) => {
  return await http(token).get(`/api/events/all?size=${size}&page=${page}`);
}

export const deleteById = async (id) => {
  return await http(token).delete(`/api/events/${id}`);
}

export const getPublishedEventById = async (id) => {
  return await http().get(`/api/events/${id}`);
};

export const setPublishedState = async (id, state) => {
  return await http(token).post(`/api/events/${id}/published`, {
    published: state ?? false
  });
}