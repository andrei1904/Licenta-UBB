package socialnetwork.repository.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import socialnetwork.domain.Message;
import socialnetwork.domain.Utilizator;

import java.time.LocalDate;
import java.util.List;

public class MessagesPdf extends AbstractPdfRepository {
    public MessagesPdf(Utilizator user) {
        super(user);
    }

    @Override
    public void addContent(Document document, LocalDate from, LocalDate to,
                           List<Utilizator> friends, List<Message> messages)
            throws DocumentException {

        Paragraph text = new Paragraph();
        addEmptyLine(text, 1);

        text.add(new Paragraph("Messages for user: " + user.toString(), titleFont));

        text.add(new Paragraph(
                "Messages from friend: " + friends.get(0).toString() + ", from: " + from + ", to: " + to, titleFont2
        ));

        addEmptyLine(text, 3);

        if (messages.isEmpty()) {
            text.add(new Paragraph("There are no messages from this user in this period of time!", headerFont));
            document.add(text);
            return;
        }

        text.add(new Paragraph("1. Messages", headerFont));

        for (Message message : messages) {
            text.add(new Paragraph(message.toString(), basicFont));
        }

        document.add(text);
    }
}
