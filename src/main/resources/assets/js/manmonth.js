/**
 * Created by donghoon on 2016. 4. 2..
 */
$(function () {
    $('input[name="daterange"]').daterangepicker();
    $('#manMonthSubmit').on('click', function () {
        var duration = $('#manMonthDuration').val();
        $.ajax({
            url: 'calcManMonth',
            type: 'post',
            data: {
                duration: duration
            },
            success: function (response) {
                $("#manMonthResult").text(response);
            },
            error: function (err) {
                alert(JSON.stringify(err));
            }
        });
    });
});