import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class WaterGauges {
    final static String FILENAME = "wodowskazy_lista_ID.txt";

    public static Set<String> waterGaugeList() {

        String fileAddress;
        BufferedReader br = null;
        Scanner scan;
        Set<String> waterGaugeList = new HashSet<>();
        // for every single address in fileList, scan this file and get list of unique waterGauge points ID's from all of them
        for (int i = 0; i < FileService.fileList().size(); i++) {
            fileAddress = FileService.fileList().get(i).toString();
            try {
                br = new BufferedReader(new FileReader(fileAddress));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("nie odnaleziono pliku");
            }
            scan = new Scanner(br);
            while (scan.hasNextLine()) {
                waterGaugeList.add(scan.nextLine().split(",")[0].replace("\"", ""));
            }
            scan.close();
        }
        return new TreeSet<>(waterGaugeList);
    }
    static void getWaterGaugeFileList() {
        try {
            Files.write(Paths.get(FILENAME), waterGaugeList());
        } catch (IOException ex) {
            System.out.println("Nie mo¿na zapisaæ pliku.");
        }
    }
}