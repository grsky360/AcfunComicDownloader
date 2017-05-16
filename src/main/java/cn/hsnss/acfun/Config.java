package cn.hsnss.acfun;

import java.util.ArrayList;
import java.util.Scanner;

public class Config {
	private ArrayList<Node> config;
	private class Node {
		String key, content;
		Node(String key, String content) {
			this.key = key;
			this.content = content;
		}
	}
	public String Search(String key) {
		for(Node n : config)
			if(n.key == key)
				return n.content;
		return null;
	}
	public Config() {
		config = new ArrayList<>();
	}
	public void add(String key, String content) {
		config.add(new Node(key, content));
	}
}

class HConfig {
	HConfig() {
		this.missurl = new ArrayList<>();
		this.regex = new ArrayList<>();
	}

	public void setOs(String os) {
		this.os = os;
	}

	public void setRegex(String regex) {
		Scanner scanner = new Scanner(regex);
		while(scanner.hasNext())
			this.regex.add(scanner.nextLine());
	}

	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}

	public void setMissurl(String missurl) {
		Scanner scanner = new Scanner(missurl);
		while(scanner.hasNext())
			this.missurl.add(scanner.nextLine());
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUA(String UA) {
		this.UA = UA;
	}

	public void setThreadnum(int threadnum) {
		this.threadnum = threadnum;
	}

	String savepath;
	String os;
	String UA;
	String type;
	ArrayList<String> missurl;
	ArrayList<String> regex;
	int threadnum;
}

class DLConfig {
	public DLConfig() {}

	public void setSize(int w, int h, int size) {
		this.w = w;
		this.h = h;
		this.size = size;
	}

	public void setBuffersize(int buffersize) {
		this.buffersize = buffersize;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	int w;
	int h;
	int size;
	int buffersize;
	int speed;
}
