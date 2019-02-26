$(document).ready(() => {
    $("#checkall").unbind('click').click(() => {
        checkAllChart();
    });
    $("#checkpoint").unbind('click').click(() => {
        console.log("check point button was pressed");
        submit();
        console.log("Submit was executed");
    });
});

function submit() {


    const x = Number($("#inputX").val());
    const y = Number($("#inputY").val());


    console.log(x);
    console.log(y);


    if (!checkFields(y)) {
        // alert(y);
        return;
    }


    addPoint(x, y);
}

let stopped = true;

async function checkAllChart() {
    const $button = $('#checkall');

    if (!stopped) {
        stopped = true;

        $button.addClass('btn-primary');
        $button.removeClass('btn-danger');
        $button.html('Checк all graph');

        return;
    }

    $button.removeClass('btn-primary');
    $button.addClass('btn-danger');
    $button.html('Stop checking');

    const coords = Array.from(generateCoordinates())
        .sort(sortByRadius);

    stopped = false;
    for (let x of coords) {
        await addPoint(x.x, x.y);

        while (stopped) {
            await sleep(100);
        }
    }
}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

function* generateCoordinates() {
    for (let x = 6; x >= -6; x -= 0.5) {
        for (let y = 6; y >= -6; y -= 0.5) {
            yield {x, y};
        }
    }
}

function sortByRadius(x, y) {
    const radX = x.x * x.x + x.y * x.y;
    const radY = y.x * y.x + y.y * y.y;

    if (radX === radY) {
        return 0;
    }

    if (radX > radY) {
        return 1;
    }

    return -1;
}

function addColumn($tr, text) {
    $("<td/>", {
        text: text
    }).appendTo($tr);
}

async function addPoint(chart_x, chart_y) {
    const result = await sendRequest(chart_x, chart_y);

    const draw_x = chart_x * 500 / 14 + 250;
    const draw_y = 250 - chart_y * 500 / 14;

    // const draw_x = chart_x + 250;
    // const draw_y = chart_y + 250;

    drawGenericPoint(draw_x, draw_y, isInArea() ? '#28A745' : '#DC3545');

    // let _chart = $("#myCanvas");
    // console.log("drawing the point");
    // _chart.drawEllipse({
    //     fillStyle: '#28A745',
    //     x: draw_x, y: draw_y,
    //     width: 5, height: 5
    // });
    // console.log("Done");

}

async function sendRequest(chart_x, chart_y) {
    const r = Number($("#inputR").val());
    const params = `x=${chart_x}&y=${chart_y}&r=${r}`;
    const result = await $.get("?" + params, "", "json");
    // alert(chart_x);
    // alert(chart_y);
    const $tr = $("<tr/>");
    let {x, y} = getCoordinates(chart_x, chart_y);

    addColumn($tr, result.date);
    addColumn($tr, chart_x);
    addColumn($tr, chart_y);
    addColumn($tr, r);
    // addColumn($tr, result.parameters.r);
    // addColumn($tr, result.isInArea ? "In" : "Out");

    $("#results").prepend($tr);
    return result;
}


function getCoordinates(chart_x, chart_y) {
    console.log(chart_x);
    console.log(chart_y);
    return {chart_x, chart_y};
}
