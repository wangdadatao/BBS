<%--
  Created by IntelliJ IDEA.
  User: 海涛
  Date: 2016/4/8
  Time: 13:51
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
            <div class="alert alert-error">
                注册成功,请登录
            </div>
        </div>

        <form id="form-forgetpwd" class="form-horizontal">
            <div class="control-group">
                <label class="control-label">帐号:</label>
                <div class="controls">
                    <input type="text" name="username" placeholder="请输入帐号">
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">验证码</label>
                <div class="controls">
                    <input type="text" name="captcha" placeholder="请输入验证码">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label"></label>
                <div class="controls">
                    <a id="a-change-captcha" href="javascript:;">
                        <img id="img-captcha" src="/newcaptcha.png" alt="验证码">
                    </a>
                </div>
            </div>

            <div class="form-actions">
                <a id="a-submit" class="btn btn-primary">确定</a>
                <a class="pull-right" href="/login.do">登录</a>
            </div>

        </form>
    </div>
    <!--box end-->
</div>

<div class="container hide" id="div-container2">
    <div class="box">
        <div class="box-header">
            <span class="title">找回密码</span>
        </div>
        <div class="box-padding">
            <div class="alert alert-success">
                请前往该账号绑定的邮箱中查看邮件并进行相关操作
            </div>
        </div>
    </div>
</div>

<!--container end-->
<script src="/static/js/jquery-1.12.2.min.js"></script>
<script src="/static/js/jquery.validate.js"></script>

<script>
    //更换验证码
    $("#a-change-captcha").click(function () {
        changeCaptcha();
    });

    function changeCaptcha() {
        $("#img-captcha").attr("src", "/newcaptcha.png?_=" + new Date().getTime());
    }

    //验证表单
    $(function () {
        $("#form-forgetpwd").validate({
            errorClass: "text-error",
            errorEelment: "span",
            rules: {
                username: {
                    required: true
                }
            },
            messages: {
                username: {
                    required: "请输入用户名!"
                }
            },

            submitHandler: function (form) {
                $.ajax({
                    url: "/forgetpwd.do",
                    type: "post",
                    data: $(form).serialize(),
                    success: function (json) {
                        if (json.status == 'error') {
                            if (json.errorMessage == "1") {
                                // 帐号或密码错误
                                $("#show-alert").show();
                                $("#show-alert .alert").text("你输入的帐号不存在!");
                                changeCaptcha();
                            } else if (json.errorMessage == "2") {
                                // 验证码错误
                                $("#show-alert").show();
                                $("#show-alert .alert").text("你输入的验证码错误");
                                changeCaptcha();
                            } else if (json.errorMessage == "3") {
                                //参数错误
                                $("#show-alert").show();
                                $("#show-alert .alert").text("参数错误,请重试");
                                changeCaptcha();
                            } else {
                                $("#show-alert").show();
                                $("#show-alert .alert").text("服务器正在吃饭,请稍后再试");
                                changeCaptcha();
                            }
                        } else {
                            $("#div-container1").hide("fast");
                            $("#div-container2").show();
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