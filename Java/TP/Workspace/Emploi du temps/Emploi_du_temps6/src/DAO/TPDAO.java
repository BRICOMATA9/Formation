package DAO;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.JOptionPane;

import BDD.TP;
import SQL.DB;
import SQL.SQL;

public class TPDAO implements IDAO<TP> {

    private DB db;
	private SQL queryString = new SQL();
	private SimpleDateFormat formatterDate = new SimpleDateFormat("yy-MM-dd");
	private SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm:ss");

    private TPDAO() {
    	db = DB.getInstance();
    }
    private static IDAO<TP> iTPDAO;

    public static IDAO<TP> getInstance() {
        if (iTPDAO == null) {
            iTPDAO = new TPDAO();
        }
        return iTPDAO;
    }
	
	public Stream<Integer> getId(){
		List<Integer> list = new ArrayList<Integer>();
		return list.stream();
	}
	public <V>TP getById(V idTP) throws Exception{
		TP tp = new TP();
		return tp;
	}
    
	public Stream<TP> getAll(){
		List<TP> listAllTP = new ArrayList<TP>();
		try{
			String[] table = { "TP" };
			String t = queryString.Query_Select(table, TPAtt, "")+ " ORDER BY dateDebut";;
			ResultSet result = db.Get_DB( t);
			while (result.next()) {
				TP tP = new TP();
				tP.setIdModule(ModuleDAO.getInstance().getById(result.getString(1)));
				tP.setIdSalle(SalleDAO.getInstance().getById(result.getInt(2)));
				tP.setIdEnseignant(EnseignantDAO.getInstance().getById(result.getInt(3)));
				tP.setIdSection(SectionDAO.getInstance().getById(result.getInt(4)));
				tP.setIdGroupe(GroupeDAO.getInstance().getById(result.getInt(5)));
				tP.setDateDebut((Date) formatterDate.parse(result.getString(6)));
				String s = result.getString(7);
				System.out.println(s);
				tP.setHeureDebut((Date) formatterTime.parse(s));
				tP.setDuree((Date) formatterTime.parse(result.getString(8)));
				listAllTP.add(tP);
			}
		}catch(Exception x){
			JOptionPane.showMessageDialog(null,x.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
		}
		return listAllTP.stream();
	}
	
	public void ajouter(TP tP) throws Exception {
		String t = queryString.Query_Insert("TP", TPAtt, TPVal(tP));
		if (db.Execute_DB(t)) System.out.println("Ok!");
	}

	public Boolean supprimer(TP tP) throws Exception {
		String cond =  condTP(tP);
		System.out.println(cond);
		String t = queryString.Query_Delete("TP", condTP(tP));
		return (db.Execute_DB(t));
	}

	public Boolean modifier(TP tP) throws Exception  {
		String t = queryString.Query_Update("TP", TPAtt, TPVal(tP), condTP(tP));
		return (db.Execute_DB(t));
	}
	
	private String[] TPAtt = {"idModule","idSalle","idEnseignant","idSection","idGroupe","dateDebut","heureDebut","durree" };
	private String[] TPVal(TP tP) {
		String[] res = new String[8];
		res[0] = tP.getIdModule().getIdModule().toString();
		res[1] = tP.getIdSalle().getIdSalle().toString();
		res[2] = tP.getIdEnseignant().getIdPersonne().toString();
		res[3] = tP.getIdSection().getIdSection().toString();
		res[4] = tP.getIdGroupe().getIdGroupe().toString();
		res[5] = formatterDate.format(tP.getDateDebut());
		res[6] = formatterTime.format(tP.getHeureDebut());
		res[7] = formatterTime.format(tP.getDuree());
		return res;
	}
	
	public Stream<TP> getAllSeance(){
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
				tP.setIdModule(ModuleDAO.getInstance().getById(result.getString(1)));
				tP.setIdSalle(SalleDAO.getInstance().getById(result.getInt(2)));
				tP.setIdEnseignant(EnseignantDAO.getInstance().getById(result.getInt(3)));
				tP.setIdSection(SectionDAO.getInstance().getById(result.getInt(4)));
				tP.setIdGroupe(GroupeDAO.getInstance().getById(result.getInt(5)));
				tP.setDateDebut((Date) formatterDate.parse(result.getString(6)));
				tP.setHeureDebut((Date) formatterTime.parse(result.getString(7)));
				tP.setDuree((Date) formatterTime.parse(result.getString(8)));
				listAllTP.add(tP);
			}
		}catch(Exception x){
			JOptionPane.showMessageDialog(null,x.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
		}
		return listAllTP.stream();
	}
	
	private String condTP (TP tP){
		return "idModule = '"+tP.getIdModule().getIdModule()+
		"' AND idSalle = '"+tP.getIdSalle().getIdSalle()+
		"' AND idEnseignant = '"+tP.getIdEnseignant().getIdPersonne()+
		"' AND idSection = '"+tP.getIdSection().getIdSection()+
		"' AND idGroupe = '"+tP.getIdGroupe().getIdGroupe()+
		"' AND dateDebut = '"+formatterDate.format(tP.getDateDebut())+
		"' AND heureDebut = '"+formatterTime.format(tP.getHeureDebut())+
		"' AND durree = '"+formatterTime.format(tP.getDuree())+"'";
	}

}