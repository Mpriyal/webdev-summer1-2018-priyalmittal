(function() {

    var $email;
    var $username;
    var $password;
    var $firstName;
    var $lastName;
    var $role;
    var $dob;
    var $phone;
    var $updateBtn;
    var $logoutBtn;
    var loginPage = "../login/login.template.client.html";
    var userService = new UserServiceClient();
    var profileURL = new URLSearchParams(window.location.search);
    var getId = profileURL.get('userId');

    $(init);

    function init() {

        $email = $("#staticEmail");
        $username = $("#inputUsername");
        $password = $("#inputPassword");
        $firstName = $("#firstName");
        $lastName = $("#lastName");
        $role = $("#roleFld");
        $dob = $("#dobFld");
        $phone = $("#phone");
        $updateBtn = $("#updateBtn");
        $logoutBtn = $("#logoutBtn");
            if(getId!=null){
                findUserById(getId);
            }
        $updateBtn.click(updateUser);
        $logoutBtn.click(logoutUser);
    }

    function findUserById(userId) {
        userService.findUserById(userId)
            .then(renderUser);
    }

    function updateUser() {
        var dob = new Date($dob.val());
        dob.setDate(dob.getDate()+1);
        var user = {
            email: $email.val(),
            firstName: $firstName.val(),
            lastName: $lastName.val(),
            username: $username.val(),
            password: $password.val(),
            role: $role.val(),
            dateOfBirth: dob,
            phone: $phone.val()
        };
        console.log(user);
        userService
            .updateUser(getId,user)
            .then(success);
}

    function success() {
            alert("Updated successfully");
    }

    function renderUser(user) {
        console.log(user);
        var now = new Date(user.dateOfBirth);
        now.setDate(now.getDate()+1)
        var day = ("0" + now.getDate()).slice(-2);
        var month = ("0" + (now.getMonth() + 1)).slice(-2);
        var today = now.getFullYear()+"-"+(month)+"-"+(day);

        console.log(today);
        console.log(user);
        $email.val(user.email);
        $username.val(user.username);
        $password.val(user.password);
        $firstName.val(user.firstName);
        $lastName.val(user.lastName);
        $role.val(user.role);
        $phone.val(user.phone);
        $dob.val(today);
        console.log($dob);
    }

    function logoutUser() {
        window.location.href=loginPage;
    }
})();