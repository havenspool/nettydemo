package com.havens.nettydemo.db;

import com.havens.nettydemo.entity.DBObject;
import com.havens.nettydemo.entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;

/**
 * Created by havens on 15-8-12.
 */
public class DBObjectDAO implements DAO{
    protected QueryRunner innerRunner;
    protected GenKeyQueryRunner innerInsertRunner;
    public DBObjectDAO(QueryRunner queryRunner) {
        setQueryRunner(queryRunner);
    }

    public void setQueryRunner(QueryRunner queryRunner) {
        innerRunner = queryRunner;
        innerInsertRunner = new GenKeyQueryRunner<Long>(queryRunner.getDataSource(),
                new ScalarHandler<Long>());
    }

    public void update(DBObject obj) throws DBException {
        update(obj, obj.getTableName());
    }

    public void insert(DBObject obj) throws DBException {
        insert(obj, obj.getTableName());
    }

    public void delete(DBObject obj) throws DBException {
        delete(obj, obj.getTableName());
    }

    public void update(DBObject obj, String table) throws DBException {

    }

    public void delete(DBObject obj, String table) throws DBException {

    }

    public void insert(DBObject obj, String table) throws DBException {
        System.out.println(table);
        try {
            Field keyField = DBObjectManager.getInsertIncrKeyField(table);
            System.out.println(keyField);
            Set<String> columns = DBObjectManager.getTableAllColumnsNoIncr(table); // true means if it is not auto increase then add key's column
            Object[] objs = new Object[columns.size()];
            int count = 0;
            for (String column : columns) {
                objs[count] = obj.getValueByField(column);
                count++;
            }

            String sql = DBObjectManager.getInsertSQLByTable(table);
            // no thread safe
            int mount = innerInsertRunner.insert(sql, objs);
            if (mount < 1) {
                throw new SQLException("No data insert." + sql + "\n" + obj);
            }

            if (keyField != null /* is auto increase */) {
                if (keyField.getType().equals(Integer.TYPE)) {
                    keyField.set(obj, ((Long) innerInsertRunner.getGeneratedKeys()).intValue());
//                        System.out.println(sql);
                } else if (keyField.getType().equals(Long.TYPE)) {
                    keyField.set(obj, innerInsertRunner.getGeneratedKeys());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException(e.getMessage());
        }
    }

    public void update(Collection<? extends DBObject> objs, String table_name) throws DBException {

    }

    public void insert(Collection<? extends DBObject> objs, String table_name) throws DBException {

    }

    public void delete(Collection<? extends DBObject> objs, String table_name) throws DBException {

    }

    public static void main(String[] args) throws DBException{
        User user=new User();
        user.id=10001;
        user.name="havens";
        user.pwd="123456";

        DBObjectDAO dao=new DBObjectDAO(DataSourceManager.getQueryRunner());
        dao.insert(user);
    }
}
