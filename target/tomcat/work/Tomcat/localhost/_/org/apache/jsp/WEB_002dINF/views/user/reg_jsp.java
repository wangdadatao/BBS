/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2016-04-08 01:34:05 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class reg_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/views/user/../publicdate.jsp", Long.valueOf(1460005929305L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fchoose;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fotherwise;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fchoose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fotherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fchoose.release();
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.release();
    _005fjspx_005ftagPool_005fc_005fotherwise.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <title>注册用户</title>\r\n");
      out.write("    <link href=\"http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css\" rel=\"stylesheet\">\r\n");
      out.write("    <link href=\"http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"/static/css/style.css\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class=\"header-bar\">\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("        <a href=\"/index.do\" class=\"brand\">\r\n");
      out.write("            <i class=\"fa fa-reddit-alien\"></i>\r\n");
      out.write("        </a>\r\n");
      out.write("        <ul class=\"unstyled inline pull-right\">\r\n");
      out.write("            ");
      if (_jspx_meth_c_005fchoose_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("        </ul>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!--header-bar end-->\r\n");
      out.write("<div class=\"container\">\r\n");
      out.write("    <div class=\"box\">\r\n");
      out.write("        <div class=\"box-header\">\r\n");
      out.write("            <span class=\"title\"><i class=\"fa fa-sign-in\"></i> 注册账号</span>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <form action=\"\" id=\"form-reg\" class=\"form-horizontal\">\r\n");
      out.write("            <div class=\"control-group\">\r\n");
      out.write("                <label class=\"control-label\">账号</label>\r\n");
      out.write("                <div class=\"controls\">\r\n");
      out.write("                    <input type=\"text\" name=\"username\">\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"control-group\">\r\n");
      out.write("                <label class=\"control-label\">密码</label>\r\n");
      out.write("                <div class=\"controls\">\r\n");
      out.write("                    <input type=\"text\" name=\"password\" id=\"password\">\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"control-group\">\r\n");
      out.write("                <label class=\"control-label\">重复密码</label>\r\n");
      out.write("                <div class=\"controls\">\r\n");
      out.write("                    <input type=\"text\" name=\"repassword\">\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"control-group\">\r\n");
      out.write("                <label class=\"control-label\">电子邮件</label>\r\n");
      out.write("                <div class=\"controls\">\r\n");
      out.write("                    <input type=\"text\" name=\"email\">\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"form-actions\">\r\n");
      out.write("                <a id=\"a-submit\" href=\"javascript:;\" class=\"btn btn-primary\">注册</a>\r\n");
      out.write("                <a href=\"/login.do\">\r\n");
      out.write("                    <span id=\"span-success\" class=\"hide\">注册成功! 5秒后跳入登录页面(点击直接进入)</span>\r\n");
      out.write("                </a>\r\n");
      out.write("                <a class=\"pull-right\" href=\"/login.do\">登录</a>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("        </form>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("    <!--box end-->\r\n");
      out.write("</div>\r\n");
      out.write("<!--container end-->\r\n");
      out.write("<script src=\"/static/js/jquery-1.12.2.min.js\"></script>\r\n");
      out.write("<script src=\"/static/js/jquery.validate.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("    $(function () {\r\n");
      out.write("\r\n");
      out.write("        $(\"#form-reg\").validate({\r\n");
      out.write("            errorClass: \"text-error\",\r\n");
      out.write("            errorElement: \"span\",\r\n");
      out.write("\r\n");
      out.write("            rules: {\r\n");
      out.write("                username: {\r\n");
      out.write("                    required: true,\r\n");
      out.write("                    minlength: \"3\",\r\n");
      out.write("                    maxlength: \"15\",\r\n");
      out.write("                    remote: \"/valiusername.do\"\r\n");
      out.write("                },\r\n");
      out.write("                password: {\r\n");
      out.write("                    required: true,\r\n");
      out.write("                    rangelength: [6, 16]\r\n");
      out.write("                },\r\n");
      out.write("                repassword: {\r\n");
      out.write("                    required: true,\r\n");
      out.write("                    equalTo: \"#password\"\r\n");
      out.write("                },\r\n");
      out.write("                email: {\r\n");
      out.write("                    required: true,\r\n");
      out.write("                    email: true,\r\n");
      out.write("                    remote: \"/valiemail.do\"\r\n");
      out.write("                }\r\n");
      out.write("            },\r\n");
      out.write("\r\n");
      out.write("            messages: {\r\n");
      out.write("                username: {\r\n");
      out.write("                    required: \" 请输入账号\",\r\n");
      out.write("                    minlength: \" 账号最小长度3位\",\r\n");
      out.write("                    maxlength: \" 账号最大长度15位\",\r\n");
      out.write("                    remote: \"该账号已被使用,请重新输入\"\r\n");
      out.write("                },\r\n");
      out.write("                password: {\r\n");
      out.write("                    required: \" 请输入密码\",\r\n");
      out.write("                    rangelength: \" 密码长度应大于6位小于16位\"\r\n");
      out.write("                },\r\n");
      out.write("                repassword: {\r\n");
      out.write("                    required: \" 请再次输入密码\",\r\n");
      out.write("                    equalTo: \" 两次密码输入不同\"\r\n");
      out.write("                },\r\n");
      out.write("                email: {\r\n");
      out.write("                    required: \" 请输入邮箱\",\r\n");
      out.write("                    email: \" 请输入正确的邮箱\",\r\n");
      out.write("                    remote: \"该邮箱已绑定账号\"\r\n");
      out.write("                }\r\n");
      out.write("            },\r\n");
      out.write("\r\n");
      out.write("            submitHandler: function (form) {\r\n");
      out.write("                $.post(\"/reg.do\", $(form).serialize()).done(function (result) {\r\n");
      out.write("                    if (result.state == \"error\") {\r\n");
      out.write("                        alert(result.errorMessage);\r\n");
      out.write("                    } else {\r\n");
      out.write("                        $(\"#span-success\").show();\r\n");
      out.write("                        var time = 5;\r\n");
      out.write("                        setInterval(function () {\r\n");
      out.write("                            time--;\r\n");
      out.write("                            if (time == 0) {\r\n");
      out.write("                                window.location.href = \"/login.do\";\r\n");
      out.write("                                return;\r\n");
      out.write("                            }\r\n");
      out.write("                            $(\"#span-success\").html(\"注册成功! \" + time + \"秒后跳入登录页面(点击直接进入)\")\r\n");
      out.write("                        }, 1000)\r\n");
      out.write("                    }\r\n");
      out.write("                }).fail(function (result) {\r\n");
      out.write("                    alert(\"服务器异常,请稍后再试!\");\r\n");
      out.write("                });\r\n");
      out.write("\r\n");
      out.write("            }\r\n");
      out.write("\r\n");
      out.write("        })\r\n");
      out.write("\r\n");
      out.write("        $(\"#a-submit\").click(function () {\r\n");
      out.write("            $(\"#form-reg\").submit();\r\n");
      out.write("        })\r\n");
      out.write("\r\n");
      out.write("    })\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fchoose_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f0 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f0.setParent(null);
    int _jspx_eval_c_005fchoose_005f0 = _jspx_th_c_005fchoose_005f0.doStartTag();
    if (_jspx_eval_c_005fchoose_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                ");
        if (_jspx_meth_c_005fwhen_005f0(_jspx_th_c_005fchoose_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                ");
        if (_jspx_meth_c_005fotherwise_005f0(_jspx_th_c_005fchoose_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("            ");
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f0 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    // /WEB-INF/views/user/../publicdate.jsp(18,16) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty sessionScope.curr_user}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f0 = _jspx_th_c_005fwhen_005f0.doStartTag();
    if (_jspx_eval_c_005fwhen_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                    <li>\r\n");
        out.write("                        <a href=\"#\">\r\n");
        out.write("                            <img src=\"http://7xp5t4.com1.z0.glb.clouddn.com/Fqb8f9uDknAt2ilBoY-ipSZRMes-?imageView2/1/w/20/h/20\"\r\n");
        out.write("                                 class=\"img-circle\" alt=\"\">\r\n");
        out.write("                        </a>\r\n");
        out.write("                    </li>\r\n");
        out.write("                    <li>\r\n");
        out.write("                        <a href=\"\"><i class=\"fa fa-plus\"></i></a>\r\n");
        out.write("                    </li>\r\n");
        out.write("                    <li>\r\n");
        out.write("                        <a href=\"#\"><i class=\"fa fa-bell\"></i></a>\r\n");
        out.write("                    </li>\r\n");
        out.write("                    <li>\r\n");
        out.write("                        <a href=\"setting.html\"><i class=\"fa fa-cog\"></i></a>\r\n");
        out.write("                    </li>\r\n");
        out.write("                    <li>\r\n");
        out.write("                        <a href=\"#\"><i class=\"fa fa-sign-out\"></i></a>\r\n");
        out.write("                    </li>\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f0 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    int _jspx_eval_c_005fotherwise_005f0 = _jspx_th_c_005fotherwise_005f0.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                    <li>\r\n");
        out.write("                        <a href=\"/login.do\">登录</a> &nbsp;&nbsp;\r\n");
        out.write("                        <a href=\"/reg.do\">注册</a>\r\n");
        out.write("                    </li>\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f0);
    return false;
  }
}
