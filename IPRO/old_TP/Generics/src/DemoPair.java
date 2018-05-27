public class DemoPair {
  public static void main(String[] args) {
    Integer[] tab = {1, 2, 3};
    Pair<Integer> firsts = ArrayUtils.firstElements(tab);
    System.out.println(firsts.toString());
    String[] phrase = {"marie", "possède", "une", "petite", "lampe"};
    Pair<String> extr = ArrayUtils.minAndMax(phrase);
    System.out.println(extr.toString());
  }
}