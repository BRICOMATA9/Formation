package Interface.Supprimer;

import static java.util.stream.Collectors.toList;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import BDD.Cours;
import DAO.EnseignantDAO;
import DAO.ModuleDAO;
import DAO.SalleDAO;
import DAO.SectionDAO;
import Interface.Function;

@SuppressWarnings("serial")
public class SupprimerCours extends JFrame implements ActionListener {

	JComboBox<Object> idModule;
	JComboBox<Object> idSalle;
	JComboBox<Object> idEnseignant;
	JComboBox<Object> idSection;
	JDateChooser dateDebut;
	JComboBox<Object> heureDebut;

	private final SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
	
	private String[] heure_ = {"08:00","08:30","09:00","09:30","10:00","10:30","11:00","11:30","12:00","12:30",
			"13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30","17:00","17:30"};
	
    JButton enregistrer;
    JButton annuler;
    private Function<Cours> function; 

    public SupprimerCours(String s,Function<Cours> f) throws Exception{

		setTitle(s+" un Cours");
		setBounds(200, 200,360, 390);
		setVisible(true);
		function = f;
		
        JLabel module = new JLabel("Module");
        idModule = new JComboBox<Object>((Object[])ModuleDAO.getInstance().getId().toArray());
        idModule.addActionListener(this);
        
        JLabel enseignant = new JLabel("Enseignant");
        idEnseignant = new JComboBox<Object>((Object[])EnseignantDAO.getInstance().getId().toArray());
        idEnseignant.addActionListener(this);
        
		JLabel date = new JLabel("Date :");
		dateDebut = new JDateChooser();
		dateDebut.setLocale(new Locale("fr","FR"));
		dateDebut.setDate(GregorianCalendar.getInstance().getTime());
		
		//dateDebut.addPropertyChangeListener(this);
		
        JLabel heure = new JLabel("Heure");
        heureDebut = new JComboBox<Object>(heure_);
        heureDebut.addActionListener(this);
        
        JLabel section = new JLabel("Section");
        idSection = new JComboBox<Object>((Object[])SectionDAO.getInstance().getId().toArray());
        idSection.addActionListener(this);
		
        JLabel salle = new JLabel("Salle");
        idSalle = new JComboBox<Object>((Object[])SalleDAO.getInstance().getIdNDispo(dateDebut.getDate()).toArray());
        idSalle.addActionListener(this);
		
		dateDebut.addPropertyChangeListener("date", new PropertyChangeListener() {
		    @Override
		    public void propertyChange(PropertyChangeEvent evt) {
		    	try{
			        Date date = (Date)evt.getNewValue();
			        Stream<Integer> o = SalleDAO.getInstance().getIdNDispo(date);
			        List<Integer> l = o.collect(toList());
			        idSalle.removeAllItems();
		    		for(Integer i:l)
		    			idSalle.addItem(i);
		    		//idSalle.setSelectedIndex(0);
		    	}catch(Exception v){}
		    }
		});
 
        enregistrer = new JButton(s);
        enregistrer.addActionListener(this);
        
        annuler = new JButton("Annuler");
        annuler.addActionListener(this);
                
        Container contentPane = getContentPane();
        GridBagLayout gbl = new GridBagLayout();

        JPanel jp = new JPanel();
        jp.setLayout(gbl);
        contentPane.add(jp);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;

       	Insets inset = new Insets(10,10,5,5); 
        gbc.insets = inset;

        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL; 

        add(dateDebut,gbc,jp,1,0,1,1);
        add(heureDebut,gbc,jp,1,1,1,1);
        add(idSalle,gbc,jp,1,2,1,1);
        add(idModule,gbc,jp,1,3,1,1);
        add(idEnseignant,gbc,jp,1,4,1,1);
        add(idSection,gbc,jp,1,5,1,1);

        add(date,gbc,jp,0,0,1,1);
        add(heure,gbc,jp,0,1,1,1);
        add(salle,gbc,jp,0,2,1,1);
        add(module,gbc,jp,0,3,1,1);
        add(enseignant,gbc,jp,0,4,1,1);
        add(section,gbc,jp,0,5,1,1);

		JPanel jp2 = new JPanel();
		jp2.add (enregistrer);
		jp2.add (annuler);

		getContentPane().add (jp2, BorderLayout.SOUTH);
				
 }

    public void actionPerformed(ActionEvent e){
    	Object source = e.getSource();
		try{
	    	if (source==enregistrer){
				Cours cours = new Cours();
				cours.setIdModule(ModuleDAO.getInstance().getById(idModule.getSelectedItem().toString()));
				cours.setIdSalle(SalleDAO.getInstance().getById(Integer.parseInt (idSalle.getSelectedItem().toString())));
				cours.setIdEnseignant(EnseignantDAO.getInstance().getById(Integer.parseInt (idEnseignant.getSelectedItem().toString())));
				cours.setIdSection(SectionDAO.getInstance().getById(Integer.parseInt (idSection.getSelectedItem().toString())));
				cours.setDateDebut(dateDebut.getDate());
				cours.setHeureDebut((Date) formatTime.parse((String)heureDebut.getSelectedItem()+":00"));
				//CoursDAO.getInstance().ajouter(cours);
				if (function.apply(cours)) setVisible(false);
	    	}else if (source==annuler)
				setVisible(false);
		}catch(Exception eh){
			JOptionPane.showMessageDialog(this,eh.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
		}
    }

	public void add (Component c, GridBagConstraints gbc,JPanel jp, int x, int y, int w, int h){
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		jp.add(c,gbc);
	}
}

