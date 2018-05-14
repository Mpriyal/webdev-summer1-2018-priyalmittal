(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $usernameFld = $('#username');
        $passwordFld = $('#password');
        $loginBtn = $('#signUpBtn');
        $loginBtn.click(login);
    }
    function login() {
        var username = $usernameFld.val();
        var password = $passwordFld.val();
        userService.login(username,password).then(loginSuccessful);
    }

    function loginSuccessful() {
        alert("Logged in successfully!");
    }
})();
