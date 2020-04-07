package StringFirstAssignment;

import edu.duke.URLResource;

public class Part4 {

    private URLResource urlRes = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");

    public void findURLS() {

        for (String word : urlRes.words()) {
            String wordLower = word.toLowerCase();
            int index = wordLower.indexOf("youtube.com");
            if (index != -1) {
                int begURL = word.lastIndexOf("\"", index);
                int endURl = word.indexOf("\"", index + 1);
                System.out.println(word.substring(begURL+1, endURl ));
            }
        }
    }

    public static void main(String args[]) {
        Part4 p4 = new Part4();
        p4.findURLS();
    }
}