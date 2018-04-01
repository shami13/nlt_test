import java.util.*;

public class Key {
    private String[] key;
    private Integer changedWordPlace = null;
    private List<String> uniqueWords = new LinkedList<>();
    private List<Entity> cases = new LinkedList<>();

    public String[] getKey() {
        return key;
    }

    public void setKey(String[] key) {
        this.key = key;
    }

    public Integer getChangedWordPlace() {
        return changedWordPlace;
    }

    public void setChangedWordPlace(Integer changedWordPlace) {
        this.changedWordPlace = changedWordPlace;
    }

    public Key(Entity entity) {
        this.key = entity.getWords();
        this.cases.add(entity);
    }

    public List<Entity> getCases() {
        return cases;
    }

    public void setCases(List<Entity> cases) {
        this.cases = cases;
    }

    public boolean ifIsThisCaseWillAddToBase(Entity entity) {
        if (entity.getLength() != key.length) {
            return false;
        }
        if (changedWordPlace == null) {
            Integer possiblePlaceForChangedWord = null;
            for (int i = 0; i < key.length; i++) {
                if (!entity.getWords()[i].equals(key[i])) {
                    if (possiblePlaceForChangedWord == null) {
                        possiblePlaceForChangedWord = i;
                    } else {
                        return false;
                    }
                }
            }
            if (possiblePlaceForChangedWord != null) {
                changedWordPlace = possiblePlaceForChangedWord;
                addUniqueWord(key[changedWordPlace]);
            }
        } else {
            for (int i = 0; i < key.length; i++) {
                if (!entity.getWords()[i].equals(key[i]) && i != changedWordPlace) {
                    return false;
                }
            }
        }
        if (changedWordPlace != null) {
            uniqueWords.add(entity.getWord(changedWordPlace));
        }
        cases.add(entity);
        return true;
    }

    private void addUniqueWord(String word) {
        if (!uniqueWords.contains(word)) {
            uniqueWords.add(word);
        }
    }

    public void sortCases() {
        cases.sort(Comparator.comparing(Entity::getDate));
    }

    public void printCases() {
        for (Entity entity: cases) {
            System.out.println(entity.getValue());
        }
    }

    public void printUniqueWords() {
        StringBuilder builder = new StringBuilder("The changing word was: ");

        for (int i = 0; i < uniqueWords.size(); i ++) {
            String word = uniqueWords.get(i);
            builder.append(word);

            if (i < uniqueWords.size() - 1) {
                builder.append(", ");
            }
        }

        System.out.println(builder.toString());
    }

    public void print() {
        sortCases();
        printCases();
        printUniqueWords();
    }
}
