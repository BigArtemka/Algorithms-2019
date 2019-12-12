package lesson6;

import kotlin.NotImplementedError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.max;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     *
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    /*
    Трудоёмкость O(m*n), где m, n - длины первого и второго слова соотв.
    Ресурсоёмкость O(m*n)
     */
    public static String longestCommonSubSequence(String first, String second) {
        int fl = first.length();
        int sl = second.length();
        int[][] lcs = new int[fl + 1][sl + 1];
        StringBuilder result = new StringBuilder();

        for (int i = fl - 1; i >= 0; i--) {
            for (int j = sl - 1; j >= 0; j--) {
                if (first.charAt(i) == second.charAt(j)) {
                    lcs[i][j] = lcs[i + 1][j + 1] + 1;
                } else {
                    lcs[i][j] = max(lcs[i + 1][j], lcs[i][j + 1]);
                }
            }
        }

        int i = 0, j = 0;
        while (lcs[i][j] != 0 && i < fl && j < sl) {
            if (first.charAt(i) == second.charAt(j)) {
                result.append(first.charAt(i));
                i++;
                j++;
            } else {
                if (lcs[i][j] == lcs[i + 1][j])
                    i++;
                else
                    j++;
            }
        }


        return result.toString();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     *
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    /*
        Трудоёмкость O(n^2) - где n - количество элмементов в списке
        Ресурсоёмкость O(n)
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
            if (list.isEmpty()) return new ArrayList<>();
            int[] l = new int[list.size()];
            int[] prev = new int[list.size()];
            int max = 0;

            for (int i = 0; i < list.size(); i++) {
                l[i] = 1;
                prev[i] = -1;
                for (int j = 0; j < i; j++) {
                    if (list.get(j) < list.get(i))
                        if (l[j] + 1 > l[i]) {
                            l[i] = l[j] + 1;
                            prev[i] = j;
                        }
                }
                if (l[i] > l[max]) max = i;
            }

            List<Integer> res = new ArrayList<>();
            while (max != -1) {
                res.add(list.get(max));
                max = prev[max];
            }

            Collections.reverse(res);
            return res;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     *
     * В файле с именем inputName задано прямоугольное поле:
     *
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     *
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     *
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
