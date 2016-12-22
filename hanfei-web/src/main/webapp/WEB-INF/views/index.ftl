<#-- 引入jsp标签 -->
<#assign c=JspTaglibs["http://java.sun.com/jsp/jstl/core"]>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
</head>
<body>
hi ${username} welcome to index.ftl
<br>
<#--使用jsp标签-->
<@c.out value="${username=='freemarker'}"/>
</body>
</html>