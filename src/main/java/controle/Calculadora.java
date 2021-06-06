/*PLima*/
package controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/Calculadora")
public class Calculadora extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Float valor1 = null;
        Float valor2 = null;
        String tmp = null;
        boolean erroValor1 = false;
        boolean erroValor2 = false;

        if (request.getParameter("valor1") != null) {
            try {
                tmp = request.getParameter("valor1");
                valor1 = Float.parseFloat(tmp); 
            } catch (NumberFormatException ex) {
            	System.out.println(ex.toString());
                erroValor1 = true;
                request.setAttribute("erroValor1", "Valor 1 inválido!");
            }
        }

        if (request.getParameter("valor2") != null) {
            try {
                tmp = request.getParameter("valor2");
                valor2 = Float.parseFloat(tmp);
            } catch (NumberFormatException ex) {
            	System.out.println("valor2 error: " + ex.toString());
                erroValor2 = true;
                request.setAttribute("erroValor2", "Valor 2 inválido!");
            }
        }
        
        
        if ((!erroValor1 && !erroValor2) && (valor1 != null && valor2 != null)) {
            Float resultado = null;
            char op = request.getParameter("op").charAt(0);

            switch (op) {
                case '+':
                    resultado = valor1 + valor2;
                    break;
                case '-':
                    resultado = valor1 - valor2;
                    break;
                case '*':
                    resultado = valor1 * valor2;
                    break;
                case '/':
                    if (valor2 > 0) {
                        resultado = valor1 / valor2;
                    } else {                 
                        request.setAttribute("erroDiv", "Divisão por zero");
                    }
                    break;
            }
            if (resultado != null) {
                request.setAttribute("aux", "Resultado: ");
                request.setAttribute("resultado", String.format("%f %c %f = %f", valor1, op, valor2, resultado));
            }
            
        }

        request.getRequestDispatcher("calcula.jsp").forward(request, response);
	}
}
