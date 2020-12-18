import java.util.*;

public class DFA{
    String start;
    String[] acceptStates;   
    transistions[] arr;
    

    public DFA() {
        this.arr = new transistions[3];
        transistions holder = new transistions("S", "A", "B", false);
        this.arr[0] = holder;
        holder = new transistions("A", "A", "A", true);
        this.arr[1] = holder;
        holder = new transistions("B", "B", "B", false);
        this.arr[2] = holder;

        this.start = arr[0].state;
        this.acceptStates = new String[1];
        this.acceptStates[0] = arr[1].state;
    }

    public DFA(transistions[] arrH) {
        this.start = arrH[0].state;
        int acceptStateCounter = 0;
        for(int i = 0; i < arrH.length; i++){
            if(arrH[i].isAccept == true){
                acceptStateCounter++;
            }
        }
        this.acceptStates = new String[acceptStateCounter];
        for(int i = 0; i < arrH.length; i++){
            if(arrH[i].isAccept == true){
                this.acceptStates[acceptStateCounter-1] = arrH[i].state;
                acceptStateCounter--;
            }
        }
        this.arr = new transistions[arrH.length];
        this.arr = arrH;
    }

    public void printAcceptStates(String[] acceptStates) {
        for(int i = 0; i < acceptStates.length; i++){
            System.out.println( "\t" + acceptStates[i]);
        }
    }

    public boolean isAcceptedString(String input, DFA dfa) {
        char currentState = dfa.start.charAt(0);
        System.out.println("(" + currentState + ", lambda, " + currentState + ")");
        for(int i = 0; i < input.length(); i ++) {
            if(input.charAt(i) == '0') {
                for(int j = 0; j < dfa.arr.length; j++) {
                    if(dfa.arr[j].state.charAt(0) == currentState) {
                        System.out.println("(" + currentState + ", 0, " + dfa.arr[j].symbol0 + ")");
                        currentState = dfa.arr[j].symbol0.charAt(0);
                        break;
                    }
                }
            }
            else if(input.charAt(i) == '1'){
                for(int j = 0; j < dfa.arr.length; j++) {
                    if(dfa.arr[j].state.charAt(0) == currentState) {
                        System.out.println("(" + currentState + ", 1, " + dfa.arr[j].symbol1 + ")");
                        currentState = dfa.arr[j].symbol1.charAt(0);
                        break;
                    }
                }
            }
        }
        for(int i = 0; i < dfa.acceptStates.length; i++) {
            if(currentState == dfa.acceptStates[i].charAt(0)){
                return true;
            }
        }
        return false;
    }

    public DFA dfaTester() {
        transistions[] holderArr = new transistions[4];
        transistions holder = new transistions("S", "A", "B", true);
        holderArr[0] = holder;
        holder = new transistions("A", "C", "S", false);
        holderArr[1] = holder;

        holder = new transistions("B", "S", "C", false);
        holderArr[2] = holder;

        holder = new transistions("C", "C", "C", false);
        holderArr[3] = holder;

        DFA dfa = new DFA(holderArr);
        return dfa;
    }

    public void printDFA(DFA dfa) {
        transistions printer = new transistions("", "", "", false);
        printer.printString(dfa.arr);
    }

    public static void main(String[] args) {
        DFA test = new DFA();
        transistions printer = new transistions("", "", "", false);
        System.out.println("*******************************************************************\n");
        System.out.println("created starts with 0 DFA:");
        System.out.println("start state: " + test.start + "\naccept states: " );
        test.printAcceptStates(test.acceptStates);
        printer.printString(test.arr);
        System.out.println("*******************************************************************\n");

        String input = "101011011";
        System.out.println(input);
        System.out.println(test.isAcceptedString(input, test));
        System.out.println("*******************************************************************\n");

        input = "011110";
        System.out.println(input);
        System.out.println(test.isAcceptedString(input, test));
        System.out.println("*******************************************************************\n");


        transistions[] holderArr = new transistions[2];
        transistions holder = new transistions("S", "S", "A", true);
        holderArr[0] = holder;
        holder = new transistions("A", "S", "A", false);
        holderArr[1] = holder;
        DFA divisibleBy2 = new DFA(holderArr);

        System.out.println("created divisibleBy2 DFA:");
        System.out.println("start state: " + divisibleBy2.start + "\naccept states: " );
        test.printAcceptStates(divisibleBy2.acceptStates);
        printer.printString(divisibleBy2.arr);

        input = "011110";
        System.out.println(input);
        System.out.println(divisibleBy2.isAcceptedString(input, divisibleBy2));
        System.out.println("*******************************************************************\n");
    }


}

