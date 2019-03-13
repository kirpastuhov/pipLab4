function restoreHitHistory() {
    $.ajax({
       url: 'http://localhost:8968/Lab4_war/web/hitdata/read',
        // url : 'http://localhost:8080/Lab_4_war_exploded/web/hitdata/read',
        method: "get",
        success: function (response) {
            _chart = $('#myCanvas');
            let json = JSON.parse(response);
            let currentR = Number(document.getElementById('inputR').value);
            let _size = Number(_chart.attr("width").replace("px", ""));
            _radius = Number($("#inputR").val());
            if (response != null) {
                for (let i = 0; i < json.length; i++) {
                    let x = json[i].x;
                    let y = json[i].y;
                    let r = json[i].r;
                    let res = json[i].isPointInArea;
                    if (r!== currentR) {
                        color = "grey";
                    } else {
                        if (res) {
                            color = "#28A745";
                        } else {
                            color = "#DC3545";
                        }
                    }

                    const draw_x = parseFloat(x) * 500 / 14 + _size / 2;
                    const draw_y = _size / 2 - parseFloat(y)* 500 / 14;

                    drawGenericPoint(draw_x, draw_y, color);
                }
            }
        }
    })
}

