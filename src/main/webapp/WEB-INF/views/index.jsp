<%--
  Created by IntelliJ IDEA.
  User: 海涛
  Date: 2016/4/7
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
    <script src="/static/js/jquery-1.12.2.min.js"></script>
    <script src="/static/js/fenye/jquery.twbsPagination.min.js"></script>


</head>
<body>
<%@ include file="publicdate.jsp" %>
<!--header-bar end-->
<div class="container">
    <div class="box">
        <div class="talk-item">
            <ul class="topic-type unstyled inline" style="margin-bottom:0px;">
                <li class="${empty param.code? 'active':''}"><a href="/index.do">全部</a></li>
                <c:forEach items="${requestScope.nodes}" var="node">
                    <li class="${param.code == node.id ? 'active':''}"><a
                            href="/index.do?code=${node.id}">${node.type}</a></li>
                </c:forEach>

            </ul>
        </div>

        <c:forEach items="${requestScope.page.items}" var="topic">
            <div class="talk-item">
                <table class="talk-table">
                    <tr>
                        <td width="50">
                            <img class="avatar"
                                 src="http://7xsaoe.com1.z0.glb.clouddn.com/${topic.user.headimg}?imageView2/1/w/40/h/40"
                                 alt="">
                        </td>
                        <td width="80">
                            <a href="">${topic.user.username}</a>
                        </td>
                        <td width="auto">
                            <a href="/topic/showtopic.do?code=${topic.id}" }>${topic.title}</a>
                        </td>
                        <td width="50" align="center">
                            <c:if test="${topic.replynum > 0}">
                                <span class="badge">${topic.replynum}</span>
                            </c:if>
                        </td>
                    </tr>
                </table>
            </div>
        </c:forEach>

        <c:if test="${requestScope.page.totalPages > 1}">
            <div class="pagination" id="pages"></div>
        </c:if>
    </div>
    <!--box end-->
</div>
<!--container end-->

<div class="footer" style="width:100%">
    <div class="container">
        Copyright © 2016 wanghaitao
    </div>
</div>
<script>
    if ("${requestScope.page.totalCount}" <= 7) {
        $(".footer").css("position","fixed");
        $(".footer").css("bottom","0")
    }


    $("#pages").twbsPagination({
        totalPages:${requestScope.page.totalPages},
        visiblePages: 5,
        first: '首页',
        last: '末页',
        prev: '上一页',
        next: '下一页',
        href: "?code=${param.code}&p={{number}}"
    });
</script>
</body>
</html>