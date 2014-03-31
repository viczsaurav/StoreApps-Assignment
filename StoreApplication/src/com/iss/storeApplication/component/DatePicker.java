package com.iss.storeApplication.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iss.storeApplication.common.StringUtility;

public class DatePicker extends JPanel {
	int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
	int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);;
	JLabel l = new JLabel("", JLabel.CENTER);
	String day = "";
	JDialog d;
	JButton[] button = new JButton[49];

	DatePicker datePicker;
	JLabel label = new JLabel("Selected Date: ");
	final JTextField text = new JTextField(10);
	JButton calenderPopbtn = new JButton("Calender");
	
	public boolean isPastEnabled() {
		return pastEnabled;
	}

	public void setPastEnabled(boolean pastEnabled) {
		this.pastEnabled = pastEnabled;
	}

	public boolean isFutureEnabled() {
		return futureEnabled;
	}

	public void setFutureEnabled(boolean futureEnabled) {
		this.futureEnabled = futureEnabled;
	}

	boolean pastEnabled = false;
	boolean futureEnabled = true;
	
	
	public void setLabel(String label) {
		this.label.setText(label);
	}

	public DatePicker() {
		this.datePicker = this;

		text.setEditable(false);
		
		calenderPopbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				show();
			}
		});
		JPanel p = new JPanel(new GridLayout(1,2));
		p.add(label);
		p.add(text,BorderLayout.CENTER);
		p.add(calenderPopbtn,BorderLayout.WEST);
		add(p);

	}
	
	public DatePicker(String label) {
		this();
		this.setLabel(label);
		
	}

	public DatePicker(String label,boolean pastEnabled,boolean futureEnabled) {
		this();
		this.setLabel(label);
		this.setPastEnabled(pastEnabled);
		this.setFutureEnabled(futureEnabled);
		
	}

	public void hide() {
		if (d != null)
			d.dispose();
	}

	public void show() {
		d = new JDialog();
		d.setModal(true);
		String[] header = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };
		JPanel p1 = new JPanel(new GridLayout(7, 7));
		p1.setPreferredSize(new Dimension(430, 120));

		for (int x = 0; x < button.length; x++) {
			final int selection = x;
			button[x] = new JButton();
			button[x].setFocusPainted(false);
			button[x].setBackground(Color.white);
			if (x > 6)
				button[x].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						day = button[selection].getActionCommand();
						text.setText(StringUtility.getStringFromDate(datePicker.getPickedDate()));
						d.dispose();
					}
				});
			if (x < 7) {
				button[x].setText(header[x]);
				button[x].setForeground(Color.red);
			}
			p1.add(button[x]);
		}
		JPanel p2 = new JPanel(new GridLayout(1, 3));
		JButton previous = new JButton("<< Previous");
		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				month--;
				displayDate();
			}
		});
		p2.add(previous);
		p2.add(l);
		JButton next = new JButton("Next >>");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				month++;
				displayDate();
			}
		});
		p2.add(next);
		d.add(p1, BorderLayout.CENTER);
		d.add(p2, BorderLayout.SOUTH);
		d.pack();
		d.setLocationRelativeTo(calenderPopbtn);
		displayDate();
		d.setVisible(true);
	}

	public void displayDate() {
		for (int x = 7; x < button.length; x++)
			button[x].setText("");
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"MMMM yyyy");
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(year, month, 1);
		int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);

		Calendar now = Calendar.getInstance();
		int currentyear = now.get(Calendar.YEAR);
		int currentmonth = now.get(Calendar.MONTH); // Note: zero based!
		int currentday = now.get(Calendar.DAY_OF_MONTH);

		int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
		if(pastEnabled)
		for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++) {
			button[x].setText("" + day);
			if (year <= currentyear && month == currentmonth) {
				if (day < currentday) {
					button[x].setEnabled(false);

				}
			} else if (year <= currentyear && month < currentmonth) {
				button[x].setEnabled(false);
			} else
				button[x].setEnabled(true);
		}
		if(futureEnabled)
		{
		for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++) {
			button[x].setText("" + day);
			if (year >= currentyear && month == currentmonth) {
				if (day > currentday) {
					button[x].setEnabled(false);

				}
				else
				{
					button[x].setEnabled(true);
				}
			} else if (year >= currentyear && month > currentmonth) {
				button[x].setEnabled(false);
			} else
				button[x].setEnabled(true);
		}
		}
		l.setText(sdf.format(cal.getTime()));
		d.setTitle("Date Picker");

	}

	public Date getPickedDate() {
		if (day.equals(""))
			return new Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(year, month, Integer.parseInt(day));
		return cal.getTime();
	}

	public void setDate(Date startDate) {
		this.text.setText(StringUtility.getStringFromDate(startDate));
		
	}
	

}
