function signIn() {
    let Login = $('#Login_S').val();
    let Password = $('#Password_S').val();
    let json = (String(JSON.stringify({
        Password: Password,
        Login: Login
    })));
    console.log(json);
    $.ajax({
        url: 'http://localhost:7857/Lab4_war/web/sign/in',
        type: "post",
        contentType: "application/json",
        data: {
            content:  json
        },
         success:function () {
            location.href = 'http://localhost:7857/Lab4_war/main.html'

         },
         statusCode: {
             500: function () {
                 $('.hide-block').html("User not found");
             }
         }
    });
}