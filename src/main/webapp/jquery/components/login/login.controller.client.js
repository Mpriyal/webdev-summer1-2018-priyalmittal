(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    var profilePage = "../profile/profile.template.client.html";
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
        if(response!=null && response.username!=null && response.password!=null) {
            userService.setSession(response.username, response.password).then(function () {
                alert("Login successful!");
                var userId = response.id;
                window.location.href=profilePage+"?userId="+userId;
            });
        }
        else if (response == null) {
            alert('Incorrect credentials');
        }
    }

})();
