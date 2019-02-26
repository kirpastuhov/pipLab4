function sendHitData() {
    let X = $('#X').val();
    let Y = $('#Y').val();
    let R = $('#R').val();
    let json = (String(JSON.stringify({
        X: Number(X),
        Y: Number(Y),
        R: Number(R),
    })));
    //console.log(json);
    $.ajax({
        url: 'http://localhost:8080/Lab_4_war_exploded/web/hitdata/add',
        type: "post",
        contentType: "application/json",
        success: function (response) {

            let st = JSON.parse(response.toString());
            console.log(st);
        },
        data: {
            content:  json
        }
    });
}