package sql;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import bdd.Batiment;
import bdd.Cours;
import bdd.Enseignant;
import bdd.Etudiant;
import bdd.Groupe;
import bdd.Inscription;
import bdd.Module;
import bdd.Personne;
import bdd.Salle;
import bdd.Section;
import bdd.TP;

public class Recherche {

	private DB db;
	private SQL queryString = new SQL();
	private SimpleDateFormat formatterDate = new SimpleDateFormat("yy-MM-dd");
	private SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm:ss");
	private JFrame frame;

	public Recherche(DB db) {
		this.db=db;
		//this.frame=frame;
	}

	public Personne login(String account, String password) throws Exception {

		String[] table = { "Etudiant" };
		String[] fields = { "idPersonne" };
		String condition = "login= '" + account + "' AND mdp = '"+ password + "'";

		String t = queryString.Query_Select(table, etudiantAtt, condition);
		ResultSet result = db.Get_DB(t);
		Etudiant user = new Etudiant();
		while (result.next()){
			user.setIdPersonne(result.getInt(1));
			user.setNom(result.getString(2));
			user.setPrenom(result.getString(3));
			user.setLogin(result.getString(4));
			user.setMdp(result.getString(5));
			user.setIdGroupe(result.getInt(6));
		}if (user.getIdPersonne() != null) {
			System.out.println("existe!");
			return user;
		} else {
			table[0] = "Enseignant";
			fields[0] = "idPersonne";
			condition = "login= '" + account + "' AND mdp = '"+ password + "'";

			t = queryString.Query_Select(table, fields, condition);
			result = db.Get_DB(t);
			Enseignant user2 = new Enseignant();
			while (result.next()){
				user2.setIdPersonne(result.getInt(1));
				user2.setNom(result.getString(2));
				user2.setPrenom(result.getString(3));
				user2.setLogin(result.getString(4));
				user2.setMdp(result.getString(5));
				user2.setGrade(result.getString(6));
			}if (user.getIdPersonne() != null) {
				System.out.println("existe!");
				return user2;
			}else{
				System.out.println("n'existe pas !");
				return null;
			}
		}
	}

/*	public Section getSectionById(String idSection) throws Exception {
		Section section = new Section();
		String[] table = { "Section" };
		String[] fields = { "idSection, Annee" };
		String condition = "idSection = '" + idSection + "'";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = db.Get_DB( t);
		while (result.next()) {
			section.setIdSection(new Integer(result.getInt(1)));
			section.setAnnee((Date) formatterDate.parse(result.getString(2)));
		}
		return section;
	}

	public Groupe getGroupeById(String idGroupe) throws Exception {
		Groupe Groupe = new Groupe();
		String[] table = { "Groupe" };
		String[] fields = { "idGroupe,idSection" };
		String condition = "idGroupe = '" + idGroupe + "'";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = db.Get_DB( t);
		while (result.next()) {
			Groupe.setIdGroupe(result.getInt(1));
			Groupe.setIdSection(result.getInt(2));
		}
		return Groupe;

	}

	public Etudiant getEtudiantById(Integer idEtudiant) throws Exception {
		Etudiant Etudiant = new Etudiant();
		String[] table = { "Etudiant" };
		String[] fields = { "idPersonne,nom,prenom" };
		String condition = "idEtudiant = '" + idEtudiant + "'";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = db.Get_DB(t);
		while (result.next()) {
			Etudiant.setIdPersonne(result.getInt(1));
			Etudiant.setNom(result.getString(2));
			Etudiant.setPrenom(result.getString(3));
		}
		return Etudiant;
	}

	public Module getModuleById(String idModule) throws Exception {
		Module module = new Module();
		String[] table = { "Module" };
		String[] fields = { "idModule, nom, coef" };
		String condition = "idModule = '" + idModule + "'";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = db.Get_DB( t);
		while (result.next()) {
			module.setIdModule(result.getString(1));
			module.setNom(result.getString(2));
			module.setCoefficient(result.getInt(3));
			
		}
		return module;

	}

	public Module getModuleByName(String nameModule) throws Exception{
		Module module = new Module();
		String[] table = { "Module" };
		String[] fields = { "idModule, nom, coef" };
		String condition = "nom = '" + nameModule + "'";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = db.Get_DB( t);
		while (result.next()) {
			module.setIdModule(result.getString(1));
			module.setNom(result.getString(2));
			module.setCoefficient(result.getInt(3));
			
		}
		return module;

	}


	public Enseignant getEnseignantById(String idProf) throws Exception {
		Enseignant Enseignant = new Enseignant();
		String[] table = { "Enseignant" };
		String[] fields = { "idPersonne, nom, prenom" };
		String condition = "idPersonne = '" + idProf + "'";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = db.Get_DB( t);
		while (result.next()) {
			Enseignant.setIdPersonne(result.getInt(1));
			Enseignant.setNom(result.getString(2));
			Enseignant.setPrenom(result.getString(3));
		}
		return Enseignant;
	}

	public List<Groupe> getGroupeBySection(String idSection) throws Exception {
		List<Groupe> listGroupePromo = new ArrayList<Groupe>();
		String[] table = { "Groupe" };
		String[] fields = { "idGroupe, idSection" };
		String condition = "id_promo = '" + idSection + "'";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = db.Get_DB( t);
		while (result.next()) {
			Groupe Groupe = new Groupe();
			Groupe.setId(result.getString(1));
			Groupe.setIdSection(result.getString(2));
			Groupe.setName(result.getString(3));
			Groupe.setEtudiant(getEtudiantByGroupe(Groupe.getId()));
			listGroupePromo.ajouter(Groupe);
		}
		return listGroupePromo;
	}

	public EtudiantgetEtudiantByGroupe(String idGroupe) throws Exception {
		EtudiantEtudiant = new EtudiantDuTemps();
		String[] table = { "Etudiant" };
		String[] fields = { "id_Etudiant, idGroupe" };
		String condition = "idGroupe = '" + idGroupe + "'";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = db.Get_DB( t);
		while (result.next()) {
			Etudiant.setId(result.getString(1));
			Etudiant.setNom(result.getString(2));
			Etudiant.setPrenom(result.getString(3));
		}
		return Etudiant;
	}

	public List<Sceance> getSceanceByEtudiant(String idEtudiant) throws Exception {
		List<Sceance> listSceanceEtudiant = new ArrayList<Sceance>();
		String[] table = { "Sceance" };
		String[] fields = { "id_sceance, id_module, id_Etudiant, date_sceance, time_sceance" };
		String condition = "id_Etudiant = '" + idEtudiant + "'ORDER BY date_sceance";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = db.Get_DB( t);
		while (result.next()) {
			Sceance sceance = new Sceance();
			Module module = new Module();
			sceance.setId(result.getString(1));
			module = getModuleById(result.getString(2));
			sceance.setModule(module);
			sceance.setIdEtudiant(result.getString(3));
			sceance.setDate((Date) formatterDate.parse(result.getString(4)));
			sceance.setTime((Date) formatterTime.parse(result.getString(5)));
			listSceanceEtudiant.ajouter(sceance);
		}
		return listSceanceEtudiant;
	}

	public List<Sceance> getSceanceByModule(String idModule) throws Exception {
		List<Sceance> listSceanceModule = new ArrayList<Sceance>();
		String[] table = { "Sceance" };
		String[] fields = { "id_sceance, id_module, id_Etudiant, date_sceance, time_sceance" };
		String condition = "id_module = '" + idModule + "'ORDER BY date_sceance";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = db.Get_DB( t);
		while (result.next()) {
			Sceance sceance = new Sceance();
			Module module = new Module();
			sceance.setId(result.getString(1));
			module = getModuleById(result.getString(2));
			sceance.setModule(module);
			sceance.setIdEtudiant(result.getString(3));
			sceance.setDate((Date) formatterDate.parse(result.getString(4)));
			sceance.setTime((Date) formatterTime.parse(result.getString(5)));
			listSceanceModule.ajouter(sceance);
		}
		return listSceanceModule;
	}

	public List<Module> getModuleByEnseignant(String idEnseignant) throws Exception {
		List<Module> listModuleEnseignant = new ArrayList<Module>();
		String[] table = { "Module" };
		String[] fields = { "id_module, idPersonne, name_module, duree" };
		String condition = "idPersonne = '" + idEnseignant + "'";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = db.Get_DB( t);
		while (result.next()) {
			Module module = new Module();
			module.setIdModule(result.getString(1));
			module.setNom(result.getString(2));
			module.setCoefficient(result.getInt(3));
			
			listModuleEnseignant.ajouter(module);
		}
		return listModuleEnseignant;
	}

	public EtudiantgetEtudiantBySectionAndGroupe(String nameSection,String nameGroupe) throws Exception {
		List<Section> Sections = new ArrayList<Section>();
		EtudiantEtudiant = new EtudiantDuTemps();
		List<Groupe> listGroupe = new ArrayList<Groupe>();
		Sections = getAllSection();
		for (Section promo : Sections) {
			if (promo.getName().equals(nameSection)) {
				listGroupe = promo.getGroupes();
				// System.out.println(listGroupe.size());
				// System.out.println(listGroupe.get(0).getName());
			}
		}
		// System.out.println(listGroupe.get(0).getName());
		for (Groupe Groupe : listGroupe)
			if (Groupe.getName().equals(nameGroupe)) {
				Etudiant = Groupe.getEtudiant();
			}
		if (Etudiant != null) {
			System.out.println(Etudiant.getId());
		} else {
			System.out.println("Il n'y a pas Etudiant");
		}
		return Etudiant;
	}

	public List<Module> getModuleByGroupeAndSection(String nameSection, String nameGroupe) throws Exception {
		List<Module> listModuleOfGroupe = new ArrayList<Module>();
		EtudiantEtudiant = new EtudiantDuTemps();
		Etudiant = getEtudiantBySectionAndGroupe(nameSection, nameGroupe);
		if (Etudiant != null) {
			for (Sceance sceance : Etudiant.getListSceance())
				listModuleOfGroupe.ajouter(sceance.getModule());
			HashMap<String, Module> hm = new HashMap<String, Module>();
			for (Module module : listModuleOfGroupe) {
				hm.put(module.getId(), module);
			}
			Set set = hm.entrySet();
			java.util.Iterator i = set.iterator();
			List<Module> listAll = new ArrayList<Module>();
			while (i.hasNext()) {
				Entry me = (Map.Entry) i.next();
				String idModule = (String) me.getKey();
				Module module1 = new Module();
				module1= hm.get(idModule);
				listAll.ajouter(module1);
			}
			System.out.println(listAll.size());
			for (Module module:listAll){
				System.out.println(module.getName());
			}
			return listAll;
		} else {
			System.out.println("Il n'y a pas Sceance de ce Groupe");
			return null;
		}
	}*/

/*#####################################################################################################################################*/
	
	public List<Batiment> getAllBatiment(){
		List<Batiment> listAllBatiment = new ArrayList<Batiment>();
		try{
			String[] table = { "Batiment" };
			String t = queryString.Query_Select(table, batimentAtt, "");
			ResultSet result = db.Get_DB( t);
			while (result.next()) {
				Batiment batiment = new Batiment();
				batiment.setIdBatiment(result.getInt(1));
				listAllBatiment.add(batiment);
			}
		}catch(Exception x){
			JOptionPane.showMessageDialog(frame,x.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
		}
		return listAllBatiment;
	}
	
	public List<Salle> getAllSalle(){
		List<Salle> listAllSalle = new ArrayList<Salle>();
		try{
			String[] table = { "Salle" };
			String t = queryString.Query_Select(table, salleAtt, "");
			ResultSet result = db.Get_DB( t);
			while (result.next()) {
				Salle salle = new Salle();
				salle.setIdSalle(result.getInt(1));
				salle.setCapacite(result.getInt(2));
				salle.setIdBatiment(result.getInt(3));			
				listAllSalle.add(salle);
			}
		}catch(Exception x){
			JOptionPane.showMessageDialog(frame,x.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
		}
		return listAllSalle;
	}
	
	public List<Inscription> getAllInscription(){
		List<Inscription> listAllInscription = new ArrayList<Inscription>();
		try{
			String[] table = { "Inscription" };
			String t = queryString.Query_Select(table, inscriptionAtt, "");
			ResultSet result = db.Get_DB( t);
			while (result.next()) {
				Inscription inscription = new Inscription();
				inscription.setIdEtudiant(result.getInt(1));
				inscription.setIdModule(result.getString(2));
				inscription.setMoyenne(result.getDouble(3));
				listAllInscription.add(inscription);
			}
		}catch(Exception x){
			JOptionPane.showMessageDialog(frame,x.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
		}
		return listAllInscription;
	}
	
	public List<Cours> getAllCours(){
		List<Cours> listAllCours = new ArrayList<Cours>();
		try{
			String[] table = { "Cours" };
			String t = queryString.Query_Select(table, coursAtt, "")+ " ORDER BY dateDebut, heureDebut";
			ResultSet result = db.Get_DB( t);
			while (result.next()) {
				Cours cours = new Cours();
				cours.setIdModule(result.getString(1));
				cours.setIdSalle(result.getInt(2));
				cours.setIdEnseignant(result.getInt(3));
				cours.setIdSection(result.getInt(4));
				cours.setDateDebut((Date) formatterDate.parse(result.getString(5)));
				cours.setHeureDebut((Date) formatterTime.parse(result.getString(6)));
				cours.setDuree((Date) formatterTime.parse(result.getString(7)));
				listAllCours.add(cours);
			}
		}catch(Exception x){
			JOptionPane.showMessageDialog(frame,x.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
		}
		return listAllCours;
	}
	
	public List<TP> getAllSeance(){
		String[] TPAtt = {"idModule","idSalle","idEnseignant","idSection","NULL","dateDebut","heureDebut","durree" };
		List<TP> listAllTP = new ArrayList<TP>();
		try{
			String[] table1 = { "TP" };
			String t = queryString.Query_Select(table1, this.TPAtt, "");
			String[] table2 = { "Cours" };
			t += " UNION ALL "+queryString.Query_Select(table2, TPAtt, "")+ " ORDER BY dateDebut";
			ResultSet result = db.Get_DB( t);
			while (result.next()) {
				TP tP = new TP();
				tP.setIdModule(result.getString(1));
				tP.setIdSalle(result.getInt(2));
				tP.setIdEnseignant(result.getInt(3));
				tP.setIdSection(result.getInt(4));
				tP.setIdGroupe(result.getInt(5));
				tP.setDateDebut((Date) formatterDate.parse(result.getString(6)));
				tP.setHeureDebut((Date) formatterTime.parse(result.getString(7)));
				tP.setDuree((Date) formatterTime.parse(result.getString(8)));
				listAllTP.add(tP);
			}
		}catch(Exception x){
			JOptionPane.showMessageDialog(frame,x.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
		}
		return listAllTP;
	}
	
	public List<TP> getAllTP(){
		List<TP> listAllTP = new ArrayList<TP>();
		try{
			String[] table = { "TP" };
			String t = queryString.Query_Select(table, TPAtt, "")+ " ORDER BY dateDebut";;
			ResultSet result = db.Get_DB( t);
			while (result.next()) {
				TP tP = new TP();
				tP.setIdModule(result.getString(1));
				tP.setIdSalle(result.getInt(2));
				tP.setIdEnseignant(result.getInt(3));
				tP.setIdSection(result.getInt(4));
				tP.setIdGroupe(result.getInt(5));
				tP.setDateDebut((Date) formatterDate.parse(result.getString(6)));
				String s = result.getString(7);
				System.out.println(s);
				tP.setHeureDebut((Date) formatterTime.parse(s));
				tP.setDuree((Date) formatterTime.parse(result.getString(8)));
				listAllTP.add(tP);
			}
		}catch(Exception x){
			JOptionPane.showMessageDialog(frame,x.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
		}
		return listAllTP;
	}
	
	public List<Module> getAllModule(){
		List<Module> listAllModule = new ArrayList<Module>();
		try{
			String[] table = { "Module" };
			String t = queryString.Query_Select(table, moduleAtt, "");
			ResultSet result = db.Get_DB( t);
			while (result.next()) {
				Module module = new Module();
				module.setIdModule(result.getString(1));
				module.setNom(result.getString(2));
				module.setCoefficient(result.getInt(3));
				listAllModule.add(module);
			}
		}catch(Exception x){
			JOptionPane.showMessageDialog(frame,x.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
		}
		return listAllModule;
	}

	public List<Etudiant> getAllEtudiant(){
		List<Etudiant> listProfs = new ArrayList<Etudiant>();
		try{
			String[] table = { "Etudiant" };
			String t = queryString.Query_Select(table, etudiantAtt, "");
			ResultSet resultProfs = db.Get_DB( t);
			while (resultProfs.next()) {
				Etudiant etudiant = new Etudiant();			
				etudiant.setIdPersonne(resultProfs.getInt(1));
				etudiant.setNom(resultProfs.getString(2));
				etudiant.setPrenom(resultProfs.getString(3));
				etudiant.setLogin(resultProfs.getString(4));
				etudiant.setMdp(resultProfs.getString(5));
				etudiant.setIdGroupe(resultProfs.getInt(6));
				listProfs.add(etudiant);
			}
		}catch(Exception x){
			JOptionPane.showMessageDialog(frame,x.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
		}
		return listProfs;
	}

	public List<Enseignant> getAllEnseignant(){
		List<Enseignant> listProfs = new ArrayList<Enseignant>();
		try{
			String[] table = { "Enseignant" };
			String t = queryString.Query_Select(table, enseignantAtt, "");
			ResultSet resultProfs = db.Get_DB(t);
			while (resultProfs.next()) {
				Enseignant prof = new Enseignant();			
				prof.setIdPersonne(resultProfs.getInt(1));
				prof.setNom(resultProfs.getString(2));
				prof.setPrenom(resultProfs.getString(3));
				prof.setLogin(resultProfs.getString(4));
				prof.setMdp(resultProfs.getString(5));
				prof.setGrade(resultProfs.getString(6));
				listProfs.add(prof);
			}
		}catch(Exception x){
			JOptionPane.showMessageDialog(frame,x.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
		}
		return listProfs;
	}

	public List<Section> getAllSection(){
		List<Section> listPromo = new ArrayList<Section>();
		try{
			String[] table = { "Section" };
			String t = queryString.Query_Select(table, sectionAtt, "");
			ResultSet result = db.Get_DB(t);
			while (result.next()) {
				Section promo = new Section();
				promo.setIdSection(result.getInt(1));
				promo.setAnnee((Date) formatterDate.parse(result.getString(2)));
				listPromo.add(promo);
			}
		}catch(Exception x){
			JOptionPane.showMessageDialog(frame,x.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
		}
		return listPromo;
	}

	public List<Groupe> getAllGroupe() {
		List<Groupe> listGroupe = new ArrayList<Groupe>();
		try{
			String[] table = { "Groupe" };
			String t = queryString.Query_Select(table, groupeAtt, "");
			ResultSet result = db.Get_DB(t);
			while (result.next()) {
				Groupe groupe = new Groupe();
				groupe.setIdGroupe(result.getInt(1));
				groupe.setIdSection(result.getInt(2));
				listGroupe.add(groupe);
			}
		}catch(Exception x){
			JOptionPane.showMessageDialog(frame,x.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
		}
		return listGroupe;
	}

/*################################################################################################################*/

	public void ajouterBatiment(Batiment batiment) throws Exception {
		String t = queryString.Query_Insert("Batiment", batimentAtt, batimentVal(batiment));
		if (db.Execute_DB(t)) System.out.println("Ok!");
	}

	public void supprimerBatiment(Batiment salle) throws Exception {
		String condition = "idBatiment = '" + salle.getIdBatiment() + "'";
		String t = queryString.Query_Delete("Batiment", condition);
		if (db.Execute_DB(t)) System.out.println("Ok!");
	}

	public void modifierBatiment(Batiment salle) throws Exception {
		String condition = "idBatiment = '" + salle.getIdBatiment() + "'";
		String t = queryString.Query_Update("Batiment", batimentAtt, batimentVal(salle), condition);
		if (db.Execute_DB(t)) System.out.println("Ok!");
	}
	
	
	public void ajouterSalle(Salle salle) throws Exception {
		String t = queryString.Query_Insert("Salle", salleAtt, salleVal(salle));
		if (db.Execute_DB(t)) System.out.println("Ok!");
	}

	public void supprimerSalle(Salle salle) throws Exception {
		String condition = "idSalle = '" + salle.getIdSalle() + "'";
		String t = queryString.Query_Delete("Salle", condition);
		if (db.Execute_DB(t)) System.out.println("Ok!");
	}

	public void modifierSalle(Salle salle) throws Exception {
		String condition = "idSalle = '" + salle.getIdSalle() + "'";
		String t = queryString.Query_Update("Salle", salleAtt, salleVal(salle), condition);
		if (db.Execute_DB(t)) System.out.println("Ok!");
	}
	
	public void ajouterInscription(Inscription inscription) throws Exception {
		String t = queryString.Query_Insert("Inscription", inscriptionAtt, inscriptionVal(inscription));
		if (db.Execute_DB(t)) System.out.println("Ok!");
	}

	public void supprimerInscription(Inscription inscription) throws Exception {
		String condition = "idEtudiant = '" + inscription.getIdEtudiant() + 
						"' AND idModule = '" + inscription.getIdModule() + "'";
		String t = queryString.Query_Delete("Inscription", condition);
		if (db.Execute_DB(t)) System.out.println("Ok!");
	}

	public void modifierInscription(Inscription inscription) throws Exception {
		String condition = "idEtudiant = '" + inscription.getIdEtudiant() + 
				"' AND idModule = '" + inscription.getIdModule() + "'";
		String t = queryString.Query_Update("Inscription", inscriptionAtt, inscriptionVal(inscription), condition);
		if (db.Execute_DB(t)) System.out.println("Ok!");
	}
	
	public void ajouterSection(Section section) throws Exception {
		String t = queryString.Query_Insert("Section", sectionAtt, sectionVal(section));
		if (db.Execute_DB(t)) System.out.println("Ok!");
	}

	public boolean supprimerSection(Section Section) throws Exception {
		String condition = "idSection = '" + Section.getIdSection() + "'";
		String t = queryString.Query_Delete("Section", condition);
		return (db.Execute_DB(t));
	}

	public void modifierSection(Section section) throws Exception {
		String condition = "idSection = '" + section.getIdSection() + "'";
		String t = queryString.Query_Update("Section", sectionAtt, sectionVal(section), condition);
		if (db.Execute_DB(t)) System.out.println("Ok!");
	}

	public void ajouterGroupe(Groupe groupe) throws Exception {
		String t = queryString.Query_Insert("Groupe", groupeAtt, groupeVal(groupe));
		if (db.Execute_DB(t)) System.out.println("Ok!");
	}

	public void supprimerGroupe(Groupe groupe) throws Exception {
		String condition = "idGroupe = '" + groupe.getIdGroupe() + "'";
		String t = queryString.Query_Delete("Groupe", condition);
		if (db.Execute_DB( t)) System.out.println("Ok!");
	}

	public void modifierGroupe(Groupe groupe) throws Exception {
		String condition = "idGroupe = '" + groupe.getIdGroupe() + "'";
		String t = queryString.Query_Update("Groupe", groupeAtt, groupeVal(groupe), condition);
		if (db.Execute_DB(t)) System.out.println("Ok!");
	}
	
	public void ajouterTP(TP tP) throws Exception {
		String t = queryString.Query_Insert("TP", TPAtt, TPVal(tP));
		if (db.Execute_DB(t)) System.out.println("Ok!");
	}

	public boolean supprimerTP(TP tP) throws Exception {
		String cond =  condTP(tP);
		System.out.println(cond);
		String t = queryString.Query_Delete("TP", condTP(tP));
		return (db.Execute_DB(t));
	}

	public void modifierTP(TP tP) throws Exception  {
		String t = queryString.Query_Update("TP", TPAtt, TPVal(tP), condTP(tP));
		if (db.Execute_DB(t)) System.out.println("Ok!");
	}
	
	public void ajouterCours(Cours cours) throws Exception {
		String t = queryString.Query_Insert("Cours", coursAtt, coursVal(cours));
		if (db.Execute_DB(t)) System.out.println("Ok!");
	}

	public boolean supprimerCours(Cours cours) throws Exception {
		String cond =  condCours(cours);
		System.out.println(cond);
		String t = queryString.Query_Delete("Cours",cond);
		return (db.Execute_DB(t));
	}

	public boolean modifierCours(Cours cours) throws Exception  {
		String t = queryString.Query_Update("Cours", coursAtt, coursVal(cours), condCours(cours));
		return (db.Execute_DB(t));
	}

	public void ajouterEtudiant(Etudiant etudiant) throws Exception {
		String t = queryString.Query_Insert("Etudiant", etudiantAtt, etudiantVal(etudiant));
		if (db.Execute_DB(t)) System.out.println("Ok!");
	}

	public void supprimerEtudiant(Etudiant etudiant) throws Exception {
		String conditionE = "idPersonne= '" + etudiant.getIdPersonne() + "'";
		String tE = queryString.Query_Delete("Etudiant", conditionE);
		if (db.Execute_DB(tE)) System.out.println("Ok!");
	}

	public void modifierEtudiant(Etudiant etudiant) throws Exception {
		String condition = "idPersonne = '" + etudiant.getIdPersonne() + "'";
		String t = queryString.Query_Update("Etudiant", etudiantAtt, etudiantVal(etudiant), condition);
		if (db.Execute_DB(t)) System.out.println("Ok!");
	}

	public void ajouterModule(Module module) throws Exception {
		String t = queryString.Query_Insert("Module", moduleAtt, moduleVal(module));
		if (db.Execute_DB(t)) System.out.println("Ok!");
	}

	public void supprimerModule(Module module) throws Exception {
		String conditionE = "idModule= '" + module.getIdModule() + "'";
		String tE = queryString.Query_Delete("Module", conditionE);
		if (db.Execute_DB(tE)) System.out.println("Ok!");
	}

	public void modifierModule(Module module) throws Exception {
		String condition = "idModule= '" + module.getIdModule() + "'";
		String t = queryString.Query_Update("Module", moduleAtt, moduleVal(module),condition);
		if (db.Execute_DB(t)) System.out.println("Ok!");
	}

	public void ajouterEnseignant(Enseignant enseignant) throws Exception {
		String t = queryString.Query_Insert("Enseignant", enseignantAtt, enseignantVal(enseignant));
		if (db.Execute_DB(t)) System.out.println("Ok!");
	}

	public void supprimerEnseignant(Enseignant enseignant) throws Exception {
		String conditionE = "idPersonne= '" + enseignant.getIdPersonne() + "'";
		String t = queryString.Query_Delete("Enseignant", conditionE);
		if (db.Execute_DB(t)) System.out.println("Ok!");
	}

	public void modifierEnseignant(Enseignant enseignant) throws Exception {
		String condition = "idPersonne= '" + enseignant.getIdPersonne() + "'";
		String t = queryString.Query_Update("Enseignant", enseignantAtt, enseignantVal(enseignant), condition);
		if (db.Execute_DB(t)) System.out.println("Ok!");
	}
	
	private String[] enseignantAtt = { "idPersonne", "nom", "prenom","login","mdp","grade" };
	private String[] enseignantVal(Enseignant enseignant) {
		String[] res = new String[6];
		res[0] = enseignant.getIdPersonne().toString();
		res[1] = enseignant.getNom();
		res[2] = enseignant.getPrenom();
		res[3] = enseignant.getLogin();
		res[4] = enseignant.getMdp();
		res[5] = enseignant.getGrade();
		return res;
	}
	
	private String[] etudiantAtt = { "idPersonne", "nom", "prenom","login","mdp","idGroupe" };
	private String[] etudiantVal(Etudiant etudiant) {
		String[] res = new String[6];
		res[0] = etudiant.getIdPersonne().toString();
		res[1] = etudiant.getNom();
		res[2] = etudiant.getPrenom();
		res[3] = etudiant.getLogin();
		res[4] = etudiant.getMdp();
		res[5] = etudiant.getIdGroupe().toString();
		return res;
	}
	
	private String[] moduleAtt = { "idModule","nom","coef" };
	private String[] moduleVal(Module module) {
		String[] res = new String[3];
		res[0] = module.getIdModule().toString();
		res[1] = module.getNom();
		res[2] = module.getCoefficient().toString();
		return res;
	}
	
	private String[] sectionAtt = { "idSection","annee" };
	private String[] sectionVal(Section section) {
		String[] res = new String[2];
		res[0] = section.getIdSection().toString();
		res[1] = formatterDate.format(section.getAnnee());
		return res;
	}
	
	private String[] groupeAtt = { "idGroupe","idSection" };
	private String[] groupeVal(Groupe groupe) {
		String[] res = new String[2];
		res[0] = groupe.getIdGroupe().toString();
		res[1] = groupe.getIdSection().toString();
		return res;
	}
	
	private String[] coursAtt = {"idModule","idSalle","idEnseignant","idSection","dateDebut","heureDebut","durree" };
	private String[] coursVal(Cours cours) {
		String[] res = new String[7];
		res[0] = cours.getIdModule().toString();
		res[1] = cours.getIdSalle().toString();
		res[2] = cours.getIdEnseignant().toString();
		res[3] = cours.getIdSection().toString();
		res[4] = formatterDate.format(cours.getDateDebut());
		res[5] = formatterTime.format(cours.getHeureDebut());
		res[6] = formatterTime.format(cours.getDuree());
		return res;
	}
	
	private String[] TPAtt = {"idModule","idSalle","idEnseignant","idSection","idGroupe","dateDebut","heureDebut","durree" };
	private String[] TPVal(TP tP) {
		String[] res = new String[8];
		res[0] = tP.getIdModule().toString();
		res[1] = tP.getIdSalle().toString();
		res[2] = tP.getIdEnseignant().toString();
		res[3] = tP.getIdSection().toString();
		res[4] = tP.getIdGroupe().toString();
		res[5] = formatterDate.format(tP.getDateDebut());
		res[6] = formatterTime.format(tP.getHeureDebut());
		res[7] = formatterTime.format(tP.getDuree());
		return res;
	}

	private String[] inscriptionAtt = { "idEtudiant","idModule","moyenne" };
	private String[] inscriptionVal(Inscription inscription) {
		String[] res = new String[3];
		res[0] = inscription.getIdEtudiant().toString();
		res[1] = inscription.getIdModule().toString();
		res[2] = inscription.getMoyenne().toString();
		return res;
	}
	
	private String[] salleAtt = { "idSalle","capacite","idBatiment" };
	private String[] salleVal(Salle salle) {
		String[] res = new String[3];
		res[0] = salle.getIdSalle().toString();
		res[1] = salle.getCapacite().toString();
		res[2] = salle.getIdBatiment().toString();
		return res;
	}
	
	private String[] batimentAtt = { "idBatiment" };
	private String[] batimentVal(Batiment batiment) {
		String[] res = new String[1];
		res[0] = batiment.getIdBatiment().toString();
		return res;
	}
	
	private String condCours (Cours cours){
		return "idModule = '"+cours.getIdModule()+
		"' AND idSalle = '"+cours.getIdSalle()+
		"' AND idEnseignant = '"+cours.getIdEnseignant()+
		"' AND idSection = '"+cours.getIdSection()+
		"' AND dateDebut = '"+formatterDate.format(cours.getDateDebut())+
		"' AND heureDebut = '"+formatterTime.format(cours.getHeureDebut())+"'";
	}
	
	private String condTP (TP tP){
		return "idModule = '"+tP.getIdModule()+
		"' AND idSalle = '"+tP.getIdSalle()+
		"' AND idEnseignant = '"+tP.getIdEnseignant()+
		"' AND idSection = '"+tP.getIdSection()+
		"' AND idGroupe = '"+tP.getIdGroupe()+
		"' AND dateDebut = '"+formatterDate.format(tP.getDateDebut())+
		"' AND heureDebut = '"+formatterTime.format(tP.getHeureDebut())+"'";
	}
	
}
