package com.test.utility.shell;

import java.io.InputStream;

import javax.swing.JOptionPane;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class Exec {
	public static void main(String[] arg) {
		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession("msdp", "10.169.99.48", 22);

			session.setPassword("msdp");
			session.setConfig("StrictHostKeyChecking", "no");

			session.connect();

			String command = JOptionPane.showInputDialog("Enter command", "set|grep SSH");

			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);

			((ChannelExec) channel).setErrStream(System.err);

			InputStream in = channel.getInputStream();

			channel.connect();

			byte[] tmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					System.out.print(new String(tmp, 0, i));
				}
				if (channel.isClosed()) {
					if (in.available() > 0)
						continue;
					System.out.println("exit-status: " + channel.getExitStatus());
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
				}
			}
			channel.disconnect();
			session.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

}