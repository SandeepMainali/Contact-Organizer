package org.example.contacts.Controller;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.contacts.Model.Details;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

public class DatabasePDF {

    public static ByteArrayInputStream DeatailReport(List<Details> details) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();


        try {

            PdfWriter.getInstance(document, out);
            document.open();
            Font fontHeader = FontFactory.getFont(FontFactory.TIMES_BOLD, 22);
            Paragraph para = new Paragraph("Contact Details", fontHeader);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(4);
            addTableHeader(table);
            addRows(table, details);

            document.add(table);

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();

        }

        return new ByteArrayInputStream(out.toByteArray());


    }

    private static void addTableHeader(PdfPTable table) {
        Stream.of("ID", "Name","MobileNo.", "Address").forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            Font headFont = FontFactory.getFont(FontFactory.TIMES_BOLD);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setBorderWidth(2);
            header.setPhrase(new Phrase(columnTitle, headFont));
            table.addCell(header);
        });
    }

    private static void addRows(PdfPTable table, List<Details> details) {
        for (Details detail : details) {
            table.addCell(String.valueOf(detail.getId()));
            table.addCell(detail.getName());
            table.addCell(detail.getMobileno());
            table.addCell(detail.getAddress());

        }
    }

}

