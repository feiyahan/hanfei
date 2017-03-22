<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <#--移动设备优先-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>后台管理-首页</title>
    <#-- 资源引用 -->
    <#include "/admin/resource.ftl">

</head>
<body class="theme-blue">
<!-- Demo page code -->

<script type="text/javascript">
    $(function () {
        var match = document.cookie.match(new RegExp('color=([^;]+)'));
        if (match) var color = match[1];
        if (color) {
            $('body').removeClass(function (index, css) {
                return (css.match(/\btheme-\S+/g) || []).join(' ');
            });
            $('body').addClass('theme-' + color);
        }

        $('[data-popover="true"]').popover({html: true});

    });
</script>

<#-- 引入顶部导航 -->
<#include "/admin/navbar.ftl">

<#-- 引入管理后台 侧边栏 -->
<#include "/admin/sidebar.ftl">
<#-- 内容栏 -->
<div class="content">
    <div class="header">
        <div class="stats">
            <p class="stat"><span class="label label-info">5</span> Tickets</p>
            <p class="stat"><span class="label label-success">27</span> Tasks</p>
            <p class="stat"><span class="label label-danger">15</span> Overdue</p>
        </div>

        <h1 class="page-title">Dashboard</h1>
        <ul class="breadcrumb">
            <li><a href="index.html">Home</a></li>
            <li class="active">Dashboard</li>
        </ul>

    </div>
    <div class="main-content">

        <div class="row">
            <div class="col-sm-6 col-md-6">
                <div class="panel panel-default">
                    <div class="panel-heading no-collapse">Not Collapsible<span class="label label-warning">+10</span>
                    </div>
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Username</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Mark</td>
                            <td>Tompson</td>
                            <td>the_mark7</td>
                        </tr>
                        <tr>
                            <td>Ashley</td>
                            <td>Jacobs</td>
                            <td>ash11927</td>
                        </tr>
                        <tr>
                            <td>Audrey</td>
                            <td>Ann</td>
                            <td>audann84</td>
                        </tr>
                        <tr>
                            <td>John</td>
                            <td>Robinson</td>
                            <td>jr5527</td>
                        </tr>
                        <tr>
                            <td>Aaron</td>
                            <td>Butler</td>
                            <td>aaron_butler</td>
                        </tr>
                        <tr>
                            <td>Chris</td>
                            <td>Albert</td>
                            <td>cab79</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-sm-6 col-md-6">
                <div class="panel panel-default">
                    <a href="#widget1container" class="panel-heading" data-toggle="collapse">Collapsible </a>
                    <div id="widget1container" class="panel-body collapse in">
                        <h2>Here's a Tip</h2>
                        <p>This template was developed with <a href="http://middlemanapp.com/"
                                                               target="_blank">Middleman</a> and includes .erb layouts
                            and views.</p>
                        <p>All of the views you see here (sign in, sign up, users, etc) are already split up so you
                            don't have to waste your time doing it yourself!</p>
                        <p>The layout.erb file includes the header, footer, and side navigation and all of the views are
                            broken out into their own files.</p>
                        <p>If you aren't using Ruby, there is also a set of plain HTML files for each page, just like
                            you would expect.</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-6 col-md-6">
                <div class="panel panel-default">
                    <div class="panel-heading no-collapse">
                <span class="panel-icon pull-right">
                    <a href="#" class="demo-cancel-click" rel="tooltip" title="Click to refresh"><i
                            class="fa fa-refresh"></i></a>
                </span>

                        Needed to Close
                    </div>
                    <table class="table list">
                        <tbody>
                        <tr>
                            <td>
                                <a href="#"><p class="title">Care Hospital</p></a>
                                <p class="info">Sales Rating: 86%</p>
                            </td>
                            <td>
                                <p>Date: 7/19/2012</p>
                                <a href="#">View Transaction</a>
                            </td>
                            <td>
                                <p class="text-danger h3 pull-right" style="margin-top: 12px;">$20,500</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href="#"><p class="title">Custom Eyesight</p></a>
                                <p class="info">Sales Rating: 58%</p>
                            </td>
                            <td>
                                <p>Date: 7/19/2012</p>
                                <a href="#">View Transaction</a>
                            </td>
                            <td>
                                <p class="text-danger h3 pull-right" style="margin-top: 12px;">$12,600</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href="#"><p class="title">Clear Dental</p></a>
                                <p class="info">Sales Rating: 76%</p>
                            </td>
                            <td>
                                <p>Date: 7/19/2012</p>
                                <a href="#">View Transaction</a>
                            </td>
                            <td>
                                <p class="text-danger h3 pull-right" style="margin-top: 12px;">$2,500</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href="#"><p class="title">Safe Insurance</p></a>
                                <p class="info">Sales Rating: 82%</p>
                            </td>
                            <td>
                                <p>Date: 7/19/2012</p>
                                <a href="#">View Transaction</a>
                            </td>
                            <td>
                                <p class="text-danger h3 pull-right" style="margin-top: 12px;">$22,400</p>
                            </td>
                        </tr>

                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-sm-6 col-md-6">
                <div class="panel panel-default">
                    <a href="#widget2container" class="panel-heading" data-toggle="collapse">Collapsible </a>
                    <div id="widget2container" class="panel-body collapse in">
                        <h2>Built with Less</h2>
                        <p>The CSS is built with Less. There is a compiled version included if you prefer plain CSS.</p>
                        <p>Fava bean jícama seakale beetroot courgette shallot amaranth pea garbanzo carrot radicchio
                            peanut leek pea sprouts arugula brussels sprout green bean. Spring onion broccoli chicory
                            shallot winter purslane pumpkin gumbo cabbage squash beet greens lettuce celery. Gram
                            zucchini swiss chard mustard burdock radish brussels sprout groundnut. Asparagus horseradish
                            beet greens broccoli brussels.</p>
                        <p><a class="btn btn-primary">Learn more »</a></p>
                    </div>
                </div>
            </div>
        </div>

        <#-- 导入footer -->
        <#include "/admin/footer.ftl"/>
    </div>
</div>

<script type="text/javascript">
    $("[rel=tooltip]").tooltip();
    $(function () {
        $('.demo-cancel-click').click(function () {
            return false;
        });
    });
</script>
</body>
</html>