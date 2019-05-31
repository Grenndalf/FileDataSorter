import java.io.*;
import java.util.Scanner;

public class ReadAndWriteFiles {

    public static void ReadAndWrite() {
        //open BufferedReader which includes list of unique points ID's
        BufferedReader fileNameReader;

        try {
            fileNameReader = new BufferedReader(new FileReader(WaterGauges.FILENAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("plik nie istnieje");
            return;
        }
        //scan BufferedReader for points ID
        Scanner fileNameScanner = new Scanner(fileNameReader);
        while (fileNameScanner.hasNextLine()) {
            String pointID = fileNameScanner.nextLine();
            //create .csv file named with point ID,
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter((pointID + ".csv"), true);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("problem ze stworzeniem pliku");
                return;
            }
            // scan every file from fileList in search of rows which includes pointID String from fileNameScanner,
            // if scanned row contains PointID add whole row to fileWriter file
            // repeat until BufferedReader ends
            for (int i = 0; i < FileService.fileList().size(); i++) {
                String fileAddress = FileService.fileList().get(i).toString();
                BufferedReader fileReader;
                try {
                    fileReader = new BufferedReader(new FileReader(fileAddress));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("plik nie istnieje");
                    return;
                }
                Scanner fileLineScanner = new Scanner(fileReader);
                while (fileLineScanner.hasNextLine()) {
                    String rowData = fileLineScanner.nextLine();
                    if (rowData.contains(pointID)) {
                        try {
                            fileWriter.append(rowData).append("\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("problem z zapisem Wiersza");
                            return;
                        }
                    }
                }
                fileLineScanner.close();
            }
            System.out.println(pointID);
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("problem z zapisem pliku");
            }
        }
        fileNameScanner.close();
    }
}

