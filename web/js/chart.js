let _chart = 0;
let _radius = 0;

$(document).ready(updateAndDraw);

function updateRadiusValue() {
    _radius = Number($("#inputR").val());
    console.log(_radius)
}

function updateAndDraw() {
    _chart = $('#myCanvas');
    $("#inputR").change(() => {
        updateRadiusValue();
        drawChart();
        drawHistory();
    });

    updateRadiusValue();
    drawChart();


    _chart.click(e => {
        chartClick(e);
    });

    $('#checkall').mousedown(() => false);
    $('#checkpoint').mousedown(() => false);

}
function drawHistory() {

    let _chart = $("#myCanvas");
    let _size = Number(_chart.attr("width").replace("px", ""));
    let color;
    let currentR = Number(document.getElementById('inputR').value);
    let dataX = $("td.dataX");
    let dataY = $("td.dataY");
    let dataR = $("td.dataR");
    let dataRes = $("td.dataRes");

    for (let i = 0; i < dataX.length; i++) {
        let x = dataX[i];
        let y = dataY[i];
        let r = dataR[i];
        let res = dataRes[i];
        if (+r.innerHTML !== currentR) {
            color = "grey";
        } else {
            if (res.innerHTML.includes("true")) {
                color = "#28A745";
            } else {
                color = "#DC3545";
            }
        }

        const draw_x = parseFloat(x.innerHTML) * 500 / 14 + _size / 2;
        const draw_y = _size / 2 - parseFloat(y.innerHTML) * 500 / 14;

        drawGenericPoint(draw_x, draw_y, color);
    }
}

function chartClick(e) {
    const x = e.pageX - _chart.offset().left;
    const y = e.pageY - _chart.offset().top;

    console.log("x1 = ", x);
    console.log("y1 = ", y);
    console.log("e.pageX = ", e.pageX);
    console.log("e.pageY = ", e.pageY);

    const chart_x = (x - 250) * 14 / 500;
    const chart_y = (250 - y) * 14 / 500;

    console.log("x2 = ", chart_x);
    console.log("y2 = ", chart_y);

    addPoint(chart_x, chart_y);
}

