function restoreTable() {
    //$("#hits_table td").remove();
    $.ajax({
        //url: 'http://localhost:8968/Lab4_war/web/hitdata/read',
        url : 'http://localhost:8080/Lab_4_war_exploded/web/hitdata/read',
        // url: 'http://localhost:3080/Lab4_war/web/hitdata/read',
        method: "get",
        success: function (response) {
            let json = JSON.parse(response);
            if(response != null) {
                for (let i = 0; i < json.length ; i++) {
                    $("#hits_table").first().prepend("" +
                        "<tr>" +
                        "<td class='dataX'>" +
                        Math.round(json[i].x * 100)/100 +
                        "</td>" +
                        "<td class='dataY'>" +
                        Math.round(json[i].y * 100)/100 +
                        "</td>" +
                        "<td class='dataR'>" +
                        json[i].r +
                        "</td>" +
                        "<td class='dataRes'>" +
                        json[i].isPointInArea +
                        "</td>" +
                        "<td>" +
                        moment.unix(Math.round(json[i].currentTime/1000)).format("DD.MM.YYYY, h:mm:ss a") +
                        "</td>" +
                        "<td>" +
                        json[i].executionTime +
                        "</td>" +
                        "</tr>" +
                        "");
                }
            }
        },
        statusCode: {
            500: function () {
                // location.href = 'http://localhost:1314/Lab4_war/error.html'
                //  location.href = 'http://localhost:3080/Lab4_war/error.html'
                location.href = 'http://localhost:8080_exploded/Lab_4_war_exploded/error.html'
            }
        }
    })
}




