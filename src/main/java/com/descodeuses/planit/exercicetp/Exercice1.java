package com.descodeuses.planit.exercicetp;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

class Taches {
	private String tache;
	private String statut;

	public Taches(String tache) {
		this.tache = tache;
		this.statut = "non faite";
	}

	public String getTache() {
		return tache;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}
}

public class Exercice1 {

	static List<Taches> listedeTaches = new ArrayList<>();

	public static void afficherTaches(List<Taches> listedeTaches) {
		if (listedeTaches.isEmpty()) {
			System.out.println("Aucune tâche enregistrée.");
		} else {
			for (int i = 0; i < listedeTaches.size(); i++) {
				Taches t = listedeTaches.get(i);
				System.out.println((i + 1) + ". " + t.getTache() + " - " + t.getStatut());
			}
		}
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in); /// le Scanner(System.in) permet de lire ce que l’utilisateur tape au clavier.
		int choix = 0; 

		System.out.println();
		
		while(choix != 5) {
			System.out.println("menu");
			System.out.println("1. Ajouter une tache");
			System.out.println("2. Voir les taches");
			System.out.println("3. Marquer comme fait");
			System.out.println("4. Supprimer une tache");
			System.out.println("5. Quitter");
			System.out.print("Votre choix :");

			choix = scanner.nextInt(); ////  scanner.nextInt();	Variable initialisée avant lecture, puis mise à jour. Peut servir si tu utilises choix avant la saisie (ex: boucle).


			System.out.println();

		switch(choix) {
			case 1: 
				scanner.nextLine();  // Pour nettoyer le buffer après nextInt()
				System.out.println("Entrez une tâche :");
				String description = scanner.nextLine();
				listedeTaches.add(new Taches(description));
				System.out.println("Tâche ajoutée : " + description);
			break;

			case 2: /// afficher les taches
				afficherTaches(listedeTaches);
			break;

			case 3: /// Marquer la tache comme faite 
    			if (listedeTaches.isEmpty()) {
        			System.out.println("Aucune tâche à modifier.");
   				 } else {
        			afficherTaches(listedeTaches);  // Affiche les tâches avec leurs numéros

        			System.out.print("Entrez le numéro de la tâche à marquer comme faite : ");
        			int numero = scanner.nextInt();
        			scanner.nextLine(); // Vider le buffer

        		if (numero > 0 && numero <= listedeTaches.size()) {
            		Taches tache = listedeTaches.get(numero - 1);
            		tache.setStatut("faite");
            		System.out.println("Tâche marquée comme faite : " + tache.getTache());
        		} else {
            		System.out.println("Numéro invalide.");
       	 		}
				}
    		break;

			case 4: /// Supprimer une tache 
			if (listedeTaches.isEmpty()) {
				System.out.println("Aucune tâche à supprimer.");
			} else {
				afficherTaches(listedeTaches);  // Affiche la liste pour que l'utilisateur voie les numéros
		
				System.out.print("Entrez le numéro de la tâche à supprimer : ");
				int numero = scanner.nextInt();
				scanner.nextLine(); // vider le buffer
		
				if (numero > 0 && numero <= listedeTaches.size()) {
					Taches supprimee = listedeTaches.remove(numero - 1);
					System.out.println("Tâche supprimée : " + supprimee.getTache());
				} else {
					System.out.println("Numéro invalide.");
				}
			}
			break;


			case 5: /// Quitter
				System.out.println("Programme terminé.");
            	System.exit(0);
			break;
		}

			System.out.println();
	}

	scanner.close();

}}
