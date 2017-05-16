package cn.hsnss.acfun;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskMain implements Runnable {
	TaskMain(HConfig hConfig, DLConfig dlConfig) {
		this.hConfig = hConfig;
		this.dlConfig = dlConfig;
	}
	void setHtmlurl(String htmlurl) {
		this.htmlurl = new ArrayList<>();
		Scanner scanner = new Scanner(htmlurl);
		while(scanner.hasNext())
			this.htmlurl.add(scanner.nextLine());
		visited = new boolean[this.htmlurl.size()];
		defaultvisited();
	}
	public void run() {
		int i = 0;
		for(String url : htmlurl) {
			if(isVisitedOrSet(i)) {
				i++;
				continue;
			}
			Thread[] t = new Thread[hConfig.threadnum];
			int j = 0;
			parser = new Parser(url, hConfig, dlConfig);
			while(j < hConfig.threadnum)
				t[j++] = new Thread(parser, "Download");
			try {
				j = 0;
				while(j < hConfig.threadnum)
					t[j++].start();
				j = 0;
				while(j < hConfig.threadnum)
					t[j++].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		}
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

	boolean[] visited;
	ArrayList<String> htmlurl;
	Parser parser;
	HConfig hConfig;
	DLConfig dlConfig;
}
