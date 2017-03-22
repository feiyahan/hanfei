<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<#--移动设备优先-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>后台管理-所有用户</title>
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
                return (css.match(/\btheme-\S+/g) || []).join(' ')
            })
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

        <h1 class="page-title">Users</h1>
        <ul class="breadcrumb">
            <li><a href="/admin/index">Home</a></li>
            <li class="active">Users</li>
        </ul>

    </div>
    <div class="main-content">

        <div class="btn-toolbar list-toolbar">
            <a class="btn btn-primary" href="/admin/user/add" data-toggle="modal" data-target="#userModal"><i class="fa fa-plus"></i> New User</a>
            <#--<button class="btn btn-default">Import</button>
            <button class="btn btn-default">Export</button>-->
            <div class="btn-group">
            </div>
        </div>
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>#</th>
                    <th>用户名</th>
                    <th>手机号</th>
                    <th>邮箱</th>
                    <th>创建时间</th>
                    <th style="width: 3.5em;"></th>
                </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <td>${user_index+1}</td>
                    <td>${user.username}</td>
                    <td>${user.phone}</td>
                    <td>${user.email}</td>
                    <td>${user.createTime}</td>
                    <td>
                        <a href="user.html"><i class="fa fa-pencil"></i></a>
                        <a href="#myModal" role="button" data-toggle="modal"><i class="fa fa-trash-o"></i></a>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>

        <ul class="pagination">
            <li><a href="#">&laquo;</a></li>
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">&raquo;</a></li>
        </ul>

        <div class="modal small fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="myModalLabel">Delete Confirmation</h3>
                    </div>
                    <div class="modal-body">
                        <p class="error-text"><i class="fa fa-warning modal-icon"></i>Are you sure you want to delete
                            the user?<br>This cannot be undone.</p>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">Cancel</button>
                        <button class="btn btn-danger" data-dismiss="modal">Delete</button>
                    </div>
                </div>
            </div>
        </div>

        <#-- 用户 modal 模态框-->
        <div class="modal fade" id="userModal" tabindex="-1" role="dialog" aria-labelledby="userModalLabel">
            <div class="modal-dialog">
                <div class="modal-content">

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