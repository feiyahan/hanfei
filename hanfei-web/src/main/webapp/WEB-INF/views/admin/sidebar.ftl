<script type="text/javascript">
    $(function () {
        var uls = $('.sidebar-nav > ul > *').clone();
        uls.addClass('visible-xs');
        $('#main-menu').append(uls.clone());
    });
</script>
<#-- 管理后台侧边栏 -->
<div class="sidebar-nav">
    <ul>
        <li><a href="#" data-target=".dashboard-menu" class="nav-header" data-toggle="collapse">
            <i class="fa fa-fw fa-dashboard"></i> 仪表盘<i class="fa fa-collapse"></i></a></li>
        <li>
            <ul class="dashboard-menu nav nav-list collapse in">
                <li><a href="/admin/index"><span class="fa fa-caret-right"></span> 首页</a></li>
            </ul>
        </li>

        <li><a href="#" data-target=".accounts-menu" class="nav-header collapsed" data-toggle="collapse"><i class="glyphicon glyphicon-briefcase"></i> 用户 <span class="label label-info">+3</span></a></li>
        <li><ul class="accounts-menu nav nav-list collapse">
            <li><a href="/admin/user/list" class=""><span class="fa fa-caret-right"></span> 所有用户</a></li>
            <li><a href="/admin/user/add"><span class="fa fa-caret-right"></span> 添加用户</a></li>
            <li ><a href="/admin/user/myself"><span class="fa fa-caret-right"></span> 我的资料</a></li>
            <#--<li ><a href="sign-up.html"><span class="fa fa-caret-right"></span> Sign Up</a></li>
            <li ><a href="reset-password.html"><span class="fa fa-caret-right"></span> Reset Password</a></li>-->
        </ul></li>

        <li><a href="#" data-target=".legal-menu" class="nav-header collapsed" data-toggle="collapse"><i class="glyphicon glyphicon-book"></i> 文章<i class="fa fa-collapse"></i></a></li>
        <li><ul class="legal-menu nav nav-list collapse">
            <li ><a href="/admin/article/list"><span class="fa fa-caret-right"></span> 所有文章</a></li>
            <li ><a href="/admin/article/add"><span class="fa fa-caret-right"></span> 写文章</a></li>
            <li ><a href="/admin/category"><span class="fa fa-caret-right"></span> 分类目录</a></li>
            <li ><a href="/admin/tag"><span class="fa fa-caret-right"></span> 标签</a></li>
        </ul></li>


        <li><a href="help.html" class="nav-header"><i class="fa fa-fw fa-question-circle"></i> Help</a></li>
        <li><a href="faq.html" class="nav-header"><i class="fa fa-fw fa-comment"></i> Faq</a></li>
        <li><a href="http://www.feiyahan.com" class="nav-header" target="blank"><i class="fa fa-fw fa-heart"></i> 关于Feiyahan</a></li>
    </ul>
</div>