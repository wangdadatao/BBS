<%--
  Created by IntelliJ IDEA.
  User: 海涛
  Date: 2016/4/9
  Time: 16:28
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

    <script src="/static/js/jquery-1.12.2.min.js"></script>
    <script src="/static/js/jquery.validate.js"></script>
    <script src="/static/js/webuploader/webuploader.min.js"></script>

</head>
<body>

<%@include file="../publicdate.jsp" %>
<!--header-bar end-->
<div class="container">
    <div class="box">
        <div class="box-header">
            <span class="title"><i class="fa fa-cog"></i> 基本设置</span>
        </div>

        <form id="form-setEmail" class="form-horizontal">
            <div class="control-group">
                <label class="control-label">账号</label>
                <div class="controls">
                    <input type="text" value="${sessionScope.user.username}" readonly>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">电子邮件</label>
                <div class="controls">
                    <input type="email" value="${sessionScope.user.email}" name="email">
                </div>
            </div>
            <div class="form-actions">
                <a id="a-set-email" class="btn btn-primary">保存</a>
                <span id="span-emailMessage" class="hide text-success">邮箱设置成功!</span>
            </div>

        </form>

    </div>
    <!--box end-->
    <div class="box">
        <div class="box-header">
            <span class="title"><i class="fa fa-key"></i> 密码设置</span>
            <span class="pull-right muted" style="font-size: 12px">如果你不打算更改密码，请留空以下区域</span>
        </div>

        <form id="form-setPassword" class="form-horizontal">
            <div class="control-group">
                <label class="control-label">当前密码:</label>
                <div class="controls">
                    <input class="show-password" type="password" name="nowpassword" placeholder="请输入当前密码">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">新密码:</label>
                <div class="controls">
                    <input type="password" class="show-password" name="newpassword" id="newpassword"
                           placeholder="请输入新密码">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">重复密码:</label>
                <div class="controls">
                    <input type="password" class="show-password" name="renewpassword" placeholder="请再次输入新密码">
                </div>
            </div>
            <div class="form-actions">
                <a id="a-set-password" class="btn btn-primary">保存</a>
                <span id="span-settingpassword" class="hide"></span>
            </div>

        </form>

    </div>
    <!--box end-->

    <div class="box">
        <div class="box-header">
            <span class="title"><i class="fa fa-user"></i> 头像设置</span>
        </div>

        <form action="" class="form-horizontal">
            <div class="control-group">
                <label class="control-label">当前头像</label>
                <div class="controls">
                    <img id="img-upload"
                         src="http://7xsaoe.com1.z0.glb.clouddn.com/${sessionScope.user.headimg}?imageView2/1/w/50/h/50"
                         class="img-circle">
                </div>
            </div>
            <hr>
            <p style="padding-left: 20px">关于头像的规则</p>
            <ul>
                <li>禁止使用任何低俗或者敏感图片作为头像</li>
                <li>如果你是男的，请不要用女人的照片作为头像，这样可能会对其他会员产生误导</li>
            </ul>
            <div class="form-actions">
                <div id="div-upload">
                    <%--<div class=".choose">选择头像</div>--%>
                    <span id="choose" style="height:30px;">选择头像</span>
                    <span class="hide" id="span-show-ifsuccess"></span>
                </div>
            </div>
        </form>

    </div>
    <!--box end-->

</div>
<!--container end-->
<script>
    $(function () {

        //提交设置邮箱的表单
        $("#a-set-email").click(function () {
            $("#from-setEmail").submit();
        });

        //设置邮箱
        $("#form-setEmail").validate({
            errorClass: "text-error",
            errorElement: "span",
            rules: {
                email: {
                    required: true,
                    email: true,
                    remote: "/valiemail.do"
                }
            },
            messages: {
                email: {
                    required: "请输入邮箱",
                    email: "请输入正确的邮箱",
                    remote: "该邮箱已被使用,请修改"
                }
            },
            submitHandler: function (form) {
                $.ajax({
                    url: "/user/settingemail.do",
                    type: "post",
                    data: $(form).serialize(),
                    success: function (json) {
                        if (json.status == 'error') {
                            alert("服务器忙,邮箱设置失败");
                        } else {
                            $("#span-emailMessage").show();
                            setTimeout(function () {
                                $("#span-emailMessage").hide();
                            }, 3000)
                        }
                    },
                    error: function () {
                        alert("数据异常，请稍后再试");
                    }
                });
            }
        });

        //提交设置密码的表单
        $("#a-set-password").click(function () {
            $("#form-setPassword").submit();
        });

        //设置密码
        $("#form-setPassword").validate({
            errorClass: "text-error",
            errorElement: "span",
            rules: {
                nowpassword: {
                    required: true
                },
                newpassword: {
                    required: true,
                    rangelength: [6, 16]
                },
                renewpassword: {
                    required: true,
                    equalTo: "#newpassword"
                }

            },
            messages: {
                oldpassword: {
                    required: "请输入当前密码"
                },
                newpassword: {
                    required: "请输入新密码",
                    rangelength: "密码长度为6~16位"
                },
                renewpassword: {
                    required: "请再次输入新密码",
                    equalTo: "两次密码输入不相同"
                }
            },

            submitHandler: function (form) {
                $.ajax({
                    url: "/user/settingpassword.do",
                    type: "post",
                    data: $(form).serialize(),
                    success: function (json) {
                        if (json.status == "error") {
                            //设置密码失败,提醒当前密码错误
                            setPasswordMessage("text-error", "  当前密码错误!");
                        } else {
                            setPasswordMessage("text-success", "  密码设置成功,3秒后返回登录页!");
                            setTimeout(function () {
                                window.location.href = "/loginout.do?code=5001"
                            }, 3000);

                        }
                    },
                    error: function (json) {
                        alert("参数错误!")
                    }
                })
            }
        });

        //展示密码设置后的消息
        function setPasswordMessage(msg1, msg2) {
            $("#span-settingpassword").show();
            $("#span-settingpassword").addClass(msg1);
            $("#span-settingpassword").text(msg2);
            $(".show-password").val("");
        }

        //设置头像
        var imgUrl = "";  //储存上传到七牛的图片的地址

        var uploader = WebUploader.create({
            swf: "/staic/js/webuploder/Uploader.swf",
            server: "http://upload.qiniu.com",
            pick: "#choose",
            fileVal: "file",
            auto: true,
            fileNumLimit: 1,

            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,png,bmp',
                mimeTypes: 'image/*'
            },
            formData: {"token": "${requestScope.token}"}
        });

        //文件上传成功时调用
        uploader.on("uploadSuccess", function (file, response) {
            uploader.removeFile(file); //单个文件上传成功后移除队列
            imgUrls = response.key;

            $.post("/user/settingheadimg.do", {"newheadimg": imgUrls}).done(function (json) {
                if (json.status == "success") {
                    var newImgurl = "http://7xsaoe.com1.z0.glb.clouddn.com/" + imgUrls;
                    $("#img-upload").attr("src", newImgurl + "?imageView2/1/w/50/h/50");
                    $("#img-showHeadImg").attr("src", newImgurl + "?imageView2/1/w/20/h/20");

                    $("#span-show-ifsuccess").show(function () {
                        $("#span-show-ifsuccess").text("头像设置成功!")
                        setTimeout(function () {
                            $("#span-show-ifsuccess").hide();
                        }, 2000);
                    })
                } else {
                    alert(json.errorMessage);
                }
            }).fail(function () {
                alert(response.errorMessage);
            })
        });

        //文件上传失败时触发
        uploader.on("uploadError", function (file, response) {
            alert("文件上传失败,亲稍后再试!")
        });

        //开始上传
        $("#begin").click(function () {
            uploader.upload();
        })

    })
</script>
</body>
</html>