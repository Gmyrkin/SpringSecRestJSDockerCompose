
// Для отображения списка пользователей в таблице

$(document).ready(function() {
    getAllUsers();
});

function getAllUsers() {
    $("#table").empty();
    $.ajax({
        url: '/userOne',
        type: 'GET',
        dataType: 'JSON',
        timeout: 3000,
        error: function() {
            $('#blockMenuforAdmin').hide();
        },
        success: function (data) {
            console.log(data);
            $.each(data, function (i, user) {
                $("#table").append($('<tr>').append(
                        $('<td>').append($('<span>')).text(user.id),
                        $('<td>').append($('<span>')).text(user.firstName),
                        $('<td>').append($('<span>')).text(user.lastName),
                        $('<td>').append($('<span>')).text(user.password),
                        $('<td>').append($('<span>')).text(getUserRolesText(user)),


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

