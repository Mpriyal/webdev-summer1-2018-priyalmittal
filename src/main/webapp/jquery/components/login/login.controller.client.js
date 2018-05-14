(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
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
        console.log(response);
        if(response.status == 200) {
            alert("Login successful!");
        }
        else if(response.status == 409){
            alert('Incorrect credentials');
        }
    }
})();
