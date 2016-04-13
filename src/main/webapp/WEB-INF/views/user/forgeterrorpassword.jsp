<%--
  Created by IntelliJ IDEA.
  User: 海涛
  Date: 2016/4/8
  Time: 23:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>

<%@include file="../publicdate.jsp" %>
<!--header-bar end-->
<div class="container" id="div-container1">
    <div class="box" >
        <div class="box-header">
            <span class="title">找回密码</span>
        </div>

        <div id="show-alert">
            <div id="show-error-text" class="alert alert-error">
                ${requestScope.errorMessage}
            </div>
        </div>

        <div class="box-padding"></div>

    </div>
    <!--box end-->
</div>
<!--container end-->
<script src="/static/js/jquery-1.12.2.min.js"></script>
<script src="/static/js/jquery.validate.js"></script>

</body>
</html>