/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.symbol.vertical;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.TexturePalette;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.FilPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.background.DeviceNightBackground;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.plugin.symbol.BarEvent;
import org.jensoft.core.plugin.symbol.BarListener;
import org.jensoft.core.plugin.symbol.BarSymbol;
import org.jensoft.core.plugin.symbol.BarSymbol.SymbolInflate;
import org.jensoft.core.plugin.symbol.BarSymbolGroup;
import org.jensoft.core.plugin.symbol.BarSymbolLayer;
import org.jensoft.core.plugin.symbol.Stack;
import org.jensoft.core.plugin.symbol.StackedBarSymbol;
import org.jensoft.core.plugin.symbol.SymbolComponent;
import org.jensoft.core.plugin.symbol.SymbolPlugin;
import org.jensoft.core.plugin.symbol.SymbolPlugin.SymbolNature;
import org.jensoft.core.plugin.symbol.SymbolToolkit;
import org.jensoft.core.plugin.symbol.painter.effect.BarEffect1;
import org.jensoft.core.plugin.symbol.painter.fill.BarDefaultFill;
import org.jensoft.core.plugin.symbol.painter.label.BarSymbolRelativeLabel;
import org.jensoft.core.plugin.symbol.painter.label.BarSymbolRelativeLabel.HorizontalAlignment;
import org.jensoft.core.plugin.symbol.painter.label.BarSymbolRelativeLabel.VerticalAlignment;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class SimpleVerticalBarStackedGroupDemo extends View {

	@Portfolio(name = "SimpleVerticalBarStackedGroupDemo", width = 500, height = 250)
	public static View getPortofolio() {
		SimpleVerticalBarStackedGroupDemo demo = new SimpleVerticalBarStackedGroupDemo();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

	public SimpleVerticalBarStackedGroupDemo() {

		Projection proj = new Projection.Linear(0, 0, -30, 120);
		registerProjection(proj);

		SymbolPlugin barPlugin = new SymbolPlugin();
		barPlugin.setNature(SymbolNature.Vertical);
		proj.registerPlugin(barPlugin);

		Stack s1b1g1 = SymbolToolkit.createStack("s1", TangoPalette.CHAMELEON1, 12);
		Stack s2b1g1 = SymbolToolkit.createStack("s2", TangoPalette.CHAMELEON2, 20);
		Stack s3b1g1 = SymbolToolkit.createStack("s3", TangoPalette.CHAMELEON3, 40);
		StackedBarSymbol b1g1 = SymbolToolkit.createStackedBarSymbol("b1g1", SymbolInflate.Ascent, 74, s1b1g1, s2b1g1, s3b1g1);

		Stack s1b2g1 = SymbolToolkit.createStack("s1", TangoPalette.CHAMELEON1, 20);
		Stack s2b2g1 = SymbolToolkit.createStack("s2", TangoPalette.CHAMELEON2, 40);
		Stack s3b2g1 = SymbolToolkit.createStack("s3", TangoPalette.CHAMELEON3, 20);

		StackedBarSymbol b2g1 = SymbolToolkit.createStackedBarSymbol("b1g1", SymbolInflate.Ascent, 47, s1b2g1, s2b2g1, s3b2g1);

		// or use alternative with list
		// List<Stack> stacks = BarFactory.createStacks(new
		// String[]{"s1","s2","s3"}, new
		// Color[]{TangoPalette.CHAMELEON1,TangoPalette.CHAMELEON2,TangoPalette.CHAMELEON3},new
		// double[]{20d,40d,20d});
		// Stack[] sa = stacks.toArray(new Stack[0]);
		// StackedBarSymbol b2g1 = BarFactory.createStackedSmbol("b1g1",
		// SymbolInflate.Ascent,74,sa);

		BarSymbolGroup group1 = SymbolToolkit.createBarGroup("Group 1", 0, 25);
		group1.addSymbol(b1g1);
		group1.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 6));
		group1.addSymbol(b2g1);
		group1.setBarEffect(new BarEffect1());
		group1.setBarFill(new BarDefaultFill());

		// Group2
		StackedBarSymbol b1g2 = new StackedBarSymbol("b1g2", "b1g2");
		b1g2.setThemeColor(new Color(255, 255, 255));
		b1g2.setAscentValue(73);

		b1g2.addStack("s2", TangoPalette.BUTTER1, 12);
		b1g2.addStack("s2", TangoPalette.BUTTER2, 28);
		b1g2.addStack("s3", TangoPalette.BUTTER3, 13);

		StackedBarSymbol b2g2 = new StackedBarSymbol("b2g2", "b2g2");
		b2g2.setThemeColor(new Color(255, 255, 255));
		b2g2.setAscentValue(38);

		b2g2.addStack("s1", TangoPalette.BUTTER1, 23);
		b2g2.addStack("s2", TangoPalette.BUTTER2, 15);
		b2g2.addStack("s3", TangoPalette.BUTTER3, 7);

		BarSymbolGroup group2 = SymbolToolkit.createBarGroup("Group 2", 0, 25);
		group2.setBarEffect(new BarEffect1());
		group2.setBarFill(new BarDefaultFill());
		group2.addSymbol(b1g2);
		group2.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 6));
		group2.addSymbol(b2g2);

		// Group3
		StackedBarSymbol b1g3 = new StackedBarSymbol("b1g3", "b1g3");
		b1g3.setThemeColor(new Color(255, 255, 255));
		b1g3.setAscentValue(63);

		b1g3.addStack("s1", TangoPalette.ORANGE1, 17);
		b1g3.addStack("s2", TangoPalette.ORANGE2, 6);
		b1g3.addStack("s3", TangoPalette.ORANGE3, 39);

		StackedBarSymbol b2g3 = new StackedBarSymbol("b2g3", "b2g3");
		b2g3.setThemeColor(new Color(255, 255, 255));
		b2g3.setAscentValue(46);

		b2g3.addStack("s1", TangoPalette.ORANGE1, 12);
		b2g3.addStack("s2", TangoPalette.ORANGE2, 30);
		b2g3.addStack("s3", TangoPalette.ORANGE3, 18);

		BarSymbolGroup group3 = SymbolToolkit.createBarGroup("Group 3", 0, 25);
		group3.setBarEffect(new BarEffect1());
		group3.setBarFill(new BarDefaultFill());
		group3.addSymbol(b1g3);
		group3.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 6));
		group3.addSymbol(b2g3);

		proj.registerPlugin(new DeviceNightBackground());

		StripePlugin stripePlugin = new StripePlugin.MultiplierStripe.H(0, 30);
		StripePalette bp = new StripePalette();
		float[] fractions = { 0f, 0.5f, 1f };
		Color c = RosePalette.CORALRED;
		Color[] colors = { ColorPalette.alpha(c, 200), ColorPalette.alpha(c, 80), ColorPalette.alpha(c, 200) };
		bp.addPaint(fractions, colors);
		bp.addPaint(TexturePalette.getTriangleCarbonFiber());
		stripePlugin.setStripePalette(bp);
		stripePlugin.setAlpha(0.1f);
		proj.registerPlugin(stripePlugin);

		GridPlugin gridLayout = new GridPlugin.MultiplierGrid(0, 20, GridOrientation.Horizontal);
		gridLayout.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout);

		BarSymbolLayer barLayer = new BarSymbolLayer();
		barPlugin.addLayer(barLayer);

		barLayer.addBarListener(new BarListener() {

			@Override
			public void barSymbolReleased(BarEvent e) {
				System.out.println("bar released : " + e.getBarSymbol().getName());
			}

			@Override
			public void barSymbolPressed(BarEvent e) {
				System.out.println("bar pressed : " + e.getBarSymbol().getName());
			}

			@Override
			public void barSymbolExited(BarEvent e) {
				System.out.println("bar exited : " + e.getBarSymbol().getName());
			}

			@Override
			public void barSymbolEntered(BarEvent e) {
				System.out.println("bar entered : " + e.getBarSymbol().getName());
			}

			@Override
			public void barSymbolClicked(BarEvent e) {

				if (!(e.getBarSymbol() instanceof StackedBarSymbol)) {
					System.out.println("bar clicked : " + e.getBarSymbol().getClass());
					if (e.getBarSymbol().getBarLabel() == null) {
						BarSymbol symbol = e.getBarSymbol();
						BarSymbolRelativeLabel rl = new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.Middle, Color.WHITE, symbol.getThemeColor(), Color.BLACK);
						symbol.setBarLabel(rl);
						symbol.getHost().getProjection().getView().repaintDevice();
					} else {
						BarSymbol symbol = e.getBarSymbol();
						symbol.setBarLabel(null);
						symbol.getHost().getProjection().getView().repaintDevice();
					}
				}
			}

		});

		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		barLayer.addSymbol(group1);
		barLayer.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 100));
		barLayer.addSymbol(group2);
		barLayer.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 100));
		barLayer.addSymbol(group3);
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));

		Font f = new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Stacked Bar Group");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, FilPalette.CHERRY));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.South, 0.8f, LegendAlignment.Rigth));
		TitleLegendPlugin lgendL = new TitleLegendPlugin();
		lgendL.addLegend(legend);
		proj.registerPlugin(lgendL);

		ZoomBoxPlugin zoomTool = new ZoomBoxPlugin();
		proj.registerPlugin(zoomTool);
		// zoomTool.setBoxType(BoxType.BoxY);

		TranslatePlugin toolTranslate = new TranslatePlugin();
		proj.registerPlugin(toolTranslate);

		ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
		proj.registerPlugin(zoomWheel);

	}

}
