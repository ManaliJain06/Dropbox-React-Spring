/**
 * Created by ManaliJain on 10/14/17.
 */
const axios = require("axios");

export const createGroup = (payload) => {
    // const token = sessionStorage.jwtToken;
    // console.log("token",token);
    return axios.post('http://localhost:8080/groups/createGroup', payload
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

export const getGroups = (payload) => {
    // const token = sessionStorage.jwtToken;
    // console.log("token",token);
    let user_uuid = payload.user_uuid;
    return axios.get(`http://localhost:8080/groups/getGroups/${user_uuid}`
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

export const addMember = (payload) => {
    // const token = sessionStorage.jwtToken;
    // console.log("token",token);
    return axios.post('http://localhost:8080/groups/addMember', payload
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

export const deleteMember = (payload) => {
    // const token = sessionStorage.jwtToken;
    // console.log("token",token);
    return axios.post('http://localhost:8080/groups/deleteMember', payload
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

export const deleteFileFromGroup = (payload) => {
    // const token = sessionStorage.jwtToken;
    // console.log("token",token);
    return axios.post('http://localhost:8080/groups/deleteFileFromGroup', payload
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

export const deleteGroup = (payload) => {
    // const token = sessionStorage.jwtToken;
    // console.log("token", token);
    return axios.post('http://localhost:8080/groups/deleteGroup', payload
    )
        .then(function (response) {
            console.log("cookie: ", document.cookie);
            console.log(response);
            return response
        })
        .catch(function (error) {
            console.log(error);
            return error
        });
};