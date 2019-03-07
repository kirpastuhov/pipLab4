let prevValue = false;

$(document).ready(async () => {
    updateY();
});

function updateY() {
    const $y = $("#inputY");

    const check = checkY($y.val());

    if (check === prevValue) {
        return;
    }6746

    $y.toggleClass("is-invalid");
    $y.toggleClass("is-valid");

    prevValue = !prevValue;
}