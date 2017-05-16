package cn.hsnss.acfun;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser implements Runnable{
	public Parser(String htmlurl, HConfig hConfig, DLConfig dConfig) {
		if(!htmlurl.contains("http://"))
			htmlurl = "http://" + htmlurl;
		this.htmlurl = htmlurl;
		this.savepath = hConfig.savepath;
		if(hConfig.os == "Windows")
			this.savepath += "\\";
		else
			this.savepath += "/";
		this.hConfig = hConfig;
		this.downloader = new Downloader(dConfig);
		if(!Default())
			System.out.println("Default failed.");
	}
	public void run() {
		int i = 0;
		for(String url : picUrl) {
			if(isVisitedOrSet(i)) {
				i++;
				continue;
			}
			downloader.download(url, i++ + ".jpg");
		}
	}

	private boolean setHtmlContent() {
		try {
			URL url = new URL(htmlurl);
			String charset = "UTF-8";
			int sec_timeout = 1000;
			URLConnection con = url.openConnection();
			con.setDoOutput(true);
			con.setReadTimeout(60 * sec_timeout);
			con.setRequestProperty("User-Agent", hConfig.UA);
			InputStream instream = con.getInputStream();
			htmlContent = InStream2String(instream, charset);
		} catch (MalformedURLException e) {
			System.out.println("Fail to create a url from the pic.");
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Fail to connect to the url.");
			return false;
		}
		return true;
	}
	private void getTitle() {
		try {
			Pattern pattern = Pattern.compile("(<title>(.*)</title>)|((<TITLE>(.*)</TITLE>))");
			Matcher matcher = pattern.matcher(htmlContent);
			if(matcher.find())
				title = matcher.group(0);
			title = title.replaceAll("<title>", "");
			title = title.replaceAll("</title>", "");
			title = title.replaceAll("\\?", "");
			title = title.replaceAll("\\*", "");
			title = title.replaceAll("\\\\", "");
			title = title.replaceAll(":", "");
			title = title.replaceAll("\\/", "");
			title = title.replaceAll("\"", "");
			title = title.replaceAll("\'", "");
			title = title.replaceAll("<", "");
			title = title.replaceAll(">", "");
			title = title.replaceAll("|", "");
		} catch (IllegalStateException e) {
			System.out.println("Fail to match the regex");
		}
	}
	private void getPicUrl() {
		picUrl = new ArrayList<>();
		String regex = "";
		int i = 1;
		regex = hConfig.regex.get(0);
		while(i < hConfig.regex.size())
			regex += "|" + hConfig.regex.get(i++);
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(htmlContent);
		while(matcher.find()) {
			String s = matcher.group(0);
			boolean flag = false;
			for(String ss : hConfig.missurl) {
				if(s.contains(ss)) {
					flag = true;
					break;
				}
			}
			if(flag)
				continue;
			picUrl.add(s);
		}
	}
	public boolean Default() {
		if(!setHtmlContent())
			return false;
		getTitle();
		getPicUrl();
		visited = new boolean[picUrl.size()];
		defaultvisited();
		savepath += title;
		new File(savepath).mkdir();
		if(hConfig.os == "Windows")
			savepath += "\\";
		else
			savepath += "/";
		downloader.setSavepath(savepath);
		return true;
	}

	private static String InStream2String(InputStream instream, String charset) throws IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(instream, charset));
		StringBuffer stringbuffer = new StringBuffer();
		String str;
		while((str = buffer.readLine()) != null)
			stringbuffer.append(str);
		return stringbuffer.toString();
	}

	void defaultvisited() {
		int i = 0;
		while(i < visited.length)
			visited[i++] = false;
	}
	synchronized boolean isVisitedOrSet(int i) {
		if(visited[i])
			return true;
		visited[i] = true;
		return false;
	}
	String htmlurl;
	String savepath;
	HConfig hConfig;
	Downloader downloader;

	String htmlContent;
	String title;
	ArrayList<String> picUrl;
	boolean[] visited;
}
