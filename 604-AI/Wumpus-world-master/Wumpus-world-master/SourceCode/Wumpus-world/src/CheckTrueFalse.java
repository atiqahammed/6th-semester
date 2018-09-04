import java.io.*;
import java.util.*;

public class CheckTrueFalse {

    public static final int TRUE = 1;
    public static final int FALSE = 0;
    public static final int UNKNOWN = -1;
    public static final int NO_OF_ROW_COLS = 5;
    public static final String UNDERSCORE = "_";
    public static final String RESULT_FILE = "result.txt";

    public static int MONSTER[][] = new int[5][5];
    public static int PIT[][] = new int[5][5];
    public static int STENCH[][] = new int[5][5];
    public static int BREEZE[][] = new int[5][5];
    public static int counter = 0;

    public static boolean isComputingNegationStatement = false;

    public static ArrayList<String> symbols = new ArrayList<String>();
    public static ArrayList<String> symbols2;
    public static HashMap<String, Boolean> model = new HashMap<String, Boolean>();

    /**
     * @param args
     */
    public static void main(String[] args) {

        if (args.length != 3) {
            // takes three arguments
            System.out.println("Usage: " + args[0] + " [wumpus-rules-file] [additional-knowledge-file] [input_file]\n");
            exit_function(0);
        }

        assign_default_symbol_values();

        // create some buffered IO streams
        String buffer;
        BufferedReader inputStream;
        BufferedWriter outputStream;

        // create the knowledge base and the statement
        LogicalExpression knowledge_base = new LogicalExpression();
        LogicalExpression statement = new LogicalExpression();
        // LogicalExpression negation_statement = new LogicalExpression();

        // open the wumpus_rules.txt
        try {
            inputStream = new BufferedReader(new FileReader(args[0]));

            // load the wumpus rules
            System.out.println("loading the wumpus rules...");
            knowledge_base.setConnective("and");

            while ((buffer = inputStream.readLine()) != null) {
                if (!(buffer.startsWith("#") || (buffer.equals("")))) {
                    // the line is not a comment
                    checkAndInitializeSymbolValue(buffer); // B_2_3 or (not M_2_3)
                    LogicalExpression subExpression = readExpression(buffer);
                    knowledge_base.setSubexpression(subExpression);
                }
            }

            // close the input file
            inputStream.close();

        } catch (Exception e) {
            System.out.println("failed to open " + args[0]);
            e.printStackTrace();
            exit_function(0);
        }
        // end reading wumpus rules

        // read the additional knowledge file
        try {
            inputStream = new BufferedReader(new FileReader(args[1]));

            // load the additional knowledge
            System.out.println("loading the additional knowledge...");

            // the connective for knowledge_base is already set. no need to set it again.
            // i might want the LogicalExpression.setConnective() method to check for that
            // knowledge_base.setConnective("and");

            while ((buffer = inputStream.readLine()) != null) {
                if (!(buffer.startsWith("#") || (buffer.equals("")))) {
                    checkAndInitializeSymbolValue(buffer); // B_2_3 or (not M_2_3)
                    LogicalExpression subExpression = readExpression(buffer);
                    knowledge_base.setSubexpression(subExpression);
                }
            }

            // close the input file
            inputStream.close();

        } catch (Exception e) {
            System.out.println("failed to open " + args[1]);
            e.printStackTrace();
            exit_function(0);
        }
        // end reading additional knowledge

        // check for a valid knowledge_base
        if (!valid_expression(knowledge_base)) {
            System.out.println("invalid knowledge base");
            exit_function(0);
        }

        // print the knowledge_base
        knowledge_base.print_expression("\n");

        // read the statement file
        try {
            inputStream = new BufferedReader(new FileReader(args[2]));

            System.out.println("\n\nLoading the statement file...");
            // actually read the statement file
            // assuming that the statement file is only one line long
            while ((buffer = inputStream.readLine()) != null) {
                if (!buffer.startsWith("#")) {
                    // the line is not a comment
                    statement = readExpression(buffer);
                    break;
                }
            }

            // close the input file
            inputStream.close();

        } catch (Exception e) {
            System.out.println("failed to open " + args[2]);
            e.printStackTrace();
            exit_function(0);
        }
        // end reading the statement file

        // check for a valid statement
        if (!valid_expression(statement)) {
            System.out.println("invalid statement");
            exit_function(0);
        }

        // print the statement
        statement.print_expression("");
        // print a new line
        System.out.println("\n");

        // print a new line
        System.out.println("\n");

        createUnknownSysmbolList();

        symbols2 = new ArrayList<String>(symbols);
        
        System.out.println("\n\nProcessing...\n\n");

        boolean tt_entails_alpha = TT_Entails(knowledge_base, statement, symbols, model);

        counter = 0;
        isComputingNegationStatement = true;

        boolean tt_entails_negation_alpha = TT_Entails(knowledge_base, statement, symbols2, model);

        isComputingNegationStatement = false;

        evaluateFinalResult(tt_entails_alpha, tt_entails_negation_alpha);

    } // end of main

    /**
     * This function implements TT_Entails() method of 'Entailment' algorithm.
     * 
     * @param knowledge_base KB in form of logical expressions
     * @param alpha statement to check for entailment (in form of logical expressions)
     * @param symbols_list
     * @param model
     * 
     * @return true if KB entails alpha, false otherwise
     * */

    private static boolean TT_Entails(LogicalExpression knowledge_base, LogicalExpression alpha,
        ArrayList<String> symbols_list, HashMap<String, Boolean> model) {
        // TODO Auto-generated method stub
        return TT_Check_All(knowledge_base, alpha, symbols_list, model);
    }

    /**
     * This function implements TT_Check_All() method of 'Entailment' algorithm. It creats possible 2^n models(row of truth
     * table) for n unknown symbols, and for each model checks weather KB and apha both satisfies or not
     * 
     * @param knowledge_base KB in form of logical expressions
     * @param alpha statement to check for entailment (in form of logical expressions)
     * @param symbols list of unknown symbols
     * @param model row of truth table(consists true or false value for each unknown symbol)
     * 
     * @return true if KB entails alpha, false otherwise
     * */
    private static boolean TT_Check_All(LogicalExpression knowledge_base, LogicalExpression alpha, ArrayList<String> symbols,
        HashMap<String, Boolean> model) {
        // TODO Auto-generated method stub

        if (symbols.isEmpty()) {
            if (PL_TRUE(knowledge_base, model, false)) {
                return PL_TRUE(alpha, model, isComputingNegationStatement);
            } else {
                return true;
            }
        } else {
            String P = symbols.remove(0);
            ArrayList<String> rest = new ArrayList<String>(symbols);

            return TT_Check_All(knowledge_base, alpha, rest, EXTENDS(P, true, model))
                && TT_Check_All(knowledge_base, alpha, rest, EXTENDS(P, false, model));
        }
    }

    /**
     * This function implements ETENDS() method of 'Entailment' algorithm. it assigns true or false value to given symbol
     * 
     * @param P unique symbol
     * @param value true or false value
     * @param model row of truth table(consists true or false value for each unknown symbol)
     * 
     * */
    private static HashMap<String, Boolean> EXTENDS(String P, boolean value, HashMap<String, Boolean> model) {
        // TODO Auto-generated method stub
        model.put(P, value);
        return model;
    }

    /**
     * This function implements PL_TRUE() method of 'Entailment' algorithm. It checks weather given logical statement is true
     * for given model
     * 
     * @param logical_statement KB or statement, in form of logical expressions
     * @param model row of truth table(consists true or false value for each unknown symbol)
     * @param isComputingNegationStatement to check weather statement under evaluation is negation of actual statement or not
     * 
     * @return true if statement evaluates to 'true' for given model, false otherwise
     * */
    private static boolean PL_TRUE(LogicalExpression logical_statement, HashMap<String, Boolean> model,
        boolean isComputingNegationStatement) {
        // TODO Auto-generated method stub

        boolean result = logical_statement.solve_expressions(model);

        LogicalExpression.clearStack();

        if (isComputingNegationStatement) {
            return !result;
        } else {
            return result;
        }
    }

    /**
     * This function creates a list of unknown symbols It checks whether symbol already has value assigned or not, if not then
     * add that symbol to the list of unknown symbol for further use.
     * 
     * This method executes after checkAndInitializeSymbolValue(). see also checkAndInitializeSymbolValue()
     * */

    private static void createUnknownSysmbolList() {
        // TODO Auto-generated method stub

        for (int i = 1; i < NO_OF_ROW_COLS; i++) {
            for (int j = 1; j < NO_OF_ROW_COLS; j++) {
                if (MONSTER[i][j] == UNKNOWN) {
                    symbols.add("M_" + i + "_" + j);
                    model.put("M_" + i + "_" + j, false);
                }
            }
        }
        for (int i = 1; i < NO_OF_ROW_COLS; i++) {
            for (int j = 1; j < NO_OF_ROW_COLS; j++) {
                if (PIT[i][j] == UNKNOWN) {
                    symbols.add("P_" + i + "_" + j);
                    model.put("P_" + i + "_" + j, false);
                }
            }
        }
        for (int i = 1; i < NO_OF_ROW_COLS; i++) {
            for (int j = 1; j < NO_OF_ROW_COLS; j++) {
                if (STENCH[i][j] == UNKNOWN) {
                    symbols.add("S_" + i + "_" + j);
                    model.put("S_" + i + "_" + j, false);
                }
            }
        }
        for (int i = 1; i < NO_OF_ROW_COLS; i++) {
            for (int j = 1; j < NO_OF_ROW_COLS; j++) {
                if (BREEZE[i][j] == UNKNOWN) {
                    symbols.add("B_" + i + "_" + j);
                    model.put("B_" + i + "_" + j, false);
                }
            }
        }
    }

    /**
     * This function initialize all symbols to default unknown value -1.
     * */
    private static void assign_default_symbol_values() {

        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                MONSTER[i][j] = UNKNOWN;
            }
        }
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                PIT[i][j] = UNKNOWN;
            }
        }
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                STENCH[i][j] = UNKNOWN;
            }
        }
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                BREEZE[i][j] = UNKNOWN;
            }
        }
    }

    /**
     * This function prints values for all symbols. Use this only for testing
     * */
    private static void print_symbol_values() {

        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                System.out.println("M_" + i + "_" + j + " = " + MONSTER[i][j]);
            }
        }
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                System.out.println("P_" + i + "_" + j + " = " + PIT[i][j]);
            }
        }
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                System.out.println("S_" + i + "_" + j + " = " + STENCH[i][j]);
            }
        }
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                System.out.println("B_" + i + "_" + j + " = " + BREEZE[i][j]);
            }
        }
    }

    /**
     * This method checks for KB lines like 'B_2_3' or '(not M_2_3)' in order to initialize true or false values for symbols
     * 
     * For example: for line 'B_2_3' -> B[2][3] will be initialized to 'true' for line '(not M_2_3)' -> M[2][3] will be
     * initialized to 'false'
     * 
     * @param line read line from knowledge file
     */
    private static void checkAndInitializeSymbolValue(String line) {
        // TODO Auto-generated method stub
        int values_to_assign = TRUE;
        String symbol = line;
        String symbol_initials = null;
        String[] symbol_literals = new String[3];

        if (!line.startsWith("(")) { // checks if line contains only unique symbol i.e 'B_2_3'
            values_to_assign = TRUE;
        } else if ((line.startsWith("(not") || line.startsWith("(NOT"))
            && !(line.startsWith("(not (") || line.startsWith("(NOT ("))) { // checks if line contains only negation of symbol
                                                                            // i.e '(not M_2_3)'
            values_to_assign = FALSE;
            symbol = line.substring(line.indexOf(" ") + 1, line.indexOf(")"));
        } else {
            return;
        }

        symbol_literals = symbol.split(UNDERSCORE);
        symbol_initials = symbol_literals[0];
        int pos_x = Integer.parseInt(symbol_literals[1]);
        int pos_y = Integer.parseInt(symbol_literals[2]);

        if (symbol_initials.equals("M")) {
            MONSTER[pos_x][pos_y] = values_to_assign;
        } else if (symbol_initials.equals("P")) {
            PIT[pos_x][pos_y] = values_to_assign;
        } else if (symbol_initials.equals("S")) {
            STENCH[pos_x][pos_y] = values_to_assign;
        } else if (symbol_initials.equals("B")) {
            BREEZE[pos_x][pos_y] = values_to_assign;
        } else {
            System.out.println("Oops...Incorrect knowlwdge base format!!");
        }

    }

    /**
     * This function retrieve values for given symbol.
     * 
     * @param symbol symbol to find value for
     * 
     * @return true or false
     * */
    public static boolean getValueFromArray(String symbol) {
        // TODO Auto-generated method stub

        String symbol_initials = null;
        String[] symbol_literals = new String[3];

        symbol_literals = symbol.split(UNDERSCORE);
        symbol_initials = symbol_literals[0];
        int pos_x = Integer.parseInt(symbol_literals[1]);
        int pos_y = Integer.parseInt(symbol_literals[2]);

        if (symbol_initials.equals("M")) {
            if (MONSTER[pos_x][pos_y] == TRUE) {
                return true;
            } else {
                return false;
            }
        } else if (symbol_initials.equals("P")) {
            if (PIT[pos_x][pos_y] == TRUE) {
                return true;
            } else {
                return false;
            }
        } else if (symbol_initials.equals("S")) {
            if (STENCH[pos_x][pos_y] == TRUE) {
                return true;
            } else {
                return false;
            }
        } else if (symbol_initials.equals("B")) {
            if (BREEZE[pos_x][pos_y] == TRUE) {
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("Oops...Incorrect Symbol format!!");
        }
        return false;
    }

    /*
     * this method reads logical expressions if the next string is a: - '(' => then the next 'symbol' is a subexpression - else
     * => it must be a unique_symbol
     * 
     * it returns a logical expression
     * 
     * notes: i'm not sure that I need the counter
     */
    public static LogicalExpression readExpression(String input_string) {
        LogicalExpression result = new LogicalExpression();

        // trim the whitespace off
        input_string = input_string.trim();

        if (input_string.startsWith("(")) {
            // its a subexpression

            String symbolString = "";

            // remove the '(' from the input string
            symbolString = input_string.substring(1);

            if (!symbolString.endsWith(")")) {
                // missing the closing paren - invalid expression
                System.out.println("missing ')' !!! - invalid expression! - readExpression():-" + symbolString);
                exit_function(0);

            } else {
                // remove the last ')'
                // it should be at the end
                symbolString = symbolString.substring(0, (symbolString.length() - 1));
                symbolString.trim();

                // read the connective into the result LogicalExpression object
                symbolString = result.setConnective(symbolString);
            }

            // read the subexpressions into a vector and call setSubExpressions( Vector );
            result.setSubexpressions(read_subexpressions(symbolString));

        } else {
            // the next symbol must be a unique symbol
            // if the unique symbol is not valid, the setUniqueSymbol will tell us.
            result.setUniqueSymbol(input_string);
        }

        return result;
    }

    /*
     * this method reads in all of the unique symbols of a subexpression the only place it is called is by
     * read_expression(String, long)(( the only read_expression that actually does something ));
     * 
     * each string is EITHER: - a unique Symbol - a subexpression - Delineated by spaces, and paren pairs
     * 
     * it returns a vector of logicalExpressions
     */

    public static Vector<LogicalExpression> read_subexpressions(String input_string) {

        Vector<LogicalExpression> symbolList = new Vector<LogicalExpression>();
        LogicalExpression newExpression;// = new LogicalExpression();
        String newSymbol = new String();

        input_string.trim();

        while (input_string.length() > 0) {

            newExpression = new LogicalExpression();

            if (input_string.startsWith("(")) {
                // its a subexpression.
                // have readExpression parse it into a LogicalExpression object
                // find the matching ')'
                int parenCounter = 1;
                int matchingIndex = 1;
                while ((parenCounter > 0) && (matchingIndex < input_string.length())) {
                    if (input_string.charAt(matchingIndex) == '(') {
                        parenCounter++;
                    } else if (input_string.charAt(matchingIndex) == ')') {
                        parenCounter--;
                    }
                    matchingIndex++;
                }

                // read untill the matching ')' into a new string
                newSymbol = input_string.substring(0, matchingIndex);

                // pass that string to readExpression,
                newExpression = readExpression(newSymbol);

                // add the LogicalExpression that it returns to the vector symbolList
                symbolList.add(newExpression);

                // trim the logicalExpression from the input_string for further processing
                input_string = input_string.substring(newSymbol.length(), input_string.length());

            } else {
                // its a unique symbol ( if its not, setUniqueSymbol() will tell us )
                // I only want the first symbol, so, create a LogicalExpression object and
                // add the object to the vector

                if (input_string.contains(" ")) {
                    // remove the first string from the string
                    newSymbol = input_string.substring(0, input_string.indexOf(" "));
                    input_string = input_string.substring((newSymbol.length() + 1), input_string.length());
                } else {
                    newSymbol = input_string;
                    input_string = "";
                }
                newExpression.setUniqueSymbol(newSymbol);
                symbolList.add(newExpression);
            }

            input_string.trim();

            if (input_string.startsWith(" ")) {
                // remove the leading whitespace
                input_string = input_string.substring(1);
            }
        }
        return symbolList;
    }

    /*
     * this method checks to see if a logical expression is valid or not a valid expression either: ( this is an XOR ) - is a
     * unique_symbol - has: -- a connective -- a vector of logical expressions
     */
    public static boolean valid_expression(LogicalExpression expression) {

        // checks for an empty symbol
        // if symbol is not empty, check the symbol and
        // return the truthiness of the validity of that symbol

        if (!(expression.getUniqueSymbol() == null) && (expression.getConnective() == null)) {
            // we have a unique symbol, check to see if its valid
            return valid_symbol(expression.getUniqueSymbol());
        }

        // symbol is empty, so
        // check to make sure the connective is valid

        // check for 'if / iff'
        if ((expression.getConnective().equalsIgnoreCase("if")) || (expression.getConnective().equalsIgnoreCase("iff"))) {

            // the connective is either 'if' or 'iff' - so check the number of connectives
            if (expression.getSubexpressions().size() != 2) {
                System.out.println("error: connective \"" + expression.getConnective() + "\" with "
                    + expression.getSubexpressions().size() + " arguments\n");
                return false;
            }
        }
        // end 'if / iff' check

        // check for 'not'
        else if (expression.getConnective().equalsIgnoreCase("not")) {
            // the connective is NOT - there can be only one symbol / subexpression
            if (expression.getSubexpressions().size() != 1) {
                System.out.println("error: connective \"" + expression.getConnective() + "\" with "
                    + expression.getSubexpressions().size() + " arguments\n");
                return false;
            }
        }
        // end check for 'not'

        // check for 'and / or / xor'
        else if ((!expression.getConnective().equalsIgnoreCase("and")) && (!expression.getConnective().equalsIgnoreCase("or"))
            && (!expression.getConnective().equalsIgnoreCase("xor"))) {
            System.out.println("error: unknown connective " + expression.getConnective() + "\n");
            return false;
        }
        // end check for 'and / or / not'
        // end connective check

        // checks for validity of the logical_expression 'symbols' that go with the connective
        for (Enumeration e = expression.getSubexpressions().elements(); e.hasMoreElements();) {
            LogicalExpression testExpression = (LogicalExpression) e.nextElement();

            // for each subExpression in expression,
            // check to see if the subexpression is valid
            if (!valid_expression(testExpression)) {
                return false;
            }
        }
        // if the method made it here, the expression must be valid
        return true;
    }

    /** this function checks to see if a unique symbol is valid */
    // ////////////////// this function should be done and complete
    // originally returned a data type of long.
    // I think this needs to return true /false
    // public long valid_symbol( String symbol ) {
    public static boolean valid_symbol(String symbol) {
        if (symbol == null || (symbol.length() == 0)) {
            return false;
        }

        for (int counter = 0; counter < symbol.length(); counter++) {
            if ((symbol.charAt(counter) != '_') && (!Character.isLetterOrDigit(symbol.charAt(counter)))) {

                System.out.println("String: " + symbol + " is invalid! Offending character:---" + symbol.charAt(counter)
                    + "---\n");

                return false;
            }
        }

        // the characters of the symbol string are either a letter or a digit or an underscore,
        // return true
        return true;
    }

    /**
     * This function evaluates final decision of about intailment.
     * 
     * @param tt_entails_alpha indicates wether KB intails statement or not
     * @param tt_entails_negation_alpha indicates wether KB intails negation of statement or not
     * 
     * */
    private static void evaluateFinalResult(boolean tt_entails_alpha, boolean tt_entails_negation_alpha) {
        // TODO Auto-generated method stub
        String decision = "I don't know if the statement is definitely true or definitely false.";
        if (tt_entails_alpha && !tt_entails_negation_alpha) {
            decision = "definitely true";
        } else if (!tt_entails_alpha && tt_entails_negation_alpha) {
            decision = "definitely false";
        } else if (!tt_entails_alpha && !tt_entails_negation_alpha) {
            decision = "possibly true, possibly false";
        } else if (tt_entails_alpha && tt_entails_negation_alpha) {
            decision = "both true and false";
        }

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("                     FINAL RESULT = " + decision + "                                   ");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("\n\n");

        printDecisionToFile(decision, RESULT_FILE);

    }

    /**
     * This function write entailment decision to text file.
     * 
     * @param decision final entailment decision
     * @param resultFile file name to write decision in to
     * 
     * */
    public static void printDecisionToFile(String decision, String resultFile) {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(resultFile));

            // write the current turn
            output.write(decision + "\r\n");
            output.close();

        } catch (IOException e) {
            System.out.println("\nProblem writing to the result file!\n" + "Try again.");
            e.printStackTrace();
        }
    }

    private static void exit_function(int value) {
        System.out.println("exiting from checkTrueFalse");
        System.exit(value);
    }
}
