package Pattern;

public class Patterns {

    // Names
    public static final String namesPattern = "[a-zA-Z$_][a-zA-Z0-9$_]*";

    // Numbers
    public static final String numbersPattern = "([0-9]+|[0-9]+[.][0-9]+)";

    // print sum
    // print 2
    // print 2.9
    public static final String printPattern = "print[ ]([a-zA-Z$_][a-zA-Z0-9$_]*|[0-9]+|[0-9]+[.][0-9]+)";

    // for 100
    public static final String forStartingPattern = "for[ ][0-9]+";

    // end for
    public static final String forEndingPattern = "end[ ]for";

    // sum = x
    // sum = 2
    // sum = 2.8
    // sum = x + y
    // sum = x + 2
    // sum = 2 + x
    // sum = 7 + 8
    // sum = 7.8 + 2.1
    // sum = x + 2.7
    // sum = 2.3 + x
    public static final String commandsPattern = "[a-zA-Z$_][a-zA-Z0-9$_]*[ ]{0,1}[=][ ]{0,1}([a-zA-Z$_][a-zA-Z0-9$_]*|[0-9]+|[0-9]+[.][0-9]+)[ ]{0,1}[+-/*]{0,1}[ ]{0,1}([a-zA-Z$_][a-zA-Z0-9$_]*|[0-9]+|[0-9]+[.][0-9]+)*";

    // int x = 2
    // int x
    // float x = 2
    // float x
    public static final String variableDefinitionPattern = "(int|float)[ ]+[a-zA-Z$_][a-zA-Z0-9$_]*[ ]{0,1}[=]{0,1}[ ]{0,1}([0-9]+|[0-9]+[.][0-9]+)*";
}