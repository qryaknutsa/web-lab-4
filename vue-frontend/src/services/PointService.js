import axios from 'axios'

const POINT_API_BASE_URL = 'http://localhost:8410/api/point'

class PointService {
    getPoints(login) {
        return axios.get(POINT_API_BASE_URL+"/"+login);
    }

    setPoint(x, y, r, login) {
        return axios.post(POINT_API_BASE_URL, {
            x: parseFloat(x),
            y: parseFloat(y),
            r: parseFloat(r),
            login: login
        });
    }

    deleteTable(login){
        return axios.delete(POINT_API_BASE_URL+"/delete/"+login);
    }
}


export default new PointService();
