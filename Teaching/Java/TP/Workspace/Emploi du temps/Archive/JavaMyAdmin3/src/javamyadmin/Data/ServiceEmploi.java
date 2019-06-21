package javamyadmin.Data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javamyadmin.Data.*;
import javamyadmin.Model.*;

public class ServiceEmploi {

	private Connection conn;
	private ProcessDB process = new ProcessDB();;
	private Query queryString = new Query();

	public ServiceEmploi() {
		conn = Mysql.getConnection();
	}

	public boolean logginUser(String account, String password) throws Exception {

		String[] table = { "User" };
		String[] fields = { "id_user" };
		String condition = "account= '" + account + "' AND password = '"+ password + "'";

		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = process.Get_DB(conn, t);
		User user = new User();
		while (result.next())
			user.setId(result.getString(1));
		if (user.getId() == null) {
			System.out.println("N'existe pas !");
			return false;
		} else {
			System.out.println("pass !");
			return true;
		}
	}

	public Promotion findPromotionById(String idPromo) throws Exception {
		Promotion promotion = new Promotion();
		String[] table = { "Promotion" };
		String[] fields = { "id_promo,name_promo, year" };
		String condition = "id_promo = '" + idPromo + "'";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = process.Get_DB(conn, t);
		while (result.next()) {
			promotion.setId(result.getString(1));
			promotion.setClasses(findClasseByPromotion(promotion.getId()));
			promotion.setName(result.getString(2));
			promotion.setYear(Integer.parseInt(result.getString(3)));
		}
		return promotion;

	}

	public Classe findClasseById(String idClasse) throws Exception {
		Classe classe = new Classe();
		String[] table = { "Classe" };
		String[] fields = { "id_classe,id_promo,name_classe" };
		String condition = "id_classe = '" + idClasse + "'";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = process.Get_DB(conn, t);
		while (result.next()) {
			classe.setId(result.getString(1));
			classe.setIdPromotion(result.getString(2));
			classe.setName(result.getString(3));
			// classe.setEmploi(findEmploiByClass(classe.getId()));
		}
		return classe;

	}

		public Classe findClasseByName(String nameClasse) throws Exception {
			Classe classe = new Classe();
			String[] table = { "Classe" };
			String[] fields = { "id_classe,id_promo,name_classe" };
			String condition = "name_classe = '" + nameClasse + "'";
			String t = queryString.Query_Select(table, fields, condition);
			ResultSet result = process.Get_DB(conn, t);
			while (result.next()) {
				classe.setId(result.getString(1));
				classe.setIdPromotion(result.getString(2));
				classe.setName(result.getString(3));
				classe.setEmploi(findEmploiByClasse(classe.getId()));
			}
			return classe;
		}

	public EmploiDuTemps findEmploiById(String idEmploi) throws Exception {
		EmploiDuTemps emploi = new EmploiDuTemps();
		String[] table = { "Emploi" };
		String[] fields = { "id_emploi,id_classe" };
		String condition = "id_emploi = '" + idEmploi + "'";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = process.Get_DB(conn, t);
		while (result.next()) {
			emploi.setId(result.getString(1));
			emploi.setClasse(result.getString(2));
			emploi.setListSceance(findSceanceByEmploi(emploi.getId()));
		}
		return emploi;
	}

	public Sceance findSceanceById(String idSceance) throws Exception {
		Sceance sceance = new Sceance();
		String[] table = { "Sceance" };
		String[] fields = { "id_sceance,id_module,id_emploi, date_sceance, time_sceance" };
		String condition = "id_sceance = '" + idSceance+ "' ORDER BY date_sceance";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = process.Get_DB(conn, t);
			while (result.next()) {
				SimpleDateFormat formatterDate = new SimpleDateFormat("yy-MM-dd");
				SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm:ss");
				sceance.setId(result.getString(1));
				sceance.setModule(findModuleById(result.getString(2)));
				sceance.setIdEmploi(result.getString(3));
				sceance.setDate((Date) formatterDate.parse(result.getString(4)));
				sceance.setTime((Date) formatterTime.parse(result.getString(5)));
			}
		return sceance;

	}

	public Module findModuleById(String idModule) throws Exception {
		Module module = new Module();
		String[] table = { "Module" };
		String[] fields = { "id_module, id_prof, name_module, duree" };
		String condition = "id_module = '" + idModule + "'";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = process.Get_DB(conn, t);
		while (result.next()) {
			module.setId(result.getString(1));
			module.setIdProfessor(result.getString(2));
			module.setName(result.getString(3));
			module.setDuree(Integer.parseInt(result.getString(4)));
		}
		return module;

	}

	public Module findModuleByName(String nameModule) throws Exception{
		Module module = new Module();
		String[] table = { "Module" };
		String[] fields = { "id_module, id_prof, name_module, duree" };
		String condition = "name_module = '" + nameModule + "'";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = process.Get_DB(conn, t);
		while (result.next()) {
			module.setId(result.getString(1));
			module.setIdProfessor(result.getString(2));
			module.setName(result.getString(3));
			module.setDuree(Integer.parseInt(result.getString(4)));
		}
		return module;

	}

	public List<Module> findAllNameModule() throws Exception {
		List<Module> listAllModule = new ArrayList<Module>();
		String[] table = { "Module" };
		String[] fields = { "*" };
		String condition = "";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = process.Get_DB(conn, t);
		while (result.next()) {
			Module module = new Module();
			module.setId(result.getString(1));
			module.setIdProfessor(result.getString(2));
			module.setName(result.getString(3));
			module.setDuree(Integer.parseInt(result.getString(4)));
			listAllModule.add(module);
		}
		for (Module module : listAllModule)
			System.out.println(module.getName());
		return listAllModule;
	}

	public Professor findProfessorById(String idProf) throws Exception {
		Professor professor = new Professor();
		String[] table = { "Professor" };
		String[] fields = { "id_prof, first_name, last_name, email" };
		String condition = "id_prof = '" + idProf + "'";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = process.Get_DB(conn, t);
		while (result.next()) {
			professor.setId(result.getString(1));
			professor.setFirstName(result.getString(2));
			professor.setLastName(result.getString(3));
			professor.setEmail(result.getString(4));
			professor.setModules(findModuleByProfessor(professor.getId()));
		}
		//System.out.println(professor.getModules().get(0).getName());
		return professor;
	}

	public List<Classe> findClasseByPromotion(String idPromo) throws Exception {
		List<Classe> listClassePromo = new ArrayList<Classe>();
		String[] table = { "Classe" };
		String[] fields = { "id_classe, id_promo, name_classe" };
		String condition = "id_promo = '" + idPromo + "'";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = process.Get_DB(conn, t);
		while (result.next()) {
			Classe classe = new Classe();
			classe.setId(result.getString(1));
			classe.setIdPromotion(result.getString(2));
			classe.setName(result.getString(3));
			classe.setEmploi(findEmploiByClasse(classe.getId()));
			listClassePromo.add(classe);
		}
		return listClassePromo;
	}

	public EmploiDuTemps findEmploiByClasse(String idClasse) throws Exception {
		EmploiDuTemps emploi = new EmploiDuTemps();
		String[] table = { "Emploi" };
		String[] fields = { "id_emploi, id_classe" };
		String condition = "id_classe = '" + idClasse + "'";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = process.Get_DB(conn, t);
		while (result.next()) {
			emploi.setId(result.getString(1));
			emploi.setClasse(result.getString(2));
			emploi.setListSceance(findSceanceByEmploi(emploi.getId()));
		}
		return emploi;
	}

	public List<Sceance> findSceanceByEmploi(String idEmploi) throws Exception {
		List<Sceance> listSceanceEmploi = new ArrayList<Sceance>();
		String[] table = { "Sceance" };
		String[] fields = { "id_sceance, id_module, id_emploi, date_sceance, time_sceance" };
		String condition = "id_emploi = '" + idEmploi + "'ORDER BY date_sceance";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = process.Get_DB(conn, t);
		while (result.next()) {
			Sceance sceance = new Sceance();
			Module module = new Module();
			SimpleDateFormat formatterDate = new SimpleDateFormat("yy-MM-dd");
			SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm:ss");
			sceance.setId(result.getString(1));
			module = findModuleById(result.getString(2));
			sceance.setModule(module);
			sceance.setIdEmploi(result.getString(3));
			sceance.setDate((Date) formatterDate.parse(result.getString(4)));
			sceance.setTime((Date) formatterTime.parse(result.getString(5)));
			listSceanceEmploi.add(sceance);
		}
		return listSceanceEmploi;
	}

	public List<Sceance> findSceanceByModule(String idModule) throws Exception {
		List<Sceance> listSceanceModule = new ArrayList<Sceance>();
		String[] table = { "Sceance" };
		String[] fields = { "id_sceance, id_module, id_emploi, date_sceance, time_sceance" };
		String condition = "id_module = '" + idModule + "'ORDER BY date_sceance";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = process.Get_DB(conn, t);
		while (result.next()) {
			Sceance sceance = new Sceance();
			Module module = new Module();
			SimpleDateFormat formatterDate = new SimpleDateFormat("yy-MM-dd");
			SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm:ss");
			sceance.setId(result.getString(1));
			module = findModuleById(result.getString(2));
			sceance.setModule(module);
			sceance.setIdEmploi(result.getString(3));
			sceance.setDate((Date) formatterDate.parse(result.getString(4)));
			sceance.setTime((Date) formatterTime.parse(result.getString(5)));
			listSceanceModule.add(sceance);
		}
		return listSceanceModule;
	}

	public List<Module> findModuleByProfessor(String idProfessor) throws Exception {
		List<Module> listModuleProfessor = new ArrayList<Module>();
		String[] table = { "Module" };
		String[] fields = { "id_module, id_prof, name_module, duree" };
		String condition = "id_prof = '" + idProfessor + "'";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = process.Get_DB(conn, t);
		while (result.next()) {
			Module module = new Module();
			module.setId(result.getString(1));
			module.setIdProfessor(result.getString(2));
			module.setName(result.getString(3));
			module.setDuree(Integer.parseInt(result.getString(4)));
			listModuleProfessor.add(module);
		}
		return listModuleProfessor;
	}

	public List<Professor> findAllProfessor() throws Exception {
		List<Professor> listProfs = new ArrayList<Professor>();
		String[] table = { "Professor" };
		String[] fields = { "id_prof, first_name, last_name, email " };
		String condition = "";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet resultProfs = process.Get_DB(conn, t);
		while (resultProfs.next()) {
			Professor prof = new Professor();
			prof.setId(resultProfs.getString(1));
			prof.setFirstName(resultProfs.getString(2));
			prof.setLastName(resultProfs.getString(3));
			prof.setEmail(resultProfs.getString(4));
			prof.setModules(findModuleByProfessor(prof.getId()));
			listProfs.add(prof);
		}
		return listProfs;
	}

	public List<Promotion> findAllPromotion() throws Exception {
		List<Promotion> listPromo = new ArrayList<Promotion>();
		String[] table = { "Promotion" };
		String[] fields = { "id_promo, name_promo, year" };
		String condition = "";
		String t = queryString.Query_Select(table, fields, condition);
		ResultSet result = process.Get_DB(conn, t);
		while (result.next()) {
			Promotion promo = new Promotion();
			promo.setId(result.getString(1));
			promo.setName(result.getString(2));
			promo.setYear(Integer.parseInt(result.getString(3)));
			promo.setClasses(findClasseByPromotion(promo.getId()));
			listPromo.add(promo);
		}
		// System.out.println(allPromo.getListPromos().get(1).getClasses().get(0).getEmploi().getListSceance().get(0).getModule().getName());
		return listPromo;
	}

	public EmploiDuTemps findEmploiByPromotionAndClasse(String namePromotion,String nameClasse) throws Exception {
		List<Promotion> promotions = new ArrayList<Promotion>();
		EmploiDuTemps emploi = new EmploiDuTemps();
		List<Classe> listClasse = new ArrayList<Classe>();
		promotions = findAllPromotion();
		for (Promotion promo : promotions) {
			if (promo.getName().equals(namePromotion)) {
				listClasse = promo.getClasses();
				// System.out.println(listClasse.size());
				// System.out.println(listClasse.get(0).getName());
			}
		}
		// System.out.println(listClasse.get(0).getName());
		for (Classe classe : listClasse)
			if (classe.getName().equals(nameClasse)) {
				emploi = classe.getEmploi();
			}
		if (emploi != null) {
			System.out.println(emploi.getId());
		} else {
			System.out.println("Il n'y a pas emploi");
		}
		return emploi;
	}

	public List<Module> findModuleByClasseAndPromotion(String namePromotion, String nameClasse) throws Exception {
		List<Module> listModuleOfClasse = new ArrayList<Module>();
		EmploiDuTemps emploi = new EmploiDuTemps();
		emploi = findEmploiByPromotionAndClasse(namePromotion, nameClasse);
		if (emploi != null) {
			for (Sceance sceance : emploi.getListSceance())
				listModuleOfClasse.add(sceance.getModule());
			HashMap<String, Module> hm = new HashMap<String, Module>();
			for (Module module : listModuleOfClasse) {
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
				listAll.add(module1);
			}
			System.out.println(listAll.size());
			for (Module module:listAll){
				System.out.println(module.getName());
			}
			return listAll;
		} else {
			System.out.println("Il n'y a pas Sceance de ce classe");
			return null;
		}
	}

	public List<String> findNameOfAllPromotion() throws Exception {
		List<String> allNamePromotion = new ArrayList<String>();
		String[] table = { "Promotion" };
		String[] fields = { "name_promo" };
		String condition = "";
		String t = queryString.Query_Select_Distinct(table, fields, condition);
		ResultSet result = process.Get_DB(conn, t);
		while (result.next()) {
			String name;
			name = result.getString(1);
			allNamePromotion.add(name);
		}
		return allNamePromotion;
	}

	public List<String> findNameOfAllClasse() throws Exception {
		List<String> allNameClasse = new ArrayList<String>();
		String[] table = { "Classe" };
		String[] fields = { "name_classe" };
		String condition = "";
		String t = queryString.Query_Select_Distinct(table, fields, condition);
		ResultSet result = process.Get_DB(conn, t);
		while (result.next()) {
			String name;
			name = result.getString(1);
			allNameClasse.add(name);
		}
		return allNameClasse;
	}

	public List<Sceance> findAllSceance() throws Exception {
		List<Sceance> listAllSceance = new ArrayList<Sceance>();
		String[] table = { "Sceance" };
		String[] fields = { "id_sceance, id_module, id_emploi, date_sceance, time_sceance" };
		String condition = "";
		String t = queryString.Query_Select(table, fields, condition) + " ORDER BY date_sceance";
		ResultSet result = process.Get_DB(conn, t);
		while (result.next()) {
			Sceance sceance = new Sceance();
			Module module = new Module();
			SimpleDateFormat formatterDate = new SimpleDateFormat("yy-MM-dd");
			SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm:ss");
			sceance.setId(result.getString(1));
			module = findModuleById(result.getString(2));
			sceance.setModule(module);
			sceance.setIdEmploi(result.getString(3));
			sceance.setDate((Date) formatterDate.parse(result.getString(4)));
			sceance.setTime((Date) formatterTime.parse(result.getString(5)));
			listAllSceance.add(sceance);
		}
		return listAllSceance;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void addSceance(Sceance sceance) throws Exception {
		SimpleDateFormat formatterDate = new SimpleDateFormat("yy-MM-dd");
		SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm:ss");
		String table = "Sceance";
		String[] fields = { "id_sceance", "id_module", " id_emploi","date_sceance", "time_sceance" };
		String[] value = { sceance.getId(), sceance.getModule().getId(),
			sceance.getIdEmploi(), formatterDate.format(sceance.getDate()),
			formatterTime.format(sceance.getTime()) };
		String t = queryString.Query_Insert(table, fields, value);
		if (process.Execute_DB(conn, t))
			System.out.println("Ok!");
	}

	public void deleteSceance(Sceance sceance) throws Exception {
		String table = "Sceance";
		String condition = "id_sceance = '" + sceance.getId() + "'";
		String t = queryString.Query_Delete(table, condition);
		System.out.println(t);
		if (process.Execute_DB(conn, t))
			System.out.println("Ok!");
	}

	public void updateSceance(Sceance sceance) throws Exception  {
		SimpleDateFormat formatterDate = new SimpleDateFormat("yy-MM-dd");
		SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm:ss");
		String table = "Sceance";
		String[] fields = { "id_module", " id_emploi", "date_sceance","time_sceance" };
		String[] value = { sceance.getModule().getId(), 
			sceance.getIdEmploi(),
			formatterDate.format(sceance.getDate()),
			formatterTime.format(sceance.getTime()) };
		String condition = "id_sceance = '" + sceance.getId() + "'";
		String t = queryString.Query_Update(table, fields, value, condition);
		if (process.Execute_DB(conn, t))
			System.out.println("Ok!");
	}

	public void addEmploi(EmploiDuTemps emploi) throws Exception {
		String table = "Emploi";
		String[] fields = { "id_emploi", "id_classe", };
		String[] value = { emploi.getId(), emploi.getClasse() };
		String t = queryString.Query_Insert(table, fields, value);
		if (process.Execute_DB(conn, t))
			System.out.println("Ok!");
	}

	public void deleteEmploi(EmploiDuTemps emploi) throws Exception {
		List<Sceance> listSceance = new ArrayList<Sceance>();
		listSceance = findSceanceByEmploi(emploi.getId());
		for (Sceance sceance : listSceance)
			deleteSceance(sceance);
		String tableE = "Emploi";
		String conditionE = "id_emploi= '" + emploi.getId() + "'";
		String tE = queryString.Query_Delete(tableE, conditionE);
		if (process.Execute_DB(conn, tE))
			System.out.println("Ok!");
	}

	public void addModule(Module module) throws Exception {
		ProcessDB process = new ProcessDB();
		Query queryString = new Query();
		String table = "Module";
		String[] fields = { "id_module", "id_prof", "name_module", "duree" };
		String[] value = { module.getId(), module.getIdProfessor(),
				module.getName(), Integer.toString(module.getDuree()) };
		String t = queryString.Query_Insert(table, fields, value);
		if (process.Execute_DB(conn, t))
			System.out.println("Ok!");

	}

	public void deleteModule(Module module) throws Exception {
		List<Sceance> listSceance = new ArrayList<Sceance>();
		listSceance = findSceanceByModule(module.getId());
		for (Sceance sceance : listSceance)
			deleteSceance(sceance);
		String tableE = "Module";
		String conditionE = "id_module= '" + module.getId() + "'";
		String tE = queryString.Query_Delete(tableE, conditionE);
		if (process.Execute_DB(conn, tE))
			System.out.println("Ok!");
	}

	public void addProfessor(Professor professor) throws Exception {
		String table = "Professor";
		String[] fields = { "id_prof", "first_name", "last_name", "email" };
		String[] value = { professor.getId(), 
				professor.getFirstName(),
				professor.getLastName(), 
				professor.getEmail() };
		String t = queryString.Query_Insert(table, fields, value);
		if (process.Execute_DB(conn, t))
			System.out.println("Ok!");

	}

	public void deleteProfessor(Professor professor) throws Exception {
		List<Module> listModule = new ArrayList<Module>();
		listModule = findModuleByProfessor(professor.getId());
		for (Module module : listModule)
			deleteModule(module);
		String tableE = "Professor";
		String conditionE = "id_professor= '" + professor.getId() + "'";
		String tE = queryString.Query_Delete(tableE, conditionE);
		if (process.Execute_DB(conn, tE))
			System.out.println("Ok!");
	}
}
