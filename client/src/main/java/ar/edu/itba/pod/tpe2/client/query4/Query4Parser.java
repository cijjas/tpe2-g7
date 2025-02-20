package ar.edu.itba.pod.tpe2.client.query4;

import ar.edu.itba.pod.tpe2.client.utils.cli_parsing.BaseArguments;
import ar.edu.itba.pod.tpe2.client.utils.cli_parsing.BaseParser;
import lombok.Getter;
import org.apache.commons.cli.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Getter
public class Query4Parser extends BaseParser {
    private Query4Arguments arguments;

    @Override
    public Options getOptions() {
        Options options = super.getOptions();
        addCustomOptions(options);
        return options;
    }

    private void addCustomOptions(Options options) {
        options.addRequiredOption("Dfrom", "Dfrom", true, "From date");
        options.addRequiredOption("Dto", "Dto", true, "To date");
    }


    @Override
    public void parse(CommandLine cmd) throws ParseException {
        super.parse(cmd);
        parseCustomArguments(cmd);
    }

    private void parseCustomArguments(CommandLine cmd) throws ParseException {
        String from = cmd.getOptionValue("Dfrom");
        String to = cmd.getOptionValue("Dto");

        LocalDate fromDate = validateAndParseDate(from, "Dfrom");
        LocalDate toDate = validateAndParseDate(to, "Dto");

        arguments = new Query4Arguments(super.getArguments().getAddresses(), super.getArguments().getCity(), super.getArguments().getInPath(), super.getArguments().getOutPath(), super.getArguments().getClusterName(), super.getArguments().getClusterPass(), fromDate, toDate);
    }

    private LocalDate validateAndParseDate(String dateStr, String dateType) throws ParseException {
        if (dateStr == null || dateStr.isEmpty()) {
            throw new ParseException(dateType + " must not be empty");
        }
        if (!dateStr.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
            throw new ParseException("Invalid date format for " + dateType + ". Expected format is dd/MM/yyyy");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            throw new ParseException("Invalid date for " + dateType + ". Expected format is dd/MM/yyyy");
        }
    }

    @Override
    public BaseArguments getArguments(String[] args) throws ParseException {
        CommandLineParser cliParser = new DefaultParser();
        Options options = getOptions();
        CommandLine cmd = cliParser.parse(options, args);
        parse(cmd);
        return arguments;
    }
}
