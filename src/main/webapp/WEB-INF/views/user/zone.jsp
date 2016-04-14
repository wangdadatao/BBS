<%--
  Created by IntelliJ IDEA.
  User: 海涛
  Date: 2016/4/14
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人中心</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">

    <script src="/static/js/jquery-1.12.2.min.js"></script>
    <script src="/static/js/jquery.validate.js"></script>
    <script src="/static/js/webuploader/webuploader.min.js"></script>

</head>
<body>
<%@include file="../publicdate.jsp" %>



<script>
    var path = window.location.href;
    if (path.indexOf("zone.jsp")) {
        $("#small-logo").text("个人中心")
    }
</script>

</body>
</html>
