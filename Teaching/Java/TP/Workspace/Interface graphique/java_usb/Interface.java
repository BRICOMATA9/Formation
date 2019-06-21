import java.awt.*;
import javax.swing.*;

public class Interface extends JComponent {
	public static JComponent createComponent ( final MemoireUSB usb ){
		return new JComponent (){
			protected void paintComponent ( Graphics g ){
			int x=4;//initialisation de l'abscisse 
				super . paintComponent ( g );
				for(int i=0;i<usb.getTailleBlocs();i++){
					if(usb.getBlocs(i).getNom().equals("vide")){	
						//si le Bloc est vide on colore l'espace occupé avec le bleu				
						g.setColor(Color.BLUE);
						g.fillRect(x,4,usb.getBlocs(i).getTaille()-1,getHeight());
                    				g.setFont(new Font("Arial", Font.BOLD,15));
                    				g.setColor(Color.WHITE);
                   				g.drawString(usb.getBlocs(i).toString(),x+4,75);
                   				x+=usb.getBlocs(i).getTaille();
                   					//on augmente l'abscisse de l'espace occupé par ce Bloc 					
					}
					else{
					//si le Bloc n'est pas vide on colore l'espace occupé avec le rouge
						g.setColor(Color.RED);
						g.fillRect(x,4,usb.getBlocs(i).getTaille()-1,getHeight());
                    				g.setFont(new Font("Arial", Font.BOLD,15));
                    				g.setColor(Color.WHITE);
                    				g.drawString(usb.getBlocs(i).getNom(),x+4,25);
                   				g.drawString(usb.getBlocs(i).toString(),x+4,75);
						x+=usb.getBlocs(i).getTaille();	
					//on augmente l'abscisse de l'espace occupé par ce rouge					
					}
				}				
			}			
		};
	}
}
