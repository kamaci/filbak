package com.kamaci.filbak;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Furkan KAMACI
 */
public final class ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationRunner.class);

    /**
     * Private constructor to prevent instantiation
     */
    private ApplicationRunner() {
    }

    /**
     * Main class for application
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Options options = new Options();

        options.addOption("t", "threadCounts", true, "comma separated thread count list");
        options.addOption("m", "maxNumber", true, "loop up to that maximum number");
        options.addOption("r", "retryCount", true, "number of retry count to test each thread");
        options.addOption("a", "about", false, "get info about application");

        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar filbak-{version}.jar -t [THREAD LIST] -m [MAX ITERATION NUMBER] -r [RETRY COUNT] ",
                "----------------------------------", options, "----------------------------------");

        // create the parser
        CommandLineParser parser = new BasicParser();

        // parse the command line arguments
        CommandLine commandLine;

        int maxNumber = 0;
        int threadCounts[] = null;
        int retryCount = 0;

        try {
            commandLine = parser.parse(options, args);
            if (commandLine.hasOption("m")) {
                maxNumber = Integer.parseInt(commandLine.getOptionValue("m"));
            }

            if (commandLine.hasOption("t")) {
                String[] threadCountStrArray = commandLine.getOptionValue("t").split(",");
                threadCounts = new int[threadCountStrArray.length];
                for (int i = 0; i < threadCountStrArray.length; i++) {
                    int threadCount = Integer.parseInt(threadCountStrArray[i]);
                    if (threadCount <= 0) {
                        LOGGER.error("Use a thread number greater than 0!");
                        throw new ParseException("Use a thread number greater than 0!");
                    }
                    threadCounts[i] = threadCount;
                }
            }

            if (commandLine.hasOption("r")) {
                retryCount = Integer.parseInt(commandLine.getOptionValue("r"));
            }

            if (commandLine.hasOption("a")) {
                LOGGER.info("This application is developed by Furkan KAMACI.");
            }

            if (maxNumber <= 0) {
                maxNumber = AlgorithmRunner.DEFAULT_MAX_NUMBER;
            }


            if (threadCounts == null) {
                threadCounts = AlgorithmRunner.DEFAULT_THREAD_COUNTS;
            }

            if (retryCount <= 0) {
                retryCount = AlgorithmRunner.DEFAULT_RETRY_COUNT;
            }

            LOGGER.info("Algorithm parameters:");

            StringBuilder threadCountsString = new StringBuilder("[ ");
            for (int threadCount : threadCounts) {
                threadCountsString.append(threadCount).append(" ");
            }
            threadCountsString.append("]");

            LOGGER.info("Thread Counts:" + threadCountsString);
            LOGGER.info("Max Number:" + maxNumber);
            LOGGER.info("Retry Count:" + retryCount);

            AlgorithmRunner algorithmRunner = new AlgorithmRunner(threadCounts, maxNumber, retryCount);

            algorithmRunner.runStatistics();

        } catch (ParseException e) {
            LOGGER.error("Check how to run application!");
        } catch (InterruptedException e) {
            LOGGER.error("InterruptedException occurred!");
        }
    }

}
