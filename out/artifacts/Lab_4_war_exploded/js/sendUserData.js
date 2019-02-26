function sendUserData() {
    let Login = $('#Login').val();
    let Password = $('#Password').val();
    let json = (String(JSON.stringify({
        Password: Password,
        Login: Login
    })));
    $.ajax({
        url: 'http://localhost:8080/Lab_4_war_exploded/web/registration',
        type: "post",
        contentType: "application/json",
        data: {
            content:  json
        },
        success:function () {
            location.href = 'http://localhost:8080/Lab_4_war_exploded/main.html'
        },
        statusCode: {
            500: function () {
                $('.hide-block').html("Произошел троллинг, такой логин уже занят.");
            }
        }
    });
}