(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn;
    var $loginBtn;
    var userService = new UserServiceClient();
    var loginPage = "http://localhost:8080/jquery/components/login/login.template.client.html";
    $(main);

    function main() {
        $usernameFld = $('#UsernameFld');
        $passwordFld = $('#PasswordFld');
        $verifyPasswordFld = $('#verifyPasswordFld');
        $registerBtn = $('#signUpBtn')
        $loginBtn = $('#loginBtn')
        $registerBtn.click(register);
        $loginBtn.click(goToLogin);

        // $loginBtn.attr("href",loginPage);

    }

    function goToLogin() {
        window.location.href=loginPage;
    }

    function register() {

        var username = $usernameFld.val();
        var password = $passwordFld.val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var role = $('#roleFld').val();

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role
        };
        userService.register(user).then(signUpSuccessful);
    }

    function signUpSuccessful(response) {
        console.log(response);
        if(response.status == 200) {
            alert("Sign Up successful!");
        }
        else if(response.status == 409){
            alert('Username already exists. Please, choose another username!');
        }
    }

})();
