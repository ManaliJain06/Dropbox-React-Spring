/**
 * Created by ManaliJain on 10/2/17.
 */
const axios = require("axios");
// const api = process.env.REACT_APP_CONTACTS_API_URL || 'http://localhost:3001'
// const token = localStorage.jwtToken;


export const saveInterest = (payload) => {
    // const token = sessionStorage.jwtToken;
    // console.log("token",token);
    return axios.post('http://localhost:8080/user/interest', payload
    )
        .then(function (response) {
            console.log("cookie: ",document.cookie);
            console.log(response);
            return response
        })
        .catch(function (error) {
            console.log(error);
            return error
        });
};

export const saveAbout = (payload) => {
    // const token = sessionStorage.jwtToken;
    // console.log("payload", payload)
    return axios.post('http://localhost:8080/user/about', payload
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

export const getLinks = (payload) => {
    // const token = sessionStorage.jwtToken;
    let user_uuid = payload.user_uuid;
    return axios.get(`http://localhost:8080/files/getLinks/${user_uuid}`
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