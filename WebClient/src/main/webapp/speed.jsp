<%@page import="java.io.FileWriter"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="com.gargoylesoftware.htmlunit.html.HtmlElement"%>
<%@page import="com.gargoylesoftware.htmlunit.BrowserVersion"%>
<%@page import="com.gargoylesoftware.htmlunit.WebClient"%>
<%@page import="com.gargoylesoftware.htmlunit.html.HtmlPage"%>
<%--
<%@page import="com.itextpdf.text.log.SysoLogger"%>
--%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.MalformedURLException"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.net.URL"%>
<%@ page import="com.gargoylesoftware.htmlunit.html.DomElement" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%--
<script type="text/javascript" src="/scripts/jquery.js"></script>
--%>
<title>Insert title here</title>
<%
    //获取客户端地址
	String ip = request.getRemoteAddr();
	String requestType = "";
	if(request.getHeader("X-Forwarded-For") != null){
		ip = request.getHeader("X-Forwarded-For") + "";
	}
	if(request.getHeader("X-Forwarded-Proto") != null){
		requestType = request.getHeader("X-Forwarded-Proto") + "";
	}
	String userInfo = "";
    //获取客户端ip的相关信息
	if(ip != ""){
        ip = "116.228.153.46";
		String hostUrl = "http://www.ip.cn/index.php?ip=" + ip;
		WebClient client = new WebClient(BrowserVersion.FIREFOX_38);
		HtmlPage page_ = client.getPage(hostUrl); 
        DomElement p = page_.getElementById("result");
		userInfo = p.getTextContent();
	}
%>
<%
    //模拟访问
//	String urlString = "https://www.processon.com/teams";
	String urlString = "https://www.google.com";
	StringBuffer document = new StringBuffer();
	long requestTime = 0;
	long responseTime = 0;
	try { 
        //记录请求时间
		requestTime = System.currentTimeMillis();
		URL url = new URL(urlString); 
		URLConnection conn = url.openConnection();
        conn.setConnectTimeout(120000);
        conn.setReadTimeout(30000);
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); 
		String line = null; 
		while ((line = reader.readLine()) != null){
			document.append(line + "\n"); 
		}
		reader.close(); 
		conn.getInputStream().close();
        //记录响应时间
		responseTime = System.currentTimeMillis();
	} catch (MalformedURLException e) { 
	} catch (IOException e) { 
	} 
	String result = "<div style='font-size:40px;font-weight:bold;'>Response Time </div><div style='font-size:60px;font-weight:bold;color:#f60'>" + (responseTime-requestTime) + " ms</div>";
	
	//写入记录
	try {
        FileWriter writer = new FileWriter("/opt/info.txt", true);
        writer.write(userInfo + " response time:"+ (responseTime-requestTime) + " -- type:" + requestType +"\r\n");
        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
%>
</head>
<body>
<div style="font-size:12px;color:#999;font-family:微软雅黑;position:absolute;left:5px;bottom:2px;"><%= userInfo %></div>
<div style="width:400px;height:200px;position:absolute;left:50%;top:50%;margin-left:-200px;margin-top:-100px;text-align:center;" id="speed"><%= result %></div>
</body>
</html>