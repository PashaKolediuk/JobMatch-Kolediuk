$('[data-toggle=confirmation]').confirmation({
    rootSelector: '[data-toggle=confirmation]'
    // other options
});

$(document).ready(function () {
    var language = document.getElementsByTagName('html')[0].getAttribute('lang');
    var buttonName;
    if (language == "en") {
        buttonName = " Back to Top";
    } else {
        buttonName = " Наверх";
    }
    $('body').append('<div id="toTop" class="btn btn-info"><span class="glyphicon glyphicon-chevron-up"></span>' + buttonName + '</div>');
    $(window).scroll(function () {
        if ($(this).scrollTop() != 0) {
            $('#toTop').fadeIn();
        } else {
            $('#toTop').fadeOut();
        }
    });
    $('#toTop').click(function () {
        $("html, body").animate({scrollTop: 0}, 600);
        return false;
    });
});


$(document).ready(function () {
    $(".dropdown").hover(
        function () {
            $('.dropdown-menu', this).not('.in .dropdown-menu').stop(true, true).slideDown("fast");
            $(this).toggleClass('open');
        },
        function () {
            $('.dropdown-menu', this).not('.in .dropdown-menu').stop(true, true).slideUp("fast");
            $(this).toggleClass('open');
        }
    );
});


$(function () {
    $(".dropdown").hover(
        function () {
            $('.dropdown-menu', this).stop(true, true).fadeIn("fast");
            $(this).toggleClass('open');
            $('b', this).toggleClass("caret caret-up");
        },
        function () {
            $('.dropdown-menu', this).stop(true, true).fadeOut("fast");
            $(this).toggleClass('open');
            $('b', this).toggleClass("caret caret-up");
        });
});

$(document).ready(function () {
    $('.input-group input[required], .input-group textarea[required], .input-group select[required]').on('keyup change', function () {
        var $form = $(this).closest('form'),
            $group = $(this).closest('.input-group'),
            $addon = $group.find('.input-group-addon'),
            $icon = $addon.find('span'),
            state = false;

        if (!$group.data('validate')) {
            state = $(this).val() ? true : false;
        } else if ($group.data('validate') == "name") {
            state = /^[a-zA-Z][a-zA-Z\s-]{2,30}$/.test($(this).val());
        } else if ($group.data('validate') == "companyName") {
            state = /^[a-zA-Z0-9][a-zA-Z0-9\s,_-]{2,40}$/.test($(this).val());
        } else if ($group.data('validate') == "email") {
            state = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/.test($(this).val());
        } else if ($group.data('validate') == "password") {
            state = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,30}$/.test($(this).val());
        } else if ($group.data('validate') == "confirmPassword") {
            state = $(this).val() == $('#password').val() &&
                /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,30}$/.test($(this).val());
        } else if ($group.data('validate') == 'phone') {
            state = /^(\+)[0-9]{12}$/.test($(this).val());
        } else if ($group.data('validate') == "graduationYear") {
            state = !isNaN(parseFloat($(this).val())) && isFinite($(this).val()) &&
                $(this).val() >= 1960 && $(this).val() <= 2020;
        } else if ($group.data('validate') == "requiredExperience") {
            state = !isNaN(parseFloat($(this).val())) && isFinite($(this).val()) &&
                $(this).val() >= 0 && $(this).val() <= 60;
        } else if ($group.data('validate') == "salary") {
            state = !isNaN(parseFloat($(this).val())) && isFinite($(this).val()) &&
                $(this).val() >= 0 && $(this).val() <= 999999;
        } else if ($group.data('validate') == "mark") {
            state = !isNaN(parseFloat($(this).val())) && isFinite($(this).val()) &&
                $(this).val() >= 0 && $(this).val() <= 10;
        }

        if (state) {
            $addon.removeClass('danger');
            $addon.addClass('success');
            $icon.attr('class', 'glyphicon glyphicon-ok');
        } else {
            $addon.removeClass('success');
            $addon.addClass('danger');
            $icon.attr('class', 'glyphicon glyphicon-remove');
        }

        if ($form.find('.input-group-addon.danger').length == 0) {
            $form.find('[type="submit"]').prop('disabled', false);
        } else {
            $form.find('[type="submit"]').prop('disabled', true);
        }
    });

    $('.input-group input[required], .input-group textarea[required], .input-group select[required]').trigger('change');


});


$(document).ready(function () {
// Tooltip only Text
    $('.masterTooltip').hover(function () {
        // Hover over code
        var title = $(this).attr('data-original-title') == undefined ?
            $(this).attr('title') :
            $(this).attr('data-original-title');
        $(this).data('tipText', title).removeAttr('title');
        $('<p class="tooltip"></p>')
            .text(title)
            .appendTo('body')                   // appendTo
            .fadeIn('slow');
    }, function () {
        // Hover out code
        $(this).attr('title', $(this).data('tipText'));
        $('.tooltip').remove();
    }).mousemove(function (e) {
        var mousex = e.pageX + 20; //Get X coordinates
        var mousey = e.pageY + 10; //Get Y coordinates
        $('.tooltip')
            .css({top: mousey, left: mousex})
    });
});

$(document).ready(function(){

    var messageHeight = $('.info').outerHeight();
    $('.info').css('top', -messageHeight);

    $('.info').animate({top:"0"}, 500);

    $('.message').click(function(){
        $(this).animate({top: -$(this).outerHeight()}, 500);
    });

});





























