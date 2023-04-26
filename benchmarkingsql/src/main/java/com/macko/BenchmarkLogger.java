package com.macko;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * This class logs the results of benchmarking into a file designated as benchmark.txt found in the /benchmarking_results directory.
 * If this file already exists, it countinues to write the results into the said file. 
 */
public class BenchmarkLogger {
    
    private static final String FILE_NAME = "results.txt";
    public static void writeResult(String methodName, long totalTime) {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file, true);
            writer.write(methodName + " | " + totalTime + " ms\n");
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file");
            e.printStackTrace();
        }
    }
}
