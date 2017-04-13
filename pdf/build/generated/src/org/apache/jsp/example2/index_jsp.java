package org.apache.jsp.example2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import logica.convertidor;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");

logica.convertidor convertidor = new convertidor();

convertidor.convertirApedf("http://localhost:8084/pdf/example2/index.jsp");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("  <head>\n");
      out.write("    <meta charset=\"utf-8\">\n");
      out.write("    <title>Example 1</title>\n");
      out.write("    <link rel=\"stylesheet\" href=\"style.css\" media=\"all\" />\n");
      out.write("  </head>\n");
      out.write("  <body>\n");
      out.write("    <header class=\"clearfix\">\n");
      out.write("      <div id=\"logo\">\n");
      out.write("        <img src=\"logo.png\">\n");
      out.write("      </div>\n");
      out.write("      <h1>INVOICE 3-2-1</h1>\n");
      out.write("      <div id=\"company\" class=\"clearfix\">\n");
      out.write("        <div>Company Name</div>\n");
      out.write("        <div>455 Foggy Heights,<br /> AZ 85004, US</div>\n");
      out.write("        <div>(602) 519-0450</div>\n");
      out.write("        <div><a href=\"mailto:company@example.com\">company@example.com</a></div>\n");
      out.write("      </div>\n");
      out.write("      <div id=\"project\">\n");
      out.write("        <div><span>PROJECT</span> Website development</div>\n");
      out.write("        <div><span>CLIENT</span> John Doe</div>\n");
      out.write("        <div><span>ADDRESS</span> 796 Silver Harbour, TX 79273, US</div>\n");
      out.write("        <div><span>EMAIL</span> <a href=\"mailto:john@example.com\">john@example.com</a></div>\n");
      out.write("        <div><span>DATE</span> August 17, 2015</div>\n");
      out.write("        <div><span>DUE DATE</span> September 17, 2015</div>\n");
      out.write("      </div>\n");
      out.write("    </header>\n");
      out.write("    <main>\n");
      out.write("      <table>\n");
      out.write("        <thead>\n");
      out.write("          <tr>\n");
      out.write("            <th class=\"service\">SERVICE</th>\n");
      out.write("            <th class=\"desc\">DESCRIPTION</th>\n");
      out.write("            <th>PRICE</th>\n");
      out.write("            <th>QTY</th>\n");
      out.write("            <th>TOTAL</th>\n");
      out.write("          </tr>\n");
      out.write("        </thead>\n");
      out.write("        <tbody>\n");
      out.write("          <tr>\n");
      out.write("            <td class=\"service\">Design</td>\n");
      out.write("            <td class=\"desc\">Creating a recognizable design solution based on the company's existing visual identity</td>\n");
      out.write("            <td class=\"unit\">$40.00</td>\n");
      out.write("            <td class=\"qty\">26</td>\n");
      out.write("            <td class=\"total\">$1,040.00</td>\n");
      out.write("          </tr>\n");
      out.write("          <tr>\n");
      out.write("            <td class=\"service\">Development</td>\n");
      out.write("            <td class=\"desc\">Developing a Content Management System-based Website</td>\n");
      out.write("            <td class=\"unit\">$40.00</td>\n");
      out.write("            <td class=\"qty\">80</td>\n");
      out.write("            <td class=\"total\">$3,200.00</td>\n");
      out.write("          </tr>\n");
      out.write("          <tr>\n");
      out.write("            <td class=\"service\">SEO</td>\n");
      out.write("            <td class=\"desc\">Optimize the site for search engines (SEO)</td>\n");
      out.write("            <td class=\"unit\">$40.00</td>\n");
      out.write("            <td class=\"qty\">20</td>\n");
      out.write("            <td class=\"total\">$800.00</td>\n");
      out.write("          </tr>\n");
      out.write("          <tr>\n");
      out.write("            <td class=\"service\">Training</td>\n");
      out.write("            <td class=\"desc\">Initial training sessions for staff responsible for uploading web content</td>\n");
      out.write("            <td class=\"unit\">$40.00</td>\n");
      out.write("            <td class=\"qty\">4</td>\n");
      out.write("            <td class=\"total\">$160.00</td>\n");
      out.write("          </tr>\n");
      out.write("          <tr>\n");
      out.write("            <td colspan=\"4\">SUBTOTAL</td>\n");
      out.write("            <td class=\"total\">$5,200.00</td>\n");
      out.write("          </tr>\n");
      out.write("          <tr>\n");
      out.write("            <td colspan=\"4\">TAX 25%</td>\n");
      out.write("            <td class=\"total\">$1,300.00</td>\n");
      out.write("          </tr>\n");
      out.write("          <tr>\n");
      out.write("            <td colspan=\"4\" class=\"grand total\">GRAND TOTAL</td>\n");
      out.write("            <td class=\"grand total\">$6,500.00</td>\n");
      out.write("          </tr>\n");
      out.write("        </tbody>\n");
      out.write("      </table>\n");
      out.write("      <div id=\"notices\">\n");
      out.write("        <div>NOTICE:</div>\n");
      out.write("        <div class=\"notice\">A finance charge of 1.5% will be made on unpaid balances after 30 days.</div>\n");
      out.write("      </div>\n");
      out.write("    </main>\n");
      out.write("    <footer>\n");
      out.write("      Invoice was created on a computer and is valid without the signature and seal.\n");
      out.write("    </footer>\n");
      out.write("  </body>\n");
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
