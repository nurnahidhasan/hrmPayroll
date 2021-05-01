<!DOCTYPE html>
<html lang="en">

<head>
    <style>
        /* Loading Spinner */
        .spinner{margin:0;width:70px;height:18px;margin:-35px 0 0 -9px;position:absolute;top:50%;left:50%;text-align:center}.spinner > div{width:18px;height:18px;background-color:#333;border-radius:100%;display:inline-block;-webkit-animation:bouncedelay 1.4s infinite ease-in-out;animation:bouncedelay 1.4s infinite ease-in-out;-webkit-animation-fill-mode:both;animation-fill-mode:both}.spinner .bounce1{-webkit-animation-delay:-.32s;animation-delay:-.32s}.spinner .bounce2{-webkit-animation-delay:-.16s;animation-delay:-.16s}@-webkit-keyframes bouncedelay{0%,80%,100%{-webkit-transform:scale(0.0)}40%{-webkit-transform:scale(1.0)}}@keyframes bouncedelay{0%,80%,100%{transform:scale(0.0);-webkit-transform:scale(0.0)}40%{transform:scale(1.0);-webkit-transform:scale(1.0)}}
    </style>
    <meta charset="UTF-8">
<!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
<title> .:BRAC University:. </title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- Favicons -->

<link rel="apple-touch-icon-precomposed" sizes="144x144" href="${pageContext.request.contextPath}/static/images/icons/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="${pageContext.request.contextPath}/static/images/icons/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="${pageContext.request.contextPath}/static/images/icons/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/static/images/icons/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/icons/favicon.png">



    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.css">


<!-- HELPERS -->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/helpers/animate.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/helpers/backgrounds.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/helpers/boilerplate.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/helpers/border-radius.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/helpers/grid.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/helpers/page-transitions.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/helpers/spacing.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/helpers/typography.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/helpers/utils.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/helpers/colors.css">

<!-- ELEMENTS -->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/elements/badges.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/elements/buttons.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/elements/content-box.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/elements/dashboard-box.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/elements/forms.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/elements/images.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/elements/info-box.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/elements/invoice.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/elements/loading-indicators.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/elements/menus.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/elements/panel-box.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/elements/response-messages.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/elements/responsive-tables.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/elements/ribbon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/elements/social-box.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/elements/tables.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/elements/tile-box.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/elements/timeline.css">



<!-- ICONS -->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/icons/fontawesome/fontawesome.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/icons/linecons/linecons.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/icons/spinnericon/spinnericon.css">


<!-- WIDGETS -->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/accordion-ui/accordion.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/calendar/calendar.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/carousel/carousel.css">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/charts/justgage/justgage.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/charts/morris/morris.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/charts/piegage/piegage.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/charts/xcharts/xcharts.css">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/chosen/chosen.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/colorpicker/colorpicker.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/datatable/datatable.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/datepicker/datepicker.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/datepicker-ui/datepicker.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/daterangepicker/daterangepicker.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/dialog/dialog.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/dropdown/dropdown.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/dropzone/dropzone.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/file-input/fileinput.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/input-switch/inputswitch.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/input-switch/inputswitch-alt.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/ionrangeslider/ionrangeslider.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/jcrop/jcrop.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/jgrowl-notifications/jgrowl.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/loading-bar/loadingbar.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/maps/vector-maps/vectormaps.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/markdown/markdown.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/modal/modal.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/multi-select/multiselect.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/multi-upload/fileupload.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/nestable/nestable.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/noty-notifications/noty.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/popover/popover.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/pretty-photo/prettyphoto.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/progressbar/progressbar.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/range-slider/rangeslider.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/slidebars/slidebars.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/slider-ui/slider.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/summernote-wysiwyg/summernote-wysiwyg.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/tabs-ui/tabs.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/theme-switcher/themeswitcher.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/timepicker/timepicker.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/tocify/tocify.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/tooltip/tooltip.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/touchspin/touchspin.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/uniform/uniform.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/wizard/wizard.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/widgets/xeditable/xeditable.css">

<!-- SNIPPETS -->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/snippets/chat.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/snippets/files-box.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/snippets/login-box.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/snippets/notification-box.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/snippets/progress-box.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/snippets/todo.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/snippets/user-profile.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/snippets/mobile-navigation.css">

<!-- APPLICATIONS -->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/applications/mailbox.css">

<!-- Admin theme -->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/themes/admin/layout.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/themes/admin/color-schemes/default.css">

<!-- Components theme -->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/themes/components/default.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/themes/components/border-radius.css">

<!-- Admin responsive -->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/helpers/responsive-elements.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/helpers/admin-responsive.css">

    <!-- JS Core -->

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js-core/jquery-core.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js-core/jquery-ui-core.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js-core/jquery-ui-widget.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js-core/jquery-ui-mouse.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js-core/jquery-ui-position.js"></script>
    <!--<script type="text/javascript" src="${pageContext.request.contextPath}/static/js-core/transition.js"></script>-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js-core/modernizr.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js-core/jquery-cookie.js"></script>





    <script type="text/javascript">
        $(window).load(function(){
            setTimeout(function() {
                $('#loading').fadeOut( 400, "linear" );
            }, 300);
        });
    </script>



</head>
<body>
<div id="loading">
    <div class="spinner">
        <div class="bounce1"></div>
        <div class="bounce2"></div>
        <div class="bounce3"></div>
    </div>
</div>

<style type="text/css">

    html,body {
        height: 100%;
        background: #fff;
    }

</style>

<div class="center-vertical">
    <div class="center-content row">

        <form method="POST" action="${pageContext.request.contextPath}/login" id="login-validation" class="col-md-4 col-sm-5 col-xs-11 col-lg-3 center-margin">
            <h3 class="text-center pad25B font-gray text-transform-upr font-size-23">Human Resource <span class="opacity-80">v1.0</span></h3>
            <div id="login-form" class="content-box bg-default">
                <div class="content-box-wrapper pad20A">
                    <img class="mrg25B center-margin radius-all-100 display-block" src="${pageContext.request.contextPath}/static/images/bracu_logo.png" alt="">
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon addon-inside bg-gray">
                                <i class="glyph-icon icon-envelope-o"></i>
                            </span>
                            <input type="text" name="username" class="form-control" id="exampleInputEmail1" placeholder="Enter email or pin">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon addon-inside bg-gray">
                                <i class="glyph-icon icon-unlock-alt"></i>
                            </span>
                            <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-block btn-primary">Login</button>
                    </div>
                    <div class="row">
                        <div class="checkbox-primary col-md-6" style="height: 20px;">
                            <label>
                                <input type="checkbox" id="loginCheckbox1" class="custom-checkbox">
                                Remember me
                            </label>
                        </div>
                        <div class="text-right col-md-6">
                            <a href="#" class="switch-button" switch-target="#login-forgot" switch-parent="#login-form" title="Recover password">Forgot your password?</a>
                        </div>
                    </div>
                </div>
            </div>

            <div id="login-forgot" class="content-box bg-default hide">
                <div class="content-box-wrapper pad20A">

                    <div class="form-group">
                        <label for="exampleInputEmail2">Email address:</label>
                        <div class="input-group">
                            <span class="input-group-addon addon-inside bg-gray">
                                <i class="glyph-icon icon-envelope-o"></i>
                            </span>
                            <input type="email" class="form-control" id="exampleInputEmail2" placeholder="Enter email">
                        </div>
                    </div>
                </div>
                <div class="button-pane text-center">
                    <button type="submit" class="btn btn-md btn-primary">Recover Password</button>
                    <a href="#" class="btn btn-md btn-link switch-button" switch-target="#login-form" switch-parent="#login-forgot" title="Cancel">Cancel</a>
                </div>
            </div>

        </form>

    </div>
</div>



    <!-- WIDGETS -->

<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.js"></script>

<!-- Bootstrap Dropdown -->

<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/static/widgets/dropdown/dropdown.js"></script> -->

<!-- Bootstrap Tooltip -->

<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/static/widgets/tooltip/tooltip.js"></script> -->

<!-- Bootstrap Popover -->

<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/static/widgets/popover/popover.js"></script> -->

<!-- Bootstrap Progress Bar -->

<script type="text/javascript" src="${pageContext.request.contextPath}/static/widgets/progressbar/progressbar.js"></script>

<!-- Bootstrap Buttons -->

<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/static/widgets/button/button.js"></script> -->

<!-- Bootstrap Collapse -->

<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/static/widgets/collapse/collapse.js"></script> -->

<!-- Superclick -->

<script type="text/javascript" src="${pageContext.request.contextPath}/static/widgets/superclick/superclick.js"></script>

<!-- Input switch alternate -->

<script type="text/javascript" src="${pageContext.request.contextPath}/static/widgets/input-switch/inputswitch-alt.js"></script>

<!-- Slim scroll -->

<script type="text/javascript" src="${pageContext.request.contextPath}/static/widgets/slimscroll/slimscroll.js"></script>

<!-- Slidebars -->

<script type="text/javascript" src="${pageContext.request.contextPath}/static/widgets/slidebars/slidebars.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/widgets/slidebars/slidebars-demo.js"></script>

<!-- PieGage -->

<script type="text/javascript" src="${pageContext.request.contextPath}/static/widgets/charts/piegage/piegage.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/widgets/charts/piegage/piegage-demo.js"></script>

<!-- Screenfull -->

<script type="text/javascript" src="${pageContext.request.contextPath}/static/widgets/screenfull/screenfull.js"></script>

<!-- Content box -->

<script type="text/javascript" src="${pageContext.request.contextPath}/static/widgets/content-box/contentbox.js"></script>

<!-- Overlay -->

<script type="text/javascript" src="${pageContext.request.contextPath}/static/widgets/overlay/overlay.js"></script>

<!-- Widgets init for demo -->

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js-init/widgets-init.js"></script>

<!-- Theme layout -->

<script type="text/javascript" src="${pageContext.request.contextPath}/static/themes/admin/layout.js"></script>

<!-- Theme switcher -->

<script type="text/javascript" src="${pageContext.request.contextPath}/static/widgets/theme-switcher/themeswitcher.js"></script>

</body>

<!-- Mirrored from agileui.com/demo/monarch/demo/admin-template/login-2.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 27 Aug 2017 08:18:13 GMT -->
</html>