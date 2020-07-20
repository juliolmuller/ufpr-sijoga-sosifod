package com.lacussoft.sijoga.bean;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Named
@RequestScoped
public class ReportBean implements Serializable {

    private Date data1;
    private Date data2;

    @Inject
    private ExternalContext externalContext;

    public void printReport(int id, String report) {
        String path = null;

        if (report.equals("Parte")) {
            path = "/reports/Parte.jrxml";
        } else if (report.equals("FechadosAd")) {
            path = "/reports/FechadosAd.jrxml";
        } else if (report.equals("AbertosAd")) {
            path = "/reports/AbertosAd.jrxml";
        }

        InputStream jasperTemplate = externalContext.getResourceAsStream(path);
        Connection conn = null;

        try {
            JasperReport JasperReport = JasperCompileManager.compileReport(jasperTemplate);
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dac_sijoga", "postgres", "datascienz");

            Map parametros = new HashMap();
            parametros.put("parteId", id);

            if (report.equals("AbertosAd")) {
                parametros.put("data1", new java.sql.Date(data1.getTime()));
                parametros.put("data2", new java.sql.Date(data2.getTime()));
            }

            JasperPrint JasperPrint = JasperFillManager.fillReport(JasperReport, parametros, conn);
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();

            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"relatorio.pdf\"");
            JasperExportManager.exportReportToPdfStream(JasperPrint, outputStream);

            outputStream.flush();
            outputStream.close();
            FacesContext.getCurrentInstance().renderResponse();
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException | SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    public void setData1(Date data1) {
        this.data1 = data1;
    }

    public Date getData1() {
        return data1;
    }

    public void setData2(Date data2) {
        this.data2 = data2;
    }

    public Date getData2() {
        return data2;
    }
}
