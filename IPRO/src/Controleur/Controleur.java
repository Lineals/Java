package Controleur;

import Metier.*;
import Dao.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
        HashMap<Sellable,Integer> stock= Boutique.getInstance().stock;
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
    	Client client = null;
        for(Client cl : Boutique.getInstance().clients){
            if(cl.getId()==idClient){
                client = cl;
            }
        }
        return client;
    }
    public static Commande getCommandeById(int idCommande){
    	Commande commande = null;
        for (Commande com : Boutique.getInstance().commandes){
            if(com.getId()==idCommande){
                commande = com;
            }
        }
        return commande;
    }
    public static Sellable getArticleByReference(String ref){
    	Sellable article = null;
        for(Sellable art : Boutique.getInstance().stock.keySet()){
            if(art.getRef().equals(ref)){
                article = art;
            }
        }
        return article;
    }
    
}
