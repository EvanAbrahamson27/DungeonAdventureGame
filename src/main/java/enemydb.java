package main.java;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author tom capaul
 * @authos isabelle del castillo
 *
 * Simple class to demonstrate SQLite connectivity
 * 1) create connection
 * 2) add a table
 * 3) add entries to the table
 * 4) query the table for its contents
 * 5) display the results
 *
 * NOTE: any interactions with a database should utilize a try/catch
 * since things can go wrong
 *
 * @see <a href="https://shanemcd.org/2020/01/24/how-to-set-up-sqlite-with-jdbc-in-eclipse-on-windows/">
https://shanemcd.org/2020/01/24/how-to-set-up-sqlite-with-jdbc-in-eclipse-on-windows/</a>
 *
 */
public class enemydb {

    public static void main(String[] args) {
        SQLiteDataSource ds = null;

        //establish connection (creates db file if it does not exist :-)
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:stats.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println( "Opened database successfully" );


        //now create a table
        String query = "CREATE TABLE IF NOT EXISTS stats ( " +
                "CHARACTER TEXT NOT NULL, " +
                "HEALTH INTEGER NOT NULL, " +
                "ATK_SPEED INTEGER NOT NULL, " +
                "CHANCE_TO_HIT DOUBLE NOT NULL" +
                "DAMAGE_MIN INTEGER NOT NULL, " +
                "DAMAGE_MAX INTEGER NOT NULL, " +
                "HEAL_CHANCE DOUBLE NOT NULL, " +
                "HEAL_MIN INTEGER NOT NULL" +
                "HEAL_MAX INTEGER NOT NULL )";
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate( query );
            System.out.println( "executeUpdate() returned " + rv );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        System.out.println( "Created stats table successfully" );

        //next insert two rows of data
        System.out.println( "Attempting to insert two rows into stats table" );

        String query1 = "INSERT INTO stats ( CHARACTER, HEALTH, ATK_SPEED, CHANCE_TO_HIT, DAMAGE_MIN, " +
                "DAMAGE_MAX, HEAL_CHANCE, HEAL_MIN, HEAL_MAX ) VALUES ( 'OGRE', '200', '2', '0.6', '30' , " +
                "'60', '0.1', '30', '60' )";
        String query2 = "INSERT INTO stats ( CHARACTER, HEALTH, ATK_SPEED, CHANCE_TO_HIT, DAMAGE_MIN, " +
                "DAMAGE_MAX, HEAL_CHANCE, HEAL_MIN, HEAL_MAX ) VALUES ( 'GREMLIN', '70', '5', '0.8', '15' , " +
                "'30', '0.4', '20', '40' )";
        String query3 = "INSERT INTO stats ( CHARACTER, HEALTH, ATK_SPEED, CHANCE_TO_HIT, DAMAGE_MIN, " +
                "DAMAGE_MAX, HEAL_CHANCE, HEAL_MIN, HEAL_MAX ) VALUES ( 'SKELETON', '100', '3', '0.8', '30' , " +
                "'50', '0.3', '30', '50' )";

        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate( query1 );
            System.out.println( "1st executeUpdate() returned " + rv );

            rv = stmt.executeUpdate( query2 );
            System.out.println( "2nd executeUpdate() returned " + rv );

            rv = stmt.executeUpdate( query3 );
            System.out.println( "3rd executeUpdate() returned " + rv );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }


        //now query the database table for all its contents and display the results
        System.out.println( "Selecting all rows from stats table" );
        query = "SELECT * FROM stats";

        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            //walk through each 'row' of results, grab data by column/field name
            // and print it
            while ( rs.next() ) {
                String character = rs.getString( "CHARACTER" );
                String health = rs.getString( "HEALTH" );
                String atkSpeed = rs.getString("ATK_SPEED");
                String chanceToHit = rs.getString("CHANCE_TO_HIT");
                String dmgMin = rs.getString("DAMAGE_MIN");
                String dmgMax = rs.getString("DAMAGE_MAX");
                String healChance = rs.getString("CHANCE_TO_HEAL");
                String healMin = rs.getString("HEAL_MIN");
                String healMax = rs.getString("HEAL_MAX");

                System.out.println( "Result: Character = " + character +
                        ", health points = " + health + ", minimum damage = " + dmgMin + ", maximum damage ="
                        + dmgMax + ", attack speed = " + atkSpeed + ", chance to hit = " + chanceToHit + "%" +
                        ", chance to heal = " + healChance + ", minimum heal = " + healMin + ", maximum heal = "
                        + healMax );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        System.out.println("press enter to close program/window");
        Scanner input = new Scanner(System.in);
        input.nextLine();
    }

}
