<%--
  Created by IntelliJ IDEA.
  User: 海涛
  Date: 2016/4/8
  Time: 21:57
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
    <div class="box">
        <div class="box-header">
            <span class="title">找回密码</span>
        </div>

        <div id="show-alert" style="display: none">
            <div id="show-error-text" class="alert alert-error"></div>
        </div>

        <form id="form-forgetpwd" class="form-horizontal">
            <div class="control-group">
                <label class="control-label">密码:</label>
                <div class="controls">
                    <input id="password" type="password" name="password" placeholder="请输入密码">
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">确认密码:</label>
                <div class="controls">
                    <input type="password" name="repassword" placeholder="请再次输入密码">
                </div>
            </div>

            <div class="form-actions">
                <a id="a-submit" class="btn btn-primary">确定</a>
            </div>

        </form>
    </div>
    <!--box end-->
</div>
<!--container end-->
<script src="/static/js/jquery-1.12.2.min.js"></script>
<script src="/static/js/jquery.validate.js"></script>

<script>
    //验证表单
    $(function () {
        $("#form-forgetpwd").validate({
            errorClass: "text-error",
            errorEelment: "span",
            rules: {
                password: {
                    required: true,
                    rangelength: [6, 16]
                },
                repassword: {
                    required: true,
                    equalTo: "#password"
                }
            },
            messages: {
                password: {
                    required: "请输入密码",
                    rangelength: "密码长度为6~16位"
                },
                repassword: {
                    required: "请再次输入密码",
                    equalTo: "两次密码输入不同"
                }
            },

            submitHandler: function (form) {
                $.ajax({
                    url: "/forgetcallback.do",
                    type: "post",
                    data: $(form).serialize(),
                    success: function (json) {
                        if (json.status == 'error') {
                            //设置密码错误
                            $("#show-alert").show();
                            $("#show-error-text").text(json.errorMessage);

                        } else {
                            //设置密码成功
                            $("#div-container1").hide();
                            window.location.href = "/login.do?code=7001"

                        }
                    },
                    error: function () {
                        alert("服务器错误，请稍后再试");
                    }
                });
            }
        })
    });


    //提交表单
    $("#a-submit").click(function () {
        $("#form-forgetpwd").submit();
    });

</script>

</body>
</html>