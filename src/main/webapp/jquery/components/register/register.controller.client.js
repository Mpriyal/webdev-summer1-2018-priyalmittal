(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn;
    // var $loginBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $usernameFld = $('#UsernameFld');
        $passwordFld = $('#PasswordFld');
        $verifyPasswordFld = $('#verifyPasswordFld');
        $registerBtn = $('#signUpBtn')
        $registerBtn.click(register);
        // $('#loginBtn').attr("href",loginPage);

    }

    function register() {
        var username = $usernameFld.val();
        var password = $passwordFld.val();
        var user = new User(username,password,null,null,null,null,null,null);
        userService.register(user);
    }
})();
