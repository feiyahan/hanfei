<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <#--移动设备优先-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>登录</title>
    <link href="/resource/favicon.ico" rel="shortcut icon">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/bootstrap/font-awesome/css/font-awesome.css">
    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
<#--<link rel="stylesheet" href="/resource/bootstrap/css/bootstrap-theme.min.css">-->

    <link rel="stylesheet" href="/resource/stylesheets/theme.css">
    <link rel="stylesheet" href="/resource/stylesheets/premium.css">

</head>
<body class="theme-blue">
<!-- Demo page code -->

<script type="text/javascript">
    $(function () {
        var match = document.cookie.match(new RegExp('color=([^;]+)'));
        if (match) var color = match[1];
        if (color) {
            $('body').removeClass(function (index, css) {
                return (css.match(/\btheme-\S+/g) || []).join(' ')
            })
            $('body').addClass('theme-' + color);
        }

        $('[data-popover="true"]').popover({html: true});

    });
</script>
<style type="text/css">
    #line-chart {
        height: 300px;
        width: 800px;
        margin: 0px auto;
        margin-top: 1em;
    }

    .navbar-default .navbar-brand, .navbar-default .navbar-brand:hover {
        color: #fff;
    }
</style>

<script type="text/javascript">
    $(function () {
        var uls = $('.sidebar-nav > ul > *').clone();
        uls.addClass('visible-xs');
        $('#main-menu').append(uls.clone());
    });
</script>
<div class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <a class="" href="index.html"><span class="navbar-brand"><span class="fa fa-paper-plane"></span> Feiyahan</span></a>
    </div>
    <div class="navbar-collapse collapse" style="height: 1px;"></div>
</div>
<div class="dialog">
    <div class="panel panel-default">
        <p class="panel-heading no-collapse">Sign In</p>
        <div class="panel-body">
            <form method="post" role="form">
                <div class="form-group">
                    <label>Username</label>
                    <input type="text" class="form-control span12" name="username" placeholder="Username" value="hanfei">
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" class="form-controlspan12 form-control" name="password" placeholder="Password" value="123456">
                </div>
                <input type="submit" class="btn btn-primary pull-right" value="Sign in"/>
                <label class="remember-me"><input type="checkbox" name="rememberMe" checked="checked"> Remember me</label>
                <div class="clearfix"></div>
            </form>
        </div>
    </div>
    <p class="pull-right" style=""><a href="http://www.feiyahan.com" target="blank" style="font-size: .75em; margin-top: .25em;">Design by Portnine</a></p>
    <p><a href="reset-password.html">Forgot my password?</a></p>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="/resource/jquery/jquery-1.11.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="/resource/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $("[rel=tooltip]").tooltip();
    $(function() {
        $('.demo-cancel-click').click(function(){return false;});
    });
</script>
</body>
</html>