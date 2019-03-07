function restoreHitHistory() {
    $.ajax({
        url: 'http://localhost:56645/Lab4_war/web/hitdata/read',
        method: "get",
        success: function (response) {
            _chart = $('#myCanvas');
            let json = JSON.parse(response);
            if(response != null) {
                for (let i = 0; i < json.length ; i++) {
                     drawGenericPoint(
                         json[i].x * 500 / 14 + 250,
                         250 - json[i].y * 500 / 14,
                         "orange"
                     );
                     console.log(json[i].x)
                }
            }
        }
    })
}

