package ssh;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.awt.Component;
import java.io.*;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Properties;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class App {
    /*
        public static String sql = "SELECT concat(p.pkey, \'-\', i.issuenum) AS \"Ключ проблемы\" ,\nto_char( i.created, \'DD.MM.YYYY HH24:MI\') AS \"Создано\",\ncf1.customvalue AS \"Подразделение ФОМС\",\ncf2.stringvalue AS \"Контактная информация\",\ncf0.customvalue AS \"Подсистема\",\ni.description::TEXT AS \"Описание\",\nit.pname AS \"Тип задачи\",\npr.pname AS \"Приоритет\",\nl.lbl_arr AS \"Метки\",\nto_char( i.resolutiondate, \'DD.MM.YYYY HH24:MI\') AS \"Дата решения\", \ncf3.textvalue AS \"Содержание выполненных работ\", \ncfX.customvalue AS \"Содержание выполненных работ(кратко)\",\nist.pname AS \"Статус\",\nisl.name AS \"Уровень безопасности\",\ni.summary AS \"Тема\",\nrs.pname AS \"Решение\",\ncf4.customvalue AS \"Категория запроса\" \nFROM jiraissue i \n  JOIN project p ON p.id = i.project                                                                                         \n  JOIN issuetype it ON it.id = i.issuetype                                                                                   \n  JOIN priority pr ON pr.id = i.priority                                                                                     \n  LEFT JOIN resolution rs ON rs.id = i.resolution                                                                            \n  LEFT JOIN issuestatus ist ON ist.id = i.issuestatus                                                                        \n  LEFT JOIN schemeissuesecuritylevels isl ON isl.id = i.security                                                             \n  LEFT JOIN LATERAL (SELECT array_agg(l.label ORDER BY l.id) AS lbl_arr                                                      \n                       FROM label l                                                                                          \n                      WHERE l.issue = i.id                                                                                   \n                     ) AS l ON TRUE                                                                                          \n  LEFT JOIN LATERAL (SELECT cfo.customvalue                                                                                  \n                       FROM customfieldvalue cfv                                                                             \n                       JOIN customfield cf ON cf.id = cfv.customfield                                                        \n                       JOIN customfieldoption cfo ON cfo.customfield = cfv.customfield AND cfo.id = cfv.stringvalue::NUMERIC \n                      WHERE cfv.issue = i.id                                                                                 \n                        AND cf.id IN (10100)                                                                                 \n                      LIMIT 1                                                                                                \n                     ) AS cf0 ON TRUE                                                                                        \n  LEFT JOIN LATERAL (SELECT cfo.customvalue                                                                                  \n                       FROM customfieldvalue cfv                                                                             \n                       JOIN customfield cf ON cf.id = cfv.customfield                                                        \n                       JOIN customfieldoption cfo ON cfo.customfield = cfv.customfield AND cfo.id = cfv.stringvalue::NUMERIC \n                      WHERE cfv.issue = i.id                                                                                 \n                        AND cf.id IN (10400)                                                                                 \n                      LIMIT 1                                                                                                \n                     ) AS cfX ON TRUE                                                                                        \n  LEFT JOIN LATERAL (SELECT cfo.customvalue                                                                                  \n                       FROM customfieldvalue cfv                                                                             \n                       JOIN customfield cf ON cf.id = cfv.customfield                                                        \n                       JOIN customfieldoption cfo ON cfo.customfield = cfv.customfield AND cfo.id = cfv.stringvalue::NUMERIC \n                      WHERE cfv.issue = i.id                                                                                 \n                        AND cf.id IN (10101)                                                                                 \n                      LIMIT 1                                                                                                \n                     ) AS cf1 ON TRUE                                                                                        \n  LEFT JOIN LATERAL (SELECT cfv.stringvalue                                                                                  \n                       FROM customfieldvalue cfv                                                                             \n                       JOIN customfield cf ON cf.id = cfv.customfield                                                        \n                      WHERE cfv.issue = i.id                                                                                 \n                        AND cf.id IN (10102)                                                                                 \n                      LIMIT 1                                                                                                \n                     ) AS cf2 ON TRUE                                                                                        \n  LEFT JOIN LATERAL (SELECT cfv.textvalue AS textvalue                                                                       \n                       FROM customfieldvalue cfv                                                                             \n                       JOIN customfield cf ON cf.id = cfv.customfield                                                        \n                      WHERE cfv.issue = i.id                                                                                 \n                        AND cf.id IN (10103)                                                                                 \n                      LIMIT 1                                                                                                \n                     ) AS cf3 ON TRUE                                                                                        \nLEFT JOIN LATERAL (SELECT cfo.customvalue                                                                                    \nFROM customfieldvalue cfv                                                                                                    \nJOIN customfield cf ON cf.id = cfv.customfield                                                                               \nJOIN customfieldoption cfo ON cfo.customfield = cfv.customfield AND cfo.id = cfv.stringvalue::NUMERIC                        \nWHERE cfv.issue = i.id                                                                                                       \nAND cf.id IN (10300)                                                                                                         \nLIMIT 1                                                                                                                      \n) AS cf4 ON TRUE                                                                                                             \n WHERE                                                                                                                       \n       i.created::DATE <@ daterange( ? ::DATE, ? ::DATE, \'[]\')                                                               \n AND p.pkey = ?                                                                                                               \nORDER BY i.id DESC ;\t                                                                                                      \n";
    */
    public static String sql = "";

    public static void main(String[] args) throws MalformedURLException {
        try {
            // System.out.println(System.getProperty("user.dir"));
            File file = new File(System.getProperty("user.dir") + "\\sql.txt");
            FileReader fr = new FileReader(file);
            //BufferedReader br = new BufferedReader(fr);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            String line;
            while ((line = br.readLine()) != null) {
                //обрабатываем считанную строку - пишем ее в консоль
                // System.out.println(line);
                sql = sql + line + '\n';
            }
            System.out.println(sql);
            br.close();
            fr.close();
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        SimpleGUI app = new SimpleGUI(sql);
        app.setVisible(true);
    }

    public static void go(String date1, String date2, String SSHpass, String FileName, String DB, boolean t, boolean[] checkBoxesState) {
        try {
            Connection e = null;
            e = ssh(e, SSHpass);
            if (sql.indexOf("insert") >= 0 || sql.indexOf("update") >= 0 || sql.indexOf("delete") >= 0 || sql.indexOf("truncate") >= 0 || sql.indexOf("drop") >= 0) {
                JOptionPane.showMessageDialog((Component) null, "No Inserts or updates please!", "Output", -1);
            }

            if (!t) {
                PrepareInsert(e, FileName, date1, date2, DB, t, new boolean[4]);
            } else {
                PrepareInsert(e, FileName, date1, date2, DB, t, checkBoxesState);
            }

            JOptionPane.showMessageDialog((Component) null, "Done!\n Файл " + FileName + ".xls создан в " + System.getProperty("user.dir"), "Output", -1);
            System.exit(0);
        } catch (Exception var8) {
            var8.printStackTrace();
            var8.getMessage();
        }

    }

    public static void PrepareInsert(Connection con, String FileName, String date1, String date2, String DB, boolean t, boolean[] arr) {
        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            PreparedStatement statement = con.prepareStatement(sql);
            String SheetName;
            if (!t) {
                statement.setString(1, date1);
                statement.setString(2, date2);
                statement.setString(3, DB);
                SheetName = "Period";
                BeginInsert(wb, SheetName, statement, FileName, date1, date2, DB, t, arr);
            } else {
                for (int i = 0; i < arr.length; ++i) {
                    if (arr[i]) {
                        System.out.printf(" Идёт загрузка %s квартала ", new Object[]{Integer.valueOf(i + 1)});
                        System.out.println();
                        statement.setString(1, i == 0 ? "2017/01/01" : (i == 1 ? "2017/04/01" : (i == 2 ? "2017/07/01" : "2017/10/01")));
                        statement.setString(2, i == 0 ? "2017/03/31" : (i == 1 ? "2017/06/30" : (i == 2 ? "2017/09/30" : "2017/12/31")));
                        statement.setString(3, DB);
                        SheetName = "Quartal" + (i + 1);
                        BeginInsert(wb, SheetName, statement, FileName, date1, date2, DB, t, arr);
                    }
                }
            }
        } catch (Exception var11) {
            ;
        }
    }

    public static void BeginInsert(Workbook wb, String SheetName, PreparedStatement statement, String FileName, String date1, String date2, String DB, boolean t, boolean[] arr) {
        try {
            byte row = 0;
            ResultSet rs = null;
            rs = statement.executeQuery();
            new Date();
            String output = System.getProperty("user.dir") + "/" + FileName + ".xls";
            Sheet list = wb.createSheet(SheetName);
            Row dataRow = list.createRow(row);

            try {
                Cell cell;
                int x;
                FileOutputStream fileout;
                for (x = 0; x < rs.getMetaData().getColumnCount(); ++x) {
                    cell = dataRow.createCell(x);
                    cell.setCellValue(rs.getMetaData().getColumnName(x + 1));
                    fileout = new FileOutputStream(output);

                    try {
                        wb.write(fileout);
                        fileout.close();
                    } catch (IOException var19) {
                        var19.printStackTrace();
                    }
                }

                for (int var22 = row + 1; rs.next(); ++var22) {
                    dataRow = list.createRow(var22);

                    for (x = 0; x < rs.getMetaData().getColumnCount(); ++x) {
                        cell = dataRow.createCell(x);
                        cell.setCellValue(rs.getString(x + 1));
                        fileout = new FileOutputStream(output);
                        wb.write(fileout);
                        fileout.close();
                    }
                }
            } catch (Exception var20) {
                ;
            }
        } catch (Exception var21) {
            ;
        }

    }

    private static void doSshTunnel(String strSshUser, String strSshPassword, String strSshHost, int nSshPort, String strRemoteHost, int nLocalPort, int nRemotePort) throws JSchException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(strSshUser, strSshHost, 22);
        session.setPassword(strSshPassword);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();
        session.setPortForwardingL(nLocalPort, strRemoteHost, nRemotePort);
    }

    public static Connection ssh(Connection con, String Sshpass) {
        try {
            String strSshUser = "bars";
            String strSshPassword = Sshpass.toString();
            String strSshHost = "93.170.52.205";
            byte nSshPort = 22;
            String strRemoteHost = "localhost";
            short nLocalPort = 4443;
            short nRemotePort = 5432;
            String strDbUser = "isupport";
            String strDbPassword = "isup";
            doSshTunnel(strSshUser, strSshPassword, strSshHost, nSshPort, strRemoteHost, nLocalPort, nRemotePort);
            con = DriverManager.getConnection("jdbc:postgresql://localhost:" + nLocalPort + "/jira", strDbUser, strDbPassword);
        } catch (Exception var11) {
            ;
        }

        return con;
    }

}


