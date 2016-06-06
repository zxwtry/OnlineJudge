import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

public class Main {
    private static final String BRAND_NAME_PATH = "brand_name";
    private static final String SOURCE_PATH = "/home/run/wcdata/source/";
    private static HashMap<String, Brand> brandMap; 
    private static ArrayList<String> brandName;
    private static ArrayList<Brand> allBands;
    private static ArrayList<String> brandAlias;
    public static void main(String[] args) {
        init();
        File baseDir = new File(SOURCE_PATH);
        String[] strings = baseDir.list();
        for (String string : strings) {
            readAnTie(string);
            solveName(string);
        }
        Collections.sort(allBands);
        for (Brand brand : allBands)
            System.out.println(brand.brand_name+"\t\t"+brand.count);
    }
    private static void solveName(String string) {
        String[] parts = string.split("_");
        if (parts.length == 2) {
            solveOneLine(parts[1]);
        }
    }
    private static void readAnTie(String tie_Name) {
        File file = new File(SOURCE_PATH + tie_Name);
        if (!file.exists()) {
            System.out.println("wo cao 文件不存在");
            return;
        }
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = bufferedReader.readLine();
            while(line != null) {
                solveOneLine(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
    private static void solveOneLine(String line) {
        line = line.toLowerCase();
        int index = line.indexOf("楼");
        if (-1 != index)
            line = line.substring(index);
        for (String name : brandAlias) {
            if (line.indexOf(name) != -1) {
                brandMap.get(name).add();
            }
        }
    }
    //完成static变量的初始化，从brand_name文件中读取所有品牌信息
    private static void init() {
        brandMap = new HashMap<String, Brand>();
        brandName = new ArrayList<String>();
        brandAlias = new ArrayList<String>();
        allBands = new ArrayList<Brand>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(BRAND_NAME_PATH))));
            String line = br.readLine();
            while(line != null) {
                String[] partStage1 = line.split("[:#]");
                if (partStage1 == null || partStage1.length <= 0)
                    continue;
                Brand brand = new Brand(partStage1[0]);
                allBands.add(brand);
                brandName.add(partStage1[0]);
                for (int in = 0; in < partStage1.length; in ++) {
                    String strnow = partStage1[in];
                    if (strnow == null || strnow.trim().length() == 0)
                        continue;
                    brandAlias.add(strnow);
                    brandMap.put(strnow, brand);
                } 
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
    //品牌对象
    static class Brand implements Comparable<Brand>{
        String brand_name;
        int count;
        public Brand(String brand_name) {
            this.brand_name = brand_name;
            this.count = 0;
        }
        public void add(){
            count ++;
        } 
        public int getCount() {
            return count;
        }
        @Override
        public int compareTo(Brand o) {
            return o.getCount()-count;
        }
    }   
}
