package cn.hsnss.acfun;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * @author Hyia
 */
public class Layout  {
	private TaskMain taskMain;
	private HConfig hConfig = new HConfig();
	private DLConfig dlConfig = new DLConfig();
	private int threadnum;


	private void htmlurl_textareaCaretUpdate() {
		int n = htmlurl_textarea.getLineCount();
		htmlnum_value_label.setText(String.valueOf(n));
	}

	private void selectpath_buttonActionPerformed() {
		// TODO add your code here
		chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		if(chooser.showOpenDialog(main_jframe) == JFileChooser.APPROVE_OPTION)
			savepath_textfield.setText(chooser.getCurrentDirectory().toString());
	}

	private void openfilebuttonActionPerformed() {
		chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileFilter(new FileNameExtensionFilter("Txt file", "txt"));
		if(chooser.showOpenDialog(main_jframe) == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			try {
				InputStreamReader instream = new InputStreamReader(new FileInputStream(file), "UTF-8");
				BufferedReader buffer = new BufferedReader(instream);
				htmlurl_textarea.setText("");
				String line;
				while((line = buffer.readLine()) != null)
					htmlurl_textarea.append(line + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void save_buttonActionPerformed() {
		chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileFilter(new FileNameExtensionFilter("Txt file", "txt"));
		if(chooser.showSaveDialog(main_jframe) == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			try {
				FileOutputStream outstream = new FileOutputStream(file);
				Scanner scanner = new Scanner(htmlurl_textarea.getText());
				while(scanner.hasNext())
					outstream.write((scanner.nextLine() + "\n").getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void apply_main_buttonActionPerformed() {
		threadnum = Integer.valueOf(threadnum_spinner.getValue().toString());
		hConfig.setSavepath(savepath_textfield.getText());
		if(System.getProperties().toString().contains("Windows"))
			hConfig.setOs("Windows");
		else
			hConfig.setOs("Linux");
	}

	private void start_buttonActionPerformed() {
		// TODO add your code here
		apply_main_buttonActionPerformed();
		apply_config_buttonActionPerformed();
		apply_regex_buttonActionPerformed();
		taskMain = new TaskMain(hConfig, dlConfig);
		taskMain.setHtmlurl(htmlurl_textarea.getText());
		taskMain.setHtmlurl(htmlurl_textarea.getText());
		int n = Integer.valueOf(threadnum_spinner.getValue().toString());
		int i = 0;
		Thread[] t = new Thread[n];
		while(i < n)
			t[i++] = new Thread(taskMain);
		try {
			i = 0;
			while(i < n)
				t[i++].start();
			i = 0;
			while(i < n)
				t[i++].join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void apply_config_buttonActionPerformed() {
		hConfig.setUA(ua_textfield.getText());
		dlConfig.setSize(Integer.valueOf(w_textfield.getText()), Integer.valueOf(h_textfield.getText()), Integer.valueOf(size_textfield.getText()));
		dlConfig.setBuffersize(Integer.valueOf(buffersize_textfield.getText()));
		dlConfig.setSpeed(Integer.valueOf(dl_spinner.getValue().toString()));
		hConfig.setType(type_textfield.getText());
		hConfig.setThreadnum(Integer.valueOf(picthreadnum_spinner.getValue().toString()));
	}

	private void reset_config_buttonActionPerformed() {
		// TODO add your code here
		ua_textfield.setText("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
		w_textfield.setText("100");
		h_textfield.setText("100");
		size_textfield.setText("0");
		buffersize_textfield.setText("1024");
		picthreadnum_spinner.setValue(1);
		type_textfield.setText("jpg;gif;png");
		dl_spinner.setValue(0);

		apply_config_buttonActionPerformed();
	}

	private void apply_regex_buttonActionPerformed() {
		hConfig.setMissurl(miss_url_textarea.getText());
		hConfig.setRegex(regex_textarea.getText());
	}

	private void reset_regex_buttonActionPerformed() {
		// TODO add your code here
		miss_url_textarea.setText("bdstatic\nportrait\nitem");
		regex_textarea.setText("http://[^\\s]*.jpg");
		apply_regex_buttonActionPerformed();
	}

	void initComponents() {
		// Default the UI style
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
			e.printStackTrace();
		}
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		main_jframe = new JFrame();
		JTabbedPane tabbedPane = new JTabbedPane();
		url_tab = new JPanel();
		scrollPane2 = new JScrollPane();
		htmlurl_textarea = new JTextArea();
		openfilebutton = new JButton();
		save_button = new JButton();
		apply_main_button = new JButton();
		threadnum_label = new JLabel();
		threadnum_spinner = new JSpinner();
		savepath_label = new JLabel();
		savepath_textfield = new JTextField();
		selectpath_button = new JButton();
		start_button = new JButton();
		htmlnum_label = new JLabel();
		htmlnum_value_label = new JLabel();
		config_tab = new JPanel();
		ua_label = new JLabel();
		ua_textfield = new JTextField();
		JLabel sep1_label = new JLabel();
		w_label = new JLabel();
		w_textfield = new JTextField();
		size_label = new JLabel();
		size_textfield = new JTextField();
		size_kb_label = new JLabel();
		h_label = new JLabel();
		h_textfield = new JTextField();
		buffersize_label = new JLabel();
		buffersize_textfield = new JTextField();
		buffersize_kb_label = new JLabel();
		type_label = new JLabel();
		type_textfield = new JTextField();
		sep2_label = new JLabel();
		picthreadnum_label = new JLabel();
		picthreadnum_spinner = new JSpinner();
		dl_label = new JLabel();
		dl_spinner = new JSpinner();
		dl_dw_label = new JLabel();
		dl_info_label = new JLabel();
		JButton apply_config_button = new JButton();
		reset_config_button = new JButton();
		url_regex_tab = new JPanel();
		regex1_label = new JLabel();
		scrollPane3 = new JScrollPane();
		miss_url_textarea = new JTextArea();
		regex3_label = new JLabel();
		scrollPane4 = new JScrollPane();
		regex_textarea = new JTextArea();
		apply_regex_button = new JButton();
		reset_regex_button = new JButton();
		help_tab = new JPanel();
		help_label_title = new JLabel();
		help_label_content = new JLabel();
		JLabel version = new JLabel();

		//======== main_jframe ========
		{
			main_jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			main_jframe.setTitle("PicDownloader");
			main_jframe.setVisible(true);
			Container main_jframeContentPane = main_jframe.getContentPane();
			main_jframeContentPane.setLayout(new GridBagLayout());
			((GridBagLayout)main_jframeContentPane.getLayout()).columnWidths = new int[] {24, 944, 0, 0};
			((GridBagLayout)main_jframeContentPane.getLayout()).rowHeights = new int[] {16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			((GridBagLayout)main_jframeContentPane.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};
			((GridBagLayout)main_jframeContentPane.getLayout()).rowWeights = new double[] {0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0E-4};

			//======== tabbedPane ========
			{
				tabbedPane.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 20));
				tabbedPane.setBorder(null);

				//======== url_tab ========
				{
					url_tab.setLayout(new GridBagLayout());
					((GridBagLayout)url_tab.getLayout()).columnWidths = new int[] {38, 133, 142, 157, 89, 121, 0, 0, 0, 0};
					((GridBagLayout)url_tab.getLayout()).rowHeights = new int[] {41, 0, 51, 0, 178, 38, 29, 0, 0, 0, 0};
					((GridBagLayout)url_tab.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0E-4};
					((GridBagLayout)url_tab.getLayout()).rowWeights = new double[] {0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0E-4};

					//======== scrollPane2 ========
					{

						//---- htmlurl_textarea ----
						htmlurl_textarea.setLineWrap(true);
						htmlurl_textarea.addCaretListener(e -> htmlurl_textareaCaretUpdate());
						scrollPane2.setViewportView(htmlurl_textarea);
					}
					url_tab.add(scrollPane2, new GridBagConstraints(1, 1, 5, 5, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- openfilebutton ----
					openfilebutton.setText("\u6253\u5f00\u6587\u4ef6");
					openfilebutton.addActionListener(e -> openfilebuttonActionPerformed());
					url_tab.add(openfilebutton, new GridBagConstraints(7, 1, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- save_button ----
					save_button.setText("\u4fdd\u5b58\u4e3a\u6587\u4ef6");
					save_button.addActionListener(e -> save_buttonActionPerformed());
					url_tab.add(save_button, new GridBagConstraints(7, 3, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- apply_main_button ----
					apply_main_button.setText("\u5e94\u7528");
					apply_main_button.addActionListener(e -> apply_main_buttonActionPerformed());
					url_tab.add(apply_main_button, new GridBagConstraints(7, 5, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- threadnum_label ----
					threadnum_label.setText("\u540c\u65f6\u4efb\u52a1\u6570 : (\u6d4b\u8bd5\u4e2d,\u614e\u7528)");
					threadnum_label.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
					url_tab.add(threadnum_label, new GridBagConstraints(1, 7, 2, 1, 0.0, 0.0,
							GridBagConstraints.WEST, GridBagConstraints.NONE,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- threadnum_spinner ----
					threadnum_spinner.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
					threadnum_spinner.setModel(new SpinnerNumberModel(1, 1, 1/* 8 */, 1));
					url_tab.add(threadnum_spinner, new GridBagConstraints(3, 7, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- savepath_label ----
					savepath_label.setText("\u4fdd\u5b58\u76ee\u5f55 : ");
					savepath_label.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
					url_tab.add(savepath_label, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0,
							GridBagConstraints.WEST, GridBagConstraints.NONE,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- savepath_textfield ----
					savepath_textfield.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 15));
					url_tab.add(savepath_textfield, new GridBagConstraints(2, 8, 3, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- selectpath_button ----
					selectpath_button.setText("\u6d4f\u89c8");
					selectpath_button.addActionListener(e -> selectpath_buttonActionPerformed());
					url_tab.add(selectpath_button, new GridBagConstraints(5, 8, 2, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.NONE,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- start_button ----
					start_button.setText("\u5f00\u59cb\u4efb\u52a1");
					start_button.addActionListener(e -> start_buttonActionPerformed());
					url_tab.add(start_button, new GridBagConstraints(7, 8, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- htmlnum_label ----
					htmlnum_label.setText("\u5f53\u524d\u7f51\u9875\u603b\u6570 : ");
					htmlnum_label.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
					url_tab.add(htmlnum_label, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0,
							GridBagConstraints.WEST, GridBagConstraints.NONE,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- htmlnum_value_label ----
					htmlnum_value_label.setText("0");
					htmlnum_value_label.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
					url_tab.add(htmlnum_value_label, new GridBagConstraints(2, 9, 2, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.NONE,
							new Insets(0, 0, 0, 0), 0, 0));
				}
				tabbedPane.addTab("Html url", url_tab);

				//======== config_tab ========
				{
					config_tab.setLayout(new GridBagLayout());
					((GridBagLayout)config_tab.getLayout()).columnWidths = new int[] {0, 0, 169, 0, 0, 138, 0, 0};
					((GridBagLayout)config_tab.getLayout()).rowHeights = new int[] {25, 0, 54, 0, 0, 0, 61, 0, 13, 0, 0, 0};
					((GridBagLayout)config_tab.getLayout()).columnWeights = new double[] {0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0E-4};
					((GridBagLayout)config_tab.getLayout()).rowWeights = new double[] {0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0E-4};

					//---- ua_label ----
					ua_label.setText("User-Agent : ");
					ua_label.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
					config_tab.add(ua_label, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.NONE,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- ua_textfield ----
					ua_textfield.setText("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
					config_tab.add(ua_textfield, new GridBagConstraints(2, 1, 4, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- sep1_label ----
					sep1_label.setText("\u6587\u4ef6\u9650\u5236");
					sep1_label.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
					config_tab.add(sep1_label, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- w_label ----
					w_label.setText("\u6700\u5c0f\u5bbd\u5ea6");
					w_label.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
					config_tab.add(w_label, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.NONE,
							new Insets(0, 0, 0, 0), 0, 0));
					w_textfield.setText("100");
					w_textfield.setHorizontalAlignment(SwingConstants.CENTER);
					config_tab.add(w_textfield, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- size_label ----
					size_label.setText("\u6700\u5c0f\u5927\u5c0f");
					size_label.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
					config_tab.add(size_label, new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.NONE,
							new Insets(0, 0, 0, 0), 0, 0));
					size_textfield.setText("0");
					size_textfield.setHorizontalAlignment(SwingConstants.CENTER);
					config_tab.add(size_textfield, new GridBagConstraints(5, 3, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- size_kb_label ----
					size_kb_label.setText(" KB");
					size_kb_label.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
					config_tab.add(size_kb_label, new GridBagConstraints(6, 3, 1, 1, 0.0, 0.0,
							GridBagConstraints.WEST, GridBagConstraints.NONE,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- h_label ----
					h_label.setText("\u6700\u5c0f\u9ad8\u5ea6");
					h_label.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
					config_tab.add(h_label, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.NONE,
							new Insets(0, 0, 0, 0), 0, 0));
					h_textfield.setText("100");
					h_textfield.setHorizontalAlignment(SwingConstants.CENTER);
					config_tab.add(h_textfield, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- buffersize_label ----
					buffersize_label.setText("\u7f13\u51b2\u533a\u5927\u5c0f");
					buffersize_label.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
					config_tab.add(buffersize_label, new GridBagConstraints(4, 4, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.NONE,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- buffersize_textfield ----
					buffersize_textfield.setText("1024");
					buffersize_textfield.setHorizontalAlignment(SwingConstants.CENTER);
					config_tab.add(buffersize_textfield, new GridBagConstraints(5, 4, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- buffersize_kb_label ----
					buffersize_kb_label.setText(" B");
					buffersize_kb_label.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
					config_tab.add(buffersize_kb_label, new GridBagConstraints(6, 4, 1, 1, 0.0, 0.0,
							GridBagConstraints.WEST, GridBagConstraints.NONE,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- type_label ----
					type_label.setText("\u56fe\u7247\u683c\u5f0f");
					type_label.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
					config_tab.add(type_label, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.NONE,
							new Insets(0, 0, 0, 0), 0, 0));
					type_textfield.setText("jpg;gif;png");
					config_tab.add(type_textfield, new GridBagConstraints(2, 5, 4, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- sep2_label ----
					sep2_label.setText("\u4e0b\u8f7d\u8bbe\u7f6e");
					sep2_label.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
					config_tab.add(sep2_label, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- picthreadnum_label ----
					picthreadnum_label.setText("\u5355\u7f51\u5740\u4e0b\u8f7d\u8fde\u63a5\u6570");
					picthreadnum_label.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
					config_tab.add(picthreadnum_label, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.NONE,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- picthreadnum_spinner ----
					picthreadnum_spinner.setModel(new SpinnerNumberModel(1, 1, 16, 1));
					config_tab.add(picthreadnum_spinner, new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- dl_label ----
					dl_label.setText("\u4e0b\u8f7d\u901f\u5ea6");
					dl_label.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
					config_tab.add(dl_label, new GridBagConstraints(4, 7, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.NONE,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- dl_spinner ----
					dl_spinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
					config_tab.add(dl_spinner, new GridBagConstraints(5, 7, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- dl_dw_label ----
					dl_dw_label.setText(" KB/s");
					dl_dw_label.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
					config_tab.add(dl_dw_label, new GridBagConstraints(6, 7, 1, 1, 0.0, 0.0,
							GridBagConstraints.WEST, GridBagConstraints.NONE,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- dl_info_label ----
					dl_info_label.setText("(\u9ed8\u8ba40, \u4ee3\u8868\u4e0d\u9650\u5236)");
					dl_info_label.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
					config_tab.add(dl_info_label, new GridBagConstraints(4, 8, 1, 1, 0.0, 0.0,
							GridBagConstraints.NORTH, GridBagConstraints.NONE,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- apply_config_button ----
					apply_config_button.setText("\u5e94\u7528");
					apply_config_button.addActionListener(e -> apply_config_buttonActionPerformed());
					config_tab.add(apply_config_button, new GridBagConstraints(2, 9, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- reset_config_button ----
					reset_config_button.setText("\u91cd\u7f6e");
					reset_config_button.addActionListener(e -> reset_config_buttonActionPerformed());
					config_tab.add(reset_config_button, new GridBagConstraints(5, 9, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 0, 0), 0, 0));
				}
				tabbedPane.addTab("Config", config_tab);

				//======== url_regex_tab ========
				{
					url_regex_tab.setLayout(new GridBagLayout());
					((GridBagLayout)url_regex_tab.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
					((GridBagLayout)url_regex_tab.getLayout()).rowHeights = new int[] {67, 153, 69, 157, 19, 57, 0, 0};
					((GridBagLayout)url_regex_tab.getLayout()).columnWeights = new double[] {0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0E-4};
					((GridBagLayout)url_regex_tab.getLayout()).rowWeights = new double[] {0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0E-4};

					//---- regex1_label ----
					regex1_label.setText("\u5ffd\u7565\u5305\u542b\u4ee5\u4e0b\u5173\u952e\u5b57\u7684URL");
					regex1_label.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
					url_regex_tab.add(regex1_label, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
							new Insets(0, 0, 0, 0), 0, 0));

					//======== scrollPane3 ========
					{
						miss_url_textarea.setText("bdstatic\nportrait\nitem");
						scrollPane3.setViewportView(miss_url_textarea);
					}
					url_regex_tab.add(scrollPane3, new GridBagConstraints(1, 1, 5, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- regex3_label ----
					regex3_label.setText("\u81ea\u5b9a\u4e49\u89e3\u6790\u5668(\u6b63\u5219\u5339\u914d)");
					regex3_label.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
					url_regex_tab.add(regex3_label, new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
							new Insets(0, 0, 0, 0), 0, 0));

					//======== scrollPane4 ========
					{
						regex_textarea.setText("http://[^\\s]*.jpg");
						scrollPane4.setViewportView(regex_textarea);
					}
					url_regex_tab.add(scrollPane4, new GridBagConstraints(1, 3, 5, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- apply_regex_button ----
					apply_regex_button.setText("\u5e94\u7528");
					apply_regex_button.addActionListener(e -> apply_regex_buttonActionPerformed());
					url_regex_tab.add(apply_regex_button, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- reset_regex_button ----
					reset_regex_button.setText("\u91cd\u7f6e");
					reset_regex_button.addActionListener(e -> reset_regex_buttonActionPerformed());
					url_regex_tab.add(reset_regex_button, new GridBagConstraints(4, 5, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 0, 0), 0, 0));
				}
				tabbedPane.addTab("Url regex", url_regex_tab);

				//======== help_tab ========
				{
					help_tab.setLayout(new GridBagLayout());
					((GridBagLayout)help_tab.getLayout()).columnWidths = new int[] {0, 0, 0};
					((GridBagLayout)help_tab.getLayout()).rowHeights = new int[] {52, 0, 0, 0};
					((GridBagLayout)help_tab.getLayout()).columnWeights = new double[] {1.0, 0.0, 1.0E-4};
					((GridBagLayout)help_tab.getLayout()).rowWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};

					//---- help_label_title ----
					help_label_title.setText("Help");
					help_label_title.setFont(new Font("\u5b8b\u4f53", Font.BOLD, 30));
					help_label_title.setHorizontalAlignment(SwingConstants.CENTER);
					help_tab.add(help_label_title, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 0, 0), 0, 0));

					//---- help_label_content ----
					help_label_content.setVerticalAlignment(SwingConstants.TOP);
					help_label_content.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
					help_label_content.setText("    Welcome to use my program.    The program is to download the picture");
					help_label_content.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
					help_tab.add(help_label_content, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 0, 0), 0, 0));
				}
				tabbedPane.addTab("Help", help_tab);
			}
			main_jframeContentPane.add(tabbedPane, new GridBagConstraints(1, 1, 1, 9, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));

			//---- version ----
			version.setText("Version : 0.0.9");
			version.setHorizontalAlignment(SwingConstants.RIGHT);
			version.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
			main_jframeContentPane.add(version, new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
					new Insets(0, 0, 0, 0), 0, 0));
			main_jframe.setSize(995, 655);
			main_jframe.setLocationRelativeTo(main_jframe.getOwner());
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JFrame main_jframe;
	private JFileChooser chooser;
	private JPanel url_tab;
	private JScrollPane scrollPane2;
	private JTextArea htmlurl_textarea;
	private JButton openfilebutton;
	private JButton save_button;
	private JButton apply_main_button;
	private JLabel threadnum_label;
	private JSpinner threadnum_spinner;
	private JLabel savepath_label;
	private JTextField savepath_textfield;
	private JButton selectpath_button;
	private JButton start_button;
	private JLabel htmlnum_label;
	private JLabel htmlnum_value_label;
	private JPanel config_tab;
	private JLabel ua_label;
	private JTextField ua_textfield;
	private JLabel w_label;
	private JTextField w_textfield;
	private JLabel size_label;
	private JTextField size_textfield;
	private JLabel size_kb_label;
	private JLabel h_label;
	private JTextField h_textfield;
	private JLabel buffersize_label;
	private JTextField buffersize_textfield;
	private JLabel buffersize_kb_label;
	private JLabel type_label;
	private JTextField type_textfield;
	private JLabel sep2_label;
	private JLabel picthreadnum_label;
	private JSpinner picthreadnum_spinner;
	private JLabel dl_label;
	private JSpinner dl_spinner;
	private JLabel dl_dw_label;
	private JLabel dl_info_label;
	private JButton reset_config_button;
	private JPanel url_regex_tab;
	private JLabel regex1_label;
	private JScrollPane scrollPane3;
	private JTextArea miss_url_textarea;
	private JLabel regex3_label;
	private JScrollPane scrollPane4;
	private JTextArea regex_textarea;
	private JButton apply_regex_button;
	private JButton reset_regex_button;
	private JPanel help_tab;
	private JLabel help_label_title;
	private JLabel help_label_content;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
