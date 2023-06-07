package socialnetwork.repository.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import socialnetwork.domain.Message;
import socialnetwork.domain.Utilizator;

import java.time.LocalDate;
import java.util.List;

public class ActivityPdf extends AbstractPdfRepository {
    public ActivityPdf(Utilizator user) {
        super(user);
    }

    @Override
    public void addContent(Document document, LocalDate from, LocalDate to,
                           List<Utilizator> friends, List<Message> messages)
            throws DocumentException {

        Paragraph text = new Paragraph();
        addEmptyLine(text, 1);

        text.add(new Paragraph("Activity of user: " + user.toString(), titleFont));

        text.add(new Paragraph(
                "Activity from: " + from + ", to: " + to, titleFont2
        ));

        addEmptyLine(text, 3);

        text.add(new Paragraph("1. New Friends", headerFont));

        for (Utilizator user : friends) {
            text.add(new Paragraph(user.toString(), basicFont));
        }


        addEmptyLine(text, 2);
        text.add(new Paragraph("2. Messages", headerFont));

        for (Message message : messages) {
            text.add(new Paragraph(message.toString(), basicFont));
        }

        document.add(text);
    }
}
