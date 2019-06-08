package Interface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Supplier;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bdd.Batiment;
import bdd.Cours;
import bdd.Enseignant;
import bdd.Etudiant;
import bdd.Groupe;
import bdd.Module;
import bdd.Salle;
import bdd.Section;
import bdd.TP;
import sql.Recherche;

public class Supprimer <T> extends JFrame implements ActionListener {

	JComboBox supprimer;

    JButton enregistrer;
    JButton annuler;
	Recherche service;

    public Supprimer(Recherche service, Supplier<List<T>> f){

		this.service=service;
		setTitle("Nouvelle promotion");
		setBounds(200, 200,270, 290);
		setVisible(true);

        JLabel label = new JLabel("Supprimer une entité");
				String vpromo[] = {"module1","module2"};
        supprimer = new JComboBox(f.get().toArray()); 
 
        enregistrer = new JButton("Supprimer");
        enregistrer.addActionListener(this);
        
        annuler = new JButton("Annuler");
        annuler.addActionListener(this);
                
        Container contentPane = getContentPane();
        GridBagLayout gbl = new GridBagLayout();

        JPanel jp = new JPanel();
        jp.setLayout(gbl);
        contentPane.add(jp);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 0;
        gbc.weighty = 0;
        
       	Insets inset = new Insets(10,10,5,5); 
        gbc.insets = inset;

        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        
        add(supprimer,gbc,jp,2,0,1,1);

        add(label,gbc,jp,0,0,1,1);

		JPanel jp2 = new JPanel();
		jp2.add (enregistrer);
		jp2.add (annuler);

		getContentPane().add (jp2, BorderLayout.SOUTH);
				
 }

	public void actionPerformed(ActionEvent e){
    	Object source = e.getSource();
    	if (source==enregistrer){
				try{
					if(supprimer.getSelectedItem() instanceof Enseignant)
						service.supprimerEnseignant((Enseignant)supprimer.getSelectedItem());
					else if(supprimer.getSelectedItem() instanceof Etudiant)
						service.supprimerEtudiant((Etudiant)supprimer.getSelectedItem());
					else if(supprimer.getSelectedItem() instanceof Module)
						service.supprimerModule((Module)supprimer.getSelectedItem());
					else if(supprimer.getSelectedItem() instanceof Groupe)
						service.supprimerGroupe((Groupe)supprimer.getSelectedItem());
					else if(supprimer.getSelectedItem() instanceof Section)
						service.supprimerSection((Section)supprimer.getSelectedItem());
					else if(supprimer.getSelectedItem() instanceof Cours)
						service.supprimerCours((Cours)supprimer.getSelectedItem());
					else if(supprimer.getSelectedItem() instanceof TP)
						service.supprimerTP((TP)supprimer.getSelectedItem());
					else if(supprimer.getSelectedItem() instanceof Salle)
						service.supprimerSalle((Salle)supprimer.getSelectedItem());
					else if(supprimer.getSelectedItem() instanceof Batiment)
						service.supprimerBatiment((Batiment)supprimer.getSelectedItem());
					setVisible(false);
				}catch(Exception eh){
					JOptionPane.showMessageDialog(this,eh.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
				}
    	}else if (source==annuler)
				setVisible(false);
    }
    
	public void add (Component c, GridBagConstraints gbc,JPanel jp, int x, int y, int w, int h){
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		jp.add(c,gbc);
	}

/*	private void supprimer(T elm){
		Object o=JOptionPane.showInputDialog(this, "Choisissez la base que vous voulez supprimer",
			"Boite d'options",JOptionPane.QUESTION_MESSAGE,null, elm.toArray(), null);
		//Mysql.executer("use "+(String)o);
		Mysql.supprimer("Drop database "+(String)o+";");
	}*/
}
