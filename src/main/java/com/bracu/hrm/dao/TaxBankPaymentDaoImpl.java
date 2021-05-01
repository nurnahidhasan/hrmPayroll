package com.bracu.hrm.dao;

import com.bracu.hrm.exception.ServiceConditionException;
import com.bracu.hrm.exception.SystemException;
import com.bracu.hrm.model.payroll.TaxBankPayment;
import com.bracu.hrm.util.SQLDataSource;
import com.bracu.hrm.util.SQLDataSourceTax;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rana on 3/11/18.
 */
@Repository("taxBankPayment")
@Slf4j
public class TaxBankPaymentDaoImpl extends AbstractDao<Integer, TaxBankPayment> implements TaxBankPaymentDao {
    @Override
    public void save(TaxBankPayment taxBankPayment) {
        try {
            persist(taxBankPayment);
        }catch (ConstraintViolationException e){
            log.error(e.getMessage());
            throw new SystemException(e.getMessage());
        }catch (JDBCConnectionException e){
            log.error(e.getMessage());
            throw new SystemException(e.getMessage());
        }catch (UnsupportedOperationException e){
           log.error(e.getMessage());
            throw new ServiceConditionException(e.getMessage());
        }catch (DataAccessException e){
            log.error(e.getMessage());
            throw new ServiceConditionException(e.getMessage());
        }catch (PersistenceException e){
            log.error(e.getMessage());
            throw new ServiceConditionException(e.getMessage());
        }
    }

    @Override
    public List<TaxBankPayment> getList() {
        String sql=
                "SELECT\n" +
                        "*\n"+
                        "FROM \n"+
                        "tax_bank_payment ";
        return executeSQL(sql);
    }
    @Override
    public List findAllThirdParty() {
        Connection con  = new SQLDataSourceTax().getSqlConnection();
        List allResult = new ArrayList();
        ResultSet rs = null;
        ArrayList hotelResultList = new ArrayList();
        try
        {
            con = new SQLDataSourceTax().getSqlConnection();
            Statement s1 = con.createStatement();
            String sqlString = "select id, name + ' - ' + id as partyName from admin_party_setup_t";
            rs = s1.executeQuery(sqlString);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
             hotelResultList = new ArrayList(50);
            while (rs.next()){
                HashMap row = new HashMap(columnCount);
                for(int i=1; i<=columnCount; ++i){
                    row.put(rsmd.getColumnName(i),rs.getObject(i));
                }
                hotelResultList.add(row);
            }

        }catch (SQLException e) {


        }catch
                (Exception e)
        {
            e.printStackTrace();
        }


        return hotelResultList;
    }

    @Override
    public TaxBankPayment getHTaxBankPaymentById(int id) {
        try {
            Criteria criteria=createEntityCriteria();
            criteria.add(Restrictions.eq("id",id));
            return (TaxBankPayment) criteria.uniqueResult();
        } catch (HibernateException e) {
            log.error(e.getMessage());
            throw new SystemException(e.getMessage());

        }
    }
    @Override
    public List<TaxBankPayment> getTaxBankpaymentById(int id) {
        String sql= "SELECT * FROM tax_bank_payment WHERE id="+id;
        return executeSQL(sql);
    }

    public ResultSet printTaxCertificate(Map params) {
        Connection con  = new SQLDataSourceTax().getSqlConnection();
        List allResult = new ArrayList();
        ResultSet rs = null;
        try
        {
            con = new SQLDataSourceTax().getSqlConnection();
            Statement s1 = con.createStatement();
            String sqlString = "select \n" +
                    "ptt.id partyid, \n" +
                    "ptt.name partyName, \n" +
                    "ptt.address partyAddress, \n" +
                    "YEAR(trans_date) yearName, \n" +
                    "MONTH(trans_date) monthName,\n" +
                    "LEFT(DateName( month , \n" +
                    "DateAdd( month , month(trans_date) , 0 ) - 1 ),3)+ ', ' + CONVERT(varchar(10), year(trans_date)) dateOfPayment,\n" +
                    "sum(bill_amount) billAmount, sum(total_tax_deduct) taxAmount\n" +
                    "from third_party_transaction_parent_t\n" +
                    "INNER JOIN admin_party_setup_t ptt on ptt.id = third_party_transaction_parent_t.party_id\n" +
                    "where party_id = '"+ params.get("partyId") +"' AND trans_date BETWEEN '"+params.get("fromDate") +"' and '"+params.get("toDate") +"'\n" +
                    "GROUP BY ptt.address, ptt.name, ptt.id, MONTH(trans_date),\n" +
                    "LEFT(DateName( month , DateAdd( month , month(trans_date) , 0 ) - 1 ),3)+ ', ' + CONVERT(varchar(10), year(trans_date)), \n" +
                    "YEAR(trans_date), \n" +
                    "MONTH(trans_date)\n" +
                    "order BY YEAR(trans_date), \n" +
                    "MONTH(trans_date)";
            rs = s1.executeQuery(sqlString);

            //allResult = Utility.convertResultSetToList(rs);
            //con.close();
            //String result = new result[20];

        }catch (SQLException e) {


        }catch
                (Exception e)
        {
            e.printStackTrace();
        }


        return rs;
    }

}

