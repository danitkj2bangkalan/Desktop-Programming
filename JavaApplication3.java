// program 1
package javaapplication3;
/**
 *
 * @author mrfirdaus
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JavaApplication3 {
    public static void main(String[] args) throws ClassNotFoundException {
        // Class.forName("org.sqlite.JDBC");
        Connection connection = null;
        try {
        // Membuat database connection
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout 30 detik.
            statement.executeUpdate("DROP TABLE IF EXISTS person");
            //statement.executeUpdate("CREATE TABLE person (id INTEGER, name STRING)");
            String names[] = { "Andi", "Budi", "Cici", "Doni", "Enggar" };
            for (int i = 0; i < names.length; i++) {
                statement.executeUpdate("INSERT INTO person values(' " + (i+1) + "', '" + names[i] + "')");
            }
            statement.executeUpdate("UPDATE person SET name='Adi' WHERE id='1'");
            statement.executeUpdate("DELETE FROM person WHERE id='1'");
            ResultSet resultSet = statement.executeQuery("SELECT * from person");
            while (resultSet.next()) {
                System.out.println("name = " + resultSet.getString("name"));
                System.out.println("id = " + resultSet.getInt("id"));
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
