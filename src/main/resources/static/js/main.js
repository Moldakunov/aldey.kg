(function ($) {
    "use strict";
    var iOS = /iPad|iPhone|iPod/.test(navigator.userAgent) && !window.MSStream;

    var isMobile = {
        Android: function () {
            return navigator.userAgent.match(/Android/i);
        },
        BlackBerry: function () {
            return navigator.userAgent.match(/BlackBerry/i);
        },
        iOS: function () {
            return navigator.userAgent.match(/iPhone|iPad|iPod/i);
        },
        Opera: function () {
            return navigator.userAgent.match(/Opera Mini/i);
        },
        Windows: function () {
            return navigator.userAgent.match(/IEMobile/i);
        },
        any: function () {
            return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows());
        }
    };

    function parallax() {
        $('.bg--parallax').each(function () {
            var el = $(this),
                xpos = "50%",
                windowHeight = $(window).height();
            if (isMobile.any()) {
                $(this).css('background-attachment', 'scroll');
            } else {
                $(window).scroll(function () {
                    var current = $(window).scrollTop(),
                        top = el.offset().top,
                        height = el.outerHeight();
                    if (top + height < current || top > current + windowHeight) {
                        return;
                    }
                    el.css('backgroundPosition', xpos + " " + Math.round((top - current) * 0.2) + "px");
                });
            }
        });
    }

    function backgroundImage() {
        var databackground = $('[data-background]');
        databackground.each(function () {
            if ($(this).attr('data-background')) {
                var image_path = $(this).attr('data-background');
                $(this).css({
                    'background': 'url(' + image_path + ')'
                });
            }
        });
    }

    function siteToggleAction() {
        var navSidebar = $('.navigation--sidebar'),
            filterSidebar = $('.ps-filter--sidebar');
        $('.menu-toggle-open').on('click', function (e) {
            e.preventDefault();
            $(this).toggleClass('active');
            navSidebar.toggleClass('active');
            $('.ps-site-overlay').toggleClass('active');
        });

        $('.ps-toggle--sidebar').on('click', function (e) {
            if (!$(this).hasClass('cart-link')) {
                e.preventDefault();
                var url = $(this).attr('href');
                $(this).toggleClass('active');
                $(this).siblings('a').removeClass('active');
                $(url).toggleClass('active');
                $(url).siblings('.ps-panel--sidebar').removeClass('active');
                $('.ps-site-overlay').toggleClass('active');
            }
        });

        $('#filter-sidebar').on('click', function (e) {
            e.preventDefault();
            filterSidebar.addClass('active');
            $('.ps-site-overlay').addClass('active');
        });

        $('.ps-filter--sidebar .ps-filter__header .ps-btn--close').on('click', function (e) {
            e.preventDefault();
            filterSidebar.removeClass('active');
            $('.ps-site-overlay').removeClass('active');
        });

        $('body').on("click", function (e) {
            if ($(e.target).siblings(".ps-panel--sidebar").hasClass('active')) {
                $('.ps-panel--sidebar').removeClass('active');
                $('.ps-site-overlay').removeClass('active');
            }
        });
    }

    function subMenuToggle() {
        $('.menu--mobile .menu-item-has-children > .sub-toggle').on('click', function (e) {
            e.preventDefault();
            var current = $(this).parent('.menu-item-has-children');
            $(this).toggleClass('active');
            current.siblings().find('.sub-toggle').removeClass('active');
            current.children('.sub-menu').slideToggle(350);
            current.siblings().find('.sub-menu').slideUp(350);
            if (current.hasClass('has-mega-menu')) {
                current.children('.mega-menu').slideToggle(350);
                current.siblings('.has-mega-menu').find('.mega-menu').slideUp(350);
            }

        });
        $('.menu--mobile .has-mega-menu .mega-menu__column .sub-toggle').on('click', function (e) {
            e.preventDefault();
            var current = $(this).closest('.mega-menu__column');
            $(this).toggleClass('active');
            current.siblings().find('.sub-toggle').removeClass('active');
            current.children('.mega-menu__list').slideToggle(350);
            current.siblings().find('.mega-menu__list').slideUp(350);
        });
        var listCategories = $('.ps-list--categories');
        if (listCategories.length > 0) {
            $('.ps-list--categories .menu-item-has-children > .sub-toggle').on('click', function (e) {
                e.preventDefault();
                var current = $(this).parent('.menu-item-has-children');
                $(this).toggleClass('active');
                current.siblings().find('.sub-toggle').removeClass('active');
                current.children('.sub-menu').slideToggle(350);
                current.siblings().find('.sub-menu').slideUp(350);
                if (current.hasClass('has-mega-menu')) {
                    current.children('.mega-menu').slideToggle(350);
                    current.siblings('.has-mega-menu').find('.mega-menu').slideUp(350);
                }

            });
        }
    }

    function stickyHeader() {
        var header = $('.header'),
            scrollPosition = 0,
            checkpoint = 50;
        if (header.data('sticky') === true) {
            $(window).scroll(function () {
                var currentPosition = $(this).scrollTop();
                if (currentPosition > checkpoint) {
                    header.addClass('header--sticky');
                } else {
                    header.removeClass('header--sticky');
                }
            });
        } else {
            return false;
        }
        var stickyCart = $('#cart-sticky');
        if (stickyCart.length > 0) {
            $(window).scroll(function () {
                var currentPosition = $(this).scrollTop();
                if (currentPosition > checkpoint) {
                    stickyCart.addClass('active');
                } else {
                    stickyCart.removeClass('active');
                }
            });
        }
    }

    function owlCarouselConfig() {
        var target = $('.owl-slider');
        if (target.length > 0) {
            target.each(function () {
                var el = $(this),
                    dataAuto = el.data('owl-auto'),
                    dataLoop = el.data('owl-loop'),
                    dataSpeed = el.data('owl-speed'),
                    dataGap = el.data('owl-gap'),
                    dataNav = el.data('owl-nav'),
                    dataDots = el.data('owl-dots'),
                    dataAnimateIn = (el.data('owl-animate-in')) ? el.data('owl-animate-in') : '',
                    dataAnimateOut = (el.data('owl-animate-out')) ? el.data('owl-animate-out') : '',
                    dataDefaultItem = el.data('owl-item'),
                    dataItemXS = el.data('owl-item-xs'),
                    dataItemSM = el.data('owl-item-sm'),
                    dataItemMD = el.data('owl-item-md'),
                    dataItemLG = el.data('owl-item-lg'),
                    dataItemXL = el.data('owl-item-xl'),
                    dataNavLeft = (el.data('owl-nav-left')) ? el.data('owl-nav-left') : "<i class='icon-chevron-left'></i>",
                    dataNavRight = (el.data('owl-nav-right')) ? el.data('owl-nav-right') : "<i class='icon-chevron-right'></i>",
                    duration = el.data('owl-duration'),
                    datamouseDrag = (el.data('owl-mousedrag') == 'on') ? true : false;
                if (target.children('div, span, a, img, h1, h2, h3, h4, h5, h5').length >= 2) {
                    el.owlCarousel({
                        animateIn: dataAnimateIn,
                        animateOut: dataAnimateOut,
                        margin: dataGap,
                        autoplay: dataAuto,
                        autoplayTimeout: dataSpeed,
                        autoplayHoverPause: true,
                        loop: dataLoop,
                        nav: dataNav,
                        mouseDrag: datamouseDrag,
                        touchDrag: true,
                        autoplaySpeed: duration,
                        navSpeed: duration,
                        dotsSpeed: duration,
                        dragEndSpeed: duration,
                        navText: [dataNavLeft, dataNavRight],
                        dots: dataDots,
                        items: dataDefaultItem,
                        responsive: {
                            0: {
                                items: dataItemXS
                            },
                            480: {
                                items: dataItemSM
                            },
                            768: {
                                items: dataItemMD
                            },
                            992: {
                                items: dataItemLG
                            },
                            1200: {
                                items: dataItemXL
                            },
                            1680: {
                                items: dataDefaultItem
                            }
                        }
                    });
                }

            });
        }
    }

    function masonry($selector) {
        var masonry = $($selector);
        if (masonry.length > 0) {
            if (masonry.hasClass('filter')) {
                masonry.imagesLoaded(function () {
                    masonry.isotope({
                        columnWidth: '.grid-sizer',
                        itemSelector: '.grid-item',
                        isotope: {
                            columnWidth: '.grid-sizer'
                        },
                        filter: "*"
                    });
                });
                var filters = masonry.closest('.masonry-root').find('.ps-masonry-filter > li > a');
                filters.on('click', function (e) {
                    e.preventDefault();
                    var selector = $(this).attr('href');
                    filters.find('a').removeClass('current');
                    $(this).parent('li').addClass('current');
                    $(this).parent('li').siblings('li').removeClass('current');
                    $(this).closest('.masonry-root').find('.ps-masonry').isotope({
                        itemSelector: '.grid-item',
                        isotope: {
                            columnWidth: '.grid-sizer'
                        },
                        filter: selector
                    });
                    return false;
                });
            } else {
                masonry.imagesLoaded(function () {
                    masonry.masonry({
                        columnWidth: '.grid-sizer',
                        itemSelector: '.grid-item'
                    });
                });
            }
        }
    }

    function mapConfig() {
        var map = $('#contact-map');
        if (map.length > 0) {
            map.gmap3({
                address: map.data('address'),
                zoom: map.data('zoom'),
                mapTypeId: google.maps.MapTypeId.ROADMAP,
                scrollwheel: false
            }).marker(function (map) {
                return {
                    position: map.getCenter(),
                    icon: 'img/marker.png',
                };
            }).infowindow({
                content: map.data('address')
            }).then(function (infowindow) {
                var map = this.get(0);
                var marker = this.get(1);
                marker.addListener('click', function () {
                    infowindow.open(map, marker);
                });
            });
        } else {
            return false;
        }
    }

    function slickConfig() {
        var product = $('.ps-product--detail');
        if (product.length > 0) {
            var primary = product.find('.ps-product__gallery'),
                second = product.find('.ps-product__variants'),
                vertical = product.find('.ps-product__thumbnail').data('vertical');
            primary.slick({
                slidesToShow: 1,
                slidesToScroll: 1,
                asNavFor: '.ps-product__variants',
                fade: true,
                dots: false,
                infinite: false,
                arrows: primary.data('arrow'),
                prevArrow: "<a href='#'><i class='fa fa-angle-left'></i></a>",
                nextArrow: "<a href='#'><i class='fa fa-angle-right'></i></a>",
            });
            second.slick({
                slidesToShow: second.data('item'),
                slidesToScroll: 1,
                infinite: false,
                arrows: second.data('arrow'),
                focusOnSelect: true,
                prevArrow: "<a href='#'><i class='fa fa-angle-up'></i></a>",
                nextArrow: "<a href='#'><i class='fa fa-angle-down'></i></a>",
                asNavFor: '.ps-product__gallery',
                vertical: vertical,
                responsive: [
                    {
                        breakpoint: 1200,
                        settings: {
                            arrows: second.data('arrow'),
                            slidesToShow: 4,
                            vertical: false,
                            prevArrow: "<a href='#'><i class='fa fa-angle-left'></i></a>",
                            nextArrow: "<a href='#'><i class='fa fa-angle-right'></i></a>"
                        }
                    },
                    {
                        breakpoint: 992,
                        settings: {
                            arrows: second.data('arrow'),
                            slidesToShow: 4,
                            vertical: false,
                            prevArrow: "<a href='#'><i class='fa fa-angle-left'></i></a>",
                            nextArrow: "<a href='#'><i class='fa fa-angle-right'></i></a>"
                        }
                    },
                    {
                        breakpoint: 480,
                        settings: {
                            slidesToShow: 3,
                            vertical: false,
                            prevArrow: "<a href='#'><i class='fa fa-angle-left'></i></a>",
                            nextArrow: "<a href='#'><i class='fa fa-angle-right'></i></a>"
                        }
                    },
                ]
            });
        }
    }

    function tabs() {
        $('.ps-tab-list  li > a ').on('click', function (e) {
            e.preventDefault();
            var target = $(this).attr('href');
            $(this).closest('li').siblings('li').removeClass('active');
            $(this).closest('li').addClass('active');
            $(target).addClass('active');
            $(target).siblings('.ps-tab').removeClass('active');
        });
        $('.ps-tab-list.owl-slider .owl-item a').on('click', function (e) {
            e.preventDefault();
            var target = $(this).attr('href');
            $(this).closest('.owl-item').siblings('.owl-item').removeClass('active');
            $(this).closest('.owl-item').addClass('active');
            $(target).addClass('active');
            $(target).siblings('.ps-tab').removeClass('active');
        });
    }

    function rating() {
        $('select.ps-rating').each(function () {
            var readOnly;
            if ($(this).attr('data-read-only') == 'true') {
                readOnly = true
            } else {
                readOnly = false;
            }
            $(this).barrating({
                theme: 'fontawesome-stars',
                readonly: readOnly,
                emptyValue: '0'
            });
        });
    }

    function productLightbox() {
        var product = $('.ps-product--detail');
        if (product.length > 0) {
            $('.ps-product__gallery').lightGallery({
                selector: '.item a',
                thumbnail: true,
                share: false,
                fullScreen: false,
                autoplay: false,
                autoplayControls: false,
                actualSize: false
            });
            if (product.hasClass('ps-product--sticky')) {
                $('.ps-product__thumbnail').lightGallery({
                    selector: '.item a',
                    thumbnail: true,
                    share: false,
                    fullScreen: false,
                    autoplay: false,
                    autoplayControls: false,
                    actualSize: false
                });
            }
        }
        $('.ps-gallery--image').lightGallery({
            selector: '.ps-gallery__item',
            thumbnail: true,
            share: false,
            fullScreen: false,
            autoplay: false,
            autoplayControls: false,
            actualSize: false
        });
        $('.ps-video').lightGallery({
            thumbnail: false,
            share: false,
            fullScreen: false,
            autoplay: false,
            autoplayControls: false,
            actualSize: false
        });
    }

    function backToTop() {
        var scrollPos = 0;
        var element = $('#back2top');
        $(window).scroll(function () {
            var scrollCur = $(window).scrollTop();
            if (scrollCur > scrollPos) {
                // scroll down
                if (scrollCur > 500) {
                    element.addClass('active');
                } else {
                    element.removeClass('active');
                }
            } else {
                // scroll up
                element.removeClass('active');
            }

            scrollPos = scrollCur;
        });

        element.on('click', function () {
            $('html, body').animate({
                scrollTop: '0px'
            }, 800);
        });
    }

    function filterSlider() {
        var el = $('.ps-slider');
        var min = el.siblings().find('.ps-slider__min');
        var max = el.siblings().find('.ps-slider__max');
        var defaultMinValue = el.data('default-min');
        var defaultMaxValue = el.data('default-max');
        var maxValue = el.data('max');
        var step = el.data('step');
        if (el.length > 0) {
            el.slider({
                min: 0,
                max: maxValue,
                step: step,
                range: true,
                values: [defaultMinValue, defaultMaxValue],
                slide: function (event, ui) {
                    var values = ui.values;
                    min.text('$' + values[0]);
                    max.text('$' + values[1]);
                }
            });
            var values = el.slider("option", "values");
            min.text('$' + values[0]);
            max.text('$' + values[1]);
        } else {
            // return false;
        }
    }

    function modalInit() {
        var modal = $('.ps-modal');
        if (modal.length) {
            if (modal.hasClass('active')) {
                $('body').css('overflow-y', 'hidden');
            }
        }
        modal.find('.ps-modal__close, .ps-btn--close').on('click', function (e) {
            e.preventDefault();
            $(this).closest('.ps-modal').removeClass('active');
        });
        $('.ps-modal-link').on('click', function (e) {
            e.preventDefault();
            var target = $(this).attr('href');
            $(target).addClass('active');
            $("body").css('overflow-y', 'hidden');
        });
        $('.ps-modal').on("click", function (event) {
            if (!$(event.target).closest(".ps-modal__container").length) {
                modal.removeClass('active');
                $("body").css('overflow-y', 'auto');
            }
        });
    }

    function searchInit() {
        var searchbox = $('.ps-search');
        $('.ps-search-btn').on('click', function (e) {
            e.preventDefault();
            searchbox.addClass('active');
        });
        searchbox.find('.ps-btn--close').on('click', function (e) {
            e.preventDefault();
            searchbox.removeClass('active');
        });
    }

    function countDown() {
        var time = $(".ps-countdown");
        time.each(function () {
            var el = $(this),
                value = $(this).data('time');
            var countDownDate = new Date(value).getTime();
            var timeout = setInterval(function () {
                var now = new Date().getTime(),
                    distance = countDownDate - now;
                var days = Math.floor(distance / (1000 * 60 * 60 * 24)),
                    hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)),
                    minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60)),
                    seconds = Math.floor((distance % (1000 * 60)) / 1000);
                el.find('.days').html(days);
                el.find('.hours').html(hours);
                el.find('.minutes').html(minutes);
                el.find('.seconds').html(seconds);
                if (distance < 0) {
                    clearInterval(timeout);
                    el.closest('.ps-section').hide();
                }
            }, 1000);
        });
    }

    function productFilterToggle() {
        $('.ps-filter__trigger').on('click', function (e) {
            e.preventDefault();
            var el = $(this);
            el.find('.ps-filter__icon').toggleClass('active');
            el.closest('.ps-filter').find('.ps-filter__content').slideToggle();
        });
        if ($('.ps-sidebar--home').length > 0) {
            $('.ps-sidebar--home > .ps-sidebar__header > a').on('click', function (e) {
                e.preventDefault();
                $(this).closest('.ps-sidebar--home').children('.ps-sidebar__content').slideToggle();
            })
        }
    }

    function mainSlider() {
        var homeBanner = $('.ps-carousel--animate');
        homeBanner.slick({
            autoplay: true,
            speed: 1000,
            lazyLoad: 'progressive',
            arrows: false,
            fade: true,
            dots: true,
            prevArrow: "<i class='slider-prev ba-back'></i>",
            nextArrow: "<i class='slider-next ba-next'></i>"
        });
    }

    function subscribePopup() {
        var subscribe = $('#subscribe'),
            time = subscribe.data('time');
        setTimeout(function () {
            if (subscribe.length > 0) {
                subscribe.addClass('active');
                $('body').css('overflow', 'hidden');
            }
        }, time);
        $('.ps-popup__close').on('click', function (e) {
            e.preventDefault();
            $(this).closest('.ps-popup').removeClass('active');
            $('body').css('overflow', 'auto');
        });
        //Кнопка Базар жок чтоб закрыть
        $('.button_bazar_jok').on('click', function (e) {
                    e.preventDefault();
                    $(this).closest('.ps-popup').removeClass('active');
                    $('body').css('overflow', 'auto');
                });

        $('#subscribe').on("click", function (event) {
            if (!$(event.target).closest(".ps-popup__content").length) {
                subscribe.removeClass('active');
                $("body").css('overflow-y', 'auto');
            }
        });
    }

    function stickySidebar() {
        var sticky = $('.ps-product--sticky'),
            stickySidebar, checkPoint = 992,
            windowWidth = $(window).innerWidth();
        if (sticky.length > 0) {
            stickySidebar = new StickySidebar('.ps-product__sticky .ps-product__info', {
                topSpacing: 20,
                bottomSpacing: 20,
                containerSelector: '.ps-product__sticky',
            });
            if ($('.sticky-2').length > 0) {
                var stickySidebar2 = new StickySidebar('.ps-product__sticky .sticky-2', {
                    topSpacing: 20,
                    bottomSpacing: 20,
                    containerSelector: '.ps-product__sticky',
                });
            }
            if (checkPoint > windowWidth) {
                stickySidebar.destroy();
                stickySidebar2.destroy();
            }
        } else {
            return false;
        }
    }

    function accordion() {
        var accordion = $('.ps-accordion');
        accordion.find('.ps-accordion__content').hide();
        $('.ps-accordion.active').find('.ps-accordion__content').show();
        accordion.find('.ps-accordion__header').on('click', function (e) {
            e.preventDefault();
            if ($(this).closest('.ps-accordion').hasClass('active')) {
                $(this).closest('.ps-accordion').removeClass('active');
                $(this).closest('.ps-accordion').find('.ps-accordion__content').slideUp(350);

            } else {
                $(this).closest('.ps-accordion').addClass('active');
                $(this).closest('.ps-accordion').find('.ps-accordion__content').slideDown(350);
                $(this).closest('.ps-accordion').siblings('.ps-accordion').find('.ps-accordion__content').slideUp();
            }
            $(this).closest('.ps-accordion').siblings('.ps-accordion').removeClass('active');
            $(this).closest('.ps-accordion').siblings('.ps-accordion').find('.ps-accordion__content').slideUp();
        });
    }

    function progressBar() {
        var progress = $('.ps-progress');
        progress.each(function (e) {
            var value = $(this).data('value');
            $(this).find('span').css({
                width: value + "%"
            })
        });
    }

    function customScrollbar() {
        $('.ps-custom-scrollbar').each(function () {
            var height = $(this).data('height');
            $(this).slimScroll({
                height: height + 'px',
                alwaysVisible: true,
                color: '#000000',
                size: '6px',
                railVisible: true,
            });
        })
    }

    function select2Cofig() {
        $('select.ps-select').select2({
            placeholder: $(this).data('placeholder'),
            minimumResultsForSearch: -1
        });
    }

    function carouselNavigation() {
        var prevBtn = $('.ps-carousel__prev'),
            nextBtn = $('.ps-carousel__next');
        prevBtn.on('click', function (e) {
            e.preventDefault();
            var target = $(this).attr('href');
            $(target).trigger('prev.owl.carousel', [1000]);
        });
        nextBtn.on('click', function (e) {
            e.preventDefault();
            var target = $(this).attr('href');
            $(target).trigger('next.owl.carousel', [1000]);
        });
    }

    function dateTimePicker() {
        $('.ps-datepicker').datepicker();
    }

    $(window).on('load', function () {
        $('body').addClass('loaded');
        subscribePopup();
    });

    $('#select-sorting-with-reload').change(function () {
        switch ($("#select-sorting-with-reload").val()) {
            case $("#select-sorting-default").val() :
                sortField = "";
                direction = "";
                break;
            case $("#select-sorting-date-new").val() :
                sortField = "id";
                direction = "desc";
                break;
            case $("#select-sorting-price-asc").val() :
                sortField = "price";
                direction = "asc";
                break;
            case $("#select-sorting-price-desc").val() :
                sortField = "price";
                direction = "desc";
                break;
            default :
                sortField = "";
                direction = "";
                break;
        }

        // console.log("destroying...");
        $('#pagination').twbsPagination('destroy');
        applyPagination();
    });

    /*$(document).ready(function(){
        $("button").click(function(){
            $.post("demo_test_post.asp",
                {
                    name: "Donald Duck",
                    city: "Duckburg"
                },
                function(data,status){
                    alert("Data: " + data + "\nStatus: " + status);
                });
        });
    });*/

    /*Свойства всплывающего окна*/
    let modalSuccess = document.getElementById("myModalSuccess");
    let modalFailed = document.getElementById("myModalFailed")
    let modalSigninError = document.getElementById("myModalSigninError");
// Get the <span> element that closes the modal
    let spanSuccess = document.getElementsByClassName("closeSuccess")[0];
    let spanFailed = document.getElementsByClassName("closeFailed")[0];
// When the user clicks the button, open the modal
    /*btn.onclick = function() {
        modal.style.display = "block";
    }*/
// When the user clicks on <span> (x), close the modal
    /*spanSuccess.onclick = function() {
        modalSuccess.style.display = "none";
        window.location.href = "/sign-in";
    }
    spanFailed.onclick = function() {
        modalFailed.style.display = "none";
        window.location.href = "/sign-in";
    }*/
// When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modalSuccess) {
            modalSuccess.style.display = "none";
            window.location.href = "/sign-in";
        }
    }
    window.onclick = function(event) {
        if (event.target == modalFailed) {
            modalFailed.style.display = "none";
        }
    }
    window.onclick = function(event) {
        if (event.target == modalSigninError) {
            modalSigninError.style.display = "none";
        }
    }


    function getCookie(cname) {
        var name = cname + "=";
        var decodedCookie = decodeURIComponent(document.cookie);
        var ca = decodedCookie.split(';');
        for(var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }

    function setCookie(cname,cvalue,exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays*24*60*60*1000));
        var expires = "expires=" + d.toGMTString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }

    function checkCookie() {
        let count=getCookie("countFavoriteProducts");
        if (count != "") {
            alert("Cookie " + count)
            document.getElementById("countFavoriteProducts").innerText = count;
        } else {
            alert("Cookie " + count)
            document.getElementById("countFavoriteProducts").innerText = 0;
        }
    }

    $('#signinButton').on('click', function (event) {
        event.preventDefault();
        $(document).ready(function() {
            $.ajax({
                type: 'POST',
                url: '/signin',
                data: {
                    emailSignin: document.getElementById("emailSignin").value,
                    passwordSignin: document.getElementById("passwordSignin").value,
                },

                success: function (text) {
                    /*alert('Успешно. Ответ: ' + text);*/
                    if (text==='true'){
                        alert("Все правильно ввели")
                        window.location="/account";
                    }
                    else if (text==='false'){
                        /*modalSigninError.style.display = "block";*/
                        alert("Не правильно ввели логин пароль")
                        $('#signinFormId').trigger("reset");
                    }
                },
                error: function () {
                    alert('Ошибка!');

                }
            });

        });

        });

    $('#forgotPasswordButton').on('click', function (event) {
        event.preventDefault();

        $(document).ready(function() {
            $.ajax({
                type: 'POST',
                url: '/forgotPasswordPost',
                data: {
                    email: document.getElementById("emailForgotPassword").value,
                },

                success: function () {
                    alert("Теперь проверьте свою почту")
                    window.location="/sign-in";
                },
                error: function () {
                    alert("Неправильно пошло что-то")
                    $('#forgotPasswordFormId').trigger("reset");
                }
            });
            $('#forgotPasswordFormId').trigger("reset");
        });
    });

    $('#registerButton').on('click', function (event) {
        event.preventDefault();

    $(document).ready(function() {
        $.ajax({
            type: 'POST',
            url: '/register',
            data: {
                userName: document.getElementById("username").value,
                email: document.getElementById("email").value,
                password: document.getElementById("password").value,
                phone: document.getElementById("phoneNumber").value,
            },

            success: function () {
                alert("Успешно зарегистрирован")
                /*modalSuccess.style.display = "block";*/
                window.location.href = "/sign-in";

            },
            error: function () {
                alert("Ошибка в регистрации")
                /*modalFailed.style.display = "block";*/
                $('#registerFormId').trigger("reset");
            }
        });
        $('#registerFormId').trigger("reset");
    });
    });

    $('#signOutButton').on('click', function (event) {
        event.preventDefault();

        $(document).ready(function() {
            $.ajax({
                type: 'POST',
                url: '/signOut',
                success: function () {
                    console.log('success');
                    window.location.replace("/index");
                },
                error: function () {
                    console.log('error')
                }
            });
        });
    });

    $('#addToFavoriteMainPage').on('click', function (event) {
        event.preventDefault();

        $(document).ready(function() {
            $.ajax({
                type: 'POST',
                url: '/addToFavorite',
                data: {
                    productId: document.getElementById("product-id").innerText,
                },
                success: function (count) {
                    /*$(".countFavoriteProducts").text(count);*/
                    alert(count)
                },
                error: function (text) {
                    alert(text)
                }
            });
        });

    });

    $('#addToFavoriteButton').on('click', function (event) {
        event.preventDefault();

        $(document).ready(function() {
            $.ajax({
                type: 'POST',
                url: '/addToFavorite',
                data: {
                    productId: document.getElementById("product-id").innerText,
                },
                success: function (text) {
                    if (text === "error"){
                        alert('Please, sign in your account')
                    }
                    else if (text.includes("products")){
                        alert(text)
                    }
                },
                error: function (text) {
                    alert(text)
                }
            });
        });

    });

    $('#updatePasswordButton').on('click', function (event) {
        event.preventDefault();
        $(document).ready(function() {
            $.ajax({
                type: 'POST',
                url: '/updatePassword',
                data: {
                    userId: document.getElementById("userId").innerText,
                    oldPassword : document.getElementById("oldPassword").value,
                    newPassword : document.getElementById("newPassword").value,
                },
                success: function (text) {
                    if (text === "error"){
                        alert('Error in old password')
                    }
                    else if (text === "success"){
                        alert("Success update password")
                    }
                    else if (text === "errorerror"){
                        alert("Error error")
                    }
                    location.reload()
                },
                error: function (text) {
                    alert(text)
                }
            });
        });
    });

    $('.remove-product-from-wish_list').on('click', function (e) {
        e.preventDefault();
        e.stopPropagation();

        let a = $('#removeProductFromFavorite').data('product_id_favorite');

        $(document).ready(function() {
            $.ajax({
                type: 'POST',
                url: '/addToFavorite',
                data: {
                    productId: a,
                },
                success: function (count) {
                    /*$(".countFavoriteProducts").text(count);*/
                    alert('Udaleno')
                    window.location.reload();
                },
                error: function (text) {
                    alert(text)
                }
            });
        });

    })

    function createJSONForRegister() {
        /*var obj = '{'
            +'"userName" : "Raj",'
            +'"email"  : "32",'
            +'"password" : "123",'
            +'"phone" : "789",'
            +'"storeId" : 1'
            +'}';*/

        /*var obj = {
            "userName": document.getElementById("username").value,
            "email": document.getElementById("email").value,
            "password": document.getElementById("password").value,
            "phone": document.getElementById("phoneNumber").value,
            "storeId": 1
             };*/
        /*let obj = new Object();
        obj.userName = document.getElementById("username").value;
        obj.email  = document.getElementById("email").value;
        obj.password = document.getElementById("password").value;
        obj.phone = document.getElementById("phoneNumber").value;
        obj.storeId = 1;
        let jsonString= JSON.stringify(obj);*/
        let data = {}
        data["userName"]= document.getElementById("username").value;
        data["email"]= document.getElementById("email").value;
        data["password"]= document.getElementById("password").value;
        data["phone"]= document.getElementById("phoneNumber").value;
        data["storeId"]= 1;
        return data;
    }

    function cart() {
        $('.form-group--number button').on('click', function (e) {
            var direction = $(this).attr('class');
            var parentTr = $(this).parents('tr');
            var count = $(this).parent().find('input.form-control');
            var valCount = count.val();
            if ("up" === direction) {
                valCount++;
            } else if ("down" === direction) {
                valCount--;
            }

            if (valCount < 1) {
                valCount = 1;
            } else {
                count.val(valCount);
                var price = parentTr.find('input.product-price').val();
                var total = price * valCount;
                parentTr.find('span.product-total-price').text(total);

                updateTotalCart();
                updateCartCount(valCount, parentTr.attr('data-productId'));
            }
        })

        $('.ps-table--shopping-cart .remove-product-from-cart').on('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            var parentTr = $(this).parents('tr');
            parentTr.remove();
            updateUserProductInformation("DELETE", 'countProductsClass', parentTr.attr('data-productId'));
            updateTotalCart();
            //location.reload();
        })
    }

    $('#buttonBuy').on('click', function (e) {
        e.preventDefault();
        e.stopPropagation();

        if ($('#phoneNumberClient').val() !== '') {
            var product, order = [];

            $('.ps-table--shopping-cart .product-cart-row').each(function (i) {
                var $tds = $(this).find('td'),
                    productId = $tds.eq(4).find('input.product-id').val(),
                    count = $tds.eq(3).find('input.countOfProduct').val();

                order[i] = {
                    "productId": productId,
                    "count": count
                };
            });

            product = JSON.stringify(order);

            if (JSON.stringify(product).length > 8) {
                var good = {
                    "userId": 0,
                    "nickName": $('#nickNameClient').val(),
                    "phone": $('#phoneNumberClient').val(),
                    "address": $('#addressClient').val(),
                    "ids": JSON.parse(product)
                };

                // console.log(good);

                $.ajax({
                    type: "POST",
                    url: "/cart/buy",
                    data: JSON.stringify(good),
                    beforeSend: function (request) {
                        console.log("sending order...");
                        request.setRequestHeader("Content-type", "application/json; charset=utf-8");
                        request.setRequestHeader("Accept", "application/json");
                    },
                    success: function () {
                        console.log("ok!")
                    },
                    error: function () {
                        console.log("lol");
                    },
                    dataType: 'json'
                });

                $.ajax({
                    type: "DELETE",
                    url: "/cart?product=",
                    success: function () {
                        console.log("ok all removed!")
                    },
                    error: function () {
                        console.log("lol all not removed");
                    }
                });
                $(location).attr('href', "/index");
            }
        } else {
            console.log("phoneNumber is empty");
            $('#myModalBuy').modal('show');
        }
    })

    function productActions() {
        $('.product__action').on('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            var productId = $(this).attr('data-productId');
            updateUserProductInformation("PUT", 'countProductsClass', productId);

            $('#multiplyButton').attr("disabled", true);
        })


    }

    $(document).ready(function () {
        $("#multiplyButton").click(function () {
            $('#myModal').modal('show');
        });

        $("#ps-product-rating").change(function() {
            $("#user-rating").val(document.getElementById("review-product-rating-box").selectedIndex);
        });

        $("#product-review-form").submit(function (event) {
            event.preventDefault();

            var data = {
                "text": $("#review-product-text").val(),
                "productId": document.getElementById('product-id').textContent,
                "rating": document.getElementById('review-product-rating-box').selectedIndex,
                "userName": $('#review-product-username').val()
            };

            $.ajax({
                type: "POST",
                url: '/review',
                data: JSON.stringify(data),
                beforeSend: function (request) {
                    request.setRequestHeader("Content-type", "application/json; charset=utf-8");
                    request.setRequestHeader("Accept", "application/json");
                    console.log(data);
                },
                processData: false,
                contentType: false,
                cache: false,
                timeout: 1000000
            });

            $("#review-product-button").prop("disabled", true);
            $("#product-review-form")[0].reset();
            window.location.reload();
        })
    });

    function updateUserProductInformation(type, dataAction, productId) {
        $.ajax({
            type: type,
            url: '/cart?product=' + productId,
            success: function (response) {
                var counterItemMobile = $('.' + dataAction + 'Mobile');
                var counterItem = $('.' + dataAction);
                var count = parseInt(counterItem.text());
                var countMobile = parseInt(counterItemMobile.text());
                if ("added" === response) {
                    counterItem.text(count + 1);
                    counterItemMobile.text(countMobile + 1);
                    // console.log("Вы успешно добавили товар в корзину");
                } else if ("removed" === response) {
                    counterItem.text(count - 1);
                    counterItemMobile.text(countMobile - 1);
                    // console.log("Вы успешно удалили товар из корзины");
                }
            }
        });
    }

    function updateCartCount(total, productId) {
        $.ajax({
            type: "PUT",
            url: '/cart?product=' + productId + '&count=' + total,
            /*success: function (response) {},*/
            dataType: 'json'
        });
    }

    function updateTotalCart() {
        var totalPrice = 0;
        $('.ps-table--shopping-cart .product-cart-row').each(function (e) {
            var price = $(this).find('input.product-price').val();
            var count = $(this).find('.form-group--number input.form-control').val();
            totalPrice += (price * count);
        })

        $('#cart-subtotal').text(totalPrice);
    }

    function successSearchButton() {
        $('#searchText').value === "" ? $('#searchButton').prop('disabled', true) : $('#searchButton').prop('disabled', false);
        $('#searchTextMobile').value === "" ? $('#searchButtonMobile').prop('disabled', true) : $('#searchButtonMobile').prop('disabled', false);
        $('#searchTextMobileMenu').value === "" ? $('#searchButtonMobileMenu').prop('disabled', true) : $('#searchButtonMobileMenu').prop('disabled', false);
    }


        //Нажатие кнопки "Купить быстро" на странице товара
        $('input#submitInFastBuyInput').on('click', function (event) {
                event.preventDefault();

                //Беру значение которое написал пользователь в input
                var input = $('#inputInFastBuyInput').val();
                //Если значение меньше 10 ничего не делаем
                if(input == '' || input.length<10){
                   $('#textInFastBuyInput').text('Напишите номер полностью')
                }
                //Если значение больше или ровно 10 то отправляем в Java пост запросом на адрес /fastBuy
                else if(input.length>=10){
                    $('#textInFastBuyInput').text('Подождите немного, заказ оформляется......')
                    $(document).ready(function() {
                     $.ajax({
                         type: 'POST',
                         url: '/fastBuy',
                         data: {
                             phoneNumber: document.getElementById("inputInFastBuyInput").value,
                             productId : document.getElementById('product-id').textContent,
                         },
                         success: function () {
                             $('#inputInFastBuyInput').val('');
                             $('#textInFastBuyInput').text('')
                             /*console.log("Норм");*/
                             fastBuyModal.style.display = "none";
                             thanksForFastBuyModal.style.display = "block";
                         },
                         error: function () {
                             $('#inputInFastBuyInput').val('');
                             $('#textInFastBuyInput').text('Произошла ошибка, попробуйте позже');
                         }
                     });
                 });
                 /*fastBuyModal.style.display = "none"*/
                }
            });

            //Нажатие кнопки закрыть Thanks For fast buy in product box
            $('input#submitInThanksFastBuy').on('click', function (event) {
                event.preventDefault();
                thanksForFastBuyModal.style.display = "none";
              });

            //ЗДЕСЬ НАЧИНАЕТСЯ "КУПИТЬ БЫСТРО" ГЛАВНОЙ СТРАНИЦЫ index
            /* For tag in index купить в один клик*/
             $('.product___action').on('click', function (e) {
                 e.preventDefault();
                 e.stopPropagation();
                 //var productId = $(this).attr('data-productId');
                 var productId = $(this).attr('data-productId');
                 var modal = document.getElementById("fastBuyModalInIndex");
                 modal.style.display = "block";
                 //console.log(productId);
                 $('input#submitInFastBuyInputInIndex').on('click', function (event) {
                                    event.preventDefault();

                                    var phoneNumber = document.getElementById("inputInFastBuyInputInIndex").value;
                                    var input = $('#inputInFastBuyInputInIndex').val();
                                    //console.log(productId);
                                    if(input == '' || input.length<10){
                                         $('#textInFastBuyInputInIndex').text('Напишите номер полностью')
                                      }
                                    else if(input.length>=10){
                                           $('#textInFastBuyInputInIndex').text('Подождите немного, заказ оформляется......')
                                           $(document).ready(function() {
                                            $.ajax({
                                                type: 'POST',
                                                url: '/fastBuy',
                                                data: {
                                                    phoneNumber: phoneNumber,
                                                    productId : productId,
                                                },
                                                success: function () {
                                                    $('#inputInFastBuyInputInIndex').val('');
                                                    $('#textInFastBuyInputInIndex').text('')
                                                    /*console.log("Норм");*/
                                                    fastBuyModalInIndex.style.display = "none";
                                                    thanksForFastBuyModalInIndex.style.display = "block";
                                                },
                                                error: function () {
                                                    $('#inputInFastBuyInputInIndex').val('');
                                                    $('#textInFastBuyInputInIndex').text('Произошла ошибка, попробуйте позже');
                                                }
                                            });
                                        });
                                        /*fastBuyModal.style.display = "none"*/
                                       }
                                    })
             })
             // When the user clicks anywhere outside of the modal, close it
             window.onclick = function(event) {
             var modal = document.getElementById("fastBuyModalInIndex");
               if (event.target == modal) {
                 modal.style.display = "none";
                 document.getElementById("inputInFastBuyInputInIndex").value = '';
               }
             }
             $('input#submitInThanksFastBuyInIndex').on('click', function (event) {
                             event.preventDefault();
                             thanksForFastBuyModalInIndex.style.display = "none";
                           });




    $(function () {
        backgroundImage();
        owlCarouselConfig();
        siteToggleAction();
        subMenuToggle();
        masonry('.ps-masonry');
        productFilterToggle();
        tabs();
        slickConfig();
        productLightbox();
        rating();
        backToTop();
        stickyHeader();
        filterSlider();
        mapConfig();
        modalInit();
        searchInit();
        countDown();
        mainSlider();
        parallax();
        stickySidebar();
        accordion();
        progressBar();
        customScrollbar();
        select2Cofig();
        carouselNavigation();
        dateTimePicker();
        $('[data-toggle="tooltip"]').tooltip();
        cart();
        updateTotalCart();
        productActions();
        successSearchButton();
    });

})(jQuery);

function successSigninButton() {
    if (document.getElementById("emailSignin").value === "" ||
        document.getElementById("passwordSignin").value === "")
    {
        document.getElementById('signinButton').disabled = true;
    } else {
        document.getElementById('signinButton').disabled = false;
    }
}

function successUpdatePasswordButton() {
    if (document.getElementById("oldPassword").value === "" ||
        document.getElementById("newPassword").value === "") {

        document.getElementById('updatePasswordButton').disabled = true;
    } else {
        document.getElementById('updatePasswordButton').disabled = false;
    }
}

function successRegisterButton() {
    if (document.getElementById("username").value === "" ||
        document.getElementById("email").value === "" ||
        document.getElementById("password").value === "" ||
        document.getElementById("repeatPassword").value === "") {

        document.getElementById('registerButton').disabled = true;
    } else {
        document.getElementById('registerButton').disabled = false;
    }
}

function successReviewButton() {
    if (document.getElementById("review-product-text").value === "" ||
        document.getElementById("review-product-rating-box").selectedIndex === 0 ||
        document.getElementById("review-product-username").value === "") {
        document.getElementById('textErrorForReview').innerHTML = 'Пожалуйста, заполните все поля (оценка, отзыв и имя)';
    } else {
        document.getElementById('textErrorForReview').innerHTML = 'Все правильно, нажмите на кнопку "Отправить отзыв"';
        document.getElementById('review-product-button').disabled = false;
    }
}



function checkPassword() {
    var password1 = document.getElementById("password");
    var passwordStatus = document.getElementById("passwordStatus")

    if (passwordStatus && !password1.checkValidity()) {
        passwordStatus.classList.replace("input:valid+span:after", "input:invalid+span:after");
        passwordStatus.textContent = "Заполните поле Пароль (минимум 6 символов) !";
        console.log("password1 is incorrect");
    }

    if (passwordStatus && password1.checkValidity) {
        passwordStatus.classList.replace("input:invalid+span:after", "input:valid+span:after");
        passwordStatus.textContent = '';
        console.log("password1 is ok");
    }
}

function comparePasswords() {
    var password1 = document.getElementById("password");
    var password2 = document.getElementById("repeatPassword");
    var repeatPasswordStatus = document.getElementById("repeatPasswordStatus");

    if (password1.value === password2.value) {
        repeatPasswordStatus.classList.replace("input:invalid+span:after", "input:valid+span:after");
        console.log("password2 is ok");
    }

    function showModal() {
        modal+=
            '<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">'+
                '<div class="modal-dialog">'+
                    '<div class="modal-content">'+
                        '<div class="modal-header" style="border: #0D0A0A; background: #00bbd3">'+
                            '<h4 class="modal-title" id="myModalLabel" style="color: #000000">Успешно добавлено в корзину</h4>'+
                            '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'+
                        '</div>'+
                    '</div>'+
                '</div>'+
            '</div>'

        return modal;
    }


}



