/*
 * Accepts the input and output directories, along with the extension to be used and an optional depth parameter
 * This converts a directory an all it's children to a nested XML file that contains file/directory names, sizes, and last modified dates
 */
package ca.mtooh.dirsnap;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.stream.Stream;

/**
 *
 * @author mtoohey
 */
public class SnapToXML {

    //Creates a tab string so that the repeat method can be used
    String tab = "\t";

    XMLSpecialCharSanitizer st = new XMLSpecialCharSanitizer();

    public Path snapToXML(Path selectedInputPath, Path selectedOutputPath, String extension, Integer... depth) {
        try {
            //Searches to see if the currently planned file name exists, and if it does it adds an increasingly large integer to the end in brackets until the file is not aready taken
            String idealOutputPath = selectedOutputPath.toString() + "/" + selectedInputPath.getFileName() + extension;
            String outputPath = "empty";
            if (Files.exists(Paths.get(idealOutputPath))) {
                int i = 0;
                while (Files.exists(Paths.get(outputPath))) {
                    i++;
                    outputPath = selectedOutputPath.toString() + "/" + selectedInputPath.getFileName() + " (" + i + ")" + extension;
                }
            } else {
                outputPath = idealOutputPath;
            }

            //Creates the file and begins the output
            File f = new File(selectedInputPath.toString());
            OutputStream fout = new FileOutputStream(outputPath);
            OutputStream bout = new BufferedOutputStream(fout);
            OutputStreamWriter out = new OutputStreamWriter(bout, "8859_1");

            //Begins writing the file
            out.write("<?xml version=\"1.0\" ");
            out.write("encoding=\"ISO-8859-1\"?>\r\n");

            /*
             * Checks if a depth limit was included, if it was it checks if it was zero.
             * If it was not included, it calls the recursive method without a depth limit.
             * If it was included and was 0 or less, then it writes the directory to the XML and includes a <depthLimit> element in the child and does not call the recursive file.
             * If it was included and was greater than 0 it calls the recursive method with a depthLimit parameter decreased by 1 from the input.
             */
            if (depth.length > 0) {
                if (depth[0] <= 0) {
                    out.write("<dir>\r\n" + "\t<name>" + st.sanitize(selectedInputPath.getFileName().toString()) + "</name>\r\n" + "\t<last_modified>" + f.lastModified() + "</last_modified>\r\n" + "\t<size>" + f.length() + "</size>\r\n" + "\t<children>\r\n" + "\t\t<depthLimit></depthLimit>\r\n" + "\t</children>\r\n" + "</dir>\r\n");
                } else {
                    out.write("<dir>\r\n" + "\t<name>" + st.sanitize(selectedInputPath.getFileName().toString()) + "</name>\r\n" + "\t<last_modified>" + f.lastModified() + "</last_modified>\r\n" + "\t<size>" + f.length() + "</size>\r\n" + "\t<children>\r\n" + snapToXMLRecursive(selectedInputPath, 2, depth[0] - 1) + "\t</children>\r\n" + "</dir>\r\n");
                }
            } else {
                out.write("<dir>\r\n" + "\t<name>" + st.sanitize(selectedInputPath.getFileName().toString()) + "</name>\r\n" + "\t<last_modified>" + f.lastModified() + "</last_modified>\r\n" + "\t<size>" + f.length() + "</size>\r\n" + "\t<children>\r\n" + snapToXMLRecursive(selectedInputPath, 2) + "\t</children>\r\n" + "</dir>\r\n");
            }

            //Flushes the output
            out.flush();
            out.close();

            //Returns the path that the file was output to so that the view method knows where to find it.
            return Paths.get(outputPath);
        } catch (UnsupportedEncodingException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public String snapToXMLRecursive(Path currentPath, int indents, Integer... depth) {
        //Creates a stringbuilder for the output to be written to so that it can be returned as a string
        StringBuilder thisLoopOutput = new StringBuilder();

        try {
            //Creates a stream from the content of the current directory and converts it to an array of paths
            Stream<Path> currentPathContent = Files.list(currentPath);
            Path[] dirContent = currentPathContent.toArray(Path[]::new);

            //Iterrates through the array
            for (Path dirContent1 : dirContent) {
                File f = new File(dirContent1.toString());

                //Checks if the current file is an directory
                if (f.isDirectory()) {
                    /*
                     * If it's a directory it checks if a depth limit was included, if it was it checks if it was zero.
                     * If it was not included, it calls the recursive method without a depth limit.
                     * If it was included and was 0 or less, then it writes the directory to the stringbuilder and includes a <depthLimit> element in the child and does not call the recursive file.
                     * If it was included and was greater than 0 it calls the recursive method with a depthLimit parameter decreased by 1 from the input.
                     */
                    if (depth.length > 0) {
                        if (depth[0] == 0) {
                            thisLoopOutput.append(String.join("", Collections.nCopies(indents, tab)) + "<dir>\r\n" + String.join("", Collections.nCopies(indents, tab)) + "\t<name>" + st.sanitize(dirContent1.getFileName().toString()) + "</name>\r\n" + String.join("", Collections.nCopies(indents, tab)) + "\t<last_modified>" + f.lastModified() + "</last_modified>\r\n" + String.join("", Collections.nCopies(indents, tab)) + "\t<size>" + f.length() + "</size>\r\n" + String.join("", Collections.nCopies(indents, tab)) + "\t<children>\r\n" + String.join("", Collections.nCopies(indents, tab)) + "\t\t<depthLimit></depthLimit>\r\n" + String.join("", Collections.nCopies(indents, tab)) + "\t</children>\r\n" + String.join("", Collections.nCopies(indents, tab)) + "</dir>\r\n");
                        } else {
                            thisLoopOutput.append(String.join("", Collections.nCopies(indents, tab)) + "<dir>\r\n" + String.join("", Collections.nCopies(indents, tab)) + "\t<name>" + st.sanitize(dirContent1.getFileName().toString()) + "</name>\r\n" + String.join("", Collections.nCopies(indents, tab)) + "\t<last_modified>" + f.lastModified() + "</last_modified>\r\n" + String.join("", Collections.nCopies(indents, tab)) + "\t<size>" + f.length() + "</size>\r\n" + String.join("", Collections.nCopies(indents, tab)) + "\t<children>\r\n" + snapToXMLRecursive(dirContent1, indents + 2, depth[0] - 1) + String.join("", Collections.nCopies(indents, tab)) + "\t</children>\r\n" + String.join("", Collections.nCopies(indents, tab)) + "</dir>\r\n");
                        }
                    } else {
                        thisLoopOutput.append(String.join("", Collections.nCopies(indents, tab)) + "<dir>\r\n" + String.join("", Collections.nCopies(indents, tab)) + "\t<name>" + st.sanitize(dirContent1.getFileName().toString()) + "</name>\r\n" + String.join("", Collections.nCopies(indents, tab)) + "\t<last_modified>" + f.lastModified() + "</last_modified>\r\n" + String.join("", Collections.nCopies(indents, tab)) + "\t<size>" + f.length() + "</size>\r\n" + String.join("", Collections.nCopies(indents, tab)) + "\t<children>\r\n" + snapToXMLRecursive(dirContent1, indents + 2) + String.join("", Collections.nCopies(indents, tab)) + "\t</children>\r\n" + String.join("", Collections.nCopies(indents, tab)) + "</dir>\r\n");
                    }
                } else {
                    //If it's a file it adds the file's information to the stringbuilder
                    thisLoopOutput.append(String.join("", Collections.nCopies(indents, tab)) + "<file>\r\n" + String.join("", Collections.nCopies(indents, tab)) + "\t<name>" + st.sanitize(dirContent1.getFileName().toString()) + "</name>\r\n" + String.join("", Collections.nCopies(indents, tab)) + "\t<last_modified>" + f.lastModified() + "</last_modified>\r\n" + String.join("", Collections.nCopies(indents, tab)) + "\t<size>" + f.length() + "</size>\r\n" + String.join("", Collections.nCopies(indents, tab)) + "</file>\r\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //Returns the stringbuilder as a string
        return thisLoopOutput.toString();
    }
}
