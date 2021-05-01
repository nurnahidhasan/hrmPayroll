package com.bracu.hrm.service;

import com.bracu.hrm.dao.DepartmentDao;
import com.bracu.hrm.dao.MailDao;
import com.bracu.hrm.dao.PaySlipDao;
import com.bracu.hrm.dao.SetupEntityDao;
import com.bracu.hrm.model.Employee;
import com.bracu.hrm.model.email.Mail;
import com.bracu.hrm.model.org.Department;
import com.bracu.hrm.service.payslip.PaySlipService;
import com.bracu.hrm.util.SQLDataSource;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.lowagie.text.pdf.PdfWriter;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import javax.activation.DataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormatSymbols;
import java.util.*;


@Service("departmentService")
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;


    public List<Department> findAll() {
        return departmentDao.listAll();
    }



}
