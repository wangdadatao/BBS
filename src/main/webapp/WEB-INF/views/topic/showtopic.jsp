<%--
  Created by IntelliJ IDEA.
  User: 海涛
  Date: 2016/4/11
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主题页</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/js/editer/styles/simditor.css">
    <style>
        body {
            background-image: url(/static/img/bg.jpg);
        }

        .simditor .simditor-body {
            min-height: 100px;
        }
    </style>
</head>
<body>

<%@include file="../publicdate.jsp" %>

<!--header-bar end-->
<div class="container">
    <div class="box">
        <ul class="breadcrumb" style="background-color: #fff;margin-bottom: 0px;">
            <li><a href="/index.do">首页</a> <span class="divider">/</span></li>
            <li class="active">${requestScope.topic.node.type}</li>
        </ul>
        <div class="topic-head">
            <img class="img-rounded avatar"
                 src="http://7xsaoe.com1.z0.glb.clouddn.com/${requestScope.topic.user.headimg}?imageView2/1/w/60/h/60"
                 alt="">
            <h3 class="title">${requestScope.topic.title}</h3>
            <p class="topic-msg muted">作者:<a href="">${requestScope.topic.user.username}</a> ·
                <span id="timeago" title="${requestScope.topic.createtime}"></span>
            </p>
        </div>
        <div class="topic-body">
            ${requestScope.topic.text}
        </div>
        <div class="topic-toolbar">
            <c:if test="${not empty sessionScope.user}">
                <ul class="unstyled inline pull-left">
                    <c:choose>
                        <c:when test="${requestScope.favstatus == 'true'}">
                            <li><a href="javascript:;" class="a-favtopic">取消收藏</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="javascript:;" class="a-favtopic">加入收藏</a></li>
                        </c:otherwise>
                    </c:choose>

                    <li><a href="">感谢</a></li>
                    <li><a href=""></a></li>
                </ul>
            </c:if>
            <ul class="unstyled inline pull-right muted">
                <li>${requestScope.topic.viewnum} 点击</li>
                <li><span id="span-showfavnum">${requestScope.topic.favnum}</span> 收藏</li>
                <li>${requestScope.topic.likenum} 感谢</li>
            </ul>
        </div>
    </div>
    <!--box end-->

    <div class="box" style="margin-top:20px;">
        <div class="talk-item muted" style="font-size: 12px">
            <span id="span-replynum">${requestScope.topic.replynum}</span>回复 | 截止至:<span id="span-show-time"></span>
        </div>
        <div id="div-show-comments">

        </div>
    </div>


    <c:choose>
        <c:when test="${not empty sessionScope.user}">
            <div class="box" style="margin:20px 0;">
                <a name="addcomment"></a>
                <div class="talk-item muted" style="font-size: 12px"><i class="fa fa-plus"></i> 添加一条新回复</div>
                <form id="form-comment-text" style="padding: 15px;margin-bottom:0px;">
                    <textarea name="commenttext" id="editor"></textarea>
                </form>
                <div class="talk-item muted" style="text-align: right;font-size: 12px">
                    <span class="pull-left">请尽量让自己的回复能够对别人有帮助回复</span>
                    <button id="btn-sendcomment" class="btn btn-primary">发布</button>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="box">
                <div class="box" style="margin:20px 0;">
                    <div class="talk-item muted" style="font-size: 12px"><i class="fa fa-plus"></i> 添加一条新回复</div>

                    <div class="talk-item muted" style="text-align: right;font-size: 12px">
                        <h5>请你
                            <a style="color: blue"
                               href="/login.do?reback=/topic/showtopic.do?code=${requestScope.topic.id}">登录</a>
                            后才可回复</h5>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>

</div>


<!--container end-->
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="/static/js/editer/scripts/module.min.js"></script>
<script src="/static/js/editer/scripts/hotkeys.min.js"></script>
<script src="/static/js/editer/scripts/uploader.min.js"></script>
<script src="/static/js/editer/scripts/simditor.min.js"></script>
<script src="/static/js/jquery.timeago.js"></script>
<script src="/static/js/moment.min.js"></script>
<script src="/static/js/handlebars-v4.0.5.js"></script>


<script type="text/mytemplates" id="commentListTemplate">
    {{#each data}}
    <div class="talk-item">
        <a name="{{counter @index}}楼"></a>
        <table class="talk-table">
            <tr>
                <td width="50">
                    <img class="avatar" style="top:0"
                         src="http://7xsaoe.com1.z0.glb.clouddn.com/{{user.headimg}}?imageView2/1/w/40/h/40" alt="">
                </td>
                <td width="auto">
                    <a href="" style="font-size: 12px">{{user.username}}</a> |
                    <span style="font-size: 12px" class="reply timeago" title="{{createtime}}"></span>
                    <br>
                    <div style="margin:10px 0 0 20px; padding: 0 0 0 10px; border-left:1px solid #ededed">
                        {{{texts}}}
                    </div>
                    <a name="reply"></a>
                </td>
                <td width="70" align="right" style="font-size: 12px">
                    <a href="javascript:;" class="replyLink" data-count="{{counter @index}}" title="回复"><i
                            class="fa fa-reply"></i></a>&nbsp;
                    <span class="badge">{{counter @index}}</span>
                </td>
            </tr>
        </table>

    </div>
    {{/each}}
</script>


<script>
    $(function () {

        <c:if test="${not empty sessionScope.user}">
        var editor = new Simditor({
            textarea: $('#editor'),
            toolbar: false
        });
        </c:if>

        $("#timeago").timeago();
        $(".timeagoo").timeago();

        $("#span-show-time").text(moment().format("YYYY-MM-DD HH:ss:mm"));

        Handlebars.registerHelper("counter", function (index) {
            return index + 1;
        })

        //查询数据库中的所有回复 并显示到页面上
        function initComment() {
            $.ajax({
                url: "/topic/searchcomments.do",
                type: "post",
                data: {"topicid": "${requestScope.topic.id}"},
                beforeSend: function () {
                    $("#div-show-comments").text("内容正在加载中...")
                },
                success: function (json) {
                    if (json.status == "error") {
                        alert(json.errormessage);
                    } else {
                        $("#div-show-comments").html("");
                        var source = $("#commentListTemplate").html();
                        var template = Handlebars.compile(source);
                        var html = template(json);
                        $("#div-show-comments").append(html);
                    }

                    $(".timeago").timeago();
                },
                error: function () {
                    alert("数据异常,请稍后再试!")
                },
                complete: function () {
                }
            })
        }

        //提交回复内容
        $("#btn-sendcomment").click(function () {
            var texts = editor.getValue();
            if (texts.length == 0) {
                alert("请输入评论内容!");
            } else {
                sendComment();
            }
        });

        //异步提交回复
        function sendComment() {
            $.ajax({
                url: "/topic/addcomment.do",
                type: "post",
                data: {"commenttext": editor.getValue(), "topicid": "${requestScope.topic.id}"},
                beforeSend: function () {
                    $("#btn-sendcomment").text("发布中...");
                    $("#btn-sendcomment").attr("disabled", "disabled");
                },
                success: function (json) {
                    if (json.status == "error") {
                        alert(json.errorMessage);
                    } else {
                        initComment();
                        editor.setValue("");

                        var replynum = parseInt($("#span-replynum").text()) + 1;
                        $("#span-replynum").text(replynum);
                    }
                },
                error: function () {
                    alert("系统异常,请稍后再试! 如果频繁遇到此问题,请联系管理员");
                },
                complete: function () {
                    $("#btn-sendcomment").text("发布");
                    $("#btn-sendcomment").removeAttr("disabled");
                }

            })
        }

        //收藏主题
        $(".a-favtopic").click(function () {
            var This = this;
            var favStatus = "";
            favStatus = (This.innerText == "加入收藏") ? "fav" : "unfav";

            var favNum = parseInt($("#span-showfavnum").text());
            favNum = (This.innerText == "加入收藏") ? (favNum + 1) : (favNum - 1);
            $("#span-showfavnum").text(favNum);

            $.post("/topic/favtopic.do", {
                "topicid":${requestScope.topic.id},
                "favstatus": favStatus
            }).done(function (json) {
                if (json.status == "success") {
                    This.innerText = (favStatus == "fav") ? "取消收藏" : "加入收藏"
                } else {
                    $("#a-favtopic").text("收藏失败");
                    setTimeout(function () {
                        $("#a-favtopic").text("加入收藏")
                    }, 2000);
                }
            }).fail(function () {
                alert("参数错误,收藏失败,请稍后再试")
            });
        });

        //回复几楼
        $(document).delegate(".replyLink", "click", function () {

            var count = $(this).attr("data-count");
            var mgs = "<a href='#" + count + "楼'>" + count + "楼</a>&nbsp;&nbsp;"
            editor.setValue(mgs);
            editor.focus();
            window.location.href = "#addcomment";
        });


        initComment();
    });
</script>

</body>
</html>