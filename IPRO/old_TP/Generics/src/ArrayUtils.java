
public class ArrayUtils<T> {
	
	public static <T> Pair<T> firstElements(T[] arr) {
		return new Pair<T>(arr[0], arr[1]);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T extends Comparable> Pair<T> minAndMax(T[] tab) {
		T min = tab[0];
		T max = tab[0];
		for (T elem: tab) {
			if (min.compareTo(elem) > 0) {
				min = elem;
			}
			if (max.compareTo(elem) < 0) {
				max = elem;
			}
		}
		return new Pair<T>(min, max);
	}
}
