<#-- 引入jsp标签 -->
<#assign c=JspTaglibs["http://java.sun.com/jsp/jstl/core"]>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
hi ${username} welcome to index.ftl
<br>
<#--使用jsp标签-->
<@c.out value="${username=='freemarker'}"/>
</body>
</html>