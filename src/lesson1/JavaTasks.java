package lesson1;

import kotlin.NotImplementedError;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     *
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     *
     * Пример:
     *
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     *
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     *
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputName));
        ArrayList<String> am = new ArrayList<>();
        ArrayList<String> pm = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            if (line.matches("((0[1-9])|(1[0-2])):[0-5][0-9]:[0-5][0-9] (AM|PM)")) {
                if (line.startsWith("12")) {
                    line = "00" + line.substring(2);
                }
                if (line.endsWith("AM")) am.add(line);
                else pm.add(line);
            }
            else throw new IllegalArgumentException("Неверный формат данных");
            line = reader.readLine();
        }
        reader.close();
        Collections.sort(am);
        Collections.sort(pm);
        am.addAll(pm);
        PrintWriter writer = new PrintWriter(outputName);
        for (String s : am) {
            if (s.startsWith("00"))
                s = "12" + s.substring(2);
            writer.println(s);
        }
        writer.close();
    }

    /**
     * Сортировка адресов
     *
     * Средняя
     *
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     *
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     *
     * Людей в городе может быть до миллиона.
     *
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     *
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputName));
        TreeMap<String, TreeSet<String>> result = new TreeMap<>();
        String line = reader.readLine();
        while (line != null) {
            Matcher m = Pattern.compile("^([A-zёЁА-я-]+ [A-zёЁА-я-]+) - ([A-zёЁА-я-]+ [0-9][0-9]*)$").matcher(line);
            if (m.matches()) {
                StringBuilder s = new StringBuilder(m.group(2));
                if (s.charAt(s.length() - 2) == ' ') s.insert(s.length() - 1, '0');
                String str = s.toString();
                if (result.get(str) != null)
                    result.get(str).add(m.group(1));
                else {
                    TreeSet<String> set = new TreeSet<>();
                    set.add(m.group(1));
                    result.put(str, set);
                }
            } else throw new IllegalArgumentException("Неверный формат данных");
            line = reader.readLine();
        }
        reader.close();
        PrintWriter writer = new PrintWriter(new File(outputName));
        for (Map.Entry<String, TreeSet<String>> entry : result.entrySet()) {
            {
                StringBuilder s = new StringBuilder(entry.getKey());
                if (s.charAt(s.length() - 2) == '0') s.deleteCharAt(s.length() - 2);
                s.append(" - ");
                for (String str: entry.getValue())
                    s.append(str).append(", ");
                writer.println(s.substring(0, s.length() - 2));
            }
        }
        writer.close();
    }

    /**
     * Сортировка температур
     *
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     *
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     *
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     *
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputName));
        TreeMap<Short, Integer> result = new TreeMap<>();
        String line = reader.readLine();
        final short min_t = -2730;
        final short max_t = 5000;
        while (line != null) {
            short n = (short) (Float.parseFloat(line)*10);
            if (n >= min_t && n <= max_t)
                result.merge(n, 1, (a, b) -> a + b);
            else throw new IllegalArgumentException("Неверные входные данные");
            line = reader.readLine();
        }
        reader.close();
        PrintWriter writer = new PrintWriter(new File(outputName));
        for (Map.Entry<Short, Integer> entry : result.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                float n = (float) entry.getKey() / 10;
                writer.println(n);
            }
        }
        writer.close();
    }


    /**
     * Сортировка последовательности
     *
     * Средняя
     * (Задача взята с сайта acmp.ru)
     *
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     *
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     *
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     *
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Соединить два отсортированных массива в один
     *
     * Простая
     *
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     *
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     *
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
