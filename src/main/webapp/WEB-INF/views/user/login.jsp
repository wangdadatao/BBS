<%--
  Created by IntelliJ IDEA.
  User: 海涛
  Date: 2016/4/7
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
<%@ include file="../publicdate.jsp" %>
<!--header-bar end-->
<div class="container">
    <div class="box">
        <div class="box-header">
            <span class="title"><i class="fa fa-sign-in"></i> 登录</span>
        </div>

        <c:choose>
            <c:when test="${param.code == '9001'}">
                <div>
                    <div class="alert alert-success">
                        注册成功,请登录
                    </div>
                </div>
            </c:when>
            <c:when test="${param.code == '8001'}">
                <div>
                    <div class="alert alert-success">
                        已安全退出!
                    </div>
                </div>
            </c:when>
            <c:when test="${param.code == '7001'}">
                <div>
                    <div class="alert alert-success">
                        找回密码成功,请登录
                    </div>
                </div>
            </c:when>
        </c:choose>
        <div id="show-alert" style="display: none">
            <div class="alert alert-error">
                注册成功,请登录
            </div>
        </div>

        <form id="form-log" class="form-horizontal">
            <div class="control-group">
                <label class="control-label">账号</label>
                <div class="controls">
                    <input type="text" name="username">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">密码</label>
                <div class="controls">
                    <input type="text" name="password">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label"></label>
                <div class="controls">
                    <a href="/forgetpwd.do">忘记密码</a>
                </div>
            </div>

            <div class="form-actions">
                <a id="a-login" class="btn btn-primary">登录</a>
                <a class="pull-right" href="/reg.do">注册账号</a>
            </div>

        </form>


    </div>
    <!--box end-->
</div>
<!--container end-->

<script src="/static/js/jquery-1.12.2.min.js"></script>
<script src="/static/js/jquery.validate.js"></script>

<script>
    $(function () {

        $("#form-log").validate({
            errorClass: "text-error",
            errorElemetn: "span",
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true
                }
            },
            messages: {
                username: {
                    required: "请输入帐号"
                },
                password: {
                    required: "请输入密码"
                }
            },
            submitHandler: function (form) {
                var $a = $("#a-login");

                $.ajax({
                    url: "/login.do",
                    type: "post",
                    data: $(form).serialize(),
                    beforeSend: function () {
                        $a.text("登录中...").attr("disabled", "disabled");
                    },
                    success: function (json) {
                        if (json.state == 'error') {
                            if (json.errorMessage == "1") {
                                // 帐号或密码错误
                                $("#show-alert").show();
                                $("#show-alert .alert").text("帐号或密码错误!")
                            } else if (json.errorMessage == "2") {
                                // 参数错误
                                $("#show-alert").show();
                                $("#show-alert .alert").text("参数错误")
                            }
                        } else {
                            window.location.href = "/index.do";
                        }
                    },
                    error: function () {
                        alert("服务器错误，请稍后再试");
                    },
                    complete: function () {
                        $a.text("登录").removeAttr("disabled");
                    }
                });
            }


        });

        $("#a-login").click(function () {
            $("#form-log").submit();
        })


    })
</script>


</body>
</html>
