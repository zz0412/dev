import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.base.Strings;
import org.apache.commons.collections.map.ListOrderedMap;

import java.util.List;
import java.util.Map;

/**
 * Created by zhou on 2015/12/24.
 */
public class DownloadNovel {
    public static void main(String[] args) {
        try {
            String url = "http://www.23wx.com/html/41/41192/";

            WebClient webClient = new WebClient(BrowserVersion.CHROME);
            // 关闭js和css解析
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setCssEnabled(false);

            HtmlPage page = webClient.getPage(url);
            List<HtmlAnchor> allHtmlAnchor = page.getAnchors();

            Map<String ,HtmlAnchor> htmlAnchorMap = new ListOrderedMap();
            for (HtmlAnchor htmlAnchor : allHtmlAnchor) {
                String s = htmlAnchor.getFirstChild().asText();
                if (Strings.isNullOrEmpty(s)) {
                    continue;
                }
                s = s.trim();

                // 过滤无用数据或重复数据
                if (!htmlAnchorMap.containsKey(s)&&s.startsWith("第")) {
                    htmlAnchorMap.put(s, htmlAnchor);
                }
            }
            for (Map.Entry<String, HtmlAnchor> entry : htmlAnchorMap.entrySet()) {
                System.out.println(entry.getKey());
                HtmlAnchor htmlAnchor = entry.getValue();
                String str = getHtmlAnchorPageText(htmlAnchor);
                if (!Strings.isNullOrEmpty(str)) {
                    Txt.appendToFile("C:\\Users\\zhou\\Desktop\\aaa.txt", str);
                    Thread.sleep(5); // 等待io写入完成，避免同时写入导致异常的情况
                }
            }
            System.out.println("下载完成！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String getHtmlAnchorPageText(HtmlAnchor htmlAnchor) {

        String title = "\n" + htmlAnchor.getFirstChild().asText() + "\n"; // 章节标题
        try {
            Page page = htmlAnchor.click(); // 访问链接
            if (page.isHtmlPage()) {
                // 获取页面的显示内容
                String textContent = ((HtmlPage) page).getBody().getTextContent();
                // 过滤页面中的页首、页脚、广告、多余的换行等
                textContent = textContent.replace("\n\n", "\n");
                textContent = textContent.replace("\t", "");
                textContent = textContent.replace("    ", "");
                textContent = title + textContent.substring(textContent.indexOf("show_share();") + 13, textContent.indexOf("show_htm2();"));
                return textContent;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
