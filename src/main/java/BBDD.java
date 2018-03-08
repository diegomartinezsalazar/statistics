import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BBDD {

    // Declare the JDBC objects.
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    public void openconnection()
    {

        String connectionUrl = "jdbc:sqlserver://DESKTOP-5DDLJ2R\\SQLEXPRESS;" +
                "databaseName=VOLSTATSDB;user=gueststats;password=gueststats";

        try {
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            con = DriverManager.getConnection(connectionUrl);
        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            //if (rs != null) try { rs.close(); } catch(Exception e) {}
            //if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            //if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }

    public void insertDataRow (String matchId, String data) {
        // Create and execute an SQL statement that returns some data.
        String SQL = "INSERT INTO raw_match " + "VALUES ('" + matchId + "', '" + data + "')";
        try {
            stmt = con.createStatement();
            if (!stmt.execute(SQL))
            {
                new Exception ();
            }

            // Iterate through the data in the result set and display it.
            //while (rs.next()) {
            //    System.out.println(rs.getString(4) + " " + rs.getString(6));

            //}
        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            //if (rs != null) try { rs.close(); } catch(Exception e) {}
            //if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            //if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }

    public void deleteRawMatchTable (){
        // Create and execute an SQL statement that returns some data.
        String SQL = "TRUNCATE TABLE raw_match";
        try {
            stmt = con.createStatement();
            if (!stmt.execute(SQL))
            {
                new Exception ();
            }

            // Iterate through the data in the result set and display it.
            //while (rs.next()) {
            //    System.out.println(rs.getString(4) + " " + rs.getString(6));

            //}
        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            //if (rs != null) try { rs.close(); } catch(Exception e) {}
            //if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            //if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }

    public ArrayList readMatch (String matchId){
        String SQL = "Select Data From raw_match Where match_Id = '" + matchId + "'";
        ArrayList result = new ArrayList();
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                String data = rs.getString("data");
                result.add(data);
            }
            // Handle any errors that may have occurred.
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            //if (rs != null) try { rs.close(); } catch(Exception e) {}
            //if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            //if (con != null) try { con.close(); } catch(Exception e) {}
        }
        return result;
    }

    public ArrayList readCloseWordsFromBBDD(String word){
        String SQL = "Select Type, Translation From close_words_translation Where Type = '" + word + "'";
        ArrayList result = new ArrayList();
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                String data = rs.getString("Translation");
                result.add(data);
            }
            // Handle any errors that may have occurred.
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            //if (rs != null) try { rs.close(); } catch(Exception e) {}
            //if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            //if (con != null) try { con.close(); } catch(Exception e) {}
        }
        return result;
    }

    public ArrayList readSkillsFromBBDD(String word){
        String SQL = "Select Type, Translation From skills_translation Where Skill = '" + word + "'";
        ArrayList result = new ArrayList();
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                String data = rs.getString("Translation");
                result.add(data);
            }
            // Handle any errors that may have occurred.
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            //if (rs != null) try { rs.close(); } catch(Exception e) {}
            //if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            //if (con != null) try { con.close(); } catch(Exception e) {}
        }
        return result;
    }
}