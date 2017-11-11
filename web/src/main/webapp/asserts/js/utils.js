$(document).ready(function () {

    $('.reserve-book').click(function () {
        reserveBook($(this));
    });
    $('.return-book').click(function () {
        returnBook($(this));
    });
    $('.ban-reader').click(function () {
        banReader($(this));
    });
});

function reserveBook(element) {
    var bookID = $(element).attr('id');
    $.ajax({
        type: 'get',
        url: contextUrl + '/frontController?command=reserveBook&bookID=' + bookID,
        success: function (response) {
            var str = "\"Auth required\"";

            if (response.toString() === str) {
                window.location.href = contextUrl + '/frontController?command=login';
            }
            else {
                $('#quantity').text(response);
                $('.reserve-book').attr('class', 'btn btn-secondary disabled').text(reserved);
            }
        }

    }).fail(function (data) {
        if (console && console.log) {
            console.log("Error data:", data);
        }
    });
}

function returnBook(element) {
    var bookID = $(element).attr('id');
    $.ajax({
        type: 'get',
        url: contextUrl + '/frontController?command=returnBook&bookID=' + bookID,
        success: function (response) {
            var str = "\"Auth required\"";

            if (response.toString() === str) {
                window.location.href = contextUrl + '/frontController?command=login';
            }
            else {
                $('.return-book#' + bookID).attr('class', 'btn btn-secondary disabled').text(returned);
            }
        }

    }).fail(function (data) {
        if (console && console.log) {
            console.log("Error data:", data);
        }
    });

}

function banReader(element) {
    var readerID = $(element).attr('id');
    $.ajax({
        type: 'get',
        url: contextUrl + '/frontController?command=banReader&readerID=' + readerID,
        success: function (response) {
            var str = $('.ban-reader#' + readerID).text().trim();
            if (str === ban) {
                console.log(str + ban);
                $('.ban-reader#' + readerID).removeClass('btn-danger').addClass('btn-success').text(unban);
            } else {
                console.log(str + unban);
                $('.ban-reader#' + readerID).removeClass('btn-success').addClass('btn-danger').text(ban);
            }

        }

    }).fail(function (data) {
        if (console && console.log) {
            console.log("Error data:", data);
        }
    });
}

