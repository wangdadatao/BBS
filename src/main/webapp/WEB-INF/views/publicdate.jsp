<%--
  Created by IntelliJ IDEA.
  User: 海涛
  Date: 2016/4/7
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="header-bar">
    <div class="container">
        <a href="/index.do" class="brand">
            <i class="fa fa-reddit-alien"></i>
        </a>
        <ul class="unstyled inline pull-right">
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <li>
                        <a href="#">
                            <img src="http://7xsaoe.com1.z0.glb.clouddn.com/${sessionScope.user.headimg}?imageView2/1/w/20/h/20"
                                 class="img-circle" alt="">
                        </a>
                    </li>
                    <li>
                        <a href=""><i class="fa fa-plus"></i></a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-bell"></i></a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-cog"></i></a>
                    </li>
                    <li>
                        <a href="/loginout.do"><i class="fa fa-sign-out"></i></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="/login.do">登录</a> &nbsp;&nbsp;
                        <a href="/reg.do">注册</a>
                    </li>
                </c:otherwise>
            </c:choose>

        </ul>
    </div>
</div>
