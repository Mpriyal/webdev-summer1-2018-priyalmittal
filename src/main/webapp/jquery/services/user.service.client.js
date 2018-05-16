function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.findUserById = findUserById;
    this.register = register;
    this.login = login;
    this.setSession = setSession;
    this.profile = profile;
    this.url = window.location.origin+'/api/user';
    this.registerUrl = window.location.origin+'/api/register';
    this.loginUrl = window.location.origin+'/api/login';
    this.setSessionUrl = window.location.origin+'/api/session/set';
    this.profileUrl = window.location.origin+'/api/profile'
    var self = this;

    function createUser(user) {
        return fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function updateUser(userId,user) {
        return fetch(self.url+"/"+userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type' : 'application/json'
            }
        }).then(function (response) {
            return response.json();
        })
    }

    function findUserById(userId) {
        return fetch(self.url+'/'+userId)
            .then(function(response) {
                return response.json();
            });
    }

    function deleteUser(userId) {
        return fetch(self.url + '/' + userId, {
            method: 'delete',
        })
    }

    function findAllUsers() {
        return fetch(self.url)
        .then(function (response) {
            return response.json();
        });
    }

    function register(user) {
        return fetch(self.registerUrl, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
            .then(function (response){
                if (response.status == 409){
                    return null;
                }
                return response.json();
            });
    }

    function login(username, password) {
        var user = {
            username: username,
            password: password,
            firstName: null,
            lastName: null,
            email: null,
            phone: null,
            role: null,
            dateOfBirth: null
        };
        return fetch(self.loginUrl, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
            .then(function (response){
                if (response.status == 409){
                    return null;
                }
            return response.json();
        });
    }

    function setSession(username, password) {
        return fetch(self.setSessionUrl+'/username/'+username)
            .then(function (userResponse) {
                return fetch(self.setSessionUrl+'/password/'+password)
                    .then(function (passResponse) {
                    console.log(userResponse+ passResponse);
                    return userResponse+ passResponse;
                    });
            });
    }

    function profile(user) {
        return fetch(self.profileUrl, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }
}