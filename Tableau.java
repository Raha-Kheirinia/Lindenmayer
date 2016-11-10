package exercise;

/**
 * Solution des exercices du TP6
 * Chaque methode qui represente un exercice aura le numero
 * de cet exercice dans le commentaire.
 */


public class Tableau {
    int taille;   
    double j;
    int [] contenu;
   
    
    /**
     * Constructeur de tableau aleatoire
     * dont les valeurs seront comprises entre 1 et 100
     * @param taille La taille du tableau.
     */  
 
    public Tableau (int n) {
        taille=n;
        contenu=new int [taille];
        for (int i=0 ; i < taille ; i++) {        
	    contenu[i] = (int)(100 * Math.random()+1);
        }
    }

    /**
     * Constructeur de tableau aleatoire
     * composee des elements entre 0 et elem.
     *
     * @param taille:  La taille du tableau.
     * @param elem L'element maximum a generer.
     */  
 
    public Tableau (int n, int elem) {
        taille=n;
        contenu=new int [taille];
        for (int i=0 ; i < taille ; i++) {        
	    contenu[i] = (int)(Math.random()*elem + 0.5);
        }
    }

    
    public Tableau(String tableauChaine[]) {
	//ca sera utile récupérer les arguments du main
	taille=tableauChaine.length;
	contenu=new int[taille];
	for (int i=0 ; i < taille ; i++)
	    contenu[i]=Integer.parseInt(tableauChaine[i]);
    }

    /**
     * Methode d'affichage : la methode toString est la
     * methode par defaut utilisee par System.out.println
     * @return Le tableau transforme en chaine de caracteres
     */
    public String toString() {
        String s=new String ("[");
        for (int i=0 ; i < taille-1 ; i++){

	    s += contenu[i] + ",";
        }
	s += contenu[taille-1]+"]";
	return s;
    }

    /**
     * Exercice 1.1
     *
     *
     * Methode pour calculer la moyenne des elements
     * d'un tableau donne en parametre
     **/
       
    public float moyenne() {
	int somme = 0;
	for (int i = 0; i < taille; i++)
	    somme += contenu[i];
	return (somme/(float)taille);
    }

    /**
     * Exercice 1.2
     *
     * Methode pour trouver l'indice du deuxieme plus grand element 
     * d'un tableau d'elements distincts.
     * @return l'indice du deuxieme plus grand element
     **/

    public int Indice2plusGrand() {
        int k=1;
	int imax=0;
	int imax2=0;

	while (contenu[k] == contenu[0]) {
	    k++;
	    if(k==taille)return(-1);
	}
        if (contenu[k] > contenu[0]) {
	    imax=k;
	    imax2=0;
	} else { 
	    imax=0;
	    imax2=k;
	}

	for (int i=k ; i < taille ; i++) {
	    if (contenu[i] > contenu[imax]) {
		imax2=imax;
		imax=i;
	    }
	    else if ((contenu[i] > contenu[imax2]) && (contenu[i] < contenu[imax]))
		imax2=i;
	}
	return imax2;
    }

    /**
     * Exercice 1.2
     *
     * Voici une autre facon de faire le meme exercice, mais avec 2 methodes:
     * une qui envoie l'indice du plus grand et une autre qui utilise
     * la premiere pour trouver l'indice du 2eme plus grand
     * NOTE: avec cette methode s'il y a 2 ocurrences d'un meme numero
     * il donnera la deuxieme ocurrence comme le 2eme plus grand
     * @return l'indice du plus grand et 2eme plus grand respectivement
     */

    public int indicePlusGrand() {
	int indice=0;
	int max=contenu[0];
	for (int i=1; i<taille ; i++) {
	    if (max < contenu[i]) {
		max=contenu[i];
		indice=i;
	    }
	}
	return indice;
    }

    public int indiceDeuxiemePlusGrand() {
	int indice;
	int max;
	int pg=indicePlusGrand();

	if (pg!=0) {
	    max=contenu[0];
	    indice=0;
	} else {
	    max=contenu[1];
	    indice=1;
	}
	for (int i=0 ; i<taille ; i++) {
	    if ((max < contenu[i]) && (i!=pg)) {
		max=contenu[i];
		indice=i;
	    }
	}
	return indice;
    }


    /**
     * Exercice 1.2.2
     *
     * Methode pour trouver l'indice du nieme plus grand element 
     * d'un tableau d'elements distincts.
     * @return l'indice du nieme plus grand element
     **/

    public void TriInsertion() {
	for (int i=1 ; i < taille ; i++){
	    int j=0;
	    
	    while (contenu[j]<contenu[i])
		j++;
	    int tampon=contenu[i];
	    for (int k=i ; k > j ; k--)
		contenu[k]=contenu[k-1];
	    contenu[j]=tampon;
	}
    }

    public int Recherche(int value) {
	for (int i=0 ; i < taille ; i++) {
	    if (contenu[i] == value) 
		return i;
	}
	return -1;
    }

    public int getNValue(int n) {
	Tableau interm = new Tableau(taille);
	for (int i=0 ; i < taille ; i++)
	    interm.contenu[i]=contenu[i];
	interm.TriInsertion();
	return Recherche(interm.contenu[n-1]);
    }

    
    /**
     * Exercice 1.3
     *
     * Cette methode permet de trouver le nombre d'occurrences 
     * de nombres pairs dans un tableau.
     * @return le nombre d'ocurrences de nombres pairs
     */

    public int occurence() {
	int c = 0;
	for (int i=0; i<taille; i++)
	    if(contenu[i]%2 == 0)
		c++;
	return c;
    }

     
    /** 
     * Exercice 1.4
     *
     * Cet algorithme permet de reconnaitre si un tableau est une permutation. 
     * @return vrai si il est une permutation faux en cas contraire
     */

    public boolean permutation() {
	for (int i=0 ; i < taille ; i++){
	    for (int j=0; j < i ; j++){
		if (contenu[i] == contenu[j])
		    return false;
	    }
	    if ((contenu[i] < 1) || (contenu[i] > taille))
		return false;
	}
	return true;
    }

 
    /**
     * Exercice 1.5
     *
     * Cette methode renvoie le nombre de descentes du tableau 
     * donne en parametre, c'est-a-dire le nombre de
     * i tels que t[i+1]<t[i]
     * @return nombre de descentes
     */

    public int descente() {
	int descentes=0;
	for (int i=0 ; i < taille-1 ; i++){
	    if (contenu[i+1] < contenu[i])
		descentes++;
	}
	return descentes;
    }

    /**
     * Exercice 1.5.2
     *
     * Cette methode renvoie le nombre de descentes generales du tableau 
     * donne en parametre
     * @return nombre de descentes generales
     */

    public int descentegen() {
	int desc=0;
	boolean on_descent=false;
	int i=0;

	while (i < taille-1) {
	    if (contenu[i+1] < contenu[i]) { 
		if (on_descent == false) { 
		    desc++;
		    on_descent = true;
		}
		i++;
	    } else {
		on_descent = false; 
		i++;
	    }
	}
	return desc;
    }


    public void TriInsertionDec() {
	for (int i = 1; i<taille ; i++) {
	    int j = 0;
	    while (contenu[j] > contenu[i])
		j++;
	    int tampon = contenu[i];
	    for (int k=i ; k > j ; k--)
		contenu[k] = contenu[k-1];
	    contenu[j] = tampon;
	}
    }

    /**
     * Exercice 2.2
     *
     * Fusionne deux tableaux tries
     * On va piocher au fur et a mesure dans un ou l'autre des tableaux
     * pour rechercher la valeur a placer dans le resultat de la fusion
     * @param t le tableau a fusionner avec this
     * @return nouveau tableau resultant de la fusion de this et t
     */

    Tableau fusion(Tableau t) {
	int i = 0, j = 0, k = 0;
	Tableau resultat = new Tableau(taille + t.taille);

	while ((i < taille) && (j < t.taille)) {
	    if (contenu[i] < t.contenu[j]) {
		resultat.contenu[k] = contenu[i];
		i++;
	    } else {
		resultat.contenu[k] = t.contenu[j];
		j++;
	    }
	    k++;
	}

	/* le programme rentrera au plus dans l'un des deux while ci-dessous pour
	   copier les données "restantes" dans resultat */

	while (i < taille)
	    resultat.contenu[k++] = contenu[i++];
	while (j < t.taille)
	    resultat.contenu[k++] = t.contenu[j++];

	return resultat;
    }


    /**
     * Methode principale pour tester tous les autres methodes
     * @param argv les arguments passe au programme
     */

    public static void main (String argv[]) {
        Tableau t1=new Tableau(10);
	
        System.out.println("Le tableau est: " + t1);
	System.out.println("La valeur Moyenne est: " + t1.moyenne());

        System.out.println("L'indice du deuxieme plus grand element est: " + t1.Indice2plusGrand());
        System.out.println("Avec la deuxieme methode: " + t1.indiceDeuxiemePlusGrand());
        System.out.println("L'indice du cinquieme (par ex.) plus grand element est: " + t1.getNValue(5));

	System.out.println("Le nombre d'occurences de nb paires est: " + t1.occurence());

	System.out.println("Le tableau est une permutation?: " + t1.permutation()); 

	System.out.println("Le nombre de descentes dans le tableau est: " + t1.descente());
	System.out.println("Le nombre de descentes generales dans le tableau  est: " + t1.descentegen());

	t1.TriInsertionDec();
	System.out.println("Le Tableau trie par ordre decroissant: " + t1);
		
	t1.TriInsertion();	
	System.out.println("Le Tableau trie par ordre croissant: " + t1);

	Tableau t2=new Tableau(15);
	t2.TriInsertion();	
	System.out.println("Le resultat de la fusion triee de " + t1 + " et " + t2 + " est: " + t1.fusion(t2));
    }
}
