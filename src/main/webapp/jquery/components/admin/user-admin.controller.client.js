////IIFE
//(function () {
//    var $usernameFld, $passwordFld;
//    var $removeBtn, $editBtn, $createBtn;
//    var $firstNameFld, $lastNameFld;
//    var $userRowTemplate, $tbody;
//    var userService = new AdminUserServiceClient();
//    $(main);
//
//    function main() { … }
//    function createUser() { … }
//    function findAllUsers() { … }
//    function findUserById() { … }
//    function deleteUser() { … }
//    function selectUser() { … }
//    function updateUser() { … }
//    function renderUser(user) { … }
//    function renderUsers(users) { … }
//})(); //anonymous function

(function hello() {

	jQuery(main);

	function main() {

        var users = [
			{username: 'bob'},
			{username: 'charlie'}
		];
        var tr = $('.wbdv-template');

        var body = $('tbody');
        for(var i=0;i<users.length;i++){
        	var user = users[i];
        	console.log(user);
            var clone = tr.clone();
            clone.find('.wbdv-username').html(user.username);
            body.append(clone);
		}
    }
}
)();