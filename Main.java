import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\oguzs\\IdeaProjects\\SE302_Practice\\src\\first.bib");
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(file, true)
        );

        BufferedReader reader = new BufferedReader(
                new FileReader(file)
        );

        if (file.createNewFile()) {
            System.out.println("File created!");
//            writer.write("@Article{Sabitay2011, author = {Oguz Sabitay}, title = {Perks of being very smart}, journal = {what is this?}, year = {2011},}");
//            writer.close();

        } else {
            System.out.println("File already exists!");
//            writer.write("@Article{Sabitay2011, author = {Oguz Sabitay}, title = {Perks of being very smart}, journal = {what is this?}, year = {2011},}");
//            writer.close();

            String line;
            String allLines = "";
            String[] splitLine = {};
            int openCurlyBraceCounter = 0;

            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
                allLines += line + " ";

                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '{') {
                        openCurlyBraceCounter++;
                    }
                }
            }

            splitLine = allLines.split("[{},]+|(\\s=\\s)+|(=\\s)+|(\\s=)+|(=)+");
            System.out.println(openCurlyBraceCounter);
            for (String element: splitLine) {
                System.out.println(element);
            }

            System.out.println(splitLine.length);

//            System.out.println(splitLine[0]);
        }
    }
}
