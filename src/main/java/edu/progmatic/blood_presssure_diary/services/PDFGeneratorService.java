package edu.progmatic.blood_presssure_diary.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.progmatic.blood_presssure_diary.constants.DateFormats;
import edu.progmatic.blood_presssure_diary.models.measurement.MeasurementDetails;
import edu.progmatic.blood_presssure_diary.models.registration.User;
import edu.progmatic.blood_presssure_diary.repositories.MeasureRepository;
import edu.progmatic.blood_presssure_diary.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PDFGeneratorService {
    Logger logger = LoggerFactory.getLogger(PDFGeneratorService.class);
    private UserRepository userRepository;
    private MeasureRepository measureRepository;

    @Autowired
    public PDFGeneratorService(UserRepository userRepository, MeasureRepository measureRepository) {
        this.userRepository = userRepository;
        this.measureRepository = measureRepository;
    }

    public List<MeasurementDetails> generatePDF() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateFormats.DATE_TIME_FORMAT);
            String fileName = "C:\\Users\\Csaba\\IdeaProjects\\blood_pressure_diary\\chillyfacts_test.pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Object principal = auth.getPrincipal();
            User user = (User) principal;

            document.open();
            Paragraph paragraph = new Paragraph(user.getLastName()+" "+user.getFirstName()+" vérnyomás eredményei");
            document.add(paragraph);
            PdfPTable table = new PdfPTable(4);
            PdfPCell c1 = new PdfPCell(new Phrase("Dátum"));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Szisztolés"));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Diasztolés"));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Pulzus"));
            table.addCell(c1);
            logger.info(user.getMeasurements()+" lista");
            logger.info(user.getMeasurements().get(0).getDate()+" datum");
            table.setHeaderRows(1);
            for (int i = 0; i <user.getMeasurements().size() ; i++) {
                table.addCell(user.getMeasurements().get(i).getDate().format(formatter));
                table.addCell(user.getMeasurements().get(i).getSystolicValue()+"");
                table.addCell(user.getMeasurements().get(i).getDiastolicValue()+"");
                table.addCell(user.getMeasurements().get(i).getPulsePerMin()+"");
            }


            document.add(table);
            document.close();
            System.out.println("pdf created");
            return user.getMeasurements();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }
}
