package base;

import com.opencsv.CSVReader;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

public class CsvDataProviders {

    @DataProvider(name = "csvReader")
    public static Iterator<Object[]> csvReader(Method method) {
        List<Object[]> list = new ArrayList<>();
        String pathname = "src/test/resources/DataProviders/" + method.getDeclaringClass().getSimpleName() + "/"
                + method.getName() + ".csv";
        File file = new File(pathname);
        try{
            CSVReader reader = new CSVReader(new FileReader(file));
            String[] keys = reader.readNext();
            if (keys != null) {
                String[] dataCase;
                while ((dataCase = reader.readNext()) != null) {
                    Map<String, String> testData = new HashMap<>();
                    for (int i=0; i<keys.length; i++) {
                        testData.put(keys[i], dataCase[i]);
                    }
                    list.add(new Object[] {testData});
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + pathname + " was not found. " + e.getStackTrace().toString());
        } catch (IOException e) {
            throw new RuntimeException("Could not read " + pathname + " file. " + e.getStackTrace().toString());
        }
        return list.iterator();
    }
}
