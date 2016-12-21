package br.ufc.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import br.ufc.model.Funcionario;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class PDFUtil {
	
	public void gerarPDF(List<Funcionario> funcionarios){

		System.out.println(("HEY"));
		Document document = new Document();
		
		try {
			PdfWriter.getInstance(document, new FileOutputStream("~/arquivo.pdf"));

			document.open();

			Paragraph p = new Paragraph("Relatório PDF:");
			p.setAlignment(1);
			document.add(p);

			Paragraph p2 = new Paragraph("");
			document.add(p2);
			
			PdfPTable table = new PdfPTable(3);
			
			PdfPCell cel1 =  new PdfPCell(new Paragraph("TEste 1"));
			PdfPCell cel2 =  new PdfPCell(new Paragraph("TEste 2"));
			PdfPCell cel3 =  new PdfPCell(new Paragraph("TEste 3"));
			
			table.addCell(cel1);
			table.addCell(cel2);
			table.addCell(cel3);
			
			for (Funcionario f:funcionarios){

				PdfPCell cel4 =  new PdfPCell(new Paragraph(f.getNome()));
				PdfPCell cel5 =  new PdfPCell(new Paragraph(f.getCargo()));
				PdfPCell cel6 =  new PdfPCell(new Paragraph( String.valueOf(f.getSalario())));
				
				table.addCell(cel4);
				table.addCell(cel5);
				table.addCell(cel6);
			}
			
			document.add(table);
			document.close();
			



		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



}
