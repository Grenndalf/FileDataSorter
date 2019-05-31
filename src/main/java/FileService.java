import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FileService {
    static List<File> fileList() {
        File folder = new File("/home/bartosz-olczak/Pulpit/Pliki_do_przerobienia");
        List<File> listOfFiles;
        listOfFiles = Arrays.asList(Objects.requireNonNull(folder.listFiles()));
        Collections.sort(listOfFiles);
        return listOfFiles;
    }
}



