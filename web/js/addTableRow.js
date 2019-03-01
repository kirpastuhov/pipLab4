function addTableRow() {
    $.ajax({
        url: 'http://localhost:8080/Lab_4_war_exploded/web/hitdata/read',
        method: "get",
        success: function (response) {
            let json = JSON.parse(response);
            if(response != null) {
                for (let i = 0; i < json.length ; i++) {
                    $("#hits_table").first().append("" +
                        "<tr>" +
                        "<td>" +
                            (i + 1) +
                        "</td>" +
                        "<td>" +
                            json[i].x +
                        "</td>" +
                        "<td>" +
                            json[i].y +
                        "</td>" +
                        "<td>" +
                            json[i].r +
                        "</td>" +
                        "<td>" +
                            json[i].isPointInArea +
                        "</td>" +
                        "<td>" +
                            moment.unix(json[i].currentTime).format() +
                        "</td>" +
                        "<td>" +
                            json[i].executionTime +
                        "</td>" +
                        "</tr>" +
                        "");
                }
            }
        }
    })



}