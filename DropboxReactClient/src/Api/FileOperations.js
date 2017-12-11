/**
 * Created by ManaliJain on 10/11/17.
 */

const axios = require("axios");
// const api = process.env.REACT_APP_CONTACTS_API_URL || 'http://localhost:3001'
// const token = localStorage.jwtToken;


export const starItem = (payload) => {
    // const token = sessionStorage.jwtToken;
    // console.log("token",token);
    return axios.post('http://localhost:8080/files/starFileOrDir', payload
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

export const deleteFile = (payload) => {
    // const token = sessionStorage.jwtToken;
    // console.log("token",token);
    return axios.post('http://localhost:8080/files/deleteFileAndDir/', payload
    )
        .then(function (response) {
            // console.log("cookie: ",document.cookie);
            console.log(response);
            return response
        })
        .catch(function (error) {
            console.log(error);
            return error
        });
};

export const deleteDir = (payload) => {
    // const token = sessionStorage.jwtToken;
    // console.log("token",token);
    return axios.post('http://localhost:8080/files/deleteFileAndDir/', payload
    )
        .then(function (response) {
            // console.log("cookie: ",document.cookie);
            console.log(response);
            return response
        })
        .catch(function (error) {
            console.log(error);
            return error
        });
};

export const deleteFileInDir = (payload) => {
    // const token = sessionStorage.jwtToken;
    // console.log("token",token);
    return axios.post('http://localhost:8080/files/deleteFileFromDir', payload
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

export const shareFile = (payload) => {
    // const token = sessionStorage.jwtToken;
    // console.log("token",token);
    return axios.post('http://localhost:8080/files/shareFileOrDir', payload
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

export const shareDir = (payload) => {
    // const token = sessionStorage.jwtToken;
    // console.log("token",token);
    return axios.post('http://localhost:8080/files/shareFileOrDir', payload
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

// export const shareLink = (payload) => {
//     // const token = sessionStorage.jwtToken;
//     // console.log("token",token);
//     return axios.post('http://localhost:8080/files/shareLink', payload
//     )
//         .then(function (response) {
//             console.log("cookie: ",document.cookie);
//             console.log(response);
//             return response
//         })
//         .catch(function (error) {
//             console.log(error);
//             return error
//         });
// };