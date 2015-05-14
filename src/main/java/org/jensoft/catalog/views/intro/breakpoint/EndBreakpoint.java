/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.intro.breakpoint;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.jensoft.catalog.views.intro.DemoBreakPoint;
import org.jensoft.catalog.views.intro.JenSoftBreackpointExecutor;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.AbstractPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.view.View;
import org.jensoft.core.view.WidgetPlugin.PushingBehavior;

/**
 * <code>EndExecutor</code>
 * 
 * @author Sébastien Janaud
 */
public class EndBreakpoint extends DemoBreakPoint {

	public EndBreakpoint(JenSoftBreackpointExecutor jenSoftDemoExecutor, View view) {
		super(jenSoftDemoExecutor, view);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jensoft.demo.intro.DemoBreackPoint#getMessage()
	 */
	@Override
	protected DemoMessage getMessage() {
		DemoMessage msg = new DemoMessage("Please Donate...");
		msg.setSize(280, 100);
		msg.setTitle("JenSoft API 1.0 - The End");
		msg.setMessageTitleColor(RosePalette.MELON);
		msg.setMessageForeground(Color.WHITE);
		return msg;
	}

	/**
	 * Un install all plug ins which has been registered in this demo
	 * 
	 * @author Sébastien Janaud
	 */
	class Desinstall extends Thread {

		@Override
		public void run() {
			try {
				synchronized (this) {
					wait(200);

					jenSoftDemoExecutor.getDefaultProjection().setVisible(true);
					view.repaint();
					wait(500);
					OutlinePlugin o = new OutlinePlugin(Color.BLACK);
					getJenSoftDemoExecutor().getDefaultProjection().registerPlugin(o);
					view.repaint();

					List<AbstractPlugin> plugins = getJenSoftDemoExecutor().getDefaultProjection().getPluginRegistry();
					//System.out.println("plugins size " + plugins.size());
					int size = plugins.size();
					List<AbstractPlugin> dplugins = new ArrayList<AbstractPlugin>();
					for (int i = size - 1; i >= 0; i--) {
						if (plugins.get(i) != jenSoftDemoExecutor.getMessagePlugin() && plugins.get(i) != o) {
							dplugins.add(plugins.get(i));
						}
					}

					int rsize = dplugins.size();
					for (int j = rsize - 1; j >= 0; j--) {
						//System.out.println("unregister : " + dplugins.get(j).getClass() + "@" + dplugins.get(j).getName());
						getJenSoftDemoExecutor().getDefaultProjection().unregisterPlugin(dplugins.get(j));
						view.repaintView();
						wait(200);
					}
					o.deflateOutline(0).join();
					getJenSoftDemoExecutor().getDefaultProjection().unregisterPlugin(o);
					view.repaintView();

				}
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void run() {
		try {
			synchronized (this) {

				super.run();
				Desinstall desinstall = new Desinstall();
				desinstall.start();
				Font f =  new Font("Dialog", Font.PLAIN, 14);
				view.getWidgetPlugin().pushMessage("JenSoft API - The End", 500, null, PushingBehavior.Fast, f, RosePalette.getInstance().getPaletteRandomColor().darker(), false);

				Thread.sleep(4000);

			}
		} catch (InterruptedException e) {
		} finally {
			System.out.println("out executor : " + getClass().getSimpleName());
			//System.exit(0);
		}
	}

}
