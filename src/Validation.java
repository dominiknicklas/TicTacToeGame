import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    protected static int enterInt() {
        Scanner input = new Scanner(System.in);
        int number = 0;
        boolean valid = true;
        do {
            try{
                number = input.nextInt();
                valid = false;
            } catch(Exception e) {
                input.next();
                System.out.println("The input must be of type Integers!");
            }
        } while(valid);

        return number;
    }

    static String validateName(String type) {
        String regex = "^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$";
        Pattern pat = Pattern.compile(regex);
        String name;
        boolean valid = true;
        Scanner input = new Scanner(System.in);
        do{
            if(!valid){
                System.out.println("This can't be a name");
            }
            System.out.println("Enter name of " + type);
            name = input.next();
            Matcher mat = pat.matcher(name);
            valid = mat.matches();
        } while(!valid);
        return name;
    }
}
