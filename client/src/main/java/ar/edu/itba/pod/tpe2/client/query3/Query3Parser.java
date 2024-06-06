package ar.edu.itba.pod.tpe2.client.query3;


import ar.edu.itba.pod.tpe2.client.utils.parsing.BaseArguments;
import ar.edu.itba.pod.tpe2.client.utils.parsing.BaseParser;
import lombok.Getter;
import org.apache.commons.cli.*;

@Getter
public class Query3Parser extends BaseParser {
    private Query3Arguments arguments;

    @Override
    protected void addCustomOptions(Options options) {
        options.addRequiredOption("Dn", "Dn", true, "Number of top agencies");
    }

    @Override
    protected void parseCustomArguments(CommandLine cmd) throws ParseException {
        String nValue = cmd.getOptionValue("Dn");
        int n;
        try {
            n = Integer.parseInt(nValue);
        } catch (NumberFormatException e) {
            throw new ParseException("The value of Dn must be a valid integer: " + nValue);
        }
        BaseArguments baseArgs = super.getArguments();
        arguments = new Query3Arguments(baseArgs.getAddresses(), baseArgs.getCity(), baseArgs.getInPath(), baseArgs.getOutPath(), baseArgs.getClusterName(), baseArgs.getClusterPass(), n);
    }

    @Override
    public BaseArguments getArguments(String[] args) throws ParseException {
        CommandLineParser cliParser = new DefaultParser();
        Options options = super.getOptions();
        CommandLine cmd = cliParser.parse(options, args);
        super.parse(cmd);
        parseCustomArguments(cmd);
        return arguments;
    }


}
