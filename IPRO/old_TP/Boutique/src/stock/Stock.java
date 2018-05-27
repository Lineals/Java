package stock;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Stock {
	private HashMap<Article, Integer> hm = new HashMap<>();
	
	public Stock(Article art, int num) {
		hm.put(art, num);
	}
	
	public Map<Article, Integer> getLowStock(int num){
		Map<Article, Integer> article = hm.entrySet()
											 .stream()
											 .filter(entry -> entry.getValue() < num)
											 .collect(Collectors.toMap(
													 Map.Entry::getKey,
													 Map.Entry::getValue
											  ));			
		return article;
	}
	
	public int getStockByRef(String ref) {
		int n = 0;
		Article article = findByRef(ref);
		if (article != null) {
			n = hm.get(article);
		}
		return n;
	}
	
	private Article findByRef(String ref) {
		Article article = null;
		for (Article art: hm.keySet()) {
			if (art.getRef() == ref) {
				article = art;
			}
		}
		return article;
	}
	
	public void decreaseStock(String ref, int num) {
		Article article = findByRef(ref);
		if (article != null) {
			int current_num = hm.get(article);
			if (current_num > num) {
				hm.put(article, current_num - num);
			}
		}
	}
	
	public void addArticle(Article art, int num) throws InvalidParameterException{
		if (num > 0) {
			if (hm.putIfAbsent(art, num) != null) {
				hm.put(art, hm.get(art) + num);
			}
		}
		else {
			throw new InvalidParameterException();
		}
	}
	
	public boolean delByRef(String ref) {
		Boolean bool = false;
		Article article = findByRef(ref);
		if (article != null) {
			hm.remove(article);
			bool = true;
		}
		return bool;
	}
	
	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		for (java.util.Map.Entry<Article, Integer> c: hm.entrySet()) {
			Article article = c.getKey();
			sBuilder.append("Ref : " + article.getRef() + 
							" Article :" + article.toString() + 
							" Nombre : " + c.getValue() + "\n");
		}
		return sBuilder.toString();
	}
}
