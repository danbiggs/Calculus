package calculus;
import java.util.Scanner;
public class Calculus {  
    /**
     * Calculates the derivative of a term of a function
     * Pre: Term must be in the form ax^b
     * Post: Return derivative of term
     */
    public static String polynomial_derivative(double a, double b){
        String Newton;
        if(b!=0){
            Newton=(b*a)+"x^"+(b-1);
        }
        else{
            Newton="0";
        }
        return Newton;
    }
    /**
     * Turns an array of derivative terms into one function one line long
     * pre: none
     * post: one entire function returned
     */
    public static String polynomial_summation(String[]derivative){
        String function=" ";
        for(int i=0;i<derivative.length;i++){
            function=function+" + "+derivative[i];
        }
        return function;
    }
    /**
     * Turns an array of derivative terms and an array of the original terms into the derivative of a function using the chain rule
     * pre: both arrays must be of the same length
     * post: one entire derivative function returned
     */
    public static String chain_sum(String[]derivative, String[]original,int power){
        String first=polynomial_summation(original);
        String second=polynomial_summation(derivative);
        String total=power+"("+first+")^"+(power-1)+"*"+"("+second+")";
        return total;
    }
    /**
     * Turns an array of derivative terms and an array of the original terms into the derivative of a function using the quotient rule
     * pre: derivative_top and original_top must be the same length; derivative_bottom and original_bottom must be of the same length
     * post: one entire derivative function returned
     */
    public static String rational_sum(String[]derivative_top, String[]derivative_bottom, String[]original_top, String[]original_bottom){
        String top_derivative=polynomial_summation(derivative_top);
        String bottom_derivative=polynomial_summation(derivative_bottom);
        String top_original=polynomial_summation(original_top);
        String bottom_original=polynomial_summation(original_bottom);
        String total="(("+top_derivative+")"+"("+bottom_original+")"+" - ("+top_original+")"+"("+bottom_derivative+"))"+"/"+"("+bottom_original+")^2";
        return total;
    }
    public static void main(String[] args) {
        Scanner StringInput=new Scanner (System.in); //initializes Scanner for Strings
        Scanner intInput=new Scanner (System.in); //initializes Scanner for ints
        Scanner doubleInput=new Scanner (System.in); //initializes Scanner for doubles
        System.out.println("Please state how many terms are in this function.");
        int FuncTerm=intInput.nextInt(); //takes in number of terms of entire function
        String[]derivative=new String[FuncTerm]; //creates an array, of which each space will contain the deriative of the appropriate term
        
        for(int i=0;i<FuncTerm;i++){
        System.out.println("Please state what type of term this is. Choose from among: polynomial, exponential, rational, or trigonometric.");
        String FuncType=StringInput.nextLine(); //records the type of function
        if(FuncType.equals("polynomial")){
            System.out.println("Please state whether this term will require the chain rule. Type 'yes' or 'no.'");
            String Chain=StringInput.nextLine();
            if(Chain.equals("no")){ //takes the derivative of the term whose a and b values are inputted, storing it in the 'derivative' array
                System.out.println("Please input this term in the form ax^b; if this term is a constant its value for a and 0 for b");
                System.out.println("The a value?");
                double a=doubleInput.nextDouble();
                System.out.println("The b value?");
                double b=doubleInput.nextDouble();
                derivative[i]=polynomial_derivative(a, b);
            }
            if(Chain.equals("yes")){ //takes the derivative of the term using the chain rule, storing it in the 'derivative' array
                System.out.println("Assume the function is in the form: f(x)=(ax^b+cx^d...)^e");
                System.out.println("Please state the number of terms inside the brackets (i.e., a function with only a and c per the above example would have 2).");
                int ChainNum=intInput.nextInt();
                String[]ChainDeriv=new String[ChainNum]; //creates an array to store the derivative of each term within the brackets of this term
                String[]ChainOrigi=new String[ChainNum]; //creates an array to store the original terms within the brackets
                for(int j=0;j<ChainNum;j++){
                System.out.println("Please input each term within the outer brackets in the form ax^b; if this term is a constant, type its value for a and 0 for b.");    
                System.out.println("The a value?");
                double a=doubleInput.nextDouble();
                System.out.println("The b value?");
                double b=doubleInput.nextDouble();
                ChainDeriv[j]=polynomial_derivative(a, b);
                ChainOrigi[j]=a+"x^"+b;
                }
                System.out.println("Please state the exponent outside the outer brackets");
                int ChainPower=intInput.nextInt(); //takes in the outer power
                derivative[i]=chain_sum(ChainDeriv, ChainOrigi, ChainPower);
            }
        }
        if(FuncType.equals("rational")){ //takes the derivative of the rational term using the chain rule
            System.out.println("Assume the top and bottom of the function are each in the form: f(x)=(ax^b+cx^d...)");
            System.out.println("Please state how many terms are in the top half of this function");
            int FuncTop=intInput.nextInt(); //records the number of terms in the top
            System.out.println("Please state how many terms are in the bottom half of this function");
            int FuncBottom=intInput.nextInt(); //records the number of terms in the bottom
            String[]original_top=new String[FuncTop]; //stores all the original terms in the top of the rational term
            String[]derivative_top=new String[FuncTop]; //stores the derivatives of the top
            String[]original_bottom=new String[FuncBottom]; //stores all the original terms of the bottom
            String[]derivative_bottom=new String[FuncBottom]; //stores all the derivatives of the bottom
            for(int k=0;k<FuncTop;k++){ //records the a and b value of each term within the top half
                System.out.println("Please input each term within the top in the form ax^b; if this term is a constant, type its value for a and 0 for b");
                System.out.println("The a value?");
                double a=doubleInput.nextDouble();
                System.out.println("The b value?");
                double b=doubleInput.nextDouble();
                original_top[k]=a+"x^"+b;
                derivative_top[k]=polynomial_derivative(a, b);
            }
            for(int l=0;l<FuncBottom;l++){ //records the a and b value of each term within the bottom half
                System.out.println("Please input each term within the bottom in the form ax^b; if this term is a constant, type its value for a and 0 for b");
                System.out.println("The a value?");
                double a=doubleInput.nextDouble();
                System.out.println("The b value?");
                double b=doubleInput.nextDouble();
                original_bottom[l]=a+"x^"+b;
                derivative_bottom[l]=polynomial_derivative(a, b);
            }
            derivative[i]=rational_sum(derivative_top, derivative_bottom, original_top, original_bottom); //stores derivative of rational term in the 'derivative' array
        }
        if(FuncType.equals("exponential")){ //takes the derivative of an exponential function
            System.out.println("Assume the term is in the form a^x; state the value of a");
            System.out.println("The a value?");
            double a=doubleInput.nextDouble();
            derivative[i]=a+"^x*(ln("+a+"))";
        }
        if(FuncType.equals("trigonometric")){ //takes the derivative of trigonometric functions according to the ratio involved
            System.out.println("Assume the term is in the form aTrig(x), where a is a coefficient and Trig a trigonometric ratio");
            System.out.println("The a value?");
            double a=doubleInput.nextDouble();
            System.out.println("The trigonometric ratio (short form)?");
            String ratio=StringInput.nextLine();
            if(ratio.equals("sin")){ //a trigonometric ratio is matched with the appropriate ratio for the derivative of its function
                derivative[i]=a+"cos(x)";
            }
            if(ratio.equals("cos")){
                derivative[i]="-"+a+"sin(x)";
            }
            if(ratio.equals("tan")){
                derivative[i]=a+"sec^2(x)";
            }
            if(ratio.equals("csc")){
                derivative[i]="-"+a+"csc(x)cot(x)";
            }
            if(ratio.equals("sec")){
                derivative[i]=a+"sec(x)tan(x)";
            }
            if(ratio.equals("cot")){
                derivative[i]=a+"-csc^2(x)";
            }
            if(ratio.equals("arcsin")){
                derivative[i]=a+"/sqrt(1-x^2)";
            }
            if(ratio.equals("arccos")){
                derivative[i]="-"+a+"/sqrt(1-x^2)";
            }
            if(ratio.equals("arctan")){
                derivative[i]=a+"/(1+x^2)";
            }
            if(ratio.equals("arccot")){
                derivative[i]="-"+a+"/(1+x^2)";
            }
            if(ratio.equals("arcsec")){
                derivative[i]=a+"/(abs(x)sqrt(x^2-1)";
            }
            if(ratio.equals("arccsc")){
                derivative[i]="-"+a+"/(abs(x)sqrt(x^2-1)";
            }
        }
        }
        System.out.println(polynomial_summation(derivative));//uses the polynomial_summation method to sum up all the terms in the 'derivative' array into one one-line function, then prints it
    }
}
