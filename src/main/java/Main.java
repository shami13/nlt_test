import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static final String FILE_NAME = "test.in";

    public static void main(String[] args) throws ParseException {
        File file = new File(Main.class.getClassLoader().getResource(FILE_NAME).getFile());
        Path filePath = file.toPath();


        List<String> lines = new LinkedList<>();
        List<Key> result = new LinkedList<>();

        try (Stream<String> stream = Files.lines(filePath)) {

            lines = stream.collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : lines) {
            Entity entity = new Entity(line);
            checkLine(entity, result);
        }

        printResult(result);
    }

    private static void printResult(List<Key> result) {
        for (Key key : result) {
            key.print();
            System.out.println();
        }
    }

    private static void checkLine(Entity entity, List<Key> result) {
        boolean addedEntity = false;

        for (Key key : result) {
            if (key.ifIsThisCaseWillAddToBase(entity)) {
                addedEntity = true;
            }
        }

        if (!addedEntity) {
            Key key = new Key(entity);
            result.add(key);
        }
    }
}
