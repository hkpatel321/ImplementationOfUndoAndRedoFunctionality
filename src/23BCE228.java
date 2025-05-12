import java.util.*;
import java.io.*;
import java.util.regex.Pattern;


//Custom class of Stack
class Stack {
    node top;
    // Node class
    class node {
        String data;
        node next;
        public node(String data) {
            this.data = data;
        }
    }
   // push function to push element in the stack
    public void push(String data) {
        node newnode = new node(data);
        if (top == null) {
            top = newnode;
        } else {
            newnode.next = top;
            top = newnode;
        }
    }
//pop function to reomove the last element from the stack
    public String pop() {
        if (top == null) {
            return null;
        } else {
            System.out.println("popped: " + top.data);
            String data = top.data;
            top = top.next;
            return data;
        }
    }
}

 class Files {
    List<String> contentlist ;  // Contentlist  is ArrayList to store the content written in the file
    StringBuilder textbuilder = new StringBuilder("");
    Stack tempstack = new Stack(); // stack to perform redo functionality
    // load content to read data from the file and store it in StringBuilder and finally to  contentListb
    public void loadcontent(String filepath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {

                textbuilder.append(line).append(" ");
            }
        } catch (IOException e) {
            System.out.println("an error occurred: " + e.getMessage());
        }

        String text = textbuilder.toString();
        System.out.println("original content: " + text);
        contentlist = new ArrayList<>(Arrays.asList(text.split(Pattern.quote(" "))));
    }
// undo- functionality -> Implemented by removing  the last element from contentlist and pushing that element to the stack
    public void undo() {
        if (!contentlist.isEmpty()) {
            String lastitem = contentlist.remove(contentlist.size() - 1);
            tempstack.push(lastitem);
            displaycontent();
        } else {
            System.out.println("no content to remove.");
        }
    }
// prints the data from the contentlist by converting it to String
    public void displaycontent() {
        String result = String.join(" ", contentlist);
        if(result.isEmpty()){
            System.out.println("Currently no content in the file ");
            return;
        }
        System.out.println("content: " + result);
    }
// redo functionality -> implemented by popping the element from the stack and pushing it again to List
    public void redo() {
        String lastitem = tempstack.pop();
        if (lastitem != null) {
            contentlist.add(lastitem);
            displaycontent();
        } else {
            System.out.println("no content to restore.");
        }
    }
// Find and Replace functionality -> Implemented by taking the find string and Replacement string . The word is searched and then replaced in the file .
    public void searchandreplace(String target, String replacement, String filepath) {
        boolean found=false;
        for (int i = 0; i < contentlist.size(); i++) {
            if (contentlist.get(i).equals(target)) {
                found=true;
                contentlist.set(i, replacement);
            }
        }
        if(!found){
            System.out.println("No such word in file");
            displaycontent();
            return;
        }
        displaycontent();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            String updatedcontent = String.join(" ", contentlist);
            writer.write(updatedcontent);
        } catch (IOException e) {
            System.out.println("an error occurred while writing to the file: " + e.getMessage());
        }
    }

    // adds the content in the file

    public void appendcontent(String newcontent, String filepath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true));
            writer.write(" " + newcontent);
            writer.close();

            contentlist.addAll(Arrays.asList(newcontent.split(Pattern.quote(" "))));
            System.out.println("updated content after appending: ");
            displaycontent();

        } catch (IOException e) {
            System.out.println("an error occurred: " + e.getMessage());
        }
    }
}
 class Main {

    // It converts the path entered by the use to a valid path .
    public  static String  filePathGenerator(String path) {
        return path.replace("\\", "\\\\").trim();
    }
    public static void main(String[] args) {
        Files filehandler = new Files();
        Scanner scanner = new Scanner(System.in);
        String filepath = "C:\\Users\\patel\\IdeaProjects\\DSAssignment\\src\\example.txt";
        String inputfilepath;
        System.out.println("Do you want to continue with default filepath or you want to read your own file with own path?(yes/no)");

        String answer=scanner.nextLine();
        if(answer.equalsIgnoreCase("yes")){
            System.out.println("Enter the file path: ");
             inputfilepath=scanner.nextLine();
        }else{
            inputfilepath=filepath;
        }




        filehandler.loadcontent(filePathGenerator(inputfilepath));

        boolean quit = false;
       // Different cases
        while (!quit) {
            System.out.println("\nchoose an option:");
            System.out.println("1. Undo");
            System.out.println("2. Redo");
            System.out.println("3. search and replace");
            System.out.println("4. append content");
            System.out.println("5. show current text");
            System.out.println("6. exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    filehandler.undo();
                    break;

                case 2:
                    filehandler.redo();
                    break;

                case 3:
                    System.out.println("enter the word to find:");
                    String target = scanner.nextLine();
                    System.out.println("enter the replacement word:");
                    String replacement = scanner.nextLine();
                    filehandler.searchandreplace(target, replacement, inputfilepath);
                    break;

                case 4:
                    System.out.println("enter the content to add:");
                    String newcontent = scanner.nextLine();
                    filehandler.appendcontent(newcontent, inputfilepath);
                    break;

                case 5:
                    System.out.println("current content in the file:");
                    filehandler.displaycontent();
                    break;

                case 6:
                    quit = true;
                    break;

                default:
                    System.out.println("invalid option. please try again.");
                    break;
            }
        }


    }
}