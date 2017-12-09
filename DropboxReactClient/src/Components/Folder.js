/**
 * Created by ManaliJain on 10/7/17.
 */
import React, {Component} from 'react';
import * as Validate from './validation';
import * as API from '../Api/FileUpload';
import {loginData,loginState} from '../Actions/index';
import {connect} from 'react-redux';
class Folder extends Component{

    constructor(props) {
        super(props);
        let loginData = this.props.loginDataProp;
        this.state = {
            "dir_name" : '',
            "user_uuid" : loginData.user_uuid,
            "message" : ''
        }
    }

    handleSubmitForFolder=(event) => {
        var valid = Validate.folderName(this.state);
        if (valid === '') {
            API.createDirectory(this.state)
                .then((res) => {
                    if (res.data.statusCode === 201) {
                        this.setState({
                            message: res.data.message,
                            dir_name: ''
                        });
                    } else if (res.data.statusCode === 500) {
                        this.setState({
                            message: res.data.message
                        });
                    } else if(res.data.statusCode === 400) {
                        this.setState({
                            message: res.data.message
                        });
                    } else if (res.data.statusCode === 601  || res.data.statusCode === 600) {
                        alert("Token expired or invalid. Please login again");
                        this.setState({
                            isLoggedIn: false,
                            message: res.data.message
                        });
                        sessionStorage.removeItem("jwtToken");
                        this.props.loginState(false);
                    }
                });
        } else {
            this.setState({
                ...this.state,
                message: valid
            });
            event.preventDefault();
        }
    }

    callAPI = () => {


    };


    render() {
        let messagediv =null;
        if(this.state.message !== ''){
            messagediv = <div className="clearfix">
                <div className="alert alert-success text-center" role="alert">{this.state.message}</div>
            </div>;
        } else{
            messagediv = <div></div>;
        }
        return(
            <div>
                <div className="row">
                    {messagediv}
                </div>
                <form className="form-horizontal InterestForm">
                    <div className="row">

                        <div className="form-group">
                            <div className="col-sm-2">
                                New Folder Name
                            </div>
                            <div className="col-sm-offset-1 col-sm-7">
                                <input type="text" className="form-control" placeholder="Folder Name"
                                       value={this.state.dir_name}
                                       onChange={(event) => {
                                           this.setState({...this.state,dir_name: event.target.value});
                                       }}
                                />
                            </div>
                        </div>
                    </div>

                    <div className="form-group">
                        <div className="col-sm-offset-3 col-sm-4">
                            <button type="button" className="btn btn-info"
                                    onClick={this.handleSubmitForFolder}>Submit</button>
                        </div>
                    </div>

                </form>
            </div>
        );
    }
}
// export default Interest;
// function mapDispatchToProps(dispatch) {
//     // return bindActionCreators({loginState:loginState},dispatch)
//     return {
//         loginState: (data) => dispatch(loginState(data)),
//         loginData: (data) => dispatch(loginData(data)),
//         interestUpdate: (data) => dispatch(interestUpdate(data))
//     };
// }

function mapStateToProps(state) {
    console.log("state App", state)
    return{
        loginDataProp : state.loginData
    };
}
function mapDispatchToProps(dispatch) {
    // return bindActionCreators({loginState:loginState},dispatch)
    return {
        loginState: (data) => dispatch(loginState(data))
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(Folder);