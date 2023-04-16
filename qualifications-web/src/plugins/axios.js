import axios from 'axios';

export const http = (token) => {
  const params = {
    baseURL: import.meta.env.VITE_BACKEND_URL,
    headers: {}
  };

  if (token) {
    params.headers.Authorization = `Bearer ${token}`;
  }

  console.log(params);

  return axios.create(params);
};