package tmp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;



import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.util.Region;


public class ExcelUtils {
  
  /**
   * 读取EXCEL
   * @param firstrow 从第几行开始读取
   * @return 读取后返回数组
   */
  @SuppressWarnings("deprecation")
  public static String[][] importExcel(File file, int firstrow)
      throws FileNotFoundException, IOException {
    List<String[]> result = new ArrayList<String[]>();
    int rowSize = 0;
    BufferedInputStream in = new BufferedInputStream(new FileInputStream(
        file));
    POIFSFileSystem fs = new POIFSFileSystem(in);
    HSSFWorkbook wb = new HSSFWorkbook(fs);
    HSSFCell cell = null;
    for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
      HSSFSheet st = wb.getSheetAt(sheetIndex);
      for (int rowIndex = firstrow; rowIndex <= st.getLastRowNum(); rowIndex++) {
        HSSFRow row = st.getRow(rowIndex);
        if (row == null) {
          continue;
        }
        int tempRowSize = row.getLastCellNum() + 1;
        if (tempRowSize > rowSize) {
          rowSize = tempRowSize;
        }
        String[] values = new String[rowSize];
        Arrays.fill(values, "");
        boolean hasValue = false;

        for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
          String value = "";
          cell = row.getCell(columnIndex);
          if (cell != null) {
            switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING://读取的格式为字符串
              value = cell.getStringCellValue();
              break;
            case HSSFCell.CELL_TYPE_NUMERIC://读取的格式为数组
              //如果格式为日期格式，自定义格式输出
              if (HSSFDateUtil.isCellDateFormatted(cell)) {
                Date date = cell.getDateCellValue();
                if (date != null) {
                  value = new SimpleDateFormat("yyyy-MM-dd")
                      .format(date);
                } else {
                  value = "";
                }
              } else {
                //如果格式为数值，自定义格式输出
                value = new DecimalFormat().format(cell
                    .getNumericCellValue());
              }
              break;
            case HSSFCell.CELL_TYPE_FORMULA:
              // 导入时如果为公式生成的数据则无值
              value = "";
              break;
              // 导入时如果为空
            case HSSFCell.CELL_TYPE_BLANK:
              break;
            case HSSFCell.CELL_TYPE_ERROR:
              value = "";
              break;
              // 导入时如果为BOOLEAN型 自定义格式输出
            case HSSFCell.CELL_TYPE_BOOLEAN:
              value = (cell.getBooleanCellValue() == true ? "Y"
                  : "N");
              break;
            default:
              value = "";
            }
          }

          values[columnIndex] = rightTrim(value);
          hasValue = true;
        }

        if (hasValue) {
          result.add(values);
        }
      }

      in.close();
      String[][] returnArray = new String[result.size()][rowSize];
      for (int i = 0; i < returnArray.length; i++) {
        returnArray[i] = (String[]) result.get(i);
      }
      return returnArray;
    }
    return null;

  }

  /**
   * 去掉字符串右边的空格
   * 
   * @param str 要处理的字符串
   * @return 处理后的字符串
   */
  public static String rightTrim(String str) {
    if (str == null) {
      return "";
    }
    int length = str.length();
    for (int i = length - 1; i >= 0; i--) {
      if (str.charAt(i) != 0x20) {
        break;
      }
      length--;
    }
    return str.substring(0, length);
  }
  
/**
   * 创建通用EXCEL头部
   * 
   * @param headString 头部显示的字符
   * @param colSum 该报表的列数
   */
  @SuppressWarnings("deprecation")
  public void createNormalHead(String headString, int colSum,HSSFSheet sheet,HSSFWorkbook wb) {
      
    HSSFRow row1 = sheet.createRow(0);

    // 设置第一行
    HSSFCell cell = row1.createCell(0);
    row1.setHeight((short) 800);

    // 定义单元格为字符串类型
    cell.setCellType(HSSFCell.ENCODING_UTF_16);
    cell.setCellValue(new HSSFRichTextString(headString));

    // 指定合并区域
    sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) colSum));

    HSSFCellStyle cellStyle = wb.createCellStyle();

    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
    cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
    //cellStyle.setWrapText(true);// 指定单元格自动换行

    // 设置单元格字体
    HSSFFont font = wb.createFont();
    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    font.setFontName("宋体");
    font.setFontHeight((short) 300);
    cellStyle.setFont(font);

    cell.setCellStyle(cellStyle);
  }
  
  /**
   * 创建通用报表第二行的格式
   * 
   */
  public HSSFCellStyle getCellStyle(HSSFWorkbook wb) {
    // 创建单元格样式
    HSSFCellStyle cellStyle = wb.createCellStyle();
 		// 指定单元格居中对齐
 		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
 		// 指定单元格垂直居中对齐
 		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
 		// 指定当单元格内容显示不下时自动换行
 		//cellStyle.setWrapText(true);
 		// 设置单元格字体
 		HSSFFont font = wb.createFont();
 		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
 		font.setFontName("宋体");
 		font.setFontHeight((short) 200);
 		//font.setFontHeightInPoints((short) 22);
 		cellStyle.setFont(font);
 		return cellStyle;

  }


  public static void main(String[] args) throws IOException {
    String path = ExcelUtils.class.getResource("/").getPath() + "景点坐标点.xlsx";

    String[][] ret = importExcel(new File(path), 1);

    System.out.println( JSON.toJSONString(ret) );

  }
}