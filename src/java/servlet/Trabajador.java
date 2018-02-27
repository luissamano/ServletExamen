/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marti
 */
public class Trabajador extends HttpServlet {

    private Connection conn;
    private String user = "root", password = "";

    private String Clave, HorasLab, Nombre, HorasPag, ApellidoPat,
                   ApellidoMat, depto, HorasExt, Sueldos, SueldoNeto;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");

        Clave = req.getParameter("clave");
        HorasLab = req.getParameter("horasl");
        Nombre = req.getParameter("nombre");
        HorasPag = req.getParameter("horasp");
        ApellidoPat = req.getParameter("appp");
        ApellidoMat = req.getParameter("appm");
        depto = req.getParameter("depto");
        HorasExt = req.getParameter("horase");
        Sueldos = req.getParameter("sueldos");
        SueldoNeto = req.getParameter("sueldon");

        Enumeration nombresParams = req.getParameterNames();
        while (nombresParams.hasMoreElements()) {
            String param = (String) nombresParams.nextElement();
            String valor = req.getParameter(param);
            //ArchivoTutorias.println(param + ": " + valor);
        }

//                ArchivoTutorias.println("<FIN>");
//                ArchivoTutorias.close();
//                fw.close();
        try {

            //Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nomina?user=" + user + "&pasword=" + password);

            String query = String.format("INSERT INTO nomina.tblnominas"
                    + "(Clave, Nombre, AppPaterno, AppMaterno, Depto, HorasLab, HorasPagadas, HorasExtras, SueldoNormal, SueldoNeto)"
                    + "VALUES ("
                    + "  '" + Clave + " '"
                    + ", '" + Nombre + "'"
                    + ", '" + ApellidoPat + "'"
                    + ", '" + ApellidoMat + "'"
                    + ", '" + depto + "'"
                    + ", '" + HorasLab + "'"
                    + ", '" + HorasPag + "'"
                    + ", '" + HorasExt + "'"
                    + ", '" + Sueldos + "'"
                    + ", '" + SueldoNeto + "')");
            
            //processRequest(request, response);
            PreparedStatement st = conn.prepareStatement(query);
            st.execute();
            conn.close();

        } catch (SQLException | ClassNotFoundException se) {

        }
    }

}
