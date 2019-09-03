import java.util.Iterator;
import java.util.Vector;
import java.sql.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.event.TableModelEvent;

public class JDBCAdapter extends AbstractTableModel{
     Statement            statement;
     ResultSet           resultSet;
     String[]             columnNames = {};
     Vector		rows = new Vector();
     ResultSetMetaData    metaData;
    
    public JDBCAdapter(Statement statement) {
       this.statement = statement;
    }
    
    public void executeQuery(String query) throws SQLException   {
        if (statement == null) {
            System.err.println("There is no database to execute the query.");
            return;
        }
        
        resultSet = statement.executeQuery(query);
        metaData = resultSet.getMetaData();
        
        int numberOfColumns =  metaData.getColumnCount();
        columnNames = new String[numberOfColumns];
        // Get the column names and cache them.
        // Then we can close the connection.
        for(int column = 0; column < numberOfColumns; column++) {
            columnNames[column] = metaData.getColumnLabel(column+1);
        }
        
        // Get all rows.
        rows = new Vector();
        while(resultSet.next()) {
            Vector newRow = new Vector();
            for (int i = 1; i <= getColumnCount(); i++) {
                newRow.addElement(resultSet.getObject(i));
            }
            rows.addElement(newRow);
        }
        //  close(); Need to copy the metaData, bug in jdbc:odbc driver.
        fireTableChanged(null); // Tell the listeners a new table has arrived.
    }
    
    public void close() {
        System.out.println("Closing db connection ...");
        try {
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }
    
    //////////////////////////////////////////////////////////////////////////
    //
    //             Implementation of the TableModel Interface
    //
    //////////////////////////////////////////////////////////////////////////
    
    // MetaData
    
    public String getColumnName(int column) {
        if (columnNames[column] != null) {
            return columnNames[column];
        } else {
            return "";
        }
    }
    
    public Class getColumnClass(int column) {
        int type;
        try {
            type = metaData.getColumnType(column+1);
        } catch (SQLException e) {
            return super.getColumnClass(column);
        }
        
        switch(type) {
            case Types.CHAR:
            case Types.VARCHAR:
            case Types.LONGVARCHAR:
                return String.class;
                
            case Types.BIT:
                return Boolean.class;
                
            case Types.TINYINT:
            case Types.SMALLINT:
            case Types.INTEGER:
                return Integer.class;
                
            case Types.BIGINT:
                return Long.class;
                
            case Types.FLOAT:
            case Types.DOUBLE:
                return Double.class;
                
            case Types.DATE:
                return java.sql.Date.class;
                
            default:
                return Object.class;
        }
    }
    
    public boolean isCellEditable(int row, int column) {
        try {
            return metaData.isWritable(column+1);
        } catch (SQLException e) {
            return false;
        }
    }
    
    public synchronized int getColumnCount() {
        return columnNames.length;
    }
    
    // Data methods
    
    public int getRowCount() {
        return rows.size();
    }
    
    public synchronized Object getValueAt(int aRow, int aColumn) {
        Vector row = (Vector)rows.elementAt(aRow);
        return row.elementAt(aColumn);
    }
     public Object[] getColonne1(){
        Object[] obj = new Object[rows.size()];
        for(int i = 0 ; i<rows.size();i++){
            obj[i] = ((Vector)rows.get(i)).firstElement();
        }
        return obj;
    }
    public String dbRepresentation(int column, Object value) {
        int type;
        
        if (value == null) {
            return "null";
        }
        
        try {
            type = metaData.getColumnType(column+1);
        } catch (SQLException e) {
            return value.toString();
        }
        
        switch(type) {
            case Types.INTEGER:
            case Types.DOUBLE:
            case Types.FLOAT:
                return value.toString();
            case Types.BIT:
                return ((Boolean)value).booleanValue() ? "1" : "0";
            case Types.DATE:
                return value.toString(); // This will need some conversion.
            default:
                return "\""+value.toString()+"\"";
        }
        
    }
    
    public void setValueAt(Object value, int row, int column) {
        try {
            String tableName = metaData.getTableName(column+1);
            
            // Some of the drivers seem buggy, tableName should not be null.
            if (tableName == null) {
                System.out.println("Table name returned null.");
            }
            String columnName = getColumnName(column);
            String query =
                    "update "+tableName+
                    " set "+columnName+" = "+dbRepresentation(column, value)+
                    " where ";
            // We don't have a model of the schema so we don't know the
            // primary keys or which columns to lock on. To demonstrate
            // that editing is possible, we'll just lock on everything.
            for(int col = 0; col<getColumnCount(); col++) {
                String colName = getColumnName(col);
                if (colName.equals("")) {
                    continue;
                }
                if (col != 0) {
                    query = query + " and ";
                }
                query = query + colName +" = "+
                        dbRepresentation(col, getValueAt(row, col));
            }
            System.out.println(query);
            System.out.println("Not sending update to database");
            /**************************/
            statement.executeQuery(query);
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Update failed");
        }
        Vector dataRow = (Vector)rows.elementAt(row);
        dataRow.setElementAt(value, column);
        
    }
}