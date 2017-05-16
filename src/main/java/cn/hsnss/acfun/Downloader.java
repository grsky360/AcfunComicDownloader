package cn.hsnss.acfun;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Downloader {
	public Downloader(DLConfig dConfig) {
		this.dConfig = dConfig;
	}
	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}
	public boolean download(String srcurl, String filename) {
		if(new File(savepath + filename).exists()) {
			return true;
		}
		int byteread;
		URL url;
		try {
			url = new URL(srcurl);
			URLConnection con = url.openConnection();
			{
				BufferedImage imgsize = ImageIO.read(url.openConnection().getInputStream());
				if(imgsize.getWidth() < dConfig.w || imgsize.getHeight() < dConfig.h)
					return true;
				if(dConfig.size != 0 && con.getContentLength() < 1024 * dConfig.size)
					return true;
			}
			InputStream instream = con.getInputStream();


			FileOutputStream fileOutputStream = new FileOutputStream(savepath + filename);
			byte[] buffer = new byte[dConfig.buffersize];
			while((byteread = instream.read(buffer)) != -1)
				fileOutputStream.write(buffer, 0, byteread);
			fileOutputStream.close();
			fileOutputStream.flush();
		} catch (MalformedURLException e) {
			System.out.println("Fail to create a url from the pic.");
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Fail to find the file/dir.");
			return false;
		} catch (IOException e) {
			System.out.println("Fail to connect to the url.");
			return false;
		}
		return true;
	}

	DLConfig dConfig;
	private String savepath;
}
