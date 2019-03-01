function sendHitData(X, Y, R) {
    let json = (String(JSON.stringify({
        X: Number(X),
        Y: Number(Y),
        R: Number(R),
    })));
    $.ajax({
        url: 'http://localhost:8080/Lab_4_war_exploded/web/hitdata/add',
        type: "post",
        contentType: "application/json",
        success: function (response) {

            let st = JSON.parse(response);
            console.log(st);
        },
        data: {
            content:  json
        }
    });
}