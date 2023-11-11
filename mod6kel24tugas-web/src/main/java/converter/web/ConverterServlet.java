package converter.web;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import converter.ejb.ConverterBean;

@WebServlet(name = "ConverterServlet", urlPatterns = {"/ConverterServlet"})
public class ConverterServlet extends HttpServlet {

    private ConverterBean cb = new ConverterBean();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<style>");
        out.println("body { background-color: #aeabf5; font-family: Tahoma, sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
        out.println(".container { text-align: center; background-color: #fff; padding: 20px; border-radius: 10px; }");
        out.println("form { background-color: #fff; padding: 10px; border-radius: 5px; }");
        out.println("input[type='text'] { width: 80%; padding: 10px; margin: 10px 0; border: 1px solid #ccc; border-radius: 5px; }");
        out.println("input[type='submit'] { background-color: #4a4a4d; width: 80%; color: #fff; padding: 10px 20px; border: none; border-radius: 3px; cursor: pointer; margin: 5px; }");
        out.println("</style>");
        out.println("<title>Temperature Converter</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>Temperature Converter Kel 24</h1>");
        try {
            String degree = request.getParameter("degree");
            if ((degree != null) && (degree.length() > 0)) {
                try {
                    double d = Double.parseDouble(degree);
                    if (request.getParameter("R TO F") != null) {
                        String reamurgrade = cb.rtof(d); 
                        out.println("<p>" + degree + " Reamur are " + reamurgrade + " Fahrenheit.</p>");
                    }
                    if (request.getParameter("F TO R") != null) {
                        String fahrenheit = cb.ftor(d);
                        out.println("<p>" + degree + " Fahrenheit are " + fahrenheit + " Reamur.</p>");
                    }
                } catch (NumberFormatException e) {
                    out.println("<p>Error: Please enter a valid number for the degree.</p>");
                }
            } else {
                out.println("<p>Enter degree to convert:</p>");
                out.println("<form method=\"get\">");
                out.println("<p> <input type=\"text\" name=\"degree\" size=\"25\"></p>");
                out.println("<br/>");
                out.println("<input type=\"submit\" name=\"F TO R\" value=\"F TO R\">"
                        + "<input type=\"submit\" name=\"R TO F\" value=\"R TO F\">");
                out.println("</form>");
                out.println("</div>");
            }
        } finally {
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}