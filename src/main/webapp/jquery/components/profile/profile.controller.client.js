(function() {

    var $email;
    var $username;
    var $firstName;
    var $lastName;
    var $role;
    var $dob;
    var $phone;
    var $updateBtn;
    var $globalId;
    var $logoutBtn;
    var loginPage = "../login/login.template.client.html";
    var userService = new UserServiceClient();

    if(sessionStorage.username!=undefined && sessionStorage.password!=undefined) {
        userService.login(sessionStorage.username, sessionStorage.password)
            .then(function (response) {
                console.log(response);
                console.log(response[0]);
                $globalId = response.json().id;
                init();
            })
    }
    else {
        init();
    }

    function init() {
        $email = $("#staticEmail");
        $username = $("#inputUsername");
        $firstName = $("#firstName");
        $lastName = $("#lastName");
        $role = $("#roleFld");
        $dob = $("#dobFld");
        $phone = $("#phone");
        $updateBtn = $("#updateBtn")
        $logoutBtn = $("#logoutBtn")

            if($globalId!=undefined){
                findUserById($globalId);
            }
        $updateBtn.click(updateUser);
        $logoutBtn.click(logoutUser);
    }

    function findUserById(userId) {
        userService.findUserById(userId)
            .then(renderUser);
    }

    function updateUser() {
        var user = {
            email: $email.val(),
            firstName: $firstName.val(),
            lastName: $lastName.val(),
            username: $username.val(),
            role: $role.val(),
            dob: $dob.val(),
            phone: $phone.val()
        };
        console.log(user);
        userService
            .updateUser($globalId,user)
            .then(success);
}

    function success() {
        // if(response == null){
        //     alert("unable to update");
        // }
        // else {
            alert("Updated successfully");
        // }
    }

    function renderUser(user) {
        console.log(user);
        $email.val(user.email);
        $username.val(user.username);
        $firstName.val(user.firstName);
        $lastName.val(user.lastName);
        $role.val(user.role);
        $phone.val(user.phone);
        $dob.val(user.dateOfBirth);
    }

    function logoutUser() {
        sessionStorage.removeItem("username");
        sessionStorage.removeItem("password");
        window.location.href=loginPage;
    }
})();