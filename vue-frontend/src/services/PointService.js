import axios from 'axios'

const POINT_API_BASE_URL = 'http://localhost:8410/api/point'

class PointService {
    getPoints() {
        return axios.get(POINT_API_BASE_URL);
    }

    setPoint(x, y, r) {
        return axios.post(POINT_API_BASE_URL, {
            x: parseFloat(x),
            y: parseFloat(y),
            r: parseFloat(r)
        });
    }

    deleteTable(){
        return axios.delete(POINT_API_BASE_URL);
    }
}


export default new PointService();
