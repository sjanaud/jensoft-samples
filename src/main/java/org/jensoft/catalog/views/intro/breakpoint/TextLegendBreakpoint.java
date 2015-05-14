/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.intro.breakpoint;

import java.awt.Color;
import java.awt.Font;

import org.jensoft.catalog.views.intro.DemoBreakPoint;
import org.jensoft.catalog.views.intro.JenSoftBreackpointExecutor;
import org.jensoft.core.palette.color.Alpha;
import org.jensoft.core.palette.color.Spectral;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.WidgetPlugin.PushingBehavior;

public class TextLegendBreakpoint extends DemoBreakPoint {

	public TextLegendBreakpoint(JenSoftBreackpointExecutor jenSoftDemoExecutor, View view) {
		super(jenSoftDemoExecutor, view);
	}

	@Override
	protected DemoMessage getMessage() {
		DemoMessage msg = new DemoMessage("Continue and install text legend.");
		msg.setSize(320, 110);
		msg.setTitle("JenSoft API - Text Legend");
		msg.setMessageTitleColor(Color.WHITE);
		msg.setMessageForeground(new Alpha(Color.WHITE, 250));
		return msg;
	}

	@Override
	public void run() {
		super.run();
		try {
			synchronized (this) {
				Font f =  new Font("Dialog", Font.PLAIN, 12);
				view.getWidgetPlugin().pushMessage("Install Text Legend", 500, null, PushingBehavior.Fast,f, Color.BLACK);

				Projection proj = jenSoftDemoExecutor.getDefaultProjection();
				view.repaint();
				TitleLegendPlugin legendPlg = new TitleLegendPlugin();
				proj.registerPlugin(legendPlg);

				Thread.sleep(1000);

				Font f12 =  new Font("Dialog", Font.PLAIN, 12);
				TitleLegend legend = new TitleLegend("JenSoft API V1.0");
				legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, Spectral.SPECTRAL_RED.brighter()));
				legend.setFont(f12);
				legend.setConstraints(new TitleLegendConstraints(LegendPosition.East, 0.8f, LegendAlignment.Left));
				legendPlg.addLegend(legend);
				view.repaint();
				wait(200);

				legend.setConstraints(new TitleLegendConstraints(LegendPosition.East, 0.2f, LegendAlignment.Middle));
				legend.setLegendFill(new TitleLegendGradientFill(Spectral.SPECTRAL_BLUE1, Spectral.SPECTRAL_YELLOW));
				view.repaint();
				wait(200);

				legend.setConstraints(new TitleLegendConstraints(LegendPosition.East, 0.2f, LegendAlignment.Rigth));
				legend.setLegendFill(new TitleLegendGradientFill(Spectral.SPECTRAL_BLUE1, Spectral.SPECTRAL_YELLOW));
				view.repaint();
				wait(200);

				legend.setConstraints(new TitleLegendConstraints(LegendPosition.North, 0.2f, LegendAlignment.Rigth));
				legend.setLegendFill(new TitleLegendGradientFill(Spectral.SPECTRAL_BLUE1, Spectral.SPECTRAL_RED));
				view.repaint();
				wait(200);

				legend.setConstraints(new TitleLegendConstraints(LegendPosition.North, 0.2f, LegendAlignment.Middle));
				legend.setLegendFill(new TitleLegendGradientFill(Spectral.SPECTRAL_BLUE1, Spectral.SPECTRAL_GREEN));
				view.repaint();
				wait(200);

				legend.setConstraints(new TitleLegendConstraints(LegendPosition.North, 0.2f, LegendAlignment.Left));
				legend.setLegendFill(new TitleLegendGradientFill(Spectral.SPECTRAL_BLUE1, Spectral.SPECTRAL_GREEN));
				view.repaint();
				wait(200);

				legend.setConstraints(new TitleLegendConstraints(LegendPosition.West, 0.2f, LegendAlignment.Rigth));
				legend.setLegendFill(new TitleLegendGradientFill(Color.yellow, Color.orange.darker()));
				view.repaint();
				wait(200);

				legend.setConstraints(new TitleLegendConstraints(LegendPosition.West, 0.2f, LegendAlignment.Middle));
				legend.setLegendFill(new TitleLegendGradientFill(Color.yellow, Color.orange.darker()));
				view.repaint();
				wait(200);

				legend.setConstraints(new TitleLegendConstraints(LegendPosition.South, 0.5f, LegendAlignment.Left));
				legend.setLegendFill(new TitleLegendGradientFill(Spectral.SPECTRAL_YELLOW.brighter(), Spectral.SPECTRAL_RED.brighter()));
				view.repaint();
				wait(200);

				legend.setConstraints(new TitleLegendConstraints(LegendPosition.South, 0.5f, LegendAlignment.Middle));
				legend.setLegendFill(new TitleLegendGradientFill(Spectral.SPECTRAL_YELLOW.brighter(), Spectral.SPECTRAL_RED.brighter()));
				view.repaint();
				wait(200);

				legend.setConstraints(new TitleLegendConstraints(LegendPosition.South, 0.7f, LegendAlignment.Rigth));
				legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, Spectral.SPECTRAL_BLUE2));
				view.repaint();
			}
		} catch (InterruptedException e) {
		}
	}

}
