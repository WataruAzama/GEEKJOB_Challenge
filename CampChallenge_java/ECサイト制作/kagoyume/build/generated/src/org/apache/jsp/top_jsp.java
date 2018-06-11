package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import kagoyume.JumsHelper;
import javax.servlet.http.HttpSession;

public final class top_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

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
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");

JumsHelper jh = JumsHelper.getInstance();
HttpSession hs = request.getSession();
String errorStr = "";
if ((String)request.getAttribute("errorStr") != null) {
    errorStr = (String)request.getAttribute("errorStr");
}

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>かごゆめ</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        無課金で買い物した気分になりましょう。<br>\n");
      out.write("        <form action=\"Search\" method=\"GET\">\n");
      out.write("            <input type=\"text\" name=\"retrieval\" value=\"\">\n");
      out.write("            <input type=\"submit\" name=\"btnsubmit\" value=\"検索\">\n");
      out.write("        </form>\n");
      out.write("        ");
if (!errorStr.equals("")) {
      out.write("\n");
      out.write("        ");
      out.print(errorStr);
      out.write("\n");
      out.write("        ");
}
      out.write("\n");
      out.write("    </body>\n");
      out.write("    ");
if ((String)hs.getAttribute("logPass") == null) {
      out.write("\n");
      out.write("    ");
      out.print(jh.loginPage("top.jsp"));
      out.write("\n");
      out.write("    ");
}else{
      out.write("\n");
      out.write("    ");
      out.print(jh.logoutPage());
      out.write("\n");
      out.write("    ");
}
      out.write("\n");
      out.write("    <a href=\"Mydata\">会員情報一覧</a>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
