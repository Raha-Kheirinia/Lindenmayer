
/*
 * Sur mac ca ne marche pas mais sur windows ca marche.
 * On a entrer sur Eclipse les arguments de l'exemple du prof puis
 * on a clique sur "Run" , ca nous  cree un fichier ".eps" mais sur
 * Mac ca ne peut pas le lire, on a essaye sur Windows et ca peut le lire. 
 * Il faut donc l'essayer sur Windows
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author Zohreh Kheirnia
 * @author Aata-allah Rchidi
 *
 */

public class Lindenmayer {

	
	
	
	
	 public static void main(String[] args) throws IOException {
		 
		 // initiallisation des args sur ligne de commande
		 
	        double d = 2;
	        double delta = 22.5;
	        double x = 250;
	        double y = 0;
	        double a = 90;
	        double n = 6;
	        
	        String regle_f = "f";
	        String regle_F = "F";
	        
	        String omega =""; // symbol de depart
	        
	        boolean flag = false;
	        
	        ArrayList<String> regles = new ArrayList<>() ;
	        ArrayList<String> rules2= new ArrayList<>();

	        
	        
	        String nom_fichier="";	// dans le quel on copie et on ecrit le cod postScriipt
	        BufferedWriter writer = null;
	        String debut_iter ="[";
	        
	        
	        // copie le preimir partie de code PS jusque le ligne BIGIN 
	        //pour pouvoir ecrite et ajouter le code par le suite
	        String texte="%!PS-Adobe-3.0 EPSF-3.0\n" +"%%Title: (dessin systeme L --- IFT2015 automne 2016)\n" +"%%Creator: (moi)\n" +
	                    "%%BoundingBox: 0 0 500 500 \n" + "%%EndComments\n" + "\n" +"% \n" +"% %%BeginProlog ... %%EndProlog sont des commentaires de structuration de document \n" +
	                    "% pour denoter un bloc de definitions de nouveaux ensembles d'operateurs (procset) \n" +"% et d'autres ressources (p.e., polices). \n" + "\n" + "\n" + "%\n" + "% %%BeginResource...%%EndResource enferment une ressource \n" + "% comme un ensemble d'operateurs (procset). \n" + "%\n" + "\n" +"%%BeginProlog\n" +"%%BeginResource: procset (graphisme tortue) 1.0 0\n" +"\n" + "% --------- definition d'operations pour graphisme tortue\n" + "%\n" + "% Le systeme de coordonnes suit les deplacements et tours de la tortue,\n" +
	                    "% de maniere a ce que la tourtue est toujours a l'origine avec le next tourne \n" +"% vers l'axe X. \n" + "% \n" +"% Operations: \n" +"% T:move avance la tortue \n" +"% T:draw avance la tortue en dessinant une ligne entre les points de depart et arrivee\n" + "% T:turn tourne le nez de la tortue\n" +"\n" + "/T:move % d T:move -\n" + "	% avance la tortue par d (nombre --- positif, negatif ou 0)\n" +
	                    "{\n" + "	0 translate % mettre l'origin a cette nouvelle position\n" +"} def\n" +"\n" + "/T:draw % d T:draw -\n" +"	% avance la torue par d en dessinant une ligne\n" +"{\n" +"	newpath \n" +"	0 0 moveto\n" +  "	0 lineto\n" + "	currentpoint \n" +"	stroke \n" +"	translate \n" +"} def\n" + "\n" + "/T:turn % angle T:turn -\n" +
	                    "	% tourne le nez de la tourtue par l'angle (en degres)\n" +"{\n" + "	rotate\n" +"} def\n" + "\n" + "/T:init % x y angle T:init -\n" + "	% etat initiel de la tortue\n" +  "{	\n" +
	                    "	3 1 roll translate\n" +"	rotate \n" + "} def\n" +"\n" + "%%EndResource\n" + "\n" + "%%BeginResource: procset (regle aleatoire) 1.0 0\n" +"\n" +"realtime srand % graine aleatoire --- pendant les tests, utiliser une valeur fixe (p.e., 2015 rand) si necessaire pour repetabilite\n" +
	                    "\n" + "/L:rnd % [op1 op2 ...] L.rnd -\n" + "	% choisit un operateur au hasard et l'execute\n" + "	% op1, op2 etc sont des noms (commencent par /)\n" +"{\n" +
	                    "	rand % nombre aleatoire entre 0 et 2^31-1\n" + "	1 index length % longueur du tableau \n" + "	mod % nombre aleatoire entre 0 et len-1\n" + "	get \n" +
	                    "	cvx % conversion a executable \n" + "	exec % executer\n" +"} def\n" +"\n" + "%%EndResource\n" +"\n" + "% \n" +
	                    "% La reste du fichier est genere par votre traducteur. Il n'est pas necessaire d'ecrire des \n" +
	                    "% commentaires. \n" + "% ---------------- BEGIN\n" +"\n %%BeginResource: procset (systeme L) 1.0 0\n";
	        
	        
	        // verification de ligne de commande
	        for (int i=0;i<args.length;i++){
	            switch (args[i]){
	                case "-D" :		{	 d = Double.parseDouble(args[i+1]);  	break;	}
	                case "-x" :		{ 	 x = Double.parseDouble(args[i+1]); 	break;	}
	                case "-y" :		{	 y = Double.parseDouble(args[i+1]); 	break;	}
	                case "-delta" :	{ 	 delta = Double.parseDouble(args[i+1]);	break;	}
	                case "-a" :		{ 	 a = Double.parseDouble(args[i+1]); 	break;	}
	                
	                
	                //pour pouvoir trouver n "nbr entier" 
	                default :
	                    if (args[i].charAt(0)>=48 && 
	                    	args[i].charAt(0)<=57 && 
	                        args[i].charAt(args[i].length()-1)>=48 &&
	                        args[i].charAt(args[i].length()-1)<=57)
	  	                    		{ n = Double.parseDouble(args[i]); }
	                    
	                    // trouver character de depart avant symbole de "\"
	                    else if (i+1<args.length-1 && args[i+1].contains("\\"))
	                     			{ omega = args[i]; }
	                    
	                    
	                    //  pour supprimer ":" dans le string de code entre '' 
	                    // ’F:F[+F]F[-F]F’ devient F[+F]F[-F]F
	                    else if (args[i].contains("’")){
	                    			if (args[i].contains(":")&& 
	                    					(args[i].charAt(args[i].indexOf(":")-1))=='F'){
	                    					String[] res = args[i].split(":", args[i].length()-1);
	                    					res[1] = res[1].substring(0, res[1].length()-1);
	                    					//souvgarder les regles sous le format String
	                    					regles.add(res[1]);	                    					
	                    			} 
	                    			  else if (args[i].contains(":") && (args[i].charAt(args[i].indexOf(":")-1))=='f'){
	                                      flag = true;
	                                      String[] temp = args[i].split(":", args[i].length()-1);
	                                      temp[1] = temp[1].substring(0, temp[1].length()-1);
	                                      rules2.add(temp[1]);
	                                  }
	                    	}
	                    
	                    // trouver character de finir des regles  ">"
	                    // et trouver le nom de fichier avant "."
	                    else if (i-1>0 && args[i-1].equalsIgnoreCase(">")){
	                    		if (args[i].contains("."))
	                    			nom_fichier = args[i].substring(0, args[i].indexOf("."));
	                    		else 
	                    			nom_fichier = args[i];
	                    	}
	            }	            
	        } 
	        
	        
	        // ajouter les nouveux valeurs de "d" et "delta" dans notre premier partie de code postScripts 
	        texte += "/L:d "+d+" def % deplacement par defaut\n"+"/L:a "+delta+" def % angle par defaut\n\n";
	
	        
	            
	            if (flag == false){
	                String[] temp = regles_pro(regles,regle_F,true);
	                texte += temp[0];
	                debut_iter = temp[1];
	                texte += regle_d(regle_F,debut_iter);
	            }
	            else if (flag == true){
	                String iter;
	                String[] temp1 = regles_pro(regles,regle_F,false);
	                String[] temp2 = regles_pro(rules2,regle_f,true);
	                texte += temp1[0];
	                debut_iter = temp1[1];
	                texte += temp2[0];
	                iter = temp2[1];
	                                texte += regle_d(regle_F,debut_iter);
	                texte += regle_d(regle_f,iter);
	        }
	            
	        
	            // ajouter les valeur de args
	            
	 	       texte += "/omega % \n {\n"+omega+"\n" +"} def\n" + "\n" +"%%EndResource\n" + "%%EndProlog\n" +"\n" +
	 	                "% ------- fin de definitions, dessin commence ici\n" + 
	 	    		 				"0.5 setlinewidth\n" +"\n" +
	 	        					x+" "+y+" % x0 y0\n"+
	 	        					a+" % angle initial\n" +
	 	        					"T:init\n" +"\n" +
	 	        					n+" % nombre d'iterations \n" +
	 	        					omega +" % symbole de debut\n" + "\n" +"% le fichier doit finir par le commentaire special %%EOF\n"
	 	        					+ "%%EOF";
	 	                    		System.out.println(texte);
	        
	 
	 	writer = new BufferedWriter(new FileWriter(nom_fichier+".eps"));
	 	writer.write(texte);
	 	writer.close( ); 	                    		
	 }	 	                    		
	        
	        
	 
	 
	    // creation des regles
   private static String regle_d(String r,String iter){
	        String texte="";
	        String regle_Temp ="";
	        if (r.equals("F"))regle_Temp = "T:draw";
	        else regle_Temp = "T:move";
	        texte = "/"+r+" % iter "+r+" - % la valeur iter donne la profondeur de la recursion\n" +
	                "{\n \t dup % dupliquer la valeur car \"eq\" va la consumer\n\t0 eq \n" +
	                "\t{ % if: cas de base --- bousculer la tortue\n\tL:d "+regle_Temp+"\n" +
	                "\t\tpop % enlever le '0'\n}\n\t{ % else: expansion par une regle au hasard\n\t\t1 sub % decrementer la variable iter\n"+
	                "\t\t"+iter+" L:rnd % <<= c'est ou on choisit les regles possibles \n} ifelse\n } def\n \n";
	        return texte;
	                   
	    }
	 	                    		
  private static String[] regles_pro(ArrayList<String> regles ,String regle,boolean fin){
          	
	  String[] fileTable = new String[2];
      String texte = "";
      String debut_iter = "[";
	        for(int j=0;j<regles.size();j++){
	        	
	            String regle_temp = regles.get(j);
	            
	        	//j+1 est le nbr de regle
	            texte += "/"+regle+(j+1)+" % iter "+regle+(j+1)+" - \n	% expansion par regle "+(j+1)+": "+regle+"->"+regles.get(j)+"\n % en une iteration\n"+ "{\n";
	          
	            	for (int k=0;k<regle_temp.length();k++){
	            			if (regle_temp.charAt(k)=='F' || regle_temp.charAt(k)=='f') {
	            													texte +="  \tdup "+regle+"  \n";}

	            			else if (regle_temp.charAt(k)=='['){	texte +="	\tgsave % on encode '[' par l'operateur gsave qui sauvegarde l'etat courant du contexte graphique, incluant la tortue\n";;}
	            			
	            			else if (regle_temp.charAt(k)=='+'){	texte +="	\tL:a T:turn % +\n";}
	            			
	            			else if (regle_temp.charAt(k)==']'){	texte +="	\tgrestore % on encode ']' par l'operateur grestore qui retablit le contexte graphique plus recemment sauvegarde par gsave\n" +"			% gsave - grestore fonctionne comme push-pop sur une pile speciale	\n";}
	            			
	            			else if (regle_temp.charAt(k)=='-'){	texte +="	\tL:a neg T:turn % -\n";}
	            			
	            	         if (k==regle_temp.length()-1 && j!=regles.size()-1){
	                             texte+="	"+regle+"\n} def\n";
	                             debut_iter +="/"+regle+(j+1)+" ";
	                         }
	                         else if (k==regle_temp.length()-1 && j==regles.size()-1){
	                              debut_iter +="/"+regle+(j+1)+"]";
	                              if(fin == true)
	                              texte += "	\n	pop\n} def\n";
	                              else texte +="	"+regle+"\n} def\n";
	                         }
	                      }
	                  }
	                  fileTable[0] = texte;
	                  fileTable[1]=debut_iter;
	              return fileTable;
	          }
	     
	           
  
  
  
	      
	           
	}   
	        

	    
	

	
	
	
	
	

