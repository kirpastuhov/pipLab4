function sendUserData() {
    let Login = $('#Login').val();
    let Password = $('#Password').val();
    let json = (String(JSON.stringify({
        Password: Password,
        Login: Login
    })));
    $.ajax({
        url: 'http://localhost:5635/Lab4_war/web/registration',
        type: "post",
        contentType: "application/json",
        data: {
            content:  json
        },
        success:function () {
            location.href = 'http://localhost:5635/Lab4_war/main.html'
        },
        statusCode: {
            500: function () {
                $('.hide-block').html("Произошел троллинг, такой логин уже занят.");
            }
        }
    });
}
function quit() {
    $.ajax({
        url: 'http://localhost:5635/Lab4_war/web/sign/out',
        type: "post",
        contentType: "application/json",
        success:function () {
            location.href = 'http://localhost:5635/Lab4_war/index.html'
        }
    });

}