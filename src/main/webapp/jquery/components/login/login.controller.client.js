(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    var profilePage = "../profile/profile.template.client.html"
    $(main);

    function main() {
        $usernameFld = $('#username');
        $passwordFld = $('#password');
        $loginBtn = $('#signInBtn');
        $loginBtn.click(login);
    }
    function login() {
        var username = $usernameFld.val();
        var password = $passwordFld.val();
        userService.login(username,password).then(loginSuccessful);
    }

    function loginSuccessful(response) {
        if(response.status == 200) {
            sessionStorage.username = response.username;
            sessionStorage.password = response.password;
            alert("Login successful!");
            window.location.href=profilePage;
        }
        else if(response.status == 409){
            alert('Incorrect credentials');
        }
    }

})();
