(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld, $firstNameFld, $lastNameFld, $phoneFld, $emailFld, $dobFld;
    var $registerBtn;
    var $loginBtn;
    var userService = new UserServiceClient();
    var loginPage = "../login/login.template.client.html";
    var profilePage = "../profile/profile.template.client.html";
    $(main);

    function main() {
        $usernameFld = $('#UsernameFld');
        $passwordFld = $('#PasswordFld');
        $verifyPasswordFld = $('#verifyPasswordFld');
        $firstNameFld = $('#FirstNameFld');
        $lastNameFld = $('#LastNameFld');
        $phoneFld = $('#PhoneFld');
        $emailFld = $('#EmailFld');
        $dobFld = $('#DobFld');
        $registerBtn = $('#signUpBtn')
        $loginBtn = $('#loginBtn')
        $registerBtn.click(register);
        $loginBtn.click(goToLogin);

    }

    function goToLogin() {
        window.location.href=loginPage;
    }

    function register() {

        var username = $usernameFld.val();
        var password = $passwordFld.val();
        var verifyPassword = $verifyPasswordFld.val();
        var firstName = $firstNameFld.val();
        var lastName = $lastNameFld.val();
        var phone = $phoneFld.val();
        var email = $emailFld.val();
        var dob = new Date($dobFld.val());
        var role = $('#roleFld').val();
        dob.setDate(dob.getDate()+1);

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            phone: phone,
            email: email,
            dateOfBirth: dob,
            role: role
        };

        if(password == verifyPassword) {
            userService.register(user).then(signUpSuccessful);
        }
        else {
            alert("Password and verify password does not match!");
        }
    }

    function signUpSuccessful(response) {
        if(response!=null) {
            alert("Sign Up successful!");
            var userId = response.id;
                window.location.href=profilePage+"?userId="+userId;
        }
        else {
            alert('Username already exists. Please, choose another username!');
        }
    }

})();
