$(function () {
    $('input[id="date:startDate"]').daterangepicker({
        locale: {
            format: 'DD/MM/YYYY'
        },
        "singleDatePicker": true,
        "startDate": moment()
    })
});

$(function () {
    $('input[id="date:endDate"]').daterangepicker({
        locale: {
            format: 'DD/MM/YYYY'
        },
        "singleDatePicker": true,
        "startDate": moment()
    })
});
