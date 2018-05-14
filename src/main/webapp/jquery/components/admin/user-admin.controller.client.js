(function () {

        jQuery(main);
        var tbody;
        var template;
        var userService = new UserServiceClient();

        function main() {
            tbody = $('tbody');
            template = $('.wbdv-template');
            $('#wbdv-create').click(createUser);
            findAllUsers();
        }

        function findAllUsers() {
            userService.findAllUsers().then(renderUsers);
        }

        /**
		 * This function creates a user when the present input fields are
		 * populated and the plus-sign button is clicked
         */
        function createUser() {
        	var username = $('#usernameFld').val();
            var password = $('#passwordFld').val();
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

            userService.createUser(user).then(findAllUsers);
		}

		function deleteUser(event) {
            var deleteBtn = $(event.currentTarget);
            var userId = deleteBtn.parent().parent().parent().parent().attr('id');
            userService.deleteUser(userId);
        }

        function updateUser(event) {
            var editBtn = $(event.currentTarget);
            var userId = editBtn.parent().parent().parent().parent().attr('id');
            var username = $('#usernameFld').val();
            var password = $('#passwordFld').val();
            var firstName = $('#firstNameFld').val();
            var lastName = $('#lastNameFld').val();
            var role = $('#roleFld').val();

            var user = new User(username, password, firstName, lastName, null, null, role, null);

            userService.updateUser(userId, user).then(findAllUsers);
        }

        function renderUsers(users) {

            tbody.empty();
            for (var i = 0; i < users.length; i++) {
                var user = users[i];
                var clone = template.clone();
                clone.attr('id', user.id);
                clone.find('.wbdv-username').html(user.username);
                clone.find('.wbdv-password').html(user.password);
                clone.find('.wbdv-first-name').html(user.firstName);
                clone.find('.wbdv-last-name').html(user.lastName);
                clone.find('.wbdv-role').html(user.role);
                clone.find('.wbdv-remove').click(deleteUser);
                clone.find('.wbdv-edit').click(updateUser);
                tbody.append(clone);
            }
        }

    }
)();