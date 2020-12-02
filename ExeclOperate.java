package com.lx.test01;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;

import com.google.common.base.Preconditions;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
*
* 向数据库导入Excel中的注释至表的注释中
*
*
*
*
* */

public class ExeclOperate {
    //获取数据库连接
    public Connection conn(){
        Connection con=null;
        try {
            //第一步：加载JDBC驱动
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //第二步：创建数据库连接
            con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.100:1521:wisdomdb", "j2020", "j2020");

        }catch(ClassNotFoundException cnf){
            System.out.println("driver not find:"+cnf);

        }catch(SQLException sqle){
            System.out.println("can't connection db:"+sqle);

        }
        catch (Exception e) {
            System.out.println("Failed to load JDBC/ODBC driver."+e);

        }

        return con;
    }
    //读取excel
    public void getExcel() throws Exception {
        InputStream is = new FileInputStream(new File("C:\\Users\\12390\\Desktop\\135工商库说明.xlsx"));
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        // 获取每一个工作薄（sheet）
        for (int numSheet = 1; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }


               //添加注释至字段
           // addCommentToField(xssfSheet);

            //添加注释至表
            addCommentToTable(xssfSheet);



        }
    }
    public  void addCommentToField(  XSSFSheet xssfSheet )throws Exception {

        // 获取当前工作薄的每一行
        for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
            if (xssfRow != null) {
                //读取第1列数据
                if (xssfRow.getCell(0)!=null){
                    String one = getValue(xssfRow.getCell(0));

                }




                //读取第二列数据
                String two = getValue(xssfRow.getCell(1));
                //读取第4列数据

                String four = getValue(xssfRow.getCell(3));
                String five=new String();
                if(xssfRow.getCell(4) !=null){
                    five = getValue(xssfRow.getCell(4));

                }

                if(five!=""| five !=null){
                    String addc=" COMMENT ON COLUMN "+two+"."+four+" IS '"+five+"'";
                    System.out.println("SQL:"+addc);

                    editComment(addc);

                }

            }
        }

    }

    public  void addCommentToTable(  XSSFSheet xssfSheet )throws Exception {

        // 获取当前工作薄的每一行
        for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
            if (xssfRow != null) {
                //读取第3列数据
                String three =new String();
                if (xssfRow.getCell(2)!=null){
                  three = getValue(xssfRow.getCell(2));

                }




                //读取第二列数据

                String two=new String();
                if(xssfRow.getCell(1) !=null){
                    two = getValue(xssfRow.getCell(1));

                }

                if(two!="" && two!=null  &&  three!="" && three!=null ){
                    String addc=" COMMENT ON  table "+two+" IS '"+three+"'";
                    System.out.println("SQL:"+addc);

                    editComment(addc);

                }

            }
        }

    }
    //转换数据格式
    private String getValue(XSSFCell xssfRow) {


        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfRow.getNumericCellValue());
        }  else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BLANK) {
            return "";
        }else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }


    //添加注释
    public int editComment(String sql) throws SQLException{
        Connection conn = this.conn();
        int re = 0;
        try{
            conn.setAutoCommit(false);//事务开始
            PreparedStatement sm = conn.prepareStatement(sql);

            re = sm.executeUpdate();
            if(re < 0){               //插入失败
                conn.rollback();      //回滚
                sm.close();
                conn.close();
                return re;
            }
            conn.commit();            //插入正常
            sm.close();
            conn.close();
            return re;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        conn.close();
        return 0;
    }

    //测试
    public static void main(String[] args)  {

        ExeclOperate e=new ExeclOperate();

        try {
            e.getExcel();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println("导入完成！");




    }


}
