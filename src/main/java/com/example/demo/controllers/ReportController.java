package com.example.demo.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Producto;
import com.example.demo.model.Reporte;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@RequestMapping("/report")
public class ReportController {

	
	@GetMapping("/report")
	public void report(HttpServletResponse response) throws JRException, IOException {
		
		// 1. Acceder al reporte 
		InputStream jasperStream = this.getClass().getResourceAsStream("/reportes/primerReporte2.jasper");
		
		// 2. Preparar los datos 
		Map<String, Object> params = new HashMap<>();
		params.put("usuario", "Jorge Ventura");
		
		List<Reporte> listProduct = new ArrayList<>();
		listProduct.add(new Reporte("1", "Producto 1", "12.00"));
		listProduct.add(new Reporte("2", "Producto 2", "0.50"));
		listProduct.add(new Reporte("3", "Producto 3", "1.50"));
		listProduct.add(new Reporte("4", "Producto 4", "120.00"));
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listProduct);
		
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		
		// 3. Configuracion 
		
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "filename=reporte_ejemplo.pdf");
		
		// 4. Exportar reporte
		final OutputStream outputStream = response.getOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		
		
	}
	
	
}
