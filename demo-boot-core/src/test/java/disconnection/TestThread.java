package disconnection;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.StyleSet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

public class TestThread {
    public static void main(String[] args){
        ExcelWriter writer = new ExcelWriter("D:/temp/test.xlsx", AppsFly.class.getSimpleName());
        writer.getStyleSet().setAlign(HorizontalAlignment.RIGHT, VerticalAlignment.CENTER);

        writer.write(CollUtil.newArrayList(new AppsFly()));
        writer.setColumnWidth(-1, 30);
        StyleSet styleSet = writer.getStyleSet();
        CellStyle cellStyle = styleSet.getCellStyleForNumber();
        cellStyle.setDataFormat(writer.getWorkbook().createDataFormat().getFormat("@"));
        writer.getSheet().setDefaultColumnStyle(2, cellStyle);
        writer.setRowStyleIfHasData(1, cellStyle);


        writer.setSheet(GoogleFireBase.class.getSimpleName());
        writer.write(CollUtil.newArrayList(new GoogleFireBase()));
        writer.setColumnWidth(-1, 30);

        writer.close();
    }

}
