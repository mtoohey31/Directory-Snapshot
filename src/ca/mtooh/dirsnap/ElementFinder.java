/*
 * This provides methods for getting different elements from an XML file created by the Directory Snapshot Application.
 * It has methods of each type for both nodes and elements.
 */
package ca.mtooh.dirsnap;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.w3c.dom.*;

/**
 *
 * @author mtoohey
 */
public class ElementFinder {

    public String displayString(Node input) {
        //Creates a new date time formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mm:ss a").withZone(ZoneId.systemDefault());

        //Finds different the elements and inserts the necessary text in between then returns it
        return input.getFirstChild().getNextSibling().getTextContent() + " (Last Modified: " + formatter.format(Instant.ofEpochMilli(Long.parseLong(input.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getTextContent()))) + ", Size: " + humanReadableByteCount(Long.parseLong(input.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent()), true) + ")";
    }

    public String displayString(Element input) {
        //Creates a new date time formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mm:ss a").withZone(ZoneId.systemDefault());

        //Finds different the elements and inserts the necessary text in between then returns it
        return input.getFirstChild().getNextSibling().getTextContent() + " (Last Modified: " + formatter.format(Instant.ofEpochMilli(Long.parseLong(input.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getTextContent()))) + ", Size: " + humanReadableByteCount(Long.parseLong(input.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent()), true) + ")";
    }

    public String name(Node input) {
        //Finds the name of the current node
        return input.getFirstChild().getNextSibling().getTextContent();
    }

    public String name(Element input) {
        //Finds the name of the current element
        return input.getFirstChild().getNextSibling().getTextContent();
    }

    public String size(Node input) {
        //Finds the size in bytes of the current node
        return input.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getTextContent();
    }

    public String size(Element input) {
        //Finds the size in bytes of the current element
        return input.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getTextContent();
    }

    public String lastModified(Node input) {
        //Finds the millisecond time of the current node
        return input.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent();
    }

    public String lastModified(Element input) {
        //Finds the millisecond time of the current node
        return input.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent();
    }

    public Node children(Node input) {
        //Finds the child element of the current node
        return input.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling();
    }

    public Node children(Element input) {
        //Finds the child element of the current element
        return input.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling();
    }

    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}
