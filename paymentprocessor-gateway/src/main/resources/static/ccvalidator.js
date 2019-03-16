$("#send-transaction-form").submit(function(event) {
    event.preventDefault();

    var $form = $( this ),s
    url = $form.attr( 'action' );

    var paymentInfo = {};
    paymentInfo.id = randomString(20);
    paymentInfo.creditCardNumber = $('#creditCardNumber').val();
    paymentInfo.amount = $('#amount').val();

    addPaymentInfoToOverview(paymentInfo);
    sendPayment(url, paymentInfo);

    addRandomPaymentToFields();
});

addRandomPaymentToFields();

function addRandomPaymentToFields() {
    $('#creditCardNumber').val(randomString(10));
    $('#amount').val((Math.random() * 1000).toFixed(2));
}

function addPaymentInfoToOverview(paymentInfo) {
    var paymentInfoHolder = $("<li/>");
    paymentInfoHolder.prop("id", "paymentinfo" + paymentInfo.id);
    paymentInfoHolder.text(paymentInfo.creditCardNumber + " payment of " + paymentInfo.amount);
    paymentInfoHolder.addClass("list-group-item");
    paymentInfoHolder.addClass("list-group-item-light");

    $("#payments").prepend(paymentInfoHolder);

    console.log($("#payments"));
}

function sendPayment(url, paymentInfo) {
    var postJSON = JSON.stringify(paymentInfo);

    $.ajax({
        contentType: 'application/json',
        type: 'POST',
        url: url,
        data: postJSON
    });
}

function randomString(length) {
    var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghiklmnopqrstuvwxyz'.split('');

    if (! length) {
        length = Math.floor(Math.random() * chars.length);
    }

    var str = '';
    for (var i = 0; i < length; i++) {
        str += chars[Math.floor(Math.random() * chars.length)];
    }
    return str;
}
