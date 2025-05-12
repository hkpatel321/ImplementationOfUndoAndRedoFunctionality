//import java.io.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.regex.Pattern;
//
//public class Files {
//    List<String> contentlist = new ArrayList<>();
//    StringBuilder textbuilder = new StringBuilder("");
//    Stack tempstack = new Stack();
//
//    public void loadcontent(String filepath) {
//        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                textbuilder.append(line).append(" ");
//            }
//        } catch (IOException e) {
//            System.out.println("an error occurred: " + e.getMessage());
//        }
//
//        String text = textbuilder.toString();
//        System.out.println("original content: " + text);
//        contentlist = new ArrayList<>(Arrays.asList(text.split(Pattern.quote(" "))));
//    }
//
//    public void undo() {
//        if (!contentlist.isEmpty()) {
//            String lastitem = contentlist.remove(contentlist.size() - 1);
//            tempstack.push(lastitem);
//            displaycontent();
//        } else {
//            System.out.println("no content to remove.");
//        }
//    }
//
//    public void displaycontent() {
//        String result = String.join(" ", contentlist);
//        if(result.isEmpty()){
//            System.out.println("Currently no content in the file ");
//            return;
//        }
//        System.out.println("content: " + result);
//    }
//
//    public void redo() {
//        String lastitem = tempstack.pop();
//        if (lastitem != null) {
//            contentlist.add(lastitem);
//            displaycontent();
//        } else {
//            System.out.println("no content to restore.");
//        }
//    }
//
//    public void searchandreplace(String target, String replacement, String filepath) {
//        boolean found=false;
//        for (int i = 0; i < contentlist.size(); i++) {
//            if (contentlist.get(i).equals(target)) {
//                found=true;
//                contentlist.set(i, replacement);
//            }
//        }
//        if(!found){
//            System.out.println("No such word in file");
//            displaycontent();
//            return;
//        }
//        displaycontent();
//
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
//            String updatedcontent = String.join(" ", contentlist);
//            writer.write(updatedcontent);
//        } catch (IOException e) {
//            System.out.println("an error occurred while writing to the file: " + e.getMessage());
//        }
//    }
//
//    public void appendcontent(String newcontent, String filepath) {
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true));
//            writer.write(" " + newcontent);
//            writer.close();
//
//            contentlist.addAll(Arrays.asList(newcontent.split(Pattern.quote(" "))));
//            System.out.println("updated content after appending: ");
//            displaycontent();
//
//        } catch (IOException e) {
//            System.out.println("an error occurred: " + e.getMessage());
//        }
//    }
//}