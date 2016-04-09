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
    <script src="/static/js/jquery-1.12.2.min.js"></script>
    <script src="/static/js/jquery.validate.js"></script>

    <script>
        $(function () {

            $("#form-log").validate({
                errorClass: "text-error",
                errorElement: "span",
                rules: {
                    username: {
                        required: true
                    },
                    password: {
                        required: true
                    },
                    captcha: {
                        required: true,
                        remote: "/valicaptcha.do"
                    }
                },
                messages: {
                    username: {
                        required: " 请输入帐号"
                    },
                    password: {
                        required: " 请输入密码"
                    },
                    captcha: {
                        required: "请输入验证码",
                        remote: "验证码错误"
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
                                    $(".div-show-alert").hide();
                                    $("#show-alert .alert").text("帐号或密码错误!")

                                } else if (json.errorMessage == "2") {
                                    // 参数错误
                                    errorShows("请输入验证码");

                                } else if (json.errorMessage == "3") {
                                    errorShows("验证码错误")
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

            //显示错误的提示
            function errorShows(errorMessage) {
                $("#show-alert").show();
                $(".div-show-alert").hide();
                $("#show-alert .alert").text(errorMessage);
                $("#div-show-captcha").show();
                $('#input-captcha').val("");
                $("#img-captcha").attr("src", "/newcaptcha.png?_=" + new Date().getTime());
            }

            //错误码为6001时显示验证码
            function captchaShow() {
                $("#div-show-captcha").show();
                $("#img-captcha").attr("src", "/newcaptcha.png?_=" + new Date().getTime());
            }

            //点击图片更换验证码
            $("#a-change-captcha").click(function () {
                $("#input-captcha").val("");
                $("#img-captcha").attr("src", "/newcaptcha.png?_=" + new Date().getTime());
            })

        })
    </script>
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
                <div class="div-show-alert">
                    <div class="alert alert-success">
                        注册成功,请登录
                    </div>
                </div>
            </c:when>
            <c:when test="${param.code == '8001'}">
                <div class="div-show-alert">
                    <div class="alert alert-success">
                        已安全退出!
                    </div>
                </div>
            </c:when>
            <c:when test="${param.code == '7001'}">
                <div class="div-show-alert">
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
                    <input type="text" name="username" placeholder="请输入密码">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">密码</label>
                <div class="controls">
                    <input type="text" name="password" placeholder="请输入密码">
                </div>
            </div>

            <div id="div-show-captcha" class="hide">
                <div class="control-group">
                    <label class="control-label">验证码</label>
                    <div class="controls">
                        <input id="input-captcha" type="text" name="captcha" placeholder="请输入验证码">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label"></label>
                    <div class="controls">
                        <a id="a-change-captcha" href="javascript:;">
                            <img id="img-captcha" src="" alt="验证码">
                        </a>
                    </div>
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
<c:choose>
    <c:when test="${param.code == '6001'}">
        <script>
            $("#div-show-captcha").show();
            $("#img-captcha").attr("src", "/newcaptcha.png?_=" + new Date().getTime());
        </script>
    </c:when>
</c:choose>

</body>
</html>
