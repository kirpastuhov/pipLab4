function drawGenericPoint(draw_x, draw_y, fillStyle) {
    let _chart = $("#myCanvas");
    _chart.drawEllipse({
        fillStyle: fillStyle,
        x: draw_x, y: draw_y,
        width: 5, height: 5
    });
}