import jwt_decode from "jwt-decode";

const jwt = {
  decode: (token) => jwt_decode(token),
  install(app) {
    app.config.globalProperties.$jwt = {
      decode: this.decode
    }
  }
}

export default jwt;