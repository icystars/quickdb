package cat.quickdb.db.dbms;

import cat.quickdb.db.AdminBase;
import cat.quickdb.db.connection.ConnectionDB;
import cat.quickdb.db.connection.ConnectionDBFirebird;
import cat.quickdb.db.connection.ConnectionDBPostgre;
import cat.quickdb.db.dbms.firebird.Firebird;
import cat.quickdb.db.dbms.mysql.MySQL;
import cat.quickdb.db.dbms.postgre.PostgreSQL;
import cat.quickdb.db.dbms.sqlite.SQLite;
import cat.quickdb.reflection.EntityManager;

public class DbmsInterpreter {

    public static ConnectionDB connect(AdminBase.DATABASE db, String... args){
        ConnectionDB conex = null;
        switch (db) {
            case MYSQL:
                conex = new ConnectionDB(args);
                conex.connectMySQL();
                break;
            case SQLSERVER:
                conex = new ConnectionDB(args);
                conex.connectSQLServer();
                break;
            case POSTGRES:
                conex = new ConnectionDBPostgre(args);
                conex.connectPostgres();
                break;
            case SQLite:
                conex = new ConnectionDB("", "", args[0], "", "");
                conex.connectSQLite();
                break;
            case FIREBIRD:
                conex = new ConnectionDBFirebird(args);
                conex.connectFirebird();
                break;
        }

        return conex;
    }

    public static boolean createTable(AdminBase.DATABASE db, AdminBase admin, Object entity,
            Object[] objects, EntityManager manager){

        boolean value = false;
        switch(db){
            case SQLite:
                value = SQLite.createTable(admin, entity, objects, manager);
                break;
            case MYSQL:
                value = MySQL.createTable(admin, entity, objects, manager);
                break;
            case POSTGRES:
                value = PostgreSQL.createTable(admin, entity, objects, manager);
                break;
            case FIREBIRD:
                value = Firebird.createTable(admin, entity, objects, manager);
                break;
        }

        return value;
    }

}
