package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     * <p>
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     * <p>
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     * <p>
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     * <p>
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 5
     * <p>
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 х
     * <p>
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 х 3
     * 8   4
     * 7 6 Х
     * <p>
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     * <p>
     * 1 Х 3
     * х   4
     * 7 6 Х
     * <p>
     * 1 Х 3
     * Х   4
     * х 6 Х
     * <p>
     * х Х 3
     * Х   4
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   х
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   Х
     * Х х Х
     * <p>
     * Общий комментарий: решение из Википедии для этой задачи принимается,
     * но приветствуется попытка решить её самостоятельно.
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     * <p>
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    static public String longestCommonSubstring(String firs, String second) {
        int[][] arr = new int[firs.length()][second.length()];
        int maxX = 0, maxY = 0;
        for (int i = 0; i < firs.length(); i++) {
            for (int j = 0; j < second.length(); j++) {
                if (firs.charAt(i) == second.charAt(j)) {
                    if (i > 0 && j > 0)
                        arr[i][j] = arr[i - 1][j - 1] + 1;
                    else arr[i][j] = 1;
                    if (arr[i][j] > arr[maxX][maxY]) {
                        maxX = i;
                        maxY = j;
                    }
                } else arr[i][j] = 0;
            }
        }
        return firs.substring(maxX - arr[maxX][maxY] + 1, maxX + 1);
    }

    /**
     * Число простых чисел в интервале
     * Простая
     * <p>
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     * <p>
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        throw new NotImplementedError();
    }

    /**
     * Балда
     * Сложная
     * <p>
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     * <p>
     * И Т Ы Н
     * К Р А Н
     * А К В А
     * <p>
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     * <p>
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     * <p>
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     * <p>
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */
    static public Set<String> baldaSearcher(String inputName, Set<String> words) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputName));
        String str = reader.readLine();
        List<List<Character>> balda = new ArrayList<>();
        int count = 0;
        while (str != null) {
            String[] arr = str.split(" ");
            balda.add(new ArrayList<>());
            for (String it : arr)
                if (it.matches("[A-ZА-Я]"))
                    balda.get(count).add(it.charAt(0));
                else throw new IllegalArgumentException("Неверный формат данных");
            str = reader.readLine();
            count++;
        }
        Set<String> result = new HashSet<>();
        words.forEach(word -> {
            for (int i = 0; i < balda.size(); ++i) {
                for (int j = 0; j < balda.get(i).size(); ++j) {
                    if (tryNext(balda, word, 0, i, j)) {
                        result.add(word);
                        return;
                    }
                }
            }
        });
        return result;
    }

    static private boolean tryNext(List<List<Character>> balda, String word, int index, int i, int j) {
        if (index == word.length()) return true;
        char current = word.charAt(index);
        if (balda.get(i).get(j) != current) return false;
        else {
            boolean top = false, right = false,
                    bot = false, left = false;
            int countCols = balda.get(i).size();
            int countRows = balda.size();
            if (i < countRows - 1) {
                top = tryNext(balda, word, index + 1, i + 1, j);
            }
            if (j < countCols - 1) {
                right = tryNext(balda, word, index + 1, i, j + 1);
            }
            if (i > 0) {
                bot = tryNext(balda, word, index + 1, i - 1, j);
            }
            if (j > 0) {
                left = tryNext(balda, word, index + 1, i, j - 1);
            }
            return left || top || right || bot;
        }
    }
}