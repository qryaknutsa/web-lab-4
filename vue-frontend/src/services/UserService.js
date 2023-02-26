import axios from 'axios'

const USER_API_BASE_URL = 'http://localhost:8410/api/user'


class UserService {

    isExist(login, password) {
        return axios.post(USER_API_BASE_URL + "/" + login, {
            login: login,
            password: password,
        });
    }

    setUser(login, password) {
        return axios.post(USER_API_BASE_URL, {
            login: login,
            password: password,
        });
    }

    deleteUsers() {
        return axios.delete(USER_API_BASE_URL);
    }
}


export default new UserService();