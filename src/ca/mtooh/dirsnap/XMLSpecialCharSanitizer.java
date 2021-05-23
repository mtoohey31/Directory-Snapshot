/* 
 * This adds escape codes for special XML characters into text so that the text can be used in an XML file without causing issues.
 * It does this by calling multiple recursive methods that go through each of the 5 characters 1 by 1.
 * For each one of the recursive methods is looks for the index of the specified character, modifies the string using a stringbuilder
 * to replace it with the corresponding escape code, the call the same method again, but searching after the spot where the escape code has been added already.
 * After no more are found (when the indexOf method returns -1) it moves on to the next symbol, when it reaches the end, it finishes.
 */
package ca.mtooh.dirsnap;

/**
 *
 * @author mtoohey
 */
public class XMLSpecialCharSanitizer {

    public String sanitize(String input) {
        //Calls the first method, finding the in
        return sanitizeAmpRecursive(input, input.indexOf("&"));
    }

    public String sanitizeAmpRecursive(String input, Integer index) {
        //Checks if the indexOf method found an "&"
        if (index == -1) {
            //Moves on to the next character if none was found
            return sanitizeLtRecursive(input, input.indexOf("<"));
        } else {
            //If one was found it creates a stringbuilder from the current string, inserts the escape code after the symbol, and the calls the same method again, except using indexOf to search after the escape code
            StringBuilder removed = new StringBuilder(input);
            removed.insert(index + 1, "amp;");
            return sanitizeAmpRecursive(removed.toString(), removed.toString().indexOf("&", index + 3));
        }
    }

    public String sanitizeLtRecursive(String input, Integer index) {
        //Checks if the indexOf method found an "<"
        if (index == -1) {
            //Moves on to the next character if none was found
            return sanitizeGtRecursive(input, input.indexOf(">"));
        } else {
            //If one was found it creates a stringbuilder from the current string, replaces the character with it's escape code, and the calls the same method again, except using indexOf to search after the escape code
            StringBuilder removed = new StringBuilder(input);
            removed.deleteCharAt(index);
            removed.insert(index, "&lt;");
            return sanitizeLtRecursive(removed.toString(), removed.toString().indexOf("<", index + 2));
        }
    }

    public String sanitizeGtRecursive(String input, Integer index) {
        //Checks if the indexOf method found an ">"
        if (index == -1) {
            //Moves on to the next character if none was found
            return sanitizeQuotRecursive(input, input.indexOf("\""));
        } else {
            //If one was found it creates a stringbuilder from the current string, replaces the character with it's escape code, and the calls the same method again, except using indexOf to search after the escape code
            StringBuilder removed = new StringBuilder(input);
            removed.deleteCharAt(index);
            removed.insert(index, "&gt;");
            return sanitizeGtRecursive(removed.toString(), removed.toString().indexOf(">", index + 2));
        }
    }

    public String sanitizeQuotRecursive(String input, Integer index) {
        //Checks if the indexOf method found an """
        if (index == -1) {
            //Moves on to the next character if none was found
            return sanitizeAposRecursive(input, input.indexOf("\'"));
        } else {
            //If one was found it creates a stringbuilder from the current string, replaces the character with it's escape code, and the calls the same method again, except using indexOf to search after the escape code
            StringBuilder removed = new StringBuilder(input);
            removed.deleteCharAt(index);
            removed.insert(index, "&quot;");
            return sanitizeQuotRecursive(removed.toString(), removed.toString().indexOf("\"", index + 4));
        }
    }

    public String sanitizeAposRecursive(String input, Integer index) {
        //Checks if the indexOf method found an "'"
        if (index == -1) {
            //Finishes the chain and returns the current string
            return input;
        } else {
            //If one was found it creates a stringbuilder from the current string, replaces the character with it's escape code, and the calls the same method again, except using indexOf to search after the escape code
            StringBuilder removed = new StringBuilder(input);
            removed.deleteCharAt(index);
            removed.insert(index, "&apos;");
            return sanitizeAposRecursive(removed.toString(), removed.toString().indexOf("\'", index + 4));
        }
    }
}
