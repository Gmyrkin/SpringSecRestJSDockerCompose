
    // Для отображения списка пользователей в таблице

$(document).ready(function() {
    getAllUsers();
});

    function getAllUsers() {
    $("#table").empty();
    $.ajax({
        url: '/admin/users',
        type: 'GET',
        dataType: 'JSON',
        timeout: 3000,
        success: function (data) {
            console.log(data);
            $.each(data, function (i, user) {
                    if(user.role == "ROLE_ADMIN") {
                    $('#menuUser').trigger('click');
                    $('#main2').trigger('click');
                    $('#blockMenuforUser').hide();
                }
                $("#table").append($('<tr>').append(
                    $('<td>').append($('<span>')).text(user.id),
                    $('<td>').append($('<span>')).text(user.firstName),
                    $('<td>').append($('<span>')).text(user.lastName),
                    $('<td>').append($('<span>')).text(user.password),
                    $('<td>').append($('<span>')).text(getUserRolesText(user)),

                    $('<td>').append($('<button>').text("Edit").attr({
                            "type": "button",
                            "class": "btn btn-primary edit",
                            "data-toggle": "modal",
                            "data-target": "#myModal",
                        })
                            .data("user", user)),

                    $('<td>').append($('<button>').text("Delete").attr({
                            "type": "button",
                            "class": "btn btn-danger delete",
                            "data-toggle": "modal",
                            "data-target": "#myModalDelete",
                        })
                            .data("user", user))
                        )
                    );
                });
            },
        });
    }

    function getUserRolesText(user) {
        if (!user || !Array.isArray(user.roles)){
            throw new Error("Invalid user object");}

        const roles = user.roles.map(role => role.name).join("\n")
        return roles;
}

    //Для редактирования user через модальное окно

    $(document).on("click", ".edit", function () {
        let user = $(this).data('user');
        console.log(user);

        $('#idInput').val(user.id);
        $('#firstNameInput').val(user.firstName);
        $('#lastNameInput').val(user.lastName);
        $('#passwordInput').val(user.password);
        $('#roleInput').val(user.role);



    });

    $(document).on("click", ".editUser", function () {
        let formData = $(".formEditUser").serializeArray();
        console.log(formData);
    $.ajax({
        type: 'POST',
        url: '/admin/update/user',
        data: formData,
        timeout: 100,
        success: function () {
            getAllUsers();
        }
    });
});

    //Для удаления user через модальное окно

    $(document).on("click", ".delete", function () {
    let user = $(this).data('user');

    $('#id').val(user.id);
    $('#firstName').val(user.firstName);
    $('#lastName').val(user.lastName);

    $(document).on("click", ".deleteUser", function () {
        $.ajax({
            type: 'POST',
            url: '/admin/delete/user',
            data: {id: $('#id').val()},
            timeout: 100,
            success: function () {
                getAllUsers();
            }
        });
    });
})

    // Для добавления пользователя

$('.addUser').click(function () {
    $('#usersTable').trigger('click');
    let formData = $(".formAddUser").serializeArray();

    $.ajax({
        type: 'POST',
        url: '/admin/create/user',
        data: formData,
        timeout: 100,
        success: function () {
            $('.formAddUser')[0].reset();
            getAllUsers();
        }
    });
});

    //Для отображения информаций о пользователе на его странице и сокрытия меню в зависемости от роли

// $(document).ready(getTable());
// function getTable() {
//     $("#userTable").empty();
//     $.ajax({
//         type: 'GET',
//         url: '/user/getTable',
//         timeout: 3000,
//         error: function() {
//             $('#blockMenuforUser').hide();
//         },
//         success: function(data) {
//             console.log(data);
//             $.each(data, function (i, user) {
//                 if(user.role == "ROLE_USER") {
//                     $('#menuUser').trigger('click');
//                     $('#main2').trigger('click');
//                     $('#blockMenuforAdmin').hide();
//                 }
//                 $("#userTable").append($('<tr>').append(
//                     $('<td>').append($('<span>')).text(user.id),
//                     $('<td>').append($('<span>')).text(user.firstName),
//                     $('<td>').append($('<span>')).text(user.lastName),
//                     $('<td>').append($('<span>')).text(user.password),
//                     )
//                 );
//             });
//         }
//     });
// }