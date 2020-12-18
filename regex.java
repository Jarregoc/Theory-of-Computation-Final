import java.util.ArrayList;

public class regex{

    String[][] regexGrammar;
    int numOfStates;

    public regex(DFA dfa) {
        this.numOfStates = dfa.arr.length;
        this.regexGrammar = new String[dfa.arr.length][6];
        for(int i = 0; i < dfa.arr.length; i++) {
            this.regexGrammar[i][0] = dfa.arr[i].state;
            int k = 1;
            for(int j = 0; j < dfa.arr.length; j++) {
                if(dfa.arr[j].symbol0.charAt(0) == this.regexGrammar[i][0].charAt(0)) {
                    this.regexGrammar[i][k] = dfa.arr[j].state + "0";
                    k++;
                }
                if(dfa.arr[j].symbol1.charAt(0) == this.regexGrammar[i][0].charAt(0)) {
                    this.regexGrammar[i][k] = dfa.arr[j].state + "1";
                    k++;
                }
            }
        }
        for(int i =0; i < dfa.arr.length; i++) {
            if(dfa.arr[i].isAccept == true) {
                for(int j = 0; j < dfa.arr.length; j++){
                    if(this.regexGrammar[i][j] == null){
                        this.regexGrammar[i][j] = "Lambda";
                    }
                }
            }
        }

    }

    public void regexPrinter(regex printer) {
        for(int i = 0; i < printer.numOfStates; i++){
            System.out.println(printer.regexGrammar[i][0] + ":");
            for(int j = 1; j < 6; j++) {
                System.out.println("\t" + printer.regexGrammar[i][j]);
            }
        }
    }

    public String singleToString(String[] singleGrammar) {
        String printer = singleGrammar[0] + "= ";
        for(int i = 1; i < singleGrammar.length; i++) {
            if(i == singleGrammar.length - 1) {
                if(singleGrammar[i] != null){
                    printer += (singleGrammar[i]);
                }
            }
            else {
                if(singleGrammar[i] != null){
                    if(singleGrammar[i+1] == null) {
                        printer += (singleGrammar[i]);
                    }
                    else {
                        printer += (singleGrammar[i] + " + ");
                    }
                }
            }
        }
        return printer;
    }

    public void allToString(regex allPrinter){
        for(int i = 0; i < allPrinter.numOfStates; i++) {
            System.out.println(singleToString(allPrinter.regexGrammar[i])); 
        }
    }

    public ArrayList<String> getAcceptEquation(regex printer) {
        ArrayList<String> acceptEquation = new ArrayList<String>();
        String acceptEquationHolder = "";
        for(int i = 0; i < printer.numOfStates; i++) {
            if(singleToString(printer.regexGrammar[i]).contains("Lambda")) {
                for(int j = 0; j < printer.regexGrammar[i].length; j++) {
                    if(j == 0) {
                        acceptEquationHolder += (printer.regexGrammar[i][j] + " = ");
                    }
                    else if(printer.regexGrammar[i][j] != null) {
                        if(j == printer.regexGrammar[i].length) {
                            acceptEquationHolder += (printer.regexGrammar[i][j]);
                        }
                        else{
                            if(printer.regexGrammar[i][j+1] != null){
                                acceptEquationHolder += (printer.regexGrammar[i][j] + " + ");
                            }
                            else{
                                acceptEquationHolder += (printer.regexGrammar[i][j]);
                            }
                            
                        }
                    }
                }
                break;
            }
        }
        for(int k = 0; k < acceptEquationHolder.length(); k++) {
            acceptEquation.add(String.valueOf(acceptEquationHolder.charAt(k)));
        }
        return acceptEquation;
    }

    public boolean isTerminal(String symbol) {
        System.out.println("isTerminal(" + symbol + ")  " + (symbol.equals("1") || symbol.equals("0") || symbol.equals("Lambda") || symbol.equals("+") || symbol.equals("-") || symbol.equals(" ")));
        return (symbol.equals("1") || symbol.equals("0") || symbol.equals("Lambda") || symbol.equals("+") || symbol.equals("-") || symbol.equals(" "));
    } 

    public boolean isArdensForm(ArrayList<String> equation) {
        return false;
    }

    public ArrayList<String> getTransistions(regex temp, String symbol) {
        ArrayList<String> trans = new ArrayList<String>();
        for (int i = 0; i < temp.numOfStates; i++) {
            if(symbol.equals(temp.regexGrammar[i][0])) {
                for(int j = 1; j < temp.regexGrammar[i].length; j++) {
                    if(temp.regexGrammar[i][j] != null) {
                        trans.add(temp.regexGrammar[i][j]);
                    }
                }
            }
        }
        return trans;
    }

    public boolean isAcceptedString(String string, regex grammar) {
        String description = grammar.regexGrammar[0][0];
        char symbolHolder = '-';
        System.out.println("number of State: " + grammar.numOfStates);
        System.out.println("number of cells for S " + grammar.regexGrammar[0].length);
        for(int i = string.length() - 1; i >= 0; i--){
            symbolHolder = string.charAt(i);
            System.out.println("checking for " + symbolHolder);
            for(int j = 0; j < grammar.numOfStates; j++) {
                System.out.println("looping thru grammar.regexGrammar[" + j + "]"); 
                if(String.valueOf(description.charAt(0)).equals(grammar.regexGrammar[j][0])) {
                    System.out.println("description: " + description + " is equal to " + "grammar.regexGrammar[" + j  + "][0]: "+ grammar.regexGrammar[j][0]);
                    for(int k = 1; k < grammar.regexGrammar[j].length; k++) {
                        if(grammar.regexGrammar[j][k] != null) {
                            if(grammar.regexGrammar[j][k].contains(String.valueOf(symbolHolder))) {
                                description = description.replaceFirst(String.valueOf(description.charAt(0)), grammar.regexGrammar[j][k]);
                            }
                        }
                       
                    }
                }
            }
        } 
        if(description.charAt(0) != '0' || description.charAt(0) != '1') {
            for(int i = 0; i < grammar.numOfStates; i++) {
                if(description.charAt(0) == grammar.regexGrammar[i][0].charAt(0)) {
                    for(int j = 0; j < grammar.regexGrammar[i].length; j++) {
                        if(grammar.regexGrammar[i][j] != null) {
                            if(grammar.regexGrammar[i][j].equals("Lambda")) {
                                description = description.replaceFirst(String.valueOf(description.charAt(0)), "Lambda");
                            }
                        }
                    }
                }
            }
        }
        
        System.out.println(description); 
        if((description.equals("Lambda" + string)) || (description.equals(string))) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        DFA holder = new DFA();
        DFA tester = holder.dfaTester();

        regex rTester = new regex(tester);
        rTester.regexPrinter(rTester);

        rTester.allToString(rTester);
        System.out.println(rTester.getAcceptEquation(rTester));
        System.out.println("***********************************************\n");

        System.out.println(rTester.getTransistions(rTester, "S"));
       
        System.out.println(rTester.isAcceptedString("010101", rTester));
    }
}
