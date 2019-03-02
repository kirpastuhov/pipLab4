function addTableRow() {
    $("#hits_table tr").remove();
    $.ajax({
        url: 'http://localhost:8080/Lab_4_war_exploded/web/hitdata/read',
        method: "get",
        success: function (response) {
            let json = JSON.parse(response);
            if(response != null) {
                for (let i = 0; i < json.length ; i++) {
                    var tableRef = document.getElementById('hits_table').getElementsByTagName('tbody')[0];
                    // Insert a row in the table at row index 0
                    var newRow   = tableRef.insertRow(0);


                    // Insert a cell in the row at index 0
                    var numCell  = newRow.insertCell(0);
                    numCell.classList = "numCell";


                    // Append a text node to the cell
                    var newText  = document.createTextNode(i + 1);
                    numCell.appendChild(newText);

                    var xCell  = newRow.insertCell(1);
                    xCell.className = "dataX";

                    // Append a text node to the cell
                    var newText  = document.createTextNode(json[i].x);
                    xCell.appendChild(newText);

                    var yCell  = newRow.insertCell(2);
                    yCell.className = "dataY";

                    // Append a text node to the cell
                    var newText  = document.createTextNode(json[i].y);
                    yCell.appendChild(newText);

                    var rCell  = newRow.insertCell(3);
                    rCell.className = "dataR";


                    // Append a text node to the cell
                    var newText  = document.createTextNode(json[i].r);
                    rCell.appendChild(newText);

                    var areaCell  = newRow.insertCell(4);
                    areaCell.className = "dataRes";

                    // Append a text node to the cell
                    var newText  = document.createTextNode(json[i].isPointInArea);
                    areaCell.appendChild(newText);

                    var currenttimeCell  = newRow.insertCell(5);


                    // Append a text node to the cell
                    var newText  = document.createTextNode(moment.unix(Math.round(json[i].currentTime/1000)).format("DD.MM.YY, hh:mm:ss a"));
                    currenttimeCell.appendChild(newText);

                    var executionCell  = newRow.insertCell(6);


                    // Append a text node to the cell
                    var newText  = document.createTextNode(json[i].executionTime);
                    executionCell.appendChild(newText);
                    // $("#tbody").first().prepend("" +
                    //     "<tr>" +
                    //     "<td>" +
                    //         (i + 1) +
                    //     "</td>" +
                    //     "<td>" +
                    //         json[i].x +
                    //     "</td>" +
                    //     "<td>" +
                    //         json[i].y +
                    //     "</td>" +
                    //     "<td>" +
                    //         json[i].r +
                    //     "</td>" +
                    //     "<td>" +
                    //         json[i].isPointInArea +
                    //     "</td>" +
                    //     "<td>" +
                    //         moment.unix(Math.round(json[i].currentTime/1000)).format("MMMM Do YYYY, h:mm:ss a") +
                    //     "</td>" +
                    //     "<td>" +
                    //         json[i].executionTime +
                    //     "</td>" +
                    //     "</tr>" +
                    //     "");
                }
            }
        }
    })
}




