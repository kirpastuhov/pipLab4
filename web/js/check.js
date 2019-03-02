$(document).ready(() => {
    $("#checkpoint").unbind('click').click(() => {
        console.log("check point button was pressed");
        submit();
    });
});
function submit() {
    const x = Number($("#inputX").val());
    const y = Number($("#inputY").val());
    console.log(x);
    console.log(y);
    if (!checkFields(y)) return;

    addPoint(x, y);
}
async function addPoint(chart_x, chart_y) {
    const result = await sendRequest(chart_x, chart_y);
    console.log(result);
    const draw_x = chart_x * 500 / 14 + 250;
    const draw_y = 250 - chart_y * 500 / 14;
    console.log(result);
    // drawGenericPoint(draw_x, draw_y, '#28A745');
}
function sendHitData(X, Y, R) {
    let json = (String(JSON.stringify({
        X: Number(X),
        Y: Number(Y),
        R: Number(R),
    })));
    var res;
    $.ajax({
        url: 'http://localhost:8080/Lab_4_war_exploded/web/hitdata/add',
        type: "post",
        contentType: "application/json",
        success: function (response) {
            // console.log(response.x);
            // console.log(response.y);
            // console.log(response.r);
            // console.log(response.isPointInArea);
            // res = response.isPointInArea;

            addTableRow();
            const draw_x = response.x * 500 / 14 + 250;
            const draw_y = 250 - response.y * 500 / 14;
            if (response.isPointInArea) {
                color = "#28A745";
            } else {
                color = "#DC3545";
            }
            drawGenericPoint(draw_x, draw_y, color);
            return res
        },
        data: {
            content:  json
        }
    });

}
async function sendRequest(chart_x, chart_y) {
    const chart_r = Number($("#inputR").val());
    var a =sendHitData(chart_x, chart_y, chart_r);
    console.log(a);
}