<%--
  Created by IntelliJ IDEA.
  User: 海涛
  Date: 2016/4/7
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册用户</title>
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
            <span class="title"><i class="fa fa-sign-in"></i> 注册账号</span>
        </div>

        <form action="" id="form-reg" class="form-horizontal">
            <div class="control-group">
                <label class="control-label">账号</label>
                <div class="controls">
                    <input type="text" name="username" placeholder="请输入帐号">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">密码</label>
                <div class="controls">
                    <input type="password" name="password" id="password" placeholder="请输入密码">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">重复密码</label>
                <div class="controls">
                    <input type="password" name="repassword" placeholder="请再次输入密码">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">电子邮件</label>
                <div class="controls">
                    <input type="text" name="email" placeholder="请输入邮箱地址">
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
                        <img id="img-captcha" src="/newcaptcha.png" title="点击更换" alt="验证码">
                    </a>
                </div>
            </div>

            <div class="form-actions">
                <a id="a-submit" href="javascript:;" class="btn btn-primary">注册</a>
                <a href="/login.do?code=9001">
                    <span id="span-success" class="hide">注册成功! 5秒后自动跳入登录页面(点击直接进入)</span>
                </a>
                <a class="pull-right" href="/login.do">登录</a>
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

        $("#a-change-captcha").click(function () {
            changeCaptcha();
        });


        function changeCaptcha() {
            $("#img-captcha").attr("src", "/newcaptcha.png?_=" + new Date().getTime());
        }

        $("#form-reg").validate({
            errorClass: "text-error",
            errorElement: "span",

            rules: {
                username: {
                    required: true,
                    minlength: "3",
                    maxlength: "15",
                    remote: "/valiusername.do"
                },
                password: {
                    required: true,
                    rangelength: [6, 16]
                },
                repassword: {
                    required: true,
                    equalTo: "#password"
                },
                email: {
                    required: true,
                    email: true,
                    remote: "/valiemail.do"
                },
                captcha: {
                    required: true,
                    remote: "/valicaptcha.do"
                }
            },

            messages: {
                username: {
                    required: " 请输入账号",
                    minlength: " 账号最小长度3位",
                    maxlength: " 账号最大长度15位",
                    remote: " 该账号已被使用,请重新输入"
                },
                password: {
                    required: " 请输入密码",
                    rangelength: " 密码长度应大于6位小于16位"
                },
                repassword: {
                    required: " 请再次输入密码",
                    equalTo: " 两次密码输入不同"
                },
                email: {
                    required: " 请输入邮箱",
                    email: " 请输入正确的邮箱",
                    remote: " 该邮箱已绑定账号"
                },
                captcha: {
                    required: " 请输入验证码",
                    remote: " 输入的验证码不正确"
                }
            },

            submitHandler: function (form) {
                $.post("/reg.do", $(form).serialize()).done(function (result) {
                    if (result.state == "error") {
                        changeCaptcha();
                        alert(result.errorMessage);
                    } else {
                        $("#span-success").show();
                        var time = 5;
                        setInterval(function () {
                            time--;
                            if (time == 0) {
                                window.location.href = "/login.do?code=9001";
                                return;
                            }
                            $("#span-success").html("注册成功! " + time + "秒后跳入登录页面(点击直接进入)")
                        }, 1000)
                    }
                }).fail(function (result) {
                    alert("服务器异常,请稍后再试!");
                });

            }

        })

        $("#a-submit").click(function () {
            $("#form-reg").submit();
        })

    })
</script>

</body>
</html>
