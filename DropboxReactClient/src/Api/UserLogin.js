/**
 * Created by ManaliJain on 9/29/17.
 */
const axios = require("axios")
// const api = process.env.REACT_APP_CONTACTS_API_URL || 'http://localhost:3001'


export const signup = (payload) => {
    console.log("payload", payload)
    return axios.post('http://localhost:8080/user/signup', payload)
        .then(function (response) {
            console.log(response);
            return response
        })
        .catch(function (error) {
            console.log(error);
            return error
        });
};

export const login = (payload) => {
    console.log("payload", payload)
    return axios.post('http://localhost:8080/user/login', payload)
        .then(function (response) {
            console.log(response);
            return response
        })
        .catch(function (error) {
            console.log(error);
            return error
        });
};

export const signout = () => {
    return axios.post('http://localhost:8080/signout', {}
    )
        .then(function (response) {
            console.log(response);
            return response
        })
        .catch(function (error) {
            console.log(error);
            return error
        });
};