package socialnetwork.repository.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import socialnetwork.domain.Message;
import socialnetwork.domain.Utilizator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

public abstract class AbstractPdfRepository {
    protected Utilizator user;

    public AbstractPdfRepository(Utilizator user) {
        this.user = user;
    }

    protected static final Font basicFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.NORMAL);
    protected static final Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 20,
            Font.BOLD);
    protected static final Font titleFont2 = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    protected static final Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);

    public void generate(File file, String fileName, LocalDate from, LocalDate to,
                         List<Utilizator> friends, List<Message> messages) {
        try {
            File finalFile = new File(file + fileName);
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(finalFile));
            document.open();
            addContent(document, from, to, friends, messages);
            document.close();

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }

    public void addContent(Document document, LocalDate from, LocalDate to,
                            List<Utilizator> friends, List<Message> messages)
            throws DocumentException {

    }

    protected static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
