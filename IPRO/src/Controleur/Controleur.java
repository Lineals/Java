package Controleur;

import Metier.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Controleur {
    public static String afficherClients() {
        String res="";
        for (Client cl: Boutique.getInstance().clients) {

            res+=cl.toString()+"\n";
        }
        return res;
    }
    public static String afficherCommandes(){
        String res="";
        for (Commande com : Boutique.getInstance().commandes){
            res+=com.toString()+"\n";
        }
        return res;
    }
    public static String afficherStock(){
        String res = "";
        HashMap<Article,Integer> stock= Boutique.getInstance().stock;
        Iterator it =  stock.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            res+=(pair.getKey()+" = "+pair.getValue());
            it.remove();
        }
        return res;
    }
    public static String afficherCommandesClient(int idClient){
        String res="";
        Client cli = getClientById(idClient);
        for(Commande com : cli.getCommandes()){
            res+=com.toString()+"\n";
        }
        return res;
    }
    public static Client getClientById(int idClient){
        for(Client cl : Boutique.getInstance().clients){
            if(cl.getId()==idClient){
                return cl;
            }
        }
        return null;
    }
    public static Commande getCommandeById(int idCommande){
        for (Commande com : Boutique.getInstance().commandes){
            if(com.getId()==idCommande){
                return com;
            }
        }
        return null;
    }
    public static Article getArticleByReference(String ref){
        for(Article art : Boutique.getInstance().stock.keySet()){
            if(art.getRef()==ref){
                return art;
            }
        }
        return null;
    }
}
