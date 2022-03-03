package com.thinkit;

import com.thinkit.service.CofeeMeetingScheduler;
import com.thinkit.service.VirtualCoffeeService;
import org.apache.commons.cli.*;

import java.net.URL;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class VirtualCoffeeClient {
    public static final String START_OPTION = "s";
    public static final String END_OPTION = "e";
    public static final String OFFSET_OPTION = "o";
    public static final String NUMBER_OF_PARTICIPANTS_OPTION = "n";
    public static final String HOST_OPTION = "h";
    private static String start;
    private static String end;
    private static String offset;
    private static String nbParticipants;
    private static String host = "localhost";


    public static void main(String[] args) throws Exception {
        // create Options object
        Options options = new Options();
        initOptions(options);
        // automatically generate the help statement
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("mvn exec:java -Dexec.args=\"-s '8am' -e '8pm' -o 'gmt+1' -n '3' -h '127.0.0.1'\"", options);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        boolean error = false;
        error = validateStartOption(options, cmd, error);

        error = validateEndOption(options, cmd, error);

        error = validateOffsetOption(options, cmd, error);

        error = validateNumberParticipants(options, cmd, error);

        readHostOption(options, cmd);


        if (error) {
            return;
        }

        URL url = new URL("http://"+host+":7779/ws/coffee?wsdl");

        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://service.thinkit.com/", "VirtualCoffeeServiceImplService");
        Service service = Service.create(url, qname);
        VirtualCoffeeService virtualCoffee = service.getPort(VirtualCoffeeService.class);
        CofeeMeetingScheduler response = virtualCoffee.getGuestsAvailabilityForCoffee(start, end,
                offset, nbParticipants);
        System.out.println(parseResult(response));
    }

    private static void readHostOption(Options options, CommandLine cmd) {
        if(cmd.hasOption(HOST_OPTION)) {

            if (cmd.getOptionValue(HOST_OPTION).matches("\\d\\.\\d\\.\\d\\.\\d|.*")) {
                host = cmd.getOptionValue(HOST_OPTION);
            } else {
                // error
                System.err.println("wrong argument -" + HOST_OPTION + " " + options.getOption(HOST_OPTION).getDescription());
            }
        }
    }

    private static boolean validateNumberParticipants(Options options, CommandLine cmd, boolean error) {
        if(cmd.hasOption(NUMBER_OF_PARTICIPANTS_OPTION)) {

            if (cmd.getOptionValue(NUMBER_OF_PARTICIPANTS_OPTION).matches("[1-3]")) {
                nbParticipants = cmd.getOptionValue(NUMBER_OF_PARTICIPANTS_OPTION);
            } else {
                // error
                System.err.println("wrong argument -" + NUMBER_OF_PARTICIPANTS_OPTION + " " + options.getOption(NUMBER_OF_PARTICIPANTS_OPTION).getDescription());
                error = true;
            }
        }
        else {
            // error
            System.err.println("missing argument -" + NUMBER_OF_PARTICIPANTS_OPTION + " " + options.getOption(NUMBER_OF_PARTICIPANTS_OPTION).getDescription());
            error = true;
        }
        return error;
    }

    private static boolean validateOffsetOption(Options options, CommandLine cmd, boolean error) {
        if(cmd.hasOption(OFFSET_OPTION)) {

            if (cmd.getOptionValue(OFFSET_OPTION).matches("(((gmt|GMT)(\\+|-)(([0-2]?)[1-9]))|(gmt|GMT))")) {
                offset = cmd.getOptionValue(OFFSET_OPTION);
            } else {
                // error
                System.err.println("wrong argument -" + OFFSET_OPTION + " " + options.getOption(OFFSET_OPTION).getDescription());
                error = true;
            }
        }
        else {
            // error
            System.err.println("missing argument -" + OFFSET_OPTION + " " + options.getOption(OFFSET_OPTION).getDescription());
            error = true;
        }
        return error;
    }

    private static boolean validateEndOption(Options options, CommandLine cmd, boolean error) {
        if(cmd.hasOption(END_OPTION)) {

            if (cmd.getOptionValue(END_OPTION).matches("[0-2]?[0-9](am|pm)")) {
                end = cmd.getOptionValue(END_OPTION);
            } else {
                // error
                System.err.println("wrong argument -" + END_OPTION + " " + options.getOption(END_OPTION).getDescription());
                error = true;
            }
        }
        else {
            // error
            System.err.println("missing argument -" + END_OPTION + " " + options.getOption(END_OPTION).getDescription());
            error = true;
        }
        return error;
    }

    private static boolean validateStartOption(Options options, CommandLine cmd, boolean error) {
        if(cmd.hasOption(START_OPTION)) {

            if (cmd.getOptionValue(START_OPTION).matches("[1-2]?[0-9](am|pm)")) {
                start = cmd.getOptionValue(START_OPTION);
            } else {
                // error
                System.err.println("wrong argument -" + START_OPTION + " " + options.getOption(START_OPTION).getDescription());
                error = true;
            }
        }
        else {
            // error
            System.err.println("missing argument -" + START_OPTION + " " + options.getOption(START_OPTION).getDescription());
            error = true;
        }
        return error;
    }

    private static void initOptions(Options options) {
        // add s (start time) option
        options.addOption(START_OPTION, true, "You must provide start time like \"8am\" or \"8pm\" or any other valid time in this format");
        // add e (end time) option
        options.addOption(END_OPTION, true, "You must provide end time like \"8am\" or \"8pm\" or any other valid time in this format");
        // add o (offset) option
        options.addOption(OFFSET_OPTION, true, "You must provide offset from GMT in the format \"gmt+x\" or \"gmt-x\" where x is the offset of hours from gmt");
        // add n (number of participants) option
        options.addOption(NUMBER_OF_PARTICIPANTS_OPTION, true, "You must provide the number of participants which must be between 1 and 3");
        // add h (server ip address) option
        options.addOption(HOST_OPTION, true, "You can provide the host ip address which you will connec to or the dns name " +
                "like \"127.0.0.1\" or \"localhost\" ");
    }

    private static String parseResult(CofeeMeetingScheduler result) {
        List<Integer> slots = result.getSlots();
        List<String> participantNames = result.getParticipantNames();

        if (slots.isEmpty()) {
            return "No common slot have been found";
        }
        if (participantNames.isEmpty()) {
            return "No users have been found";
        }
        StringBuilder sb = new StringBuilder("You can have a Virtual Cofee with: "
                + String.join(",", participantNames) + "\n"
                + "at these time slots:\n");
        slots.forEach(slot -> {
            sb.append("from " + slot + " to "
                    + ((slot + 1) % 24 ) + "\n");
        });
        return sb.toString();
    }
}
