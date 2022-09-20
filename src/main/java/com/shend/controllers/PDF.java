package com.shend.controllers;

import com.shend.tables.GetCount;
import com.shend.tables.GetCount1;
import com.shend.tables.GetDesc;
import com.shend.tables.GetDesc1;
import com.shend.tables.MaxCallDuration;
import com.shend.tables.Queries20;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

public class PDF {

    static void genPDF(String query, ObservableList<GetCount> list9, ObservableList<GetCount1> list10,
            ObservableList<Queries20> list13, ObservableList<MaxCallDuration> list14, ObservableList<GetDesc> list17,
            ObservableList<GetDesc1> list18) {

        PdfWriter writer = null;
        try {
            var cur_time = LocalTime.now().getHour() + "-" + LocalTime.now().getMinute() + "-" + LocalTime.now().getSecond();
            File myObj = new File("C:\\Users\\Vleugel\\Desktop\\" + query.toUpperCase() + "_" + cur_time + ".pdf");
            myObj.createNewFile();
            writer = new PdfWriter(myObj.getPath());
            PdfDocument pdf = new PdfDocument(writer);
            Document doc = new Document(pdf);
            Table table = null;
            switch (query) {
                case "query9":
                    Paragraph title1 = new Paragraph("How many abonents use each tariff");
                    doc.add(title1);
                    float[] pointColumnWidths = {150F, 150F};
                    table = new Table(pointColumnWidths);
                    table.addCell("Tariff");
                    table.addCell("Count");
                    for (var i : list9) {
                        table.addCell(i.getName());
                        table.addCell(i.getCount().toString());
                    }
                    break;
                case "query10":
                    Paragraph title = new Paragraph("How many abonents use each privilege");
                    doc.add(title);
                    float[] pointColumnWidths2 = {150F, 150F};
                    table = new Table(pointColumnWidths2);
                    table.addCell("Privilege");
                    table.addCell("Count");
                    for (var i : list10) {
                        table.addCell(i.getPl());
                        table.addCell(i.getCount().toString());
                    }
                    break;
                case "query13":
                    Paragraph title2 = new Paragraph("Max service payment for each abonent");
                    doc.add(title2);
                    float[] pointColumnWidths1 = {150F, 150F, 150F, 150F, 150F};
                    table = new Table(pointColumnWidths1);
                    table.addCell("Name");
                    table.addCell("Surname");
                    table.addCell("Patronymic");
                    table.addCell("Payment");
                    table.addCell("Telephone");
                    for (var i : list13) {
                        table.addCell(i.getName());
                        table.addCell(i.getSurname());
                        table.addCell(i.getPatronymic());
                        table.addCell(i.getPrice().toString());
                        table.addCell(i.getTel_number());
                    }
                    break;
                case "query14":
                    Paragraph title3 = new Paragraph("Max call price for each abonent");
                    doc.add(title3);
                    float[] pointColumnWidths12 = {150F, 150F};
                    table = new Table(pointColumnWidths12);
                    table.addCell("Abonent");
                    table.addCell("Price");
                    for (var i : list14) {
                        table.addCell(i.getName());
                        table.addCell(i.getMax().toString());
                    }
                    break;
                case "query17":
                    Paragraph title4 = new Paragraph("Most popular tariff and not yet used tariff");
                    doc.add(title4);
                    float[] pointColumnWidths3 = {150F, 150F};
                    table = new Table(pointColumnWidths3);
                    table.addCell("Tariff");
                    table.addCell("Description");
                    for (var i : list17) {
                        table.addCell(i.getTariff());
                        table.addCell(i.getDescription());
                    }
                    break;
                case "query18":
                    Paragraph title5 = new Paragraph("Most popular privilege and not yet used privilege");
                    doc.add(title5);
                    float[] pointColumnWidths4 = {150F, 150F};
                    table = new Table(pointColumnWidths4);
                    table.addCell("Service");
                    table.addCell("Description");
                    for (var i : list18) {
                        table.addCell(i.getService());
                        table.addCell(i.getDescription());
                    }
                    break;
            }
            doc.add(table);
            doc.close();
        } catch (Exception ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
