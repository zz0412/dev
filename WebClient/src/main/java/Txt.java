import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Txt {

    /**
     * 读取文件，返回<code>String</code>
     *
     * @param path    文件路径
     * @param charset 字符集，可为空
     * @return <code>String</code>
     * @throws IOException
     */
    public static String getString(String path, String charset) throws IOException {

        //返回字符串
        String returnStr = "";

        FileInputStream fis = new FileInputStream(path);

        InputStreamReader isr;

        if (charset!=null&&charset.trim().length()>0) {

            isr = new InputStreamReader(fis);

        } else {
            // 处理中文乱码
            isr = new InputStreamReader(fis, charset);
        }
        BufferedReader br = new BufferedReader(isr);

        String s;
        while ((s = br.readLine()) != null) {

            returnStr += s;
        }
        br.close();

        return returnStr;
    }

    /**
     * 读取文件，返回<code>List<String></code>
     *
     * @param path    文件路径
     * @param charset 字符集，可为空
     * @return <code>String</code>
     * @throws IOException
     */
    public static List<String> getList(String path, String charset) throws IOException {

        // 返回的集合
        List<String> list = new ArrayList<String>();

        FileInputStream fis = new FileInputStream(path);

        InputStreamReader isr;

        if (charset!=null&&charset.trim().length()>0) {

            isr = new InputStreamReader(fis);

        } else {
            // 处理中文乱码
            isr = new InputStreamReader(fis, charset);
        }
        BufferedReader br = new BufferedReader(isr);

        String s;
        while ((s = br.readLine()) != null) {

            list.add(s.trim());
        }
        br.close();

        return list;
    }


    /**
     * 将内容覆盖写入文件中
     *
     * @param filePath 文件路径
     * @param str      需要写入文件的内容
     * @throws IOException
     */

    public static void coverToFile(String filePath, String str) throws IOException {

        File file = new File(filePath);

        // 不存在则创建该文件
        if (!file.exists()) {

            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(filePath);

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(str);

        bufferedWriter.close();

        fileWriter.close();
    }

    /**
     * 将内容追加写入到文件中
     *
     * @param filePath 文件路径
     * @param str      需要写入文件的内容
     * @throws IOException
     */
    public static void appendToFile(String filePath, String str) throws IOException {

        File file = new File(filePath);

        // 不存在则创建该文件
        if (!file.exists()) {

            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(filePath, true);

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(str);

        bufferedWriter.close();

        fileWriter.close();
    }
}
